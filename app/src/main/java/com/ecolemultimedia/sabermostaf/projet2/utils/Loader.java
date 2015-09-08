package com.ecolemultimedia.sabermostaf.projet2.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class Loader {
	private ProgressDialog dialog= null;
	private Context m_context= null;

	public Loader(Context context) {

		this.m_context = context;

	}

	public void showDialog() {

		dialog = new ProgressDialog(m_context);
		dialog.setMessage("Chargement..");
		dialog.show();

	}

	public void dismissDialog() {

		if (dialog != null)

			dialog.dismiss();
		}

}
