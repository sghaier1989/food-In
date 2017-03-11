package com.example.sghaier.foodin;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by SGHAIER on 31/01/2017.
 */
public class RestauItem implements Serializable{

    String title;

    public RestauItem(String title, String subtitle, String img, String status) {
        this.title = title;
        this.subtitle = subtitle;
        this.img = img;
        this.status = status;
    }

    String subtitle;
    String status;
    String img;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public static ArrayList<RestauItem> getAListOfContact() {
        ArrayList<RestauItem> listrestauitems = new ArrayList<RestauItem>();

        listrestauitems.add(new RestauItem("LE MAHARAJA", "Restaurant Indien","lkldskk", "Livraison | A emporter | Reservation"));
        listrestauitems.add(new RestauItem("LE GARDE MANGER", "Salon de thé ,patisseries, déjener","lkldskk", "A emporter | Reservation"));
        listrestauitems.add(new RestauItem("LE MAHARAJA", "Restaurant Indien","lkldskk", "Livraison | A emporter | Reservation"));
        listrestauitems.add(new RestauItem("LE GARDE MANGER", "Salon de thé ,patisseries, déjener","lkldskk", "A emporter | Reservation"));
        listrestauitems.add(new RestauItem("LE MAHARAJA", "Restaurant Indien","lkldskk", "Livraison | A emporter | Reservation"));
        listrestauitems.add(new RestauItem("LE GARDE MANGER", "Salon de thé ,patisseries, déjener","lkldskk", "A emporter | Reservation"));



        return listrestauitems;
    }

    public static ArrayList<RestauItem> getAListOfCommandetemp() {
        ArrayList<RestauItem> listrestauitems = new ArrayList<RestauItem>();

        listrestauitems.add(new RestauItem("Sanda Nan", "2,00€","3", ""));
        listrestauitems.add(new RestauItem("Beef Mardas", "11,00€","2", "Sans suppléments"));
        listrestauitems.add(new RestauItem("Beef Mardas", "11,00€","1", "Suppléments soda chawal 2,50€"));
        listrestauitems.add(new RestauItem("Beef Mardas", "11,00€","2", "Sans suppléments"));
        listrestauitems.add(new RestauItem("Sanda Nan", "2,00€","3", ""));
        listrestauitems.add(new RestauItem("Beef Mardas", "11,00€","4", "Suppléments soda chawal 2,50€"));


        return listrestauitems;
    }

    public static ArrayList<RestauItem> getAListOfHistoriquetemp() {
        ArrayList<RestauItem> listrestauitems = new ArrayList<RestauItem>();

        listrestauitems.add(new RestauItem("Le MAHARAJA", "41,50€ T.T.C","13/12/16 à 19h56", ""));
        listrestauitems.add(new RestauItem("Le PALACE", "76,00€ T.T.C","16/11/16 à 20h39", ""));
        listrestauitems.add(new RestauItem("FRESH BOX", "44,90€ T.T.C","11/10/16 à 21h28", ""));

        return listrestauitems;
    }



}
