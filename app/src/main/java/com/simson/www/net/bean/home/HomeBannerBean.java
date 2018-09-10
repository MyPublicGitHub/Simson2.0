package com.simson.www.net.bean.home;

import java.util.List;

/**
 * 作者：冯涛 on 2018/5/11 14:34
 * <p>
 * 邮箱：716774214@qq.com
 */
public class HomeBannerBean {


    private List<BroadcastsBean> broadcasts;
    private List<BroadcastsBean> signIns;

    public List<BroadcastsBean> getBroadcasts() {
        return broadcasts;
    }

    public void setBroadcasts(List<BroadcastsBean> broadcasts) {
        this.broadcasts = broadcasts;
    }

    public List<BroadcastsBean> getSignIns() {
        return signIns;
    }

    public void setSignIns(List<BroadcastsBean> signIns) {
        this.signIns = signIns;
    }

}
