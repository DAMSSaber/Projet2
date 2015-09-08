package com.ecolemultimedia.sabermostaf.projet2.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ecolemultimedia.sabermostaf.projet2.cells.CellHistorique;
import com.ecolemultimedia.sabermostaf.projet2.cells.CellItem;
import com.ecolemultimedia.sabermostaf.projet2.models.Item;

import java.util.ArrayList;


public class HistoriqueListAdapter extends BaseAdapter {

    private ArrayList<String> listCateg = null;
    private Context context = null;

    public HistoriqueListAdapter(Context context,
                                 ArrayList<String> listCateg) {

        this.listCateg = listCateg;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listCateg.size();
    }

    @Override
    public String getItem(int position) {
        return listCateg.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String comment = listCateg.get(position);

        CellHistorique cellClass = null;

        if (convertView == null) {
            cellClass = new CellHistorique(context);
            cellClass = (CellHistorique) cellClass.init();
        } else
            cellClass = (CellHistorique) convertView;

        cellClass.reuse(comment);
        return cellClass;
    }
    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }

    @Override
    public boolean isEnabled(int arg0)
    {
        return true;
    }
}
