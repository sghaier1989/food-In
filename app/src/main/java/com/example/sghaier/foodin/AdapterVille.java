package com.example.sghaier.foodin;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SGHAIER on 31/01/2017.
 */
public class AdapterVille extends BaseAdapter {
    private Context context;
    private List<villes> mList;
    private String[] codepostalelistes  ;
    Typeface tf1,tf2,tf3;
    TextView tv_nom,tv_codepostale;

    public AdapterVille(Activity context, List<villes> listvilles, String[] codepostalelistes ) {
        this.context = context;
        this.mList = listvilles  ;
        this.codepostalelistes =codepostalelistes ;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.itemalertville, null);
        }

        tv_nom = (TextView)convertView.findViewById(R.id.tv_alert_ville);
        tv_codepostale = (TextView)convertView.findViewById(R.id.tv_alert_codepostale);

        tf1 = Typeface.createFromAsset(context.getAssets(), "fonts/GothamRounded-Bold.otf");
        tf2 = Typeface.createFromAsset(context.getAssets(), "fonts/GothamRounded-Medium.otf");
        tf3 = Typeface.createFromAsset(context.getAssets(), "fonts/GothamRounded-Light.otf");

        tv_nom.setText(mList.get(position).getNom().toString());tv_nom.setTypeface(tf1);
        tv_codepostale.setText(codepostalelistes[position]);tv_codepostale.setTypeface(tf3);




        return convertView;
    }
}