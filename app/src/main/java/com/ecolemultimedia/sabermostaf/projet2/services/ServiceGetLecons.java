package com.ecolemultimedia.sabermostaf.projet2.services;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ecolemultimedia.sabermostaf.projet2.components.AppController;
import com.ecolemultimedia.sabermostaf.projet2.interfaces.RequestCallbackLecons;
import com.ecolemultimedia.sabermostaf.projet2.models.Formation;
import com.ecolemultimedia.sabermostaf.projet2.utils.Const;

import org.json.JSONObject;

/**
 * Created by saber on 10/08/15.
 */
public class ServiceGetLecons {
    public static final String TAG = ServiceGetLecons.class.getSimpleName();
    private static ServiceGetLecons ourInstance = new ServiceGetLecons();
    private String tag_json_arry = "tag_json_arry";
    private static ServiceGetLecons mInstance = null;
    private JSONObject month = null;


    private Formation formation = null;


    public static ServiceGetLecons getInstance() {
        if (ourInstance == null) {
            ourInstance = new ServiceGetLecons();
        }

        return ourInstance;
    }


    public void getLecons(final RequestCallbackLecons delegate, String id) {
        Log.v("Debeug", "URL "+Const.URL_JSON_LEC +id+ "/tree");
        JsonObjectRequest req = new JsonObjectRequest(Const.URL_JSON_LEC +id+ "/tree",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.v("Debeug", "response"+response);


                formation = new Formation();
                formation.initFormation(response);

                delegate.onGetLeconsSuccess(formation);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                if (error != null) {
                    Log.v("Debeug", "error"+error.getMessage());
                    delegate.onGetLeconsError(error.getMessage());

                }
            }
        });

        req.setShouldCache(true);
        AppController.getInstance().addToRequestQueue(req, tag_json_arry);

    }

}

