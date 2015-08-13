package com.ecolemultimedia.sabermostaf.projet2.interfaces;

import com.ecolemultimedia.sabermostaf.projet2.models.Categories;

import java.util.ArrayList;

/**
 * Created by saber on 10/08/15.
 */
public interface RequestCallbackCategories {

    public void onGetCategoriesSuccess(ArrayList<Categories> listCat);
    public void onGetCategoriesError(String  message);

}
