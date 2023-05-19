package com.example.finalprojecthp;

public class ItemData {
    private String imageUrl;
    private String title;
    private String release;
    private String time;
    private String id;

    public ItemData(String id, String imageUrl, String title, String release, String time) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.release = release;
        this.time = time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getRelease() {
        return release;
    }
    public String getTime() {
        return time;
    }

    public String getId() {
        return id;
    }
}
