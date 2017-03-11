package com.example.sghaier.foodin;

import android.content.Intent;
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

public class Pannier extends Fragment {
    ArrayList<RestauItem> listresto = null;
    public commandeadaptateur adapter;
    Button btnvalidate;
    TextView tvtotal;
    Typeface tf1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.activity_pannier, container, false);
        setHasOptionsMenu(true);

        listresto = RestauItem.getAListOfCommandetemp();
        adapter = new commandeadaptateur(getActivity(), listresto);
        final ListView list = (ListView)rootView.findViewById(R.id.listecommande);
        btnvalidate = (Button)rootView.findViewById(R.id.pannier_btn_validate);
        list.setAdapter(adapter);

        tvtotal = (TextView)rootView.findViewById(R.id.panier_tv_total);
        tf1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GothamRounded-Bold.otf");
        tvtotal.setTypeface(tf1);



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Final_Command fragment = new Final_Command();
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(" Votre Commande ");
                fragmentTransaction.commit();
                //finish();
            }
        });

        btnvalidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdressDelivery fragment = new AdressDelivery();
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(" Adresse de livraison ");
                fragmentTransaction.commit();
                //finish();
            }
        });

        return  rootView;
    }
}
