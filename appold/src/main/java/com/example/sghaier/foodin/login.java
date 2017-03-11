package com.example.sghaier.foodin;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class login extends  AppCompatActivity {
    Button btnlogin, btnfcb;
    TextView tv_subscrite,tv_ici,tv_forget_passwd;
    EditText edit_login_email,password;
    Typeface tf1,tf2,tf3;

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_login);

        btnlogin = (Button) this.findViewById(R.id.btn_login_connect);
        btnfcb = (Button) this.findViewById(R.id.btn_login_fcb);
        tv_subscrite= (TextView) this.findViewById(R.id.login_tv_creat);
        tv_ici= (TextView) this.findViewById(R.id.tv_ici);
        tv_forget_passwd = (TextView) this.findViewById(R.id.tv_forget_passwd);
        edit_login_email = (EditText) this.findViewById(R.id.edit_login_email);
        password = (EditText) this.findViewById(R.id.edit_login_passwd);

        tf1 = Typeface.createFromAsset(getAssets(), "fonts/GothamRounded-Bold.otf");
        tf2 = Typeface.createFromAsset(getAssets(), "fonts/GothamRounded-Medium.otf");
        tf3 = Typeface.createFromAsset(getAssets(), "fonts/GothamRounded-Light.otf");

        btnlogin.setTypeface(tf3);
        btnfcb.setTypeface(tf3);

        tv_subscrite.setTypeface(tf3);
        tv_ici.setTypeface(tf3);
        tv_forget_passwd.setTypeface(tf3);

        edit_login_email.setTypeface(tf3);password.setTypeface(tf3);


        btnfcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, Pannier.class);
                startActivity(i);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, MainActivity.class);
                startActivity(i);
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


}
