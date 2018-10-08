package com.simson.www.ui.shop.detail;

import android.content.Intent;
import android.graphics.Paint;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.mine.SubmitOrderBean;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.net.bean.shop.CommodityDetailPraiseBean;
import com.simson.www.ui.adapter.MyViewPageAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.mine.pay.PayActivity;
import com.simson.www.ui.mine.post.comment.MyCommentFragment;
import com.simson.www.ui.shop.detail.detail.DetailFragment;
import com.simson.www.ui.shop.detail.praise.PraiseActivity;
import com.simson.www.ui.shop.detail.procedure.ProcedureFragment;
import com.simson.www.utils.GlideImageLoader;
import com.simson.www.utils.GlideUtils;
import com.simson.www.utils.ToastUtils;
import com.simson.www.widget.ViewPagerForScrollView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CommodityDetailActivity extends BasePresenterActivity<CommodityDetailPresenter, CommodityDetailContract.View>
        implements CommodityDetailContract.View {

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_present)
    TextView tvPresent;
    @BindView(R.id.tv_original)
    TextView tvOriginal;
    @BindView(R.id.iv_collection)
    ImageView ivCollection;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPagerForScrollView mViewPager;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.tv_total_points)
    TextView tvTotalPoints;
    @BindView(R.id.rate)
    RatingBar rate;
    @BindView(R.id.tv_environment)
    TextView tvEnvironment;
    @BindView(R.id.tv_profession)
    TextView tvProfession;
    @BindView(R.id.tv_serve)
    TextView tvServe;
    @BindView(R.id.tv_effect)
    TextView tvEffect;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_contents)
    TextView tvContents;
    @BindView(R.id.tv_collection)
    TextView tvCollection;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity_detail;
    }

    @Override
    protected void initViews() {
        ArrayList<Fragment> frag = new ArrayList<>();
        frag.add(DetailFragment.newInstance(getItemId()));
        frag.add(ProcedureFragment.newInstance(getItemId()));
        frag.add(new MyCommentFragment());
        ArrayList<String> titleData = new ArrayList<>();
        titleData.add("商品详情");
        titleData.add("预约流程");
        titleData.add("相关日记");
        MyViewPageAdapter adapter = new MyViewPageAdapter(getSupportFragmentManager(), titleData, frag);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData() {
        initBanner();
        mPresenter.getCommodityDetail();
        mPresenter.getCommodityDetailPicture();
        mPresenter.getCommodityDetailPraise();
    }

    @Override
    public void showCommodityDetail(CommodityDetailBean bean) {
        List<String> bannerImageList = bean.getPicture();
        mBanner.setImages(bannerImageList);//设置图片集合
        mBanner.start();
        tvContent.setText("" + bean.getItem_name());
        tvPresent.setText("￥" + bean.getPresent_price());
        tvOriginal.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvOriginal.setText("￥" + bean.getOriginal_price());
        point = bean.getItem_point();
        money = bean.getPresent_price();
        initMethod(bean.getIs_collect() == 0 ? false : true);
    }
    boolean isCollect;
    private void initMethod(boolean isCollect) {
        if (isCollect) {
            mMethodCollect = "delete";
            tvCollection.setText("已收藏");
            //ivCollection.setImageResource();
        } else {
            mMethodCollect = "save";
            tvCollection.setText("收藏");
            //ivCollection.setImageResource();
        }
        this.isCollect = isCollect;
    }

    @Override
    public void showCommodityDetailPicture(CommodityDetailBean bean) {

    }

    @Override
    public void showCommodityDetailPraise(CommodityDetailPraiseBean bean) {

        tvTotalPoints.setText(bean.getItem_comment_star() + "分");
        rate.setRating(bean.getItem_comment_star());
        tvEnvironment.setText("环境：" + bean.getEffect_score() + "");
        tvProfession.setText("专业：" + bean.getProfessional_score() + "");
        tvServe.setText("服务：" + bean.getService_score() + "");
        tvEffect.setText("效果：" + bean.getEffect_score() + "");
        GlideUtils.with(bean.getCustomer_head(), ivHeader);
        tvName.setText(bean.getCustomer_name() + "");
        tvContents.setText(bean.getContent() + "");
    }

    @Override
    public void showSaveShopCart(BaseBean bean) {
        if (bean.result == 0) {
            ToastUtils.showToast("加入购物车成功");
        } else {
            ToastUtils.showToast("加入购物车失败");
        }
    }

    double money;
    int point;

    @Override
    public void showSubmitOrder(SubmitOrderBean bean) {
        if (bean.getOrderId() != null) {
            Intent intent = new Intent(this, PayActivity.class);
            intent.putExtra("transactionMoney", money);
            intent.putExtra("transactionPoint", point);
            intent.putExtra("orderId", bean.getOrderId());
            startActivity(intent);
        }
    }

    @Override
    public void collect(BaseBean bean) {
        if (bean.result == 0) {
            ToastUtils.showToast("成功");
        } else {
            ToastUtils.showToast("失败");
        }
        initMethod(!isCollect);
    }

    @OnClick({R.id.ll_consultation, R.id.ll_collection, R.id.tv_save_shop, R.id.tv_pay_now, R.id.tv_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_more:
                startActivity(new Intent(this, PraiseActivity.class).putExtra("itemId", getItemId()));
                break;
            case R.id.ll_consultation:
                break;
            case R.id.ll_collection:
                mPresenter.collect();
                break;
            case R.id.tv_save_shop:
                mPresenter.saveShopCart();
                break;
            case R.id.tv_pay_now:
                mPresenter.submitOrder();
                break;
        }
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

    private String id, mMethodCollect;
    int mBuyNum = 1;

    @Override
    public String getMethod() {
        return mMethodCollect;
    }

    @Override
    public String getType() {
        return Const.COLLECT_TYPE.COMMODITY;
    }

    @Override
    public String getBuyNum() {
        return mBuyNum + "";
    }

}
