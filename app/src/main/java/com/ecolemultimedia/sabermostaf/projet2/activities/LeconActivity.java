package com.ecolemultimedia.sabermostaf.projet2.activities;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.ecolemultimedia.sabermostaf.projet2.R;
import com.ecolemultimedia.sabermostaf.projet2.adapters.ChapitreListAdapter;
import com.ecolemultimedia.sabermostaf.projet2.components.DrawerActivity;
import com.ecolemultimedia.sabermostaf.projet2.interfaces.RequestCallbackLecons;
import com.ecolemultimedia.sabermostaf.projet2.models.Formation;
import com.ecolemultimedia.sabermostaf.projet2.models.Item;
import com.ecolemultimedia.sabermostaf.projet2.services.ServiceGetLecons;
import com.ecolemultimedia.sabermostaf.projet2.utils.ListOfCategorie;
import com.ecolemultimedia.sabermostaf.projet2.utils.ListOfFormation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LeconActivity extends DrawerActivity implements RequestCallbackLecons {


    private int pos = 0;

    private Formation formation = null;

    private TextView ui_tx_title_lecon = null;
    private TextView ui_tx_sub_title_lecon = null;
    private TextView ui_tx_author_lecon = null;
    private TextView ui_tx_sub_content_lecon = null;
    private TextView ui_tx_lecon_time = null;
    private TextView ui_tx_lecon_price = null;
    private TextView ui_tx_lecon_note = null;
    private TextView ui_tx_lecon_date = null;
    private TextView ui_tx_prerequi_content = null;

    private ExpandableListView ui_list_chapitre=null;
    private ArrayList<Item> listItem=null;

    private ChapitreListAdapter adapterss=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        pos = bundle.getInt("position");
        formation = new Formation();
        setupActrionBar();
        setContentView(R.layout.activity_lecon);
        initDrawer();
        ServiceGetLecons.getInstance().getLecons(this, ListOfFormation.getInstance().getListFormation().get(pos).getEan13());


        mListCategorie.addAll(ListOfCategorie.getInstance().getListCategorie());
        adapter.notifyDataSetChanged();


        ui_tx_sub_title_lecon = (TextView) findViewById(R.id.ui_tx_sub_title_lecon);
        ui_tx_author_lecon = (TextView) findViewById(R.id.ui_tx_author_lecon);
        ui_tx_sub_content_lecon = (TextView) findViewById(R.id.ui_tx_sub_content_lecon);
        ui_tx_lecon_time = (TextView) findViewById(R.id.ui_tx_lecon_time);
        ui_tx_lecon_price = (TextView) findViewById(R.id.ui_tx_lecon_price);
        ui_tx_lecon_note = (TextView) findViewById(R.id.ui_tx_lecon_note);
        ui_tx_lecon_date = (TextView) findViewById(R.id.ui_tx_lecon_date);
        ui_tx_prerequi_content = (TextView) findViewById(R.id.ui_tx_prerequi_content);

        ui_list_chapitre=(ExpandableListView)findViewById(R.id.ui_list_chapitre);
        listItem= new ArrayList<Item>();

        adapterss=new ChapitreListAdapter(this, listItem);
        ui_list_chapitre.setAdapter(adapterss);


    }


    @Override
    public void onGetLeconsSuccess(Formation lecons) {


        Log.v("Debeug", "Lecon");
        formation = lecons;
        if (formation != null) {


            if (formation.getSubtitle() != null) {
                ui_tx_sub_title_lecon.setText(formation.getSubtitle());
            }
            ui_tx_author_lecon.setText("Auteur(s) : Alain Beauvois");

            if (formation.getDescription() != null) {
                ui_tx_sub_content_lecon.setText(formation.getDescription());
            }
            if (formation.getPublishedDate() != null) {

                Date MyDate;
                try {
                    SimpleDateFormat newDateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd");
                    MyDate = newDateFormat.parse(formation.getPublishedDate().substring(0, 10));

                    String myDateTwo = newDateFormat.format(MyDate);
                    ui_tx_lecon_date.setText("" + myDateTwo);

                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (formation.getPrice() != null) {


                Double val = Double.parseDouble(formation.getPrice());

                if (formation.getPrice() == null) {
                    ui_tx_lecon_price.setText("Gratuit");
                } else {

                    ui_tx_lecon_price.setText("" + Math.round(val) + " €");
                }
            }

            if (formation.getRating() != null) {
                ui_tx_lecon_note.setText("" + formation.getRating() + " / 100");
            }

            if (formation.getDuration() != null) {

                int value = Integer.parseInt(formation.getDuration());

                ui_tx_lecon_time.setText(getDurationString(value));
            }


            if (formation.getPrerequisites() != null) {
                ui_tx_prerequi_content.setText("");
            }

            if(formation.getListItem()!=null){
                listItem.addAll(formation.getListItem());
                adapterss.notifyDataSetChanged();
            }


        }


    }

    private String getDurationString(int seconds) {

        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;

        return twoDigitString(hours) + " : " + twoDigitString(minutes) + " : " + twoDigitString(seconds);
    }

    private String twoDigitString(int number) {

        if (number == 0) {
            return "00";
        }

        if (number / 10 == 0) {
            return "0" + number;
        }

        return String.valueOf(number);
    }

    @Override
    public void onGetLeconsError(String message) {

    }


    public void setupActrionBar() {
        ActionBar bar = getActionBar();
        //  bar.setCustomView(R.layout.actionbar_custom_view_home);
        //  bar.setTitle(ListOfCategorie.getInstance().getListCategorie().get(pos).getmTitle());
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
