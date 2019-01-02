package com.simson.www.ui.main.login;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.simson.www.BuildConfig;
import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.event.Event;
import com.simson.www.event.RxEvent;
import com.simson.www.net.bean.main.CodeBean;
import com.simson.www.net.bean.main.LoginBean;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.main.reset.ResetPasswordActivity;
import com.simson.www.utils.SPUtils;
import com.simson.www.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BasePresenterActivity<LoginPresenter, LoginContract.View> implements LoginContract.View {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_get_code)
    TextView btnGetCode;
    @BindView(R.id.ll_phone_code)
    LinearLayout llPhoneCode;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.ll_user_psw)
    LinearLayout llUserPsw;
    @BindView(R.id.btn_sign_in)
    Button btnSignIn;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;
    @BindView(R.id.iv_password)
    ImageView ivPassword;
    private int mType;
    private boolean isHideFirst = true;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    llPhoneCode.setVisibility(android.view.View.GONE);
                    llUserPsw.setVisibility(android.view.View.VISIBLE);
                    mType = 1;
                } else {
                    llPhoneCode.setVisibility(android.view.View.VISIBLE);
                    llUserPsw.setVisibility(android.view.View.GONE);
                    mType = 0;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public void showCode(CodeBean bean) {
        ToastUtils.showToast("获取验证码成功");
        if (BuildConfig.DEBUG) {
            etCode.setText(bean.getCode());
            mPresenter.login();
        }
    }

    @Override
    public void showLogin(LoginBean bean) {
        ToastUtils.showToast("登录成功");
        SPUtils.put(Const.USER_INFO.CUSTOMER_MOBLE, bean.getMobile());
        RxEvent.getInstance().postEvent(Const.EVENT_ACTION.LOGIN, new Event(Event.Type.LOGIN, true));
        finish();
    }


    @OnClick({R.id.btn_get_code, R.id.btn_sign_in, R.id.tv_forget_password, R.id.tv_agreement, R.id.iv_password})
    public void onViewClicked(android.view.View view) {
        switch (view.getId()) {
            case R.id.btn_get_code:
                mPresenter.getCode();
                break;
            case R.id.btn_sign_in:

                mPresenter.login();
                break;
            case R.id.tv_forget_password:
                startActivity(new Intent(this, ResetPasswordActivity.class));
                break;

            case R.id.iv_password:
                if (isHideFirst == true) {
                    //ivPassword.setImageResource(R.drawable.open);
                    //密文
                    HideReturnsTransformationMethod method1 = HideReturnsTransformationMethod.getInstance();
                    etPassword.setTransformationMethod(method1);
                    isHideFirst = false;
                } else {
                    //ivPassword.setImageResource(R.drawable.close);
                    //密文
                    TransformationMethod method = PasswordTransformationMethod.getInstance();
                    etPassword.setTransformationMethod(method);
                    isHideFirst = true;

                }
                // 光标的位置
                int index = etPassword.getText().toString().length();
                etPassword.setSelection(index);
                break;
            case R.id.tv_agreement:

                break;
        }
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("登录");
        /*mToolbar.inflateMenu(R.menu.toolbar_menu_login);
        mToolbar.setOnMenuItemClickListener(item -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            return false;
        });*/
        return true;
    }

    @Override
    public String getUserName() {
        if (mType == 0) return etPhone.getText().toString();
        else return etUsername.getText().toString();
    }

    @Override
    public String getPassWord() {
        if (mType == 0) return etCode.getText().toString();
        else return etPassword.getText().toString();
    }
}
