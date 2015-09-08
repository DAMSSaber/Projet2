package com.ecolemultimedia.sabermostaf.projet2.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ShareActionProvider;

import com.ecolemultimedia.sabermostaf.projet2.R;

import java.lang.reflect.Method;

public class MyShareAction extends ShareActionProvider {

	public MyShareAction(Context context) {
		super(context);
		mContext = context;
	}

	private Context mContext;

	@Override
	public View onCreateActionView() {
		View view = super.onCreateActionView();
		if (view != null) {
			try {
				Drawable icon = mContext.getResources().getDrawable(
						R.drawable.ic_action_social_share);
				Method method = view.getClass().getMethod(
						"setExpandActivityOverflowButtonDrawable",
						Drawable.class);
				method.invoke(view, icon);
			} catch (Exception e) {
				Log.e("MyShareActionProvider", "onCreateActionView", e);
			}
		}

		return view;
	}

}
