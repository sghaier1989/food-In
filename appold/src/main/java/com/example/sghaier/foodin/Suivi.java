package com.example.sghaier.foodin;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Suivi extends Fragment {
    LinearLayout linearsuivi,idcontacter;
    ImageView imgflech;
    boolean flech =false;
    TextView suivi_tv1,suivi_tv2,suivi_tv3,suivi_tv4,suivi_tv5,suivi_tv6,suivi_tv7;
    Typeface tf1,tf2,tf3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.activity_suivi, container, false);

        linearsuivi = (LinearLayout) rootView.findViewById(R.id.linearsuivi);
        idcontacter = (LinearLayout) rootView.findViewById(R.id.idcontacter);
        imgflech = (ImageView) rootView.findViewById(R.id.imgflech);

        suivi_tv1 = (TextView) rootView.findViewById(R.id.suivi_tv1);
        suivi_tv2 = (TextView) rootView.findViewById(R.id.suivi_tv2);
        suivi_tv3 = (TextView) rootView.findViewById(R.id.suivi_tv3);
        suivi_tv4 = (TextView) rootView.findViewById(R.id.suivi_tv4);
        suivi_tv5 = (TextView) rootView.findViewById(R.id.suivi_tv5);
        suivi_tv6 = (TextView) rootView.findViewById(R.id.suivi_tv6);
        suivi_tv7 = (TextView) rootView.findViewById(R.id.suivi_tv7);

        tf1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GothamRounded-Bold.otf");
        tf2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GothamRounded-Medium.otf");
        tf3 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GothamRounded-Light.otf");

        suivi_tv1.setTypeface(tf2);

        suivi_tv2.setTypeface(tf1);

        suivi_tv3.setTypeface(tf2);
        suivi_tv4.setTypeface(tf2);
        suivi_tv5.setTypeface(tf2);
        suivi_tv6.setTypeface(tf2);

        suivi_tv7.setTypeface(tf1);

        imgflech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(flech)
                {
                    linearsuivi.setVisibility(View.VISIBLE);
                    idcontacter.setVisibility(View.VISIBLE);
                    imgflech.setImageResource(R.drawable.flechehaut);
                    flech=false;
                }else{
                    linearsuivi.setVisibility(View.GONE);
                    idcontacter.setVisibility(View.GONE);
                    imgflech.setImageResource(R.drawable.flechebas);
                    flech=true;
                }
            }
        });
        return rootView;
    }
}
