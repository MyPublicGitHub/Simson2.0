package com.simson.www.ui.community.expert.detail;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.community.QuestionsDetailBean;
import com.simson.www.ui.adapter.QuestDetailAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.utils.GlideUtils;
import com.simson.www.widget.CircleImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;

public class QuestionDetailActivity extends BasePresenterActivity<QuestionDetailPresenter, QuestionDetailContract.View>
        implements QuestionDetailContract.View {


    @BindView(R.id.iv_header)
    CircleImageView ivHeader;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.bga)
    BGANinePhotoLayout bga;
    @BindView(R.id.tv_comments)
    TextView tvComments;
    @BindView(R.id.tv_praises)
    TextView tvPraises;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_follow)
    TextView tvFollow;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_question_detail;
    }

    QuestDetailAdapter adapter;

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuestDetailAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        //adapter.bindToRecyclerView(recyclerView);
        //adapter.setEmptyView(R.layout.list_empty_view);
    }

    @Override
    protected void initData() {
        mPresenter.questionsDetail();
    }

    @Override
    protected void getIntent(Intent intent) {
        questionsId = intent.getStringExtra("questionsId");
    }

    String questionsId;

    @Override
    public String questionsId() {
        return questionsId;
    }

    @Override
    public void questionsDetail(QuestionsDetailBean bean) {
        /**
         * questions_id : 1111
         * customer_id : 2018090115357871316905625
         * customer_name : 176****7732
         * customer_head : http://58.213.14.195:8081/upload/customer/201810/M7nnxi0yWKYR1xL0.png
         * questions_content : 如何保养头发
         * browse : 0
         * create_time : 2018-09-27
         * is_display : 0
         * is_follow : 0
         * praises : 0
         * answerCount : 1
         * pictures : ["http://58.213.14.195:8081/upload/questions/f271c674.jpg"]
         * pictureSize : 1
         * answers : [{"answer_id":"113","answer_content":"黑芝麻泡水喝可以保养头发","doctor_id":"111401","doctor_name":"吴望月","doctor_head":"https://appapi.maofa.com/userfiles/doctor/head/guangzhou/wuwangyue.png","position":"医师","create_time":"2018-09-27 10:49"}]
         */
        GlideUtils.with(bean.getCustomer_head(), ivHeader);
        tvName.setText(bean.getCustomer_name() + "");
        bga.setData((ArrayList<String>) bean.getPictures());
        tvDate.setText(bean.getCreate_time() + "");
        tvContent.setText(bean.getQuestions_content() + "");
        tvComments.setText(bean.getAnswerCount() + "回答");
        tvPraises.setText(bean.getPraises() + "赞");
        tvFollow.setText(isFollow==true?"已关注":"+ 关注");
        adapter.replaceData(bean.getAnswers());
        id = bean.getCustomer_id();
        isFollow = bean.getIs_follow() == 0 ? false : true;
    }

    boolean isFollow = false;

    @Override
    public void follow(BaseBean bean) {
        tvFollow.setText(isFollow==true?"已关注":"+ 关注");
        isFollow = !isFollow;
    }

    @Override
    protected QuestionDetailPresenter createPresenter() {
        return new QuestionDetailPresenter();
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("提问详情");
        return true;
    }

    String id, method;

    @OnClick({R.id.tv_follow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_follow:
                method = isFollow == true ? "delete" : "save";
                mPresenter.follow(id, method, Const.FOLLOW_TYPE.USER);
                break;
        }
    }
}
