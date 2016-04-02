package com.example.service;

import com.example.bindservice.R;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button btn1,btn2,btn3;
	private ServiceConnection serviceCon;
	private Intent it;
	private MyService.MyBinder ibinder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		initView();
		
		
	}
	public void init()
	{
		serviceCon = new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				Log.i("Test", "onServiceDisconnected");
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				Log.i("Test", " onServiceConnected");
				ibinder =(MyService.MyBinder) service;
			}
		};
		//5.0后用intent 隐式调用就出现Service Intent  must be explitict，也就是说从Lollipop开始，service服务必须采用显示方式启动。
//		it = new Intent();
//		it.setAction("com.example.service.MyService");
		it = new Intent(MainActivity.this,MyService.class);
	}
	
	public void initView()
	{

		btn1 = (Button)findViewById(R.id.button1);
		btn2 = (Button)findViewById(R.id.button2);
		btn3 = (Button)findViewById(R.id.button3);
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bindService(it, serviceCon, Service.BIND_AUTO_CREATE);
				
			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				unbindService(serviceCon);
			}
		});
		btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ibinder.printCount();
			}
		});
		
	}
}
