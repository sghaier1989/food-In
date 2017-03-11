package com.example.sghaier.foodin;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    Button btn_login,btn_inscrit,btn_resto,btn_restos,btn_livraison,btn_alert2,btn_comm,btn_historique,btn_panier, btn_cart , btn_suivi,btn_bot;
    Typeface tf1,tf2,tf3;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bot fragment = new Bot();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        getSupportActionBar().setTitle(" Fooder ");
        getSupportActionBar().setSubtitle(" Votre assistant gastronome ");
        fragmentTransaction.commit();
//
//        btn_login = (Button)findViewById(R.id.btn_login);
//            btn_bot  = (Button)findViewById(R.id.btn_bot);
//        btn_inscrit = (Button)findViewById(R.id.btn_inscrire);
//        btn_resto = (Button)findViewById(R.id.btn_resto);
//        btn_restos = (Button)findViewById(R.id.btn_restos);
//        btn_livraison = (Button)findViewById(R.id.btn_livraison);
//        btn_comm = (Button)findViewById(R.id.btn_commande);
//        btn_historique = (Button)findViewById(R.id.btn_historique);
//        btn_panier = (Button)findViewById(R.id.btn_panier);
//        // btn_alert2 = (Button)findViewById(R.id.btn_alert2);
//        btn_cart = (Button)findViewById(R.id.btn_cart);
//        btn_suivi = (Button) findViewById(R.id.btn_suivi);
//
//
//        btn_suivi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, Suivi.class);
//                startActivity(i);
//            }
//        });
//
//        btn_bot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, Bot.class);
//                startActivity(i);
//            }
//        });
//
//
//        btn_resto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, Resto_detaille.class);
//                startActivity(i);
//            }
//        });
//        btn_cart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, tabshost.class);
//                startActivity(i);
//            }
//        });
//        btn_restos.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, Restau.class);
//                startActivity(i);
//            }
//        });
//
//
//
//        btn_inscrit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, Subscribe.class);
//                startActivity(i);
//            }
//        });
//        btn_livraison.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, AdressDelivery.class);
//                startActivity(i);
//            }
//        });
//        btn_historique.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, HistoriqueCommande.class);
//                startActivity(i);
//            }
//        });
//        btn_comm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, Final_Command.class);
//                startActivity(i);
//            }
//        });
//        btn_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, login.class);
//                startActivity(i);
//            }
//        });
//        btn_panier.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, Pannier.class);
//                startActivity(i);
//            }
//        });
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        //Set the fragment initially

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_suivi) {

            Suivi fragment = new Suivi();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            getSupportActionBar().setTitle(" Suivi de commande ");
            fragmentTransaction.addToBackStack( "bot" ).commit();
            item.setChecked(true);

        } else if (id == R.id.nav_search) {
            Restau fragment = new Restau();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            getSupportActionBar().setTitle(" Restaurants ");
            getSupportActionBar().setSubtitle("");
            fragmentTransaction.addToBackStack( "bot" ).commit();
            item.setChecked(true);

        } else if (id == R.id.nav_fooder) {
            Bot fragment = new Bot();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            getSupportActionBar().setTitle(" Fooder ");
            getSupportActionBar().setSubtitle(" Votre assistant gastronome ");
            fragmentTransaction.addToBackStack( "bot" ).commit();
            item.setChecked(true);

        } else if (id == R.id.nav_editinfo) {
            Editinfos fragment = new Editinfos();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            getSupportActionBar().setTitle(" Modifier mon profil ");getSupportActionBar().setSubtitle("");
            getSupportActionBar().setSubtitle("");
            fragmentTransaction.addToBackStack( "bot" ).commit();
            item.setChecked(true);

        } else if (id == R.id.nav_historique) {
            HistoriqueCommande fragment = new HistoriqueCommande();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            getSupportActionBar().setTitle(" Historique des commandes ");
            fragmentTransaction.addToBackStack( "bot" ).commit();
            item.setChecked(true);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
