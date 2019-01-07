package com.simson.www.common;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.simson.www.application.AppContext;
import com.simson.www.ui.community.knowledge.detail.WebViewActivity;
import com.simson.www.ui.main.MainActivity;
import com.simson.www.ui.main.vote.VoteActivity;
import com.simson.www.utils.LogUtils;

import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

public class MyJPushReceiver extends BroadcastReceiver {

    private static final String TAG = "MyReceiver";

    private NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
        initMap();
        if (null == nm) {
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        Bundle bundle = intent.getExtras();
        LogUtils.e("onReceive - " + intent.getAction() + ", extras: ");

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            LogUtils.e("JPush 用户注册成功");

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            LogUtils.e("接受到推送下来的自定义消息");

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            LogUtils.e("接受到推送下来的通知");

            receivingNotification(context, bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            LogUtils.e("用户点击打开了通知");

            openNotification(context, bundle);

        } else {
            LogUtils.e("Unhandled intent - " + intent.getAction());
        }
    }

    private void receivingNotification(Context context, Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        LogUtils.e(" title : " + title);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        LogUtils.e("message : " + message);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        LogUtils.e("extras : " + extras);
    }

    private void openNotification(Context context, Bundle bundle) {
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        String type = "";
        String url = "";
        String address = "";
        try {
            JSONObject extrasJson = new JSONObject(extras);
            type = extrasJson.optString("type");
            url = extrasJson.optString("url");
            address = extrasJson.optString("address");
        } catch (Exception e) {
            LogUtils.e("Unexpected: extras is not a valid json");
            return;
        }
        //  type 1 网址url type2 投票  3 红包  4.天气预报   5 地图address
        if ("1".equals(type)) {
            Intent mIntent = new Intent(context, WebViewActivity.class);
            mIntent.putExtras(bundle);
            mIntent.putExtra(Const.WEB_VIEW_TITLE, message + "");
            mIntent.putExtra(Const.WEB_VIEW_URL, url);
            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mIntent);
        } else if ("2".equals(type)) {
            Intent mIntent = new Intent(context, VoteActivity.class);
            mIntent.putExtras(bundle);
            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mIntent);
        } else if ("3".equals(type)) {
            Intent mIntent = new Intent(context, MainActivity.class);
            mIntent.putExtras(bundle);
            Const.RED = true;
            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mIntent);
        } else if ("5".equals(type)) {
            getLatlon(address);
        }
    }

    private void getLatlon(String cityName) {
        if (TextUtils.isEmpty(cityName)) return;
        GeocodeSearch geocodeSearch = new GeocodeSearch(AppContext.getContext());
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                if (i == 1000) {
                    if (geocodeResult != null && geocodeResult.getGeocodeAddressList() != null
                            && geocodeResult.getGeocodeAddressList().size() > 0) {

                        GeocodeAddress geocodeAddress = geocodeResult.getGeocodeAddressList().get(0);
                        double latitude = geocodeAddress.getLatLonPoint().getLatitude();//纬度s
                        double longitude = geocodeAddress.getLatLonPoint().getLongitude();//经度
                        LogUtils.e("--------------------------------------mLatitude:" + mLatitude);
                        Poi start = new Poi("当前位置", new LatLng(mLatitude, mLongitude), "");
                        Poi end = new Poi(cityName, new LatLng(latitude, longitude), "");
                        AmapNaviPage.getInstance().showRouteActivity(AppContext.getContext(), new AmapNaviParams(start, null, end, AmapNaviType.DRIVER), null);
                    }
                } else {
                    LogUtils.e("i:" + i);
                }
            }
        });

        GeocodeQuery geocodeQuery = new GeocodeQuery(cityName.trim(), "0");
        geocodeSearch.getFromLocationNameAsyn(geocodeQuery);
    }

    private void initMap() {
        //初始化定位
        mLocationClient = new AMapLocationClient(AppContext.getContext());
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

    double mLatitude, mLongitude;
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
                    mLatitude = latitudes;
                    mLongitude = longitudes;
                    //LogUtils.e("纬度：" + latitudes + "经度：" + longitudes + "地址：" + address);
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }

        }
    };

}

