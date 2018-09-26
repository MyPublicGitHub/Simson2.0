package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.community.QuestionsBean;

import java.util.List;

public class QuestionAdapter extends BaseQuickAdapter<QuestionsBean, BaseViewHolder> {

    public QuestionAdapter(@Nullable List<QuestionsBean> data) {
        super(R.layout.item_expert_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QuestionsBean item) {

    }
}

