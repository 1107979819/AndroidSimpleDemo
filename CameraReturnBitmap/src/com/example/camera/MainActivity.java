package com.example.camera;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import com.example.camerabitmap.R;
/**
 * 调用系统摄像机拍照返回Bitmap
 * @author WYL
 *
 */
public class MainActivity extends Activity {
	private Button btn ;
	private ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button)findViewById(R.id.button1);
		imageView=(ImageView)findViewById(R.id.imageView1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(it,Activity.DEFAULT_KEYS_DIALER);
				
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == Activity.DEFAULT_KEYS_DIALER) {
	            Bundle bundle = data.getExtras();
	            Bitmap bitmap = (Bitmap) bundle.get("data");
	            imageView.setImageBitmap(bitmap);
	        }
		super.onActivityResult(requestCode, resultCode, data);
	}


}
