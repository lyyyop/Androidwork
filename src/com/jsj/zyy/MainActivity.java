package com.jsj.zyy;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
	private TabHost tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		Intent it =getIntent(); 
		Intent me=new Intent(this,MeActivity.class);
		Intent home=new Intent(this,HomeActivity.class);
		Intent todo=new Intent(this,TodoActivity.class);
        String user = it.getStringExtra("user");
        me.putExtra("username", user);
        home.putExtra("username", user);
        todo.putExtra("username", user);
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(getResources().getText(R.string.tabtitle1)
				,getResources().getDrawable(R.drawable.home)).setContent(home));
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(getResources().getText(R.string.tabtitle2)
				,getResources().getDrawable(R.drawable.todo)).setContent(todo));
		tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator(getResources().getText(R.string.tabtitle4)
				,getResources().getDrawable(R.drawable.me)).setContent(me));		 
		
	}
}
