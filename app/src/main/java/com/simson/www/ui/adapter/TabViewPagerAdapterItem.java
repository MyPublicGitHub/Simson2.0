package com.simson.www.ui.adapter;

import android.support.v4.app.Fragment;


import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.ui.community.diary.item.DiaryItemFragment;
import com.simson.www.ui.community.expert.item.ExpertItemFragment;
import com.simson.www.ui.community.knowledge.item.KnowledgeItemFragment;
import com.simson.www.ui.home.item.HomeItemFragment;

import java.util.ArrayList;
import java.util.List;


public class TabViewPagerAdapterItem {
    private String title;
    private Fragment fragment;

    public TabViewPagerAdapterItem(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public static List<TabViewPagerAdapterItem> createHomeFragments(List<ItemTypeBean> titleList) {
        ArrayList<TabViewPagerAdapterItem> adapterItems = new ArrayList<>();
        for (int i = 0; i < titleList.size(); i++) {
            adapterItems.add(new TabViewPagerAdapterItem(titleList.get(i).getTypeName(),
                    HomeItemFragment.newInstance(titleList.get(i).getItemTypeId())));
        }
        return adapterItems;
    }

    public static List<TabViewPagerAdapterItem> createDiaryItemFragments(List<String> titleList) {
        ArrayList<TabViewPagerAdapterItem> adapterItems = new ArrayList<>();
        for (int i = 0; i < titleList.size(); i++) {
            adapterItems.add(new TabViewPagerAdapterItem(titleList.get(i),
                    DiaryItemFragment.newInstance(i)));
        }
        return adapterItems;
    }

    public static List<TabViewPagerAdapterItem> createKnowledgeItemFragments(List<String> titleList) {
        ArrayList<TabViewPagerAdapterItem> adapterItems = new ArrayList<>();
        for (int i = 0; i < titleList.size(); i++) {
            adapterItems.add(new TabViewPagerAdapterItem(titleList.get(i),
                    KnowledgeItemFragment.newInstance(i)));
        }
        return adapterItems;
    }

    public static List<TabViewPagerAdapterItem> createExpertItemFragments(List<String> titleList) {
        ArrayList<TabViewPagerAdapterItem> adapterItems = new ArrayList<>();
        for (int i = 0; i < titleList.size(); i++) {
            adapterItems.add(new TabViewPagerAdapterItem(titleList.get(i),
                    ExpertItemFragment.newInstance(i)));
        }
        return adapterItems;
    }
}
