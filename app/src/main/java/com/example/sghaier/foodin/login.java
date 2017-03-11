package com.example.sghaier.foodin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class login extends  AppCompatActivity {
    Button btnlogin, btnfcb;
    TextView tv_subscrite,tv_ici,tv_forget_passwd,tv_login_restauration;
    EditText edit_login_email,password;
    Typeface tf1,tf2,tf3;
    LoginButton loginfcb;
    private ProgressDialog pDialog;
    int result  ;
    CallbackManager callbackmanager;
    private GoogleApiClient client;
    private static String authentification = "https://dev.food-in.fr/api/authentification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        tf1 = Typeface.createFromAsset(getAssets(), "fonts/GothamRounded-Bold.otf");
        tf2 = Typeface.createFromAsset(getAssets(), "fonts/GothamRounded-Medium.otf");
        tf3 = Typeface.createFromAsset(getAssets(), "fonts/GothamRounded-Light.otf");

        btnlogin = (Button) this.findViewById(R.id.btn_login_connect);
        btnlogin.setTypeface(tf3);

        //tv_login_restauration = (TextView) this.findViewById(R.id.tv_login_restauration);
        //tv_login_restauration.setTypeface(tf3);
        btnfcb = (Button) this.findViewById(R.id.btn_login_fcb);
        btnfcb.setTypeface(tf3);

        loginfcb = (LoginButton) this.findViewById(R.id.btn_login_fcb);
        callbackmanager = CallbackManager.Factory.create();

        loginfcb.registerCallback(callbackmanager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("fcb sucess = "+loginResult.toString());
                Intent i = new Intent(login.this, MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


        tv_subscrite= (TextView) this.findViewById(R.id.login_tv_creat);
        tv_ici= (TextView) this.findViewById(R.id.tv_ici);
        tv_forget_passwd = (TextView) this.findViewById(R.id.tv_forget_passwd);
        edit_login_email = (EditText) this.findViewById(R.id.edit_login_email);
        password = (EditText) this.findViewById(R.id.edit_login_passwd);

        tv_subscrite.setTypeface(tf3);
        tv_ici.setTypeface(tf3);
        tv_forget_passwd.setTypeface(tf3);

        edit_login_email.setTypeface(tf3);password.setTypeface(tf3);

//        btnfcb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(login.this, Pannier.class);
//                startActivity(i);
//            }
//        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Checklogin().execute();
                // System.out.println(result);


            }
        });

        tv_subscrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, Subscribe.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackmanager.onActivityResult(requestCode,resultCode,data);
    }

    private class Checklogin extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(login.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show() ;

            // result = true ;

        }

        @Override
        protected Void doInBackground(Void... arg0) {


            HttpHandler sh = new HttpHandler();
            ArrayList tag = new ArrayList() ;
            tag.add("email") ;
            tag.add("password") ;
            ArrayList param = new ArrayList() ;
            param.add("achrafsmida123kk0000000@gmail.com") ;
            param.add("achsmi") ;
            String jsonStr = sh.makeServiceCall(authentification , "POST" ,  tag ,  param);
            System.out.println(jsonStr) ;
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                //System.out.println();
                // JSONObject success = jsonObj.getJSONObject("success");

              //  result =  Integer.parseInt(jsonObj.get("success").toString() );
              //  System.out.println(result);

              //  if(result != 0) {

                    Intent i = new Intent(login.this, MainActivity.class);
                    startActivity(i);
                //}
               // else {


              //  }

            } catch (final JSONException e) {


            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

        }
    }
}
