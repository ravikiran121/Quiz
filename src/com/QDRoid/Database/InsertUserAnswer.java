package com.QDRoid.Database;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class InsertUserAnswer extends Activity {
	
	int CurrentQues=1;
	
	DBAdapter db=new DBAdapter(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addanswer);
        //int CurrentQues=1;
        addAnswer();
		
        Button Next=(Button)findViewById(R.id.next);
        Next.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CurrentQues++;
				addAnswer();
			}
		}); 
        Button ViewScore=(Button)findViewById(R.id.ViewScore);
        ViewScore.setOnClickListener(new View.OnClickListener() {
        	
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(InsertUserAnswer.this,QuizActivity.class);
				startActivity(i);
				
			}
		}); 
			
	}
	public void addAnswer(){
		Log.d("Test","Adding");
		
		TextView question=(TextView)findViewById(R.id.question);
		RadioButton opt1=(RadioButton)findViewById(R.id.r1);
		RadioButton opt2=(RadioButton)findViewById(R.id.radioButton2);
		RadioButton opt3=(RadioButton)findViewById(R.id.radioButton3);
		RadioButton opt4=(RadioButton)findViewById(R.id.radioButton4);
		
		final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioAnswer);
        OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i("Test", "onCheckedChanged() id:" + checkedId);
                String ans;
                int radioBtnChecked = radioGroup.getCheckedRadioButtonId();      
                // if (radioBtnChecked <= 0) {
                //   radioText = "None selected";      
                // } 
                // else {
                     RadioButton rBtn = (RadioButton) findViewById(radioBtnChecked);
                     ans = rBtn.getText().toString();
                     Log.i("radio","radio button name:"+ans);
              
              db.open();
              db.Updatequestiontable(CurrentQues, ans);
              db.close();
            }
        };
        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
		
		db.open();
		Cursor c=db.getRecord(CurrentQues);
		 if(c.moveToFirst()){
		    	do{
		    		//displayQuestion(c);
		    		question.setText(c.getString(1));
					opt1.setText(c.getString(2));
					opt2.setText(c.getString(3));
					opt3.setText(c.getString(4));
					opt4.setText(c.getString(5));
		    	  }
		    	while(c.moveToNext());}
		 
	db.close();	 
	}
		
	public void viewScore(View v) {
		Intent i = new Intent(this, QuizActivity.class);
		startActivity(i);
	}

}
