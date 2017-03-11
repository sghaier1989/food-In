package com.example.sghaier.foodin;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.Settings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 05/03/2017.
 */
class CheckUpdate extends AsyncTask<Void, Void, Void> {
    ArrayList<RestauItem> listresto = null;
    List<restaurant> restaurantlist  ;
    List<villes> villes  ;
    List<plats> plats  ;
    List<specialies> specialies  ;
    List<categorieplats> categorieplats  ;
    List<supplimenttypes> supplimenttypes  ;
    List<supplimentchoices> supplimentchoices  ;
    List<restaurantspecialities> restaurantspecialities  ;
    List<categortiesrestaurants> categortiesrestaurants  ;
    List<vilecodepostal> vilecodepostals  ;
    private static String checkversioning = "https://dev.food-in.fr/api/checkversioning";
    private static String validatedupdate = "https://dev.food-in.fr/api/validatedupdate";
    String android_id ;
    Context context ;
    Thread thread ;
    public CheckUpdate(String android_id, Context context, Thread thread) {
        this.android_id = android_id;
        this.context = context;
        this.thread = thread;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected Void doInBackground(Void... arg0) {

        System.out.println(android_id) ;


        HttpHandler sh = new HttpHandler();

        ArrayList tag = new ArrayList() ;
        tag.add("idmobile") ;
        ArrayList param = new ArrayList() ;
        param.add(android_id) ;
        String jsonStr = sh.makeServiceCall(checkversioning , "POST" ,  tag ,  param);
        System.out.println(jsonStr) ;

        try {

            JSONObject jsonObj = new JSONObject(jsonStr);
            // System.out.println(jsonObj.names()) ;

            Object data = jsonObj.get("data");
            System.out.println(data.toString() ) ;
            if( data.toString()!= "1") {
                Object version = jsonObj.get("version");

                JSONObject currentdata = jsonObj.getJSONObject("data");

                JCGSQLiteHelper db = new JCGSQLiteHelper(context);

                SQLiteDatabase database = db.getWritableDatabase();
                db.onUpgrade(database, 1, 2);
                vilecodepostals = this.addvilecodepostal(currentdata.getJSONArray("vilecodepostal"), db);

             //   for (int i = 0; i < vilecodepostals.size(); i++) {

                //    System.out.println(vilecodepostals.get(i));
              //  }
                restaurantlist = this.addrestaurants(currentdata.getJSONArray("restaurants"), db);
                plats = this.addplats(currentdata.getJSONArray("plats"), db);
                villes = this.addvilles(currentdata.getJSONArray("villes"), db);
                ;
                specialies = this.addspecialies(currentdata.getJSONArray("specialies"), db);
                ;
                categorieplats = this.addcategorieplats(currentdata.getJSONArray("categorieplats"), db);
                ;
                supplimenttypes = this.addsupplimenttypes(currentdata.getJSONArray("supplimenttypes"), db);
                supplimentchoices = this.addsupplimentchoices(currentdata.getJSONArray("supplimentchoices"), db);
                restaurantspecialities = this.addrestaurantspecialities(currentdata.getJSONArray("restaurants_specialities"), db);

                categortiesrestaurants = this.addcategortiesrestaurants(currentdata.getJSONArray("categorties_restaurants"), db);



                tag = new ArrayList();
                tag.add("idmobile");
                tag.add("version");
                param = new ArrayList();
                param.add(android_id);
                param.add(version.toString());
               // param.add(12);
                jsonStr = sh.makeServiceCall(validatedupdate, "POST", tag, param);

                //   System.out.println( db.getAllrestaurants()) ;

                //
            }

        } catch (final JSONException e) {


        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        Intent openMainActivity =  new Intent(context,login.class);
        openMainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(openMainActivity);
    }


    public List<restaurant> addrestaurants(JSONArray allrestaurants, JCGSQLiteHelper db) {
        try {

            for (int i = 0; i < allrestaurants.length(); i++) {
                JSONObject c = allrestaurants.getJSONObject(i);
                // Log.e(TAG, "Response from url: " + c.getString("id"));
                //
                //
                //System.out.println( c.getString("id")) ;

                int id = c.getInt("id");
                String nom = c.getString("nom");
                String adresse = c.getString("adresse");
                String tel = c.getString("tel");
                String presentation = c.getString("presentation");
                String mobilephoto = c.getString("mobilephoto");
                downloadImage(mobilephoto) ;
                String mobileicone = c.getString("mobileicone");
                if(mobilephoto!=null)
                {
                    downloadImage(mobilephoto);
                }
                String livraison = c.getString("livraison");
                String takeaway = c.getString("takeaway");
                String commandetable = c.getString("commandetable");
                String classement = c.getString("classement");
                int ville_id = c.getInt("ville_id");

                db.createrestaurant(new restaurant(id, nom, adresse, tel, presentation, mobilephoto, mobileicone, livraison, takeaway, commandetable , classement ,ville_id));
            }

            restaurantlist = db.getAllrestaurants() ;

        } catch (final JSONException e) {


        }
        return restaurantlist ;
    }

    private void downloadImage(String img) {
        try {
            String urlcode = "https://dev.food-in.fr/uploads/restaurants/images/" + img ;
            URL url = new URL(urlcode);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/restaurants" );
            myDir.mkdirs();
            File file = new File (myDir, img);


            FileOutputStream out = new FileOutputStream(file);
            myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();




        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public List<plats> addplats(JSONArray allplats, JCGSQLiteHelper db) {
        db.createplat(allplats);
        plats =  db.getAllplats() ;
        return plats ;
    }
    public List<villes> addvilles(JSONArray allvilles, JCGSQLiteHelper db) {
        db.createvilles(allvilles);
        villes = db.getAllvilles() ;
        return villes ;
    }
    public List<specialies> addspecialies(JSONArray allspecialies, JCGSQLiteHelper db) {
        db.createspecialies(allspecialies);
        specialies = db.getallspecialies() ;
        return specialies ;
    }
    public List<categorieplats> addcategorieplats(JSONArray allcategorieplats, JCGSQLiteHelper db) {
        db.createcategorieplats(allcategorieplats);
        categorieplats = db.getallcategorieplats() ;
        return categorieplats ;
    }
    public List<supplimenttypes> addsupplimenttypes(JSONArray allsupplimenttypes, JCGSQLiteHelper db) {
        db.createsupplimenttypes(allsupplimenttypes);
        supplimenttypes = db.getallsupplimenttypes() ;
        return supplimenttypes ;
    }
    public List<supplimentchoices> addsupplimentchoices(JSONArray allsupplimentchoices, JCGSQLiteHelper db) {
        db.createsupplimentchoices(allsupplimentchoices);
        supplimentchoices = db.getallsupplimentchoices() ;
        return supplimentchoices ;
    }
    public List<restaurantspecialities> addrestaurantspecialities(JSONArray allrestaurantspecialities, JCGSQLiteHelper db) {
        db.createrestaurantspecialities(allrestaurantspecialities);
        restaurantspecialities = db.getallrestaurantspecialities() ;
        return restaurantspecialities ;
    }
    public List<categortiesrestaurants> addcategortiesrestaurants(JSONArray allcategortiesrestaurants, JCGSQLiteHelper db) {
        db.createcategortiesrestaurants(allcategortiesrestaurants);
        categortiesrestaurants = db.getallcategortiesrestaurants() ;
        return categortiesrestaurants ;
    }
    public List<vilecodepostal> addvilecodepostal(JSONArray allvilecodepostal, JCGSQLiteHelper db) {
        db.createvilecodepostals(allvilecodepostal);
        vilecodepostals = db.getallvilecodepostals() ;
        return vilecodepostals ;
    }
}