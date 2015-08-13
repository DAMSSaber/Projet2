package com.ecolemultimedia.sabermostaf.projet2.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;

import com.ecolemultimedia.sabermostaf.projet2.R;


public class SplashScreen extends Activity {

	public static final String TAG = SplashScreen.class.getSimpleName();
	protected boolean _active = true;
	protected int _splashTime = 2000;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setupActrionBar();
		setContentView(R.layout.activity_splach_screen);


		Thread splashTread = new Thread() {
			@Override
			public void run() {
				try {
					int waited = 0;
					while (_active && (waited < _splashTime)) {
						sleep(100);
						if (_active) {
							waited += 100;
						}
					}
				} catch (Exception e) {

				} finally {

					Intent intent = new Intent(SplashScreen.this,
							HomeActivity.class);
					startActivity(intent);
					overridePendingTransition(0, 0);
					finish();
				}
			};
		};
		splashTread.start();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();

	}




	public void setupActrionBar() {
		ActionBar bar = getActionBar();
		bar.hide();
	}
}