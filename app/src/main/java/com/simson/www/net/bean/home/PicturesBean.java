package com.simson.www.net.bean.home;

import java.io.Serializable;

public class PicturesBean implements Serializable {
    /**
     * id : 111
     * url : http://58.213.14.195:8081/upload/hospital.png
     */

    private String id;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}