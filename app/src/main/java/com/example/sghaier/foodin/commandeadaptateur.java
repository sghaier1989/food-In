package com.example.sghaier.foodin;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SGHAIER on 01/02/2017.
 */
public class commandeadaptateur extends BaseAdapter {
    private Context context;
    private List<RestauItem> mList;
    Typeface tf1,tf2,tf3;

    public commandeadaptateur(Activity context, ArrayList<RestauItem> listcontac) {
        this.context = context;
        mList=listcontac  ;
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
            convertView = inflater.inflate(R.layout.items_pannier, null);
        }

        TextView tv1_title = (TextView)convertView.findViewById(R.id.itemspaniers_tv_title);
        TextView tv1_subtitle = (TextView)convertView.findViewById(R.id.itemspaniers_tv_prix);
        TextView tv1_status = (TextView)convertView.findViewById(R.id.itemspaniers_tv_nbr);
        TextView tv1_img = (TextView)convertView.findViewById(R.id.itemspaniers_tv_subtitle);


        tf1 = Typeface.createFromAsset(context.getAssets(), "fonts/GothamRounded-Bold.otf");
        tf2 = Typeface.createFromAsset(context.getAssets(), "fonts/GothamRounded-Medium.otf");
        tf3 = Typeface.createFromAsset(context.getAssets(), "fonts/GothamRounded-Light.otf");

        tv1_title.setText(mList.get(position).getTitle().toString());tv1_title.setTypeface(tf1);
        tv1_subtitle.setText(mList.get(position).getSubtitle().toString());tv1_subtitle.setTypeface(tf1);
        tv1_status.setText(mList.get(position).getStatus().toString());tv1_status.setTypeface(tf3);
        tv1_status.setText(mList.get(position).getImg().toString());tv1_status.setTypeface(tf3);

        return convertView;
    }
}


