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
import java.util.*;

public class Size2xMulti extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		new Thread(){
			public void run(){
				ArrayList files=(ArrayList)getIntent().getSerializableExtra(Intent.EXTRA_STREAM);
				ArrayList<String> stringFiles=new ArrayList();
				for(Object o:files)stringFiles.add(o+"");
				for (String data:stringFiles) {
					try {
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
						Bitmap resized=Bitmap.createBitmap(bmp.getWidth()*2,bmp.getHeight()*2,Bitmap.Config.ARGB_8888);
						for(int x=0;x<bmp.getWidth();x++){
							for(int y=0;y<bmp.getHeight();y++){
								int col=bmp.getPixel(x,y);
								resized .setPixel(x*2  ,y*2  ,col);
								resized .setPixel(x*2+1,y*2  ,col);
								resized .setPixel(x*2  ,y*2+1,col);
								resized .setPixel(x*2+1,y*2+1,col);
							}
						}
						bmp.recycle();
						File f=new File(Environment.getExternalStorageDirectory(),"ImageResizer");
						if(!f.exists())f.mkdirs();
						f=new File(f,System.currentTimeMillis()+".png");
						try {
							Log.d("dbg", "save:" + resized.compress(Bitmap.CompressFormat.PNG, 100, os = new FileOutputStream(f)));
						} catch (Throwable e) {
							finish();
						}finally{
							try {
								os.flush();
								os.close();
							} catch (Throwable ez) {}
							resized.recycle();
						}
					} catch (Throwable e) {}
				}
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
				Size2xMulti.super.finish();
			}
		});
	}
}
