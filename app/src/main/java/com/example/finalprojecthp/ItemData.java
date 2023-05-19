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
    private String trailer;
    private String budget;
    private String boxoffice;
    private String wiki;

    public ItemData(String id, String imageUrl, String title, String release, String time, String rating, String director, String producer, String trailer, String budget, String boxoffice, String wiki) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.release = release;
        this.time = time;
        this.rating = rating;
        this.director = director;
        this.producer = producer;
        this.trailer = trailer;
        this.budget = budget;
        this.boxoffice = boxoffice;
        this.wiki = wiki;
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
    public String getTrailer() {
        return trailer;
    }
    public String getBudget() {
        return budget;
    }
    public String getBoxoffice() {
        return boxoffice;
    }
    public String getWiki() {
        return wiki;
    }
}
