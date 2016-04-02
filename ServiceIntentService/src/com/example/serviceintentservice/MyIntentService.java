package com.example.serviceintentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyIntentService extends IntentService {
	 private final String TAG = "Test";  
	 
	public MyIntentService() {
		super("Test");
		// TODO Auto-generated constructor stub
	}

	//必须重写的核心方法  
    @Override  
    protected void onHandleIntent(Intent intent) {  
        //Intent是从Activity发过来的，携带识别参数，根据参数不同执行不同的任务  
        String action = intent.getExtras().getString("param");  
        if(action.equals("s1"))Log.i(TAG,"启动service1");  
        else if(action.equals("s2"))Log.i(TAG,"启动service2");  
        else if(action.equals("s3"))Log.i(TAG,"启动service3");  
          
        //让服务休眠10秒  
        try{  
            Thread.sleep(10000);  
        }catch(InterruptedException e){e.printStackTrace();}          
    }  
  
    //重写其他方法,用于查看方法的调用顺序  
    @Override  
    public IBinder onBind(Intent intent) {  
        Log.i(TAG,"onBind");  
        return super.onBind(intent);  
    }  
  
    @Override  
    public void onCreate() {  
        Log.i(TAG,"onCreate");  
        super.onCreate();  
    }  
  
    @Override  
    public int onStartCommand(Intent intent, int flags, int startId) {  
        Log.i(TAG,"onStartCommand");  
        return super.onStartCommand(intent, flags, startId);  
    }  
  
  
    @Override  
    public void setIntentRedelivery(boolean enabled) {  
        super.setIntentRedelivery(enabled);  
        Log.i(TAG,"setIntentRedelivery");  
    }  
      
    @Override  
    public void onDestroy() {  
        Log.i(TAG,"onDestroy");  
        super.onDestroy();  
    }  
      
} 