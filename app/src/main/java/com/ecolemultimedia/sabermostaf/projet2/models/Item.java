package com.ecolemultimedia.sabermostaf.projet2.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by saber on 14/08/15.
 */
public class Item {


    private String title = null;
    private String type = null;
    private int nid = 0;
    private int nb_credits = 0;
    private double duration = 0;
    private String field_poster = null;
    private String _id = null;
    private String field_video = null;
    private Boolean active = false;

    private Boolean free = false;
    private ArrayList ListItem = null;

    public void initItem(JSONObject item) throws JSONException {


            if (item.has("title")) {
                setTitle(item.getString("title"));
            }

            if (item.has("type")) {
                setType(item.getString("type"));
            }
            if (item.has("nid")) {
                setNid(item.getInt("nid"));
            }
            if (item.has("nb_credits")) {
                setNb_credits(item.getInt("nb_credits"));
            }
            if (item.has("duration")) {
                setDuration(item.getDouble("duration"));
            }
            if (item.has("field_poster")) {
                setField_poster(item.getString("field_poster"));
            }
            if (item.has("_id")) {
                set_id(item.getString("_id"));
            }

            if (item.has("field_video")) {
                if(item.getJSONArray("field_video").length()>0) {
                    setField_video(item.getJSONArray("field_video").getJSONObject(0).getString("filepath"));
                }
            }

            if (item.has("active")) {
                setActive(item.getBoolean("active"));
            }
            if (item.has("free")) {

                setFree(item.getBoolean("free"));
            }

    }

    public ArrayList getListItem() {
        return ListItem;
    }

    public void setListItem(ArrayList listItem) {
        ListItem = listItem;
    }

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getField_video() {
        return field_video;
    }

    public void setField_video(String field_video) {
        this.field_video = field_video;
    }

    public String getField_poster() {
        return field_poster;
    }

    public void setField_poster(String field_poster) {
        this.field_poster = field_poster;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getNb_credits() {
        return nb_credits;
    }

    public void setNb_credits(int nb_credits) {
        this.nb_credits = nb_credits;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
