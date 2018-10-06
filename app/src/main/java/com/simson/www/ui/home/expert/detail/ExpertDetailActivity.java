package com.simson.www.ui.home.expert.detail;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.community.PopularizationBean;
import com.simson.www.net.bean.community.QuestionsBean;
import com.simson.www.net.bean.home.DoctorDetailBean;
import com.simson.www.ui.adapter.KnowledgeItemAdapter;
import com.simson.www.ui.adapter.QuestionAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.community.expert.detail.QuestionDetailActivity;
import com.simson.www.ui.community.knowledge.detail.WebViewActivity;
import com.simson.www.utils.GlideUtils;
import com.simson.www.utils.ToastUtils;
import com.simson.www.widget.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ExpertDetailActivity extends BasePresenterActivity<ExpertDetailPresenter, ExpertDetailContract.View>
        implements ExpertDetailContract.View {

    @BindView(R.id.rv_experts)
    RecyclerView rvExpert;
    @BindView(R.id.rv_know)
    RecyclerView rvKnow;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_post)
    TextView tvPost;
    @BindView(R.id.ll_user_info)
    LinearLayout llUserInfo;
    @BindView(R.id.rate)
    RatingBar rate;
    @BindView(R.id.tv_good)
    TextView tvGood;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.iv_follow)
    CircleImageView ivFollow;
    @BindView(R.id.iv_hospital_header)
    ImageView ivHospitalHeader;
    @BindView(R.id.tv_hospital_name)
    TextView tvHospitalName;
    @BindView(R.id.tv_hospital_subscribes)
    TextView tvHospitalSubscribes;
    @BindView(R.id.tv_case)
    TextView tvCase;
    @BindView(R.id.tv_expert)
    TextView tvExpert;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_subscribes)
    TextView tvSubscribes;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_expert_detail;
    }

    @Override
    public void showDoctorDetail(DoctorDetailBean bean) {
        GlideUtils.with(bean.getDoctor_head(), ivHeader);
        tvName.setText(bean.getDoctor_name() + "");
        tvPost.setText(bean.getPosition() + "");
        rate.setRating(bean.getDoctor_star());
        tvGood.setText("擅长：" + bean.getBe_good_at() + "");
        tvNumber.setText(bean.getDoctor_ranking() + "");
        tvSubscribes.setText(bean.getDoctor_subscribes() + "");
        tvHospitalName.setText(bean.getHospital_name() + "");
        tvHospitalSubscribes.setText("预约：" + bean.getHospital_subscribes() + "");
        tvCase.setText("案例：" + bean.getCases() + "");
        tvExpert.setText("专家：" + bean.getDoctors() + "");
        tvLocation.setText("地址：" + bean.getHospital_address() + "");
        GlideUtils.with(bean.getHospital_icon(), ivHospitalHeader);
        mFollowCustomerId = bean.getDoctor_id();
        if (bean.getIs_follow() == 0) {
            isFollow = false;
        } else {
            isFollow = true;
        }
        initMethod();
    }

    @Override
    public void shoExpert(List<QuestionsBean> bean) {
        if (bean == null) {
            return;
        }
        questionAdapter.replaceData(bean);
    }

    @Override
    public void showPopularizationList(List<PopularizationBean> bean) {
        if (bean == null) {
            return;
        }
        adapter.replaceData(bean);
    }

    boolean isFollow;
    QuestionAdapter questionAdapter;
    KnowledgeItemAdapter adapter;

    @Override
    protected void initViews() {
        rvExpert.setLayoutManager(new LinearLayoutManager(this));
        questionAdapter = new QuestionAdapter(null);
        rvExpert.setAdapter(questionAdapter);
        questionAdapter.bindToRecyclerView(rvExpert);
        questionAdapter.setEmptyView(R.layout.list_empty_view);
        questionAdapter.setOnItemClickListener((adapter, view1, position) -> {
            List<QuestionsBean> bean = (List<QuestionsBean>) adapter.getData();
            String questionsId = bean.get(position).getQuestions_id();
            startActivity(new Intent(this, QuestionDetailActivity.class).putExtra("questionsId", questionsId));
        });

        rvKnow.setLayoutManager(new LinearLayoutManager(this));
        adapter = new KnowledgeItemAdapter(null);
        rvKnow.setAdapter(adapter);
        adapter.bindToRecyclerView(rvKnow);
        adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<PopularizationBean> bean = (List<PopularizationBean>) adapter.getData();
            String popularizationLink = bean.get(position).getPopularization_link();
            String popularizationId = bean.get(position).getPopularization_id();
            String url = popularizationLink + "?json={popularizationId:" + popularizationId + "}";
            startActivity(new Intent(this, WebViewActivity.class)
                    .putExtra(Const.WEB_VIEW_TITLE, "科普详情")
                    .putExtra(Const.WEB_VIEW_URL, url));
        });
    }


    @OnClick({R.id.ic_back, R.id.tv_consultation, R.id.iv_follow, R.id.tv_location, R.id.rl_hospital})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                finish();
                break;
            case R.id.tv_consultation:
                break;
            case R.id.rl_hospital:
                break;
            case R.id.iv_follow:
                mPresenter.follow();
                break;
            case R.id.tv_location:
                break;
        }
    }

    @Override
    public void follow(BaseBean bean) {
        if (bean.result == 0) {
            ToastUtils.showToast("关注成功");
            isFollow = !isFollow;
            initMethod();
        }else {
            ToastUtils.showToast("关注失败");
        }
    }

    @Override
    protected void initData() {
        mPresenter.getDoctorDetail();
        mPresenter.getExpert();
        mPresenter.getPopularizationList();
    }

    @Override
    protected ExpertDetailPresenter createPresenter() {
        return new ExpertDetailPresenter();
    }

    @Override
    public String getDoctorId() {
        return doctorId;
    }

    @Override
    public String getFollowCustomerId() {
        return mFollowCustomerId;
    }

    String mMethod, doctorId, mFollowCustomerId;

    private void initMethod() {
        if (isFollow) {
            mMethod = "delete";
            ivFollow.setVisibility(View.GONE);
            //ivFollow.setImageResource();
        } else {
            mMethod = "save";
            //ivFollow.setImageResource();
        }
    }

    @Override
    public String getMethod() {
        return mMethod;
    }

    @Override
    public String getType() {
        return "2";
    }

    @Override
    protected void getIntent(Intent intent) {
        doctorId = intent.getStringExtra("doctorId");
    }

}
