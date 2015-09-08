package com.ecolemultimedia.sabermostaf.projet2.activities;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.ecolemultimedia.sabermostaf.projet2.R;
import com.ecolemultimedia.sabermostaf.projet2.adapters.HistoriqueListAdapter;
import com.ecolemultimedia.sabermostaf.projet2.components.DrawerActivity;
import com.ecolemultimedia.sabermostaf.projet2.models.Item;
import com.ecolemultimedia.sabermostaf.projet2.utils.Loader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends DrawerActivity {


    private HistoriqueListAdapter adapter;
    private Loader loader = null;
    private ArrayList<String> listItem = null;
    private ListView ui_lis_sub_historique = null;


    private static final String PREFS_TAG = "SharedPrefs";
    private static final String ITEM_TAG = "MyProduct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loader = new Loader(this);

        listItem = new ArrayList<String>();


        setupActrionBar();
        setContentView(R.layout.activity_home);
        initDrawer();


        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE);

        String l = sharedPref.getString("name", null);

        listItem.add(l);

        ui_lis_sub_historique = (ListView) findViewById(R.id.ui_lis_sub_historique);
        adapter = new HistoriqueListAdapter(this, listItem);
       // ui_lis_sub_historique.setAdapter(adapter);
    }




    public void setupActrionBar() {
        ActionBar bar = getActionBar();
        // bar.setCustomView(R.layout.actionbar_custom_view_home);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowTitleEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowCustomEnabled(true);
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowHomeEnabled(false);
        bar.setIcon(
                new ColorDrawable(getResources().getColor(android.R.color.transparent)));
    }


}
