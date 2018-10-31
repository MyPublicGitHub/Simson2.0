package com.simson.www.ui.mine.test.save;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.home.hospital.select.SelectHospitalActivity;
import com.simson.www.utils.CommonUtils;
import com.simson.www.utils.ToastUtils;
import com.simson.www.widget.CommonPopupWindow;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

public class NewHospitalTestActivity extends BasePresenterActivity<NewHospitalTestPresenter, NewHospitalTestContract.View>
        implements NewHospitalTestContract.View {

    @BindView(R.id.tv_hospital)
    TextView tvHospital;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_remake)
    EditText etRemake;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.et_age)
    EditText etAge;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_subscribe_test;
    }

    CommonPopupWindow popupWindow;

    @OnClick({R.id.tv_hospital, R.id.tv_date, R.id.tv_sex, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_hospital:
                startActivityForResult(new Intent(this, SelectHospitalActivity.class), 1001);
                break;
            case R.id.tv_date:
                showDatePickerDialog();
                break;
            case R.id.tv_sex:
                //设置PopupWindow里的子View及点击事件
                popupWindow = new CommonPopupWindow.Builder(this)
                        .setView(R.layout.pop_sexy) //设置PopupWindow布局
                        .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) //设置宽高
                        //.setAnimationStyle(R.style.AnimDown) //设置动画
                        .setBackGroundLevel(0.5f) //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                        .setViewOnclickListener((view12, layoutResId) -> {
                            view12.findViewById(R.id.tv_man).setOnClickListener(onClickListener);
                            view12.findViewById(R.id.tv_woman).setOnClickListener(onClickListener);
                            view12.findViewById(R.id.tv_cancel).setOnClickListener(onClickListener);
                        })
                        .setOutsideTouchable(true) //设置外部是否可点击 默认是true
                        .create(); //开始构建
                popupWindow.showAsDropDown(tvSex);//弹出PopupWindow
                break;
            case R.id.tv_commit:
                mPresenter.saveHospitalTesting();
                break;
        }
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_woman:
                    tvSex.setText("女");
                    customerSex = "2";
                    break;
                case R.id.tv_man:
                    tvSex.setText("男");
                    customerSex = "1";
                    break;
                case R.id.tv_cancel:
                    break;
            }
            popupWindow.dismiss();
        }
    };
    public void showDatePickerDialog() {
        showTimePickerDialog();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_DARK,
                (view, year, monthOfYear, dayOfMonth) -> {
                    date = CommonUtils.getDatePickerToString(year, monthOfYear, dayOfMonth);
                    initDate();
                }
                , Calendar.getInstance().get(Calendar.YEAR)
                , Calendar.getInstance().get(Calendar.MONTH)
                , Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    String mDataTime, date, time;

    public void showTimePickerDialog() {
        new TimePickerDialog(this, AlertDialog.THEME_HOLO_DARK, (view, hourOfDay, minute) -> {
            String hour;
            if (hourOfDay < 10) {
                hour = "0" + hourOfDay;
            } else {
                hour = hourOfDay + "";
            }
            if (minute < 10) {
                time = hour + ":" + "0" + minute;
            } else {
                time = hour + ":" + minute;
            }
            initDate();
        }, 10, 0, true).show();

    }

    private void initDate() {
        mDataTime = date + " " + time;
        if (!TextUtils.isEmpty(date) && !TextUtils.isEmpty(time)) {
            tvDate.setText(mDataTime);
        }
    }

    String hospitalId,customerSex;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (resultCode == 1001) {
            hospitalId = data.getStringExtra("hospitalId");
            tvHospital.setText(data.getStringExtra("hospitalName") + "");
        }
    }

    @Override
    public void saveHospitalTesting(BaseBean bean) {
        ToastUtils.showToast("预约成功");
        finish();
    }

    @Override
    public String hospitalId() {
        return hospitalId;
    }


    @Override
    public String mobile() {
        return etPhone.getText().toString();
    }

    @Override
    public String subscribeTime() {
        return tvDate.getText().toString();
    }

    @Override
    public String customerName() {
        return etName.getText().toString();
    }

    @Override
    public String customerSex() {
        return customerSex;
    }

    @Override
    public String customerAge() {
        return etAge.getText().toString();
    }

    @Override
    public String remark() {
        return etRemake.getText().toString();
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("到院检测");
        return true;
    }

    @Override
    protected NewHospitalTestPresenter createPresenter() {
        return new NewHospitalTestPresenter();
    }


}
