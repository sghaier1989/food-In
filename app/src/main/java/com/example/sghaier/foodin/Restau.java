package com.example.sghaier.foodin;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Restau extends Fragment {
    private static final String TAG = HttpHandler.class.getSimpleName();

    public restoadaptateur adapter;
    public AdapterVille adapter_ville ;
    TextView restau_tv_modifier,restau_tv_restaurants_ouvert,tv_alert_ville;
    Typeface tf1,tf2,tf3;
    View myFragmentView;
    SearchView search;
    ListView list,alert_ville_liste;

    private static String checkversioning = "https://dev.food-in.fr/api/checkversioning";
    private static String validatedupdate = "https://dev.food-in.fr/api/validatedupdate";

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

    AlertDialog.Builder adb;
    AlertDialog dialog;

    List<restaurant> restaurants ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //alert ville .
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View alertDialogView = factory.inflate(R.layout.alertville, null);
        adb = new AlertDialog.Builder(getActivity());
        adb.setView(alertDialogView);
        adb.setTitle(" Liste des villes ");
        dialog = adb.create();

        alert_ville_liste = (ListView) alertDialogView.findViewById(R.id.alertville_listview);
        tf1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GothamRounded-Bold.otf");
        tf2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GothamRounded-Medium.otf");
        tf3 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GothamRounded-Light.otf");

        View rootView = inflater.inflate(R.layout.activity_restau, container, false);
        setHasOptionsMenu(true);
        listresto = RestauItem.getAListOfContact();
        //adapter = new restoadaptateur(getActivity(), listresto);
        list = (ListView)rootView.findViewById(R.id.listresto);
        restau_tv_modifier = (TextView)rootView.findViewById(R.id.restau_tv_modifier);
        restau_tv_restaurants_ouvert = (TextView)rootView.findViewById(R.id.restau_tv_restaurants_ouvert);
        restau_tv_restaurants_ouvert.setTypeface(tf1);
        restau_tv_modifier.setTypeface(tf3);

        new CheckUpdate().execute();
      //  list.setAdapter(adapter);

        String name = "" ;;
        int ville_id =6 ;
        JCGSQLiteHelper db = new JCGSQLiteHelper(getActivity());

        restaurants = db.getrestaurantBy(name, ville_id) ;
        for (int i = 0; i < restaurants.size(); i++) {
            System.out.println(restaurants.get(i));
        }

        adapter = new restoadaptateur(getActivity(),restaurants);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            System.out.println("id_restaurant = "+restaurants.get(position).getId());
            Bundle bundle = new Bundle();
            bundle.putInt("id", restaurants.get(position).getId() );
            Resto_detaille fragment = new Resto_detaille();
            fragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(""+restaurants.get(position).getNom());
            fragmentTransaction.addToBackStack( "Restau" ).commit();
            }
        });

        return rootView;
    }
    private class CheckUpdate extends AsyncTask<Void, Void, Void> {
        JCGSQLiteHelper db;
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

                   // for (int i = 0; i < categortiesrestaurants.size(); i++) {

                        //     System.out.println(categortiesrestaurants.get(i));
                  //  }

                    tag = new ArrayList();
                    tag.add("idmobile");
                    tag.add("version");
                    param = new ArrayList();
                    param.add(android_id);
                   // param.add(0);
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
            db = new JCGSQLiteHelper(getActivity());

            villes = db.getAllvilles() ;
            String[] codepostalelistes = new String[100];

            for(int i =0 ; i < villes.size() ; i++){
                String str = "";
                vilecodepostals = db.getcodepostalbyville(villes.get(i).getId())  ;
                for (int j = 0; j<vilecodepostals.size();j++)
                    str = str + vilecodepostals.get(j).getCodepostal() +" ";

                codepostalelistes[i] = str;

            }

            adapter_ville = new AdapterVille(getActivity(),villes,codepostalelistes);
            alert_ville_liste.setAdapter(adapter_ville);

            alert_ville_liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    System.out.println("Selected item  =  "+villes.get(i).getNom());
                    restaurants = db.getrestaurantBy("", villes.get(i).getId()) ;
                    adapter = new restoadaptateur(getActivity(),restaurants);
                    list.setAdapter(adapter);
                    // TODO: alert hide
                    dialog.dismiss();


                }
            });

            String path = Environment.getExternalStorageDirectory().toString()+ "/restaurants" ;
            Log.d("Files", "Path: " + path);
            File directory = new File(path);
            File[] files = directory.listFiles();
            Log.d("Files", "Size: "+ files.length);
            for (int i = 0; i < files.length; i++)
            {
                Log.d("Files", "FileName:" + files[i].getName());
            }

            restau_tv_modifier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.show();
                }
            });

            String name = "" ;;
            int ville_id =6 ;
           // List<restaurant> restaurants ;
            restaurants = db.getrestaurantBy(name, ville_id) ;
            for (int i = 0; i < restaurants.size(); i++) {

                System.out.println(restaurants.get(i));
            }
            adapter = new restoadaptateur(getActivity(),restaurants);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    System.out.println("id_restaurant = "+restaurants.get(position).getId());
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", restaurants.get(position).getId() );

                    Resto_detaille fragment = new Resto_detaille();
                    fragment.setArguments(bundle);
                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                            getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(""+restaurants.get(position).getNom());
                    fragmentTransaction.addToBackStack( "Restau" ).commit();

                }
            });

        }

        public List<restaurant> addrestaurants(JSONArray allrestaurants, JCGSQLiteHelper db) {
            try {
                for (int i = 0; i < allrestaurants.length(); i++) {
                    JSONObject c = allrestaurants.getJSONObject(i);
                    int id = c.getInt("id");
                    String nom = c.getString("nom");
                    String adresse = c.getString("adresse");
                    String tel = c.getString("tel");
                    String presentation = c.getString("presentation");
                    String mobilephoto = c.getString("mobilephoto");
                    //Upload photo et icon (url) entre = mobilephoto
                    System.out.println("path = "+mobilephoto);
                    if(mobilephoto!=null)
                    {
                        downloadImage(mobilephoto);
                    }
                    String mobileicone = c.getString("mobileicone");
                    if(mobileicone!=null)
                    {
                        downloadIcone(mobileicone);
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

        private void downloadIcone(String img) {
            try {
                String urlcode = "https://dev.food-in.fr/uploads/restaurants/images/" + img ;
                URL url = new URL(urlcode);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);

                String root = Environment.getExternalStorageDirectory().toString();
                File myDir = new File(root + "/Icon" );
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_restau, menu);
        final MenuItem searchItem = menu.findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("querry "+query) ;
                //votre code ici
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                System.out.println("s= "+s);
                String name = s ;;
                int ville_id =6 ;
                JCGSQLiteHelper db = new JCGSQLiteHelper(getActivity());
                restaurants = db.getrestaurantBy(name, ville_id) ;
                for (int i = 0; i < restaurants.size(); i++) {

                    System.out.println(restaurants.get(i));
                }
                adapter = new restoadaptateur(getActivity(),restaurants);
                list.setAdapter(adapter);

                return false;
            }
        });

    }
}