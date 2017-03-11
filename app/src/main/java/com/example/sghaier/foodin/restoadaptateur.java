package com.example.sghaier.foodin;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SGHAIER on 31/01/2017.
 */
public class restoadaptateur extends BaseAdapter {
    private Context context;
    private List<restaurant> mList;
    Typeface tf1,tf2,tf3;

    public restoadaptateur(Activity context, List<restaurant> listrestaurant) {
        this.context = context;
        mList=listrestaurant  ;
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
        RelativeLayout tv1_img = (RelativeLayout)convertView.findViewById(R.id.item_photo);
        ImageView tv2_icon = (ImageView)convertView.findViewById(R.id.imgv_restau);

        tv1_title.setText(mList.get(position).getNom().toString());tv1_title.setTypeface(tf1);
        //tv1_subtitle.setText(mList.get(position).getPresentation().toString());tv1_subtitle.setTypeface(tf2);
        //tv1_status.setText(mList.get(position).getPresentation().toString());
        tv1_status.setTypeface(tf2);
        //tv1_img.set("https://dev.food-in.fr/uploads/restaurants/images/"+mList.get(position).getMobilephoto().toString());
        File imageFile = new  File("/storage/emulated/0/restaurants/"+mList.get(position).getMobilephoto().toString().trim());
        File iconFile = new  File("/storage/emulated/0/Icon/"+mList.get(position).getMobileicone().toString().trim());
        Bitmap bmImg1 = BitmapFactory.decodeFile(iconFile.getAbsolutePath());
        BitmapDrawable background2 = new BitmapDrawable(bmImg1);
        tv2_icon.setBackgroundDrawable(background2);
        if(imageFile.exists()){
            Bitmap bmImg = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            BitmapDrawable background = new BitmapDrawable(bmImg);
            tv1_img.setBackgroundDrawable(background);
           // tv1_img.setBackgroundResource(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
        }

        String url = "http://www.restaurantmartinwishart.co.uk/wp-content/themes/martin-wishart/images/gallery/overview-of-the-restaurant.jpg";
        if(mList.get(position).getMobilephoto().toString().trim()!= null)
        {
            url = "https://dev.food-in.fr/uploads/restaurants/images/"+mList.get(position).getMobilephoto().toString();
        }
        System.out.println("url= "+url);
        //new ImageDownloader(tv1_img).execute(url);
        return convertView;
    }

    class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public ImageDownloader(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

