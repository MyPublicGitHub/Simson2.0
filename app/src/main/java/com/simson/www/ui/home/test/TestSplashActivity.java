package com.simson.www.ui.home.test;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.GlideUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class TestSplashActivity extends BasePresenterActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_gif)
    ImageView tvGif;
    @BindView(R.id.tv_test)
    TextView tvTest;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_splash;
    }

    @Override
    protected void initData() {
        GlideUtils.with(R.mipmap.ic_test_3d,tvGif);
    }

    @OnClick({R.id.iv_back, R.id.tv_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_test:
                startActivity(new Intent(this,TestActivity.class));
                finish();
                break;
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

}
