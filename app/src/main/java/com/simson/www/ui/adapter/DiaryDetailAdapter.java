package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.net.bean.community.DiaryDetailAppendBean;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;
import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;

public class DiaryDetailAdapter extends BaseQuickAdapter<DiaryDetailAppendBean,BaseViewHolder> {
    BGANinePhotoLayout.Delegate delegate;

    public DiaryDetailAdapter(@Nullable List<DiaryDetailAppendBean> data, BGANinePhotoLayout.Delegate delegates) {
        super(R.layout.item_diary_detail,data);
        delegate = delegates;
    }

    @Override
    protected void convert(BaseViewHolder helper,DiaryDetailAppendBean bean) {

        helper.setText(R.id.tv_day, bean.getDays());
        helper.setText(R.id.tv_content, bean.getContent());

        BGANinePhotoLayout ninePhotoLayout = helper.getView(R.id.npl_item_moment_photos);
        ninePhotoLayout.setDelegate(delegate);
        ninePhotoLayout.setData((ArrayList<String>) bean.getDiary_picture());
    }
}