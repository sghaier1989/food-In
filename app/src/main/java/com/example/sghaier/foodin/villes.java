package com.example.sghaier.foodin;

import java.io.Serializable;

/**
 * Created by lenovo on 21/02/2017.
 */
public class villes implements Serializable {
    int id ;
    String nom ;
    String classement ;

    public villes() {
    }

    public villes(int id, String nom, String classement) {
        this.id = id;
        this.nom = nom;
        this.classement = classement;
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
        return "villes{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", classement='" + classement + '\'' +
                '}';
    }
}
