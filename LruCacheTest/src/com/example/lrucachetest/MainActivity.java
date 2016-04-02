package com.example.lrucachetest;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * LruCache的用法
 * @author WYL
 *
 */
public class MainActivity extends Activity {

	private int count;
	
	private Button btn_last,btn_next;
	private TextView tv_index,tv_url,tv_key;
	private ImageView iv_main;
	private LruCache<String, Bitmap> lruCachePic;
	
	private int index = 0;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		addEvent();
		initData();
		test();
	}
	public void initView()
	{
		 btn_last =  (Button)findViewById(R.id.btn_last);
		 btn_next =  (Button)findViewById(R.id.btn_next);
		 tv_index = (TextView)findViewById(R.id.tv_index);
		 tv_url = (TextView)findViewById(R.id.tv_url);
		 tv_key = (TextView)findViewById(R.id.tv_key);
		 iv_main = (ImageView)findViewById(R.id.iv_main);
		 	
	}
	public void addEvent()
	{
		 btn_last.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(index>0)
				{
					index--;
					String url = Images.imageUrls[index];
					tv_index.setText(""+index);
					tv_url.setText(url);
					tv_key.setText(Utils.hashKeyFormUrl(url));
					setImageView(url,iv_main);
					
				}else
				{
					Toast.makeText(getApplicationContext(), "已经第一张", 0).show();
				}
			}
		});
		 btn_next.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(index<Images.imageUrls.length)
					{
						index++;
						String url = Images.imageUrls[index];
						tv_index.setText(""+index);
						tv_url.setText(url);
						tv_key.setText(Utils.hashKeyFormUrl(url));
						setImageView(url,iv_main);
						
					}else
					{
						Toast.makeText(getApplicationContext(), "已经是最后一张", 0).show();
					}
				}
			});
	}
	public void initData()
	{
		
		int maxMemory = (int)(Runtime.getRuntime().maxMemory()/1024);
		int cacheSize = maxMemory / 8;
		lruCachePic = new LruCache<String,Bitmap>(cacheSize)
				{

					@Override
					protected void entryRemoved(boolean evicted, String key,
							Bitmap oldValue, Bitmap newValue) {
						// TODO Auto-generated method stub
						boolean ovIsNull = false;
						boolean nvIsNull = false;
						
						if(oldValue==null)
						{
							ovIsNull = true;
						}
						if(newValue==null)
						{
							nvIsNull = true;
						}
						Log.i("LruCache"," 》》》》》》》key:"+key+" evicted:"+evicted+" ovIsNull:"+ovIsNull+" nvIsNull:"+nvIsNull);
						
						
						super.entryRemoved(evicted, key, oldValue, newValue);
					}

					@Override
					protected int sizeOf(String key, Bitmap bitmap) {
						// TODO Auto-generated method stub
						return bitmap.getRowBytes()*bitmap.getHeight()/1024;
					}
					
				};
				
				String url = Images.imageUrls[index];
				tv_index.setText(""+index);
				tv_url.setText(url);
				tv_key.setText(Utils.hashKeyFormUrl(url));
				setImageView(url,iv_main);	
				
				
	}
	public void test()
	{
		new Timer().schedule(new TimerTask(){

			@Override
			public void run() {
				MemoryMessage();
			}
		}
		, 0,1000);
	}
	public long MemoryMessage()
	{
		long maxMemory = Runtime.getRuntime().maxMemory();
		long tolMemory = Runtime.getRuntime().totalMemory();
		long freMemory = Runtime.getRuntime().freeMemory();
		int avaPro = Runtime.getRuntime().availableProcessors();
		
		Log.i("maxMem", "maxMem:"+maxMemory/1024+"K "+maxMemory/1024/1024+"M"
						+" tolMem:"+tolMemory/1024+"K "+tolMemory/1024/1024+"M"
						+" freMem:"+freMemory/1024+"K "+freMemory/1024/1024+"M"
						+" Proces:"+avaPro);
		return maxMemory;
	}
	
	
	 
	 public void setImageView(final String url,final ImageView imageView)
	 {
		 Bitmap bm = lruCachePic.get(Utils.hashKeyFormUrl(url));
		 if(bm!=null)
		 {
			 imageView.setImageBitmap(bm);
			 return ;
		 }
		 Log.i("LruCache"," download  key:"+Utils.hashKeyFormUrl(url)+" url:"+ url);
		 new Thread(){
			public void run() {
				final Bitmap bm = returnBitMap(url);
				if(bm!=null){
					 lruCachePic.put(Utils.hashKeyFormUrl(url), bm);
					 runOnUiThread(new  Runnable() {
						public void run() {
							 imageView.setImageBitmap(bm);
						}
					});
				}else
				{
					 runOnUiThread(new  Runnable() {
							public void run() {
								imageView.setImageResource(R.drawable.ic_launcher);
							}
						});
					
				}
			}
		 }.start();
		 
		 
	 }
	 public Bitmap returnBitMap(String url) {   
		   URL myFileUrl = null;   
		   Bitmap bitmap = null;   
		   
		   try {
		    myFileUrl = new URL(url);    
		    HttpURLConnection conn;
		  
		    conn = (HttpURLConnection) myFileUrl.openConnection();
		   
		    conn.setDoInput(true);   
		    conn.connect(); 
		    InputStream is = conn.getInputStream();   
		    bitmap = BitmapFactory.decodeStream(is);  
		    
		   } catch (MalformedURLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }  catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }
		   return bitmap;  
		 }
	 
}


