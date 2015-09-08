package com.ecolemultimedia.sabermostaf.projet2.cells;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecolemultimedia.sabermostaf.projet2.R;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class CellDrawerChild extends RelativeLayout {

    private TextView ui_tx_title_sub_cat = null;


    private LayoutInflater layoutInflater = null;
    private Context m_context = null;

    public CellDrawerChild(Context context) {
        super(context);
        m_context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public View init() {

        View convertView = layoutInflater.inflate(R.layout.cell_drawer_child,
                this);

        ui_tx_title_sub_cat = (TextView) convertView
                .findViewById(R.id.ui_tx_title_sub_cat);

        Typeface style = Typeface.createFromAsset(m_context.getAssets(),
                "corbel.otf");

        ui_tx_title_sub_cat.setTypeface(style);

        return this;

    }

    public void reuse(String info) {

        cleanView();
        if (info != null) {
            String parse=null;
            try {
                parse= URLDecoder.decode(info, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            ui_tx_title_sub_cat.setText(parse);



        }
    }

    public void cleanView() {

        ui_tx_title_sub_cat.setText("");


    }

}