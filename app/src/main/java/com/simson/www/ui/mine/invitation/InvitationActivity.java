package com.simson.www.ui.mine.invitation;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.common.UrlConstainer;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.adapter.InviteAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.SPUtils;
import com.simson.www.utils.ToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InvitationActivity extends BasePresenterActivity {

    @BindView(R.id.tv_invite)
    TextView tvInvite;
    @BindView(R.id.tv_invite_qr)
    TextView tvInviteQr;
    @BindView(R.id.tv_my_invite)
    TextView tvMyInvite;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    InviteAdapter mAdapter;
    @OnClick({R.id.tv_invite, R.id.tv_invite_qr, R.id.tv_my_invite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_invite:
                share(this);
                break;
            case R.id.tv_invite_qr:
                break;
            case R.id.tv_my_invite:
                break;
        }
    }
    public void share(Activity activity) {
        String url =UrlConstainer.getBaseUrl() + "invitationController/inviteFriends?customerId="
                + SPUtils.get(Const.USER_INFO.CUSTOMER_ID, "");
        UMWeb web = new UMWeb(url);
        web.setTitle("下载新生APP，积分兑换好礼");//标题
        web.setThumb(new UMImage(activity, R.mipmap.ic_logo));  //缩略图
        web.setDescription("新生植发送您大礼包");//描述
        new ShareAction(activity).withText(url)
                .withMedia(web)
                .setDisplayList(
                        SHARE_MEDIA.SINA,
                        SHARE_MEDIA.QQ,
                        SHARE_MEDIA.QZONE,
                        SHARE_MEDIA.WEIXIN_CIRCLE,
                        SHARE_MEDIA.WEIXIN)
                .setCallback(listener)
                .open();
    }

    UMShareListener listener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            LogUtils.e("onStart" + share_media.toString());
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            LogUtils.e("onResult" + share_media.toString());
            ToastUtils.showToast("邀请成功");
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            LogUtils.e("onError" + share_media.toString() + "throwable:" + throwable.toString() + "throwable.getMessage:" + throwable.getMessage());
            ToastUtils.showToast(throwable.getMessage());
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            LogUtils.e("onCancel" + share_media.toString());
            ToastUtils.showToast("取消邀请");
        }
    };
    @Override
    protected void initViews() {
        mAdapter = new InviteAdapter(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        mAdapter.bindToRecyclerView(recyclerView);
        mAdapter.setEmptyView(R.layout.list_empty_view);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_invitation;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("邀请好友");
        return true;
    }

}
