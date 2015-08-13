package com.ecolemultimedia.sabermostaf.projet2.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ecolemultimedia.sabermostaf.projet2.cells.CellFormation;
import com.ecolemultimedia.sabermostaf.projet2.cells.CellSubCategorie;
import com.ecolemultimedia.sabermostaf.projet2.models.Categories;
import com.ecolemultimedia.sabermostaf.projet2.models.Formation;

import java.util.ArrayList;


public class ListFormationAdapter extends BaseAdapter {

	private ArrayList<Formation> listFormation = null;
	private Context context = null;

	public ListFormationAdapter(Context context,
								ArrayList<Formation> listFormation) {

		this.listFormation = listFormation;
		this.context = context;
	}

	@Override
	public int getCount() {
		return listFormation.size();
	}

	@Override
	public Formation getItem(int position) {
		return listFormation.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Formation comment = listFormation.get(position);

		CellFormation cellClass = null;

		if (convertView == null) {
			cellClass = new CellFormation(context);
			cellClass = (CellFormation) cellClass.init();
		} else
			cellClass = (CellFormation) convertView;

		cellClass.reuse(comment);
		return cellClass;
	}

}
