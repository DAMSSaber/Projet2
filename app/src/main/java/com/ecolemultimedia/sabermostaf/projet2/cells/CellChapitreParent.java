package com.ecolemultimedia.sabermostaf.projet2.cells;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecolemultimedia.sabermostaf.projet2.R;


public class CellChapitreParent extends RelativeLayout {

    private TextView ui_tx_title_chapitre = null;


    private LayoutInflater layoutInflater = null;
    private Context m_context = null;

    public CellChapitreParent(Context context) {
        super(context);
        m_context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public View init() {

        View convertView = layoutInflater.inflate(R.layout.cell_chapitre_parent,
                this);

        ui_tx_title_chapitre = (TextView) convertView
                .findViewById(R.id.ui_tx_title_chapitre);

        Typeface style = Typeface.createFromAsset(m_context.getAssets(),
                "Gotham/Gotham-Bold.otf");

        ui_tx_title_chapitre.setTypeface(style);


        return this;

    }

    public void reuse(String info) {

        cleanView();
        if (info != null) {

            ui_tx_title_chapitre.setText(info);

        }
    }

    public void cleanView() {

        ui_tx_title_chapitre.setText("");

    }
}