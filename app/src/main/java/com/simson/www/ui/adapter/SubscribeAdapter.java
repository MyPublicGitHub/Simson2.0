package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.mine.SubscribeListBean;

import java.util.List;

public class SubscribeAdapter extends BaseQuickAdapter<SubscribeListBean, BaseViewHolder> {

    public SubscribeAdapter(@Nullable List<SubscribeListBean> data) {
        super(R.layout.item_subscribe, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubscribeListBean item) {
        helper.setText(R.id.tv_hospital, item.getHospital_name() + "");
        helper.setText(R.id.tv_project, item.getType_name() + "");
        helper.setText(R.id.tv_date, item.getSubscribe_date() + " " + item.getSubscribe_time());
        helper.setText(R.id.tv_friend, TextUtils.isEmpty(item.getAccompany_friends()) ? "无" : item.getAccompany_friends());

    }
}
