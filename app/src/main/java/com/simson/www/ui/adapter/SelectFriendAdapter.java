package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.home.FriendBean;
import com.simson.www.net.bean.home.HospitalItemBean;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class SelectFriendAdapter extends BaseQuickAdapter<FriendBean, BaseViewHolder> {

    public SelectFriendAdapter(@Nullable List<FriendBean> data) {
        super(R.layout.item_select_friend, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendBean item) {

        helper.setText(R.id.et_phone, item.phone + "");
        EditText editText = helper.getView(R.id.et_phone);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                item.phone = s.toString();
            }
        });
    }
}
