package com.example.frcscouting2015;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
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

public class MatchActivity extends FragmentActivity {

    TeamData teamData;
    CheckBox inputRobotAuto;
    CheckBox inputToteAuto;
    EditText inputNumberTotesAuto;
    CheckBox inputContainerAuto;
    EditText inputNumberContainersAuto;
    CheckBox inputAssistedStackingAuto;
    EditText inputNumberTotesStackedAuto;


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
        String log = "" + teamData.getTeamNumber();
        Log.d("k", log);

    }

    public void addToDatabase() {

        inputRobotAuto = (CheckBox) AutoFragment.view.findViewById(R.id.auto_robot_checkbox);
        inputNumberTotesAuto = (EditText) AutoFragment.view.findViewById(R.id.auto_tote_number);
        inputNumberContainersAuto = (EditText) AutoFragment.view.findViewById(R.id.auto_container_number);
        inputNumberTotesStackedAuto = (EditText) AutoFragment.view.findViewById(R.id.auto_totes_stacked_number);
        int numberTotesAuto = 0;
        int numberContainersAuto = 0;
        int numberTotesStackedAuto = 0;
        if (inputNumberTotesAuto.length() > 0) {
            numberTotesAuto = Integer.parseInt(inputNumberTotesAuto.getText().toString());
        }
        if (inputNumberContainersAuto.length() > 0) {
            numberContainersAuto = Integer.parseInt(inputNumberContainersAuto.getText().toString());
        }
        if (inputNumberTotesStackedAuto.length() > 0) {
            numberTotesStackedAuto = Integer.parseInt(inputNumberTotesStackedAuto.getText().toString());
        }
        //auto add to teamdata
        teamData.setNumberTotesAuto(numberTotesAuto);
        teamData.setNumberContainersAuto(numberContainersAuto);
        teamData.setNumberStackedTotesAuto(numberTotesStackedAuto);
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
        CheckBox coop = (CheckBox) findViewById(R.id.coop);

        if (toteLevel1.isChecked()) {
            teamData.setToteLevel1(teamData.getToteLevel1() + 1);
        }
        if (toteLevel2.isChecked()) {
            teamData.setToteLevel2(teamData.getToteLevel2() + 1);
        }
        if (toteLevel3.isChecked()) {
            teamData.setToteLevel3(teamData.getToteLevel3() + 1);
        }
        if (toteLevel4.isChecked()) {
            teamData.setToteLevel4(teamData.getToteLevel4() + 1);
        }
        if (toteLevel5.isChecked()) {
            teamData.setToteLevel5(teamData.getToteLevel5() + 1);
        }
        if (toteLevel6.isChecked()) {
            teamData.setToteLevel6(teamData.getToteLevel6() + 1);
        }

        if (canLevel1.isChecked()) {
            teamData.setCanLevel1(teamData.getCanLevel1() + 1);
        }
        if (canLevel2.isChecked()) {
            teamData.setCanLevel2(teamData.getCanLevel2() + 1);
        }
        if (canLevel3.isChecked()) {
            teamData.setCanLevel3(teamData.getCanLevel3() + 1);
        }
        if (canLevel4.isChecked()) {
            teamData.setCanLevel4(teamData.getCanLevel4() + 1);
        }
        if (canLevel5.isChecked()) {
            teamData.setCanLevel5(teamData.getCanLevel5() + 1);
        }
        if (canLevel6.isChecked()) {
            teamData.setCanLevel6(teamData.getCanLevel6() + 1);
        }
        if (noodle.isChecked()) {
            teamData.setNoodle(teamData.getNoodle() + 1);
        }
        if (coop.isChecked()) {
            coop.setEnabled(false);
            teamData.setCoop(1);
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

    }

    public String makeString() {
        List<TeamData> teamData2 = DatabaseHandler.getInstance(this).getAllTeamData();
        String output = "";
        for (TeamData cn : teamData2) {
            String log = cn.getTeamNumber() + "," +
                    cn.getMatchNumber() + "," + cn.getAlliance() + "," +
                    cn.getRobotAuto() + "," + cn.getNumberTotesAuto() + ","
                    + cn.getNumberContainersAuto() + ","
                    + cn.getNumberStackedTotesAuto() + "," + cn.getToteLevel1() + "," + cn.getToteLevel2() + ","
                    + cn.getToteLevel3() + "," + cn.getToteLevel4() + "," + cn.getToteLevel5() + ","
                    + cn.getToteLevel6() + "," + cn.getCanLevel1() + "," + cn.getCanLevel2() + "," + cn.getCanLevel3() + "," +
                    cn.getCanLevel4() + "," + cn.getCanLevel5() + "," + cn.getCanLevel6() + "," +
                    cn.getNoodle() + "," + cn.getCoop();
            output = output + log + ":";
        }
        return output;
    }

    public void startQR() {
        String output = makeString();
        EventBus.getDefault().postSticky(output);
        Intent i = new Intent(getApplicationContext(), qr.class);
        startActivity(i);
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
                        if(!alreadyIn){
                            addToDatabase();
                            returnToMain();
                        }else{
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

    public void saveAndStartQR(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Save Data and Start QR")
                .setMessage("Are you sure you want to save and generate a qr code?")
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
                            startQR();
                        } else {
                            startQR();
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

