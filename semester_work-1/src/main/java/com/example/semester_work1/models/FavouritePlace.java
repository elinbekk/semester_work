package com.example.semester_work1.models;

public class FavouritePlace {
    private Integer id;
    private String userId;
    private int placeId;

    public FavouritePlace(Integer id, String userId, int placeId) {
        this.id = id;
        this.userId = userId;
        this.placeId = placeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }
}


