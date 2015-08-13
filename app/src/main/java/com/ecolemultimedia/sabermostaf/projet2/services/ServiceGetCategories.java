package com.ecolemultimedia.sabermostaf.projet2.services;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ecolemultimedia.sabermostaf.projet2.components.AppController;
import com.ecolemultimedia.sabermostaf.projet2.interfaces.RequestCallbackCategories;
import com.ecolemultimedia.sabermostaf.projet2.models.Categories;
import com.ecolemultimedia.sabermostaf.projet2.utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    private ArrayList<Categories>  listCategorie=null;
    private Categories categ=null;




      public static ServiceGetCategories getInstance() {
          if (ourInstance == null) {
              ourInstance = new ServiceGetCategories();
          }

          return ourInstance;
      }


    public void getCategories(final RequestCallbackCategories delegate) {

        JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_CAT,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        Log.v("debeug","Reponse :"+response.toString());

                        listCategorie = new ArrayList<Categories>();
                        for (int i = 0; i < response.length(); i++) {

                            try {
                                JSONObject row = (JSONObject) response.get(i);
                                categ = new Categories();
                                categ.initCategorie(row);
                                listCategorie.add(categ);



                            } catch (JSONException e) {

                                e.printStackTrace();
                            }
                        }

                        delegate.onGetCategoriesSuccess(listCategorie);
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
