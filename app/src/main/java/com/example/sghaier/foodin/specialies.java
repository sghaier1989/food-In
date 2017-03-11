package com.example.sghaier.foodin;

/**
 * Created by lenovo on 21/02/2017.
 */
public class specialies {

    int id ;
    String nom ;
    String classement ;

    public specialies(int id, String nom, String classement) {
        this.id = id;
        this.nom = nom;
        this.classement = classement;
    }

    public specialies() {
    }

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

    public String getClassement() {
        return classement;
    }

    public void setClassement(String classement) {
        this.classement = classement;
    }

    @Override
    public String toString() {
        return "specialies{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", classement='" + classement + '\'' +
                '}';
    }
}
