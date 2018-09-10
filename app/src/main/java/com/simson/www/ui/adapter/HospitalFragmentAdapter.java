package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;

import java.util.List;

public class HospitalFragmentAdapter extends BaseQuickAdapter<BaseBean, BaseViewHolder> {

    public HospitalFragmentAdapter(@Nullable List<BaseBean> data) {
        super(R.layout.item_hospital_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {

//        Glide.with(mContext).load(item.getImg_path())
//                .apply(new RequestOptions()
//                        .placeholder(R.mipmap.ic_placeholder)
//                        .error(R.mipmap.ic_error))
//                .into((ImageView) helper.getView(R.id.iv_image));
//        helper.setText(R.id.tv_title, item.getName());
//        helper.setText(R.id.tv_content, item.getHospital_name());
//        helper.setText(R.id.tv_original, item.getOriginal_price() + "");
//        TextView textView = helper.getView(R.id.tv_original);
//        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        helper.setText(R.id.tv_present, item.getPresent_price() + " ");
        //helper.setText(R.id.tv_buy,"已预约："+item.getBuyNum());
    }
}
