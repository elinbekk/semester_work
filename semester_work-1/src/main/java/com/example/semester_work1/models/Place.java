package com.example.semester_work1.models;

public class Place {
    private int placeId;
    private String title;
    private String address;
    private String category;
    private String description;
    private int price;
    private int rating;

    public Place(int placeId, String title, String address, String category, String description, int price, int rating) {
        this.placeId = placeId;
        this.title = title;
        this.address = address;
        this.category = category;
        this.description = description;
        this.price = price;
        this.rating = rating;
    }

    public Place(String title, String address, String category, String description, int price, int rating) {
        this.title = title;
        this.address = address;
        this.category = category;
        this.description = description;
        this.price = price;
        this.rating = rating;
    }

    public Place(String title) {
        this.title = title;
    }

    public int getPlaceId() {
        return placeId;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getRating() {
        return rating;
    }
}

