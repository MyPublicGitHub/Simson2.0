package com.simson.www.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.core.view.IView;
import com.simson.www.utils.ToastUtils;

import java.util.List;

/**
 */

public abstract class BasePresenterFragment<P extends BasePresenter<V>, V extends IView> extends BaseFragment implements IView {

    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        //关联View
        attachView();
        initData();
    }
    @Override
    protected void initData() {}

    @Override
    public void onDestroy() {
        //接触presenter与View关联
        detachView();
        //移除所有请求
        removeAllDisposable();
        super.onDestroy();
    }

    protected void removeAllDisposable() {
        if (mPresenter != null) {
            mPresenter.removeAllDisposable();
        }
    }

    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.removeAllDisposable();
            mPresenter = null;
        }
    }

    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
    }

    protected abstract P createPresenter();


    @Override
    public void showLoading(String msg) {
        showLoadingDialog(msg);
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public void showFail(String msg) {
        ToastUtils.showToast(getActivity(), msg);
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
}
