package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;

import java.util.List;

public class HospitalActivityAdapter extends BaseQuickAdapter<BaseBean, BaseViewHolder> {

    public HospitalActivityAdapter(@Nullable List<BaseBean> data) {
        super(R.layout.item_hospital_info, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {
//        Glide.with(mContext).load(item.getAvatar())
//                .apply(new RequestOptions()
//                        .placeholder(R.mipmap.ic_placeholder)
//                        .error(R.mipmap.ic_error))
//                .into((ImageView) helper.getView(R.id.environment_iv));
//        helper.setText(R.id.tv_hospital_name, item.getName());
//        helper.setText(R.id.tv_content, item.getSynopsis());
//        helper.setText(R.id.tv_location, item.getLocation());
//        RatingStarView ratingStarView = helper.getView(R.id.hospital_rating);
//        ratingStarView.setRating(item.getTotal_points());
//        ratingStarView.setFocusable(false);
//        ratingStarView.setClickable(false);
//        helper.setText(R.id.tv_reserve, MessageFormat.format("{0}预约", item.getReserve_num()));
//        helper.setText(R.id.tv_case, MessageFormat.format("{0}案例", item.getTotal_case_num()));
    }
}
