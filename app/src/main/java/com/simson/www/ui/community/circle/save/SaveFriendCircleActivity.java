package com.simson.www.ui.community.circle.save;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;

public class SaveFriendCircleActivity extends BasePresenterActivity<SaveFriendCirclePresenter, SaveFriendCircleContract.View>
        implements SaveFriendCircleContract.View, BGASortableNinePhotoLayout.Delegate {
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.snpl_add_photos)
    BGASortableNinePhotoLayout snplAddPhotos;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_save_friend_circle;
    }
    @Override
    protected void initViews() {
        snplAddPhotos.setPlusEnable(true);//默认加号
        // 设置拖拽排序控件的代理
        snplAddPhotos.setDelegate(this);
    }

    @OnClick({R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_commit:
                mPresenter.initImage();
                break;
        }
    }

    @Override
    protected SaveFriendCirclePresenter createPresenter() {
        return new SaveFriendCirclePresenter();
    }
    private void choicePhotoWrapper() {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission.request(Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
                        File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "SimsonPhoto");
                        Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(this)
                                .cameraFileDir(takePhotoDir) // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话则不开启图库里的拍照功能
                                .maxChooseCount(snplAddPhotos.getMaxItemCount()) // 图片选择张数的最大值
                                .selectedPhotos(snplAddPhotos.getData()) // 当前已选中的图片路径集合
                                .pauseOnScroll(false) // 滚动列表时是否暂停加载图片
                                .build();
                        startActivityForResult(photoPickerIntent, RC_CHOOSE_PHOTO);
                    } else {
                        LogUtils.d("fa");
                    }
                });
    }

    private static final int RC_CHOOSE_PHOTO = 1;
    private static final int RC_PHOTO_PREVIEW = 2;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RC_CHOOSE_PHOTO) {
            snplAddPhotos.setData(BGAPhotoPickerActivity.getSelectedPhotos(data));
        } else if (requestCode == RC_PHOTO_PREVIEW) {
            snplAddPhotos.setData(BGAPhotoPickerPreviewActivity.getSelectedPhotos(data));
        }
    }

    @Override
    public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, ArrayList<String> models) {
        choicePhotoWrapper();
    }

    @Override
    public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        snplAddPhotos.removeItem(position);
    }

    @Override
    public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        Intent photoPickerPreviewIntent = new BGAPhotoPickerPreviewActivity.IntentBuilder(this)
                .previewPhotos(models) // 当前预览的图片路径集合
                .selectedPhotos(models) // 当前已选中的图片路径集合
                .maxChooseCount(snplAddPhotos.getMaxItemCount()) // 图片选择张数的最大值
                .currentPosition(position) // 当前预览图片的索引
                .isFromTakePhoto(false) // 是否是拍完照后跳转过来
                .build();
        startActivityForResult(photoPickerPreviewIntent, RC_PHOTO_PREVIEW);
    }

    @Override
    public void onNinePhotoItemExchanged(BGASortableNinePhotoLayout sortableNinePhotoLayout, int fromPosition, int toPosition, ArrayList<String> models) {

    }
    @Override
    public String content() {
        return etContent.getText().toString();
    }

    @Override
    public List<String> pictures() {
        return snplAddPhotos.getData();
    }

    @Override
    public void saveFriendsCircle(BaseBean bean) {
        ToastUtils.showToast(bean.message);
        finish();
    }
    @Override
    protected boolean initToolbar() {
        mTitle.setText("发友圈");
        return true;
    }
}
