package com.ecolemultimedia.sabermostaf.projet2.cells;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecolemultimedia.sabermostaf.projet2.R;
import com.ecolemultimedia.sabermostaf.projet2.models.Categories;

import org.jsoup.Jsoup;


public class CellSubCategorie extends RelativeLayout {

    private TextView ui_tx_sub_title_sub_cat = null;
    private TextView  ui_tx_sub_content_sub_cat= null;

    private LayoutInflater layoutInflater = null;
    private Context m_context = null;

    public CellSubCategorie(Context context) {
        super(context);
        m_context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public View init() {

        View convertView = layoutInflater.inflate(R.layout.cell_sub_categorie,
                this);

        ui_tx_sub_title_sub_cat = (TextView) convertView
                .findViewById(R.id.ui_tx_sub_title_sub_cat);

        ui_tx_sub_content_sub_cat = (TextView) convertView
                .findViewById(R.id.ui_tx_sub_content_sub_cat);


        Typeface style = Typeface.createFromAsset(m_context.getAssets(),
                "corbel.otf");

        ui_tx_sub_title_sub_cat.setTypeface(style);

        return this;

    }

    public void reuse(Categories info) {

        cleanView();
        if (info != null) {

            ui_tx_sub_title_sub_cat.setText(info.getmTitle());



            if(info.getmDescription()!=null) {
                String string =info.getmDescription().substring(0, info.getmDescription().length() / 3);


                ui_tx_sub_content_sub_cat.setText(Html.fromHtml(string));
            }

        }
    }

    public void cleanView() {
        ui_tx_sub_content_sub_cat.setText("");
        ui_tx_sub_title_sub_cat.setText("");


    }

}