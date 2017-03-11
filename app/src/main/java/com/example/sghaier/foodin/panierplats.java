package com.example.sghaier.foodin;

/**
 * Created by lenovo on 24/02/2017.
 */

public class panierplats {
    int id ;
    int plat_id;
    int quantity ;
    String remarque ;


    public panierplats(int id, int plat_id, int quantity, String remarque) {
        this.id = id;
        this.plat_id = plat_id;
        this.quantity = quantity;
        this.remarque = remarque;
    }

    public panierplats(int plat_id, int quantity, String remarque) {
        this.plat_id = plat_id;
        this.quantity = quantity;
        this.remarque = remarque;
    }

    public panierplats() {
    }

    @Override
    public String toString() {
        return "panierplats{" +
                "id=" + id +
                ", plat_id=" + plat_id +
                ", quantity='" + quantity + '\'' +
                ", remarque='" + remarque + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlat_id() {
        return plat_id;
    }

    public void setPlat_id(int plat_id) {
        this.plat_id = plat_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

}
