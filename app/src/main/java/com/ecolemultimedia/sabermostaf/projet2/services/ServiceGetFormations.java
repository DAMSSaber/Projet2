package com.ecolemultimedia.sabermostaf.projet2.services;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.ecolemultimedia.sabermostaf.projet2.components.AppController;
import com.ecolemultimedia.sabermostaf.projet2.interfaces.RequestCallbackCategories;
import com.ecolemultimedia.sabermostaf.projet2.interfaces.RequestCallbackFormation;
import com.ecolemultimedia.sabermostaf.projet2.models.Categories;
import com.ecolemultimedia.sabermostaf.projet2.models.Formation;
import com.ecolemultimedia.sabermostaf.projet2.utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by saber on 10/08/15.
 */
public class ServiceGetFormations {
    public static final String TAG = ServiceGetFormations.class.getSimpleName();
    private static ServiceGetFormations ourInstance = new ServiceGetFormations();
    private String tag_json_arry = "tag_json_arry";
    private static ServiceGetFormations mInstance = null;
    private JSONObject month = null;

    private ArrayList<Formation>  listFormation=null;
    private Formation formation=null;




      public static ServiceGetFormations getInstance() {
          if (ourInstance == null) {
              ourInstance = new ServiceGetFormations();
          }

          return ourInstance;
      }


    public void getFormation(final RequestCallbackFormation delegate, String id) {

        JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_FORM+id+"/trainings ",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        Log.v("debeug","Reponse :"+response.toString());

                        listFormation = new ArrayList<Formation>();
                        for (int i = 0; i < response.length(); i++) {

                            try {
                                JSONObject row = (JSONObject) response.get(i);
                                formation = new Formation();
                               formation.initFormation(row);
                                listFormation.add(formation);



                            } catch (JSONException e) {

                                e.printStackTrace();
                            }
                        }

                        delegate.onGetFormationSuccess(listFormation);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                if (error != null) {

                    delegate.onGetFormationError(error.toString());

                }
            }
        });


        AppController.getInstance().addToRequestQueue(req, tag_json_arry);

    }
}
