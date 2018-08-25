package com.simson.www.net.bean.home;

public class MenusBean {
    /**
     * id : 1
     * icon : http://58.213.14.195:8081/upload/homepage/hospital.png
     * menu_title : 医院
     * menu_vice_title :
     */

    private int id;
    private String icon;
    private String menu_title;
    private String menu_vice_title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
