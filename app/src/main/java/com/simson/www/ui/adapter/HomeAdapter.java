package com.simson.www.ui.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.home.IndexSynchysisBean;
import com.simson.www.ui.community.circle.detail.FriendCircleDetailActivity;
import com.simson.www.ui.community.knowledge.detail.WebViewActivity;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class HomeAdapter extends BaseMultiItemQuickAdapter<IndexSynchysisBean, BaseViewHolder> {

    public HomeAdapter(@Nullable List<IndexSynchysisBean> data) {
        super(data);
        addItemType(IndexSynchysisBean.friendsCircle, R.layout.item_home_friend_circle);
        addItemType(IndexSynchysisBean.popularization, R.layout.item_home_popularization);
    }

    @Override
    protected void convert(BaseViewHolder helper, IndexSynchysisBean item) {
        if (IndexSynchysisBean.friendsCircle == helper.getItemViewType()) {
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
            helper.setText(R.id.tv_title, item.getContent() + "");
            helper.setText(R.id.tv_comments, " 评论 " + item.getComments());
            helper.setText(R.id.tv_praises, " 赞 " + item.getPraises());
        } else if (IndexSynchysisBean.popularization == helper.getItemViewType()) {
            GlideUtils.with(item.getHospital_icon(), helper.getView(R.id.iv_header));
            helper.setText(R.id.tv_name, item.getHospital_name() + "");
            helper.setText(R.id.tv_date, item.getCreate_time() + "");
            if (item.getIs_follow() == 0) {
                helper.setText(R.id.tv_follow, "+ 关注");

            } else {
                helper.setText(R.id.tv_follow, "已关注");
            }
            RecyclerView recyclerView = helper.getView(R.id.recyclerView);
            recyclerView.setClickable(false);
            recyclerView.setFocusable(false);
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
                mContext.startActivity(new Intent(mContext, WebViewActivity.class)
                        .putExtra(Const.WEB_VIEW_TITLE, "科普详情")
                        .putExtra(Const.WEB_VIEW_URL, item.getLink_url()));
            });

            /*BGANinePhotoLayout ninePhotoLayout = helper.getView(R.id.bga);
            ninePhotoLayout.setData((ArrayList<String>) item.getPictures());*/
            helper.setText(R.id.tv_title, item.getTitle() + "");
            helper.setText(R.id.tv_browse, "  阅读  " + item.getBrowse());
            helper.setText(R.id.tv_praises, "  赞  " + item.getPraises());
            helper.addOnClickListener(R.id.tv_follow);
        }
    }
}
