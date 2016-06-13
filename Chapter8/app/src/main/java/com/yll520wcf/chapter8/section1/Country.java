package com.yll520wcf.chapter8.section1;

public class Country {
    private String name;// 国家名字
    private int imageId;// 国旗图片

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public Country(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }
}
