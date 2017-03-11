package com.example.sghaier.foodin;

/**
 * Created by lenovo on 21/02/2017.
 */
public class supplimentchoices {

    int id ;
    String libelle ;
    String classement ;
    Float prix ;
    Float prix_avan_promotion ;
    Float promotion ;
    int supplimenttype_id ;

    public supplimentchoices(int id, String libelle, String classement, Float prix, Float prix_avan_promotion, Float promotion, int supplimenttype_id) {
        this.id = id;
        this.libelle = libelle;
        this.classement = classement;
        this.prix = prix;
        this.prix_avan_promotion = prix_avan_promotion;
        this.promotion = promotion;
        this.supplimenttype_id = supplimenttype_id;
    }

    public supplimentchoices() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getClassement() {
        return classement;
    }

    public void setClassement(String classement) {
        this.classement = classement;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Float getPrix_avan_promotion() {
        return prix_avan_promotion;
    }

    public void setPrix_avan_promotion(Float prix_avan_promotion) {
        this.prix_avan_promotion = prix_avan_promotion;
    }

    public Float getPromotion() {
        return promotion;
    }

    public void setPromotion(Float promotion) {
        this.promotion = promotion;
    }

    public int getSupplimenttype_id() {
        return supplimenttype_id;
    }

    public void setSupplimenttype_id(int supplimenttype_id) {
        this.supplimenttype_id = supplimenttype_id;
    }

    @Override
    public String toString() {
        return "supplimentchoices{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", classement='" + classement + '\'' +
                ", prix=" + prix +
                ", prix_avan_promotion=" + prix_avan_promotion +
                ", promotion=" + promotion +
                ", supplimenttype_id=" + supplimenttype_id +
                '}';
    }
}
