package com.example.notificationdemo;

import java.lang.annotation.Annotation;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;import android.text.NoCopySpan.Concrete;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.RemoteViews.RemoteView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private NotificationManager notificationManager;
	private Notification notification;
	private RemoteViews remoteViews ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	
	@Override
	protected void onResume() {
		Intent it = getIntent();
		if(it!=null)
		{
			
			Toast.makeText(getApplicationContext(), ""+it.getStringExtra("Test"), Toast.LENGTH_LONG).show();
			if(it.getStringExtra("Test").equals("test"))
			{
				remoteViews.setProgressBar(R.id.progressBar1, 100, 59, false);//如果为true，progressbar在没有更新的时候，会自动动画效果
				notificationManager.notify(0, notification);
			}
			
		}
		// TODO Auto-generated method stub
		Log.i("Test","onResume");
		super.onResume();
	}

	public void initView()
	{
		findViewById(R.id.btn_createnoti).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setupNotification();
				
			}
		});
		
	findViewById(R.id.btn_updatenoti).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	
	findViewById(R.id.btn_newact).setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	});
	}
	
	
	
	public void setupNotification()
	{
		notification = new Notification();
		notification.icon = R.drawable.ic_launcher;
		notification.tickerText="hello";
		notification.when = System.currentTimeMillis();
		notification.flags  = Notification.FLAG_AUTO_CANCEL;
		Intent intent = new Intent(this,MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		//自定义notification布局、控件等
		remoteViews = new RemoteViews(getPackageName(),R.layout.notification_item);
		remoteViews.setImageViewResource(R.id.imageView1, R.drawable.ic_launcher);
		remoteViews.setImageViewResource(R.id.imageButton1, R.drawable.ic_launcher);
		remoteViews.setProgressBar(R.id.progressBar1, 100, 0, false);//如果为true，progressbar在没有更新的时候，会自动动画效果
		remoteViews.setTextViewText(R.id.textView1, "nihao");
		Intent intent2 =  new Intent(this,MainActivity.class);
		intent2.putExtra("Test", "test");
		PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0,intent2, PendingIntent.FLAG_UPDATE_CURRENT);
	
//		Intent intent2 = new Intent(this,NotificationReceiver.class);
//		PendingIntent pendingIntent2 = PendingIntent.getBroadcast(this, 0, intent2 , PendingIntent.FLAG_UPDATE_CURRENT);
		
		remoteViews.setOnClickPendingIntent(R.id.imageButton1, pendingIntent2);
		notification.contentView = remoteViews;
		notification.contentIntent = pendingIntent;
		notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(0, notification);
		
	}
/*
	public class NotificationReceiver extends BroadcastReceiver {
	    private final String ACTION_UPDATE = "update_notification";
	    @Override
	    public void onReceive(Context context, Intent intent) {
	    if (ACTION_UPDATE.equals(intent.getAction()))
	        Toast.makeText(context, "点击了自定义的notification按钮", Toast.LENGTH_LONG).show();
	    }
	}*/
}
