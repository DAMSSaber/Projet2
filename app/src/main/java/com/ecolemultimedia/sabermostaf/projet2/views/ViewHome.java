package com.ecolemultimedia.sabermostaf.projet2.views;

import android.content.Context;
import android.content.Intent;
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

import org.jsoup.Jsoup;


public class ViewHome extends RelativeLayout {


    private LayoutInflater layoutInflater = null;
    private Categories cat = null;
    private TextView ui_tx_title_pager_title = null;
    private TextView ui_tx_title_pager_description = null;

    private ImageView ui_img_pager_logo = null;
    private ImageView ui_img_pager_arrow_left = null;

    private ImageView ui_img_pager_arrow_right = null;
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
        ui_tx_title_pager_description = (TextView) convertView.findViewById(R.id.ui_tx_title_pager_description);
        ui_img_pager_arrow_left = (ImageView) convertView.findViewById(R.id.ui_img_pager_arrow_left);
        ui_img_pager_arrow_right = (ImageView) convertView.findViewById(R.id.ui_img_pager_arrow_right);
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
            String doc = Jsoup.parse(cat.getmDescription()).text();
            ui_tx_title_pager_description.setText(doc);
        }


        if (position == 0) {
            ui_img_pager_arrow_left.setVisibility(View.GONE);
            ui_img_pager_arrow_right.setVisibility(View.VISIBLE);
        } else if (position ==size-1) {
            ui_img_pager_arrow_left.setVisibility(View.VISIBLE);
            ui_img_pager_arrow_right.setVisibility(View.GONE);
        } else {
            ui_img_pager_arrow_left.setVisibility(View.VISIBLE);
            ui_img_pager_arrow_right.setVisibility(View.VISIBLE);
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
