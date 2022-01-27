package com.jsj.zyy;

import com.jsj.zyy.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MyFinalHomeworkActivity extends Activity {

	private EditText etUid;
	private EditText etPwd;
	private CheckBox cb;
	public static final String SP_INFO = "account";
	public static final String USERID = "UserId";
	public static final String PASSWORD = "password";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		etUid = (EditText) this.findViewById(R.id.editText1);
		etPwd = (EditText) this.findViewById(R.id.editText2);
		cb = (CheckBox) this.findViewById(R.id.checkBox1);
		Button btnLogin = (Button) this.findViewById(R.id.button1);
		Button btnCancle = (Button) this.findViewById(R.id.button2);
		Button btnReg = (Button) this.findViewById(R.id.button3);
		Button btnAbout = (Button) this.findViewById(R.id.button5);
		btnLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				String userName = etUid.getText().toString().trim();
				String password = etPwd.getText().toString().trim();
				SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("data/data/com.jsj.zyy/db232.db", null);
				Cursor cursor = db.query("user", null, "uname=? and upw=?", new String[] { userName, password }, null,
						null, null);
				if (cursor.getCount() > 0) {
					cursor.close();
					db.close();
					Intent it = new Intent(MyFinalHomeworkActivity.this, MainActivity.class);
					it.putExtra("user",userName);
					startActivity(it);
				} else {
					etUid.setText("");
					etPwd.setText("");
					Toast.makeText(MyFinalHomeworkActivity.this, "用户名或密码错误！", Toast.LENGTH_LONG).show();
				}
			}
		});
		checkIfRemember();
		/**
		 * 取消
		 */
		btnCancle.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				etUid.setText("");
				etPwd.setText("");
			}
		});
		/**
		 * 建表
		 */
//		Button btCreate=(Button)this.findViewById(R.id.button4);
//		btCreate.setOnClickListener(new View.OnClickListener() {
//			
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase("data/data/com.jsj.zyy/db232.db",null);
//				String sql="create table todolist(uid integer primary key autoincrement,todo text,user text)";
//				db.execSQL(sql);
////				sql="create table user(uid integer primary key autoincrement,uname text,upw text,money integer,finish integer)";
////				db.execSQL(sql);
//				db.close();
//			}
//		});
		btnReg.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(MyFinalHomeworkActivity.this, RegActivity.class);
				startActivity(it);
			}
		});
		btnAbout.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Builder dialog = new AlertDialog.Builder(MyFinalHomeworkActivity.this);
				dialog.setMessage("作者：张扬扬");
				dialog.setPositiveButton("好的", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(MyFinalHomeworkActivity.this, "谢谢！", Toast.LENGTH_SHORT).show();
					}
				});
				dialog.show();
			}
		});
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		if (cb.isChecked()) {
			String uid = etUid.getText().toString().trim();
			String pwd = etPwd.getText().toString().trim();
			rememberMe(uid, pwd);
		}
		super.onStop();
	}

	public void checkIfRemember() {
		SharedPreferences sp = getSharedPreferences(SP_INFO, MODE_PRIVATE);
		String uidStr = sp.getString(USERID, null);
		String pwdStr = sp.getString(PASSWORD, null);
		if (uidStr != null & pwdStr != null) {
			etUid.setText(uidStr);
			etPwd.setText(pwdStr);
			cb.setChecked(true);
		}
	}

	public void rememberMe(String uid, String pwd) {
		SharedPreferences sp = getSharedPreferences(SP_INFO, MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString(USERID, uid);
		editor.putString(PASSWORD, pwd);
		editor.commit();
	}
}
