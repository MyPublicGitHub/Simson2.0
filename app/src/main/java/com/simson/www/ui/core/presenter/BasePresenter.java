package com.simson.www.ui.core.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.PayTask;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.AliPayResult;
import com.simson.www.ui.core.view.IView;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.ToastUtils;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 基类Presenter,用来处理view和请求
 */

public class BasePresenter<V extends IView> implements IPresenter<V> {
    private V view;
    //用来存放Disposable的容器
    private CompositeDisposable mCompositeDisposable;

    //绑定View
    @Override
    public void attachView(V view) {
        this.view = view;
    }

    //解除View绑定
    @Override
    public void detachView() {
        this.view = null;
    }

    //获取绑定的View
    @Override
    public V getView() {
        checkAttachView();
        return view;
    }

    //检查View是否存在
    @Override
    public void checkAttachView() {
        if (view == null)
            throw new RuntimeException("You have no binding this view");
    }

    //添加指定的请求
    @Override
    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null)
            mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(disposable);
    }

    //移除指定的请求
    @Override
    public void removeDisposable(Disposable disposable) {
        if (mCompositeDisposable != null)
            mCompositeDisposable.remove(disposable);
    }

    //取消所有的请求Tag
    @Override
    public void removeAllDisposable() {
        if (mCompositeDisposable != null)
            mCompositeDisposable.clear();
    }

    public void wechatPay(Context context, PayReq request) {
        IWXAPI api = WXAPIFactory.createWXAPI(context, Const.WE_CHAT_APP_ID);
        if (api.getWXAppSupportAPI() < Build.PAY_SUPPORTED_SDK_INT) {
            ToastUtils.showToast("您未安装最新版本微信，不支持微信支付，请安装或升级微信版本");
            return;
        }
        boolean b = api.sendReq(request);
        LogUtils.d("b:" + b + ";wechat");
    }

    public void alipay(Activity activity, String orderInfo) {
        Runnable payRunnable = () -> {
            PayTask alipay = new PayTask(activity);
            Map<String, String> result = alipay.payV2(orderInfo, true);

            Message msg = new Message();
            msg.obj = result;
            mHandler.sendMessage(msg);
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            AliPayResult payResult = new AliPayResult((Map<String, String>) msg.obj);
            switch (payResult.getResultStatus()) {
                case "9000":
                    ToastUtils.showToast("支付成功");
                    break;
                case "8000":
                    ToastUtils.showToast("正在处理中");
                    break;
                case "4000":
                    ToastUtils.showToast("订单支付失败");
                    break;
                case "5000":
                    ToastUtils.showToast("重复请求");
                    break;
                case "6001":
                    ToastUtils.showToast("已取消支付");
                    break;
                case "6002":
                    ToastUtils.showToast("网络连接出错");
                    break;
                case "6004":
                    ToastUtils.showToast("正在处理中");
                    break;
                default:
                    ToastUtils.showToast("支付失败");
                    break;
            }
        }
    };
}
