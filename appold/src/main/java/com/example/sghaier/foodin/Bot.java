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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Bot extends Fragment {
    Typeface tf1, tf2, tf3;
    LinearLayout linearreponse1, linearreponse2, linearresultrecherche;
    RelativeLayout layoutquestion2;
    boolean status = false;
    ListView listbot;
    TextView bot_tv_bonjour, bot_tv_asiatique, bot_tv_burger, bot_tv_riz, bot_tv_oriental, bot_tv_plus, bot_tv_reponse1;
    TextView bot_tv_question2, bot_tv_livraison, bot_tv_aemporter, bot_tv_reservation, bot_tv_reponse2, bot_tv_resultatrecherche;
    public restoadaptateur adapter;
    ArrayList<RestauItem> list = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.activity_bot, container, false);

        list = RestauItem.getAListOfContact();
        adapter = new restoadaptateur(getActivity(), list);
        listbot =(ListView)rootView.findViewById(R.id.listbot);
        listbot.setAdapter(adapter);


        bot_tv_bonjour = (TextView) rootView.findViewById(R.id.bot_tv_bonjour);
        bot_tv_asiatique = (TextView) rootView.findViewById(R.id.bot_tv_asiatique);
        bot_tv_burger = (TextView) rootView.findViewById(R.id.bot_tv_burger);
        bot_tv_riz = (TextView) rootView.findViewById(R.id.bot_tv_riz);
        bot_tv_oriental = (TextView) rootView.findViewById(R.id.bot_tv_oriental);
        bot_tv_plus = (TextView) rootView.findViewById(R.id.bot_tv_plus);

        bot_tv_reponse1 = (TextView) rootView.findViewById(R.id.bot_tv_reponse1);

        linearreponse1 = (LinearLayout) rootView.findViewById(R.id.linearreponse1);
        layoutquestion2 = (RelativeLayout) rootView.findViewById(R.id.layoutquestion2);
        linearreponse2 = (LinearLayout) rootView.findViewById(R.id.linearreponse2);
        linearresultrecherche = (LinearLayout) rootView.findViewById(R.id.linearresultrecherche);


        bot_tv_question2 = (TextView) rootView.findViewById(R.id.bot_tv_question2);
        bot_tv_livraison = (TextView) rootView.findViewById(R.id.bot_tv_livraison);
        bot_tv_aemporter = (TextView) rootView.findViewById(R.id.bot_tv_aemporter);
        bot_tv_reservation = (TextView) rootView.findViewById(R.id.bot_tv_reservation);
        bot_tv_reponse2 = (TextView) rootView.findViewById(R.id.bot_tv_reponse2);
        bot_tv_resultatrecherche = (TextView) rootView.findViewById(R.id.bot_tv_resultatrecherche);

        tf1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GothamRounded-Bold.otf");
        tf2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GothamRounded-Medium.otf");
        tf3 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GothamRounded-Light.otf");

        bot_tv_bonjour.setTypeface(tf3);
        bot_tv_question2.setTypeface(tf3);
        bot_tv_asiatique.setTypeface(tf3);
        bot_tv_livraison.setTypeface(tf3);
        bot_tv_burger.setTypeface(tf3);
        bot_tv_aemporter.setTypeface(tf3);
        bot_tv_riz.setTypeface(tf3);
        bot_tv_reservation.setTypeface(tf3);
        bot_tv_oriental.setTypeface(tf3);
        bot_tv_plus.setTypeface(tf3);
        bot_tv_reponse1.setTypeface(tf3);
        bot_tv_reponse2.setTypeface(tf3);

        bot_tv_resultatrecherche.setTypeface(tf3);

        bot_tv_plus.setOnClickListener(gererclick);
        bot_tv_asiatique.setOnClickListener(gererclick);
        bot_tv_burger.setOnClickListener(gererclick);
        bot_tv_riz.setOnClickListener(gererclick);
        bot_tv_oriental.setOnClickListener(gererclick);

        bot_tv_aemporter.setOnClickListener(gererclick);
        bot_tv_reservation.setOnClickListener(gererclick);
        bot_tv_livraison.setOnClickListener(gererclick);

        return rootView;

    }

    private View.OnClickListener gererclick = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId() ) {
                case R.id.bot_tv_oriental:
                    linearreponse1.setVisibility(View.VISIBLE);
                    bot_tv_reponse1.setText(" Oriental ");
                    layoutquestion2.setVisibility(View.VISIBLE);
                    break;

                case R.id.bot_tv_riz:
                    linearreponse1.setVisibility(View.VISIBLE);
                    bot_tv_reponse1.setText(" RIZ ");
                    layoutquestion2.setVisibility(View.VISIBLE);
                    break;

                case R.id.bot_tv_burger:
                    linearreponse1.setVisibility(View.VISIBLE);
                    bot_tv_reponse1.setText(" Burger ");
                    layoutquestion2.setVisibility(View.VISIBLE);
                    break;

                case R.id.bot_tv_asiatique:
                    linearreponse1.setVisibility(View.VISIBLE);
                    bot_tv_reponse1.setText(" Asiatique ");
                    layoutquestion2.setVisibility(View.VISIBLE);
                    break;

                case R.id.bot_tv_livraison:
                    linearreponse2.setVisibility(View.VISIBLE);
                    bot_tv_reponse2.setText(" Livraison ");
                    linearresultrecherche.setVisibility(View.VISIBLE);
                    break;

                case R.id.bot_tv_aemporter:
                    linearreponse2.setVisibility(View.VISIBLE);
                    bot_tv_reponse2.setText(" A emporter ");
                    linearresultrecherche.setVisibility(View.VISIBLE);
                    break;

                case R.id.bot_tv_reservation:
                    linearreponse2.setVisibility(View.VISIBLE);
                    bot_tv_reponse2.setText(" Reservation ");
                    linearresultrecherche.setVisibility(View.VISIBLE);
                    break;

                case R.id.bot_tv_plus:
                    if (status) {
                        bot_tv_riz.setVisibility(View.VISIBLE);
                        bot_tv_oriental.setVisibility(View.VISIBLE);
                        status = false;
                    } else {
                        bot_tv_riz.setVisibility(View.GONE);
                        bot_tv_oriental.setVisibility(View.GONE);
                        status = true;
                    }
                    break;

                default:
                    break;
            }
        }
    };

}
