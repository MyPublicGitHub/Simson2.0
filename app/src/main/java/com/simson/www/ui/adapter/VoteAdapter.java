package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.main.ProgramBean;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class VoteAdapter extends BaseQuickAdapter<ProgramBean, BaseViewHolder> {

    public VoteAdapter(@Nullable List<ProgramBean> data) {
        super(R.layout.item_vote, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProgramBean item) {
        helper.setText(R.id.tv_position, helper.getPosition()+1+"");
        GlideUtils.with(item.getUrl(), helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_title, item.getName() + "");
        helper.setText(R.id.tv_content, item.getDescribe() + "");
        helper.setText(R.id.tv_vote, item.getVotes() + "票");
        ImageView select = helper.getView(R.id.iv_select);
        if (item.isSelection()){
            select.setVisibility(View.VISIBLE);
        }else {
            select.setVisibility(View.GONE);
        }
    }
}
