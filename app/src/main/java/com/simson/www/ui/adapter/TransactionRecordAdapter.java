package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.mine.TransactionRecordBean;

import java.util.List;

public class TransactionRecordAdapter extends BaseQuickAdapter<TransactionRecordBean, BaseViewHolder> {

    public TransactionRecordAdapter(@Nullable List<TransactionRecordBean> data) {
        super(R.layout.item_shop_commodity, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TransactionRecordBean item) {
//        GlideUtils.with(item.getItem_icon(),helper.getView(R.id.iv_image));
//        helper.setText(R.id.tv_title,item.getItem_name()+"");
//        //helper.setText(R.id.tv_content,"");
//        helper.setText(R.id.tv_present,"￥"+item.getPresent_price());
//        helper.setText(R.id.tv_original,"￥"+item.getOriginal_price());
//        TextView textView = helper.getView(R.id.tv_original);
//        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
