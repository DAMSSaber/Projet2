package com.ecolemultimedia.sabermostaf.projet2.cells;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecolemultimedia.sabermostaf.projet2.R;
import com.ecolemultimedia.sabermostaf.projet2.models.Formation;


public class CellFormation extends RelativeLayout {

    private TextView ui_tx_title_formation = null;
    private TextView  ui_tx_sub_title_formation= null;

    private TextView   ui_tx_sub_content_formation=null;

    private LayoutInflater layoutInflater = null;
    private Context m_context = null;

    public CellFormation(Context context) {
        super(context);
        m_context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public View init() {

        View convertView = layoutInflater.inflate(R.layout.cell_formation,
                this);

        ui_tx_title_formation = (TextView) convertView
                .findViewById(R.id.ui_tx_title_formation);

        ui_tx_sub_title_formation = (TextView) convertView
                .findViewById(R.id.ui_tx_sub_title_formation);
        ui_tx_sub_content_formation= (TextView) convertView
                .findViewById(R.id.ui_tx_sub_content_formation);

        Typeface style = Typeface.createFromAsset(m_context.getAssets(),
                "corbel.otf");

        ui_tx_title_formation.setTypeface(style);

        return this;

    }

    public void reuse(Formation info) {

        cleanView();
        if (info != null) {

          /*ui_tx_sub_title_sub_cat.setText(info.getmTitle());
            String doc = Jsoup.parse(info.getmDescription()).text();
            ui_tx_sub_content_sub_cat.setText(doc);*/

        }
    }

    public void cleanView() {
        ui_tx_title_formation.setText("");
        ui_tx_sub_title_formation.setText("");

        ui_tx_sub_content_formation.setText("");
    }

}