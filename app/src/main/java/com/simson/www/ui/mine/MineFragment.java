package com.simson.www.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.event.Event;
import com.simson.www.net.bean.mine.CustomerBean;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.main.login.LoginActivity;
import com.simson.www.ui.mine.cart.ShopCartActivity;
import com.simson.www.ui.mine.collect.CollectActivity;
import com.simson.www.ui.mine.diary.MyDiaryActivity;
import com.simson.www.ui.mine.integral.IntegralActivity;
import com.simson.www.ui.mine.integral.mall.IntegralMallActivity;
import com.simson.www.ui.mine.invitation.InvitationActivity;
import com.simson.www.ui.mine.message.MyMessageActivity;
import com.simson.www.ui.mine.order.OrderActivity;
import com.simson.www.ui.mine.post.MyPostActivity;
import com.simson.www.ui.mine.set.SettingActivity;
import com.simson.www.ui.mine.sign.SignActivity;
import com.simson.www.ui.mine.user.UserInfoActivity;
import com.simson.www.ui.mine.wallet.WalletActivity;
import com.simson.www.utils.GlideUtils;
import com.simson.www.utils.SPUtils;
import com.simson.www.widget.CircleImageView;

import butterknife.BindView;
import butterknife.OnClick;


public class MineFragment extends BasePresenterFragment<MinePresenter, MineContract.View> implements MineContract.View {

    @BindView(R.id.iv_header)
    CircleImageView ivHeader;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_fans)
    TextView tvFans;
    @BindView(R.id.tv_follow)
    TextView tvFollow;
    @BindView(R.id.tv_post)
    TextView tvPost;
    @BindView(R.id.tv_diary)
    TextView tvDiary;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.tv_integral)
    TextView tvIntegral;

    @Override
    protected void initViews(View view) {
        GlideUtils.with(SPUtils.get(Const.USER_INFO.CUSTOMER_HEAD, ""), ivHeader);
        tvUserName.setText((String) SPUtils.get(Const.USER_INFO.CUSTOMER_NICK_NAME, ""));
        mPresenter.getCustomer();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void showCustomer(CustomerBean bean) {
        if (bean == null) return;
        GlideUtils.with(bean.getCustomer_head(), ivHeader);
        tvUserName.setText(bean.getCustomer_name() + "");
        tvFans.setText("粉丝：" + bean.getFans());
        tvFollow.setText("关注：" + bean.getFollows());
        tvPost.setText(bean.getSubscribes() + "");
        tvDiary.setText(bean.getDiarys() + "");
        tvMessage.setText(bean.getUnreads() + "");
        tvIntegral.setText(bean.getPoints() + "");
    }

    @OnClick({R.id.iv_setting, R.id.ll_user_info, R.id.ll_integral_mall, R.id.ll_sign_in, R.id.ll_invitation,
            R.id.ll_diary, R.id.ll_message, R.id.ll_integral, R.id.ll_post, R.id.ll_pending_payment,
            R.id.ll_pending_delivery, R.id.ll_already_shipped, R.id.ll_evaluate, R.id.ll_refund,
            R.id.ll_shop_card, R.id.ll_collect, R.id.ll_wallet})
    public void onViewClicked(View view) {
        if (TextUtils.isEmpty((String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""))) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            return;
        }
        switch (view.getId()) {
            case R.id.iv_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.ll_integral_mall:
                startActivity(new Intent(getActivity(), IntegralMallActivity.class));
                break;
            case R.id.ll_sign_in:
                startActivity(new Intent(getActivity(), SignActivity.class));
                break;
            case R.id.ll_user_info:
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
                break;
            case R.id.ll_invitation:
                startActivity(new Intent(getActivity(), InvitationActivity.class));
                break;
            case R.id.ll_diary:
                startActivity(new Intent(getActivity(), MyDiaryActivity.class));
                break;
            case R.id.ll_message:
                startActivity(new Intent(getActivity(), MyMessageActivity.class));
                break;
            case R.id.ll_integral:
                startActivity(new Intent(getActivity(), IntegralActivity.class));
                break;
            case R.id.ll_post:
                startActivity(new Intent(getActivity(), MyPostActivity.class));
                break;
            case R.id.ll_pending_payment://status：1待支付；2已支付；空全部
                startActivity(new Intent(getActivity(), OrderActivity.class).putExtra("status", "1"));
                break;
            case R.id.ll_pending_delivery:
                startActivity(new Intent(getActivity(), OrderActivity.class).putExtra("status", "2"));
                break;
            case R.id.ll_already_shipped:
                startActivity(new Intent(getActivity(), OrderActivity.class).putExtra("status", ""));
                break;
            case R.id.ll_evaluate:
                break;
            case R.id.ll_refund:
                break;
            case R.id.ll_shop_card:
                startActivity(new Intent(getActivity(), ShopCartActivity.class));
                break;
            case R.id.ll_collect:
                startActivity(new Intent(getActivity(), CollectActivity.class));
                break;
            case R.id.ll_wallet:
                startActivity(new Intent(getActivity(), WalletActivity.class));
                break;
        }
    }

    @Override
    protected void receiveEvent(Object object) {
        Event mEvent = (Event) object;
        if (mEvent.type == Event.Type.LOGIN) {
            GlideUtils.with(SPUtils.get(Const.USER_INFO.CUSTOMER_HEAD, ""), ivHeader);
            tvUserName.setText((String) SPUtils.get(Const.USER_INFO.CUSTOMER_NICK_NAME, ""));
        }
    }

    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter();
    }

    @Override
    protected String registerEvent() {
        return Const.EVENT_ACTION.LOGIN;
    }

    @Override
    protected void getBundle(Bundle bundle) {

    }

}