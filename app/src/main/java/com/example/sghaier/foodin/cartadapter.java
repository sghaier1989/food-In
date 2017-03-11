package com.example.sghaier.foodin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by SGHAIER on 17/02/2017.
 */
public class cartadapter extends BaseAdapter {
    private Context context;
    private List<plats> mList;
    Typeface tf1,tf2,tf3;
    AlertDialog.Builder adb;
    JCGSQLiteHelper db;
    public TextView tv1_title;
    TextView tv1_prix;
    TextView tv_title_alert;
    public TextView valueTV;
    LinearLayout linearlayoutadd;
    public cartadapter(Activity context, List<plats> listcontac,AlertDialog.Builder adb,JCGSQLiteHelper db) {
        this.context = context;
        this.adb = adb;
        this.db = db;
        mList=listcontac  ;
    }
    public cartadapter() {

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent ) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cart_item, null);
        }



        LayoutInflater factory = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        final View alertDialogView = factory.inflate(R.layout.alertperso, null);
        //Création de l'AlertDialog

        //On affecte la vue personnalisé que l'on a crée à notre AlertDialog
        adb.setView(alertDialogView);
        //TextView tv_total = (TextView)alertDialogView.findViewById(R.id.alerthistorique_total);
        tv_title_alert = (TextView)alertDialogView.findViewById(R.id.allertperso_tv_title);
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
        tv_title_alert.setTypeface(tf1);
        //tv_total.setTypeface(tf1);

        linearlayoutadd = (LinearLayout)convertView.findViewById(R.id.linearlayoutadd);
        tv1_title = (TextView)convertView.findViewById(R.id.itemspaniers_tv_title);
        tv1_prix = (TextView)convertView.findViewById(R.id.itemspaniers_tv_prix);
        final TextView tv1_status = (TextView)convertView.findViewById(R.id.itemspaniers_tv_nbr);
        TextView tv1_subtitle = (TextView)convertView.findViewById(R.id.itemspaniers_tv_subtitle);
        ImageView cart_icon = (ImageView)convertView.findViewById(R.id.cart_icon);

        final ImageView  ajouter  = (ImageView)convertView.findViewById(R.id.itemspaniers_tv_inc);
        ImageView  decrementer  = (ImageView)convertView.findViewById(R.id.itemspaniers_tv_dec);


        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int nb= Integer.valueOf(tv1_status.getText().toString()) + 1;
//                tv1_status.setText(String.valueOf(nb));

                int plats_id= mList.get(position).getId();


                tv_title_alert.setText(mList.get(position).getNom()+" "+mList.get(position).getPrix()+"€");
                System.out.println("teste .................... "+tv_title_alert.getText());

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
        });

        decrementer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nb =Integer.valueOf(tv1_status.getText().toString());
                if (nb!=0)
                {nb = nb - 1;}
                else
                {nb = 0 ;}
                tv1_status.setText(String.valueOf(nb));
            }
        });

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv1_title.setText(mList.get(position).getNom().toString());tv1_title.setTypeface(tf1);
                tv1_prix.setText(mList.get(position).getPrix().toString()+"€");

                List<supplimenttypes> supplimenttypes = new LinkedList<supplimenttypes>() ;
                supplimenttypes = db.getsupplimenttypesbyplats(mList.get(position).getId()) ;
                for (int i = 0; i < supplimenttypes.size(); i++) {
                    System.out.println(supplimenttypes.get(i).toString()) ;
                    List<supplimentchoices> supplimentchoiceslist = new LinkedList<supplimentchoices>();
                    supplimentchoiceslist =  db.getchoicebytype(supplimenttypes.get(i).getId()) ;
                }
                // ICI Boucle ajouter
                //  TODO: boucle de affiche choice type + choice + listner
                //

                adb.show();
            }
        });

        tf1 = Typeface.createFromAsset(context.getAssets(), "fonts/GothamRounded-Bold.otf");
        tf2 = Typeface.createFromAsset(context.getAssets(), "fonts/GothamRounded-Medium.otf");
        tf3 = Typeface.createFromAsset(context.getAssets(), "fonts/GothamRounded-Light.otf");

        tv1_subtitle.setTypeface(tf1);
        tv1_title.setText(mList.get(position).getNom().toString());tv1_title.setTypeface(tf1);
        //tv1_status.setText(mList.get(position).getNom().toString());tv1_status.setTypeface(tf3);
        tv1_subtitle.setText(mList.get(position).getDescription().toString());tv1_status.setTypeface(tf3);

        String url = "http://www.restaurantmartinwishart.co.uk/wp-content/themes/martin-wishart/images/gallery/overview-of-the-restaurant.jpg";
        if(mList.get(position).getPhoto().toString().trim()!= null)
        {
             url = "https://dev.food-in.fr/uploads/restaurants/images/"+mList.get(position).getPhoto().toString();
            System.out.println("......."+url);
        }
        System.out.println("url= "+url);
        File iconFile = new  File("/storage/emulated/0/icon/"+mList.get(position).getPhoto().toString().trim());
        cart_icon.setImageBitmap(BitmapFactory.decodeFile(iconFile.getAbsolutePath()));
//      new ImageDownloader(cart_icon).execute(url);

        return convertView;
    }

    class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public ImageDownloader(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}


