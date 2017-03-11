package com.example.sghaier.foodin;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class Restau extends Fragment {

    ArrayList<RestauItem> listresto = null;
    public restoadaptateur adapter;
    TextView restau_tv_modifier,restau_tv_restaurants_ouvert;
    Typeface tf1,tf2,tf3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        tf1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GothamRounded-Bold.otf");
        tf2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GothamRounded-Medium.otf");
        tf3 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GothamRounded-Light.otf");

        View rootView = inflater.inflate(R.layout.activity_restau, container, false);
        setHasOptionsMenu(true);
        listresto = RestauItem.getAListOfContact();
        adapter = new restoadaptateur(getActivity(), listresto);
        final ListView list = (ListView)rootView.findViewById(R.id.listresto);

        restau_tv_modifier = (TextView)rootView.findViewById(R.id.restau_tv_modifier);
        restau_tv_restaurants_ouvert = (TextView)rootView.findViewById(R.id.restau_tv_restaurants_ouvert);
        restau_tv_restaurants_ouvert.setTypeface(tf1);
        restau_tv_modifier.setTypeface(tf3);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getContext(), Resto_detaille.class);
                startActivity(i);
            }
        });
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_restau, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
