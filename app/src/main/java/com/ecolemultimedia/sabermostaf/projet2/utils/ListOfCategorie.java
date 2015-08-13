package com.ecolemultimedia.sabermostaf.projet2.utils;

import com.ecolemultimedia.sabermostaf.projet2.models.Categories;

import java.util.ArrayList;

public class ListOfCategorie {

	public static final String TAG = ListOfCategorie.class.getSimpleName();

	private static ListOfCategorie mInstance = null;

	static ArrayList<Categories> listCategorie = null;


	public static ListOfCategorie getInstance() {
		if (mInstance == null) {
			
			mInstance = new ListOfCategorie();
			listCategorie=new ArrayList<Categories>();
		}
		
		
		return mInstance;
	}

	public ArrayList<Categories> getListCategorie() {
		return listCategorie;
	}

	public void setListcategorie(ArrayList<Categories> listCategorie) {
		this.listCategorie = listCategorie;
	}

	
	
}
