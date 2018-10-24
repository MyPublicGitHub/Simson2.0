package com.simson.www.ui.shop.detail;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.location.Location;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.home.LatelyHospitalBean;
import com.simson.www.net.bean.home.TechnologyBean;
import com.simson.www.net.bean.mine.SubmitOrderBean;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.net.bean.shop.CommodityDetailPraiseBean;
import com.simson.www.ui.adapter.HospitalTechnologyAdapter;
import com.simson.www.ui.adapter.MyViewPageAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.home.hospital.detail.HospitalDetailActivity;
import com.simson.www.ui.home.hospital.select.SelectHospitalActivity;
import com.simson.www.ui.mine.pay.PayActivity;
import com.simson.www.ui.shop.detail.detail.DetailFragment;
import com.simson.www.ui.shop.detail.praise.PraiseActivity;
import com.simson.www.ui.shop.detail.procedure.ProcedureFragment;
import com.simson.www.utils.CommonUtils;
import com.simson.www.utils.GlideImageLoader;
import com.simson.www.utils.GlideUtils;
import com.simson.www.utils.LocationUtils;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.ToastUtils;
import com.simson.www.widget.CommonPopupWindow;
import com.simson.www.widget.ViewPagerForScrollView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.Calendar;
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
    @BindView(R.id.tv_point)
    TextView tvPoint;
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
    @BindView(R.id.tv_unity)
    TextView tvUnity;
    @BindView(R.id.tv_collection)
    TextView tvCollection;
    @BindView(R.id.iv_hospital_img)
    ImageView ivHospitalImg;
    @BindView(R.id.tv_hospital_name)
    TextView tvHospitalName;
    @BindView(R.id.tv_hospital_address)
    TextView tvHospitalAddress;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity_detail;
    }

    @Override
    protected void initViews() {
        ArrayList<Fragment> frag = new ArrayList<>();
        frag.add(DetailFragment.newInstance(getItemId()));
        frag.add(ProcedureFragment.newInstance(getItemId()));
        //frag.add(new MyCommentFragment());
        ArrayList<String> titleData = new ArrayList<>();
        titleData.add("商品详情");
        titleData.add("预约流程");
        //titleData.add("相关日记");
        MyViewPageAdapter adapter = new MyViewPageAdapter(getSupportFragmentManager(), titleData, frag);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData() {
        initBanner();
        mPresenter.getCommodityDetail();
        mPresenter.getCommodityDetailPicture();
        mPresenter.getCommodityDetailPraise();
        Location location = LocationUtils.getInstance(this).showLocation();
        if (location != null) {
            String address = "纬度：" + location.getLatitude() + "经度：" + location.getLongitude();
            LogUtils.e(address);
            latitude = location.getLatitude() + "";
            longitude = location.getLongitude() + "";
            mPresenter.getLatelyHospital();
        } else {
            LogUtils.e("获取地址失败：location=null");
        }
    }

    @Override
    public void showCommodityDetail(CommodityDetailBean bean) {
        beans = bean;
        List<String> bannerImageList = bean.getPicture();
        mBanner.setImages(bannerImageList);//设置图片集合
        mBanner.start();
        tvContent.setText("" + bean.getItem_name());
        tvPresent.setText("￥" + bean.getPresent_price());
        tvOriginal.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvOriginal.setText("￥" + bean.getOriginal_price());
        tvPoint.setText("积分：" + bean.getItem_point());
        tvUnity.setText("毛囊单位：" + bean.getHair_follicles_number() + "U");
        point = bean.getItem_point();
        money = bean.getPresent_price();
        initMethod(bean.getIs_collect() == 0 ? false : true);
    }

    LatelyHospitalBean latelyHospitalBean;

    @Override
    public void getLatelyHospital(LatelyHospitalBean bean) {
        latelyHospitalBean = bean;
/**
 * hospital_id : 1
 * hospital_name : 南京新生植发
 * hospital_icon : https://appapi.maofa.com/userfiles/hospital/nanjing.png
 * hospital_address : 江苏省南京市鼓楼区福建路31-1号
 * consulting_phone : 400-666-7272
 * hospital_longitude : 118.764180
 * hospital_latitude : 32.083646
 */
        if (bean == null) return;
        GlideUtils.with(bean.getHospital_icon(), ivHospitalImg);
        tvHospitalName.setText("" + bean.getHospital_name());
        tvHospitalAddress.setText("" + bean.getHospital_address());
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

    CommodityDetailBean beans;

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

    CommonPopupWindow popupWindow;

    @OnClick({R.id.ll_consultation, R.id.ll_collection, R.id.tv_save_shop, R.id.tv_pay_now, R.id.tv_more, R.id.phone_btn, R.id.hospital_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_more:
                startActivity(new Intent(this, PraiseActivity.class).putExtra("itemId", getItemId()));
                break;
            case R.id.ll_consultation:
                CommonUtils.consultation(this);
                break;
            case R.id.phone_btn:
                CommonUtils.callPhone(this, latelyHospitalBean.getConsulting_phone());
                break;
            case R.id.hospital_btn:
                startActivity(new Intent(this, HospitalDetailActivity.class).putExtra("hospitalId", latelyHospitalBean.getHospital_id()));
                break;
            case R.id.ll_collection:
                mPresenter.collect();
                break;
            case R.id.tv_save_shop:
                mPresenter.saveShopCart();
                break;
            case R.id.tv_pay_now:
                //mPresenter.submitOrder();
                if (beans == null) return;
                popupWindow = new CommonPopupWindow.Builder(this)
                        .setView(R.layout.pop_shop_detail) //设置PopupWindow布局
                        .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) //设置宽高
                        //.setAnimationStyle(R.style.AnimDown) //设置动画
                        .setBackGroundLevel(0.5f) //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                        .setViewOnclickListener((view1, layoutResId) -> {
                            RecyclerView recyclerView = view1.findViewById(R.id.recycler_view);
                            hospitalTechnologyAdapter = new HospitalTechnologyAdapter(null);
                            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                            //hospitalTechnologyAdapter.bindToRecyclerView(recyclerView);
                            //hospitalTechnologyAdapter.setEmptyView(R.layout.list_empty_view);
                            recyclerView.setAdapter(hospitalTechnologyAdapter);
                            hospitalTechnologyAdapter.setOnItemClickListener(onItemClickListener);
                            ImageView iv_image = view1.findViewById(R.id.iv_image);
                            TextView tv_title = view1.findViewById(R.id.tv_title);
                            TextView tv_present = view1.findViewById(R.id.tv_present);
                            TextView tv_unity = view1.findViewById(R.id.tv_unity);
                            tv_number = view1.findViewById(R.id.tv_number);
                            tv_hospital_name = view1.findViewById(R.id.tv_hospital_name);
                            tv_date = view1.findViewById(R.id.tv_date);
                            view1.findViewById(R.id.tv_reduce).setOnClickListener(onClickListener);
                            view1.findViewById(R.id.tv_add).setOnClickListener(onClickListener);
                            view1.findViewById(R.id.btn_commit).setOnClickListener(onClickListener);
                            tv_hospital_name.setOnClickListener(onClickListener);
                            tv_date.setOnClickListener(onClickListener);
                            GlideUtils.with(beans.getPicture().get(0), iv_image);
                            tv_title.setText("" + beans.getItem_name());
                            tv_present.setText("￥" + beans.getPresent_price());
                            tv_unity.setText("毛囊单位：" + beans.getHair_follicles_number() + "U");

                        })
                        .setOutsideTouchable(true) //设置外部是否可点击 默认是true
                        .create(); //开始构建
                popupWindow.showAsDropDown(mTitle);//弹出PopupWindow
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (resultCode == 1001) {
            tv_hospital_name.setText("" + data.getStringExtra("hospitalName"));
            hospitalId = data.getStringExtra("hospitalId");
            mPresenter.getPlantingTechnology();
        }
    }

    TextView tv_number, tv_hospital_name, tv_date;
    HospitalTechnologyAdapter hospitalTechnologyAdapter;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (tv_number != null) {
                String trim = tv_number.getText().toString().trim();
                int num = Integer.parseInt(trim);
                switch (view.getId()) {
                    case R.id.tv_reduce:
                        num = num - 1;
                        break;
                    case R.id.tv_add:
                        num = num + 1;
                        break;
                }
                tv_number.setText(num + "");
            }
            switch (view.getId()) {
                case R.id.tv_hospital_name:
                    startActivityForResult(new Intent(CommodityDetailActivity.this, SelectHospitalActivity.class), 1001);
                    break;
                case R.id.tv_date:
                    showDatePickerDialog();
                    break;
                case R.id.btn_commit:
                    if (TextUtils.isEmpty(tv_number.getText().toString())) {
                        ToastUtils.showToast("请选择购买数量");
                        return;
                    }
                    if (TextUtils.isEmpty(tv_date.getText().toString())) {
                        ToastUtils.showToast("请选择到院时间");
                        return;
                    }
                    if (TextUtils.isEmpty(hospitalId)) {
                        ToastUtils.showToast("请选择就诊医院");
                        return;
                    }
                    if (TextUtils.isEmpty(technologyId)) {
                        ToastUtils.showToast("请选择植发技术");
                        return;
                    }
                    mPresenter.submitOrder();
                    break;

            }
        }
    };

    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_DARK,
                (view, year, monthOfYear, dayOfMonth) ->
                        tv_date.setText(CommonUtils.getDatePickerToString(year, monthOfYear, dayOfMonth))
                , Calendar.getInstance().get(Calendar.YEAR)
                , Calendar.getInstance().get(Calendar.MONTH)
                , Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    BaseQuickAdapter.OnItemClickListener onItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            TechnologyBean beans = (TechnologyBean) adapter.getData().get(position);
            technologyId = beans.getTechnology_id();
        }
    };


    @Override
    public String getItemId() {
        return id;
    }

    private String id, mMethodCollect, hospitalId, technologyId;

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
        return tv_number == null ? "" : tv_number.getText().toString();
    }

    @Override
    public String hospitalId() {
        return hospitalId;
    }

    @Override
    public String technologyId() {
        return technologyId;
    }

    @Override
    public String subscribeDate() {
        return tv_date == null ? "" : tv_number.getText().toString();
    }

    @Override
    public String longitude() {
        return longitude;
    }

    String latitude, longitude;

    @Override
    public String latitude() {
        return latitude;
    }

    @Override
    public void getPlantingTechnology(List<TechnologyBean> bean) {
        hospitalTechnologyAdapter.replaceData(bean);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocationUtils.getInstance(this).removeLocationUpdatesListener();
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
    protected CommodityDetailPresenter createPresenter() {
        return new CommodityDetailPresenter();
    }

    @Override
    protected void getIntent(Intent intent) {
        id = intent.getStringExtra("id");
    }

}
