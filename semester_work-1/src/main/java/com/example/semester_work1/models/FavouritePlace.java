package com.example.semester_work1.models;

import java.util.UUID;

public class FavouritePlace {
    private UUID id;
    private UUID userId;
    private UUID placeId;

    public FavouritePlace(UUID id, UUID userId, UUID placeId) {
        this.id = id;
        this.userId = userId;
        this.placeId = placeId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getPlaceId() {
        return placeId;
    }

    public void setPlaceId(UUID placeId) {
        this.placeId = placeId;
    }
}


