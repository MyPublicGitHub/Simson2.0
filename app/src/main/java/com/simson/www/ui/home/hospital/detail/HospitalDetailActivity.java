package com.simson.www.ui.home.hospital.detail;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.home.HospitalDetailBean;
import com.simson.www.ui.adapter.HDACaseDiaryAdapter;
import com.simson.www.ui.adapter.HDAExpertAdapter;
import com.simson.www.ui.adapter.HDAHospitalInfoAdapter;
import com.simson.www.ui.adapter.HDAInstrumentAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.utils.GlideImageLoader;
import com.simson.www.utils.GlideUtils;
import com.simson.www.utils.ToastUtils;
import com.simson.www.widget.CircleImageView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HospitalDetailActivity extends BasePresenterActivity<HospitalDetailPresenter, HospitalDetailContract.View>
        implements HospitalDetailContract.View {

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.iv_header)
    CircleImageView ivHeader;
    @BindView(R.id.tv_follow)
    TextView tvFollow;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rate)
    RatingBar rate;
    @BindView(R.id.tv_appointment)
    TextView tvAppointment;
    @BindView(R.id.tv_case)
    TextView tvCase;
    @BindView(R.id.tv_expert)
    TextView tvExpert;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.ll_location)
    LinearLayout llLocation;
    @BindView(R.id.ll_call)
    LinearLayout llCall;
    @BindView(R.id.ll_online)
    LinearLayout llOnline;
    private HDAExpertAdapter mHDAExpertAdapter;
    private HDAInstrumentAdapter mHDAInstrumentAdapter;
    private HDACaseDiaryAdapter mHDACaseDiaryAdapter;
    private HDAHospitalInfoAdapter mHDAHospitalInfoAdapter;

    @BindView(R.id.rv_experts)
    RecyclerView rvExperts;
    @BindView(R.id.rv_instrument)
    RecyclerView rvInstrument;
    @BindView(R.id.rv_case_diary)
    RecyclerView rvCaseDiary;
    @BindView(R.id.rv_hospital_info)
    RecyclerView rvHospitalInfo;
    String hospitalId, mMethod;
    boolean isFollow;

    @Override
    protected void getIntent(Intent intent) {
        hospitalId = intent.getStringExtra("hospitalId");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hospital_detail;
    }

    @Override
    protected void initViews() {
        List<BaseBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new BaseBean());
        }
        mHDAExpertAdapter = new HDAExpertAdapter(list);
        rvExperts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvExperts.setAdapter(mHDAExpertAdapter);

        mHDAInstrumentAdapter = new HDAInstrumentAdapter(list);
        rvInstrument.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvInstrument.setAdapter(mHDAInstrumentAdapter);

//        mHDACaseDiaryAdapter = new HDACaseDiaryAdapter(list);
//        rvCaseDiary.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        rvCaseDiary.setAdapter(mHDACaseDiaryAdapter);

        mHDAHospitalInfoAdapter = new HDAHospitalInfoAdapter(list);
        rvHospitalInfo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvHospitalInfo.setAdapter(mHDAHospitalInfoAdapter);

        initBanner();
    }


    @Override
    public void showHospitalDetail(HospitalDetailBean bean) {
        if (bean == null) return;
        mBanner.setImages(bean.getPictures());//设置图片集合
        mBanner.start();
        GlideUtils.with(bean.getHospital_icon(), ivHeader);
        tvName.setText("" + bean.getHospital_name());
        rate.setRating(bean.getHospital_star());
        tvAppointment.setText("预约：" + bean.getSubscribes());
        tvCase.setText("专家：" + bean.getDoctors());
        if (bean.getIs_follow() == 0) {
            isFollow = false;
        } else {
            isFollow = true;
        }
        initMethod();

    }

    @OnClick({R.id.tv_follow, R.id.ll_location, R.id.ll_call, R.id.ll_online})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_follow:
                mPresenter.follow();
                break;
            case R.id.ll_location:
                break;
            case R.id.ll_call:
                break;
            case R.id.ll_online:
                break;
        }
    }

    @Override
    public void follow(BaseBean bean) {
        if (bean.result == 0) {
            ToastUtils.showToast("关注成功");
            isFollow = !isFollow;
            initMethod();
        } else {
            ToastUtils.showToast("关注失败");
        }
    }

    @Override
    protected void initData() {
        mPresenter.getHospitalDetail();
    }

    @Override
    protected HospitalDetailPresenter createPresenter() {
        return new HospitalDetailPresenter();
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("医院详情");
        return true;
    }

    @Override
    public String getHospitalId() {
        return hospitalId;
    }

    @Override
    public String getFollowCustomerId() {
        return hospitalId;
    }

    private void initMethod() {
        if (isFollow) {
            mMethod = "delete";
            tvFollow.setText("已关注");
        } else {
            mMethod = "save";
            tvFollow.setText("+ 关注");
        }
    }

    @Override
    public String getMethod() {
        return mMethod;
    }

    @Override
    public String getType() {
        return Const.FOLLOW_TYPE.HOSPITAL;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mBanner != null)
            mBanner.stopAutoPlay();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBanner != null)
            mBanner.startAutoPlay();
    }

    private void initBanner() {
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);//设置banner样式
        mBanner.setImageLoader(new GlideImageLoader());//设置图片加载器
        mBanner.setBannerAnimation(Transformer.DepthPage);//设置banner动画效果
        mBanner.setDelayTime(4000);//设置轮播时间
        mBanner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器位置（当banner模式中有指示器时）
    }

}
