package com.ecolemultimedia.sabermostaf.projet2.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.ecolemultimedia.sabermostaf.projet2.R;
import com.ecolemultimedia.sabermostaf.projet2.adapters.ListSubCategorieAdapter;
import com.ecolemultimedia.sabermostaf.projet2.components.DrawerActivity;
import com.ecolemultimedia.sabermostaf.projet2.models.Categories;
import com.ecolemultimedia.sabermostaf.projet2.utils.ListOfCategorie;
import com.ecolemultimedia.sabermostaf.projet2.utils.ListOfSubCategorie;

import java.util.ArrayList;

public class SubCategorieActivity extends DrawerActivity implements AdapterView.OnItemClickListener {


    private ListView ui_lis_sub_cat = null;
    private ArrayList<Categories> listCat = null;
    private ListSubCategorieAdapter adapterss = null;
    private Button ui_tx_sub_btn_history = null;
    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        pos = bundle.getInt("pos");
        setupActrionBar();
        setContentView(R.layout.activity_sub_categorie);
        initDrawer();
        ui_tx_sub_btn_history = (Button) findViewById(R.id.ui_tx_sub_btn_history);

        ui_tx_sub_btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubCategorieActivity.this,
                        HistoryActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        mListCategorie.addAll(ListOfCategorie.getInstance().getListCategorie());
        adapter.notifyDataSetChanged();


        ui_lis_sub_cat = (ListView) findViewById(R.id.ui_lis_sub_cat);
        adapterss = new ListSubCategorieAdapter(this, ListOfSubCategorie.getInstance().getListSubCategorie());
        ui_lis_sub_cat.setAdapter(adapterss);


        Log.v("Debeug", "Houmani");

        ui_lis_sub_cat.setOnItemClickListener(this);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.v("Debeug", "Click");
        Intent intent = new Intent(SubCategorieActivity.this, ListFormationActivity.class);
        intent.putExtra("poschild", position);
        intent.putExtra("posGroup", pos);
        startActivity(intent);
    }
}
