package com.example.sghaier.foodin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class Splash extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent openMainActivity =  new Intent(Splash.this,login.class);
                    startActivity(openMainActivity);
                }
            }
        };
        timer.start();
    }

}
