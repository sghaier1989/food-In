package com.example.sghaier.foodin;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class tabshost extends Fragment {
    Typeface tf1,tf2,tf3;
    ListView listperso;

    public cartadapter adapter2;
    AlertDialog.Builder adb;
    View alertDialogView;

    private static String checkversioning = "https://dev.food-in.fr/api/checkversioning";
    private static String validatedupdate = "https://dev.food-in.fr/api/validatedupdate";
    private ProgressDialog pDialog;

    ArrayList<RestauItem> listresto = null;
    List<plats> listplats = null;
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

    TabHost host;
    int id;

    JCGSQLiteHelper db = new JCGSQLiteHelper(getActivity());


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        tf1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GothamRounded-Bold.otf");
        tf2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GothamRounded-Medium.otf");
        tf3 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GothamRounded-Light.otf");

        View rootView = inflater.inflate(R.layout.activity_tabshost, container, false);
        setHasOptionsMenu(true);
        listperso = (ListView)rootView.findViewById(R.id.listView);

        id = this.getArguments().getInt("id");
        System.out.println("id= "+id);

        host = (TabHost)rootView.findViewById(R.id.tabHost);
        host.setup();

        LayoutInflater factory = LayoutInflater.from(getActivity());
        alertDialogView = factory.inflate(R.layout.alertperso, null);
        //Création de l'AlertDialog
        adb = new AlertDialog.Builder(getActivity());
        //On affecte la vue personnalisé que l'on a crée à notre AlertDialog
        adb.setView(alertDialogView);
        //TextView tv_total = (TextView)alertDialogView.findViewById(R.id.alerthistorique_total);
        TextView tv_title = (TextView)alertDialogView.findViewById(R.id.allertperso_tv_title);
        CheckBox ck_sans = (CheckBox)alertDialogView.findViewById(R.id.tv_sasna);
        CheckBox ck_soda = (CheckBox)alertDialogView.findViewById(R.id.tv_sasna_chawal);
        CheckBox ck_pal = (CheckBox)alertDialogView.findViewById(R.id.tv_chawal_mattar_pulao);
        CheckBox ck_kha = (CheckBox)alertDialogView.findViewById(R.id.tv_khamir);

        TextView tv_ajouter = (TextView)alertDialogView.findViewById(R.id.tv_ajouter);
        TextView tv_annuler = (TextView)alertDialogView.findViewById(R.id.tv_annuler);

        ck_sans.setTypeface(tf3);ck_soda.setTypeface(tf3);
        ck_pal.setTypeface(tf3);ck_kha.setTypeface(tf3);

        tv_ajouter.setTypeface(tf2);
        tv_annuler.setTypeface(tf2);
        tv_title.setTypeface(tf1);
        //tv_total.setTypeface(tf1);

        /*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adb.show();
            }
        });*/

        tv_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adb.setCancelable(false);
            }
        });


        new CheckUpdate().execute();

        //addpanier
        JCGSQLiteHelper db = new JCGSQLiteHelper(getActivity());

        // tu ajoute içi les parrametres des plats  ( le id de plats , la quantités , la remarque )
        panierplats   panierplats  = new panierplats(1 , 1 , "") ;


        // ajoute un plat dans le panier
        int panierplat_id = db.createpanierplats(panierplats);

        List<paniersupplements> allpaniersupplements = new LinkedList<paniersupplements>() ;

        paniersupplements   paniersupplement   ;

        // tu ajoute içi les supplements  choices dans le paniers 1 par 1

        // panier plats _id , supplements choices id , quantite de supplement
        paniersupplement = new paniersupplements(panierplat_id , 5 , 1) ;
        allpaniersupplements.add(paniersupplement) ;
        paniersupplement = new paniersupplements(panierplat_id , 6 , 1) ;
        allpaniersupplements.add(paniersupplement) ;
        paniersupplement = new paniersupplements(panierplat_id , 7 , 1) ;
        allpaniersupplements.add(paniersupplement) ;

        // ajoute dans le sqlite

        for(int i  =0; i < allpaniersupplements.size() ; i ++ ){

            db.createpaniersupplements(allpaniersupplements.get(i));
        }

        List<panierplats> paniersplats  ;
        List<paniersupplements> panierssupplements  ;
        //panierplats = new panierplats() ;
        paniersplats = db.getallpanierplats() ;
        panierssupplements = db.getallpaniersupplements() ;
        System.out.println(paniersplats.toString()) ;
        System.out.println(panierssupplements.toString()) ;

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu ,MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_tab, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_tab_panier:
                Pannier fragment = new Pannier();
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(" Votre Commande ");
                fragmentTransaction.addToBackStack( "tabshost" ).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
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

                    tag = new ArrayList();
                    tag.add("idmobile");
                    tag.add("version");
                    param = new ArrayList();
                    param.add(android_id);
                    param.add(version.toString());
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
            final JCGSQLiteHelper db = new JCGSQLiteHelper(getActivity());

            //int restaurant_id = 32 ;
            ArrayList<Integer>   categoriesid ;
            final List<categorieplats> categories = new LinkedList<categorieplats>();
            categorieplats   categorie   ;
            int thisid ;
            categoriesid =  db.getcategorybyrestaurant(id) ;

            for (int i = 0; i < categoriesid.size(); i++) {
                thisid = categoriesid.get(i) ;
                categorie= db.readcategorieplats( thisid);
                categories.add(categorie)  ;
            }

// liste des catégorie
            System.out.println("===============>   "+categories.toString()) ;

// liste des plats par catégories

            final List<List<plats> > plats = new LinkedList<List<plats>>();

            for (int i = 0; i < categories.size(); i++) {
                System.out.println(categories.get(i).toString()) ;
                TabHost.TabSpec spec = host.newTabSpec(" "+categories.get(i).getNom().toString());
                spec.setContent(R.id.linearLayout9);
                spec.setIndicator(categories.get(i).getNom());
                host.addTab(spec);
                TextView tv = (TextView) host.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
                tv.setTextColor(getResources().getColor(R.color.rose_clear));
                tv.setTypeface(tf2);
                List<plats> platslist = new LinkedList<plats>();
                platslist =  db.getplatsbyCategorieplat(categories.get(i).getId()) ;
                plats.add(platslist) ;
            }

            host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                @Override
                public void onTabChanged(String s) {
                    Log.d("test", "onTabChanged: tab number=" + categories.get(host.getCurrentTab()));
                    // charger new liste et afficher dans listview
                    List <plats> listplats = null;
                    listplats = plats.get(host.getCurrentTab());
                    adapter2 = new cartadapter(getActivity(),listplats,adb,db);
                    listperso.setAdapter(adapter2);
                }
            });

            //array array plats
            for (int i = 0; i < plats.size(); i++) {

                System.out.println(plats.get(i).toString());
            }
            int  plats_id = 192 ;

            List<supplimenttypes> supplimenttypes = new LinkedList<supplimenttypes>() ;
            supplimenttypes = db.getsupplimenttypesbyplats(plats_id) ;

            // liste des titres des supplements les phrases
            System.out.println(supplimenttypes.toString());

            List<List<supplimentchoices> > supplimentchoices = new LinkedList<List<supplimentchoices>>();

            for (int i = 0; i < supplimenttypes.size(); i++) {
                System.out.println(supplimenttypes.get(i).toString()) ;

                List<supplimentchoices> supplimentchoiceslist = new LinkedList<supplimentchoices>();
                supplimentchoiceslist =  db.getchoicebytype(supplimenttypes.get(i).getId()) ;
                supplimentchoices.add(supplimentchoiceslist) ;
            }
//array array chaque ligne de supplimentchoices = supplement type
            for (int i = 0; i < supplimentchoices.size(); i++) {
                System.out.println(supplimentchoices.get(i).toString());
            }


        }


        public List<restaurant> addrestaurants(JSONArray allrestaurants, JCGSQLiteHelper db) {
            try {

                for (int i = 0; i < allrestaurants.length(); i++) {
                    JSONObject c = allrestaurants.getJSONObject(i);
                    // Log.e(TAG, "Response from url: " + c.getString("id"));
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
                    if(mobileicone!=null)
                    {
                        downloadicon(mobileicone);
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

        private void downloadicon(String img) {
            try {
                String urlcode = "https://dev.food-in.fr/uploads/restaurants/images/" + img ;
                URL url = new URL(urlcode);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                String root = Environment.getExternalStorageDirectory().toString();
                File myDir = new File(root + "/icon" );
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
