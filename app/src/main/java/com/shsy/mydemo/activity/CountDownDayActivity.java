package com.shsy.mydemo.activity;

import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.shsy.mydemo.R;
import com.shsy.mydemo.adapter.CountDownDayAdapter;
import com.shsy.mydemo.base.BaseActivity;
import com.shsy.mydemo.bean.CountDownDayItemBean;
import com.shsy.mydemo.databinding.ActivityCountDownDayBinding;
import com.shsy.mydemo.db.utils.CountDownDayItemDBUtil;
import com.shsy.mydemo.fragment.AddDayDialogFragment;
import com.shsy.mydemo.utils.ThreadPoolManager;

import java.util.List;

/**
 * Created by Shsy on 2016/11/26.
 * 倒计时
 */

public class CountDownDayActivity extends BaseActivity<ActivityCountDownDayBinding> {
    private CountDownDayAdapter adapter;
    private boolean isNotify = false;
    private Runnable notifyRunnable = new Runnable() {
        @Override
        public void run() {
            while (isNotify) {
                SystemClock.sleep(1000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }
    };


    @Override
    protected int bindLayoutId() {
        return R.layout.activity_count_down_day;
    }

    @Override
    protected void initToolBar() {
        mBinding.toolbar.setTitle("CountDownDay");
        mBinding.toolbar.setNavigationIcon(R.mipmap.icon_back);
        setSupportActionBar(mBinding.toolbar);
        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountDownDayActivity.this.finish();
            }
        });
    }

    @Override
    protected void initData() {
        adapter = new CountDownDayAdapter(getApplicationContext());
        mBinding.setPresenter(new Presenter());
    }

    @Override
    protected void doBusiness() {
        mBinding.rvCountDown.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mBinding.rvCountDown.setAdapter(adapter);
        mBinding.rvCountDown.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int newY;
            int oldY;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                oldY = newY;
                newY += dy;

                if (oldY < newY) {// 上拉
                    mBinding.floatingActionButton.setVisibility(View.GONE);
                } else {// 下拉
                    mBinding.floatingActionButton.setVisibility(View.VISIBLE);
                }
            }
        });
        refreshData();
    }

    public class Presenter {
        /**
         * 添加一个倒计时的条目
         */
        public void addDay() {
            AddDayDialogFragment addDayDialogFragment = new AddDayDialogFragment();
            addDayDialogFragment.setOnDissmissListener(new AddDayDialogFragment.OnDismissListener() {
                @Override
                public void onDissmiss() {
                    refreshData();
                }
            });
            addDayDialogFragment.show(getSupportFragmentManager(), "0");
        }
    }

    /**
     * 刷新页面数据
     */
    private void refreshData() {
        List<CountDownDayItemBean> itemBeanList = CountDownDayItemDBUtil.selectAll();
        adapter.setList(itemBeanList);
        if (!isNotify)
            ThreadPoolManager.getInstance().addTask(notifyRunnable);
        isNotify = true;
    }

    @Override
    protected void onDestroy() {
        isNotify = false;
        super.onDestroy();
    }
}
