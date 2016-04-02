package com.example.activityflow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityA extends Activity {
	private Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a);
		Log.i("Test", "A onCreate");
		
		btn = (Button)findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it= new Intent(getApplicationContext(),ActivityB.class);
				startActivity(it);
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
			}
		});
		
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		Log.i("Test", "A onRestart");
		super.onRestart();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		Log.i("Test", "A onStart");
		super.onStart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.i("Test", "A onResume");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.i("Test", "A onPause");
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Log.i("Test", "A onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("Test", "A onDestroyonDestroy");
		super.onDestroy();
	}
	
	
	
	
}
