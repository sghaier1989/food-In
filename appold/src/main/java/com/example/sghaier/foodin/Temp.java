package com.example.sghaier.foodin;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toolbar;

public class Temp extends AppCompatActivity {
    Button btn_login,btn_inscrit,btn_resto,btn_restos,btn_livraison,btn_alert2,btn_comm,btn_historique,btn_panier;
    Typeface tf1,tf2,tf3;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        btn_login = (Button)findViewById(R.id.btn_login);
        btn_inscrit = (Button)findViewById(R.id.btn_inscrire);
        btn_resto = (Button)findViewById(R.id.btn_resto);
        btn_restos = (Button)findViewById(R.id.btn_restos);
        btn_livraison = (Button)findViewById(R.id.btn_livraison);
        btn_comm = (Button)findViewById(R.id.btn_commande);
        btn_historique = (Button)findViewById(R.id.btn_historique);
        btn_panier = (Button)findViewById(R.id.btn_panier);
        btn_alert2 = (Button)findViewById(R.id.btn_alert2);

        btn_alert2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On instancie notre layout en tant que View
                LayoutInflater factory = LayoutInflater.from(Temp.this);
                final View alertDialogView = factory.inflate(R.layout.alertperso, null);

                //Création de l'AlertDialog
                AlertDialog.Builder adb = new AlertDialog.Builder(Temp.this);

                //On affecte la vue personnalisé que l'on a crée à notre AlertDialog
                adb.setView(alertDialogView);

                tf1 = Typeface.createFromAsset(getAssets(), "fonts/GothamRounded-Bold.otf");
                tf2 = Typeface.createFromAsset(getAssets(), "fonts/GothamRounded-Medium.otf");
                tf3 = Typeface.createFromAsset(getAssets(), "fonts/GothamRounded-Light.otf");

                TextView tv_total = (TextView)alertDialogView.findViewById(R.id.alerthistorique_total);
                TextView tv_date = (TextView)alertDialogView.findViewById(R.id.tv_historique_date);
                TextView tv_historique_fermer = (TextView)alertDialogView.findViewById(R.id.tv_historique_fermer);
                TextView tv_sous_total = (TextView)alertDialogView.findViewById(R.id.tv_sous_total);
                TextView tv_frais = (TextView)alertDialogView.findViewById(R.id.tv_frais);
                TextView tv_prix_sous = (TextView)alertDialogView.findViewById(R.id.textView66);
                TextView tv_offert = (TextView)alertDialogView.findViewById(R.id.tv_offert);

//                TextView tv_historique_soda_madras0 = (TextView)alertDialogView.findViewById(R.id.tv_historique_soda_madras0);
//                TextView tv_historique_soda_madras1 = (TextView)alertDialogView.findViewById(R.id.tv_historique_soda_madras1);
//                TextView textView60 = (TextView)alertDialogView.findViewById(R.id.textView60);
//
//                tv_historique_soda_madras1.setTypeface(tf2);
//                textView60.setTypeface(tf3);
//                tv_historique_soda_madras0.setTypeface(tf2);
                tv_frais.setTypeface(tf2);tv_prix_sous.setTypeface(tf2);
                tv_sous_total.setTypeface(tf2);tv_offert.setTypeface(tf2);

                tv_date.setTypeface(tf1);
                tv_total.setTypeface(tf1);
                tv_historique_fermer.setTypeface(tf1);

                adb.show();
            }
        });

        btn_resto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Temp.this, Resto_detaille.class);
                startActivity(i);
            }
        });

        btn_restos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Temp.this, Restau.class);
                startActivity(i);
            }
        });
        btn_inscrit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Temp.this, Subscribe.class);
                startActivity(i);
            }
        });
        btn_livraison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Temp.this, AdressDelivery.class);
                startActivity(i);
            }
        });
        btn_historique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Temp.this, HistoriqueCommande.class);
                startActivity(i);
            }
        });
        btn_comm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Temp.this, Final_Command.class);
                startActivity(i);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Temp.this, login.class);
                startActivity(i);
            }
        });
        btn_panier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Temp.this, Pannier.class);
                startActivity(i);
            }
        });
    }
}
