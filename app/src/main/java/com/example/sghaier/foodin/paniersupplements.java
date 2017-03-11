package com.example.sghaier.foodin;

/**
 * Created by lenovo on 24/02/2017.
 */

public class paniersupplements {

    int  id ;
    int panierplat_id ;
    int supplimentchoice_id ;
    int quantite ;

    public paniersupplements(int panierplat_id, int supplimentchoice_id, int quantite) {
        this.panierplat_id = panierplat_id;
        this.supplimentchoice_id = supplimentchoice_id;
        this.quantite = quantite;
    }

    public paniersupplements(int id, int panierplat_id, int supplimentchoice_id, int quantite) {
        this.id = id;
        this.panierplat_id = panierplat_id;
        this.supplimentchoice_id = supplimentchoice_id;
        this.quantite = quantite;
    }

    public paniersupplements() {
    }

    @Override
    public String toString() {
        return "paniersupplements{" +
                "id=" + id +
                ", panierplat_id=" + panierplat_id +
                ", supplimentchoice_id=" + supplimentchoice_id +
                ", quantite=" + quantite +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPanierplat_id() {
        return panierplat_id;
    }

    public void setPanierplat_id(int panierplat_id) {
        this.panierplat_id = panierplat_id;
    }

    public int getSupplimentchoice_id() {
        return supplimentchoice_id;
    }

    public void setSupplimentchoice_id(int supplimentchoice_id) {
        this.supplimentchoice_id = supplimentchoice_id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
