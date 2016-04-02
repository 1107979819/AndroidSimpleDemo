package com.example.asynctasktest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
/**
 * AsyncTask ÓÃ·¨
 * @author WYL
 *
 */
public class MainActivity extends Activity {
	
	String url1 = "http://img1.bitautoimg.com/bitauto/2012/08/13/41cf2a1a-50da-41aa-a5e6-9673e74bc937.jpg";
	String url2 = "http://img.hx2car.com/upload/car/2013/1/10/04/87/90/85/41/0487908541.jpg";
	String url3 = "http://img15.3lian.com/2015/a1/10/d/231.jpg";
	private Button btn1, btn2, btn3;
	private ImageView iv;
	private ImageLoadTask  it1,it2,it3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		it1 = new ImageLoadTask();
		it2 = new ImageLoadTask();
		it3 = new ImageLoadTask();
		initView();
		
		
	}
	
	public void initView()
	{
		btn1 = (Button)findViewById(R.id.button1);
		btn2 = (Button)findViewById(R.id.button2);
		btn3 = (Button)findViewById(R.id.button3);
		
		iv = (ImageView)findViewById(R.id.imageView1);
		
		btn1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				it1.execute(url1,iv);
				
			}
			
		});
		btn2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				it2.execute(url2,iv);
			}
			
		});
		btn3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				it3.execute(url3,iv);
			}
			
		});
		
	}

	

}
