package com.simson.www.utils;


import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;

public class RSAUtils {
    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBA" +
            "IkDNmFFed+UrcISYUDiEZ0g85+VzfZUOdruOYZehtrChRt0Xq9Q7xlXNd92BRaP2Fw8dKfdXV6BN8oghBzSyl" +
            "sRDS9cVObR41Ut0aAboC5IlowskjrwXs/Zc/79HRzztEJkwEvb4b7A9BAsrHrDS9RlYCG9vepa5jQx1T8X7o4" +
            "bAgMBAAECgYAiUDClSb4M9XJSwUGj+zoIDQGWfPD4+P0ITFMRyMijLsYhKxKve2h2AzZVwXviu6Gm7+kTRUgl" +
            "69yp/Vy4zz2oFR0sS6Oc4eJ0KPi9b5OdkDcZfnMSlZBSQtDz2aaGSzD7eSjV0P/pt7P7akU92t9TlrU/3aX0d" +
            "sXZZpZelHumgQJBALwVNqC6D8EyYR+8VkyMPPnKL7HkL18eMbgOdfUND83ekpfI5Qmi/Eda10bsqrhYMjVEjr" +
            "1Jls6s7rNiYFcyFUECQQC6fPLXBsgRfz5msYlhAZ0Npzp35QqDL16lnMVWUxb+gmbhcOPePmfEPJpkj7lUyXN" +
            "1JPB2g4wzpChd5omr4QBbAkEArsRDc4wQkaPfjd6yjgSNsEoIkZMH1am352BAiUju04wqyTGaaZ7yLtehTD0j" +
            "0ZHabZbbz9WudTNJxpP2E2/tQQJAOLhaL/Z7EX9PHnLvItcXyphLWz4JpX7Z3atCBhB9lOlam0T3uZfnfbMlD" +
            "mL8boJbII0cqh82nFpYgL7mrqVfswJBAJOaP+7A8O2mcY79IL7zCj2cVNIcnZQcMX0J60nyIzIy0k6jIbfZVH" +
            "7zEY+tPR4ZbudXP7vVrYtn77oJLpglI7Y=";

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data 已加密数据
     *             privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data) throws Exception {
        byte[] keyBytes = Base64.decode(PRIVATE_KEY, Base64.DEFAULT);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return Base64.encodeToString(signature.sign(), Base64.DEFAULT);
    }

    /**
     * 私钥加密
     *
     * @param data 源数据
     *             privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data) throws Exception {
        byte[] keyBytes = Base64.decode(PRIVATE_KEY, Base64.DEFAULT);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        //Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

}
