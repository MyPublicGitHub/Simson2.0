package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.home.IndexSynchysisBean;
import com.simson.www.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;

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
            BGANinePhotoLayout ninePhotoLayout = helper.getView(R.id.bga);
            ninePhotoLayout.setData((ArrayList<String>) item.getPictures());
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
            BGANinePhotoLayout ninePhotoLayout = helper.getView(R.id.bga);
            ninePhotoLayout.setData((ArrayList<String>) item.getPictures());
            helper.setText(R.id.tv_title, item.getTitle() + "");
            helper.setText(R.id.tv_browse, "  阅读  " + item.getBrowse());
            helper.setText(R.id.tv_praises, "  赞  " + item.getPraises());
            helper.addOnClickListener(R.id.tv_follow);
        }
    }
}
