package com.simson.www.ui.community.knowledge.detail;

import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.just.agentweb.AgentWeb;
import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.LogUtils;

import butterknife.BindView;

public class WebViewActivity extends BasePresenterActivity {

    @BindView(R.id.container)
    FrameLayout mContainer;
    private AgentWeb mAgentWeb;
    private String url = "",title;

    @Override
    protected void getIntent(Intent intent) {
        if (intent != null) {
            title = getIntent().getStringExtra(Const.WEB_VIEW_TITLE);
            url = getIntent().getStringExtra(Const.WEB_VIEW_URL);
            LogUtils.e(title+"访问URL:" + url);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                title = Html.fromHtml(title, Html.FROM_HTML_MODE_LEGACY).toString();
            } else {
                title = Html.fromHtml(title).toString();
            }
        }
    }

    @Override
    protected void initData() {
        mTitle.setText(title+"");
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mContainer, new FrameLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(R.color.black)
                .createAgentWeb()
                .ready()
                .go(url);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected boolean initToolbar() {
        return true;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //将事件交给AgentWeb做处理
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAgentWeb.destroy();
    }
}
