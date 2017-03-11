package com.example.sghaier.foodin;

/**
 * Created by lenovo on 05/03/2017.
 */

public class vilecodepostal {

    int id ;
    int codepostal ;
    int ville_id ;


    public vilecodepostal(int id, int codepostal, int ville_id) {
        this.id = id;
        this.codepostal = codepostal;
        this.ville_id = ville_id;
    }

    public vilecodepostal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(int codepostal) {
        this.codepostal = codepostal;
    }

    public int getVille_id() {
        return ville_id;
    }

    public void setVille_id(int ville_id) {
        this.ville_id = ville_id;
    }

    @Override
    public String toString() {
        return "vilecodepostal{" +
                "id=" + id +
                ", codepostal=" + codepostal +
                ", ville_id=" + ville_id +
                '}';
    }
}
