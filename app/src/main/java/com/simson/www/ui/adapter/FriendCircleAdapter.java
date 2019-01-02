package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.community.FriendsCircleBean;
import com.simson.www.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;

public class FriendCircleAdapter extends BaseQuickAdapter<FriendsCircleBean, BaseViewHolder> {

    public FriendCircleAdapter(@Nullable List<FriendsCircleBean> data) {
        super(R.layout.item_firend_circle, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendsCircleBean item) {
        GlideUtils.with(item.getCustomer_head(), helper.getView(R.id.iv_header));
        helper.setText(R.id.tv_name, item.getCustomer_name() + "");
        helper.setText(R.id.tv_date, item.getCreate_time() + "");

        BGANinePhotoLayout ninePhotoLayout = helper.getView(R.id.bga);
        ninePhotoLayout.setData((ArrayList<String>) item.getPictures());
        helper.setText(R.id.tv_content, item.getContent() + "");
        helper.setText(R.id.tv_comments, " 评论 " + item.getComments());
        helper.setText(R.id.tv_praises, " 赞 " + item.getPraises());

    }
}
