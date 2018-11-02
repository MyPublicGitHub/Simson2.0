package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.home.QuestionBean;
import com.simson.www.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends BaseQuickAdapter<QuestionBean.OptionsBean, BaseViewHolder> {

    public TestAdapter(@Nullable List<QuestionBean.OptionsBean> data) {
        super(R.layout.item_test, data);
        images = new ArrayList<>();
        images.add(R.mipmap.ic_test_a);
        images.add(R.mipmap.ic_test_b);
        images.add(R.mipmap.ic_test_c);
        images.add(R.mipmap.ic_test_d);
        images.add(R.mipmap.ic_test_e);
        images.add(R.mipmap.ic_test_f);
    }

    List<Integer> images;

    @Override
    protected void convert(BaseViewHolder helper, QuestionBean.OptionsBean item) {

        GlideUtils.with(images.get(helper.getPosition()), helper.getView(R.id.iv_image));
        if (item.getIs_picture() == 0) {
            helper.getView(R.id.iv_quest_image).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.iv_quest_image).setVisibility(View.VISIBLE);
            GlideUtils.with(item.getOption_picture(), helper.getView(R.id.iv_quest_image));
        }
        TextView textView = helper.getView(R.id.tv_question);
        if (item.isChech) {
            textView.setTextColor(mContext.getResources().getColor(R.color.colorRed));
        } else {
            textView.setTextColor(mContext.getResources().getColor(R.color.black));
        }
        helper.setText(R.id.tv_question, item.getQuestionnaire_option() + "");
    }
}
