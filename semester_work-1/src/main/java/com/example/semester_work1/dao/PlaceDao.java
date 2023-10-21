package com.example.semester_work1.dao;

import com.example.semester_work1.models.FavouritePlace;
import com.example.semester_work1.models.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceDao extends Dao<Place, Integer> {
    Place save(Place item);
    FavouritePlace saveFp(FavouritePlace item);
    boolean isFavourite(FavouritePlace fp);
    List<Place> getAll();
    List<FavouritePlace> getAllFavouritePlaces();
    Optional<Place> getById(Integer id);
    void delete(Integer id);
    void update(Place item);
}
