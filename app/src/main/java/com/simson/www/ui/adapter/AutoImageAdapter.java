package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.home.ImageBean;
import com.simson.www.utils.GlideUtils;
import com.simson.www.utils.LogUtils;

import java.util.List;

public class AutoImageAdapter extends BaseMultiItemQuickAdapter<ImageBean, BaseViewHolder> {
    OnItemClickListener onItemClickListener;

    public AutoImageAdapter(@Nullable List<ImageBean> data, OnItemClickListener onItemClickListener) {
        super(data);
        addItemType(ImageBean.NULL, R.layout.list_empty_view);
        addItemType(ImageBean.ONE, R.layout.item_image_one);
        addItemType(ImageBean.TOW, R.layout.item_image_two);
        addItemType(ImageBean.MORE, R.layout.item_image_recyvlerview);
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, ImageBean item) {
       // LogUtils.e("AutoImageAdapter::::" + helper.getItemViewType());
        //LogUtils.e("GlideUtils::::" + item.images.get(0));
        if (ImageBean.ONE == helper.getItemViewType()) {
            GlideUtils.with(item.images.get(0), helper.getView(R.id.image));
        } else if (ImageBean.TOW == helper.getItemViewType()) {
            GlideUtils.with(item.images.get(0), helper.getView(R.id.image1));
            GlideUtils.with(item.images.get(1), helper.getView(R.id.image2));
        } else if (ImageBean.MORE == helper.getItemViewType()) {
            RecyclerView recyclerView = helper.getView(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            AutoImageItemAdapter adapters = new AutoImageItemAdapter(item.images);
            adapters.setOnItemClickListener(onItemClickListener);
            recyclerView.setAdapter(adapters);
        } else {
            helper.setVisible(R.id.ll_emp, false);
        }
    }
}
