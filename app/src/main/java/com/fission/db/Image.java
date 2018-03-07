package com.fission.db;


public class Image {

    String image_url;
    String image_des;

    public String getImageDescription() {
       return image_des;
    }

    public void setImageDescription(String image_des) {

        this.image_des = image_des;
    }

    public String getImageUrl() {

        return image_url;
    }

    public void setImageUrl(String image_url) {

        this.image_url = image_url;
    }
}
