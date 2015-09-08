package com.ecolemultimedia.sabermostaf.projet2.cells;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecolemultimedia.sabermostaf.projet2.R;
import com.ecolemultimedia.sabermostaf.projet2.models.Item;
import com.ecolemultimedia.sabermostaf.projet2.views.ViewPagerItem;

import java.util.ArrayList;


public class CellItem extends RelativeLayout {

    private TextView ui_tx_title_item = null;

    private ViewPagerItem viewPagerItem=null;
    private ViewPagerAdapter adapter=null;
    private LayoutInflater layoutInflater = null;
    private Context m_context = null;
    private ArrayList<Item> listItem=null;
    private ViewPager ui_view_pager_item=null;
    public CellItem(Context context) {
        super(context);
        m_context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public View init() {

        View convertView = layoutInflater.inflate(R.layout.cell_item,
                this);

        ui_tx_title_item = (TextView) convertView
                .findViewById(R.id.ui_tx_title_item);

        Typeface style = Typeface.createFromAsset(m_context.getAssets(),
                "corbel.otf");

        ui_tx_title_item.setTypeface(style);

       // ui_view_pager_item=(ViewPager)convertView.findViewById(R.id.ui_view_pager_item);

        return this;

    }

    public void reuse(Item info) {

        cleanView();
        if (info != null) {

            ui_tx_title_item.setText(info.getTitle());

        }
        listItem=new ArrayList<>();
        if(info.getListItem()!=null){


            for(int i=0; i<info.getListItem().size();)

            listItem.clear();
            listItem.addAll(info.getListItem());
            adapter=new ViewPagerAdapter();
            ui_view_pager_item.setPageMargin(-200);
            ui_view_pager_item.setHorizontalFadingEdgeEnabled(true);
            ui_view_pager_item.setFadingEdgeLength(60);
            ui_view_pager_item.setAdapter(adapter);
        }
    }

    public void cleanView() {

        ui_tx_title_item.setText("");
    }

    class ViewPagerAdapter extends PagerAdapter {
        public int getCount() {
            return listItem.size();
        }

        public Object instantiateItem(View view, int position) {

            viewPagerItem = null;

            viewPagerItem = new ViewPagerItem(m_context,listItem,position);
            viewPagerItem.init();
            ((ViewPager) view).addView(viewPagerItem);
            return viewPagerItem;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == ((View) arg1);
        }

        @Override
        public Parcelable saveState() {
            return null;
        }
    }


}