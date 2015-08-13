package com.ecolemultimedia.sabermostaf.projet2.utils;

import java.util.ArrayList;

import com.ecolemultimedia.sabermostaf.projet2.models.Categories;

public class ListOfSubCategorie {

	public static final String TAG = ListOfSubCategorie.class.getSimpleName();

	private static ListOfSubCategorie mInstance = null;

	static ArrayList<Categories> listCategorie = null;


	public static ListOfSubCategorie getInstance() {
		if (mInstance == null) {
			
			mInstance = new ListOfSubCategorie();
			listCategorie=new ArrayList<Categories>();
		}
		
		
		return mInstance;
	}

	public ArrayList<Categories> getListSubCategorie() {
		return listCategorie;
	}

	public void setListSubcategorie(ArrayList<Categories> listCategorie) {
		this.listCategorie = listCategorie;
	}

	
	
}
