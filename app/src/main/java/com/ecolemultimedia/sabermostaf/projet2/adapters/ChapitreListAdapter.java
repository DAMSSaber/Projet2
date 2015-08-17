package com.ecolemultimedia.sabermostaf.projet2.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import com.ecolemultimedia.sabermostaf.projet2.cells.CellChapitreChild;
import com.ecolemultimedia.sabermostaf.projet2.cells.CellChapitreParent;
import com.ecolemultimedia.sabermostaf.projet2.models.Formation;
import com.ecolemultimedia.sabermostaf.projet2.models.Item;

import java.util.ArrayList;

public class ChapitreListAdapter extends BaseExpandableListAdapter {

    private Context context = null;
    private ArrayList<Item> item = null;

    public ChapitreListAdapter(Context context,
                              ArrayList<Item> item) {

        this.context = context;
        this.item = item;

    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        CellChapitreParent cellParents = null;
        final String groupContent = item.get(groupPosition).getTitle();

        if (convertView == null) {
            cellParents = new CellChapitreParent(context);
            cellParents = (CellChapitreParent) cellParents.init();
        } else {
            cellParents = (CellChapitreParent) convertView;
        }
        cellParents.reuse(groupContent);
        ExpandableListView mExpandableListView = (ExpandableListView) parent;
        //mExpandableListView.expandGroup(groupPosition);

        return cellParents;

    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final Item childContent = (Item)item.get(groupPosition).getListItem().get(childPosition);

        CellChapitreChild cellChild = null;
        if (convertView == null) {
            cellChild = new CellChapitreChild(context);
            cellChild = (CellChapitreChild) cellChild.init();
        } else {

            cellChild = (CellChapitreChild) convertView;
        }

        cellChild.reuse(childContent);

        return cellChild;
    }

    @Override
    public Item getChild(int groupPosition, int childPosition) {
        return (Item) item.get(groupPosition).getListItem().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return item.get(groupPosition).getListItem().size();
    }

    @Override
    public String getGroup(int groupPosition) {
        return item.get(groupPosition).getTitle();
    }

    @Override
    public int getGroupCount() {
        return item.size();
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
