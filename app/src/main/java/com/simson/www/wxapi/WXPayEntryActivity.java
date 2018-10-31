package com.simson.www.wxapi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.ToastUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.pay_result);
        api = WXAPIFactory.createWXAPI(this, Const.WE_CHAT_APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        LogUtils.e("onReq, req = " + req.openId);
    }

    @Override
    public void onResp(BaseResp resp) {
        LogUtils.e("onPayFinish, errCode = " + resp.errCode);
        /*if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_name);
            builder.setMessage(String.valueOf(resp.errCode+resp.errStr));
            builder.show();
        }*/
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                ToastUtils.showToast("支付成功");
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                ToastUtils.showToast("取消支付");
                break;
           default:
               ToastUtils.showToast("支付失败");
               break;
        }
    }
}