package com.ecolemultimedia.sabermostaf.projet2.services;

import com.android.volley.Cache;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.ecolemultimedia.sabermostaf.projet2.components.AppController;
import com.ecolemultimedia.sabermostaf.projet2.interfaces.RequestCallbackCategories;
import com.ecolemultimedia.sabermostaf.projet2.models.Categories;
import com.ecolemultimedia.sabermostaf.projet2.models.Formation;
import com.ecolemultimedia.sabermostaf.projet2.utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by saber on 10/08/15.
 */
public class ServiceGetCategories {
    public static final String TAG = ServiceGetCategories.class.getSimpleName();
    private static ServiceGetCategories ourInstance = new ServiceGetCategories();
    private String tag_json_arry = "tag_json_arry";
    private static ServiceGetCategories mInstance = null;
    private JSONObject month = null;

    private ArrayList<Categories> listCategorie = null;
    private Categories categ = null;


    public static ServiceGetCategories getInstance() {
        if (ourInstance == null) {
            ourInstance = new ServiceGetCategories();
        }

        return ourInstance;
    }

    public ArrayList<Categories> getCacheCategrie() {

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(Const.URL_JSON_CAT);
        if (entry != null) {
            try {
                String data = new String(entry.data, "UTF-8");

                try {

                    JSONArray jsonArray = new JSONArray(data);

                    listCategorie = new ArrayList<Categories>();
                    for (int i = 0; i < jsonArray.length(); i++) {


                        JSONObject row = (JSONObject) jsonArray.get(i);
                        categ = new Categories();
                        categ.initCategorie(row);
                        listCategorie.add(categ);

                    }


                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        if (listCategorie != null) {
            return listCategorie;
        } else {
            return null;
        }
    }


    public void getCategories(final RequestCallbackCategories delegate) {

        JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_CAT,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String resp = response.toString();


                        byte ptext[] = new byte[0];
                        String value = null;
                        JSONArray jsonArray;
                        try {
                            ptext = resp.getBytes("ISO-8859-1");
                            value = new String(ptext, "utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        try {
                             jsonArray = new JSONArray(value);

                            listCategorie = new ArrayList<Categories>();
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JSONObject row = (JSONObject) jsonArray.get(i);
                                categ = new Categories();
                                categ.initCategorie(row);
                                listCategorie.add(categ);



                            }
                            delegate.onGetCategoriesSuccess(listCategorie);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                if (error != null) {

                    delegate.onGetCategoriesError(error.toString());

                }
            }
        });


        AppController.getInstance().addToRequestQueue(req, tag_json_arry);

    }
}
