package com.example.sghaier.foodin;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
/**
 * Created by SGHAIER on 02/03/2017.
 */
public class DownloadImage{
/*private final String PATH = "/data/foodin/";
    TextView tv;
    String baseurl = "https://dev.food-in.fr/uploads/restaurants/images/";
    //DownloadFromUrl(PATH+"dwn_img.jpg");

    public void DownloadFromUrl(String pictureurl) {
        try {
            String finalurl = baseurl +pictureurl;
            URL url = new URL("http://t3.gstatic.com/images?q=tbn:ANd9GcQs0EPegqi56Alq4vCgC_lVDbZvJtk51RhER7AyDEVA3nUkzjMVK-yDHY3V-w"); //you can write here any link
            File file = new File(pictureurl);

            long startTime = System.currentTimeMillis();
            //tv.setText("Starting download......from " + url);
            URLConnection ucon = url.openConnection();
            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            //We create an array of bytes
            byte[] data = new byte[50];
            int current = 0;

            while((current = bis.read(data,0,data.length)) != -1){
                buffer.write(data,0,current);
            }

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(buffer.toByteArray());
            fos.close();
            fos.close();
            //tv.setText("Download Completed in" + ((System.currentTimeMillis() - startTime) / 1000) + " sec");
        } catch (IOException e) {
             tv.setText("Error: " + e);
        }
    }

    private void SaveIamge(Bitmap finalBitmap,String namepicture) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/foodin/images");
        myDir.mkdirs();
        //Random generator = new Random();
        //n = generator.nextInt(n);
        String fname = namepicture;
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/}