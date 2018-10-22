package com.simson.www.ui.base;

import android.content.Intent;
import android.os.Bundle;

import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.home.CityListBean;
import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.core.view.IView;
import com.simson.www.utils.ToastUtils;

import java.util.List;

/**
 * 管理Presenter的Activity基类
 */

public abstract class BasePresenterActivity<P extends BasePresenter<V>, V extends IView> extends BaseActivity implements IView {

    protected P mPresenter;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        mPresenter = createPresenter();
        attachView();
        initData();
    }

    @Override
    protected void onNavigationClick() {
        finish();
    }

    @Override
    protected void getIntent(Intent intent) {
    }

    protected abstract P createPresenter();


    @Override
    protected void onDestroy() {
        //接触presenter与View关联
        detachView();
        //移除所有请求
        removeAllDisposable();
        super.onDestroy();
    }

    //关联View
    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
    }

    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected void removeAllDisposable() {
        if (mPresenter != null) {
            mPresenter.removeAllDisposable();
        }
    }

    @Override
    protected boolean initToolbar() {
        return false;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initData() {
    }

    @Override
    public void showLoading(String msg) {
        showLoadingDialog(msg);
    }

    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public void showFail(String msg) {
        ToastUtils.showToast(this, msg);
    }

    @Override
    public void showError() {
    }

    @Override
    public void showEmpty() {
    }

    @Override
    protected void receiveEvent(Object object) {
    }

    @Override
    protected String registerEvent() {
        return null;
    }

    @Override
    public void setItemType(List<ItemTypeBean> bean) {
    }

    @Override
    public void collect(BaseBean bean) {

    }

    @Override
    public void follow(BaseBean bean) {

    }

    @Override
    public void praise(BaseBean bean) {

    }

    @Override
    public void cityList(List<CityListBean> bean) {

    }
}
