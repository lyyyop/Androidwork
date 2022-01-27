package com.jsj.zyy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;

public class TodoActivity extends Activity {
	ListView lv;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.todo);
		lv = (ListView) this.findViewById(R.id.listView1);
		Button btn1=(Button)this.findViewById(R.id.btnAdd);
		final String username = getIntent().getStringExtra("username");
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("data/data/com.jsj.zyy/db232.db", null);
		Cursor cursor = db.query("todolist", null, "user=?", new String[] { username }, null, null, null);
		
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		if (cursor.moveToFirst()) {
			while (cursor.moveToNext()) {
				Map<String,Object> todo = new HashMap<String,Object>();
				todo.put("todo",cursor.getString(cursor.getColumnIndex("todo")));
				data.add(todo);
			}
		}
		/**
		 * �������Ĵ��� ��һ�������������Ļ��� 
		 * �ڶ�����������׼��ʽ������ 
		 * ��������������Ӧ�����ļ�
		 * ���ĸ���������������ĸ�������
		 * ����������������������ID�ţ�Ҫ������ֵ���Ӧ
		 */
		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item, new String[] { "todo" },
				new int[] { R.id.todo }){
					@Override
					
					public View getView(int position, View convertView, ViewGroup parent) {
						View view=super.getView(position, convertView, parent);
						Button btn1=(Button)view.findViewById(R.id.btnDelete);
						Button btn2=(Button)view.findViewById(R.id.btnDone);
						TextView tv1=(TextView)view.findViewById(R.id.todo);
						final String tv=tv1.getText().toString();
						btn1.setOnClickListener(new View.OnClickListener() {
							
							public void onClick(View v) {
								SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("data/data/com.jsj.zyy/db232.db", null);
								db.delete("todolist", "todo=?",new String[] {tv});
								Toast.makeText(TodoActivity.this, "�޸ĳɹ��������µ�¼", Toast.LENGTH_SHORT).show();
								finish();
							}
						});
						/**
						 * ���
						 */
						btn2.setOnClickListener(new View.OnClickListener() {
							
							public void onClick(View v) {
								SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("data/data/com.jsj.zyy/db232.db", null);
								Cursor cursor = db.query("user", null, "uname=?", new String[] { username }, null, null, null);
								db.delete("todolist", "todo=?",new String[] {tv});
								if (cursor.moveToFirst()){
									int money=cursor.getInt(cursor.getColumnIndex("money"));
									int finish=cursor.getInt(cursor.getColumnIndex("finish"));
									money+=1;
									finish+=1;
									ContentValues values = new ContentValues();
									values.put("money", money);
									values.put("finish", finish);
									db.update("user", values, "uname=?", new String[] {username});
								}
								
								Toast.makeText(TodoActivity.this, "�޸ĳɹ��������µ�¼", Toast.LENGTH_SHORT).show();
								finish();
							}
						});
						return view;
					}
			
		};
		lv.setAdapter(adapter);
		btn1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder=new Builder(TodoActivity.this);
				final EditText et1=new EditText(TodoActivity.this);
				builder.setTitle("Ҫ��ӵĴ���");
				builder.setView(et1);
				builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("data/data/com.jsj.zyy/db232.db", null);
						String todo= et1.getText().toString().trim();
						String sql="insert into todolist(todo,user) values('"+todo+"','"+username+"')";
						db.execSQL(sql);
						Toast.makeText(TodoActivity.this, "��ӳɹ��������µ�¼", Toast.LENGTH_SHORT).show();
						finish();
					}
				});
				builder.setNegativeButton("ȡ��", null);
				builder.show();
			}
		});
		
	}

}
