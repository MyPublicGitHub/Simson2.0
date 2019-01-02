package com.simson.www.ui.main.vote;


import android.text.TextUtils;

import com.simson.www.net.bean.main.ProgramBean;
import com.simson.www.net.bean.main.VoteBean;
import com.simson.www.net.callback.RxRedObserver;
import com.simson.www.ui.core.model.VoteModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.ToastUtils;

import java.util.List;


public class VotePresenter extends BasePresenter<VoteContract.View> implements VoteContract.Presenter {
    private VoteModel mModel;
    private VoteContract.View mView;

    VotePresenter() {
        this.mModel = new VoteModel();
    }

    @Override
    public void program() {
        //http://47.99.72.252/program.do?phone=15651739155  节目列表
        mView = getView();
        RxRedObserver<List<ProgramBean>> observer = new RxRedObserver<List<ProgramBean>>(this) {
            @Override
            public void onNext(List<ProgramBean> bean) {
                //请求成功
                if (bean != null) {
                    mView.program(bean);
                } else {
                    //失败
                    mView.showFail("获取节目列表失败");
                }
            }
        };
        String url = "http://toupiao.maofa.com/program.do?phone=" + mView.phone();
        mModel.program(url, observer);
        addDisposable(observer);
    }

    @Override
    public void vote() {
        mView = getView();
        if (TextUtils.isEmpty(mView.id())) {
            ToastUtils.showToast("请先选中节目");
            return;
        }
        RxRedObserver<VoteBean> observer = new RxRedObserver<VoteBean>(this) {
            @Override
            public void onNext(VoteBean bean) {
                mView.vote(bean);
            }
        };
        //http://47.99.72.252/vote.do?phone=00890&id=1 投票
        String url = "http://toupiao.maofa.com/vote.do?phone=" + mView.phone() + "&id=" + mView.id();
        //String url = "http://47.99.72.252/vote.do?phone=00890&id=1";
        mModel.vote(url, observer);
        addDisposable(observer);
    }
}
