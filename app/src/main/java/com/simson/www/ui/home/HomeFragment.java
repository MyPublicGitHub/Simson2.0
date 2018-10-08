package com.simson.www.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.net.bean.home.BroadcastsBean;
import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.ui.adapter.TabViewPagerAdapter;
import com.simson.www.ui.adapter.TabViewPagerAdapterItem;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.home.cases.CaseActivity;
import com.simson.www.ui.home.cause.CauseActivity;
import com.simson.www.ui.home.expert.ExpertActivity;
import com.simson.www.ui.home.hospital.HospitalActivity;
import com.simson.www.ui.main.MainActivity;
import com.simson.www.utils.GlideImageLoader;
import com.simson.www.utils.GlideUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class HomeFragment extends BasePresenterFragment<HomePresenter, HomeContract.View> implements HomeContract.View {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.iv_sign)
    ImageView ivSign;
    @BindView(R.id.tv_hospital)
    TextView tvHospital;
    @BindView(R.id.tv_expert)
    TextView tvExpert;
    @BindView(R.id.tv_case)
    TextView tvCase;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(android.view.View view) {
        initBanner();
        mPresenter.getBanner();
        mPresenter.getItemType();
        ivSign.setOnClickListener(v -> {
            mPresenter.getBanner();
            mPresenter.getItemType();
        });
    }

    @OnClick({R.id.iv_menu, R.id.tv_hospital, R.id.tv_expert, R.id.tv_case, R.id.rl_cause})
    public void onViewClicked(android.view.View view) {
        switch (view.getId()) {
            case R.id.iv_menu:
                MainActivity activity = (MainActivity) getActivity();
                activity.drawer();
                break;
            case R.id.tv_hospital:
                startActivity(new Intent(getActivity(), HospitalActivity.class));
                break;
            case R.id.tv_expert:
                startActivity(new Intent(getActivity(), ExpertActivity.class));
                break;
            case R.id.tv_case:
                startActivity(new Intent(getActivity(), CaseActivity.class));
                break;
            case R.id.rl_cause:
                startActivity(new Intent(getActivity(), CauseActivity.class));
                break;
        }
    }

    @Override
    public void setItemType(List<ItemTypeBean> bean) {
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getChildFragmentManager(),
                TabViewPagerAdapterItem.createHomeFragments(bean));
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void setBannerData(HomeBannerBean bean) {
        //List<String> mBannerTitleList = new ArrayList<>();
        List<String> bannerImageList = new ArrayList<>();
        List<String> mBannerUrlList = new ArrayList<>();
        List<BroadcastsBean> bannerDataList = bean.getBroadcasts();
        for (BroadcastsBean bannerData : bannerDataList) {
            //mBannerTitleList.add(bannerData.getMenu_title());
            bannerImageList.add(bannerData.getMenu_picture());
            mBannerUrlList.add(bannerData.getMenu_link());
        }
        mBanner.setImages(bannerImageList);//设置图片集合
        GlideUtils.with(bean.getSignIns().get(0).getMenu_picture(), ivSign);
        //mBanner.setBannerTitles(mBannerTitleList);//设置标题集合（当banner样式有显示title时）
//        mBanner.setOnBannerListener(i ->
//                startActivity(new Intent(getActivity(), WebViewActivity.class)
//                        .putExtra(Constants.WEB_VIEW_TITLE, mBannerTitleList.get(i))
//                        .putExtra(Constants.WEB_VIEW_URL, bannerDataList.get(i).getId())));
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
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

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void getBundle(Bundle bundle) {

    }
}
