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
 * Created by SGHAIER on 31/01/2017.
 */
public class restoadaptateur extends BaseAdapter {
    private Context context;
    private List<RestauItem> mList;
    Typeface tf1,tf2,tf3;

    public restoadaptateur(Activity context, ArrayList<RestauItem> listcontac) {
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
            convertView = inflater.inflate(R.layout.items_restau, null);
        }

        tf1 = Typeface.createFromAsset(context.getAssets(), "fonts/GothamRounded-Bold.otf");
        tf2 = Typeface.createFromAsset(context.getAssets(), "fonts/GothamRounded-Medium.otf");
        tf3 = Typeface.createFromAsset(context.getAssets(), "fonts/GothamRounded-Light.otf");

        TextView tv1_title = (TextView)convertView.findViewById(R.id.item_tv_title);
        TextView tv1_subtitle = (TextView)convertView.findViewById(R.id.items_tv_subtitle);
        TextView tv1_status = (TextView)convertView.findViewById(R.id.textView38);
        //TextView tv1_img = (TextView)convertView.findViewById(R.id.textnum);

        tv1_title.setText(mList.get(position).getTitle().toString());tv1_title.setTypeface(tf1);
        tv1_subtitle.setText(mList.get(position).getSubtitle().toString());tv1_subtitle.setTypeface(tf2);
        tv1_status.setText(mList.get(position).getStatus().toString());tv1_status.setTypeface(tf2);
        //tv1_status.setText(mList.get(position).getNumero().toString());

        return convertView;
    }
}

