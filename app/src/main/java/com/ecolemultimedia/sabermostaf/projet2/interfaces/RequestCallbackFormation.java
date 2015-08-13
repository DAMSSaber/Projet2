package com.ecolemultimedia.sabermostaf.projet2.interfaces;

import com.ecolemultimedia.sabermostaf.projet2.models.Categories;
import com.ecolemultimedia.sabermostaf.projet2.models.Formation;

import java.util.ArrayList;

/**
 * Created by saber on 10/08/15.
 */
public interface RequestCallbackFormation {

    public void onGetFormationSuccess(ArrayList<Formation> listCat);
    public void onGetFormationError(String message);

}
