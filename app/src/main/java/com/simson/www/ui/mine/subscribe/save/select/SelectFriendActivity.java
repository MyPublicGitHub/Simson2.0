package com.simson.www.ui.mine.subscribe.save.select;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.simson.www.R;
import com.simson.www.net.bean.home.FriendBean;
import com.simson.www.ui.adapter.SelectFriendAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectFriendActivity extends BasePresenterActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_friend;
    }

    SelectFriendAdapter adapter;
    List<FriendBean> data;

    @Override
    protected void initViews() {
        data = new ArrayList<>();
        FriendBean bean = new FriendBean();
        bean.phone = "";
        data.add(bean);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SelectFriendAdapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("陪同好友");
        return true;
    }


    @OnClick({R.id.iv_add, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add:
                if (TextUtils.isEmpty(data.get(data.size() - 1).phone)) {
                    ToastUtils.showToast("请输入联系号码");
                    return;
                }
                if (data.size() == 3) {
                    ToastUtils.showToast("最多添加3个");
                    return;
                }
                FriendBean bean = new FriendBean();
                bean.phone = "";
                data.add(bean);
                adapter = new SelectFriendAdapter(data);
                recyclerView.setAdapter(adapter);
                break;
            case R.id.btn_commit:
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < data.size(); i++) {
                    if (i == 0) {
                        sb.append(data.get(i).phone);
                    } else {
                        sb.append(",").append(data.get(i).phone);
                    }
                }
                setResult(1003, new Intent()
                        .putExtra("data", sb.toString())
                        .putExtra("dataSize", data.size()+""));
                finish();
                break;
        }
    }

}
