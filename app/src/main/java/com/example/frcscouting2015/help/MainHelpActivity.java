package com.example.frcscouting2015.help;

import com.example.frcscouting2015.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainHelpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_help);
		this.setTitle("Help");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help, menu);
		return true;
	}

}
