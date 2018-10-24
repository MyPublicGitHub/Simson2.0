package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.home.CauseListBean;
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
        BGANinePhotoLayout ninePhotoLayout = helper.getView(R.id.bga);
        //ninePhotoLayout.setDelegate(delegate);
        ninePhotoLayout.setData((ArrayList<String>) item.getPictures());
        helper.setText(R.id.tv_title, item.getTitle() + "");
        //helper.setText(R.id.tv_content,item.getTitle()+"");
        helper.setText(R.id.tv_browse, item.getBrowse() + "阅读");
        //helper.setText(R.id.tv_comments, item.getComments() + "评论");
        helper.setText(R.id.tv_praises, item.getPraises() + "赞");
    }
}
