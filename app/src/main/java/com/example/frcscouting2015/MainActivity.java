package com.example.frcscouting2015;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

import database.DatabaseHandler;
import database.TeamData;
import de.greenrobot.event.EventBus;

public class MainActivity extends Activity {

	//////////CONSTANTS///////////
    public static final String MATCH_NUM = "MatchNum";
	public static final String TEAM_NUM = "TeamNum";
	public static final String IS_RED = "isRed";
    public TeamData teamData;
	
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

        btnRed.setOnCheckedChangeListener(new OnCheckedChangeListener(){

            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                if(btnRed.isChecked())
                    btnRed.setBackgroundResource(R.drawable.high_rzone_border);
                else
                    btnRed.setBackgroundResource(R.drawable.trans_rzone_border);

            }

        });

        btnBlue.setOnCheckedChangeListener(new OnCheckedChangeListener(){

            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                if(btnBlue.isChecked())
                    btnBlue.setBackgroundResource(R.drawable.high_bzone_border);
                else
                    btnBlue.setBackgroundResource(R.drawable.trans_bzone_border);

            }

        });

        new TeamNumbers(this);
        DatabaseHandler db = DatabaseHandler.getInstance(this);
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

            /**
             * CRUD Operations
             * */

             // Inserting Team Data
            DatabaseHandler.getInstance(this).clearTable();
            TeamData teamData = new TeamData(Integer.parseInt(teamNum), Integer.parseInt(matchNum),
                    isRed,false,false,0,false,0,false,0);
            DatabaseHandler.getInstance(this).addTeamData(teamData);
            List<TeamData> teamData2 = DatabaseHandler.getInstance(this).getAllTeamData();
            for (TeamData cn : teamData2) {
                String log = "Id: "+cn.getID()+" ,Name: " + cn.getTeamNumber() + " ,match: " +
                        cn.getMatchNumber() + " ,alliance" + cn.getAlliance() + " ,robotauto" +
                        cn.getRobotAuto();
                // Writing Contacts to log
                Log.d("Name: ", log);
            }
            EventBus.getDefault().postSticky(teamData);
            startActivity(intent);
    	}else{
    		Toast.makeText(this,"Please enter all the team's information.", Toast.LENGTH_SHORT).show();
    	}
	}
    public void hideKeys(View view) {
        EditText myEditText = (EditText) findViewById(R.id.te_match_num);
        InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(myEditText.getWindowToken(), 0);
    }


    
}
