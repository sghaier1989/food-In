package com.example.sghaier.foodin;

/**
 * Created by lenovo on 21/02/2017.
 */
public class plats {
    int id ;
    String nom ;
    Float prix ;
    String Description ;
    Float prix_avan_promotion ;
    Float promotion ;
    String photo ;
    String classement ;
    int restaurant_id ;
    int categorie_plat_id ;
    Float tva ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public int getCategorie_plat_id() {
        return categorie_plat_id;
    }

    public void setCategorie_plat_id(int categorie_plat_id) {
        this.categorie_plat_id = categorie_plat_id;
    }

    public Float getTva() {
        return tva;
    }

    public void setTva(Float tva) {
        this.tva = tva;
    }

    public String getClassement() {
        return classement;
    }

    public void setClassement(String classement) {
        this.classement = classement;
    }

    public plats(int id, String nom, Float prix, String description, Float prix_avan_promotion, Float promotion, String photo, String classement, int restaurant_id, int categorie_plat_id, Float tva) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        Description = description;
        this.prix_avan_promotion = prix_avan_promotion;
        this.promotion = promotion;
        this.photo = photo;
        this.classement = classement;
        this.restaurant_id = restaurant_id;
        this.categorie_plat_id = categorie_plat_id;
        this.tva = tva;
    }

    public plats() {
    }

    @Override
    public String toString() {
        return "plats{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", Description='" + Description + '\'' +
                ", prix_avan_promotion=" + prix_avan_promotion +
                ", promotion=" + promotion +
                ", photo='" + photo + '\'' +
                ", restaurant_id=" + restaurant_id +
                ", categorie_plat_id=" + categorie_plat_id +
                ", tva=" + tva +
                '}';
    }
}
