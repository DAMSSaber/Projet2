package com.ecolemultimedia.sabermostaf.projet2.activities;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListView;

import com.ecolemultimedia.sabermostaf.projet2.R;
import com.ecolemultimedia.sabermostaf.projet2.adapters.ListFormationAdapter;
import com.ecolemultimedia.sabermostaf.projet2.components.DrawerActivity;
import com.ecolemultimedia.sabermostaf.projet2.interfaces.RequestCallbackFormation;
import com.ecolemultimedia.sabermostaf.projet2.models.Formation;
import com.ecolemultimedia.sabermostaf.projet2.services.ServiceGetFormations;
import com.ecolemultimedia.sabermostaf.projet2.utils.ListOfCategorie;
import com.ecolemultimedia.sabermostaf.projet2.utils.ListOfSubCategorie;

import java.util.ArrayList;

public class ListFormationActivity extends DrawerActivity implements RequestCallbackFormation {


    private ListView ui_lis_formation = null;
    private ArrayList<Formation> listFormation = null;
    private ListFormationAdapter adapterss = null;
    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        pos = bundle.getInt("pos");
        setupActrionBar();
        setContentView(R.layout.activity_sub_categorie);
        initDrawer();


        mListCategorie.addAll(ListOfCategorie.getInstance().getListCategorie());
        adapter.notifyDataSetChanged();

        Bundle budle = getIntent().getExtras();
        int position = budle.getInt("pos");


        ui_lis_formation = (ListView) findViewById(R.id.ui_lis_formation);

        ServiceGetFormations.getInstance().getFormation(this, ListOfSubCategorie.getInstance().getListSubCategorie().get(position).getmId());
        adapterss = new ListFormationAdapter(this, listFormation);
        ui_lis_formation.setAdapter(adapterss);

    }

    public void setupActrionBar() {
        ActionBar bar = getActionBar();
        // bar.setCustomView(R.layout.actionbar_custom_view_home);
        bar.setTitle(ListOfCategorie.getInstance().getListCategorie().get(pos).getmTitle());
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowTitleEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowCustomEnabled(true);
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowHomeEnabled(false);
        bar.setIcon(
                new ColorDrawable(getResources().getColor(android.R.color.transparent)));
    }


    @Override
    public void onGetFormationSuccess(ArrayList<Formation> listCat) {

        this.listFormation.clear();
        this.listFormation.addAll(listCat);

        adapterss.notifyDataSetChanged();

    }

    @Override
    public void onGetFormationError(String message) {

    }
}