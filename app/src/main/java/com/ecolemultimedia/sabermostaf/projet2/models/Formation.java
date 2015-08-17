package com.ecolemultimedia.sabermostaf.projet2.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by saber on 12/08/15.
 */
public class Formation {

    private String _id = null;
    private String title = null;
    private String subtitle = null;
    private String product_url = null;
    private String ean13 = null;
    private String price = null;
    private String description = null;
    private String duration = null;
    private String objectives = null;
    private String prerequisites = null;
    private String qcm = null;
    private String teaser_text = null;
    private String category = null;
    private String subcategory = null;
    private String teaser = null;
    private String publishedDate = null;
    private String poster = null;
    private String image = null;
    private String free = null;

    private String video_count = null;
    private Boolean active = false;
    private String rating = null;

    private ArrayList<Item> ListItem = null;


    public void initFormation(JSONObject formation) {


        try {
            if (formation.has("_id")) {
                set_id(formation.getString("_id"));
            }
            if (formation.has("title")) {
                setTitle(formation.getString("title"));
            }
            if (formation.has("subtitle")) {

                setSubtitle(formation.getString("subtitle"));
            }
            if (formation.has("price")) {
                setPrice(formation.getString("price"));
            }
            if (formation.has("description")) {
                setDescription(formation.getString("description"));
            }
            if (formation.has("duration")) {
                setDuration(formation.getString("duration"));
            }
            if (formation.has("objectives")) {

                setObjectives(formation.getString("objectives"));
            }
            if (formation.has("ean13")) {
                setEan13(formation.getString("ean13"));

            }
            if (formation.has("prerequisites")) {

                setPrerequisites(formation.getString("prerequisites"));

            }
            if (formation.has("qcm")) {
                setQcm(formation.getString("qcm"));
            }
            if (formation.has("teaser_text")) {
                setTeaser_text(formation.getString("teaser_text"));
            }

            if (formation.has("category")) {
                setCategory(formation.getString("category"));
            }

            if (formation.has("subcategory")) {
                setSubcategory(formation.getString("subcategory"));
            }
            if (formation.has("teaser")) {
                setTeaser(formation.getString("teaser"));
            }
            if (formation.has("publishedDate")) {
                setPublishedDate(formation.getString("publishedDate"));
            }
            if (formation.has("poster")) {
                setPoster(formation.getString("poster"));
            }
            if (formation.has("images")) {
                setImage(formation.getJSONObject("images").getJSONObject("landscapes").getString("medium"));
            }
            if (formation.has("free")) {
                setFree(formation.getString("free"));
            }
            if (formation.has("rating")) {
                setRating(formation.getJSONObject("rating").getString("average"));
            }
            if (formation.has("video_count")) {
                setVideo_count(formation.getString("video_count "));
            }
            if (formation.has("active")) {
                setActive(formation.getBoolean("active"));
            }
            ListItem = new ArrayList<Item>();
            if (formation.has("items")) {
                Log.v("Debeug", "Have Item");
                JSONArray subCat = formation.getJSONArray("items");
                for (int i = 0; i < subCat.length(); i++) {
                    Item cat = new Item();
                    cat.initItem(subCat.getJSONObject(i));
                    ListItem.add(cat);
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getProduct_url() {
        return product_url;

    }

    public void setProduct_url(String product_url) {
        this.product_url = product_url;
    }

    public String getEan13() {
        return ean13;
    }

    public void setEan13(String ean13) {
        this.ean13 = ean13;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getQcm() {
        return qcm;
    }

    public void setQcm(String qcm) {
        this.qcm = qcm;
    }

    public String getTeaser_text() {
        return teaser_text;
    }

    public void setTeaser_text(String teaser_text) {
        this.teaser_text = teaser_text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getVideo_count() {
        return video_count;
    }

    public void setVideo_count(String video_count) {
        this.video_count = video_count;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public ArrayList<Item> getListItem() {
        return ListItem;
    }

    public void setListItem(ArrayList<Item> listItem) {
        ListItem = listItem;
    }

}

