package com.shsy.mydemo.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.shsy.mydemo.R;
import com.shsy.mydemo.bean.CountDownDayBean;
import com.shsy.mydemo.bean.CountDownDayItemBean;
import com.shsy.mydemo.databinding.FragmentDialogAddDayBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Shsy on 2016/11/27.
 * 添加倒计时日的dialogfragment
 */

public class AddDayDialogFragment extends DialogFragment {

    private FragmentDialogAddDayBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dialog_add_day, container, false);
        mBinding.setPresenter(new Presenter());
        mBinding.setBean(new CountDownDayBean());
        return mBinding.getRoot();
    }

    public class Presenter {
        /**
         * 选择日期
         */
        public void selectDay() {
            new DatePickerDialog(getContext(),
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            mBinding.getBean().setDay(i + "-" + i1 + "-" + i2);
                        }
                    },
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show();
        }

        /**
         * 选择时间
         */
        public void selectTime() {
            new TimePickerDialog(getContext(),
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {
                            String hour;
                            if (i < 10) hour = "0" + i;
                            else hour = "" + i;

                            String minuts;
                            if (i1 < 10) minuts = "0" + i1;
                            else minuts = "" + i1;

                            mBinding.getBean().setTime(hour + ":" + minuts);
                        }
                    },
                    Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                    Calendar.getInstance().get(Calendar.MINUTE),
                    true).show();
        }

        /**
         * 保存
         */
        public void save() {
            if (TextUtils.equals("选择日期", mBinding.getBean().getDay())
                    || TextUtils.equals("选择时间", mBinding.getBean().getTime())
                    || TextUtils.isEmpty(mBinding.getBean().getThing())) {

                Toast.makeText(getActivity().getApplicationContext(), "请填写全部资料", Toast.LENGTH_SHORT).show();
                return;
            }
            String date = mBinding.getBean().getDay() + " " + mBinding.getBean().getTime();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            try {
                // TODO: 2016/11/27 把时间和事件保存进入数据库
                Toast.makeText(getActivity().getApplicationContext(), sdf.format(new Date(sdf.parse(date).getTime())) + "", Toast.LENGTH_SHORT).show();

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
