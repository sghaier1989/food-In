package com.example.sghaier.foodin;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Editinfos extends Fragment {
    Button btnvalider;
    Typeface tf1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.activity_edit__infos, container, false);

        btnvalider = (Button)rootView.findViewById(R.id.btn_subscribe_validate);

        tf1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GothamRounded-Bold.otf");

        btnvalider.setTypeface(tf1);

        return rootView;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
