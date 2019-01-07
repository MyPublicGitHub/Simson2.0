package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.home.IndexSynchysisItemBean;

import java.util.List;

public class HomeAdapterItemAdapter2 extends BaseMultiItemQuickAdapter<IndexSynchysisItemBean, BaseViewHolder> {

    public HomeAdapterItemAdapter2(@Nullable List<IndexSynchysisItemBean> data) {
        super(data);
        addItemType(1, R.layout.item_home_friend_circle);
        addItemType(2, R.layout.item_home_popularization);
    }

    @Override
    protected void convert(BaseViewHolder helper, IndexSynchysisItemBean item) {

        item.getPictures();
        if (1 == helper.getItemViewType()) {

        } else if (2 == helper.getItemViewType()) {

        }
    }
}
