package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;

import java.util.List;

public class DetailAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public DetailAdapter(@Nullable List<String> data) {
        super(R.layout.item_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        WebView webView = helper.getView(R.id.web);
        WebSettings settings = webView.getSettings();
        // 设置可任意缩放
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webView.loadUrl(item);
        //GlideUtils.with(item,helper.getView(R.id.iv_image));
    }
}
