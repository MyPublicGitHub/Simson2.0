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
/**
 * testing_id : a3dcb297faac4d28b626d04cb6275abd
 * hospital_id :
 * hospital_name :
 * subscribe_date :
 * subscribe_time :
 * accompany_friends :
 * is_inheritance : 0
 * hair_loss : 0
 * is_hair_planting : 0
 * pictures : []
 */
        helper.setText(R.id.tv_inheritance, "遗传性脱发：" + (item.getIs_inheritance() == 0 ? "无" : "有"));
        helper.setText(R.id.tv_planting, "有无植发史：" + (item.getIs_hair_planting() == 0 ? "无" : "有"));
        helper.setText(R.id.tv_hair_loss, "每天脱发数：" + item.getHair_loss());
    }
}
