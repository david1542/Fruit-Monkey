package com.fruitmonkey.game;

import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import engine.MonkeyRun;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("Fruit Monkey", "create() was invoked");
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MonkeyRun(), config);
	}
	
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		onDestroy();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.exit(0);
	}
}
