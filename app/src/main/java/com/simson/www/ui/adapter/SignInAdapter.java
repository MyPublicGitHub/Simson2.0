package com.simson.www.ui.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.mine.SignPageBean;

import java.util.List;

public class SignInAdapter extends BaseQuickAdapter<SignPageBean.DaysBean, BaseViewHolder> {

    public SignInAdapter(@Nullable List<SignPageBean.DaysBean> data) {
        super(R.layout.item_sign_in, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, SignPageBean.DaysBean item) {
        if (item.getSign().equals("0")) {
            helper.setBackgroundRes(R.id.item_sign_in_round_tv, R.drawable.round_no_sign_in_bg);
            helper.setTextColor(R.id.item_sign_in_round_tv, Color.parseColor("#8F9AA8"));
        } else if (item.getSign().equals("1")) {
            helper.setBackgroundRes(R.id.item_sign_in_round_tv, R.drawable.round_sign_in_bg);
            helper.setTextColor(R.id.item_sign_in_round_tv, Color.parseColor("#FFFFFF"));
        }
        helper.setText(R.id.item_sign_in_day_tv, item.getDay() + "号");
        int position = helper.getLayoutPosition();
    }
}
