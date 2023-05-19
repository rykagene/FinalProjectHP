package com.example.finalprojecthp;

public class ItemData2 {
    private String author;
    private String summary;
    private String imageUrl;
    private String title;
    private String release;
    private String time;
    private String id;
    private String rating;
    private String director;
    private String producer;

    public ItemData2(String id, String imageUrl, String title, String summary, String release,String author) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.release = release;
        this.author = author;
        this.summary = summary;

//        this.release = release;
//        this.director = director;
//        this.author = author;
//        this.summary = summary;

    }

    public String getAuthor() {
        return author;
    }

    public String getRelease() {
        return release;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public String getSummary() {
        return summary;
    }
//    public String getAuthor() {
//        return author;
//    }
    public String getTitle() {
        return title;
    }
//    public String getRelease() {
//        return release;
//    }





    public String getId() {
        return id;
    }


//    public String getRating() {
//        return rating;
//    }
//    public String getDirector() {
//        return director;
//    }
//    public String getProducer() {
//        return producer;
//    }
}
