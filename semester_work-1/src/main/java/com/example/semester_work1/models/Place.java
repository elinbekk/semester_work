package com.example.semester_work1.models;

public class Place {
    private String placeId;
    private String title;
    private String address;
    private String category;
    private String description;
    private int price;
    private double rating;
    private String image;

    public Place(String placeId, String title, String address, String category, String description, int price, double rating, String image) {
        this.placeId = placeId;
        this.title = title;
        this.address = address;
        this.category = category;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.image = image;
    }

    public Place(String placeId, String title) {
        this.placeId = placeId;
        this.title = title;
    }

    public Place(String title) {
        this.title = title;
    }

    public String getPlaceId() {
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

    public double getRating() {
        return rating;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}

