package com.ecolemultimedia.sabermostaf.projet2.components;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.ecolemultimedia.sabermostaf.projet2.R;
import com.ecolemultimedia.sabermostaf.projet2.activities.HistoryActivity;
import com.ecolemultimedia.sabermostaf.projet2.activities.ListFormationActivity;
import com.ecolemultimedia.sabermostaf.projet2.adapters.DrawerListAdapter;
import com.ecolemultimedia.sabermostaf.projet2.models.Categories;

import java.util.ArrayList;


public class DrawerActivity extends FragmentActivity {

    public static final String TAG = DrawerActivity.class.getSimpleName();
    protected DrawerLayout ui_drawerLayout = null;
    private ExpandableListView ui_drawerList = null;
    public DrawerListAdapter adapter = null;
    public ArrayList<Categories> mListCategorie = null;
    protected ActionBarDrawerToggle m_actionBarDrawerToggle = null;


    private String androidId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void initDrawer() {

        androidId = Secure.getString(this.getContentResolver(),
                Secure.ANDROID_ID);

        mListCategorie = new ArrayList<Categories>();
        ui_drawerLayout = (DrawerLayout) findViewById(R.id.ui_drawer_layout);

        ui_drawerList = (ExpandableListView) findViewById(R.id.ui_list_left_drawer);


        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header_drawer, ui_drawerList, false);

        TextView tx = (TextView) header.findViewById(R.id.tx);

        Typeface style = Typeface.createFromAsset(this.getAssets(),
                "Gotham/Gotham-Bold.otf");
        tx.setTypeface(style);
        ui_drawerList.addHeaderView(header, null, false);

        adapter = new DrawerListAdapter(this, mListCategorie);
        ui_drawerList.setAdapter(adapter);


        ui_drawerList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Intent intent = new Intent(DrawerActivity.this, ListFormationActivity.class);
                intent.putExtra("poschild", childPosition);
                intent.putExtra("posGroup", groupPosition);
                startActivity(intent);

                return false;
            }
        });


        ui_drawerList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            // Keep track of previous expanded parent
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                // Collapse previous parent if expanded.
                if ((previousGroup != -1) && (groupPosition != previousGroup)) {
                    ui_drawerList.collapseGroup(previousGroup);
                }
                previousGroup = groupPosition;
            }
        });


        m_actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                ui_drawerLayout, R.mipmap.drawer,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);


                //invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);


                //invalidateOptionsMenu();
            }
        };

        ui_drawerLayout.setDrawerListener(m_actionBarDrawerToggle);
        ui_drawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_hitory, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (m_actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_search:



                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        m_actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        m_actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();

    }

}
