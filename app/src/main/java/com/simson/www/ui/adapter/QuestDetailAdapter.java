package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.community.FriendsCircleBean;
import com.simson.www.net.bean.community.FriendsCircleCommentBean;
import com.simson.www.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;

public class FriendCircleCommentAdapter extends BaseQuickAdapter<FriendsCircleCommentBean, BaseViewHolder> {

    public FriendCircleCommentAdapter(@Nullable List<FriendsCircleCommentBean> data) {
        super(R.layout.item_firend_circle_comment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendsCircleCommentBean item) {
        GlideUtils.with(item.getCustomer_head(),helper.getView(R.id.iv_header));
        helper.setText(R.id.tv_name,item.getCustomer_name()+"");
        helper.setText(R.id.tv_date,item.getCreate_time()+"");
        helper.setText(R.id.tv_content,item.getContent()+"");

    }
}
