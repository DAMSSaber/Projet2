package com.ecolemultimedia.sabermostaf.projet2.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import com.ecolemultimedia.sabermostaf.projet2.cells.CellDrawerChild;
import com.ecolemultimedia.sabermostaf.projet2.cells.CellDrawerParent;
import com.ecolemultimedia.sabermostaf.projet2.models.Categories;

public class DrawerListAdapter extends BaseExpandableListAdapter {

	private Context context = null;
	private ArrayList<Categories> mListCategorie = null;

	public DrawerListAdapter(Context context,
			ArrayList<Categories> listCategorie) {

		this.context = context;
		this.mListCategorie = listCategorie;

	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		CellDrawerParent cellParents = null;
		final String groupContent = mListCategorie.get(groupPosition).getmTitle();

		if (convertView == null) {
			cellParents = new CellDrawerParent(context);
			cellParents = (CellDrawerParent) cellParents.init();
		} else {
			cellParents = (CellDrawerParent) convertView;
		}
		cellParents.reuse(groupContent);
		ExpandableListView mExpandableListView = (ExpandableListView) parent;
		//mExpandableListView.expandGroup(groupPosition);

		return cellParents;

	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final String childContent = this.mListCategorie.get(groupPosition)
				.getmListSubCategorie().get(childPosition).getmTitle();

		CellDrawerChild cellChild = null;
		if (convertView == null) {
			cellChild = new CellDrawerChild(context);
			cellChild = (CellDrawerChild) cellChild.init();
		} else {

			cellChild = (CellDrawerChild) convertView;
		}

		cellChild.reuse(childContent);

		return cellChild;
	}

	@Override
	public String getChild(int groupPosition, int childPosition) {
		return this.mListCategorie.get(groupPosition).getmListSubCategorie()
				.get(childPosition).getmTitle();
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.mListCategorie.get(groupPosition).getmListSubCategorie().size();
	}

	@Override
	public String getGroup(int groupPosition) {
		return this.mListCategorie.get(groupPosition).getmTitle();
	}

	@Override
	public int getGroupCount() {
		return mListCategorie.size();
	}

	@Override
	public void onGroupCollapsed(int groupPosition) {
		super.onGroupCollapsed(groupPosition);
	}

	@Override
	public void onGroupExpanded(int groupPosition) {
		super.onGroupExpanded(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

}
