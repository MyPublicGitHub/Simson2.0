package com.simson.www.net.bean.home;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class ImageBean implements MultiItemEntity {
    public List<String> images;
    public static int ONE = 1;
    public static int TOW = 2;
    public static int MORE = 3;
    public static int NULL = 0;

    @Override
    public int getItemType() {
        if (images == null) {
            return NULL;
        } else if (images.size() == 1) {
            return ONE;
        } else if (images.size() == 2) {
            return TOW;
        } else if (images.size() >= 3) {
            return MORE;
        } else {
            return NULL;
        }

    }
}
