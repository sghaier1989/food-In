package com.example.sghaier.foodin;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Resto_detaille extends AppCompatActivity {
    ImageView resto_detail_imgview,resto_detaille_imgv_livraison;
    AlertDialog.Builder adb;
    TextView alert_reservetv_annuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resto_detaille);
        LayoutInflater factory = LayoutInflater.from(this);
        final View alertDialogView = factory.inflate(R.layout.alertreservo, null);

        //Création de l'AlertDialog
        adb = new AlertDialog.Builder(this);

        //On affecte la vue personnalisé que l'on a crée à notre AlertDialog
        adb.setView(alertDialogView);

        resto_detail_imgview = (ImageView)findViewById(R.id.resto_detail_imgview);
        resto_detaille_imgv_livraison  = (ImageView)findViewById(R.id.resto_detaille_imgv_livraison);
        alert_reservetv_annuler= (TextView) alertDialogView.findViewById(R.id.alert_reservetv_annuler);

        resto_detaille_imgv_livraison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Resto_detaille.this, tabshost.class);
                startActivity(i);
            }
        });
        alert_reservetv_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        resto_detail_imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adb.show();
            }
        });
    }
}
