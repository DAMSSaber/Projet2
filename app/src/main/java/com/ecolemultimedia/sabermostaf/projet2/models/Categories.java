package com.ecolemultimedia.sabermostaf.projet2.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by saber on 10/08/15.
 */
public class Categories {

    private String mId = null;
    private String mTid = null;
    private String mTitle = null;
    private String mDescription = null;
    private String mImageUrl = null;
    private String mActive = null;
    private ArrayList<Categories> mListSubCategorie = null;


    public void initCategorie(JSONObject categorie) {

        try {
            if (categorie.has("_id")) {
                setmId(categorie.getString("_id"));
            }
            if (categorie.has("tid")) {
                setmTid(categorie.getString("tid"));
            }
            if (categorie.has("title")) {
                setmTitle(categorie.getString("title"));
            }
            if (categorie.has("image_url")) {
                  setmImageUrl(categorie.getString("image_url"));
            }
            if (categorie.has("description")) {
                setmDescription(categorie.getString("description"));
            }
            if (categorie.has("active")) {
            setmActive(categorie.getString("active"));
            }

            if (categorie.has("subcategories")) {
                mListSubCategorie=new ArrayList<Categories>();
                JSONArray subCat = categorie.getJSONArray("subcategories");

                for (int i=0; i<subCat.length();i++){
                    Categories cat=new Categories();
                    cat.initCategorie(subCat.getJSONObject(i));
                    mListSubCategorie.add(cat);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public String getmTid() {
        return mTid;
    }

    public void setmTid(String mTid) {
        this.mTid = mTid;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmActive() {
        return mActive;
    }

    public void setmActive(String mActive) {
        this.mActive = mActive;
    }

    public ArrayList<Categories> getmListSubCategorie() {
        return mListSubCategorie;
    }

    public void setmListSubCategorie(ArrayList<Categories> mListSubCategorie) {
        this.mListSubCategorie = mListSubCategorie;
    }


}
