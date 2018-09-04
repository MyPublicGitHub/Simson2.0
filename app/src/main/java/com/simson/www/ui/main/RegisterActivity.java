package com.simson.www.ui.main;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.ui.base.BasePresenterActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BasePresenterActivity<RegisterPresenter, RegisterContract.IRegisterView> implements RegisterContract.IRegisterView {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_get_code)
    TextView btnGetCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.iv_password)
    ImageView ivPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;

    @Override
    protected boolean initToolbar() {
        mTitle.setText("注册");
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }
    private boolean isHideFirst = true;
    @OnClick({R.id.btn_get_code, R.id.btn_register, R.id.tv_agreement, R.id.iv_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_code:
                mPresenter.getCode();
                break;
            case R.id.btn_register:
                mPresenter.register();
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
    public String getUserName() {
        return etPhone.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public String getCode() {
        return etCode.getText().toString();
    }

    @Override
    public void showResult(String msg) {

    }
}
