package com.QDRoid.Database;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu){
		//super.onCreateOptionsMenu(menu);
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.test_menu,(android.view.Menu) menu);
	    return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.item1:     Toast.makeText(this, "Okay lets play!", Toast.LENGTH_LONG).show();
	        setContentView(R.layout.main);
	        Intent launchNewIntent = new Intent(Menu.this,QuizActivity.class);
	        startActivityForResult(launchNewIntent, 0);
	        break;
	        case R.id.item3: Toast.makeText(this, "Lets See How to play!", Toast.LENGTH_LONG).show();
	        Intent launchIntent = new Intent(Menu.this,AndroidActivity.class);
	        startActivityForResult(launchIntent, 0);
	        break;
	        case R.id.item4: Toast.makeText(this, "Bye Bye!", Toast.LENGTH_LONG).show();
            break;
	    }
	    return true;
	}
}