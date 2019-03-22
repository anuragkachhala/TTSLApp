package com.software.ttsl.model;

public class ServicesData {

    private String title;
    private String discription;
    private int thumbnail;

    public ServicesData(String title, String discription, int thumbnail) {
        this.title = title;
        this.discription = discription;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
