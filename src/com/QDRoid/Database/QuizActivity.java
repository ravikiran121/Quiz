package com.QDRoid.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    
       Button Next=(Button)findViewById(R.id.Quiz);
        Next.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(QuizActivity.this,InsertUserAnswer.class);
				startActivity(i);
				
			}
		}); 
        
        try{
        	String destPath="data/data/"+getPackageName()+"/databases/QuizDb";
        	File f=new File(destPath);
        	if(!f.exists()){
        		CopyDB(getBaseContext().getAssets().open("QuizDb"),new FileOutputStream(destPath));
        		
        	}
        	}
        catch(FileNotFoundException e){
        	e.printStackTrace();
        }
        catch(IOException e){
        	e.printStackTrace();
        }
   
    
    
  DBAdapter db1=new DBAdapter(this);
    db1.open();
//long id=db1.InsertQuestion("Is my program working?", "yes", "No", "Can't say", "i am confused","yes",null);
//long id2=db1.InsertQuestion("Who will host KBC season 6?", "Amitabh bachchan", "SRK", "Can't say", "i am confused","SRK",null);
   /* Cursor c=db1.getRecord(1);
    if(c.moveToFirst()){
    	do{
    		displayRecords(c);
    	  }
    	while(c.moveToNext());
    	}
    db1.close();  
    
    db1.open();
    Cursor c1=db1.getRecord(2);
    if(c.moveToFirst()){
    	do{
    		displayRecords(c1);
    	  }
    	while(c1.moveToNext());
    	}
    db1.close();
    */
   // int score=0;
    db1.open();
   Cursor c3=db1.CorrectAnswer();
    //c3.getCount();
    Toast.makeText(this,"Your score is:"+c3.getCount(),Toast.LENGTH_SHORT).show();
    db1.close();
    
    
    }

	public void CopyDB(InputStream is, FileOutputStream os)throws IOException {
		// TODO Auto-generated method stub
		byte[] buffer=new byte[1024];
		int length;
		while((length=is.read(buffer))>0){
			os.write(buffer,0,length);
		}
		is.close();
		os.close();
	}
	public void displayRecords(Cursor c){
		Toast.makeText(this,"id:"+c.getString(1)+"\n"+"Question:"+c.getString(2),Toast.LENGTH_SHORT).show();
	}
	public void displayCorrectRecords(Cursor c){
		Toast.makeText(this,"Question:"+c.getString(1)+"\n"+"Correct Answer:"+c.getInt(6),Toast.LENGTH_SHORT).show();
	}
	 public void insertuseranswer(View view)
	    {
	    	
	    	Intent i = new Intent(" com.QDRoid.Database");
	    	startActivity(i);
	    	Log.d("TAG", "Clicked");
	    }
}