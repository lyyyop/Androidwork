package com.jsj.zyy;

import com.jsj.zyy.R;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MeActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me);
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("data/data/com.jsj.zyy/db232.db", null);
		String username=getIntent().getStringExtra("username");
		Cursor cursor = db.query("user", null, "uname=?", new String[] {username}, null, null, null);
		Button btn2=(Button)this.findViewById(R.id.button2);
		TextView tv1=(TextView)this.findViewById(R.id.textView1);
		TextView tv2=(TextView)this.findViewById(R.id.textView2);
		TextView tv3=(TextView)this.findViewById(R.id.textView3);
		tv1.setText("�û�����"+username);
		if (cursor.moveToFirst()){
            do {
            	tv2.setText("Ŀǰ��ң�"+cursor.getString(cursor.getColumnIndex("money"))+"ö");
        		tv3.setText("Ŀǰ�����Ŀ�꣺"+cursor.getString(cursor.getColumnIndex("finish")));
            }while (cursor.moveToNext());
        }
        cursor.close();
		
		btn2.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	
}
