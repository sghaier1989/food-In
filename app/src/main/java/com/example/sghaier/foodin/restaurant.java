package com.example.sghaier.foodin;

/**
 * Created by lenovo on 16/02/2017.
 */
public class restaurant {

    int id;
    String nom ;
    String adresse ;
    String tel ;
    String presentation;
    String mobilephoto ;
    String mobileicone ;
    String livraison ;
    String takeaway ;
    String commandetable ;
    String classement ;
    int ville_id ;

    public restaurant(int id, String nom, String adresse, String tel, String presentation, String mobilephoto, String mobileicone, String livraison, String takeaway, String commandetable, String classement, int ville_id) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.presentation = presentation;
        this.mobilephoto = mobilephoto;
        this.mobileicone = mobileicone;
        this.livraison = livraison;
        this.takeaway = takeaway;
        this.commandetable = commandetable;
        this.classement = classement;
        this.ville_id = ville_id;
    }

    public restaurant() {
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getMobilephoto() {
        return mobilephoto;
    }

    public void setMobilephoto(String mobilephoto) {
        this.mobilephoto = mobilephoto;
    }

    public String getMobileicone() {
        return mobileicone;
    }

    public void setMobileicone(String mobileicone) {
        this.mobileicone = mobileicone;
    }

    public String getLivraison() {
        return livraison;
    }

    public void setLivraison(String livraison) {
        this.livraison = livraison;
    }

    public String getTakeaway() {
        return takeaway;
    }

    public void setTakeaway(String takeaway) {
        this.takeaway = takeaway;
    }

    public String getCommandetable() {
        return commandetable;
    }

    public void setCommandetable(String commandetable) {
        this.commandetable = commandetable;
    }

    public String getClassement() {
        return classement;
    }

    public void setClassement(String classement) {
        this.classement = classement;
    }

    public int getVille_id() {
        return ville_id;
    }

    public void setVille_id(int ville_id) {
        this.ville_id = ville_id;
    }

    @Override
    public String toString() {
        return "restaurant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", tel='" + tel + '\'' +
                ", presentation='" + presentation + '\'' +
                ", mobilephoto='" + mobilephoto + '\'' +
                ", mobileicone='" + mobileicone + '\'' +
                ", livraison='" + livraison + '\'' +
                ", takeaway='" + takeaway + '\'' +
                ", commandetable='" + commandetable + '\'' +
                ", classement='" + classement + '\'' +
                ", ville_id='" + ville_id + '\'' +
                '}';
    }
}
