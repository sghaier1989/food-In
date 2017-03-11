package com.example.sghaier.foodin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.CheckedOutputStream;

public class Final_Command  extends Fragment {
    Typeface tf1,tf2,tf3;
    TextView  finalcommande_edit_adress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        tf1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GothamRounded-Bold.otf");
        tf2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GothamRounded-Medium.otf");
        tf3 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GothamRounded-Light.otf");

        View rootView = inflater.inflate(R.layout.activity_final__command, container, false);
        setHasOptionsMenu(true);

        //On instancie notre layout en tant que View
        LayoutInflater factory = LayoutInflater.from(getActivity());

        final View alertDialogView = factory.inflate(R.layout.alertperso, null);
        finalcommande_edit_adress = (TextView)rootView.findViewById(R.id.finalcommande_edit_adress);



        finalcommande_edit_adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //Création de l'AlertDialog
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());

        //On affecte la vue personnalisé que l'on a crée à notre AlertDialog
        adb.setView(alertDialogView);

        //TextView tv_total = (TextView)alertDialogView.findViewById(R.id.alerthistorique_total);
        TextView tv_title = (TextView)alertDialogView.findViewById(R.id.allertperso_tv_title);

//        CheckBox ck_sans = (CheckBox)alertDialogView.findViewById(R.id.tv_sasna);
//        CheckBox ck_soda = (CheckBox)alertDialogView.findViewById(R.id.tv_sasna_chawal);
//        CheckBox ck_pal = (CheckBox)alertDialogView.findViewById(R.id.tv_chawal_mattar_pulao);
//        CheckBox ck_kha = (CheckBox)alertDialogView.findViewById(R.id.tv_khamir);

        TextView tv_ajouter = (TextView)alertDialogView.findViewById(R.id.tv_ajouter);
        TextView tv_annuler = (TextView)alertDialogView.findViewById(R.id.tv_annuler);

        //ck_sans.setTypeface(tf3);ck_soda.setTypeface(tf3);
        //ck_pal.setTypeface(tf3);ck_kha.setTypeface(tf3);

        finalcommande_edit_adress.setTypeface(tf1);
        tv_ajouter.setTypeface(tf2);
        tv_annuler.setTypeface(tf2);
        tv_title.setTypeface(tf1);
        //tv_total.setTypeface(tf1);
        // adb.show();

        return rootView;
    }
}
