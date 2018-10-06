package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.mine.AddressBean;
import com.simson.www.net.bean.mine.ShopCartBean;
import com.simson.www.ui.mine.cart.ShopCartActivity;
import com.simson.www.utils.GlideUtils;
import com.simson.www.utils.LogUtils;

import java.util.List;

public class ShopCartAdapter extends BaseQuickAdapter<ShopCartBean, BaseViewHolder> {
    ShopCartActivity mActivity;
    public ShopCartAdapter(@Nullable List<ShopCartBean> data,ShopCartActivity activity) {
        super(R.layout.item_shop_cart, data);
        mActivity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCartBean item) {
        TextView tvNumber = helper.getView(R.id.tv_number);
        TextView tvReduce = helper.getView(R.id.tv_reduce);

        GlideUtils.with(item.getItem_icon(),helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_title, item.getItem_name());
        helper.setText(R.id.tv_present, "￥" + String.valueOf(item.getPresent_price()));
        helper.setText(R.id.tv_point, String.valueOf(item.getItem_point())+"积分");


        CheckBox checkBox = helper.getView(R.id.checkbox);
        checkBox.setOnCheckedChangeListener((compoundButton, isCheck) -> {
            item.isCheck = isCheck;
        });
        checkBox.setChecked(item.isCheck);
        helper.addOnClickListener(R.id.tv_reduce);
        helper.addOnClickListener(R.id.tv_add);

        tvNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int number = Integer.parseInt(editable.toString());
//                if (number <= 0) {
//                    tvNumber.setText("1");
//                    number = 1;
//                }
                if (number == 1) {
                    tvReduce.setBackgroundColor(tvReduce.getContext().getResources().getColor(R.color.colorBlack_6));
                    tvReduce.setClickable(false);
                } else {
                    tvReduce.setBackgroundColor(tvReduce.getContext().getResources().getColor(R.color.colorPrimary));
                    tvReduce.setClickable(true);
                }

                double prince = number * item.getPresent_price();
                int point = number * item.getItem_point();
                item.setBuy_num(number);
                item.priceUser = prince;
                item.pointUser = point;
                LogUtils.d("number:" + number);
            }
        });
        if (item.getBuy_num() == 0) {
            tvNumber.setText(1 + "");
            item.setBuy_num(1);
        } else {
            tvNumber.setText(item.getBuy_num() + "");
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                item.isCheck = b;
                if (b == false) {
                    mActivity.setCheckAll(false);
                    mActivity.isCheckAll = false;
                }
                mActivity.money();
            }
        });    }
}
