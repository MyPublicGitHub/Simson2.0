package com.simson.www.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;

import com.simson.www.common.Const;
import com.simson.www.ui.community.knowledge.detail.WebViewActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;


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

}
