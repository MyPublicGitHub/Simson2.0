package com.simson.www.net.bean.home;

import java.util.List;

/**
 * 作者：冯涛 on 2018/5/11 14:34
 * <p>
 * 邮箱：716774214@qq.com
 */
public class HomeHeaderBean {


    private List<BroadcastsBean> broadcasts;
    private List<MenusBean> menus;
    private List<MenusBean> functions;

    public List<BroadcastsBean> getBroadcasts() {
        return broadcasts;
    }

    public void setBroadcasts(List<BroadcastsBean> broadcasts) {
        this.broadcasts = broadcasts;
    }

    public List<MenusBean> getMenus() {
        return menus;
    }

    public void setMenus(List<MenusBean> menus) {
        this.menus = menus;
    }

    public List<MenusBean> getFunctions() {
        return functions;
    }

    public void setFunctions(List<MenusBean> functions) {
        this.functions = functions;
    }


}
