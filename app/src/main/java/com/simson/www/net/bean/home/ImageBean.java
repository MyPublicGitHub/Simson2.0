package com.simson.www.net.bean.home;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class ImageBean implements MultiItemEntity {
    public List<String> images;

    @Override
    public int getItemType() {
        if (images == null)
            return 0;
        return images.size();

    }
}
