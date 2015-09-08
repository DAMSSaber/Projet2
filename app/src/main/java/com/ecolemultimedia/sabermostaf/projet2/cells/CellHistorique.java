package com.ecolemultimedia.sabermostaf.projet2.cells;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.ecolemultimedia.sabermostaf.projet2.R;
import com.ecolemultimedia.sabermostaf.projet2.components.AppController;
import com.ecolemultimedia.sabermostaf.projet2.models.Item;
import com.ecolemultimedia.sabermostaf.projet2.views.ViewPagerItem;

import java.util.ArrayList;


public class CellHistorique extends RelativeLayout {

    private TextView ui_tx_title_history = null;

    private LayoutInflater layoutInflater = null;
    private Context m_context = null;

    public CellHistorique(Context context) {
        super(context);
        m_context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public View init() {

        View convertView = layoutInflater.inflate(R.layout.cell_history,
                this);

        ui_tx_title_history = (TextView) convertView
                .findViewById(R.id.ui_tx_title_history);
        Typeface style = Typeface.createFromAsset(m_context.getAssets(),
                "corbel.otf");

        ui_tx_title_history.setTypeface(style);


        return this;

    }

    public void reuse(String info) {

        cleanView();
        if (info != null) {
            ui_tx_title_history.setText(info);
        }


    }

    public void cleanView() {

        ui_tx_title_history.setText("");
    }



}