package com.simson.www.ui.home.test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.net.bean.home.AlopeciaTestBean;
import com.simson.www.net.bean.home.QuestionBean;
import com.simson.www.ui.adapter.TestAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.utils.ImageUtils;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.ToastUtils;
import com.simson.www.widget.CommonPopupWindow;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.baseadapter.BGABaseAdapterUtil;
import cn.bingoogolapple.photopicker.imageloader.BGAImage;
import cn.bingoogolapple.photopicker.util.BGAPhotoHelper;
import cn.bingoogolapple.photopicker.util.BGAPhotoPickerUtil;

public class TestActivity extends BasePresenterActivity<TestPresenter, TestContract.View> implements TestContract.View {

    @BindView(R.id.tv_question)
    TextView tvQuestion;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_camera)
    ImageView ivCamera;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.ll_commit)
    LinearLayout llCommit;
    @BindView(R.id.ll_quest)
    LinearLayout llQuest;
    @BindView(R.id.iv_test_back)
    ImageView ivTestBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    TestAdapter adapter;

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TestAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
//        homeHeaderAdapter.bindToRecyclerView(recyclerView);
//        homeHeaderAdapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            QuestionBean.OptionsBean bean = (QuestionBean.OptionsBean) adapter.getData().get(position);
            correspondingId = bean.getCorresponding_id();
            optionId = bean.getOption_id();
            if (!options.contains(bean.getOption_id()))
                options.add(bean.getOption_id());
            //options.add(bean.getOption_id());
            isSelect = true;
            mPresenter.getQuestion();
        });
        llCommit.setVisibility(View.GONE);
        llQuest.setVisibility(View.VISIBLE);
        datas = new ArrayList<>();
        questions = new ArrayList<>();
        options = new ArrayList<>();
        pictures = new ArrayList<>();

        File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "Simson");
        mPhotoHelper = new BGAPhotoHelper(takePhotoDir);
    }

    @Override
    protected void initData() {
        mPresenter.getQuestion();
    }

    CommonPopupWindow popupWindow;

    @OnClick({R.id.iv_back, R.id.iv_camera, R.id.tv_commit, R.id.iv_test_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                options.remove(options.size() - 1);
                llCommit.setVisibility(View.GONE);
                llQuest.setVisibility(View.VISIBLE);
                mToolbar.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_camera:
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
                popupWindow.showAsDropDown(ivCamera);//弹出PopupWindow
                break;
            case R.id.tv_commit:
                mPresenter.saveAlopeciaTesting();
                break;
            case R.id.iv_test_back:
                datas.remove(datas.size() - 1);
                questions.remove(questions.size() - 1);
                isSelect = false;
                getQuestion(datas.get(datas.size() - 1));
                break;
        }
    }

    boolean isSelect = false;
    List<QuestionBean> datas;

    @Override
    public void getQuestion(QuestionBean bean) {
        tvQuestion.setText(bean.getThe_first_few() + "、" + bean.getTitle());
        adapter.replaceData(bean.getOptions());
        if (questions.contains(bean.getQuestionnaire_id()) && isSelect) {
            mToolbar.setVisibility(View.GONE);
            llCommit.setVisibility(View.VISIBLE);
            llQuest.setVisibility(View.GONE);
        } else {
            if (!datas.contains(bean))
                datas.add(bean);//110
            if (!questions.contains(bean.getQuestionnaire_id()))
                questions.add(bean.getQuestionnaire_id());//110
        }
        if (bean.getThe_first_few() == 1) {
            ivTestBack.setVisibility(View.GONE);
        } else {
            ivTestBack.setVisibility(View.VISIBLE);
        }

    }

    CommonPopupWindow popupWindowRes;

    @Override
    public void saveAlopeciaTesting(AlopeciaTestBean bean) {
        if (popupWindowRes == null) {
            popupWindowRes = new CommonPopupWindow.Builder(this)
                    .setView(R.layout.pop_test) //设置PopupWindow布局
                    .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) //设置宽高
                    //.setAnimationStyle(R.style.AnimDown) //设置动画
                    .setBackGroundLevel(0.5f) //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                    .setViewOnclickListener((view1, layoutResId) -> {
                        TextView tv_alopecia_grade = view1.findViewById(R.id.tv_alopecia_grade);
                        TextView tv_alopecia_desc = view1.findViewById(R.id.tv_alopecia_desc);
                        view1.findViewById(R.id.btn_commit).setOnClickListener(onClickListener);
                        tv_alopecia_grade.setText("" + bean.getAlopecia_grade());
                        tv_alopecia_desc.setText("" + bean.getAlopecia_desc());

                    })
                    .setOutsideTouchable(true) //设置外部是否可点击 默认是true
                    .create(); //开始构建
        }
        popupWindowRes.showAsDropDown(mTitle);//弹出PopupWindow
    }

    String correspondingId, optionId;

    @Override
    public String correspondingId() {
        return correspondingId;
    }

    @Override
    public String optionId() {
        return optionId;
    }

    @Override
    public String deviceToken() {
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "";
        }
        String ID = TelephonyMgr.getDeviceId();
        return ID;
    }

    List<String> questions, options, pictures;

    @Override
    public String[] questions() {
        return questions.toArray(new String[questions.size()]);
    }

    @Override
    public String[] options() {
        return options.toArray(new String[options.size()]);
    }

    @Override
    public String[] pictures() {
        return pictures.toArray(new String[pictures.size()]);
    }


    @Override
    protected TestPresenter createPresenter() {
        return new TestPresenter();
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("脱发检测");
        return true;
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
                case R.id.tv_cancel:
                    break;
                case R.id.btn_commit:
                    finish();
                    break;
            }
            if (popupWindow != null && popupWindow.isShowing())
                popupWindow.dismiss();
        }
    };

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
                            startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_CODE_TAKE_PHOTO);
                            //startActivityForResult(mPhotoHelper.getTakePhotoIntent(), REQUEST_CODE_TAKE_PHOTO);
                        } catch (Exception e) {
                            BGAPhotoPickerUtil.show(R.string.bga_pp_not_support_take_photo);
                        }
                    } else {
                        ToastUtils.showToast("请开起存储空间和相机权限，以正常使用");
                        LogUtils.d("请开起存储空间和相机权限，以正常使用");
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
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
                    //startActivityForResult(mPhotoHelper.getCropIntent(mPhotoHelper.getCameraFilePath(), 200, 200), REQUEST_CODE_CROP);
                    Bundle bundle = data.getExtras(); // 从data中取出传递回来缩略图的信息，图片质量差，适合传递小图片
                    Bitmap bitmap = (Bitmap) bundle.get("data"); // 将data中的信息流解析为Bitmap类型
                    ivCamera.setImageBitmap(bitmap);// 显示图片
                    //处理后的照片
                    pictures.add(ImageUtils.compressedPicture(bitmap));
                } catch (Exception e) {
                    mPhotoHelper.deleteCameraFile();
                    mPhotoHelper.deleteCropFile();
                    BGAPhotoPickerUtil.show(R.string.bga_pp_not_support_crop);
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_CODE_CROP) {
                BGAImage.display(ivCamera, R.mipmap.bga_pp_ic_holder_light, mPhotoHelper.getCropFilePath(), BGABaseAdapterUtil.dp2px(200));
                //处理后的照片
                pictures.add(ImageUtils.compressedPicture(mPhotoHelper.getCropFilePath()));
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

}
