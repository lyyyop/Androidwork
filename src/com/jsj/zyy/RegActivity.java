package com.jsj.zyy;


import com.jsj.zyy.R;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegActivity extends Activity {
	private EditText etUid;
	private EditText etPwd;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reg);
		Button btInsert=(Button)this.findViewById(R.id.button1);
		Button back = (Button) this.findViewById(R.id.button2);
		etUid = (EditText) this.findViewById(R.id.editText1);
		etPwd = (EditText) this.findViewById(R.id.editText2);
		back.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(RegActivity.this, MyFinalHomeworkActivity.class);
				startActivity(it);
			}
		});
		
		btInsert.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String uid= etUid.getText().toString().trim();
				String pwd = etPwd.getText().toString().trim();
				SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase("data/data/com.jsj.zyy/db232.db",null);
				String sql="insert into user(uname,upw,money,finish) values('"+uid+"','"+pwd+"','0','0')";
				db.execSQL(sql);
				db.close();
				Toast.makeText(RegActivity.this, "×¢²á³É¹¦", Toast.LENGTH_SHORT).show();
				finish();
			}
		});
	}
}
