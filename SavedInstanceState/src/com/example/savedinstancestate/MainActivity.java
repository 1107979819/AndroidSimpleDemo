package com.example.savedinstancestate;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
/**
 * savedInstanceState »úÖÆÑÝÊ¾
 * @author WYL
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if(savedInstanceState==null)
		{
			Log.i("Test","savedInstanceState==null");
		}else
		{
			Log.i("Test"," onCreate"+savedInstanceState.getString("hello"));
		}
		
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i("Test"," onRestoreInstanceState"+savedInstanceState.getString("hello"));
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		Log.i("Test"," onSaveInstanceState(Bundle outState)");
		outState.putString("hello","hello");
		super.onSaveInstanceState(outState);
	}
	
}	
