package com.ecolemultimedia.sabermostaf.projet2.services;

import com.android.volley.Cache;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.ecolemultimedia.sabermostaf.projet2.components.AppController;
import com.ecolemultimedia.sabermostaf.projet2.interfaces.RequestCallbackFormation;
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
public class ServiceGetFormations {
    public static final String TAG = ServiceGetFormations.class.getSimpleName();
    private static ServiceGetFormations ourInstance = new ServiceGetFormations();
    private String tag_json_arry = "tag_json_arry";
    private static ServiceGetFormations mInstance = null;
    private JSONObject month = null;

    private ArrayList<Formation> listFormation = null;
    private Formation formation = null;


    public static ServiceGetFormations getInstance() {
        if (ourInstance == null) {
            ourInstance = new ServiceGetFormations();
        }

        return ourInstance;
    }


    public ArrayList<Formation> getCacheFormation(String id) {

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(Const.URL_JSON_FORM + id + "/trainings");
        if (entry != null) {
            try {
                String data = new String(entry.data, "UTF-8");

                try {

                    JSONArray jsonArray = new JSONArray(data);

                    listFormation = new ArrayList<Formation>();
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject row = (JSONObject) jsonArray.get(i);
                        formation = new Formation();
                        formation.initFormation(row);
                        listFormation.add(formation);
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        if (listFormation != null) {
            return listFormation;
        } else {
            return null;
        }
    }

    public void getFormation(final RequestCallbackFormation delegate, String id) {

        JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_FORM + id + "/trainings",
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

                            listFormation = new ArrayList<Formation>();
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JSONObject row = (JSONObject) jsonArray.get(i);
                                formation = new Formation();
                                formation.initFormation(row);
                                listFormation.add(formation);

                            }
                            delegate.onGetFormationSuccess(listFormation);


                        } catch (JSONException e) {

                            e.printStackTrace();
                        }

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
