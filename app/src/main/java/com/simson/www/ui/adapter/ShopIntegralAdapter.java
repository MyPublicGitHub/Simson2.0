package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class ShopIntegralAdapter extends BaseQuickAdapter<ShopListBean, BaseViewHolder> {

    public ShopIntegralAdapter(@Nullable List<ShopListBean> data) {
        super(R.layout.item_shop_integral, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopListBean item) {
        GlideUtils.with(item.getItem_icon(),helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_title,item.getItem_name()+"");
        helper.setText(R.id.tv_integral,item.getItem_point()+"");
    }
}
