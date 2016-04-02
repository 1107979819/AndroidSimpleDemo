package com.example.camera;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import com.example.camerasave.R;
/**
 * 调用系统摄像机拍照保存到内存卡
 * @author WYL
 *
 */
public class MainActivity extends Activity {
	private Button btn ;
	private ImageView imageView;
	private File currentImageFile = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button)findViewById(R.id.button1);
		imageView=(ImageView)findViewById(R.id.imageView1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			    File dir = new File(Environment.getExternalStorageDirectory(),"pictures");
                if(dir.exists()){
                    dir.mkdirs();
                }
                currentImageFile = new File(dir,System.currentTimeMillis() + ".bmp");
                if(!currentImageFile.exists()){
                    try {
                        currentImageFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(currentImageFile));
                startActivityForResult(it, Activity.DEFAULT_KEYS_DIALER);
				
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == Activity.DEFAULT_KEYS_DIALER) {
	        imageView.setImageURI(Uri.fromFile(currentImageFile));
	        }
		super.onActivityResult(requestCode, resultCode, data);
	}


}
