package com.simson.www.ui.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.home.CauseListBean;
import com.simson.www.net.bean.home.ImageBean;
import com.simson.www.ui.community.circle.detail.FriendCircleDetailActivity;
import com.simson.www.ui.community.knowledge.detail.WebViewActivity;
import com.simson.www.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;

public class CauseAdapter extends BaseQuickAdapter<CauseListBean, BaseViewHolder> {

    public CauseAdapter(@Nullable List<CauseListBean> data) {
        super(R.layout.item_knowledge_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CauseListBean item) {

        GlideUtils.with(item.getHospital_icon(), helper.getView(R.id.iv_header));
        helper.setText(R.id.tv_name, item.getHospital_name() + "");
        helper.setText(R.id.tv_date, item.getCreate_time() + "");
        if (item.getIs_follow() == 0) {
            helper.setText(R.id.tv_follow, "+ 关注");

        } else {
            helper.setText(R.id.tv_follow, "已关注");
        }
        //BGANinePhotoLayout ninePhotoLayout = helper.getView(R.id.bga);
        //ninePhotoLayout.setDelegate(delegate);
        //ninePhotoLayout.setData((ArrayList<String>) item.getPictures());
        //自动匹配图片
        ImageBean bean = new ImageBean();
        bean.images = item.getPictures();
        List<ImageBean> imageBeans = new ArrayList<>();
        imageBeans.add(bean);
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        AutoImageAdapter adapters = new AutoImageAdapter(imageBeans, (adapter, view, position) -> {
            mContext.startActivity(new Intent(mContext, WebViewActivity.class)
                    .putExtra(Const.WEB_VIEW_TITLE, "脱发原因")
                    .putExtra(Const.WEB_VIEW_URL, item.getAlopecia_cause_link()));
        });
        recyclerView.setAdapter(adapters);
        adapters.setOnItemClickListener((adapter, view1, position) -> {
            mContext.startActivity(new Intent(mContext, WebViewActivity.class)
                    .putExtra(Const.WEB_VIEW_TITLE, "脱发原因")
                    .putExtra(Const.WEB_VIEW_URL, item.getAlopecia_cause_link()));
        });
        helper.setText(R.id.tv_title, item.getTitle() + "");
        //helper.setText(R.id.tv_content,item.getTitle()+"");
        helper.setText(R.id.tv_browse, item.getBrowse() + "阅读");
        //helper.setText(R.id.tv_comments, item.getComments() + "评论");
        helper.setText(R.id.tv_praises, item.getPraises() + "赞");
    }
}
