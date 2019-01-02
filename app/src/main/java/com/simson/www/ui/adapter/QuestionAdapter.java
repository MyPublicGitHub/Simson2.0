package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.community.QuestionsBean;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class QuestionAdapter extends BaseQuickAdapter<QuestionsBean, BaseViewHolder> {

    public QuestionAdapter(@Nullable List<QuestionsBean> data) {
        super(R.layout.item_expert_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QuestionsBean item) {
        GlideUtils.with(item.getCustomer_head(), helper.getView(R.id.iv_header));
        helper.setText(R.id.tv_name, item.getCustomer_name());
        helper.setText(R.id.tv_date, item.getCreate_time());
        helper.setText(R.id.tv_question_content, item.getQuestions_content());
        if (item.getAnswers() != null && item.getAnswers().size() > 0) {
            helper.setText(R.id.tv_answer_content, item.getAnswers().get(0).getAnswer_content());
        } else {
            helper.setText(R.id.tv_answer_content, "");
        }
        if (item.getIs_follow() == 0) {
            helper.setText(R.id.tv_follow, "+关注");

        } else {
            helper.setText(R.id.tv_follow, "已关注");
        }
        helper.addOnClickListener(R.id.tv_follow);
        if (item.getPictures() != null && item.getPictures().size() > 0) {
            helper.getView(R.id.iv_image).setVisibility(View.VISIBLE);
            GlideUtils.with(item.getPictures(), helper.getView(R.id.iv_image));
        } else {
            helper.getView(R.id.iv_image).setVisibility(View.GONE);
        }
        helper.setText(R.id.tv_browse, " 阅读 " + item.getBrowse());
//        helper.setText(R.id.tv_comments, " 评论 "+item.getComments());
        helper.setText(R.id.tv_praises, " 赞 " + item.getPraises());
    }
}

