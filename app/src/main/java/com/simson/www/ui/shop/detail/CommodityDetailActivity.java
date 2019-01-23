package com.simson.www.ui.shop.detail;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
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
        //tvPoint.setText("积分：" + bean.getItem_point());
        tvUnity.setText("毛囊单位：" + bean.getHair_follicles_number() + "U");
        point = bean.getItem_point();
        isPoint = bean.getIs_point() == 0 ? false : true;//0普通 1积分
        if (isPoint) {
            tvPresent.setText("积分" + bean.getItem_point());
            tvUnity.setText("新生植发");
        }
        itemTypeId = bean.getItemTypeId();
        //money = bean.getPresent_price();
        pointUnityPrice = bean.getItem_point();
        initMethod(bean.getIs_collect() == 0 ? false : true);
    }

    boolean isPoint, isSave;
    LatelyHospitalBean latelyHospitalBean;

    @Override
    public void getLatelyHospital(LatelyHospitalBean bean) {
        latelyHospitalBean = bean;
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
    int point, pointUnityPrice;

    @Override
    public void showSubmitOrder(SubmitOrderBean bean) {
        if (bean.getOrderId() != null) {
            Intent intent = new Intent(this, PayActivity.class);
            beans.buyNumber = getBuyNum();
            beans.unityPrice = unityPrice;
            ArrayList<CommodityDetailBean> list = new ArrayList<>();
            list.add(beans);
            intent.putExtra("transactionMoney", money);
            intent.putExtra("transactionPoint", point);
            intent.putExtra("isPoint", isPoint);
            intent.putParcelableArrayListExtra("CommodityDetailBean", list);
            intent.putExtra("orderId", bean.getOrderId());
            startActivity(intent);
            finish();
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

    CommonPopupWindow popupWindow, popupWindowPoint;

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
                if (latelyHospitalBean==null)return;
                startActivity(new Intent(this, HospitalDetailActivity.class).putExtra("hospitalId", latelyHospitalBean.getHospital_id()));
                break;
            case R.id.ll_collection:
                mPresenter.collect();
                break;
            case R.id.tv_save_shop:
                isSave = true;
                if (beans == null) return;
                if (isPoint) {
                    showBuyPoint();
                } else {
                    showNoPoint();
                }
                break;
            case R.id.tv_pay_now:
                if (beans == null) return;
                if (isPoint) {
                    showBuyPoint();
                } else {
                    showNoPoint();
                }
                break;
        }
    }

    private void showBuyPoint() {
        if (beans == null) return;
        if (popupWindowPoint == null) {
            popupWindowPoint = new CommonPopupWindow.Builder(this)
                    .setView(R.layout.pop_shop_detail_point) //设置PopupWindow布局
                    .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) //设置宽高
                    //.setAnimationStyle(R.style.AnimDown) //设置动画
                    .setBackGroundLevel(0.5f) //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                    .setViewOnclickListener((view1, layoutResId) -> {
                        ImageView iv_image = view1.findViewById(R.id.iv_image);
                        TextView tv_title = view1.findViewById(R.id.tv_title);
                        tv_present_point = view1.findViewById(R.id.tv_present);
                        tv_number_point = view1.findViewById(R.id.tv_number);
                        tv_reduce_point = view1.findViewById(R.id.tv_reduce);
                        tv_add_point = view1.findViewById(R.id.tv_add);
                        view1.findViewById(R.id.btn_commit).setOnClickListener(onClickListener);
                        tv_present_point.setText("￥" + beans.getItem_point());
                        GlideUtils.with(beans.getPicture().get(0), iv_image);
                        tv_title.setText("" + beans.getItem_name());
                        tv_present_point.setText("￥" + beans.getItem_point());
                        tv_number_point.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                                int number = Integer.parseInt(editable.toString());
                                point = number * pointUnityPrice;
                                tv_present_point.setText("￥" + point);
                                LogUtils.d("point:" + point);
                            }
                        });
                        tv_reduce_point.setOnTouchListener(onTouchListener);
                        tv_add_point.setOnTouchListener(onTouchListener);

                    })
                    .setOutsideTouchable(true) //设置外部是否可点击 默认是true
                    .create(); //开始构建
        }
        popupWindowPoint.showAsDropDown(mTitle);//弹出PopupWindow
    }

    private void showNoPoint() {
        if (beans == null) return;
        if (popupWindow == null) {
            popupWindow = new CommonPopupWindow.Builder(this)
                    .setView(R.layout.pop_shop_detail) //设置PopupWindow布局
                    .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) //设置宽高
                    //.setAnimationStyle(R.style.AnimDown) //设置动画
                    .setBackGroundLevel(0.5f) //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                    .setViewOnclickListener((view1, layoutResId) -> {
                        RecyclerView recyclerView = view1.findViewById(R.id.recycler_view);
                        hospitalTechnologyAdapter = new HospitalTechnologyAdapter(null);
                        recyclerView.setLayoutManager(new LinearLayoutManager(this));
                        //hospitalTechnologyAdapter.bindToRecyclerView(recyclerView);
                        //hospitalTechnologyAdapter.setEmptyView(R.layout.list_empty_view);
                        recyclerView.setAdapter(hospitalTechnologyAdapter);
                        hospitalTechnologyAdapter.setOnItemClickListener(onItemClickListener);
                        ImageView iv_image = view1.findViewById(R.id.iv_image);
                        TextView tv_title = view1.findViewById(R.id.tv_title);
                        tv_present = view1.findViewById(R.id.tv_present);
                        tv_unity = view1.findViewById(R.id.tv_unity);
                        tv_number = view1.findViewById(R.id.tv_number);
                        tv_hospital_name = view1.findViewById(R.id.tv_hospital_name);
                        tv_date = view1.findViewById(R.id.tv_date);
                        tv_reduce = view1.findViewById(R.id.tv_reduce);
                        tv_add = view1.findViewById(R.id.tv_add);
                        view1.findViewById(R.id.btn_commit).setOnClickListener(onClickListener);
                        tv_hospital_name.setOnClickListener(onClickListener);
                        tv_date.setOnClickListener(onClickListener);
                        GlideUtils.with(beans.getPicture().get(0), iv_image);
                        tv_title.setText("" + beans.getItem_name());
                        tv_unity.setText("毛囊单位：" + beans.getHair_follicles_number() + "U");
                        tv_number.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                                int number = Integer.parseInt(editable.toString());
                                money = number * unityPrice;
                                tv_present.setText("￥" + money);
                                LogUtils.d("number:" + number);
                            }
                        });
                        tv_reduce.setOnTouchListener(onTouchListener);
                        tv_add.setOnTouchListener(onTouchListener);

                    })
                    .setOutsideTouchable(true) //设置外部是否可点击 默认是true
                    .create(); //开始构建
        }
        popupWindow.showAsDropDown(mTitle);//弹出PopupWindow
    }

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            longClicked = true;
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        while (longClicked) {
                            Bundle bundle = new Bundle();
                            if (v.getId() == R.id.tv_add) {
                                bundle.putBoolean("add", true);
                            } else {
                                bundle.putBoolean("add", false);
                            }
                            Message message = new Message();
                            message.setData(bundle);
                            mHandler.sendMessage(message);
                            try {
                                Thread.sleep(150);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                t.start();
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                longClicked = false;
            }
            return true;
        }
    };

    private void initNumberColor(Message msg, TextView tv_number_save, TextView tv_reduce_save, TextView tv_add_save) {
        String trim = tv_number_save.getText().toString().trim();
        int num = Integer.parseInt(trim);
        Bundle bundle = msg.getData();
        if (bundle.getBoolean("add")) {
            num = num + 1;
        } else {
            num = num - 1;
        }
        if (num <= 1) {
            tv_number_save.setText("1");
            tv_reduce_save.setBackgroundColor(tv_reduce_save.getContext().getResources().getColor(R.color.colorBlack_6));
            tv_reduce_save.setClickable(false);
            tv_add_save.setBackgroundColor(tv_reduce_save.getContext().getResources().getColor(R.color.colorPrimary));
            tv_add_save.setClickable(true);
        } else if (num > maxValue) {
            tv_number_save.setText(maxValue + "");
            tv_reduce_save.setBackgroundColor(tv_reduce_save.getContext().getResources().getColor(R.color.colorPrimary));
            tv_reduce_save.setClickable(true);
            tv_add_save.setBackgroundColor(tv_reduce_save.getContext().getResources().getColor(R.color.colorBlack_6));
            tv_add_save.setClickable(false);
        } else {
            tv_number_save.setText(num + "");
            tv_reduce_save.setBackgroundColor(tv_reduce_save.getContext().getResources().getColor(R.color.colorPrimary));
            tv_reduce_save.setClickable(true);
            tv_add_save.setBackgroundColor(tv_reduce_save.getContext().getResources().getColor(R.color.colorPrimary));
            tv_add_save.setClickable(true);
        }
    }

    boolean longClicked;
    int maxValue = 10000;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (isPoint) {
                initNumberColor(msg, tv_number_point, tv_reduce_point, tv_add_point);
            } else {
                initNumberColor(msg, tv_number, tv_reduce, tv_add);
            }
        }
    };

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

    TextView tv_number, tv_hospital_name, tv_date, tv_present, tv_reduce, tv_add,
            tv_present_point, tv_number_point, tv_reduce_point, tv_add_point, tv_unity;
    HospitalTechnologyAdapter hospitalTechnologyAdapter;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_hospital_name:
                    startActivityForResult(new Intent(CommodityDetailActivity.this, SelectHospitalActivity.class), 1001);
                    break;
                case R.id.tv_date:
                    showDatePickerDialog(tv_date);
                    break;
                case R.id.btn_commit:
                    if (isPoint) {

                    } else {
                        if (TextUtils.isEmpty(getBuyNum())) {
                            ToastUtils.showToast("请选择购买数量");
                            return;
                        }
                        if (TextUtils.isEmpty(subscribeDate())) {
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
                    }
                    if (isSave) {
                        mPresenter.saveShopCart();
                    } else {
                        mPresenter.submitOrder();
                    }
                    isSave = false;
                    if (popupWindow != null && popupWindow.isShowing())
                        popupWindow.dismiss();
                    if (popupWindowPoint != null && popupWindowPoint.isShowing())
                        popupWindowPoint.dismiss();
                    break;

            }
        }
    };

    public void showDatePickerDialog(TextView textView) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_DARK,
                (view, year, monthOfYear, dayOfMonth) ->
                        textView.setText(CommonUtils.getDatePickerToString(year, monthOfYear, dayOfMonth))
                , Calendar.getInstance().get(Calendar.YEAR)
                , Calendar.getInstance().get(Calendar.MONTH)
                , Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    double unityPrice;

    BaseQuickAdapter.OnItemClickListener onItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            TechnologyBean technologyBean = (TechnologyBean) adapter.getData().get(position);
            technologyId = technologyBean.getTechnology_id();
            unityPrice = technologyBean.getUnit_price();
            tv_number.setText("" + technologyBean.getPlanting_number());
            String trim = tv_number.getText().toString().trim();
            int num = Integer.parseInt(trim);
            money = num * unityPrice;
            tv_present.setText("￥" + money);
            if ("1".equals(technologyBean.getTechnology_type())) {
                tv_add.setBackgroundColor(getResources().getColor(R.color.colorBlack_6));
                tv_add.setClickable(false);
                tv_reduce.setBackgroundColor(getResources().getColor(R.color.colorBlack_6));
                tv_reduce.setClickable(false);
                tv_unity.setText("毛囊单位:" + technologyBean.getPlanting_number() + "套");
            } else {
                tv_add.setClickable(true);
                tv_add.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                tv_reduce.setClickable(true);
                tv_reduce.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                tv_unity.setText("毛囊单位:" + technologyBean.getPlanting_number() + "U");
            }
            for (int i = 0; i < adapter.getData().size(); i++) {
                TechnologyBean bean = (TechnologyBean) adapter.getData().get(i);
                if (i == position) {
                    bean.isCheck = true;
                } else {
                    bean.isCheck = false;
                }
            }
            hospitalTechnologyAdapter.notifyDataSetChanged();
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
        if (isPoint) {
            return tv_number_point == null ? "" : tv_number_point.getText().toString();
        }
        return tv_number == null ? "" : tv_number.getText().toString();
    }

    @Override
    public String hospitalId() {
        return hospitalId;
    }

    String itemTypeId;

    @Override
    public String itemTypeId() {
        return itemTypeId;
    }

    @Override
    public String technologyId() {
        return technologyId;
    }

    @Override
    public String subscribeDate() {
        return tv_date == null ? "" : tv_date.getText().toString();
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
