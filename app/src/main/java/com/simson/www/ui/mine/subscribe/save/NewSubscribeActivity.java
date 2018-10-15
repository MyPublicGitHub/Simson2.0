package com.simson.www.ui.mine.subscribe.save;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class NewSubscribeActivity extends BasePresenterActivity<NewSubscribePresenter, NewSubscribeContract.View>
        implements NewSubscribeContract.View {

    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_hospital)
    TextView tvHospital;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.ll_date)
    LinearLayout llDate;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll_time)
    LinearLayout llTime;
    @BindView(R.id.tv_friend)
    TextView tvFriend;
    @BindView(R.id.tv_car)
    TextView tvCar;
    @BindView(R.id.ll_car)
    LinearLayout llCar;
    @BindView(R.id.ll_friend)
    LinearLayout llFriend;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_remake)
    EditText etRemake;
    @BindView(R.id.tv_commit)
    TextView tvCommit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_subscribe;
    }

    CommonPopupWindow popupWindow;

    @OnClick({R.id.tv_type, R.id.tv_hospital, R.id.ll_date, R.id.ll_time, R.id.ll_car, R.id.ll_friend, R.id.tv_location, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_type:
                //设置PopupWindow里的子View及点击事件
                popupWindow = new CommonPopupWindow.Builder(this)
                        .setView(R.layout.pop_project_type) //设置PopupWindow布局
                        .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) //设置宽高
                        //.setAnimationStyle(R.style.AnimDown) //设置动画
                        .setBackGroundLevel(0.5f) //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                        .setViewOnclickListener((view1, layoutResId) -> {
                            view1.findViewById(R.id.tv_photo).setOnClickListener(onClickListener);
                            view1.findViewById(R.id.tv_camera).setOnClickListener(onClickListener);
                            view1.findViewById(R.id.tv_cancel).setOnClickListener(onClickListener);
                        })
                        .setOutsideTouchable(true) //设置外部是否可点击 默认是true
                        .create(); //开始构建
                popupWindow.showAsDropDown(tvType);//弹出PopupWindow
                break;
            case R.id.tv_hospital:
                startActivityForResult(new Intent(this, SelectHospitalActivity.class), 1001);
                break;
            case R.id.ll_date:
                showDatePickerDialog();
                break;
            case R.id.ll_time:
                showTimePickerDialog();
                break;
            case R.id.ll_car:
                popupWindow = new CommonPopupWindow.Builder(this)
                        .setView(R.layout.pop_car) //设置PopupWindow布局
                        .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) //设置宽高
                        //.setAnimationStyle(R.style.AnimDown) //设置动画
                        .setBackGroundLevel(0.5f) //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                        .setViewOnclickListener((view1, layoutResId) -> {
                            view1.findViewById(R.id.tv_yes).setOnClickListener(onClickListener);
                            view1.findViewById(R.id.tv_no).setOnClickListener(onClickListener);
                            view1.findViewById(R.id.tv_cancel).setOnClickListener(onClickListener);
                        })
                        .setOutsideTouchable(true) //设置外部是否可点击 默认是true
                        .create(); //开始构建
                popupWindow.showAsDropDown(tvCar);//弹出PopupWindow
                break;
            case R.id.ll_friend:
                break;
            case R.id.tv_location:
                break;
            case R.id.tv_commit:
                mPresenter.saveSubscribe();
                break;
        }
    }

    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_DARK,
                (view, year, monthOfYear, dayOfMonth) ->
                        tvDate.setText(CommonUtils.getDatePickerToString(year, monthOfYear, dayOfMonth))
                , Calendar.getInstance().get(Calendar.YEAR)
                , Calendar.getInstance().get(Calendar.MONTH)
                , Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void showTimePickerDialog() {
        new TimePickerDialog(this, AlertDialog.THEME_HOLO_DARK, (view, hourOfDay, minute) -> {
            String hour;
            if (hourOfDay < 10) {
                hour = "0" + hourOfDay;
            } else {
                hour = hourOfDay + "";
            }
            if (minute < 10) {
                tvTime.setText(hour + ":" + "0" + minute);
            } else {
                tvTime.setText(hour + ":" + minute);
            }
        }, 10, 0, true).show();

    }

    String hospitalId, subscribeType, isCar = "0";

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
    public void saveSubscribe(BaseBean bean) {
        ToastUtils.showToast("预约成功");
        finish();
    }

    @Override
    public String hospitalId() {
        return hospitalId;
    }

    @Override
    public String subscribeType() {
        return subscribeType;
    }

    @Override
    public String itemTypeId() {
        return "0";
    }

    @Override
    public String mobile() {
        return etPhone.getText().toString();
    }

    @Override
    public String subscribeDate() {
        return tvDate.getText().toString();
    }

    @Override
    public String subscribeTime() {
        return tvTime.getText().toString();
    }

    @Override
    public String accompanyFriends() {
        return null;
    }

    @Override
    public String isCar() {
        return isCar;
    }

    @Override
    public String isSpecialCar() {
        return null;
    }

    @Override
    public String detailedAddress() {
        return tvLocation.getText().toString();
    }

    @Override
    public String remark() {
        return etRemake.getText().toString();
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("在线预约");
        return true;
    }

    @Override
    protected NewSubscribePresenter createPresenter() {
        return new NewSubscribePresenter();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_photo:
                    tvType.setText("脱发检测预约");
                    subscribeType = "2";
                    break;
                case R.id.tv_camera:
                    tvType.setText("项目预约");
                    subscribeType = "1";
                    break;
                case R.id.tv_yes:
                    tvCar.setText("是");
                    isCar = "1";
                    break;
                case R.id.tv_no:
                    tvCar.setText("否");
                    isCar = "0";
                    break;
            }
            popupWindow.dismiss();
        }
    };
}
