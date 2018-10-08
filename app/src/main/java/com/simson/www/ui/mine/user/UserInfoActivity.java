package com.simson.www.ui.mine.user;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.event.Event;
import com.simson.www.event.RxEvent;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.mine.CustomerInfoBean;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.utils.CommonUtils;
import com.simson.www.utils.GlideUtils;
import com.simson.www.utils.ImageUtils;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.ToastUtils;
import com.simson.www.widget.CircleImageView;
import com.simson.www.widget.CommonPopupWindow;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.baseadapter.BGABaseAdapterUtil;
import cn.bingoogolapple.photopicker.imageloader.BGAImage;
import cn.bingoogolapple.photopicker.util.BGAPhotoHelper;
import cn.bingoogolapple.photopicker.util.BGAPhotoPickerUtil;
import io.reactivex.annotations.NonNull;

public class UserInfoActivity extends BasePresenterActivity<UserInfoPresenter, UserInfoContract.View> implements UserInfoContract.View {


    @BindView(R.id.iv_header)
    CircleImageView ivHeader;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_region)
    TextView tvRegion;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initViews() {
        // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
        File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "Simson");
        mPhotoHelper = new BGAPhotoHelper(takePhotoDir);
        initPicker();
    }

    @Override
    public void showCustomerInfo(CustomerInfoBean bean) {
        GlideUtils.with(bean.getCustomer_head(), ivHeader);
        etNickname.setText(bean.getCustomer_name() + "");
        tvSex.setText(bean.getGender() == 0 ? "" : bean.getGender() == 1 ? "男" : "女");
        tvBirthday.setText(bean.getBirthday() + "");
        String fn = "image_test.png";
        tvRegion.setText(bean.getLocation() + "");

        String path = this.getFilesDir() + File.separator + fn;
        drawableToFile(ivHeader.getDrawable(), path);
        compressedPicture = ImageUtils.compressedPicture(path);
    }

    public void drawableToFile(@NonNull Drawable drawable, @NonNull String filePath) {
        if (drawable == null) return;
        try {
            File file = new File(filePath);
            if (file.exists())
                file.delete();
            if (!file.exists()) file.createNewFile();
            FileOutputStream out = null;
            out = new FileOutputStream(file);
            ((BitmapDrawable) drawable).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, out);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void showUpdateCustomerInfo(BaseBean bean) {
//        SPUtils.put(Const.USER_INFO.CUSTOMER_HEAD,compressedPicture);customerController
//        SPUtils.put(Const.USER_INFO.CUSTOMER_NICK_NAME,etNickname.getText().toString());
//        SPUtils.put(Const.USER_INFO.CUSTOMER_SEX,tvSex.getText().toString());
//        SPUtils.put(Const.USER_INFO.CUSTOMER_LOCATION,tvRegion.getText().toString());
        RxEvent.getInstance().postEvent(Const.EVENT_ACTION.LOGIN, new Event(Event.Type.LOGIN, true));
        ToastUtils.showToast("保存成功");
    }

    @Override
    protected void initData() {
        mPresenter.getCustomerInfo();
    }

    CommonPopupWindow popupWindow;

    @OnClick({R.id.ll_birthday, R.id.ll_header, R.id.ll_sex, R.id.tv_preservation, R.id.tv_region})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_birthday:
                showDatePickerDialog();
                break;
            case R.id.ll_header:
                //设置PopupWindow里的子View及点击事件
                popupWindow = new CommonPopupWindow.Builder(this)
                        .setView(R.layout.pop_camera_photo) //设置PopupWindow布局
                        .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) //设置宽高
                        //.setAnimationStyle(R.style.AnimDown) //设置动画
                        .setBackGroundLevel(0.5f) //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                        .setViewOnclickListener((view1, layoutResId) -> {
                            view1.findViewById(R.id.tv_photo).setOnClickListener(onClickListener);
                            view1.findViewById(R.id.tv_camera).setOnClickListener(onClickListener);
                            view1.findViewById(R.id.tv_cancel).setOnClickListener(onClickListener);
                        })
                        .setOutsideTouchable(true) //设置外部是否可点击 默认是true
                        .create(); //开始构建
                popupWindow.showAsDropDown(ivHeader);//弹出PopupWindow
                break;
            case R.id.ll_sex:
                //设置PopupWindow里的子View及点击事件
                popupWindow = new CommonPopupWindow.Builder(this)
                        .setView(R.layout.pop_sexy) //设置PopupWindow布局
                        .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) //设置宽高
                        //.setAnimationStyle(R.style.AnimDown) //设置动画
                        .setBackGroundLevel(0.5f) //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                        .setViewOnclickListener((view12, layoutResId) -> {
                            view12.findViewById(R.id.tv_man).setOnClickListener(onClickListener);
                            view12.findViewById(R.id.tv_woman).setOnClickListener(onClickListener);
                            view12.findViewById(R.id.tv_cancel).setOnClickListener(onClickListener);
                        })
                        .setOutsideTouchable(true) //设置外部是否可点击 默认是true
                        .create(); //开始构建
                popupWindow.showAsDropDown(tvSex);//弹出PopupWindow
                break;
            case R.id.tv_region:
                mPicker.showCityPicker();
                break;
            case R.id.tv_preservation:
                mPresenter.updateCustomerInfo();
                break;
        }
    }

    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_DARK,
                (view, year, monthOfYear, dayOfMonth) ->
                        tvBirthday.setText(CommonUtils.getDatePickerToString(year, monthOfYear, dayOfMonth))
                , 1990, 1, 1);
        datePickerDialog.show();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_photo:
                    choosePhoto();
                    break;
                case R.id.tv_camera:
                    takePhoto();
                    break;
                case R.id.tv_woman:
                    tvSex.setText("女");
                    break;
                case R.id.tv_man:
                    tvSex.setText("男");
                    break;
                case R.id.tv_cancel:
                    break;
            }
            popupWindow.dismiss();
        }
    };

    @Override
    public String customerName() {
        return etNickname.getText().toString();
    }

    @Override
    public String customerHead() {
        return compressedPicture;
    }

    @Override
    public String birthday() {
        return tvBirthday.getText().toString();
    }

    @Override
    public String gender() {
        if (TextUtils.isEmpty(tvSex.getText().toString())) {
            return "0";
        } else return tvSex.getText().toString().equals("男") ? "1" : "2";
    }

    @Override
    public String location() {
        return tvRegion.getText().toString();
    }

    @Override
    protected UserInfoPresenter createPresenter() {
        return new UserInfoPresenter();
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("个人信息");
        return true;
    }

    private static final int REQUEST_CODE_CHOOSE_PHOTO = 1;
    private static final int REQUEST_CODE_TAKE_PHOTO = 2;
    private static final int REQUEST_CODE_CROP = 3;
    private BGAPhotoHelper mPhotoHelper;

    public void choosePhoto() {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        startActivityForResult(mPhotoHelper.getChooseSystemGalleryIntent(), REQUEST_CODE_CHOOSE_PHOTO);
                    } else {
                        ToastUtils.showToast("请开起存储空间权限，以正常使用");
                        LogUtils.d("请开起存储空间权限，以正常使用");
                    }
                });
    }

    public void takePhoto() {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) {
                        try {
                            startActivityForResult(mPhotoHelper.getTakePhotoIntent(), REQUEST_CODE_TAKE_PHOTO);
                        } catch (Exception e) {
                            BGAPhotoPickerUtil.show(R.string.bga_pp_not_support_take_photo);
                        }
                    } else {
                        ToastUtils.showToast("请开起存储空间和相机权限，以正常使用");
                        LogUtils.d("请开起存储空间和相机权限，以正常使用");
                    }
                });
    }

    String compressedPicture;//处理后的照片

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_CHOOSE_PHOTO) {
                try {
                    startActivityForResult(mPhotoHelper.getCropIntent(mPhotoHelper.getFilePathFromUri(data.getData()), 200, 200), REQUEST_CODE_CROP);
                } catch (Exception e) {
                    mPhotoHelper.deleteCropFile();
                    BGAPhotoPickerUtil.show(R.string.bga_pp_not_support_crop);
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_CODE_TAKE_PHOTO) {
                try {
                    startActivityForResult(mPhotoHelper.getCropIntent(mPhotoHelper.getCameraFilePath(), 200, 200), REQUEST_CODE_CROP);
                } catch (Exception e) {
                    mPhotoHelper.deleteCameraFile();
                    mPhotoHelper.deleteCropFile();
                    BGAPhotoPickerUtil.show(R.string.bga_pp_not_support_crop);
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_CODE_CROP) {
                BGAImage.display(ivHeader, R.mipmap.bga_pp_ic_holder_light, mPhotoHelper.getCropFilePath(), BGABaseAdapterUtil.dp2px(200));
                compressedPicture = ImageUtils.compressedPicture(mPhotoHelper.getCropFilePath());
                LogUtils.e(compressedPicture);
            }
        } else {
            if (requestCode == REQUEST_CODE_CROP) {
                mPhotoHelper.deleteCameraFile();
                mPhotoHelper.deleteCropFile();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BGAPhotoHelper.onSaveInstanceState(mPhotoHelper, outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        BGAPhotoHelper.onRestoreInstanceState(mPhotoHelper, savedInstanceState);
    }

    CityPickerView mPicker;

    private void initPicker() {
        mPicker = new CityPickerView();
        mPicker.init(this);
        //添加默认的配置，不需要自己定义
        CityConfig cityConfig = new CityConfig.Builder().build();
        cityConfig.setProvinceCyclic(false);
        cityConfig.setCityCyclic(false);
        cityConfig.setDistrictCyclic(false);
        mPicker.setConfig(cityConfig);
        //监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                StringBuffer stringBuffer = new StringBuffer();
                if (province != null) {//省份
                    stringBuffer.append(province.getName());
                }
                if (city != null) {//城市
                    stringBuffer.append(city.getName());
                }
                if (district != null) {//地区

                    stringBuffer.append(district.getName());
                }
                tvRegion.setText(stringBuffer);
            }

            @Override
            public void onCancel() {
                //ToastUtils.showToast("已取消");
            }
        });
    }
}
