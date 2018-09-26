package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.mine.AddressBean;
import com.simson.www.net.bean.mine.ShopCartBean;

import java.util.List;

public class ShopCartAdapter extends BaseQuickAdapter<ShopCartBean, BaseViewHolder> {

    public ShopCartAdapter(@Nullable List<ShopCartBean> data) {
        super(R.layout.item_hda_case_diary, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCartBean item) {
/*
        GlideUtils.with(item.getCustomer_head(),helper.getView(R.id.iv_header));
        helper.setText(R.id.tv_name, item.getCustomer_name());
        helper.setText(R.id.tv_date, item.getIssue_time());
        if (item.getIs_follow() == 0){
            helper.setText(R.id.tv_follow, "关注");

        }else {
            helper.setText(R.id.tv_follow, "已关注");
        }
        helper.addOnClickListener(R.id.tv_follow);
        GlideUtils.with(item.getShu_qian(),helper.getView(R.id.iv_before));
        GlideUtils.with(item.getShu_hou(),helper.getView(R.id.iv_after));

        helper.setText(R.id.tv_content, item.getContent());
        helper.setText(R.id.tv_price, "￥"+item.getPrice());

        helper.setText(R.id.tv_browse, item.getBrowse() + "阅读");
        helper.setText(R.id.tv_comments, item.getComments() + "评论");
        helper.setText(R.id.tv_praises,item.getPraises()+"赞");*/
    }
}
