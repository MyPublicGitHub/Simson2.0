package com.simson.www.ui.main.reset;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.main.CodeBean;
import com.simson.www.ui.base.BasePresenterActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ResetPasswordActivity extends BasePresenterActivity<ResetPasswordPresenter, ResetPasswordContract.View>
        implements ResetPasswordContract.View {

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
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_password;
    }

    private boolean isHideFirst = true;

    @Override
    public void pwdCode(CodeBean bean) {

    }

    @Override
    public void updateCustomerPwd(BaseBean bean) {

    }

    @OnClick({R.id.btn_commit, R.id.btn_get_code, R.id.iv_password, R.id.tv_agreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_code:
                mPresenter.pwdCode();
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
            case R.id.btn_commit:
                mPresenter.updateCustomerPwd();
                break;
            case R.id.tv_agreement:
                break;
        }
    }

    @Override
    public String newPwd() {
        return etPassword.getText().toString();
    }

    @Override
    public String code() {
        return etCode.getText().toString();
    }

    @Override
    public String mobile() {
        return etPhone.getText().toString();
    }

    @Override
    protected ResetPasswordPresenter createPresenter() {
        return new ResetPasswordPresenter();
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("重置密码");
        return true;
    }

}
