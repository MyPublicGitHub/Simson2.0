package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.mine.AlopeciaBean;

import java.util.List;

public class AlopeciaAdapter extends BaseQuickAdapter<AlopeciaBean, BaseViewHolder> {

    public AlopeciaAdapter(@Nullable List<AlopeciaBean> data) {
        super(R.layout.item_alopecia, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AlopeciaBean item) {

        helper.setText(R.id.tv_date, "检测时间：" + item.getCreate_time());
        helper.setText(R.id.tv_inheritance, "脱发等级：" + item.getAlopecia_grade());
        helper.setText(R.id.tv_hair_loss, "" + item.getAlopecia_desc());
    }
}
