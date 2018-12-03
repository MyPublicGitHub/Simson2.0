package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.community.QuestionsDetailBean;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class QuestDetailAdapter extends BaseQuickAdapter<QuestionsDetailBean.AnswersBean, BaseViewHolder> {

    public QuestDetailAdapter(@Nullable List<QuestionsDetailBean.AnswersBean> data) {
        super(R.layout.item_question_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QuestionsDetailBean.AnswersBean item) {
        GlideUtils.with(item.getDoctor_head(), helper.getView(R.id.iv_header));
        helper.setText(R.id.tv_name, item.getDoctor_name() + "");
        helper.setText(R.id.tv_position, item.getPosition() + "");
        helper.setText(R.id.tv_date, item.getCreate_time() + "");
        helper.setText(R.id.tv_content, item.getAnswer_content() + "");

    }
}
