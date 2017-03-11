package com.example.sghaier.foodin;

/**
 * Created by lenovo on 21/02/2017.
 */
public class supplimenttypes {

    int id ;
    String libelle ;
    String classement ;
    int choicenumbre ;
    int plat_id ;

    public supplimenttypes(int id, String libelle, String classement, int choicenumbre, int plat_id) {
        this.id = id;
        this.libelle = libelle;
        this.classement = classement;
        this.choicenumbre = choicenumbre;
        this.plat_id = plat_id;
    }

    public supplimenttypes() {
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

    public int getChoicenumbre() {
        return choicenumbre;
    }

    public void setChoicenumbre(int choicenumbre) {
        this.choicenumbre = choicenumbre;
    }

    public int getPlat_id() {
        return plat_id;
    }

    public void setPlat_id(int plat_id) {
        this.plat_id = plat_id;
    }

    @Override
    public String toString() {
        return "supplimenttypes{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", classement='" + classement + '\'' +
                ", choicenumbre=" + choicenumbre +
                ", plat_id=" + plat_id +
                '}';
    }
}
