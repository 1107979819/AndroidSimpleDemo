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

	//������д�ĺ��ķ���  
    @Override  
    protected void onHandleIntent(Intent intent) {  
        //Intent�Ǵ�Activity�������ģ�Я��ʶ����������ݲ�����ִͬ�в�ͬ������  
        String action = intent.getExtras().getString("param");  
        if(action.equals("s1"))Log.i(TAG,"����service1");  
        else if(action.equals("s2"))Log.i(TAG,"����service2");  
        else if(action.equals("s3"))Log.i(TAG,"����service3");  
          
        //�÷�������10��  
        try{  
            Thread.sleep(10000);  
        }catch(InterruptedException e){e.printStackTrace();}          
    }  
  
    //��д��������,���ڲ鿴�����ĵ���˳��  
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