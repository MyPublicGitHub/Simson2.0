package com.simson.www.ui.home.expert;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.community.DoctorBean;
import com.simson.www.net.bean.home.HospitalBean;
import com.simson.www.ui.adapter.ExpertAdapter;
import com.simson.www.ui.adapter.HospitalAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.community.expert.ExpertContract;
import com.simson.www.ui.community.expert.ExpertPresenter;
import com.simson.www.ui.home.expert.detail.ExpertDetailActivity;
import com.simson.www.utils.GlideImageLoader;
import com.simson.www.widget.CommonPopupWindow;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ExpertActivity extends BasePresenterActivity<ExpertPresenter, ExpertContract.View>
        implements ExpertContract.View {
    ExpertAdapter adapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_switch)
    TextView tvSwitch;
    private int mPage = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_expert;
    }

    @Override
    protected void initViews() {
        adapter = new ExpertAdapter(null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        setRefresh();
        adapter.setOnItemClickListener((adapter, view, position) -> {
            DoctorBean.DoctorItemBean bean = (DoctorBean.DoctorItemBean) adapter.getData().get(position);
            startActivity(new Intent(ExpertActivity.this, ExpertDetailActivity.class).putExtra("doctorId", bean.getDoctor_id()));
        });
        initBanner();
    }

    @Override
    protected void initData() {
        mPresenter.getHospitalList();
    }

    @Override
    public void showDoctorList(DoctorBean beans) {
        if (beans == null && beans.getList() != null) {
            return;
        }
        List<String> images = new ArrayList<>();
        for (int i = 0; i < beans.getRecommend().size(); i++) {
            images.add(beans.getRecommend().get(i).getDoctor_picture());
        }
        if (images.size() == 0) {
            mBanner.setVisibility(View.GONE);
        } else {
            mBanner.setVisibility(View.VISIBLE);
            mBanner.setImages(images);//设置图片集合
            mBanner.start();
        }
        List<DoctorBean.DoctorItemBean> bean = beans.getList();
        if (mPage == 1) {
            adapter.replaceData(bean);

        } else {
            adapter.addData(bean);
        }
        if (bean.size() == 0) {
            mRefreshLayout.setNoMoreData(true);
        }
    }

    HospitalAdapter hospitalAdapter;
    List<HospitalBean> beans;

    @Override
    public void getHospitalList(List<HospitalBean> bean) {
        if (bean == null) return;
        beans = bean;
        hospitalId = bean.get(0).getHospital_id();
        mPresenter.getDoctorList();
    }

    CommonPopupWindow popupWindow;

    @OnClick({R.id.tv_name, R.id.tv_switch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_name:
                break;
            case R.id.tv_switch:
                popupWindow = new CommonPopupWindow.Builder(this)
                        .setView(R.layout.pop_city_list) //设置PopupWindow布局
                        .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) //设置宽高
                        //.setAnimationStyle(R.style.AnimDown) //设置动画
                        .setBackGroundLevel(0.5f) //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                        .setViewOnclickListener((view1, layoutResId) -> {
                            RecyclerView recyclerView = view1.findViewById(R.id.recycler_view_city);
                            hospitalAdapter = new HospitalAdapter(beans);
                            recyclerView.setLayoutManager(new LinearLayoutManager(this));
                            hospitalAdapter.bindToRecyclerView(recyclerView);
                            hospitalAdapter.setEmptyView(R.layout.list_empty_view);
                            recyclerView.setAdapter(hospitalAdapter);
                            hospitalAdapter.setOnItemClickListener(onItemClickListener);

                        })
                        .setOutsideTouchable(true) //设置外部是否可点击 默认是true
                        .create(); //开始构建
                popupWindow.showAsDropDown(tvSwitch);//弹出PopupWindow
                break;
        }
    }

    BaseQuickAdapter.OnItemClickListener onItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            HospitalBean beans = (HospitalBean) adapter.getData().get(position);
            tvName.setText("" + beans.getHospital_name());
            hospitalId = beans.getHospital_id();
            mPage = 1;
            mPresenter.getDoctorList();
            if (popupWindow != null)
                popupWindow.dismiss();
        }
    };

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.getDoctorList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.getDoctorList();
            refreshLayout.finishLoadMore();
        });
    }

    @Override
    protected ExpertPresenter createPresenter() {
        return new ExpertPresenter();
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("专家");
        return true;
    }

    @Override
    public int getPage() {
        return mPage;
    }

    String hospitalId;

    @Override
    public String hospitalId() {
        return hospitalId;
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
        mBanner.start();
    }

}
