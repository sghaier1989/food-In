package com.example.sghaier.foodin;

import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.sghaier.foodin.R.id.imageView;
import static com.example.sghaier.foodin.R.id.imageView7;
import static java.security.AccessController.getContext;


public class Splash extends AppCompatActivity {

    RelativeLayout layout ;
    ImageView img , logo ;
    Animation slideUpAnimation, slideDownAnimation;
    TextView textView23 ;
    private static String checkversioning = "https://dev.food-in.fr/api/checkversioning";
    private static String validatedupdate = "https://dev.food-in.fr/api/validatedupdate";
    private ProgressDialog pDialog;


    ArrayList<RestauItem> listresto = null;
    List<restaurant> restaurantlist  ;
    List<villes> villes  ;
    List<plats> plats  ;
    List<specialies> specialies  ;
    List<categorieplats> categorieplats  ;
    List<supplimenttypes> supplimenttypes  ;
    List<supplimentchoices> supplimentchoices  ;
    List<restaurantspecialities> restaurantspecialities  ;
    List<categortiesrestaurants> categortiesrestaurants  ;
    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        layout =(RelativeLayout)findViewById(R.id.launch);
        textView23 = (TextView) findViewById(R.id.textView23);
        img=(ImageView)findViewById(R.id.imageView7);
        logo=(ImageView)findViewById(R.id.imageView38);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), "fonts/GothamRounded-Light.otf");
        textView23.setTypeface(tf2);

        Thread timer = new Thread(){
            public void run(){
                try{

                    sleep(4000);

                } catch (InterruptedException e){
                    e.printStackTrace();
                }finally{

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            layout.setBackgroundResource(R.drawable.splash2);



                        }
                    });

                    try{

                        sleep(1000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }finally{


                        try{

                            sleep(3000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
                                    //animator.setRepeatCount(ValueAnimator.);
                                    animator.setInterpolator(new LinearInterpolator());
                                    animator.setDuration(300L);
                                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                        @Override
                                        public void onAnimationUpdate(ValueAnimator animation) {
                                            // layout.setBackgroundResource(R.drawable.splash2);
                                            final float progress = (float) animation.getAnimatedValue();

                                            img.setVisibility(View.VISIBLE);
                                            logo.setVisibility(View.VISIBLE);
                                            textView23.setVisibility(View.VISIBLE);
                                            final float width = layout.getWidth();
                                            final float translationX = width * progress;
                                            img.setTranslationX(translationX);
                                            img.setTranslationX(translationX - width);

                                        }
                                    });
                                    animator.start();
                                }


                            });

                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }finally{


                            try{
                                sleep(1);
                                String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID); ;
                                Context context =getApplicationContext() ;
                                try {
                                    new CheckUpdate(android_id, context ,this).execute();
                                }
                                finally{



                                }
                            } catch (InterruptedException e){
                                e.printStackTrace();
                            }finally{



                            }
                        }
                    }
                }
            }
        };
        timer.start();








    }

}
