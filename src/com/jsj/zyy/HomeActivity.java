package com.jsj.zyy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		final String username=getIntent().getStringExtra("username");
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("data/data/com.jsj.zyy/db232.db", null);
		Cursor cursor = db.query("user", null, "uname=?", new String[] {username}, null, null, null);
		Button btn1=(Button)this.findViewById(R.id.btnAdd);
		Button btn2=(Button)this.findViewById(R.id.btnChange);
		Button btn3=(Button)this.findViewById(R.id.btnAbout);
		TextView tv1=(TextView)this.findViewById(R.id.textView2);
		if (cursor.moveToFirst()){
            do {
            	tv1.setText("Ŀǰ��ң�"+cursor.getString(cursor.getColumnIndex("money"))+"ö���һ�һ�ν�����5ö��");
            }while (cursor.moveToNext());
        }
		btn1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder=new Builder(HomeActivity.this);
				final EditText et1=new EditText(HomeActivity.this);
				builder.setTitle("Ҫ��ӵĴ���");
				builder.setView(et1);
				builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String todo= et1.getText().toString().trim();
						SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase("data/data/com.jsj.zyy/db232.db",null);
						String sql="insert into todolist(todo,user) values('"+todo+"','"+username+"')";
						db.execSQL(sql);
						db.close();
						Toast.makeText(HomeActivity.this, "��ӳɹ�,�����µ�¼", Toast.LENGTH_SHORT).show();
						finish();
					}
				});
				builder.setNegativeButton("ȡ��", null);
				builder.show();
			}
		});
		btn2.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase("data/data/com.jsj.zyy/db232.db",null);
				Cursor cursor = db.query("user", null, "uname=?", new String[] { username }, null, null, null);
				if (cursor.moveToFirst()){
					int money=cursor.getInt(cursor.getColumnIndex("money"));
					if(money<5)Toast.makeText(HomeActivity.this, "ʣ���Ҳ��㣡", Toast.LENGTH_SHORT).show();
					else{
						money-=5;
						ContentValues values = new ContentValues();
						values.put("money", money);
						db.update("user", values, "uname=?", new String[] {username});
						Toast.makeText(HomeActivity.this, "�һ��ɹ��������µ�¼", Toast.LENGTH_SHORT).show();
						finish();
					}
				}
			}
		});
		btn3.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(HomeActivity.this, AboutActivity.class);
				startActivity(it);
			}
		});
	}

}
