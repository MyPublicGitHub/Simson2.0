package com.simson.www.net.bean.home;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class IndexSynchysisItemBean implements MultiItemEntity {
    private int type;
    private List<String> pictures;

    @Override
    public int getItemType() {
        return type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

}
