package com.fission.db;


import android.net.Uri;

public class ImageItem {

    private Uri imageUri;
    private String imageDesc;

    public ImageItem(Uri imageUri, String imageDesc) {
        this.imageUri = imageUri;
        this.imageDesc = imageDesc;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
    }
}
