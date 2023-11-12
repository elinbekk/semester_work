package com.example.semester_work1.dao;

import com.example.semester_work1.models.FavouritePlace;
import com.example.semester_work1.models.Place;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlaceDao extends Dao<Place, UUID> {
    void save(Place item);
    List<Place> getAll();
    Optional<Place> getById(UUID id);
    void delete(UUID id);
    void update(Place item);
}
