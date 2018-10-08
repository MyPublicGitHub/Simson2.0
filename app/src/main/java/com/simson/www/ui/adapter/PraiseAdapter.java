package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.shop.CommentBean;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class PraiseAdapter extends BaseQuickAdapter<CommentBean, BaseViewHolder> {

    public PraiseAdapter(@Nullable List<CommentBean> data) {
        super(R.layout.item_praise, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentBean item) {

        GlideUtils.with(item.getCustomer_head(), helper.getView(R.id.iv_header));
        helper.setText(R.id.tv_name, item.getCustomer_name());
        helper.setText(R.id.tv_date, item.getCreate_time());
        helper.setText(R.id.tv_content, item.getContent());
        helper.setRating(R.id.rate, item.getItem_comment_star());
        helper.setText(R.id.tv_environment, "环境："+item.getProfessional_score() );
        helper.setText(R.id.tv_profession, "专业"+item.getProfessional_score() );
        helper.setText(R.id.tv_serve, "服务："+item.getService_score() );
        helper.setText(R.id.tv_effect, "效果："+item.getEffect_score() );
    }
}
