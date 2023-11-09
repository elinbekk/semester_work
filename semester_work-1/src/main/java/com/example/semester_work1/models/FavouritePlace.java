package com.example.semester_work1.models;

public class FavouritePlace {
    private Integer id;
    private Integer userId;
    private int placeId;

    public FavouritePlace(Integer id, Integer userId, int placeId) {
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }
}


