package com.ecolemultimedia.sabermostaf.projet2.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.ecolemultimedia.sabermostaf.projet2.R;
import com.ecolemultimedia.sabermostaf.projet2.components.AppController;
import com.ecolemultimedia.sabermostaf.projet2.models.Item;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by saberdams on 10/07/15.
 */
public class ViewPagerItem extends RelativeLayout {

    private TextView ui_tx_title_item_pager = null;

    private LayoutInflater layoutInflater = null;
    private Context m_context = null;
    private Activity activity = null;
    private RelativeLayout ui_rl_content_item = null;
    private ImageView ui_img_sub_content_videon = null;
    private Item cour = null;
    private ArrayList<Item> listItem = null;
    private int position = 0;
    private String cat = null;

    private static final String PREFS_TAG = "SharedPrefs";
    private static final String ITEM_TAG = "MyProduct";
    public ViewPagerItem(Context context, ArrayList<Item> listFormat, int position) {
        super(context);
        m_context = context;
        this.position = position;
        this.listItem = listFormat;
        this.cat = cat;
        layoutInflater = LayoutInflater.from(context);
    }

    public View init() {

        View convertView = layoutInflater
                .inflate(R.layout.cell_pager_item, this);
        ui_rl_content_item = (RelativeLayout) convertView.findViewById(R.id.ui_rl_content_item);
        ui_img_sub_content_videon = (ImageView) convertView.findViewById(R.id.ui_img_sub_content_videon);


        if (listItem != null) {
            cour = listItem.get(position);
        }

        if (cour.getField_poster() != null) {
            ImageLoader imageLoader = AppController.getInstance()
                    .getImageLoader();

            imageLoader.get(cour.getField_poster(), new ImageLoader.ImageListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("debeug",
                            "Image Load Error: " + error.getMessage());
                }

                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                    if (response.getBitmap() != null) {

                        ui_img_sub_content_videon.setImageBitmap(response.getBitmap());
                    }
                }
            });
        }

        ui_rl_content_item.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cour.getField_video() != null) {

                    SharedPreferences sharedPref = m_context.getApplicationContext().getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    String jsonPreferences = sharedPref.getString(ITEM_TAG, "");
                    editor.putString("name", cour.getTitle());
                    editor.commit();


                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW);

                        Uri data = Uri.parse(cour.getField_video());

                    intent.setDataAndType(data, "video/mp4");
                    m_context.startActivity(intent);
                }
            }
        });

        ui_tx_title_item_pager = (TextView) convertView.findViewById(R.id.ui_tx_title_item_pager);
        ui_tx_title_item_pager.setText(cour.getTitle());


        return this;

    }



}
