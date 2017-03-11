package com.example.sghaier.foodin;

import android.app.AlertDialog;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoriqueCommande extends Fragment {
    ArrayList<RestauItem> listresto = null;
    public Historiqueadaptateur adapter;
    AlertDialog.Builder adb;
    TextView tv_historique_fermer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.activity_historique_commande, container, false);
        Typeface tf1, tf2, tf3;
        listresto = RestauItem.getAListOfHistoriquetemp();
        adapter = new Historiqueadaptateur(getActivity(), listresto);
        final ListView list = (ListView) rootView.findViewById(R.id.listhistorique);

        list.setAdapter(adapter);

        tf1 = Typeface.createFromAsset(getContext().getAssets(), "fonts/GothamRounded-Bold.otf");
        tf2 = Typeface.createFromAsset(getContext().getAssets(), "fonts/GothamRounded-Medium.otf");
        tf3 = Typeface.createFromAsset(getContext().getAssets(), "fonts/GothamRounded-Light.otf");

        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View alertDialogView = factory.inflate(R.layout.alerthistorique, null);

        //Création de l'AlertDialog
        adb = new AlertDialog.Builder(getActivity());

        tv_historique_fermer = (TextView)alertDialogView.findViewById(R.id.tv_historique_fermer);
        tv_historique_fermer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //On affecte la vue personnalisé que l'on a crée à notre AlertDialog
        adb.setView(alertDialogView);

        TextView tv_total = (TextView) alertDialogView.findViewById(R.id.alerthistorique_total);
        TextView tv_date = (TextView) alertDialogView.findViewById(R.id.tv_historique_date);
        TextView tv_historique_fermer = (TextView) alertDialogView.findViewById(R.id.tv_historique_fermer);
        TextView tv_sous_total = (TextView) alertDialogView.findViewById(R.id.tv_sous_total);
        TextView tv_frais = (TextView) alertDialogView.findViewById(R.id.tv_frais);
        TextView tv_prix_sous = (TextView) alertDialogView.findViewById(R.id.textView66);
        TextView tv_offert = (TextView) alertDialogView.findViewById(R.id.tv_offert);
//
        tv_frais.setTypeface(tf2);
        tv_prix_sous.setTypeface(tf2);
        tv_sous_total.setTypeface(tf2);
        tv_offert.setTypeface(tf2);
//
        tv_date.setTypeface(tf1);
        tv_total.setTypeface(tf1);
        tv_historique_fermer.setTypeface(tf1);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adb.show();
            }
        });

        return rootView;
    }

    private void finish() {
        finish();
    }
}
