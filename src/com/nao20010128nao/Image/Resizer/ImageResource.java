package com.nao20010128nao.Image.Resizer;
import android.app.*;
import android.os.*;
import android.content.*;
import android.graphics.*;
import android.util.*;
import java.io.*;
import android.net.*;
import java.net.*;
import android.widget.*;

public class ImageResource extends Activity
{
	String resourceName="ic_launcher";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		new Thread(){
			public void run(){
				String data=getIntent().getDataString();
				if(data==null)data=((Object)getIntent().getParcelableExtra(Intent.EXTRA_STREAM)).toString();
				Bitmap bmp;InputStream is=null;OutputStream os=null;
				try {
					bmp=BitmapFactory.decodeStream(is=tryOpen(data));
				} catch (Throwable e) {
					finish();
					return;
				}finally{
					try {
						is.close();
					} catch (Throwable e) {}
				}
				File f=new File(Environment.getExternalStorageDirectory(),"ImageResizer/"+System.currentTimeMillis());
				if(!f.exists())f.mkdirs();
				
				File ldpiF=new File(f,"/drawable-ldpi/");
				ldpiF.mkdirs();
				ldpiF=new File(ldpiF,resourceName+".png");
				Bitmap ldpi=Bitmap.createScaledBitmap(bmp,36,36,false);
				try {
					Log.d("dbg", "save:" + ldpi.compress(Bitmap.CompressFormat.PNG, 100, os = new FileOutputStream(ldpiF)));
				} catch (Throwable e) {
					finish();
				}finally{
					try {
						os.flush();
						os.close();
					} catch (Throwable ez) {}
					ldpi.recycle();
				}
				
				File mdpiF=new File(f,"/drawable-mdpi/");
				mdpiF.mkdirs();
				mdpiF=new File(mdpiF,resourceName+".png");
				Bitmap mdpi=Bitmap.createScaledBitmap(bmp,48,48,false);
				try {
					Log.d("dbg", "save:" + mdpi.compress(Bitmap.CompressFormat.PNG, 100, os = new FileOutputStream(mdpiF)));
				} catch (Throwable e) {
					finish();
				}finally{
					try {
						os.flush();
						os.close();
					} catch (Throwable ez) {}
					mdpi.recycle();
				}
				
				File hdpiF=new File(f,"/drawable-hdpi/");
				hdpiF.mkdirs();
				hdpiF=new File(hdpiF,resourceName+".png");
				Bitmap hdpi=Bitmap.createScaledBitmap(bmp,72,72,false);
				try {
					Log.d("dbg", "save:" + hdpi.compress(Bitmap.CompressFormat.PNG, 100, os = new FileOutputStream(hdpiF)));
				} catch (Throwable e) {
					finish();
				}finally{
					try {
						os.flush();
						os.close();
					} catch (Throwable ez) {}
					hdpi.recycle();
				}

				File xhdpiF=new File(f,"/drawable-xhdpi/");
				xhdpiF.mkdirs();
				xhdpiF=new File(xhdpiF,resourceName+".png");
				Bitmap xhdpi=Bitmap.createScaledBitmap(bmp,96,96,false);
				try {
					Log.d("dbg", "save:" + xhdpi.compress(Bitmap.CompressFormat.PNG, 100, os = new FileOutputStream(xhdpiF)));
				} catch (Throwable e) {
					finish();
				}finally{
					try {
						os.flush();
						os.close();
					} catch (Throwable ez) {}
					xhdpi.recycle();
				}

				File xxhdpiF=new File(f,"/drawable-xxhdpi/");
				xxhdpiF.mkdirs();
				xxhdpiF=new File(xxhdpiF,resourceName+".png");
				Bitmap xxhdpi=Bitmap.createScaledBitmap(bmp,144,144,false);
				try {
					Log.d("dbg", "save:" + xxhdpi.compress(Bitmap.CompressFormat.PNG, 100, os = new FileOutputStream(xxhdpiF)));
				} catch (Throwable e) {
					finish();
				}finally{
					try {
						os.flush();
						os.close();
					} catch (Throwable ez) {}
					xxhdpi.recycle();
				}

				File xxxhdpiF=new File(f,"/drawable-xxxhdpi/");
				xxxhdpiF.mkdirs();
				xxxhdpiF=new File(xxxhdpiF,resourceName+".png");
				Bitmap xxxhdpi=Bitmap.createScaledBitmap(bmp,192,192,false);
				try {
					Log.d("dbg", "save:" + xxxhdpi.compress(Bitmap.CompressFormat.PNG, 100, os = new FileOutputStream(xxxhdpiF)));
				} catch (Throwable e) {
					finish();
				}finally{
					try {
						os.flush();
						os.close();
					} catch (Throwable ez) {}
					xxxhdpi.recycle();
				}

				File nodpiF=new File(f,"/drawable-nodpi/");
				nodpiF.mkdirs();
				nodpiF=new File(nodpiF,resourceName+".png");
				try {
					Log.d("dbg", "save:" + bmp.compress(Bitmap.CompressFormat.PNG, 100, os = new FileOutputStream(nodpiF)));
				} catch (Throwable e) {
					finish();
				}finally{
					try {
						os.flush();
						os.close();
					} catch (Throwable ez) {}
				}
				
				bmp.recycle();
				finish();
			}
		}.start();
		TextView tv;
		setContentView(tv=new TextView(this));
		tv.setText("Please wait...");
	}
	public InputStream tryOpen(String uri) throws IOException {
		Log.d("dbg", "tryOpen:" + uri);
		if (uri.startsWith("content://")) {
			return getContentResolver().openInputStream(Uri.parse(uri));
		} else if (uri.startsWith("/")) {
			return new FileInputStream(uri);
		} else {
			return URI.create(uri).toURL().openConnection().getInputStream();
		}
	}

	@Override
	public void finish() {
		// TODO: Implement this method
		runOnUiThread(new Runnable(){
			public void run(){
				ImageResource.super.finish();
			}
		});
	}
}
