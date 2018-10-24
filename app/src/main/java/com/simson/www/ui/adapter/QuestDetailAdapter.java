package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.community.FriendsCircleCommentBean;
import com.simson.www.net.bean.community.QuestionsDetailBean;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class QuestDetailAdapter extends BaseQuickAdapter<QuestionsDetailBean.AnswersBean, BaseViewHolder> {

    public QuestDetailAdapter(@Nullable List<QuestionsDetailBean.AnswersBean> data) {
        super(R.layout.item_question_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QuestionsDetailBean.AnswersBean item) {
        /**
         * answer_id : 113
         * answer_content : 黑芝麻泡水喝可以保养头发
         * doctor_id : 111401
         * doctor_name : 吴望月
         * doctor_head : https://appapi.maofa.com/userfiles/doctor/head/guangzhou/wuwangyue.png
         * position : 医师
         * create_time : 2018-09-27 10:49
         */
        GlideUtils.with(item.getDoctor_head(),helper.getView(R.id.iv_header));
        helper.setText(R.id.tv_name,item.getDoctor_name()+"");
        helper.setText(R.id.tv_position,item.getPosition()+"");
        helper.setText(R.id.tv_date,item.getCreate_time()+"");
        helper.setText(R.id.tv_content,item.getAnswer_content()+"");

    }
}
