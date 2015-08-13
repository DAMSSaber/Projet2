package com.ecolemultimedia.sabermostaf.projet2.components;

import static android.provider.Settings.System.AIRPLANE_MODE_ON;

import java.util.HashMap;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.ecolemultimedia.sabermostaf.projet2.utils.LruBitmapCache;


public class AppController extends Application {


	public static final String TAG = AppController.class.getSimpleName();

	private RequestQueue mRequestQueue = null;
	private ImageLoader mImageLoader = null;


	

	private static AppController mInstance;





	@Override
	public void onCreate() {
		super.onCreate();

		 
		mInstance = this;
	}

	public static synchronized AppController getInstance() {
		return mInstance;
	}

	public RequestQueue getRequestQueue() {

		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}

		return mRequestQueue;
	}

	public ImageLoader getImageLoader() {

		if (mImageLoader == null) {
			mImageLoader = new ImageLoader(this.mRequestQueue,
					new LruBitmapCache());
		}
		return this.mImageLoader;
	}

	public <T> void addToRequestQueue(Request<T> req, String tag) {
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}


	static boolean isAirplaneModeOn(Context context) {
		ContentResolver contentResolver = context.getContentResolver();
		return Settings.System.getInt(contentResolver, AIRPLANE_MODE_ON, 0) != 0;
	}
}
