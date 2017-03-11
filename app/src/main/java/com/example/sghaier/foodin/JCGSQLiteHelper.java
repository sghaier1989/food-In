package com.example.sghaier.foodin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JCGSQLiteHelper extends SQLiteOpenHelper {

    // database version
    private static final int database_VERSION = 1;
    // database name
    private static final String database_NAME = "restaurantDB";
    private static final String table_restaurantS = "restaurants";
    private static final String table_platS = "plats";
    private static final String table_villes = "villes";
    private static final String table_specialies = "specialies";
    private static final String table_categorieplats = "categorieplats";
    private static final String table_supplimenttypes = "supplimenttypes";
    private static final String table_supplimentchoices = "supplimentchoices";
    private static final String table_restaurantspecialities = "restaurants_specialities";
    private static final String table_categortiesrestaurants = "categorties_restaurants";
    private static final String table_vilecodepostals = "vilecodepostals";
    private static final String table_panierplats = "panierplats";
    private static final String table_paniersupplements = "paniersupplements";
    private static final String restaurant_ID = "id";
    private static final String restaurant_nom = "nom";
    private static final String restaurant_adresse = "adresse";
    private static final String restaurant_tel = "tel";
    private static final String restaurant_presentation = "presentation";
    private static final String restaurant_mobilephoto = "mobilephoto";
    private static final String restaurant_mobileicone = "mobileicone";
    private static final String restaurant_livraison = "livraison";
    private static final String restaurant_takeaway = "takeaway";
    private static final String restaurant_commandetable = "commandetable";

    private static final String[] COLUMNS = { restaurant_ID, restaurant_nom, restaurant_adresse , restaurant_tel,restaurant_presentation ,
            restaurant_mobilephoto , restaurant_mobileicone ,restaurant_livraison ,restaurant_takeaway, restaurant_commandetable };
    private static final String[] rowcategories = { "id"  ,"nom" , "classement" };

    public JCGSQLiteHelper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }





    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create restaurant table
        String CREATE_restaurant_TABLE = "CREATE TABLE restaurants ( " + "id INTEGER PRIMARY KEY, nom TEXT  ,adresse TEXT , " + " tel TEXT ,  presentation TEXT , mobilephoto TEXT , mobileicone TEXT , livraison TEXT ,  takeaway TEXT , commandetable TEXT , classement TEXT , ville_id INTEGER )";
        db.execSQL(CREATE_restaurant_TABLE);

        String CREATE_plats_TABLE = "CREATE TABLE plats ( " + "id INTEGER PRIMARY KEY, nom TEXT  ,prix FLOAT , " + " Description TEXT ,   prix_avan_promotion FLOAT , promotion FLOAT , photo TEXT ,  classement TEXT , restaurant_id INTEGER  , categorie_plat_id INTEGER , tva FLOAT )";
        db.execSQL(CREATE_plats_TABLE);


        String CREATE_villes_TABLE = "CREATE TABLE villes ( " + "id INTEGER PRIMARY KEY, nom TEXT  ,photo TEXT , " + " classement TEXT  )";
        db.execSQL(CREATE_villes_TABLE);



        String CREATE_specialies_TABLE =  "CREATE TABLE specialies ( " + "id INTEGER PRIMARY KEY, nom TEXT  ,classement TEXT )";
        db.execSQL(CREATE_specialies_TABLE);


        String CREATE_restaurants_specialities_TABLE = "CREATE TABLE restaurants_specialities ( " + "id INTEGER   PRIMARY KEY, restaurnt_id INTEGER  , speciality_id INTEGER )";
        db.execSQL(CREATE_restaurants_specialities_TABLE);


        String CREATE_categorieplats_TABLE = "CREATE TABLE categorieplats ( " + "id INTEGER PRIMARY KEY, nom TEXT  ,classement TEXT  )";
        db.execSQL(CREATE_categorieplats_TABLE);
        String CREATE_vilecodepostals = "CREATE TABLE vilecodepostals ( " + "id INTEGER PRIMARY KEY,  codepostal  INTEGER  ,ville_id INTEGER  )";
        db.execSQL(CREATE_vilecodepostals);



        String CREATE_supplimenttypes_TABLE = "CREATE TABLE supplimenttypes ( " + "id INTEGER PRIMARY KEY, libelle TEXT  ,classement TEXT , " + " choicenumbre TEXT ,  plat_id TEXT )";
        db.execSQL(CREATE_supplimenttypes_TABLE);

        String CREATE_supplimentchoices_TABLE = "CREATE TABLE supplimentchoices ( " + "id INTEGER PRIMARY KEY, libelle TEXT  ,classement TEXT , " + " prix FLOAT ,  prix_avan_promotion FLOAT , promotion FLOAT , supplimenttype_id INTEGER  )";
        db.execSQL(CREATE_supplimentchoices_TABLE);

        String CREATE_categorties_restaurants_TABLE = "CREATE TABLE categorties_restaurants ( " + "id INTEGER   PRIMARY KEY, categorieplats_id INTEGER  , restaurant_id INTEGER )";
        db.execSQL(CREATE_categorties_restaurants_TABLE);


        String CREATE_panierplats_TABLE = "CREATE TABLE panierplats ( " + "id INTEGER    PRIMARY KEY AUTOINCREMENT, plat_id INTEGER  , quantity INTEGER , remarque TEXT  )";
        db.execSQL(CREATE_panierplats_TABLE);

        String CREATE_paniersupplements_TABLE = "CREATE TABLE paniersupplements ( " + "id INTEGER    PRIMARY KEY AUTOINCREMENT, panierplat_id INTEGER  , supplimentchoice_id INTEGER, quantite INTEGER )";
        db.execSQL(CREATE_paniersupplements_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop restaurants table if already exists
        //System.out.println("i m here") ;

        db.execSQL("DROP TABLE IF EXISTS restaurants");
        db.execSQL("DROP TABLE IF EXISTS plats");
        db.execSQL("DROP TABLE IF EXISTS villes");
        db.execSQL("DROP TABLE IF EXISTS specialies");
        db.execSQL("DROP TABLE IF EXISTS restaurants_specialities");
        db.execSQL("DROP TABLE IF EXISTS categorieplats");
        db.execSQL("DROP TABLE IF EXISTS vilecodepostals");
        db.execSQL("DROP TABLE IF EXISTS supplimenttypes");
        db.execSQL("DROP TABLE IF EXISTS supplimentchoices");
        db.execSQL("DROP TABLE IF EXISTS categorties_restaurants");
        db.execSQL("DROP TABLE IF EXISTS panierplats");
        db.execSQL("DROP TABLE IF EXISTS paniersupplements");
        this.onCreate(db);
    }

    //plats
    public void createplat(JSONArray plats) {
        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        try {

            for (int i = 0; i < plats.length(); i++) {
                JSONObject plat = plats.getJSONObject(i);
                ContentValues values = new ContentValues();
                values.put("id", plat.getInt("id"));
                values.put("nom", plat.getString("nom"));
                values.put("prix", plat.getDouble("prix"));
                values.put("Description", plat.getString("Description"));
                values.put("prix_avan_promotion", plat.getDouble("prix_avan_promotion"));
                values.put("promotion", plat.getDouble("promotion"));
                values.put("photo", plat.getString("photo"));
                values.put("classement", plat.getString("classement"));
                values.put("restaurant_id", plat.getInt("restaurant_id"));
                values.put("categorie_plat_id", plat.getInt("categorie_plat_id"));
                values.put("tva", plat.getDouble("tva"));

//System.out.println(values.toString()) ;
                db.insert(table_platS, null, values);
            }
        } catch (final JSONException e) {


        }
        // close database transaction
        db.close();
    }


    public List<plats> getAllplats() {
        List<plats> plats = new LinkedList<plats>();

        // select plat query
        String query = "SELECT  * FROM " + table_platS;

        // get reference of the platDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        plats plat = null;
        if (cursor.moveToFirst()) {
            do {
                plat = new plats();
                plat.setId(Integer.parseInt(cursor.getString(0)));
                plat.setNom(cursor.getString(1));
                plat.setPrix(Float.parseFloat(cursor.getString(2)));
                plat.setDescription(cursor.getString(3));
                plat.setPrix_avan_promotion(Float.parseFloat(cursor.getString(4)));
                plat.setPromotion(Float.parseFloat(cursor.getString(5)));
                plat.setPhoto(cursor.getString(6));
                plat.setClassement(cursor.getString(7));
                plat.setRestaurant_id(Integer.parseInt(cursor.getString(8)));
                plat.setCategorie_plat_id(Integer.parseInt(cursor.getString(9)));
                plat.setTva(Float.parseFloat(cursor.getString(10)));
                // Add plat to plats
                plats.add(plat);
            } while (cursor.moveToNext());
        }
        return plats;
    }
    public List<plats> getplatsbyCategorieplat(int Categorie_plat_id) {
        List<plats> plats = new LinkedList<plats>();

        // select plat query
        String query = "SELECT  * FROM " + table_platS+ " where Categorie_plat_id like "
                + Categorie_plat_id;

        // get reference of the platDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        plats plat = null;
        if (cursor.moveToFirst()) {
            do {
                plat = new plats();
                plat.setId(Integer.parseInt(cursor.getString(0)));
                plat.setNom(cursor.getString(1));
                plat.setPrix(Float.parseFloat(cursor.getString(2)));
                plat.setDescription(cursor.getString(3));
                plat.setPrix_avan_promotion(Float.parseFloat(cursor.getString(4)));
                plat.setPromotion(Float.parseFloat(cursor.getString(5)));
                plat.setPhoto(cursor.getString(6));
                plat.setClassement(cursor.getString(7));
                plat.setRestaurant_id(Integer.parseInt(cursor.getString(8)));
                plat.setCategorie_plat_id(Integer.parseInt(cursor.getString(9)));
                plat.setTva(Float.parseFloat(cursor.getString(10)));
                // Add plat to plats
                plats.add(plat);
            } while (cursor.moveToNext());
        }
        return plats;
    }
    //end ville
    //restaurant
    public void createrestaurant(restaurant restaurant) {
        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put("id", restaurant.getId());
        values.put("nom", restaurant.getNom());
        values.put("adresse", restaurant.getAdresse());
        values.put("tel", restaurant.getTel());
        values.put("presentation", restaurant.getPresentation());
        values.put("mobilephoto", restaurant.getMobilephoto());
        values.put("mobileicone", restaurant.getMobileicone());
        values.put("livraison", restaurant.getLivraison());
        values.put("takeaway", restaurant.getTakeaway());
        values.put("commandetable", restaurant.getCommandetable());
        values.put("classement", restaurant.getClassement());
        values.put("ville_id", restaurant.getVille_id());

        // insert restaurant
        db.insert(table_restaurantS, null, values);

        // close database transaction
        db.close();
    }


    public List<restaurant> getrestaurantBy(String name , int ville_id) {

        List<restaurant> restaurants = new LinkedList<restaurant>();
        name = "%" + name + "%";

        // select restaurant query
        String query = "SELECT  * FROM " + table_restaurantS + " where nom like   '"
                + name
                + "'  and  ville_id =  " + ville_id  ;

        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        restaurant restaurant = null;
        if (cursor.moveToFirst()) {
            do {
                restaurant = new restaurant();
                restaurant.setId(Integer.parseInt(cursor.getString(0)));
                restaurant.setNom(cursor.getString(1));
                restaurant.setAdresse(cursor.getString(2));
                restaurant.setTel(cursor.getString(3));
                restaurant.setPresentation(cursor.getString(4));
                restaurant.setMobilephoto(cursor.getString(5));
                restaurant.setMobileicone(cursor.getString(6));
                restaurant.setLivraison(cursor.getString(7));
                restaurant.setTakeaway(cursor.getString(8));
                restaurant.setCommandetable(cursor.getString(9));
                restaurant.setVille_id(Integer.parseInt(cursor.getString(11)));
                // Add restaurant to restaurants
                restaurants.add(restaurant);
            } while (cursor.moveToNext());
        }
        return restaurants;

    }
    public restaurant readrestaurant(int id) {
        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getReadableDatabase();

        // get restaurant query
        Cursor cursor = db.query(table_restaurantS, // a. table
                COLUMNS, " id = ?", new String[] { String.valueOf(id) }, null, null, null, null);

        // if results !=null, parse the first one
        if (cursor != null)
            cursor.moveToFirst();
        //   System.out.println(Integer.parseInt(cursor.getString(0)));

        restaurant restaurant = new restaurant();
        restaurant.setId(Integer.parseInt(cursor.getString(0)));
        restaurant.setNom(cursor.getString(1));
        restaurant.setAdresse(cursor.getString(2));
        restaurant.setTel(cursor.getString(3));
        restaurant.setPresentation(cursor.getString(4));
        restaurant.setMobilephoto(cursor.getString(5));
        restaurant.setMobileicone(cursor.getString(6));
        restaurant.setLivraison(cursor.getString(7));
        restaurant.setTakeaway(cursor.getString(8));
        restaurant.setCommandetable(cursor.getString(9));
//        restaurant.setVille_id(Integer.parseInt(cursor.getString(11)));

        //  System.out.println(restaurant.toString());
        return restaurant;
    }

    public List<restaurant> getAllrestaurants() {
        List<restaurant> restaurants = new LinkedList<restaurant>();

        // select restaurant query
        String query = "SELECT  * FROM " + table_restaurantS;

        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        restaurant restaurant = null;
        if (cursor.moveToFirst()) {
            do {
                restaurant = new restaurant();
                restaurant.setId(Integer.parseInt(cursor.getString(0)));
                restaurant.setNom(cursor.getString(1));
                restaurant.setAdresse(cursor.getString(2));
                restaurant.setTel(cursor.getString(3));
                restaurant.setPresentation(cursor.getString(4));
                restaurant.setMobilephoto(cursor.getString(5));
                restaurant.setMobileicone(cursor.getString(6));
                restaurant.setLivraison(cursor.getString(7));
                restaurant.setTakeaway(cursor.getString(8));
                restaurant.setCommandetable(cursor.getString(9));
                // Add restaurant to restaurants
                restaurants.add(restaurant);
            } while (cursor.moveToNext());
        }
        return restaurants;
    }



    // Deleting single restaurant
    public void deleterestaurant(restaurant restaurant) {

        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // delete restaurant
        db.delete(table_restaurantS, restaurant_ID + " = ?", new String[] { String.valueOf(restaurant.getId()) });
        db.close();
    }


    //end restaurant

    //ville

    public void createvilles(JSONArray villes) {
        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        try {

            for (int i = 0; i < villes.length(); i++) {
                JSONObject ville = villes.getJSONObject(i);
                ContentValues values = new ContentValues();
                values.put("id", ville.getInt("id"));
                values.put("nom", ville.getString("nom"));
                values.put("classement", ville.getString("classement"));


//System.out.println(values);
                // insert restaurant
                db.insert(table_villes, null, values);
            }
        } catch (final JSONException e) {


        }
        // close database transaction
        db.close();
    }



    public List<villes> getAllvilles() {
        List<villes> villes = new LinkedList<villes>();

        // select restaurant query
        String query = "SELECT  * FROM " + table_villes;

        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        villes ville = null;
        if (cursor.moveToFirst()) {
            do {
                ville = new villes();
                ville.setId(Integer.parseInt(cursor.getString(0)));
                ville.setNom(cursor.getString(1));
                ville.setClassement(cursor.getString(2));

                // Add restaurant to restaurants
                villes.add(ville);
            } while (cursor.moveToNext());
        }
        return villes;
    }

    //endville
    //specialies

    public void createspecialies(JSONArray specialies) {
        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        try {

            for (int i = 0; i < specialies.length(); i++) {
                JSONObject specialty = specialies.getJSONObject(i);
                ContentValues values = new ContentValues();
                values.put("id", specialty.getInt("id"));
                values.put("nom", specialty.getString("nom"));
                values.put("classement", specialty.getString("classement"));


                //   System.out.println(values);
                // insert restaurant
                db.insert(table_specialies, null, values);
            }
        } catch (final JSONException e) {


        }
        // close database transaction
        db.close();
    }


    //spécialités
    public List<specialies> getallspecialies() {
        List<specialies> specialies = new LinkedList<specialies>();

        // select restaurant query
        String query = "SELECT  * FROM " + table_specialies;

        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        specialies specialty = null;
        if (cursor.moveToFirst()) {
            do {
                specialty = new specialies();
                specialty.setId(Integer.parseInt(cursor.getString(0)));
                specialty.setNom(cursor.getString(1));
                specialty.setClassement(cursor.getString(2));

                // Add restaurant to restaurants
                specialies.add(specialty);
            } while (cursor.moveToNext());
        }
        return specialies;
    }

    //specialies


    //categorieplats

    public void createcategorieplats(JSONArray categorieplats) {
        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        try {

            for (int i = 0; i < categorieplats.length(); i++) {
                JSONObject categorieplat = categorieplats.getJSONObject(i);
                ContentValues values = new ContentValues();
                values.put("id", categorieplat.getInt("id"));
                values.put("nom", categorieplat.getString("nom"));
                values.put("classement", categorieplat.getString("classement"));


                // System.out.println(values);
                // insert restaurant
                db.insert(table_categorieplats, null, values);
            }
        } catch (final JSONException e) {


        }
        // close database transaction
        db.close();
    }

    public categorieplats readcategorieplats(int id) {
        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getReadableDatabase();

        // get restaurant query
        Cursor cursor = db.query(table_categorieplats, // a. table
                rowcategories, " id = ?", new String[] { String.valueOf(id) }, null, null, null, null);

        // if results !=null, parse the first one
        if (cursor != null)
            cursor.moveToFirst();
        // System.out.println(cursor.toString());

        categorieplats categorieplats = new categorieplats();
        categorieplats.setId(Integer.parseInt(cursor.getString(0)));
        categorieplats.setNom(cursor.getString(1));
        categorieplats.setClassement(cursor.getString(0));

        return categorieplats;
    }
    public List<categorieplats> getallcategorieplats() {
        List<categorieplats> categorieplats = new LinkedList<categorieplats>();

        // select restaurant query
        String query = "SELECT  * FROM " + table_categorieplats;

        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        categorieplats categorieplat = null;
        if (cursor.moveToFirst()) {
            do {
                categorieplat = new categorieplats();
                categorieplat.setId(Integer.parseInt(cursor.getString(0)));
                categorieplat.setNom(cursor.getString(1));
                categorieplat.setClassement(cursor.getString(2));

                // Add restaurant to restaurants
                categorieplats.add(categorieplat);
            } while (cursor.moveToNext());
        }
        return categorieplats;
    }

    //categorieplats

//supplimenttypes


    public void createsupplimenttypes(JSONArray supplimenttypes) {
        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        try {

            for (int i = 0; i < supplimenttypes.length(); i++) {
                JSONObject supplimenttype = supplimenttypes.getJSONObject(i);
                //   System.out.println(supplimenttype.getInt("id")) ;

                ContentValues values = new ContentValues();
                values.put("id", supplimenttype.getInt("id"));
                values.put("libelle", supplimenttype.getString("libelle"));
                values.put("classement", supplimenttype.getString("classement"));
                values.put("choicenumbre", supplimenttype.getString("choicenumbre"));
                values.put("plat_id", supplimenttype.getInt("plat_id"));


                //  System.out.println(values);
                // insert restaurant
                db.insert(table_supplimenttypes, null, values);
            }
        } catch (final JSONException e) {


        }
        // close database transaction
        db.close();
    }

    public List<supplimenttypes> getallsupplimenttypes() {
        List<supplimenttypes> supplimenttypes = new LinkedList<supplimenttypes>();

        // select restaurant query
        String query = "SELECT  * FROM " + table_supplimenttypes;

        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        supplimenttypes supplimenttype = null;
        if (cursor.moveToFirst()) {
            do {
                supplimenttype = new supplimenttypes();
                supplimenttype.setId(Integer.parseInt(cursor.getString(0)));
                supplimenttype.setLibelle(cursor.getString(1));
                supplimenttype.setClassement(cursor.getString(2));
                supplimenttype.setChoicenumbre(cursor.getInt(3));
                supplimenttype.setPlat_id(cursor.getInt(4));
                // Add restaurant to restaurants
                supplimenttypes.add(supplimenttype);
            } while (cursor.moveToNext());
        }
        return supplimenttypes;
    }
    public List<supplimenttypes> getsupplimenttypesbyplats(int plat_id) {
        List<supplimenttypes> supplimenttypes = new LinkedList<supplimenttypes>();

        // select restaurant query
        String query = "SELECT  * FROM " + table_supplimenttypes + " where plat_id = "
                + plat_id;;

        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        supplimenttypes supplimenttype = null;
        if (cursor.moveToFirst()) {
            do {
                supplimenttype = new supplimenttypes();
                supplimenttype.setId(Integer.parseInt(cursor.getString(0)));
                supplimenttype.setLibelle(cursor.getString(1));
                supplimenttype.setClassement(cursor.getString(2));
                supplimenttype.setChoicenumbre(cursor.getInt(3));
                supplimenttype.setPlat_id(cursor.getInt(4));
                // Add restaurant to restaurants
                supplimenttypes.add(supplimenttype);
            } while (cursor.moveToNext());
        }
        return supplimenttypes;
    }

    // end supplimenttypes

//supplimentchoices


    public void createsupplimentchoices(JSONArray supplimentchoices) {
        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        try {

            for (int i = 0; i < supplimentchoices.length(); i++) {
                JSONObject supplimentchoice = supplimentchoices.getJSONObject(i);
                //   System.out.println(supplimenttype.getInt("id")) ;

                ContentValues values = new ContentValues();
                values.put("id", supplimentchoice.getInt("id"));
                values.put("libelle", supplimentchoice.getString("libelle"));
                values.put("classement", supplimentchoice.getString("classement"));
                values.put("prix", supplimentchoice.getString("prix"));
                values.put("prix_avan_promotion", supplimentchoice.getDouble("prix_avan_promotion"));
                values.put("promotion", supplimentchoice.getDouble("promotion"));
                values.put("supplimenttype_id", supplimentchoice.getDouble("supplimenttype_id"));


                //  System.out.println(values);
                // insert restaurant
                db.insert(table_supplimentchoices, null, values);
            }
        } catch (final JSONException e) {


        }
        // close database transaction
        db.close();
    }

    public List<supplimentchoices> getallsupplimentchoices() {
        List<supplimentchoices> supplimentchoices = new LinkedList<supplimentchoices>();

        // select restaurant query
        String query = "SELECT  * FROM " + table_supplimentchoices;

        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        supplimentchoices supplimentchoice = null;
        if (cursor.moveToFirst()) {
            do {
                supplimentchoice = new supplimentchoices();
                supplimentchoice.setId(Integer.parseInt(cursor.getString(0)));
                supplimentchoice.setLibelle(cursor.getString(1));
                supplimentchoice.setClassement(cursor.getString(2));
                supplimentchoice.setPrix(cursor.getFloat(3));

                supplimentchoice.setPrix_avan_promotion(cursor.getFloat(4));
                supplimentchoice.setPromotion(cursor.getFloat(5));
                supplimentchoice.setSupplimenttype_id(cursor.getInt(6));
                // Add restaurant to restaurants
                supplimentchoices.add(supplimentchoice);
            } while (cursor.moveToNext());
        }
        return supplimentchoices;
    }

    public List<supplimentchoices> getchoicebytype(int supplimenttype_id ) {
        List<supplimentchoices> supplimentchoices = new LinkedList<supplimentchoices>();

        // select restaurant query
        String query = "SELECT  * FROM " + table_supplimentchoices + " where supplimenttype_id = "
                + supplimenttype_id;;;

        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        supplimentchoices supplimentchoice = null;
        if (cursor.moveToFirst()) {
            do {
                supplimentchoice = new supplimentchoices();
                supplimentchoice.setId(Integer.parseInt(cursor.getString(0)));
                supplimentchoice.setLibelle(cursor.getString(1));
                supplimentchoice.setClassement(cursor.getString(2));
                supplimentchoice.setPrix(cursor.getFloat(3));

                supplimentchoice.setPrix_avan_promotion(cursor.getFloat(4));
                supplimentchoice.setPromotion(cursor.getFloat(5));
                supplimentchoice.setSupplimenttype_id(cursor.getInt(6));
                // Add restaurant to restaurants
                supplimentchoices.add(supplimentchoice);
            } while (cursor.moveToNext());
        }
        return supplimentchoices;
    }

    // end supplimentchoices
    // restaurantspecialities


    public void createrestaurantspecialities(JSONArray restaurantspecialities) {
        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        try {

            for (int i = 0; i < restaurantspecialities.length(); i++) {
                JSONObject restaurantspecialitie = restaurantspecialities.getJSONObject(i);
//console.log(restaurantspecialitie)
                ContentValues values = new ContentValues();
                values.put("id", i);
                values.put("restaurnt_id", restaurantspecialitie.getInt("restaurnt_id"));
                values.put("speciality_id", restaurantspecialitie.getInt("speciality_id"));


                //  System.out.println(values.toString());
                // insert restaurant
                db.insert(table_restaurantspecialities, null, values);
            }
        } catch (final JSONException e) {


        }
        // close database transaction
        db.close();
    }
    public List<restaurantspecialities> getallrestaurantspecialities() {
        List<restaurantspecialities> restaurantspecialities = new LinkedList<restaurantspecialities>();

        // select restaurant query
        String query = "SELECT  * FROM " + table_restaurantspecialities;

        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        restaurantspecialities restaurantspecialitie = null;
        if (cursor.moveToFirst()) {
            do {
                //  System.out.println(cursor.getString(2)) ;

                restaurantspecialitie = new restaurantspecialities();
                restaurantspecialitie.setId(Integer.parseInt(cursor.getString(0)));;
                restaurantspecialitie.setRestaurnt_id(Integer.parseInt(cursor.getString(1)));
                restaurantspecialitie.setSpeciality_id(Integer.parseInt(cursor.getString(2)));
                restaurantspecialities.add(restaurantspecialitie);
            } while (cursor.moveToNext());
        }
        // System.out.println(restaurantspecialities.toString()) ;

        return restaurantspecialities;
    }


    // end supplimentchoices

    // categortiesrestaurants


    public void createcategortiesrestaurants(JSONArray categortiesrestaurants) {
        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        try {

            for (int i = 0; i < categortiesrestaurants.length(); i++) {
                JSONObject categortiesrestaurant = categortiesrestaurants.getJSONObject(i);
//console.log(restaurantspecialitie)
                //  System.out.println(categortiesrestaurant.toString());

                ContentValues values = new ContentValues();
                values.put("id", i);
                values.put("categorieplats_id", categortiesrestaurant.getInt("categorieplats_id"));
                values.put("restaurant_id", categortiesrestaurant.getInt("restaurant_id"));


                //   System.out.println(values.toString());
                // insert restaurant
                db.insert(table_categortiesrestaurants, null, values);
            }
        } catch (final JSONException e) {


        }
        // close database transaction
        db.close();
    }


    public List<vilecodepostal> getcodepostalbyville(int ville_id ) {
        List<vilecodepostal> vilecodepostals = new LinkedList<vilecodepostal>();

        // select restaurant query
        String query = "SELECT  * FROM " + table_vilecodepostals + " where ville_id = "
                + ville_id;;;

        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        vilecodepostal vilecodepostal = null;
        if (cursor.moveToFirst()) {
            do {
                vilecodepostal = new vilecodepostal();
                vilecodepostal.setId(Integer.parseInt(cursor.getString(0)));;
                vilecodepostal.setCodepostal(Integer.parseInt(cursor.getString(1)));
                vilecodepostal.setVille_id(Integer.parseInt(cursor.getString(2)));
                vilecodepostals.add(vilecodepostal);
            } while (cursor.moveToNext());
        }
        return vilecodepostals;
    }


    public List<vilecodepostal> getallvilecodepostals() {
        List<vilecodepostal> vilecodepostals = new LinkedList<vilecodepostal>();

        // select restaurant query
        String query = "SELECT  * FROM " + table_vilecodepostals;

        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        vilecodepostal vilecodepostal = null;
        if (cursor.moveToFirst()) {
            do {
                //  System.out.println(cursor.getString(2)) ;

                vilecodepostal = new vilecodepostal();
                vilecodepostal.setId(Integer.parseInt(cursor.getString(0)));;
                vilecodepostal.setCodepostal(Integer.parseInt(cursor.getString(1)));
                vilecodepostal.setVille_id(Integer.parseInt(cursor.getString(2)));
                vilecodepostals.add(vilecodepostal);
            } while (cursor.moveToNext());
        }
        // System.out.println(restaurantspecialities.toString()) ;

        return vilecodepostals;
    }
    public void createvilecodepostals(JSONArray vilecodepostals) {
        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        try {

            for (int i = 0; i < vilecodepostals.length(); i++) {
                JSONObject vilecodepostal = vilecodepostals.getJSONObject(i);
//console.log(restaurantspecialitie)
                //  System.out.println(categortiesrestaurant.toString());

                ContentValues values = new ContentValues();
                values.put("id", i);
                values.put("codepostal", vilecodepostal.getInt("codepostal"));
                values.put("ville_id", vilecodepostal.getInt("ville_id"));


                //   System.out.println(values.toString());
                // insert restaurant
                db.insert(table_vilecodepostals, null, values);
            }
        } catch (final JSONException e) {


        }
        // close database transaction
        db.close();
    }

    public List<categortiesrestaurants> getallcategortiesrestaurants() {
        List<categortiesrestaurants> categortiesrestaurants = new LinkedList<categortiesrestaurants>();

        // select restaurant query
        String query = "SELECT  * FROM " + table_categortiesrestaurants;

        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        categortiesrestaurants categortiesrestaurant = null;
        if (cursor.moveToFirst()) {
            do {
                //  System.out.println(cursor.getString(2)) ;

                categortiesrestaurant = new categortiesrestaurants();
                categortiesrestaurant.setId(Integer.parseInt(cursor.getString(0)));;
                categortiesrestaurant.setCategorieplats_id(Integer.parseInt(cursor.getString(1)));
                categortiesrestaurant.setRestaurant_id(Integer.parseInt(cursor.getString(2)));
                categortiesrestaurants.add(categortiesrestaurant);
            } while (cursor.moveToNext());
        }
        // System.out.println(restaurantspecialities.toString()) ;

        return categortiesrestaurants;
    }
    public ArrayList<Integer> getcategorybyrestaurant( int idresto) {
        ArrayList<Integer> categorties = new ArrayList<Integer>();

        // select restaurant query
        String query = "SELECT  * FROM " + table_categortiesrestaurants + " where restaurant_id = "
                + idresto;

        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {

            do {


                categorties.add(Integer.parseInt(cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        // System.out.println(restaurantspecialities.toString()) ;

        return categorties ;    }

    // end supplimentchoices

//panier plats


    public int createpanierplats(panierplats panierplat) {
        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();



        ContentValues values = new ContentValues();
        // values.put("id", 2);
        values.put("plat_id", panierplat.getPlat_id());
        values.put("quantity", panierplat.getQuantity());
        values.put("remarque", panierplat.getRemarque());

        db.insert(table_panierplats, null, values);


        String query = "SELECT  id FROM " + table_panierplats + " WHERE id = (SELECT MAX(id)  FROM  "+ table_panierplats +" ) ";

        // get reference of the restaurantDB database
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null)
            cursor.moveToFirst();
        db.close();
        return cursor.getInt(0) ;
        // close database transaction

    }

    public List<panierplats> getallpanierplats() {
        List<panierplats> panierplats = new LinkedList<panierplats>();

        // select restaurant query
        String query = "SELECT  * FROM " + table_panierplats;

        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        panierplats panierplat = null;
        if (cursor.moveToFirst()) {
            do {
                //  System.out.println(cursor.getString(2)) ;

                panierplat = new panierplats();
                panierplat.setId(Integer.parseInt(cursor.getString(0)));;
                panierplat.setPlat_id(Integer.parseInt(cursor.getString(1)));
                panierplat.setQuantity(Integer.parseInt(cursor.getString(2)));
                panierplat.setRemarque(cursor.getString(2));
                panierplats.add(panierplat);
            } while (cursor.moveToNext());
        }
        // System.out.println(restaurantspecialities.toString()) ;

        return panierplats;
    }

    public void createpaniersupplements(paniersupplements paniersupplement) {
        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();



        ContentValues values = new ContentValues();
        // values.put("id", 2);
        values.put("panierplat_id", paniersupplement.getPanierplat_id());
        values.put("quantite", paniersupplement.getQuantite());
        values.put("supplimentchoice_id", paniersupplement.getSupplimentchoice_id());

        db.insert(table_paniersupplements, null, values);

        // close database transaction
        db.close();
    }

    public List<paniersupplements> getallpaniersupplements() {
        List<paniersupplements> paniersupplements = new LinkedList<paniersupplements>();

        // select restaurant query
        String query = "SELECT  * FROM " + table_paniersupplements;

        // get reference of the restaurantDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        paniersupplements paniersupplement = null;
        if (cursor.moveToFirst()) {
            do {
                //  System.out.println(cursor.getString(2)) ;

                paniersupplement = new paniersupplements();
                paniersupplement.setId(Integer.parseInt(cursor.getString(0)));;
                paniersupplement.setPanierplat_id(Integer.parseInt(cursor.getString(1)));
                paniersupplement.setSupplimentchoice_id(Integer.parseInt(cursor.getString(2)));
                paniersupplement.setQuantite(Integer.parseInt(cursor.getString(2)));
                paniersupplements.add(paniersupplement);
            } while (cursor.moveToNext());
        }
        // System.out.println(restaurantspecialities.toString()) ;

        return paniersupplements;
    }
}
