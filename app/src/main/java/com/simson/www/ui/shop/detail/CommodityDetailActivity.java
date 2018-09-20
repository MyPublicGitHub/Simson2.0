package com.simson.www.ui.shop.detail;

import android.content.Intent;
import android.graphics.Paint;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.net.bean.shop.CommodityDetailPraiseBean;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.utils.GlideImageLoader;
import com.simson.www.utils.GlideUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.List;

import butterknife.BindView;

public class CommodityDetailActivity extends BasePresenterActivity<CommodityDetailPresenter, CommodityDetailContract.View> implements CommodityDetailContract.View {

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_present)
    TextView tvPresent;
    @BindView(R.id.tv_original)
    TextView tvOriginal;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity_detail;
    }

    @Override
    protected void initData() {
        initBanner();
        mPresenter.getCommodityDetail();
        mPresenter.getCommodityDetailPicture();
        mPresenter.getCommodityDetailPicture();
    }

    @Override
    public void showCommodityDetail(CommodityDetailBean bean) {
        List<String> bannerImageList = bean.getPicture();
        mBanner.setImages(bannerImageList);//设置图片集合
        mBanner.start();
        tvContent.setText(""+bean.getItem_name());
        tvPresent.setText("￥"+bean.getPresent_price());
        tvPresent.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvOriginal.setText("￥"+bean.getOriginal_price());
    }

    @Override
    public void showCommodityDetailPicture(CommodityDetailBean bean) {

    }

    @Override
    public void showCommodityDetailPraise(CommodityDetailPraiseBean bean) {

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
    protected boolean initToolbar() {
        mTitle.setText("商品详情");
        return true;
    }

    String id;

    @Override
    protected void getIntent(Intent intent) {
        id = intent.getStringExtra("id");
    }

    @Override
    protected CommodityDetailPresenter createPresenter() {
        return new CommodityDetailPresenter();
    }

    @Override
    public String getItemId() {
        return id;
    }

}
