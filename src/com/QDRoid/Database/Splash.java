package com.QDRoid.Database;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.splash);
	        Handler x=new Handler();
	        x.postDelayed(new splashHandler(), 2000);
	    }
	    public class splashHandler implements Runnable {

	    	public void run() {
	    		// TODO Auto-generated method stub
	    		
	    		startActivity(new Intent(getApplication(),Menu.class));
	    		Splash.this.finish();
	    	}

	    }
	}