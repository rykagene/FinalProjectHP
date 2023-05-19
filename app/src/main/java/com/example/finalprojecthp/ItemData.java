package com.example.finalprojecthp;

public class ItemData {
    private String imageUrl;
    private String title;
    private String release;
    private String time;
    private String id;
    private String rating;
    private String director;
    private String producer;

    public ItemData(String id, String imageUrl, String title, String release, String time, String rating, String director, String producer) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.release = release;
        this.time = time;
        this.rating = rating;
        this.director = director;
        this.producer = producer;
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
    public String getTime() { return time; }
    public String getId() {
        return id;
    }
    public String getRating() {
        return rating;
    }
    public String getDirector() {
        return director;
    }
    public String getProducer() {
        return producer;
    }
}
