package com.example.semester_work1.models;

import java.util.UUID;

public class Image {
    String name;
    String src;
    String description;
    int size;
    UUID placeId;
    UUID imageId;

    public Image(String name, String src, String description, int size, UUID placeId) {
        this.name = name;
        this.src = src;
        this.description = description;
        this.size = size;
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public UUID getPlaceId() {
        return placeId;
    }

    public void setPlaceId(UUID placeId) {
        this.placeId = placeId;
    }
}
