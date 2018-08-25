package com.simson.www.utils;

import android.graphics.Color;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;


public class CommonUtils {
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
