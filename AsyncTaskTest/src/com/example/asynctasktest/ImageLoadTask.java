package com.example.asynctasktest;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class ImageLoadTask extends AsyncTask{

	private ImageView iv;
	protected Object doInBackground(Object... arg0) {
		// TODO Auto-generated method stub
		System.out.println("doInBackground");
		String url = (String) arg0[0];
		iv = (ImageView) arg0[1];
		return returnBitMap(url);
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}

	@Override
	protected void onCancelled(Object result) {
		// TODO Auto-generated method stub
		super.onCancelled(result);
	}

	@Override
	protected void onPostExecute(Object result) {
		// TODO Auto-generated method stub
		System.out.println("onPostExecute");
		iv.setImageBitmap((Bitmap) result);
		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		
		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Object... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
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
