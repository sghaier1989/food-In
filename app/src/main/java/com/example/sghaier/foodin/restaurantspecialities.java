package com.example.sghaier.foodin;

/**
 * Created by lenovo on 21/02/2017.
 */
public class restaurantspecialities {



    int id ;
    int restaurnt_id ;
    int speciality_id ;

    public restaurantspecialities(int id, int restaurnt_id, int speciality_id) {
        this.id = id;
        this.restaurnt_id = restaurnt_id;
        this.speciality_id = speciality_id;
    }

    @Override
    public String toString() {
        return "restaurantspecialities{" +
                "id=" + id +
                ", restaurnt_id=" + restaurnt_id +
                ", speciality_id=" + speciality_id +
                '}';
    }

    public restaurantspecialities() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurnt_id() {
        return restaurnt_id;
    }

    public void setRestaurnt_id(int restaurnt_id) {
        this.restaurnt_id = restaurnt_id;
    }

    public int getSpeciality_id() {
        return speciality_id;
    }

    public void setSpeciality_id(int speciality_id) {
        this.speciality_id = speciality_id;
    }
}




