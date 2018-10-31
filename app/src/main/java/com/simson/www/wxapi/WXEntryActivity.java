package com.simson.www.wxapi;


import android.os.Bundle;
import android.os.PersistableBundle;

import com.simson.www.utils.LogUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

public class WXEntryActivity extends WXCallbackActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void onReq(BaseReq req) {
        super.onReq(req);
    }

    //微信回调
    @Override
    public void onResp(BaseResp resp) {   //分享之后的回调
        LogUtils.e("微信分享成功回调了");
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK: //正确返回
                break;
        }
        super.onResp(resp);
    }

}
