package com.shsy.mydemo.utils;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.LruCache;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by 申尚宇 on 2016/10/20.
 */

public class ImageLoader {
    private static ImageLoader mInstence;
    /**
     * 图片缓存对象
     */
    private LruCache<String, Bitmap> mLruCache;
    /**
     * 线程池
     */
    private ExecutorService mThreadPool;
    private static final int DEFAULT_THREAD_COUNT = 1;

    /**
     * 队列调度方式
     */
    private Type mType = Type.LIFO;

    /**
     * 任务队列
     */
    private LinkedList<Runnable> mTaskMqueue;
    /**
     * 后台轮训线程
     */
    private Thread mPoolThread;
    private Handler mPoolThreadHandler;
    /**
     * UI线程Handler
     */
    private Handler mUIHandler;
    /**
     * 信号量
     */
    private Semaphore mSemaphorePoolThreadHandler = new Semaphore(0);
    private Semaphore mSemaphoreThreadPool;

    public enum Type {
        FIFO, LIFO
    }


    private ImageLoader(int threadCount, Type type) {
        init(threadCount, type);
    }

    public static ImageLoader getInstence() {
        if (mInstence == null) {
            synchronized (ImageLoader.class) {
                if (mInstence == null) {
                    mInstence = new ImageLoader(DEFAULT_THREAD_COUNT, Type.LIFO);
                }
            }
        }
        return mInstence;
    }

    /**
     * 初始化ImageLoader
     *
     * @param threadCount
     * @param type
     */
    private void init(int threadCount, Type type) {
        mPoolThread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                mPoolThreadHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        // 取出一个线程执行
                        mThreadPool.execute(getTask());
                        try {
                            mSemaphoreThreadPool.acquire();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                mSemaphorePoolThreadHandler.release();// 释放一个信号量
                Looper.loop();
            }
        };
        mPoolThread.start();
        // 应用可以使用的最大的内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheMemory = maxMemory / 8;
        mLruCache = new LruCache<String, Bitmap>(cacheMemory) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
        // 线程池
        mThreadPool = Executors.newFixedThreadPool(threadCount);
        mTaskMqueue = new LinkedList<>();
        // 队列轮询方式
        mType = type;

        mSemaphoreThreadPool = new Semaphore(threadCount);
    }

    /**
     * 从任务队列去除一个runnable
     *
     * @return
     */
    private Runnable getTask() {
        if (mType == Type.FIFO) {
            return mTaskMqueue.removeFirst();
        } else if (mType == Type.LIFO) {
            return mTaskMqueue.removeLast();
        }
        return null;
    }

    /**
     * 加载图片到imageView
     *
     * @param path
     * @param imageView
     */
    public void loadImage(final String path, final ImageView imageView) {
        imageView.setTag(path);

        if (mUIHandler == null) {
            mUIHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    // 获取得到的图片设置到Imageview中
                    ImageBeanHolder holder = (ImageBeanHolder) msg.obj;
                    Bitmap bm = holder.bitmap;
                    ImageView imageView = holder.imageView;
                    String path = holder.path;
                    // 判断Tag是否相同,防止图片错位
                    if (TextUtils.equals((String) imageView.getTag(), path)) {
                        imageView.setImageBitmap(bm);
                    }
                }
            };
        }

        Bitmap bm = getBitmapFromLruCache(path);

        if (bm != null) { // 在LruCache中找到了图片,通知uihandler加载图片
            refreshBitmap(path, imageView, bm);
        } else {
            addTask(new Runnable() {
                @Override
                public void run() {
                    // 加载图片
                    // 获得图片需要显示的大小
                    ImageSize imageSize = getImageViewSize(imageView);
                    // 压缩图片
                    Bitmap bm = decodeSampleBitmapFromPath(path, imageSize.width, imageSize.height);
                    // 图片加入到缓存
                    addBitmapToLruCache(path, bm);
                    // 刷新图片
                    refreshBitmap(path, imageView, bm);

                    mSemaphoreThreadPool.release();
                }
            });
        }
    }

    /**
     * 刷新图片到界面
     *
     * @param path
     * @param imageView
     * @param bm
     */
    private void refreshBitmap(String path, ImageView imageView, Bitmap bm) {
        Message msg = Message.obtain();
        msg.obj = new ImageBeanHolder(bm, imageView, path);
        mUIHandler.sendMessage(msg);
    }

    /**
     * 将图片加入缓存中
     *
     * @param path
     * @param bm
     */
    private void addBitmapToLruCache(String path, Bitmap bm) {
        if (getBitmapFromLruCache(path) == null) {
            if (bm != null) {
                mLruCache.put(path, bm);
            }
        }
    }

    /**
     * 根据图片需要显示的宽高对图片进行压缩
     *
     * @param path
     * @param width
     * @param height
     * @return
     */
    private Bitmap decodeSampleBitmapFromPath(String path, int width, int height) {
        // 获取图片宽高,不加载图片到内存
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = caculateInSampleSize(options, width, height);
        // 使用获取到的inSample再次解析图片
        options.inJustDecodeBounds = false;
        Bitmap bm = BitmapFactory.decodeFile(path, options);
        return bm;
    }

    /**
     * 根据需求的宽高和实际的宽高计算SampleSIze
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private int caculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;

        int inSampleSize = 1;

        if (width > reqWidth || height > reqHeight) {
            int widthRadio = Math.round(width * 1.0f / reqWidth);
            int heightRadio = Math.round(height * 1.0f / reqHeight);

            inSampleSize = Math.max(widthRadio, heightRadio);
        }
        return inSampleSize;
    }

    /**
     * 根据ImageView获取适当的压缩的宽和高
     *
     * @param imageView
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private ImageSize getImageViewSize(ImageView imageView) {
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        int width = imageView.getWidth();// 获取iv的实际宽度
        if (width <= 0) {
            width = lp.width;// 获取iv在layout中声明的宽度
        }
        if (width <= 0) {
            width = imageView.getMaxWidth();// 检查最大值
        }
        if (width <= 0) {
            width = imageView.getContext().getResources().getDisplayMetrics().widthPixels;// 使用屏幕的宽度
        }

        int height = imageView.getHeight();// 获取iv的实际高度
        if (height <= 0) {
            height = lp.width;// 获取iv在layout中声明的高度
        }
        if (height <= 0) {
            height = imageView.getMaxWidth();// 检查最大值
        }
        if (height <= 0) {
            height = imageView.getContext().getResources().getDisplayMetrics().heightPixels;// 使用屏幕的高度
        }
        return new ImageSize(width, height);
    }

    /**
     * 将线程加入队列并通知mpoolhandler处理
     *
     * @param runnable
     */
    private synchronized void addTask(Runnable runnable) {
        mTaskMqueue.add(runnable);
        try {
            if (mPoolThreadHandler == null) {
                mSemaphorePoolThreadHandler.acquire();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mPoolThreadHandler.sendEmptyMessage(0x520);
    }

    /**
     * 在LruCache缓存中查找是否有该图片
     *
     * @param path
     * @return
     */

    private Bitmap getBitmapFromLruCache(String path) {
        return mLruCache.get(path);
    }

    private class ImageSize {
        int width;
        int height;

        public ImageSize() {
        }

        public ImageSize(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    private class ImageBeanHolder {
        Bitmap bitmap;
        ImageView imageView;
        String path;

        public ImageBeanHolder() {
        }

        public ImageBeanHolder(Bitmap bitmap, ImageView imageView, String path) {
            this.bitmap = bitmap;
            this.imageView = imageView;
            this.path = path;
        }
    }
}
