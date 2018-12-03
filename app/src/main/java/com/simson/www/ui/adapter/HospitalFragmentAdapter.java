package com.simson.www.ui.adapter;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class HospitalFragmentAdapter extends BaseQuickAdapter<ShopListBean, BaseViewHolder> {

    public HospitalFragmentAdapter(@Nullable List<ShopListBean> data) {
        super(R.layout.item_hospital, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopListBean item) {
        GlideUtils.with(item.getItem_icon(), helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_title, item.getItem_name() + "");
        //helper.setText(R.id.tv_content,"");
        helper.setText(R.id.tv_subscribes, "已预约：" + item.getSubscribes());
        helper.setText(R.id.tv_present, "￥" + item.getPresent_price());
        helper.setText(R.id.tv_original, "￥" + item.getOriginal_price());
        TextView textView = helper.getView(R.id.tv_original);
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }


}
