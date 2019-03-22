package com.software.ttsl.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageResponse {


    @SerializedName("imageId")
    @Expose
    private long imageId;
    @SerializedName("imageFile")
    @Expose
    private String imageFile;

    @SerializedName("type")
    @Expose
    private String type;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

}

