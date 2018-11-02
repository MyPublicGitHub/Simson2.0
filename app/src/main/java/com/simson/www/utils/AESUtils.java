package com.simson.www.utils;

import android.util.Base64;

import com.simson.www.BuildConfig;
import com.simson.www.common.UrlConstainer;

import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {

    /*
     * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private static String sKey = "simsongroup12345";//key，可自行修改
    private static String ivParameter = "1024039203920300";//偏移量,可自行修改

    /**
     * AES加密
     *
     * @param encryptSrc
     * @return
     * @throws Exception
     */
    public static String encrypt(String encryptSrc) {
        if (UrlConstainer.DEBUG) return encryptSrc;//本地服务器暂时没有加密，直接返回
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(encryptSrc.getBytes("utf-8"));
            //return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
            //return Base64.encodeToString(encrypted, Base64.DEFAULT);
            return URLEncoder.encode(Base64.encodeToString(encrypted, Base64.DEFAULT), "utf-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES解密
     *
     * @param decryptSrc
     * @return
     * @throws Exception
     */
    public static String decrypt(String decryptSrc) {
        try {
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            //byte[] encrypted1 = new BASE64Decoder().decodeBuffer(decryptSrc);// 先用base64解密
            byte[] encrypted1 = Base64.decode(decryptSrc, Base64.DEFAULT);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }
}
