package com.simson.www.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.application.MyApp;
import com.simson.www.event.RxEvent;
import com.simson.www.widget.WaitProgressDialog;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.PublishSubject;

/**
 * Activity 基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar mToolbar;
    protected TextView mTitle;
    protected FrameLayout mContainerLayout;
    private WaitProgressDialog loadingDialog = null;
    private PublishSubject mSubject;
    private DisposableObserver mDisposableObserver;
    private RxEvent mRxEvent;
    private Unbinder unBinder;

    @Override
    protected void onCreate(Bundle bundle) {
        if (bundle != null) {
            //如果系统回收Activity,但是系统却保留了Fragment,当Activity被重新初始化,此时,系统保存的Fragment 的getActivity为空，
            //所以要移除旧的Fragment,重新初始化新的Fragment
            String FRAGMENTS_TAG = "android:support:fragments";
            bundle.remove(FRAGMENTS_TAG);
        }
        super.onCreate(bundle);

        setContentView(R.layout.activity_base);
        Intent intent = getIntent();
        if (intent != null)
            getIntent(intent);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle = (TextView) findViewById(R.id.title);
        mContainerLayout = (FrameLayout) findViewById(R.id.frameLayout);

        //初始化ToolBar
        boolean isToolbar = initToolbar();
        if (isToolbar) {
//            setSupportActionBar(mToolbar);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //必须要在setSupportActionBar之后,不然不起作用
            mToolbar.setNavigationOnClickListener(v -> onNavigationClick());
        } else {
            mToolbar.setVisibility(View.GONE);
        }
        //初始化Content
        initContent(getLayoutId());
        mRxEvent = RxEvent.getInstance();
        //注册事件线
        mSubject = mRxEvent.registerEvent(registerEvent());
        mDisposableObserver = new ReceiveEvent();
        mSubject.subscribe(mDisposableObserver);
    }

    protected abstract int getLayoutId();

    protected abstract void initViews();

    protected abstract void initData();

    protected abstract void getIntent(Intent intent);

    private class ReceiveEvent extends DisposableObserver {
        @Override
        public void onNext(Object o) {
            receiveEvent(o);
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销事件
        RxEvent.getInstance().unRegisterEvent(registerEvent(), mSubject, mDisposableObserver);
        if (unBinder != null)
            unBinder.unbind();
        RefWatcher refWatcher = MyApp.getRefWatcher(this);
        refWatcher.watch(this);
    }

    protected abstract void receiveEvent(Object object);

    protected abstract String registerEvent();

    protected void onNavigationClick() {
        finish();
    }

    protected abstract boolean initToolbar();

    private void initContent(int layoutId) {
        if (layoutId != 0) {
            View contentView = LayoutInflater.from(this).inflate(layoutId, mContainerLayout, false);
            mContainerLayout.addView(contentView);
            unBinder = ButterKnife.bind(this);
            initViews();
        }
    }

    /**
     * 显示带消息的进度框
     *
     * @param title 提示
     */
    protected void showLoadingDialog(String title) {
        createLoadingDialog();
        loadingDialog.setMessage(title);
        if (!loadingDialog.isShowing() && !BaseActivity.this.isFinishing()) {
            loadingDialog.show();
        }
    }

    /**
     * 显示进度框
     */
    protected void showLoadingDialog() {
        createLoadingDialog();
        if (!loadingDialog.isShowing() && !BaseActivity.this.isFinishing())
            loadingDialog.show();
    }

    /**
     * 创建LodingDialog
     */
    private void createLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = new WaitProgressDialog(this);
            loadingDialog.setCancelable(true);
            loadingDialog.setCanceledOnTouchOutside(false);
        }
    }

    /**
     * 隐藏进度框
     */
    protected void hideLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    /**
     * 点击空白区域隐藏pop 和输入框
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    /**
     * 点击空白区域隐藏pop 和输入框
     */
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

}
