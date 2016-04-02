package com.example.serviceintentservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
/**
 * IntentService演示
 * @author WYL
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("Test", "test");
		doSome();
	}
	public void doSome()
	{
		   Intent it1 = new Intent(MainActivity.this, MyIntentService.class);
	        Bundle b1 = new Bundle();  
	        b1.putString("param", "s1");  
	        it1.putExtras(b1);  
	          
	        Intent it2 = new Intent(MainActivity.this, MyIntentService.class);
	        Bundle b2 = new Bundle();  
	        b2.putString("param", "s2");  
	        it2.putExtras(b2);  
	          
	        Intent it3 = new Intent(MainActivity.this, MyIntentService.class);
	        Bundle b3 = new Bundle();  
	        b3.putString("param", "s3");  
	        it3.putExtras(b3);  
	          
	        //接着启动多次IntentService,每次启动,都会新建一个工作线程  
	        //但始终只有一个IntentService实例  
	        startService(it1);  
	        startService(it2);  
	        startService(it3);  
	}
	
}
