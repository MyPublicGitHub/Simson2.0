package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.mine.RedEnvelopesBean;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class RedEnvelopesAdapter extends BaseQuickAdapter<RedEnvelopesBean, BaseViewHolder> {

    public RedEnvelopesAdapter(@Nullable List<RedEnvelopesBean> data) {
        super(R.layout.item_red_envelopes, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RedEnvelopesBean item) {
        /**
         * crowd_no : YEt3DXj9cukceFj9bJEHJCmXX3FPVoPCaLY39jf92jWsn4ge4wBRFkiLOjq1h1Jd
         * coupon_name : 新年大红包！
         * login_id : 15651739155
         * prize_amount : 0.43
         * create_time : 2018-12-31 10:39:58
         */
        helper.setText(R.id.tv_title,item.getPrize_amount()+"元");
        helper.setText(R.id.tv_content,item.getCreate_time()+"");

    }
}
