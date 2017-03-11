package com.example.sghaier.foodin;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

public class Resto_detaille extends android.support.v4.app.Fragment {
    ImageView resto_detail_imgview,resto_detaille_imgv_livraison;
    AlertDialog.Builder adb;
    public TextView alert_reservetv_annuler,resto_detail_adress,resto_detail_description;

    private static String checkversioning = "https://dev.food-in.fr/api/checkversioning";
    private static String validatedupdate = "https://dev.food-in.fr/api/validatedupdate";

    private ProgressDialog pDialog;


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

    int id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.activity_resto_detaille, container, false);

        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View alertDialogView = factory.inflate(R.layout.alertreservo, null);

        id = this.getArguments().getInt("id");
        System.out.println("id= "+id);

        //Cr�ation de l'AlertDialog
        adb = new AlertDialog.Builder(getActivity());

        //On affecte la vue personnalis� que l'on a cr�e � notre AlertDialog
        adb.setView(alertDialogView);
        new CheckUpdate().execute();

        resto_detail_imgview = (ImageView)rootView.findViewById(R.id.resto_detail_imgview);
        resto_detaille_imgv_livraison  = (ImageView)rootView.findViewById(R.id.resto_detaille_imgv_livraison);
        alert_reservetv_annuler= (TextView) alertDialogView.findViewById(R.id.alert_reservetv_annuler);


        resto_detail_adress = (TextView) rootView.findViewById(R.id.resto_detail_adress);
        resto_detail_description= (TextView) rootView.findViewById(R.id.resto_detail_description);


        alert_reservetv_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
            }
        });
        resto_detail_imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adb.show();
            }
        });

        return rootView;
    }
    private class CheckUpdate extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected Void doInBackground(Void... arg0) {

            String android_id = Settings.Secure.getString( getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
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

                    JCGSQLiteHelper db = new JCGSQLiteHelper(getActivity());

                    SQLiteDatabase database = db.getWritableDatabase();
                    db.onUpgrade(database, 1, 2);

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
                    vilecodepostals = this.addvilecodepostal(currentdata.getJSONArray("vilecodepostal"), db);

                    //for (int i = 0; i < categortiesrestaurants.size(); i++) {

                        //     System.out.println(categortiesrestaurants.get(i));
                   // }

                    tag = new ArrayList();
                    tag.add("idmobile");
                    tag.add("version");
                    param = new ArrayList();
                    param.add(android_id);
                    param.add(version.toString());
                    jsonStr = sh.makeServiceCall(validatedupdate, "POST", tag, param);
                }

            } catch (final JSONException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            JCGSQLiteHelper db = new JCGSQLiteHelper(getActivity());

            //int restaurant_id =26 ;
            final restaurant restaurant  ;

            restaurant = db.readrestaurant(id) ;

            //resto_detail_adress = (TextView) rootView.findViewById(R.id.resto_detail_adress);
            //resto_detail_description= (TextView) rootView.findViewById(R.id.resto_detail_description);

            System.out.println(restaurant.toString());
            String adresse = restaurant.getAdresse();
            resto_detail_adress.setText(adresse);
            resto_detail_description.setText(restaurant.getPresentation());

            resto_detaille_imgv_livraison.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent i = new Intent(Resto_detaille.this, tabshost.class);
                    //startActivity(i);

                    Bundle bundle = new Bundle();
                    bundle.putInt("id", id );
                    tabshost fragment = new tabshost();
                    fragment.setArguments(bundle);

                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                            getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(restaurant.getNom());
                    fragmentTransaction.addToBackStack( "Resto_detaille" ).commit();

                }
            });



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
                    if(mobilephoto!=null)
                    {
                        downloadImage(mobilephoto);
                    }
                    String mobileicone = c.getString("mobileicone");
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
    }
}
