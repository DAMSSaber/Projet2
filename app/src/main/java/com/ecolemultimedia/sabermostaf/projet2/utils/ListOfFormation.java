package com.ecolemultimedia.sabermostaf.projet2.utils;

import com.ecolemultimedia.sabermostaf.projet2.models.Formation;

import java.util.ArrayList;

public class ListOfFormation {

    public static final String TAG = ListOfFormation.class.getSimpleName();

    private static ListOfFormation mInstance = null;

    static ArrayList<Formation> listFormation = null;


    public static ListOfFormation getInstance() {
        if (mInstance == null) {

            mInstance = new ListOfFormation();
            listFormation = new ArrayList<Formation>();
        }


        return mInstance;
    }

    public ArrayList<Formation> getListFormation() {
        return listFormation;
    }

    public void setListFormation(ArrayList<Formation> listFormation) {
        this.listFormation = listFormation;
    }


}
