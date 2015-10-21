package com.nao20010128nao.Image.Resizer;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.ViewGroup.*;

public class MainActivity extends Activity
{
	static final int MATCH_PARENT=ViewGroup.LayoutParams.MATCH_PARENT;
    static final int WRAP_CONTENT=ViewGroup.LayoutParams.WRAP_CONTENT;
    /** Called when the activity is first created. */
	CheckBox inp,outp;
	EditText wid,heig;
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
		LinearLayout ll=new LinearLayout(this);
		{
			ll.setLayoutParams(new LayoutParams(MATCH_PARENT,MATCH_PARENT));
			ll.setOrientation(LinearLayout.VERTICAL);
			ll.setGravity(Gravity.CENTER);
		}
		{
			inp=new CheckBox(this);
			inp.setLayoutParams(new LayoutParams(WRAP_CONTENT,WRAP_CONTENT));
			inp.setText("Input");
			ll.addView(inp);
		}
		{
			wid=new EditText(this);
			wid.setLayoutParams(new LayoutParams(WRAP_CONTENT,WRAP_CONTENT));
			wid.setHint("Width");
			ll.addView(wid);
		}
		{
			heig=new EditText(this);
			heig.setLayoutParams(new LayoutParams(WRAP_CONTENT,WRAP_CONTENT));
			heig.setHint("Height");
			ll.addView(heig);
		}
		{
			outp=new CheckBox(this);
			outp.setLayoutParams(new LayoutParams(WRAP_CONTENT,WRAP_CONTENT));
			outp.setText("Output");
			ll.addView(outp);
		}
		setContentView(ll);
    }
}
