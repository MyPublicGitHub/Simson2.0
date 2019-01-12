package com.simson.www.ui.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.home.ImageBean;
import com.simson.www.net.bean.shop.BigEventBean;
import com.simson.www.ui.community.knowledge.detail.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

public class HospitalFragmentAdapter extends BaseQuickAdapter<BigEventBean, BaseViewHolder> {

    public HospitalFragmentAdapter(@Nullable List<BigEventBean> data) {
        super(R.layout.item_hospital_fragment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BigEventBean item) {
        helper.setText(R.id.tv_title, item.getEvent_date() + "");
        helper.setText(R.id.tv_content, "" + item.getTitle());

        ImageBean bean = new ImageBean();
        bean.images = item.getPictures();
        List<ImageBean> imageBeans = new ArrayList<>();
        imageBeans.add(bean);
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        AutoImageAdapter adapters = new AutoImageAdapter(imageBeans);
        recyclerView.setAdapter(adapters);
        adapters.setOnItemClickListener((adapter, view1, position) -> {
            String url = item.getLink_url();
            mContext.startActivity(new Intent(mContext, WebViewActivity.class)
                    .putExtra(Const.WEB_VIEW_TITLE, item.getTitle())
                    .putExtra(Const.WEB_VIEW_URL, url));
        });
    }


}
