package com.ecolemultimedia.sabermostaf.projet2.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ecolemultimedia.sabermostaf.projet2.cells.CellSubCategorie;
import com.ecolemultimedia.sabermostaf.projet2.models.Categories;


public class ListSubCategorieAdapter extends BaseAdapter {

	private ArrayList<Categories> listCateg = null;
	private Context context = null;

	public ListSubCategorieAdapter(Context context,
								   ArrayList<Categories> listCateg) {

		this.listCateg = listCateg;
		this.context = context;
	}

	@Override
	public int getCount() {
		return listCateg.size();
	}

	@Override
	public Categories getItem(int position) {
		return listCateg.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Categories comment = listCateg.get(position);

		CellSubCategorie cellClass = null;

		if (convertView == null) {
			cellClass = new CellSubCategorie(context);
			cellClass = (CellSubCategorie) cellClass.init();
		} else
			cellClass = (CellSubCategorie) convertView;

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
