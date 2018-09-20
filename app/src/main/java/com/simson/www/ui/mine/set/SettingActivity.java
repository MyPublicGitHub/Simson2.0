package com.simson.www.ui.mine.set;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.event.Event;
import com.simson.www.event.RxEvent;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.main.login.LoginActivity;
import com.simson.www.ui.mine.feed.FeedBackActivity;
import com.simson.www.ui.mine.set.address.AddressActivity;
import com.simson.www.ui.mine.user.UserInfoActivity;
import com.simson.www.utils.DataCleanManager;
import com.simson.www.utils.SPUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BasePresenterActivity {

    @BindView(R.id.tv_cache)
    TextView tvCache;
    @BindView(R.id.tv_logout)
    TextView tvLogout;

    @OnClick({R.id.tv_user_info, R.id.tv_phone, R.id.tv_password, R.id.tv_address, R.id.tv_feed_back, R.id.tv_cache, R.id.tv_about, R.id.tv_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_user_info:
                startActivity(new Intent(this, UserInfoActivity.class));
                break;
            case R.id.tv_phone:
                break;
            case R.id.tv_password:
                break;
            case R.id.tv_address:
                startActivity(new Intent(this, AddressActivity.class));
                break;
            case R.id.tv_feed_back:
                startActivity(new Intent(this, FeedBackActivity.class));
                break;
            case R.id.tv_cache:
                showLoadingDialog("清理中...");
                DataCleanManager.clearAllCache(this);
                hideLoadingDialog();
                setCache();
                break;
            case R.id.tv_about:
                break;
            case R.id.tv_logout:
                if (TextUtils.isEmpty((String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""))) {
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                } else {
                    logout();
                }
                break;
        }
    }

    @Override
    protected void initViews() {
        setCache();
        setLogin();
    }

    private void logout() {
        SPUtils.clearAll();
        RxEvent.getInstance().postEvent(Const.EVENT_ACTION.LOGIN, new Event(Event.Type.LOGIN, false));
        setLogin();
    }

    private void setCache() {
        String cache = DataCleanManager.getTotalCacheSize(this);
        tvCache.setText("清理缓存（" + cache + ")");
    }

    private void setLogin() {
        if (TextUtils.isEmpty((String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""))) {
            tvLogout.setText("登录");
        } else {
            tvLogout.setText("退出登录");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("设置");
        return true;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

}
