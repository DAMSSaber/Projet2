package com.ecolemultimedia.sabermostaf.projet2.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ecolemultimedia.sabermostaf.projet2.cells.CellItem;
import com.ecolemultimedia.sabermostaf.projet2.cells.CellSubCategorie;
import com.ecolemultimedia.sabermostaf.projet2.models.Categories;
import com.ecolemultimedia.sabermostaf.projet2.models.Item;


public class ChapiteListAdapter extends BaseAdapter {

    private ArrayList<Item> listCateg = null;
    private Context context = null;

    public ChapiteListAdapter(Context context,
                                   ArrayList<Item> listCateg) {

        this.listCateg = listCateg;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listCateg.size();
    }

    @Override
    public Item getItem(int position) {
        return listCateg.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Item comment = listCateg.get(position);

        CellItem cellClass = null;

        if (convertView == null) {
            cellClass = new CellItem(context);
            cellClass = (CellItem) cellClass.init();
        } else
            cellClass = (CellItem) convertView;

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
