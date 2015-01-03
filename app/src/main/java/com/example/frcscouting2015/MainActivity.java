package com.example.frcscouting2015;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends Activity {

	//////////CONSTANTS///////////
    public static final String MATCH_NUM = "MatchNum";
	public static final String TEAM_NUM = "TeamNum";
	public static final String IS_RED = "isRed";
	
	public static RadioButton btnRed, btnBlue;
	
	public static int[] teamNums;

	//////////IMPLEMENTED METHODS//////////
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnRed = (RadioButton) findViewById(R.id.btn_red);
        btnBlue = (RadioButton) findViewById(R.id.btn_blue);
        
        btnRed.setButtonDrawable(R.drawable.chkbox_off);
        btnBlue.setButtonDrawable(R.drawable.chkbox_off);

        new TeamNumbers(this);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    //////////UTILITY METHODS//////////
    
    //checks if all data was entered
    public boolean dataEntered() {
    	EditText txtmatch = (EditText) this.findViewById(R.id.te_match_num);
		EditText txtteam = (EditText) this.findViewById(R.id.te_team_num);
		RadioButton btnBlue = (RadioButton) this.findViewById(R.id.btn_blue);
		RadioButton btnRed = (RadioButton) this.findViewById(R.id.btn_red);
		
		if(!txtmatch.getText().toString().matches("") && !txtteam.getText().toString().matches("")
			&& (btnBlue.isChecked() || btnRed.isChecked())){
			return true;
		}return false;
    }

    //starts match, sends user to match activity
    public void startMatch(View view) {
    	if(this.dataEntered()){

    		DataHandler.clear();

			Intent intent = new Intent(this, MatchActivity.class);

			EditText txtmatch = (EditText) this.findViewById(R.id.te_match_num);
			EditText txtteam = (EditText) this.findViewById(R.id.te_team_num);

			RadioButton btnRed = (RadioButton) this.findViewById(R.id.btn_red);

			String matchNum = txtmatch.getText().toString();
			String teamNum = txtteam.getText().toString();

			if(!TeamNumbers.isATeamNumber(Integer.parseInt(teamNum))){
				Toast.makeText(this,"That is not a valid team number.",Toast.LENGTH_SHORT).show();
				return;
			}

			boolean isRed = btnRed.isChecked();

			intent.putExtra(MATCH_NUM,matchNum);		//match number
			intent.putExtra(TEAM_NUM,teamNum);			//team number
			intent.putExtra(IS_RED,isRed);				//if alliance is red

			startActivity(intent);
    	}else{
    		Toast.makeText(this,"Please enter all the team's information.",Toast.LENGTH_SHORT).show();
    	}
	}
    
}
