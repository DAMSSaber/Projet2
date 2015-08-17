package com.ecolemultimedia.sabermostaf.projet2.interfaces;

import com.ecolemultimedia.sabermostaf.projet2.models.Formation;

import java.util.ArrayList;

/**
 * Created by saber on 10/08/15.
 */
public interface RequestCallbackLecons {

    public void onGetLeconsSuccess(Formation lecons);
    public void onGetLeconsError(String message);

}
