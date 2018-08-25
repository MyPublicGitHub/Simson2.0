package com.simson.www.ui.adapter;

import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.application.AppContext;
import com.simson.www.inter.OnHomeListItemClickListener;
import com.simson.www.net.bean.home.HomeDataBean;
import com.simson.www.ui.base.ListDataHolder;


/**
 * 文章列表
 * author:
 * date: 2018/2/12
 */

public class HomeListAdapter extends BaseListAdapter<HomeDataBean> {

    private OnHomeListItemClickListener listener;

    public HomeListAdapter(OnHomeListItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_home_article_list;
    }

    @Override
    public void bindDatas(ListDataHolder holder, final HomeDataBean bean, int itemType, final int position) {
        TextView tv_title = holder.getView(R.id.tv_title);
        tv_title.setText(bean.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(bean);
                }
            }
        });
    }

    private CharSequence getSpanText(String source) {
        Spannable mSpan = new SpannableString(source);
        mSpan.setSpan(new ForegroundColorSpan(ContextCompat.getColor(AppContext.getContext(), R.color.colorPrimary)), 0, source.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        return mSpan;
    }
}
