package com.simson.www.ui.home.hair;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.simson.www.R;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.community.circle.save.SaveFriendCircleActivity;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.stickercamera.app.camera.CameraManager;

import butterknife.BindView;

public class BeautifulHairActivity extends BasePresenterActivity {

    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.bg)
    RelativeLayout bg;
    @BindView(R.id.iv_image)
    ImageView ivImage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_beautiful_hair;
    }

    String imagePath;

    @Override
    protected void initViews() {
        ll.setVisibility(View.GONE);
        bg.setVisibility(View.VISIBLE);
    }

    public void take(View v) {
        CameraManager.getInst().openCamera(this, 1001);
    }

    public void back(View v) {
        finish();
    }

    public void share(View v) {
        Intent intent = new Intent(this,SaveFriendCircleActivity.class);
        intent.putExtra("data",imagePath);
        startActivityForResult(intent,1004);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) return;
        if (resultCode == 1003) {
            imagePath = data.getStringExtra("imagePath");
            bg.setVisibility(View.GONE);
            ll.setVisibility(View.VISIBLE);
            ivImage.setImageBitmap(BitmapFactory.decodeFile(imagePath));
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

}
