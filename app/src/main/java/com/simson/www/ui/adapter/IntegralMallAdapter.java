package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class IntegralMallAdapter extends BaseQuickAdapter<ShopListBean, BaseViewHolder> {

    public IntegralMallAdapter(@Nullable List<ShopListBean> data) {
        super(R.layout.item_integra, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopListBean item) {
        GlideUtils.with(item.getItem_icon(), helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_name, item.getItem_name() + "");
        helper.setText(R.id.tv_point, "积分：" + item.getItem_point() + "");
    }
}