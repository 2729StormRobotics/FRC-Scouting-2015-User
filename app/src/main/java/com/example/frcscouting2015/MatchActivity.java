package com.example.frcscouting2015;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scouter.fragments.AutoFragment;
import com.example.scouter.fragments.SubmitFragment;
import com.example.scouter.fragments.TeleOpFragment;
import com.example.scouter.help.MatchHelpActivity;

@SuppressLint("NewApi")
public class MatchActivity extends FragmentActivity implements TabListener,DialogInterface.OnClickListener{

	//////////CONSTANTS//////////
	public static final String EXTRA_MESSAGE = "com.example.scouter.MESSAGE";
	public static final String ASSISTS="Assists", THROWN="Thrown", CAUGHT="Caught";
	/////////////////////////////

	//////////VARIABLES//////////
	private String[] tabNames = {"Auto","Tele-Op", "Submit"};	//tab names

	public static AutoFragment fragmentAuto;					//autonomous fragment
	public static TeleOpFragment fragmentTeleOp;				//teleOp fragment
	public static SubmitFragment fragmentSubmit;				//submit fragment

	private static boolean dialogShot=false, dialogTop=false;

	private static MatchActivity copyActivity;

	public static FragmentManager fragmentManager;				//fragment manager
	private int zone;											//current zone id

	/////////////////////////////

	//////////IMPLEMENTED METHODS//////////
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		//sets up activity layout
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_match);

		//receives intent and extras
		Intent intent = this.getIntent();
		String matchNum = intent.getStringExtra(MainActivity.MATCH_NUM),
			   teamNum = intent.getStringExtra(MainActivity.TEAM_NUM);

		//initializes action bar
		final ActionBar actionBar = getActionBar();

		//initializes fragments
		fragmentAuto = new AutoFragment();
		fragmentTeleOp = new TeleOpFragment();
		fragmentSubmit = new SubmitFragment();

		//tries to convert match and team numbers to integers
		try
		{
			if(!DataHandler.isClockRunning()){
				DataHandler.setMatchNum(Integer.parseInt(matchNum));
				DataHandler.setTeamNum(Integer.parseInt(teamNum));
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}

		this.setTitle("Team " + DataHandler.getTeamNum() + ", Match " + DataHandler.getMatchNum());

		//starts user open autonomous fragment
		fragmentManager = this.getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.add(R.id.fragment_container,fragmentAuto);
		transaction.addToBackStack(null);
        transaction.commit();

	    // Specify that tabs should be displayed in the action bar.
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	    // Add 3 tabs, specifying the tab's text and TabListener
	    for (int i = 0; i < 3; i++) {
	        actionBar.addTab(actionBar.newTab().setText(tabNames[i]).setTabListener(this));
	    }

	    //setting the text of the zones
	    boolean isRed = intent.getBooleanExtra(MainActivity.IS_RED,false);
	    if(isRed){
	    	DataHandler.setToRedTeam();
	    	zone = DataHandler.FEEDER_ZONE;
	    }else{
	    	zone = DataHandler.GOAL_ZONE;
	    }

	    copyActivity = this;

	}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.match, menu);
		return true;
	}
	public void onTabReselected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		 String string = (String) tab.getText();

		 FragmentTransaction transaction = fragmentManager.beginTransaction();

         if(string == tabNames[0]){

        	 transaction.replace(R.id.fragment_container,fragmentAuto);
        	 if(DataHandler.isClockRunning())
        		 DataHandler.stopTimer();

         }else if(string == tabNames[1]){

        	 transaction.replace(R.id.fragment_container,fragmentTeleOp);
        	 if(DataHandler.isClockRunning())
        		 DataHandler.startTimer();

         }else if(string == tabNames[2]){

        	 transaction.replace(R.id.fragment_container,fragmentSubmit);
        	 if(DataHandler.isClockRunning())
        		 DataHandler.stopTimer();

         }

         transaction.addToBackStack(null);
         transaction.commit();
	}
	public void onTabUnselected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}
	public void onClick(DialogInterface dialog, int which) {
        switch (which){
        case DialogInterface.BUTTON_POSITIVE:

            if(dialogShot){

				if(dialogTop)
					DataHandler.addTopGoalTele(false);
				else
					DataHandler.addBottomGoalTele();

			}

			copyActivity.updateTopInfo();
			copyActivity.endCycle();

            break;
        case DialogInterface.BUTTON_NEGATIVE:
            break;
        }

        this.updateTopInfo();
    }
	public void onResume() {
		super.onResume();
		this.setTitle("Team " + DataHandler.getTeamNum() + ", Match "+ DataHandler.getMatchNum());
	}

	//////////////////////////////////////

	//////////UPDATING METHODS//////////
	//updates zone data and text
	public void updateZones() {
		boolean isRed = DataHandler.isRed();
		TextView tv_fTitle = (TextView)this.findViewById((isRed)?R.id.tv_redzone_title:R.id.tv_bluezone_title),
				 tv_gTitle = (TextView)this.findViewById((!isRed)?R.id.tv_redzone_title:R.id.tv_bluezone_title),
				 tv_fThrow = (TextView)this.findViewById((isRed)?R.id.tv_redzoneThrownAssist:R.id.tv_bluezoneThrownAssist),
				 tv_fCatch = (TextView)this.findViewById((isRed)?R.id.tv_redzoneCaughtAssist:R.id.tv_bluezoneCaughtAssist),
				 tv_tThrow = (TextView)this.findViewById(R.id.tv_tzoneThrownAssist),
				 tv_tCatch = (TextView)this.findViewById(R.id.tv_tzoneCaughtAssist),
				 tv_gThrow = (TextView)this.findViewById((!isRed)?R.id.tv_redzoneThrownAssist:R.id.tv_bluezoneThrownAssist),
				 tv_gCatch = (TextView)this.findViewById((!isRed)?R.id.tv_redzoneCaughtAssist:R.id.tv_bluezoneCaughtAssist);

		tv_fTitle.setText("Feeder Zone");
		tv_gTitle.setText("Goal Zone");

		tv_fThrow.setText(THROWN+": "+DataHandler.getThrownAssistsThisCycle(DataHandler.FEEDER_ZONE));
		tv_fCatch.setText(CAUGHT+": "+DataHandler.getCaughtAssistsThisCycle(DataHandler.FEEDER_ZONE));

		tv_tThrow.setText(THROWN+": "+DataHandler.getThrownAssistsThisCycle(DataHandler.TRUSS_ZONE));
		tv_tCatch.setText(CAUGHT+": "+DataHandler.getCaughtAssistsThisCycle(DataHandler.TRUSS_ZONE));

		tv_gThrow.setText(THROWN+": "+DataHandler.getThrownAssistsThisCycle(DataHandler.GOAL_ZONE));
		tv_gCatch.setText(CAUGHT+": "+DataHandler.getCaughtAssistsThisCycle(DataHandler.GOAL_ZONE));

		this.updateAssistText();
	}

	//updates score and cycle num at top of activity
	public void updateTopInfo() {
		TextView tv_cycles = (TextView)this.findViewById(R.id.tv_cycleNum),
				 tv_score = (TextView)this.findViewById(R.id.tv_scoreNum);

		tv_cycles.setText(""+DataHandler.getCycles());
		tv_score.setText(""+DataHandler.getScore());
	}

	//updates assist views text
	public void updateAssistText() {
		TextView catches = (TextView) this.findViewById(R.id.tv_TeleCaughtAssist),
				 passes = (TextView) this.findViewById(R.id.tv_TeleThrownAssist);

		passes.setText("Assists Thrown: "+DataHandler.getTotalThrownAssists());
		catches.setText("Assists Caught: "+DataHandler.getTotalCaughtAssists());
	}

	//updates text inside truss buttons
	public void updateScoringText() {
		Button	 btn_trussPass = (Button) this.findViewById(R.id.btn_trussThrow),
				 btn_trussCatch = (Button) this.findViewById(R.id.btn_trussReceive),
				 btn_trussMiss = (Button) this.findViewById(R.id.btn_trussMiss),
				 btn_miss = (Button) this.findViewById(R.id.btn_scoreMiss);

		btn_trussPass.setText("Throw: "+DataHandler.getTrussPassesThisCycle());
		btn_trussCatch.setText("Catch: "+DataHandler.getTrussCatchesThisCycle());
		btn_trussMiss.setText("Miss: "+DataHandler.getTrussMissesThisCycle());

		btn_miss.setText("Miss: "+DataHandler.getTopGoalMissesTele());
	}

	//updates all data and text in autonomous fragment
	public void updateAuto() {
		 Button btn_top = (Button)this.findViewById(R.id.btn_top),
				btn_topHot = (Button)this.findViewById(R.id.btn_topHot),
				btn_topMiss = (Button)this.findViewById(R.id.btn_topMiss),
				btn_bottom = (Button)this.findViewById(R.id.btn_bottom),
				btn_botHot = (Button)this.findViewById(R.id.btn_bottomHot);

		 btn_top.setText("Top: "+DataHandler.getTopGoalsAuto());
		 btn_topHot.setText("Top Hot: "+DataHandler.getTopHotGoals());
		 btn_bottom.setText("Bottom: "+DataHandler.getBotGoalsAuto());
		 btn_botHot.setText("Bot. Hot: "+DataHandler.getBotHotGoals());
		 btn_topMiss.setText("Miss: "+DataHandler.getTopGoalMissesAuto());
	}

	//ends the current cycle
	public void endCycle() {
		DataHandler.endCycle();
		Toast.makeText(this,"Cycle Ended. Length = "+DataHandler.getCycleLength(DataHandler.getCycles()-1)+" sec",Toast.LENGTH_SHORT).show();
		this.updateZones();
		this.updateAssistText();
		this.updateScoringText();
	}
	////////////////////////////////////////


	//////////BUTTON CLICK METHODS//////////
	//switches to the tab user pressed (BUTTON)
	public void switchTabs(View view) {
		 Button btn = (Button)view;
		 switch(btn.getId()){
		 case R.id.btn_toTeleOp:
			 this.getActionBar().selectTab(this.getActionBar().getTabAt(1));
			 if(DataHandler.getCycles()==0){DataHandler.endCycle();}
			 break;
		 case R.id.btn_endMatch:
			 this.getActionBar().selectTab(this.getActionBar().getTabAt(2));
			 break;
		 }
	}

	public boolean onOptionsItemSelected(MenuItem i) {
    	switch(i.getItemId()){
		case R.id.action_MatchHelp:
			Intent intent = new Intent(this,MatchHelpActivity.class);
			startActivity(intent);
			return true;
		default:
			return false;
    	}
    }

	//submits the data to be turned into a QR code
	public void submit(View view) {
		Intent intent = new Intent(this,QRGeneratorActivity.class);
		intent.putExtra(EXTRA_MESSAGE,DataHandler.getAllData()); 			//gets all the data in one long string
		intent.putExtra(MainActivity.MATCH_NUM,DataHandler.getMatchNum());	//gets the currents match number
		startActivity(intent); 												//starts QRGeneratorActivity
	}

	//handles all button clicks in autonomous
	public void buttonClickedAuto(View view) {
		Button btn = (Button)view;
		 switch(btn.getId()){

		 //scoring buttons
		 case R.id.btn_top:
			 DataHandler.addTopGoalAuto(false,false);
			 this.updateAuto();
			 break;
		 case R.id.btn_bottom:
			 DataHandler.addBottomGoalAuto(false);
			 this.updateAuto();
			 break;
		 case R.id.btn_topHot:
			 DataHandler.addTopGoalAuto(true,false);
			 this.updateAuto();
			 break;
		 case R.id.btn_bottomHot:
			 DataHandler.addBottomGoalAuto(true);
			 this.updateAuto();
			 break;

		 case R.id.btn_topMiss:
			 DataHandler.addTopGoalAuto(false,true);
			 this.updateAuto();
			 break;

		 case R.id.btn_clearAuto:
			 DataHandler.clearAuto();
			 this.updateAuto();
			 break;

		 //other buttons
		 case R.id.btn_defense:
			 CheckBox chk_def = (CheckBox) this.findViewById(R.id.chk_defense);
			 if(!DataHandler.didDefend()){
				 DataHandler.setDefense(true);
				 chk_def.setChecked(true);
			 }else{
				 DataHandler.setDefense(false);
				 chk_def.setChecked(false);
			 }
			 break;
		 case R.id.btn_moved:
			 CheckBox chk_mov = (CheckBox) this.findViewById(R.id.chk_move);
			 if(!DataHandler.didMove()){
				 DataHandler.setMoved(true);
				 chk_mov.setChecked(true);
			 }else{
				 DataHandler.setMoved(false);
				 chk_mov.setChecked(false);
			 }
			 break;
		 }
	}

	//handles all butto clicks in teleOp
	public void buttonClickedTele(View view) {
		 Button btn = (Button)view;
		 switch(btn.getId()){

		 //assist buttons
		 case R.id.btn_assistThrowAdd:
			 DataHandler.passed(zone);
			 this.updateZones();
			 break;
		 case R.id.btn_assistCaughtAdd:
			 DataHandler.caught(zone);
			 this.updateZones();
			 break;

		 case R.id.btn_assistThrowMinus:
			 DataHandler.cancelPass(zone);
			 this.updateZones();
			 break;
		 case R.id.btn_assistCaughtMinus:
			 DataHandler.cancelCatch(zone);
			 this.updateZones();
			 break;

		 //truss buttons
		 case R.id.btn_trussThrow:
			 DataHandler.trussPassed();
			 this.updateScoringText();
			 break;
		 case R.id.btn_trussMiss:
			 DataHandler.trussMissed();
			 this.updateScoringText();
			 break;
		 case R.id.btn_trussReceive:
			 DataHandler.trussCaught();
			 this.updateScoringText();
			 break;
		 case R.id.btn_trussClear:
			 DataHandler.clearTrussInfo();
			 this.updateScoringText();
			 break;

		 //scoring buttons
		 case R.id.btn_scoreTop:
			 dialog(true,true);
			 break;
		 case R.id.btn_scoreBottom:
			 dialog(true,false);
			 break;
		 case R.id.btn_scoreMiss:
			 DataHandler.addTopGoalTele(true);
			 this.updateScoringText();
			 break;
		 case R.id.btn_missSub:
			 DataHandler.cancelTopMiss();
			 this.updateScoringText();
			 break;

		 //end cycle
		 case R.id.btn_endCycle:
			 dialog(false,false);
			 break;

		 }
	 }

	public void dialog(boolean shot, boolean top) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("End cycle");
	    builder.setMessage("Are you sure?");
	    builder.setPositiveButton("Yes", this);
	    builder.setNegativeButton("No", this);
	    builder.show();

	    dialogShot = shot;
	    dialogTop = top;
	}

	//selects whichever zone user pressed
	public void selectZone(View view){
			View redZone = this.findViewById(R.id.layout_redzone),
				 tZone = this.findViewById(R.id.layout_tzone),
				 blueZone = this.findViewById(R.id.layout_bluezone);
			boolean isRed = DataHandler.isRed();


			switch(view.getId()){
			case R.id.layout_redzone:
				zone = (isRed)?DataHandler.FEEDER_ZONE:DataHandler.GOAL_ZONE;
				redZone.setBackgroundResource(R.drawable.high_rzone_border);
				tZone.setBackgroundResource(R.drawable.trans_wzone_border);
				blueZone.setBackgroundResource(R.drawable.trans_bzone_border);
				break;
			case R.id.layout_tzone:
				zone = DataHandler.TRUSS_ZONE;
				redZone.setBackgroundResource(R.drawable.trans_rzone_border);
				tZone.setBackgroundResource(R.drawable.high_wzone_border);
				blueZone.setBackgroundResource(R.drawable.trans_bzone_border);
				break;
			case R.id.layout_bluezone:
				zone = (!isRed)?DataHandler.FEEDER_ZONE:DataHandler.GOAL_ZONE;
				redZone.setBackgroundResource(R.drawable.trans_rzone_border);
				tZone.setBackgroundResource(R.drawable.trans_wzone_border);
				blueZone.setBackgroundResource(R.drawable.high_bzone_border);
				break;
			}
		}
	//////////////////////////////////////////

}
