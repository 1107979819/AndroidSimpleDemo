package com.example.service;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
	private Timer timer;
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.i("Test", "MyService onBind");
		return  null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.i("Test", "MyService onCreate");
		timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Log.i("Test", "MyService running");
			}
		},1000,1000);;
		
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("Test", "MyService onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.i("Test", "MyService onStart");
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		Log.i("Test", "MyService onDestroy");
		if(timer!=null)
		{
			timer.cancel();
			timer = null;
		}
		super.onDestroy();
	}
	@Override
	public boolean onUnbind(Intent intent) {
		Log.i("Test", "MyService onUnbind");
		return super.onUnbind(intent);
	}

	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("Test", "MyService onRebind");
		super.onRebind(intent);
	}


}
