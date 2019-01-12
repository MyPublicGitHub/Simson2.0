package com.simson.www.ui.home.hospital.detail;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.simson.www.R;
import com.simson.www.application.AppContext;
import com.simson.www.common.Const;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.net.bean.community.DoctorBean;
import com.simson.www.net.bean.home.HospitalDetailBean;
import com.simson.www.net.bean.home.HospitalDeviceBean;
import com.simson.www.ui.adapter.HDAExpertAdapter;
import com.simson.www.ui.adapter.HDAHospitalInfoAdapter;
import com.simson.www.ui.adapter.HDAInstrumentAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.community.knowledge.detail.WebViewActivity;
import com.simson.www.ui.home.expert.ExpertActivity;
import com.simson.www.ui.home.expert.detail.ExpertDetailActivity;
import com.simson.www.ui.home.hospital.device.DeviceActivity;
import com.simson.www.utils.CommonUtils;
import com.simson.www.utils.GlideImageLoader;
import com.simson.www.utils.GlideUtils;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.ToastUtils;
import com.simson.www.widget.CircleImageView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

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
    //private HDACaseDiaryAdapter mHDACaseDiaryAdapter;
    private HDAHospitalInfoAdapter mHDAHospitalInfoAdapter;

    @BindView(R.id.rv_experts)
    RecyclerView rvExperts;
    @BindView(R.id.rv_instrument)
    RecyclerView rvInstrument;
    //    @BindView(R.id.rv_case_diary)
//    RecyclerView rvCaseDiary;
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

        mHDAExpertAdapter = new HDAExpertAdapter(null);
        rvExperts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mHDAExpertAdapter.bindToRecyclerView(rvExperts);
        mHDAExpertAdapter.setEmptyView(R.layout.list_empty_view);
        rvExperts.setAdapter(mHDAExpertAdapter);
        mHDAExpertAdapter.setOnItemClickListener((adapter, view, position) -> {
            DoctorBean.DoctorItemBean bean = (DoctorBean.DoctorItemBean) adapter.getData().get(position);
            startActivity(new Intent(this, ExpertDetailActivity.class).putExtra("doctorId", bean.getDoctor_id()));
        });

        mHDAInstrumentAdapter = new HDAInstrumentAdapter(null);
        rvInstrument.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mHDAInstrumentAdapter.bindToRecyclerView(rvInstrument);
        mHDAInstrumentAdapter.setEmptyView(R.layout.list_empty_view);
        rvInstrument.setAdapter(mHDAInstrumentAdapter);
        mHDAInstrumentAdapter.setOnItemClickListener((adapter, view1, position) -> {
            List<HospitalDeviceBean> bean = (List<HospitalDeviceBean>) adapter.getData();
            String link = bean.get(position).getDevice_link();
            String id = bean.get(position).getDevice_id();
            String url = link + "?json={deviceId:" + id + "}";
            startActivity(new Intent(this, WebViewActivity.class)
                    .putExtra(Const.WEB_VIEW_TITLE, bean.get(position).getDevice_name() + "")
                    .putExtra(Const.WEB_VIEW_URL, url));
        });

//        mHDACaseDiaryAdapter = new HDACaseDiaryAdapter(null);
//        rvCaseDiary.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        mHDACaseDiaryAdapter.bindToRecyclerView(rvCaseDiary);
//        mHDACaseDiaryAdapter.setEmptyView(R.layout.list_empty_view);
//        rvCaseDiary.setAdapter(mHDACaseDiaryAdapter);

        mHDAHospitalInfoAdapter = new HDAHospitalInfoAdapter(null);
        rvHospitalInfo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mHDAHospitalInfoAdapter.bindToRecyclerView(rvHospitalInfo);
        mHDAHospitalInfoAdapter.setEmptyView(R.layout.list_empty_view);
        rvHospitalInfo.setAdapter(mHDAHospitalInfoAdapter);
        initBanner();
        initMap();
    }

    String phone;

    @Override
    public void showHospitalDetail(HospitalDetailBean bean) {
        if (bean == null) return;

        mBanner.setImages(bean.getPictures());//设置图片集合
        mBanner.start();
        GlideUtils.with(bean.getHospital_icon(), ivHeader);
        tvName.setText("" + bean.getHospital_name());
        rate.setRating(bean.getHospital_star());
        tvAppointment.setText("预约：" + bean.getSubscribes());
        tvCase.setText("设备：" + bean.getDevices());
        tvExpert.setText("专家：" + bean.getDoctors());
        if (bean.getIs_follow() == 0) {
            isFollow = false;
        } else {
            isFollow = true;
        }
        tvLocation.setText(bean.getHospital_address() + "");
        location = bean.getHospital_address();
        phone = bean.getConsulting_phone();
        initMethod();
    }

    String location;

    @Override
    public void showDoctorList(DoctorBean beans) {
        if (beans == null && beans.getList() != null) {
            return;
        }
        List<DoctorBean.DoctorItemBean> bean = beans.getList();
        mHDAExpertAdapter.replaceData(bean);

    }

    @Override
    public void showHospitalDeviceList(List<HospitalDeviceBean> beans) {
        if (beans == null) {
            return;
        }
        mHDAInstrumentAdapter.replaceData(beans);
    }

    @Override
    public void showDiaryList(List<DiaryBean> beans) {
        if (beans == null) {
            return;
        }
        //mHDACaseDiaryAdapter.replaceData(beans);
    }


    @OnClick({R.id.tv_doctor_more, R.id.tv_device_more,
            R.id.tv_follow, R.id.ll_location, R.id.ll_call, R.id.ll_online})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_doctor_more:
                startActivity(new Intent(this, ExpertActivity.class));
                break;
            case R.id.tv_device_more:
                startActivity(new Intent(this, DeviceActivity.class));
                break;
            /*case R.id.tv_diary_more:
                break;*/
            case R.id.tv_follow:
                mPresenter.followHospital();
                break;
            case R.id.ll_location:
                getLatlon(location);
                if (start!=null&&end!=null){
                    AmapNaviPage.getInstance().showRouteActivity(HospitalDetailActivity.this,
                            new AmapNaviParams(start, null, end, AmapNaviType.DRIVER), null);
                }
                break;
            case R.id.ll_call:
                CommonUtils.callPhone(this, phone);
                break;
            case R.id.ll_online:
                break;
        }
    }
    Poi start;
    private void getLatlon(String cityName) {
        if (TextUtils.isEmpty(cityName)) {
            LogUtils.e("地址空");
            return;
        }
        GeocodeSearch geocodeSearch = new GeocodeSearch(AppContext.getContext());
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                showLoadingDialog();
                if (i == 1000) {
                    if (geocodeResult != null && geocodeResult.getGeocodeAddressList() != null
                            && geocodeResult.getGeocodeAddressList().size() > 0) {

                        GeocodeAddress geocodeAddress = geocodeResult.getGeocodeAddressList().get(0);
                        double latitude = geocodeAddress.getLatLonPoint().getLatitude();//纬度s
                        double longitude = geocodeAddress.getLatLonPoint().getLongitude();//经度

                        end = new Poi(cityName, new LatLng(latitude, longitude), "");
                        //LogUtils.e("11111111111111111111"+start.getCoordinate().latitude);

                    }
                } else {
                    LogUtils.e("i:" + i);
                }
                hideLoadingDialog();
            }
        });

        GeocodeQuery geocodeQuery = new GeocodeQuery(cityName.trim(), "0");
        geocodeSearch.getFromLocationNameAsyn(geocodeQuery);
    }
    Poi end;
    private void initMap() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationOption = new AMapLocationClientOption();
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);

        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        if (null != mLocationClient) {
            mLocationClient.setLocationOption(mLocationOption);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            //启动定位
            mLocationClient.startLocation();
        }
    }

    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    double latitudes = amapLocation.getLatitude();//获取纬度
                    double longitudes = amapLocation.getLongitude();//获取经度
                    String address = amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    LogUtils.e("纬度：" + latitudes + "经度：" + longitudes + "地址：" + address);
                    start = new Poi("当前位置", new LatLng(latitudes, longitudes), "");
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }

        }
    };

    @Override
    public void follow(BaseBean bean) {
        if (bean.result == 0) {
            ToastUtils.showToast("成功");
            isFollow = !isFollow;
            initMethod();
        } else {
            ToastUtils.showToast("失败");
        }
    }

    @Override
    protected void initData() {

        mPresenter.getHospitalDetail();
        mPresenter.getDoctorList();
        mPresenter.getHospitalDeviceList();
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
