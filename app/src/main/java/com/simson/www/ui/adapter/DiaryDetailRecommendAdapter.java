package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.community.DiaryDetailRecommendBean;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class DiaryDetailRecommendAdapter extends BaseQuickAdapter<DiaryDetailRecommendBean, BaseViewHolder> {

    public DiaryDetailRecommendAdapter(@Nullable List<DiaryDetailRecommendBean> data) {
        super(R.layout.item_diary_detail_recommend, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DiaryDetailRecommendBean bean) {
        GlideUtils.with(bean.getCustomer_head(), helper.getView(R.id.iv_header));
        GlideUtils.with(bean.getDiary_picture(), helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_count, bean.getCounts() + "篇日记");
        helper.setText(R.id.tv_content, bean.getContent() + "");
        helper.setText(R.id.tv_name, bean.getCustomer_name() + "");
        helper.setText(R.id.tv_browse, bean.getBrowse() + "");

    }
}