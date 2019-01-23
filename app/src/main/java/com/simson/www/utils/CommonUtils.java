package com.simson.www.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;

import com.simson.www.application.AppContext;
import com.simson.www.common.Const;
import com.simson.www.ui.community.knowledge.detail.WebViewActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;


public class CommonUtils {
    public static void consultation(Context context) {
        context.startActivity(new Intent(context, WebViewActivity.class)
                .putExtra(Const.WEB_VIEW_TITLE, "咨询")
                .putExtra(Const.WEB_VIEW_URL, "http://seo.xsmaofa.com/?link=app"));
    }

    public static void callPhone(Activity activity, String phone) {
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showToast("呼叫号码为空");
            return;
        }
        RxPermissions rxPermission = new RxPermissions(activity);
        rxPermission.request(Manifest.permission.CALL_PHONE)
                .subscribe(garden -> {
                    if (garden) {
                        // 用户已经同意该权限
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setTitle("电话咨询")
                                .setMessage(phone)
                                .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                                .setNeutralButton("呼叫", (dialog, which) -> {
                                    Intent intent = new Intent(Intent.ACTION_CALL);
                                    Uri data = Uri.parse("tel:" + phone);
                                    intent.setData(data);
                                    activity.startActivity(intent);
                                    dialog.dismiss();
                                }).show();

                    }
                });
    }

    public static String getDatePickerToString(int year, int monthOfYear, int dayOfMonth) {
        StringBuffer sb = new StringBuffer();
        sb.append(year);
        if (monthOfYear < 9) {
            sb.append("-0").append(monthOfYear + 1);
        } else {
            sb.append("-").append(monthOfYear + 1);
        }
        if (dayOfMonth < 10) {
            sb.append("-0").append(dayOfMonth);
        } else {
            sb.append("-").append(dayOfMonth);
        }
        return sb.toString();
    }

    /**
     * 测量View的宽高
     *
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 获取随机rgb颜色值
     */
    public static int randomColor() {
        Random random = new Random();
        //0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        int red = random.nextInt(150);
        //0-190
        int green = random.nextInt(150);
        //0-190
        int blue = random.nextInt(150);
        //使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red, green, blue);
    }

    public static String getValidUA(String userAgent) {
        if (TextUtils.isEmpty(userAgent)) {
            return "android";
        }
        String validUA = "android";
        String uaWithoutLine = userAgent.replace("\n", "");
        for (int i = 0, length = uaWithoutLine.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                try {
                    validUA = URLEncoder.encode(uaWithoutLine, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return validUA;
            }
        }
        return uaWithoutLine;
    }

    //获取手机的唯一标识
    public static String getPhoneSign() {
        StringBuilder deviceId = new StringBuilder(); // 渠道标志

        deviceId.append("a");
        try {
            //IMEI（imei）
            TelephonyManager tm = (TelephonyManager) AppContext.getContext().getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(AppContext.getContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return "";
            }
            String imei = tm.getDeviceId();
            if (!TextUtils.isEmpty(imei)) {
                deviceId.append("imei");
                deviceId.append(imei);
                return deviceId.toString();
            }
            //序列号（sn）
            String sn = tm.getSimSerialNumber();
            if (!TextUtils.isEmpty(sn)) {
                deviceId.append("sn");
                deviceId.append(sn);
                return deviceId.toString();

            }
            //如果上面都没有， 则生成一个id：随机码
            String uuid = getUUID();
            if (!TextUtils.isEmpty(uuid)) {
                deviceId.append("id");
                deviceId.append(uuid);
                return deviceId.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            deviceId.append("id").append(getUUID());
        }
        return deviceId.toString();
    }


    public static String getUUID() {
        String uuid = (String) SPUtils.get("uuid", "");
        if (TextUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
            SPUtils.put("uuid", uuid);
        }
        return uuid;
    }
    // 这是来自 JPush Example 的设置别名的 Activity 里的代码。一般 App 的设置的调用入口，在任何方便的地方调用都可以。
    public static void setAlias() {
        String alias = (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, "");
        if (TextUtils.isEmpty(alias)) {
            return;
        }
        if ((boolean)SPUtils.get(Const.ALIAS,false)){
            return;
        }
        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private static final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    LogUtils.e(logs);
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    SPUtils.put(Const.ALIAS, true);
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    LogUtils.e(logs);
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    LogUtils.e(logs);
            }
        }
    };
    private static final int MSG_SET_ALIAS = 1001;
    private static final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    LogUtils.e("Set alias in handler.");
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(AppContext.getContext(),
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
                    LogUtils.e("Unhandled msg - " + msg.what);
            }
        }
    };
}
