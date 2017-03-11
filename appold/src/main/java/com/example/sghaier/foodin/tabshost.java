package com.example.sghaier.foodin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

public class tabshost extends AppCompatActivity {
    Typeface tf1,tf2,tf3;
    ArrayList<RestauItem> listresto = null;
    public commandeadaptateur adapter;
    AlertDialog.Builder adb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabshost);

        tf1 = Typeface.createFromAsset(getAssets(), "fonts/GothamRounded-Bold.otf");
        tf2 = Typeface.createFromAsset(getAssets(), "fonts/GothamRounded-Medium.otf");
        tf3 = Typeface.createFromAsset(getAssets(), "fonts/GothamRounded-Light.otf");

        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        LayoutInflater factory = LayoutInflater.from(this);
        final View alertDialogView = factory.inflate(R.layout.alertperso, null);

        //Création de l'AlertDialog
        adb = new AlertDialog.Builder(this);

        //On affecte la vue personnalisé que l'on a crée à notre AlertDialog
        adb.setView(alertDialogView);

        tf1 = Typeface.createFromAsset(getAssets(), "fonts/GothamRounded-Bold.otf");
        tf2 = Typeface.createFromAsset(getAssets(), "fonts/GothamRounded-Medium.otf");
        tf3 = Typeface.createFromAsset(getAssets(), "fonts/GothamRounded-Light.otf");

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



        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("PAIN INDIEN");
        spec.setContent(R.id.linearLayout9);
        spec.setIndicator("PAIN INDIEN");
        host.addTab(spec);

        TextView tv = (TextView) host.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
        tv.setTextColor(getResources().getColor(R.color.rose_clear));
        tv.setTypeface(tf2);

        //Tab 2
        spec = host.newTabSpec("ENTREES");
        spec.setContent(R.id.linearLayout10);
        spec.setIndicator("ENTREES");
        host.addTab(spec);

        tv = (TextView) host.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
        tv.setTextColor(getResources().getColor(R.color.rose_clear));
        tv.setTypeface(tf2);

        //Tab 3
        spec = host.newTabSpec("RIZ");
        spec.setContent(R.id.linearLayout11);
        spec.setIndicator("RIZ");
        host.addTab(spec);

        tv = (TextView) host.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
        tv.setTextColor(getResources().getColor(R.color.rose_clear));
        tv.setTypeface(tf2);

        listresto = RestauItem.getAListOfCommandetemp();
        adapter = new commandeadaptateur(this, listresto);
        final ListView list = (ListView)findViewById(R.id.listView);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adb.show();
            }
        });

        tv_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finishFromChild(tabshost.this);
                adb.setCancelable(false);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_tab, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_tab_panier:
                Intent i = new Intent(tabshost.this, Pannier.class);
                startActivity(i);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
