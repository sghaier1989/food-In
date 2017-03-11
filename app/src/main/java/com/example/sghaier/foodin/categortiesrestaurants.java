package com.example.sghaier.foodin;

/**
 * Created by lenovo on 23/02/2017.
 */

public class categortiesrestaurants {
    int id ;
    int categorieplats_id ;
    int restaurant_id ;

    public categortiesrestaurants(int id, int categorieplats_id, int restaurant_id) {
        this.id = id;
        this.categorieplats_id = categorieplats_id;
        this.restaurant_id = restaurant_id;
    }

    public categortiesrestaurants() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorieplats_id() {
        return categorieplats_id;
    }

    public void setCategorieplats_id(int categorieplats_id) {
        this.categorieplats_id = categorieplats_id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    @Override
    public String toString() {
        return "categortiesrestaurants{" +
                "id=" + id +
                ", categorieplats_id=" + categorieplats_id +
                ", restaurant_id=" + restaurant_id +
                '}';
    }
}
