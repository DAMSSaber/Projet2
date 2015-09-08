package com.ecolemultimedia.sabermostaf.projet2.activities;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecolemultimedia.sabermostaf.projet2.R;
import com.ecolemultimedia.sabermostaf.projet2.components.DrawerActivity;
import com.ecolemultimedia.sabermostaf.projet2.models.Formation;
import com.ecolemultimedia.sabermostaf.projet2.models.Item;
import com.ecolemultimedia.sabermostaf.projet2.utils.ListOfCategorie;
import com.ecolemultimedia.sabermostaf.projet2.utils.ListOfFormation;
import com.ecolemultimedia.sabermostaf.projet2.utils.MyShareAction;
import com.ecolemultimedia.sabermostaf.projet2.views.ViewPagerItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeconActivity extends DrawerActivity {


    private int pos = 0;

    private Formation formation = null;
    private TextView ui_tx_title_lecon = null;
    private TextView ui_tx_sub_title_lecon = null;
    private TextView ui_tx_author_lecon = null;
    private RelativeLayout ui_tx_sub_content_lecon = null;
    private TextView ui_tx_nb_lecon = null;
    private TextView ui_tx_lecon_time = null;
    private TextView ui_tx_lecon_price = null;
    private TextView ui_tx_lecon_note = null;
    private TextView ui_tx_lecon_date = null;
    private TextView ui_tx_prerequi_content = null;
    private ViewPager ui_view_pager_item = null;
    private ListView ui_list_chapitre = null;
    private ViewPagerItem viewPagerItem = null;
    private ArrayList<Item> listItem = null;
    private MyShareAction mShareActionProvider = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        pos = bundle.getInt("position");


        formation = new Formation();
        setupActrionBar();
        setContentView(R.layout.activity_lecon);
        initDrawer();
        // ServiceGetLecons.getInstance().getLecons(this, ListOfFormation.getInstance().getListFormation().get(pos).getEan13());

        formation = ListOfFormation.getInstance().getListFormation().get(pos);
        mListCategorie.addAll(ListOfCategorie.getInstance().getListCategorie());


        ui_tx_sub_title_lecon = (TextView) findViewById(R.id.ui_tx_sub_title_lecon);
        ui_tx_author_lecon = (TextView) findViewById(R.id.ui_tx_author_lecon);
        ui_tx_sub_content_lecon = (RelativeLayout) findViewById(R.id.ui_tx_sub_content_lecon);
        ui_tx_lecon_time = (TextView) findViewById(R.id.ui_tx_lecon_time);
        ui_tx_lecon_price = (TextView) findViewById(R.id.ui_tx_lecon_price);
        ui_tx_lecon_note = (TextView) findViewById(R.id.ui_tx_lecon_note);
        ui_tx_lecon_date = (TextView) findViewById(R.id.ui_tx_lecon_date);
        ui_view_pager_item = (ViewPager) findViewById(R.id.ui_view_pager_item);
        ui_tx_nb_lecon = (TextView) findViewById(R.id.ui_tx_nb_lecon);


        listItem = new ArrayList<Item>();


        if (formation != null) {


            if (formation.getSubtitle() != null) {
                ui_tx_sub_title_lecon.setText(formation.getSubtitle());
            }
            ui_tx_author_lecon.setText("Auteur(s) : Alain Beauvois");

            if (formation.getDescription() != null) {

                WebView webview = new WebView(this);
                webview.setVerticalScrollBarEnabled(false);
                WebSettings settings = webview.getSettings();
                settings.setDefaultTextEncodingName("utf-8");
                webview.setBackgroundColor(Color.TRANSPARENT);

                String html = "<html>\n" +
                        " <head></head>\n" +
                        " <body style=\"text-align:justify;color:white;background-color:#222224;\">\n" + formation.getDescription()
                        + " </body>\n" +
                        "</html>";

                webview.loadData(html, "text/html; charset=utf-8", "utf-8");
                ui_tx_sub_content_lecon.addView(webview);
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

            if (formation.getListItem() != null) {
                if (formation.getListItem() != null) {

                    Log.v("Debeug", "List  size test " + formation.getListItem().size());
                    listItem.addAll(formation.getListItem());
                    ViewPagerAdapter adapters = new ViewPagerAdapter();
                    ui_view_pager_item.setAdapter(adapters);
                    ui_tx_nb_lecon.setText("Nombre de leçons : " + formation.getListItem().size());
                }

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

    class ViewPagerAdapter extends PagerAdapter {
        public int getCount() {
            return listItem.size();
        }

        public Object instantiateItem(View view, int position) {


            viewPagerItem = null;

            viewPagerItem = new ViewPagerItem(LeconActivity.this, listItem, position);
            viewPagerItem.init();
            ((ViewPager) view).addView(viewPagerItem);
            return viewPagerItem;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == ((View) arg1);
        }

        @Override
        public Parcelable saveState() {
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_webview, menu);
        MenuItem item = menu.findItem(R.id.action_share);
        mShareActionProvider = new MyShareAction(this);
        mShareActionProvider.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);
        mShareActionProvider.setShareIntent(createShareIntent());
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        mShareActionProvider.setShareIntent(createShareIntent());

        return super.onPrepareOptionsMenu(menu);
    }


    private Intent createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, formation.getProduct_url());
        return shareIntent;
    }


}
