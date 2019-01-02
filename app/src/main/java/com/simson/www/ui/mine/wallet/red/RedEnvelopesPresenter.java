package com.simson.www.ui.mine.wallet.red;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.main.NewestRedEnvelopeBean;
import com.simson.www.net.bean.mine.RedEnvelopesBean;
import com.simson.www.net.bean.mine.TransactionRecordBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.net.callback.RxRedObserver;
import com.simson.www.ui.core.model.RedEnvelopesModel;
import com.simson.www.ui.core.model.TransactionRecordModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.mine.wallet.log.TransactionRecordContract;
import com.simson.www.utils.AESUtils;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RedEnvelopesPresenter extends BasePresenter<RedEnvelopesContract.View> implements RedEnvelopesContract.Presenter {
    private RedEnvelopesModel mModel;
    private RedEnvelopesContract.View mView;

    RedEnvelopesPresenter() {
        this.mModel = new RedEnvelopesModel();
    }

    @Override
    public void redEnvelopesRecord() {
        mView = getView();
        RxObserver<List<RedEnvelopesBean>> observer = new RxObserver<List<RedEnvelopesBean>>(this) {

            @Override
            public void onSuccess(List<RedEnvelopesBean> mData) {
                mView.redEnvelopesRecord(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail("");
            }
        };
        String url = "https://images.maofa.com/simson_admin/redEnvelopes/redEnvelopesRecord";
        Map<String, String> map = new HashMap<>();
        map.put("login_id", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_MOBLE, ""));
        String json = new Gson().toJson(map);
        mModel.redEnvelopesRecord(url,AESUtils.encrypt(json), observer);
        addDisposable(observer);
    }

}
