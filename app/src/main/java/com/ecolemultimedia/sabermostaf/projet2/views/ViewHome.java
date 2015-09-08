package com.ecolemultimedia.sabermostaf.projet2.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Html;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecolemultimedia.sabermostaf.projet2.R;
import com.ecolemultimedia.sabermostaf.projet2.activities.SubCategorieActivity;
import com.ecolemultimedia.sabermostaf.projet2.models.Categories;
import com.ecolemultimedia.sabermostaf.projet2.utils.ListOfCategorie;
import com.ecolemultimedia.sabermostaf.projet2.utils.ListOfSubCategorie;
import com.ecolemultimedia.sabermostaf.projet2.utils.Loader;

import org.jsoup.Jsoup;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class ViewHome extends RelativeLayout {


    private LayoutInflater layoutInflater = null;
    private Categories cat = null;
    private TextView ui_tx_title_pager_title = null;
    private RelativeLayout ui_tx_title_pager_description = null;

    private ImageView ui_img_pager_logo = null;


    private RelativeLayout ui_rl_view = null;
    private Context m_context = null;
    private int position = 0;
    private int size=0;

    public ViewHome(Context context, Categories categorie, int position,int size) {
        super(context);
        m_context = context;
        layoutInflater = LayoutInflater.from(context);
        this.cat = categorie;
        this.position = position;
        this.size=size;

    }

    public ViewHome(Context context) {
        super(context);
    }

    public View init() {


        layoutInflater = (LayoutInflater) m_context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View convertView = layoutInflater.inflate(
                R.layout.view_home_pager, this);

        ui_rl_view = (RelativeLayout) convertView.findViewById(R.id.ui_rl_view);

        ui_rl_view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(m_context,
                        SubCategorieActivity.class);
                ListOfSubCategorie.getInstance().setListSubcategorie(cat.getmListSubCategorie());

                intent.putExtra("pos",position);
                m_context.startActivity(intent);

            }
        });


        ui_tx_title_pager_title = (TextView) convertView.findViewById(R.id.ui_tx_title_pager_title);
        ui_tx_title_pager_description = (RelativeLayout) convertView.findViewById(R.id.ui_tx_title_pager_description);
        ui_img_pager_logo = (ImageView) convertView.findViewById(R.id.ui_img_pager_logo);

        if (cat.getmTitle() != null) {
            if (cat.getmTitle().equals("Vidéo-Compositing")) {
                ui_img_pager_logo.setImageResource(R.drawable.video);
            } else if (cat.getmTitle().equals("Code")) {

                ui_img_pager_logo.setImageResource(R.drawable.code);
            } else if (cat.getmTitle().equals("Audio-MAO")) {

                ui_img_pager_logo.setImageResource(R.drawable.audio);
            } else if (cat.getmTitle().equals("Infographie")) {

                ui_img_pager_logo.setImageResource(R.drawable.infographie);
            } else if (cat.getmTitle().equals("Web-Multimédia")) {

                ui_img_pager_logo.setImageResource(R.drawable.web_multimedia);
            } else if (cat.getmTitle().equals("Informatique")) {

                ui_img_pager_logo.setImageResource(R.drawable.informatique);
            } else if (cat.getmTitle().equals("Photographie")) {

                ui_img_pager_logo.setImageResource(R.drawable.photographie);
            } else if (cat.getmTitle().equals("Business")) {

                ui_img_pager_logo.setImageResource(R.drawable.business);
            } else if (cat.getmTitle().equals("3D")) {

                ui_img_pager_logo.setImageResource(R.drawable.troisd);
            } else if (cat.getmTitle().equals("Bureautique")) {

                ui_img_pager_logo.setImageResource(R.drawable.bureautique);
            }



        }

        if (cat.getmTitle() != null) {
            ui_tx_title_pager_title.setText(cat.getmTitle());
        }

        if (cat.getmDescription() != null) {

            WebView webview=new WebView(m_context);
            webview.setVerticalScrollBarEnabled(false);
            WebSettings settings = webview.getSettings();
            settings.setDefaultTextEncodingName("utf-8");
            webview.setBackgroundColor(Color.TRANSPARENT);

            String string =cat.getmDescription().substring(0,cat.getmDescription().length()/3);

            String html="<html>\n" +
                    " <head></head>\n" +
                    " <body style=\"text-align:justify;color:white;background-color:#222224;\">\n"+string+"..."
                    +" </body>\n" +
                    "</html>";

            webview.loadData(html, "text/html; charset=utf-8", "utf-8");
            ui_tx_title_pager_description.addView(webview);
        }



        return convertView;

    }

    public WebView settingWebView(WebView web) {

        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
        web.setScrollbarFadingEnabled(true);
        web.setVerticalScrollBarEnabled(false);
        web.setHorizontalScrollBarEnabled(false);
        web.getSettings().setDefaultTextEncodingName("utf-8");
        web.setInitialScale(200);
        web.clearHistory();
        web.clearFormData();
        web.clearCache(true);
        WebSettings webSettings = web.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDisplayZoomControls(false);

        return web;
    }


}
