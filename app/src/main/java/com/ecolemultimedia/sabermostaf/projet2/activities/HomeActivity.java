package com.ecolemultimedia.sabermostaf.projet2.activities;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.ecolemultimedia.sabermostaf.projet2.R;
import com.ecolemultimedia.sabermostaf.projet2.components.DrawerActivity;
import com.ecolemultimedia.sabermostaf.projet2.interfaces.RequestCallbackCategories;
import com.ecolemultimedia.sabermostaf.projet2.models.Categories;
import com.ecolemultimedia.sabermostaf.projet2.services.ServiceGetCategories;
import com.ecolemultimedia.sabermostaf.projet2.utils.ListOfCategorie;
import com.ecolemultimedia.sabermostaf.projet2.utils.Loader;
import com.ecolemultimedia.sabermostaf.projet2.views.ViewHome;

import java.util.ArrayList;

public class HomeActivity extends DrawerActivity implements RequestCallbackCategories {


    private ViewPager ui_view_pager = null;
    private ViewHome viewHome = null;
    private Loader loader = null;
    private ArrayList<Categories> listCat = null;
    public ViewPagerAdapter adapterPgaer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loader = new Loader(this);

        loader.showDialog();
        listCat = new ArrayList<Categories>();
        if(ServiceGetCategories.getInstance().getCacheCategrie()!=null){
            listCat.clear();
            listCat.addAll(ServiceGetCategories.getInstance().getCacheCategrie());
        }

        ServiceGetCategories.getInstance().getCategories(this);


        setupActrionBar();
        setContentView(R.layout.activity_home);
        initDrawer();

        ui_view_pager = (ViewPager) findViewById(R.id.ui_view_pager);
        adapterPgaer = new ViewPagerAdapter();
        ui_view_pager.setAdapter(adapterPgaer);


    }

    class ViewPagerAdapter extends PagerAdapter {

        public int getCount() {
            return listCat.size();
        }

        public Object instantiateItem(View view, int position) {

            viewHome = new ViewHome(HomeActivity.this, listCat.get(position), position, getCount());

            // invalidateOptionsMenu();
            viewHome.init();
            ((ViewPager) view).addView(viewHome);
            return viewHome;
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


    public void setupActrionBar() {
        ActionBar bar = getActionBar();
        // bar.setCustomView(R.layout.actionbar_custom_view_home);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowTitleEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowCustomEnabled(true);
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowHomeEnabled(false);
        bar.setIcon(
                new ColorDrawable(getResources().getColor(android.R.color.transparent)));
    }


    @Override
    public void onGetCategoriesSuccess(ArrayList<Categories> listCats) {
        loader.dismissDialog();

        if (mListCategorie != null) {
            mListCategorie.clear();

            this.listCat.clear();
            this.listCat.addAll(listCats);
            mListCategorie.addAll(listCats);

            ListOfCategorie.getInstance().setListcategorie(listCats);
            adapter.notifyDataSetChanged();
            adapterPgaer.notifyDataSetChanged();


        }

    }

    @Override
    public void onGetCategoriesError(String message) {
        Log.v("Debeug", "Categories error =" + message);
        loader.dismissDialog();
    }

}
