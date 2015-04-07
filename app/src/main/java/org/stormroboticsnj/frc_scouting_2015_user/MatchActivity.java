package org.stormroboticsnj.frc_scouting_2015_user;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import adapter.ViewPagerAdapter;
import database.DatabaseHandler;
import database.TeamData;
import de.greenrobot.event.EventBus;
import fragments.AutoFragment;
import fragments.CoopFragment;
import fragments.SubmitFragment;

public class MatchActivity extends FragmentActivity {

    TeamData teamData;
    CheckBox inputRobotAuto;
    EditText inputNumberTotesAuto;
    EditText inputNumberContainersAuto;
    EditText inputNumberTotesStackedAuto;
    EditText notesText;
    EditText containersCenterAuto;

    CheckBox coop1Chk;
    CheckBox coop2Chk;
    CheckBox coop3Chk;
    CheckBox coop4Chk;

    CheckBox troubleNoodle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_match);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Locate the viewpager in activity_main.xml
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        // Set the ViewPagerAdapter into ViewPager
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        teamData = (TeamData) EventBus.getDefault().removeStickyEvent(TeamData.class);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    public void addToDatabase() {

        inputRobotAuto = (CheckBox) AutoFragment.view.findViewById(R.id.auto_robot_checkbox);
        inputNumberTotesAuto = (EditText) AutoFragment.view.findViewById(R.id.auto_tote_number);
        inputNumberContainersAuto = (EditText) AutoFragment.view.findViewById(R.id.auto_container_number);
        inputNumberTotesStackedAuto = (EditText) AutoFragment.view.findViewById(R.id.auto_totes_stacked_number);
        notesText = (EditText) SubmitFragment.view.findViewById(R.id.notes_txt);
        containersCenterAuto = (EditText) AutoFragment.view.findViewById(R.id.containers_center_text);

        coop1Chk = (CheckBox) CoopFragment.view.findViewById(R.id.coop_level1);
        coop2Chk = (CheckBox) CoopFragment.view.findViewById(R.id.coop_level2);
        coop3Chk = (CheckBox) CoopFragment.view.findViewById(R.id.coop_level3);
        coop4Chk = (CheckBox) CoopFragment.view.findViewById(R.id.coop_level4);

        troubleNoodle = (CheckBox) SubmitFragment.view.findViewById(R.id.troublenoodle);

        int numberTotesAuto = 0;
        boolean robotAuto = false;
        int numberContainersAuto = 0;
        int numberTotesStackedAuto = 0;
        int numberContainersCenterAuto = 0;

        String notes = "";
        robotAuto = inputRobotAuto.isChecked();
        if (inputNumberTotesAuto.length() > 0) {
            numberTotesAuto = Integer.parseInt(inputNumberTotesAuto.getText().toString());
        }
        if (inputNumberContainersAuto.length() > 0) {
            numberContainersAuto = Integer.parseInt(inputNumberContainersAuto.getText().toString());
        }
        if (inputNumberTotesStackedAuto.length() > 0) {
            numberTotesStackedAuto = Integer.parseInt(inputNumberTotesStackedAuto.getText().toString());
        }
        if (containersCenterAuto.length() > 0) {
            numberContainersCenterAuto = Integer.parseInt(containersCenterAuto.getText().toString());
        }


        if(coop1Chk.isChecked()){
            teamData.setCoopLevel1(teamData.getCoopLevel1() + 1);
        }
        if(coop2Chk.isChecked()){
            teamData.setCoopLevel2(teamData.getCoopLevel2() + 1);
        }
        if(coop3Chk.isChecked()){
            teamData.setCoopLevel3(teamData.getCoopLevel3() + 1);
        }
        if(coop4Chk.isChecked()){
           teamData.setCoopLevel4(teamData.getCoopLevel4() + 1);
        }

        //auto add to teamdata
        teamData.setNumberTotesAuto(numberTotesAuto);
        teamData.setNumberContainersAuto(numberContainersAuto);
       // Log.d("number totes stacked auto add to databse", "" + numberTotesStackedAuto);
        teamData.setNumberStackedTotesAuto(numberTotesStackedAuto);
        notes = notesText.getText().toString();
        if(troubleNoodle.isChecked()){
            notes += " Trouble moving over noodle";
        }
        teamData.setNotes(notes);
        teamData.setContainers_center_auto(numberContainersCenterAuto);
        teamData.setRobotAuto(robotAuto);
        //Log.d("containers center", "" + numberContainersCenterAuto);

        //updates database
        DatabaseHandler.getInstance(this).addTeamData(teamData);
        Toast.makeText(getApplicationContext(), "Data Saved.", Toast.LENGTH_SHORT).show();
    }


    public void addStack(View view) {
        CheckBox toteLevel1 = (CheckBox) findViewById(R.id.tote_level1);
        CheckBox toteLevel2 = (CheckBox) findViewById(R.id.tote_level2);
        CheckBox toteLevel3 = (CheckBox) findViewById(R.id.tote_level3);
        CheckBox toteLevel4 = (CheckBox) findViewById(R.id.tote_level4);
        CheckBox toteLevel5 = (CheckBox) findViewById(R.id.tote_level5);
        CheckBox toteLevel6 = (CheckBox) findViewById(R.id.tote_level6);

        CheckBox canLevel1 = (CheckBox) findViewById(R.id.can_level1);
        CheckBox canLevel2 = (CheckBox) findViewById(R.id.can_level2);
        CheckBox canLevel3 = (CheckBox) findViewById(R.id.can_level3);
        CheckBox canLevel4 = (CheckBox) findViewById(R.id.can_level4);
        CheckBox canLevel5 = (CheckBox) findViewById(R.id.can_level5);
        CheckBox canLevel6 = (CheckBox) findViewById(R.id.can_level6);

        CheckBox noodle = (CheckBox) findViewById(R.id.noodle);

        boolean changed = false;


        if (toteLevel1.isChecked()) {
            teamData.setToteLevel1(teamData.getToteLevel1() + 1);
            changed = true;
        }
        if (toteLevel2.isChecked()) {
            teamData.setToteLevel2(teamData.getToteLevel2() + 1);
            changed = true;
        }
        if (toteLevel3.isChecked()) {
            teamData.setToteLevel3(teamData.getToteLevel3() + 1);
            changed = true;
        }
        if (toteLevel4.isChecked()) {
            teamData.setToteLevel4(teamData.getToteLevel4() + 1);
            changed = true;
        }
        if (toteLevel5.isChecked()) {
            teamData.setToteLevel5(teamData.getToteLevel5() + 1);
            changed = true;
        }
        if (toteLevel6.isChecked()) {
            teamData.setToteLevel6(teamData.getToteLevel6() + 1);
            changed = true;
        }

        if (canLevel1.isChecked()) {
            teamData.setCanLevel1(teamData.getCanLevel1() + 1);
            changed = true;
        }
        if (canLevel2.isChecked()) {
            teamData.setCanLevel2(teamData.getCanLevel2() + 1);
            changed = true;
        }
        if (canLevel3.isChecked()) {
            teamData.setCanLevel3(teamData.getCanLevel3() + 1);
            changed = true;
        }
        if (canLevel4.isChecked()) {
            teamData.setCanLevel4(teamData.getCanLevel4() + 1);
            changed = true;
        }
        if (canLevel5.isChecked()) {
            teamData.setCanLevel5(teamData.getCanLevel5() + 1);
            changed = true;
        }
        if (canLevel6.isChecked()) {
            teamData.setCanLevel6(teamData.getCanLevel6() + 1);
            changed = true;
        }
        if(noodle.isChecked()){
            teamData.setNoodle(teamData.getNoodle() + 1);
            changed = true;
        }

        toteLevel1.setChecked(false);
        toteLevel2.setChecked(false);
        toteLevel3.setChecked(false);
        toteLevel4.setChecked(false);
        toteLevel5.setChecked(false);
        toteLevel6.setChecked(false);

        canLevel1.setChecked(false);
        canLevel2.setChecked(false);
        canLevel3.setChecked(false);
        canLevel4.setChecked(false);
        canLevel5.setChecked(false);
        canLevel6.setChecked(false);
        noodle.setChecked(false);


        if(changed){
            Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show();
            changed = false;
        }


    }

    public void returnToMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void saveAndExit(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Save Data and Return to Main Screen")
                .setMessage("Are you sure you want to save and return to the main screen?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        boolean alreadyIn = false;
                        List<TeamData> teamDataList = DatabaseHandler.getInstance(getApplicationContext()).getAllTeamData();
                        for (TeamData cn : teamDataList) {
                            if (cn.getTeamNumber() == teamData.getTeamNumber() && cn.getMatchNumber() == teamData.getMatchNumber()) {
                                alreadyIn = true;
                            }
                        }
                        if (!alreadyIn) {
                            addToDatabase();
                            returnToMain();
                        } else {
                            returnToMain();
                        }


                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}

