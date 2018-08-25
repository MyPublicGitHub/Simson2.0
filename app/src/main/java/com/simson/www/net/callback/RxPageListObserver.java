package com.simson.www.net.callback;

import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.core.view.IListDataView;

import java.util.List;

/**
 * 分页加载功能的接口回调类
 * 分页加载逻辑在这里统一处理
 */

public abstract class RxPageListObserver<T> extends RxBaseObserver<BaseBean<T>> {

    private IListDataView<T> mListDataView;

    public RxPageListObserver(com.simson.www.ui.core.presenter.BasePresenter mPresenter) {
        super(mPresenter);
        this.mListDataView = (IListDataView<T>) mPresenter.getView();
    }
    @Override
    public void onNext(BaseBean<BaseBean<T>> baseBean) {
//        if (baseBean.errorCode == NetConfig.REQUEST_SUCCESS) {
//
//            BaseBean<T> mListData = baseBean.data;
//
//            if (mListDataView.getPage() == 0) {
//                mListDataView.clearListData();
//            }
//            if (mListData.size()) {
//                mListDataView.showNoMore();
//            } else {
//                mListDataView.autoLoadMore();
//            }
//            onSuccess(mListData);
//        } else {
//            onFail(baseBean.errorCode, baseBean.errorMsg);
//        }
    }


    @Override
    public void onError(Throwable e) {
        super.onError(e);
        mListDataView.showError();
    }

    public abstract void onSuccess(List<T> mData);

    public abstract void onFail(int errorCode, String errorMsg);
}
