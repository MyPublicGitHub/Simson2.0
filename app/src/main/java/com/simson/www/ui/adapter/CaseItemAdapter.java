package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.mine.CaseBean;
import com.simson.www.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;

public class CaseItemAdapter extends BaseQuickAdapter<CaseBean, BaseViewHolder> {

    public CaseItemAdapter(@Nullable List<CaseBean> data) {
        super(R.layout.item_case_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CaseBean item) {
        helper.setText(R.id.tv_technique, "种植技术："+item.getHair_planting_technique());
        helper.setText(R.id.tv_title, "项目："+item.getItem_name());
        helper.setText(R.id.tv_content, "毛囊数量："+item.getHair_follicles_number());

        helper.setText(R.id.tv_name, item.getCase_name()+"");
        helper.setText(R.id.tv_browse, item.getBrowse()+"");

        BGANinePhotoLayout ninePhotoLayout = helper.getView(R.id.npl_item_moment_photos);
        ninePhotoLayout.setData((ArrayList<String>) item.getPictures());

        GlideUtils.with(item.getPictures().get(0),helper.getView(R.id.iv_header));
    }
}
