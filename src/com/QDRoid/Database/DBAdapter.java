package com.QDRoid.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	
	public static final String ID="id";
	public static final String Question="question";
	public static final String Option1="option1";
	public static final String Option2="option2";
	public static final String Option3="option3";
	public static final String Option4="option4";
	public static final String CorrectAnswer="correctAnswer";
	public static final String UserAnswer="userAnswer";
	public static final String TAG="DBAdapter";
	public static final String DatabaseName="QuizDb";
	public static final String tableName="QuestionTable";
	public static final int DatabaseVersion=10;
	private Context context;
	private DatabaseHelper Dbhelper;
	private SQLiteDatabase db;
	
	
	public static final String Create_Database=
"create table if not exists QuestionTable(id integer primary key autoincrement," +
"question VARCHAR,option1 VARCHAR,option2 VARCHAR,option3 VARCHAR,option4 VARCHAR,correctAnswer VARCHAR," +
"userAnswer VARCHAR default null);";
	
	
	
	public DBAdapter(Context ctx) {
		// TODO Auto-generated constructor stub
		this.context=ctx;
		Dbhelper=new DatabaseHelper(context);
		//db=openOrCreateDatabase("MyDB", MODE_PRIVATE,null);
	}
	public class DatabaseHelper extends SQLiteOpenHelper{
		DatabaseHelper(Context context){
			super(context,DatabaseName,null,DatabaseVersion);
			}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try{
				db.execSQL(Create_Database);
			Log.d("yes","table created");
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
			// TODO Auto-generated method stub
			Log.w(TAG,"Upgrading database from version"+oldversion+"to"+newversion+"which  will be destroyed");
		db.execSQL("DROP TABLE IF EXISTS QuestionTable");
		db.execSQL("DROP TABLE IF EXISTS Question");
			onCreate(db);
		}
	}
		
		public DBAdapter open() throws SQLException{
			
			db=Dbhelper.getWritableDatabase();
			return this;
		}
		
		public void close(){
			Dbhelper.close();
			
		}
		public long InsertQuestion(String quest,String opt1,String opt2,String opt3,String opt4,String cans,String usanswer)
		{
			db= Dbhelper.getWritableDatabase();
			ContentValues initialValues=new ContentValues();
			initialValues.put(Question, quest);
			initialValues.put(Option1, opt1);
			initialValues.put(Option2, opt2);
			initialValues.put(Option3, opt3);
			initialValues.put(Option4, opt4);
			initialValues.put(CorrectAnswer, cans);
			initialValues.put(UserAnswer, usanswer);
			
			return db.insert("QuestionTable", null,initialValues);
			
		}
		
		public Cursor getRecord(long id) throws SQLException{
		
			Cursor mcusor=db.query(true,tableName,new String[] {ID,Question,Option1,Option2,Option3,Option4},ID+"="+id,null,null,null,null,null);
		if(mcusor!=null){
			mcusor.moveToFirst();
		}
		return mcusor;
		}
		public Cursor CorrectAnswer() throws SQLException{
			
			Cursor mcusor=db.query(true,tableName,new String[] {ID,Question,Option1},CorrectAnswer+"="+UserAnswer,null,null,null,null,null);
		if(mcusor!=null){
			mcusor.moveToFirst();
		}
		return mcusor;
		}
		
		public boolean Updatequestiontable(long rowid,String userans){
			db= Dbhelper.getWritableDatabase();
			ContentValues args=new ContentValues();
			args.put(UserAnswer, userans);
			return db.update(tableName,args,ID+"="+rowid,null)>0;
		}

		public Cursor getAllRecords() {
			db= Dbhelper.getWritableDatabase();
			// TODO Auto-generated method stub
			return db.query(tableName, new String[] {Question},null,null,null,null,null);
		}

		
	}
	
	

