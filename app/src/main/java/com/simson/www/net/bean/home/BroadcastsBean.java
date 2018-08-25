package com.simson.www.net.bean.home;

public class BroadcastsBean {
    /**
     * id : 7
     * icon : http://58.213.14.195:8081/upload/homepage/banner.jpg
     * menu_title : 轮播图
     * menu_vice_title :
     */

    private String id;
    private String icon;
    private String img_path;
    private String menu_title;
    private String menu_vice_title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMenu_title() {
        return menu_title;
    }

    public void setMenu_title(String menu_title) {
        this.menu_title = menu_title;
    }

    public String getMenu_vice_title() {
        return menu_vice_title;
    }

    public void setMenu_vice_title(String menu_vice_title) {
        this.menu_vice_title = menu_vice_title;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
}
