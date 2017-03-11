package com.example.sghaier.foodin;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Pannier extends AppCompatActivity {
    ArrayList<RestauItem> listresto = null;
    public commandeadaptateur adapter;
    Button btnvalidate;
    TextView tvtotal;
    Typeface tf1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pannier);

        listresto = RestauItem.getAListOfCommandetemp();
        adapter = new commandeadaptateur(this, listresto);
        final ListView list = (ListView)findViewById(R.id.listecommande);
        btnvalidate = (Button)findViewById(R.id.pannier_btn_validate);
        list.setAdapter(adapter);

        tvtotal = (TextView)findViewById(R.id.panier_tv_total);
        tf1 = Typeface.createFromAsset(getAssets(), "fonts/GothamRounded-Bold.otf");
        tvtotal.setTypeface(tf1);



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(Pannier.this, Final_Command.class);
                startActivity(i);
                //finish();
            }
        });

        btnvalidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pannier.this, Final_Command.class);
                startActivity(i);
                //finish();
            }
        });
    }
}
