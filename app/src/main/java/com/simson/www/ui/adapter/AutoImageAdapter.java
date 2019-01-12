package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.home.ImageBean;
import com.simson.www.utils.GlideUtils;
import com.simson.www.utils.LogUtils;

import java.util.List;

public class AutoImageAdapter extends BaseMultiItemQuickAdapter<ImageBean, BaseViewHolder> {

    public AutoImageAdapter(@Nullable List<ImageBean> data) {
        super(data);
        addItemType(0, R.layout.list_empty_view);
        addItemType(1, R.layout.item_image_one);
        addItemType(2, R.layout.item_image_two);
        addItemType(3, R.layout.item_image_recyvlerview);
    }

    @Override
    protected void convert(BaseViewHolder helper, ImageBean item) {
        LogUtils.e("AutoImageAdapter::::" + helper.getItemViewType());
        //LogUtils.e("GlideUtils::::" + item.images.get(0));
        if (1 == helper.getItemViewType()) {
            GlideUtils.with(item.images.get(0), helper.getView(R.id.image));
        } else if (2 == helper.getItemViewType()) {
            GlideUtils.with(item.images.get(0), helper.getView(R.id.image1));
            GlideUtils.with(item.images.get(1), helper.getView(R.id.image2));
        } else if (3 == helper.getItemViewType()) {

        }
    }
}
