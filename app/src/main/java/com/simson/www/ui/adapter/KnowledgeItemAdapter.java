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
import com.simson.www.net.bean.community.PopularizationBean;
import com.simson.www.ui.community.circle.detail.FriendCircleDetailActivity;
import com.simson.www.ui.community.knowledge.detail.WebViewActivity;
import com.simson.www.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;

public class KnowledgeItemAdapter extends BaseQuickAdapter<PopularizationBean, BaseViewHolder> {

    public KnowledgeItemAdapter(@Nullable List<PopularizationBean> data) {
        super(R.layout.item_knowledge_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PopularizationBean item) {
        GlideUtils.with(item.getHospital_icon(), helper.getView(R.id.iv_header));
        helper.setText(R.id.tv_name, item.getHospital_name() + "");
        helper.setText(R.id.tv_date, item.getCreate_time() + "");
        if (item.getIs_follow() == 0) {
            helper.setText(R.id.tv_follow, "+ 关注");

        } else {
            helper.setText(R.id.tv_follow, "已关注");
        }
        helper.addOnClickListener(R.id.tv_follow);
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
            mContext. startActivity(new Intent(mContext, WebViewActivity.class)
                    .putExtra(Const.WEB_VIEW_TITLE, "科普详情")
                    .putExtra(Const.WEB_VIEW_URL,item.getLink_url()));
        });
        /*BGANinePhotoLayout ninePhotoLayout = helper.getView(R.id.bga);
        //ninePhotoLayout.setDelegate(delegate);
        ninePhotoLayout.setData((ArrayList<String>) item.getPictures());*/
        helper.setText(R.id.tv_title, item.getTitle() + "");
        //helper.setText(R.id.tv_content,item.getTitle()+"");
        helper.setText(R.id.tv_browse, " 阅读 " + item.getBrowse());
//        helper.setText(R.id.tv_comments, item.getComments() + "评论");
        helper.setText(R.id.tv_praises, " 赞 " + item.getPraises());
    }
}
