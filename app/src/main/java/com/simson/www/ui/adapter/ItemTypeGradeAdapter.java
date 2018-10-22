package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class ItemTypeGradeAdapter extends BaseQuickAdapter<ItemTypeBean, BaseViewHolder> {

    public ItemTypeGradeAdapter(@Nullable List<ItemTypeBean> data) {
        super(R.layout.item_item_type_grade, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemTypeBean item) {

        GlideUtils.with(item.getTypeIcon(), helper.getView(R.id.iv_header));
        helper.setText(R.id.tv_name, item.getTypeName());
    }
}
