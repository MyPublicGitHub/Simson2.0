package com.simson.www.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {
    /**
     * 压缩图片并且转码成base64
     *
     * @param path
     * @return
     */
    public static String compressedPicture(String path) {
        String imageToBase64 = null;
        Bitmap bitmap = loadFile(path);
        if (bitmap != null) {
            File file = qualtiy(bitmap);
            if (file != null) {
                String absolutePath = file.getAbsolutePath();
                imageToBase64 = imageToBase64(absolutePath);
                LogUtils.e("imageToBase64:::::" + imageToBase64);
            }
        } else {
            LogUtils.e("文件转换bitmap出错");
        }
        return imageToBase64;

    }

    /**
     * 压缩图片并且转码成base64
     *
     * @param bitmap
     * @return
     */
    public static String compressedPicture(Bitmap bitmap) {
        String imageToBase64 = null;
        if (bitmap != null) {
            File file = qualtiy(bitmap);
            if (file != null) {
                String absolutePath = file.getAbsolutePath();
                imageToBase64 = imageToBase64(absolutePath);
                LogUtils.e("imageToBase64:::::" + imageToBase64);
            }
        } else {
            LogUtils.e("文件转换bitmap出错");
        }
        return imageToBase64;

    }

    /**
     * 将本地路径转化为Bitmap
     *
     * @param path 本地路径
     * @return 返回本地资源的Bitmap
     */
    private static Bitmap loadFile(String path) {
        File mFile = new File(path);
        Bitmap mBitmap = null;
        //判读文件是否存在
        if (mFile.exists()) {
            BitmapFactory.Options mOptions = new BitmapFactory.Options();
            //设置只加载尺寸资源
            mOptions.inJustDecodeBounds = true;
            //设置资源
            BitmapFactory.decodeFile(path, mOptions);
            //图片加载缩方处理
            coundScale(mOptions);
            //设置加载资源模式
            mOptions.inJustDecodeBounds = false;
            //设置资源
            mBitmap = BitmapFactory.decodeFile(path, mOptions);
        }
        return mBitmap;
    }

    /**
     * 进行图片尺寸裁剪
     *
     * @param loadBitmap 本地图片资源
     * @return 尺寸处理后的资源
     */
    private Bitmap cutFile(Bitmap loadBitmap) {
        Bitmap mCutBitmap = null;
        if (loadBitmap != null) {
            //获取经过第一次处理后图片的尺寸;
            int mLoadBitmapWidth = loadBitmap.getWidth();
            int mLoadBitmapHeight = loadBitmap.getHeight();
            float scaleX = 0;
            float scaleY = 0;
            //图片的宽高比例
            float picScale = (float) mLoadBitmapWidth / mLoadBitmapHeight;
            //计算压缩尺寸比(无论图片大小，直接规定尺寸)
            if (mLoadBitmapWidth > mLoadBitmapHeight) {
                //长图片的时候(宽度缩放到1080，高度安装原图的宽高比缩放尺寸)
                scaleX = 1080.0F / mLoadBitmapWidth;
                scaleY = 1080.0F / picScale / mLoadBitmapHeight;
            } else if (mLoadBitmapWidth < mLoadBitmapHeight) {
                //高图的时候
                scaleX = 1080.0F / mLoadBitmapWidth;
                scaleY = 1920.0F / mLoadBitmapHeight;
            } else {
                //正方形图的时候(按照宽度为基准缩放)
                scaleX = 1080.0F / mLoadBitmapWidth;
                scaleY = scaleX;
            }
            //创建一个矩阵
            Matrix mMatrix = new Matrix();
            //设置缩放比例
            mMatrix.postScale(scaleX, scaleY);
            //生成新的Bitmap
            mCutBitmap = Bitmap.createBitmap(loadBitmap, 0, 0, mLoadBitmapWidth, mLoadBitmapHeight, mMatrix, false);
        }
        return mCutBitmap;
    }

    /**
     * 质量压缩
     *
     * @param mBitmap 处理尺寸后的资源
     * @return 压缩结束后的文件
     */
    private static File qualtiy(Bitmap mBitmap) {
        File mFile = null;
        if (mBitmap != null) {
            //设置压缩后图片不超过100KB
            int maxKB = 100;
            //创建一个缓存区
            ByteArrayOutputStream mByteArrayOutputStream = new ByteArrayOutputStream();
            //默认压缩质量(不压缩)
            int options = 100;
            //压缩后的大小
            int endKB = 0;
            do {
                //清空缓存区
                mByteArrayOutputStream.reset();
                //将数据写入缓存区
                mBitmap.compress(Bitmap.CompressFormat.JPEG, options, mByteArrayOutputStream);
                //降低质量
                options -= 5;
                //重新计算缓存区数据大小
                endKB = mByteArrayOutputStream.toByteArray().length;
                //如果压缩系数小于0的时候直接跳出循环
                if (options <= 0) {
                    break;
                }
                //判断当前资源大小是否大于期望大小
            } while (endKB / 1024 > maxKB);
            //创建一个输入流
            FileOutputStream mFileOutputStream = null;
            BufferedOutputStream mBufferedOutputStream = null;
            //创建一个空文件
            mFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/manageEnd.jpg");
            try {
                mFileOutputStream = new FileOutputStream(mFile);
                mBufferedOutputStream = new BufferedOutputStream(mFileOutputStream);
                //将压缩后的数据写入都空文件中
                mBufferedOutputStream.write(mByteArrayOutputStream.toByteArray());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (mByteArrayOutputStream != null && mBufferedOutputStream != null && mFileOutputStream != null) {
                        //关闭流
                        mByteArrayOutputStream.flush();
                        mBufferedOutputStream.flush();
                        mFileOutputStream.close();
                        mBufferedOutputStream.close();
                        mByteArrayOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return mFile;
    }

    /**
     * 计算缩放比例，防止OOM
     *
     * @param opeions BitmapFactory.Options
     */
    private static void coundScale(BitmapFactory.Options opeions) {
        if (opeions != null) {
            //原图的尺寸
            int orginalWidht = opeions.outWidth;
            int orginalHieght = opeions.outHeight;
            //如果图片尺寸大于一定尺寸的时候进行缩放
            if (orginalWidht > 1080 || orginalHieght > 1920) {
                //缩小倍数
                int scale = 1;

                if (orginalWidht > orginalHieght) {
                    //长图片的时候（以宽度为基准缩小）
                    scale = orginalWidht / 1080;
                } else if (orginalWidht < orginalHieght) {
                    //高图的时候（以高度为基准缩小）
                    scale = orginalHieght / 1920;
                } else {
                    //正方形图的时候（以宽度为基准缩小）
                    scale = orginalWidht / 1080;
                }
                LogUtils.e("coundScale: " + scale);
                opeions.inSampleSize = scale;
            }
        }
    }

    /**
     * 将图片转换成Base64编码的字符串
     *
     * @param path
     * @return base64编码的字符串
     */
    public static String imageToBase64(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try {
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }

}
