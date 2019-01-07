package com.simson.www.ui.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.community.FriendsCircleBean;
import com.simson.www.ui.community.circle.detail.FriendCircleDetailActivity;
import com.simson.www.ui.community.knowledge.detail.WebViewActivity;
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
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager;
        if (item.getPictures().size() == 1) {
            layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);

        } else {
            layoutManager = new GridLayoutManager(mContext, 2, LinearLayoutManager.VERTICAL, false);
        }
        recyclerView.setLayoutManager(layoutManager);
        HomeAdapterItemAdapter adapters = new HomeAdapterItemAdapter(item.getPictures());
        recyclerView.setAdapter(adapters);
        adapters.setOnItemClickListener((adapter, view1, position) -> {
            String id = item.getFriends_circle_id();

            mContext.startActivity(new Intent(mContext, FriendCircleDetailActivity.class).putExtra("id", id));
        });
        /*BGANinePhotoLayout ninePhotoLayout = helper.getView(R.id.bga);
        ninePhotoLayout.setData((ArrayList<String>) item.getPictures());*/
        helper.setText(R.id.tv_content, item.getContent() + "");
        helper.setText(R.id.tv_comments, " 评论 " + item.getComments());
        helper.setText(R.id.tv_praises, " 赞 " + item.getPraises());

    }
}
