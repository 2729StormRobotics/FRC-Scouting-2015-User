package com.example.frcscouting2015;

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
         }

    public void addToDatabase(View view){


        new AlertDialog.Builder(this)
                .setTitle("Save Data")
                .setMessage("Are you sure you this info is correct?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with add
                        //Auto input

                        inputRobotAuto = (CheckBox) AutoFragment.view.findViewById(R.id.auto_robot_checkbox);
                        inputToteAuto = (CheckBox) AutoFragment.view.findViewById(R.id.auto_tote_checkbox);
                        inputNumberTotesAuto = (EditText) AutoFragment.view.findViewById(R.id.auto_tote_number);
                        inputContainerAuto = (CheckBox) AutoFragment.view.findViewById(R.id.auto_container_checkbox);
                        inputNumberContainersAuto = (EditText) AutoFragment.view.findViewById(R.id.auto_container_number);
                        inputAssistedStackingAuto = (CheckBox) AutoFragment.view.findViewById(R.id.auto_assisted_checkbox);
                        inputNumberTotesStackedAuto = (EditText) AutoFragment.view.findViewById(R.id.auto_totes_stacked_number);
                        int numberTotesAuto = 0;
                        int numberContainersAuto = 0;
                        int numberTotesStackedAuto = 0;
                        boolean robotAuto = inputRobotAuto.isChecked();
                        boolean toteAuto = inputToteAuto.isChecked();
                        if(inputNumberTotesAuto.length() > 0){
                            numberTotesAuto = Integer.parseInt(inputNumberTotesAuto.getText().toString());
                        }
                        boolean containerAuto = inputContainerAuto.isChecked();
                        if(inputNumberContainersAuto.length() > 0){
                            numberContainersAuto = Integer.parseInt(inputNumberContainersAuto.getText().toString());
                        }
                        boolean assistedStackingTotesAuto = inputContainerAuto.isChecked();
                        if(inputNumberTotesStackedAuto.length() > 0){
                            numberTotesStackedAuto = Integer.parseInt(inputNumberTotesStackedAuto.getText().toString());
                        }
                        //auto add to teamdata
                        teamData.setRobotAuto(robotAuto);
                        teamData.setToteAuto(toteAuto);
                        teamData.setNumberTotesAuto(numberTotesAuto);
                        teamData.setContainerAuto(containerAuto);
                        teamData.setNumberContainersAuto(numberContainersAuto);
                        teamData.setAssistedTotesAuto(assistedStackingTotesAuto);
                        teamData.setNumberStackedTotesAuto(numberTotesStackedAuto);
                        //updates database
                        DatabaseHandler.getInstance(getApplicationContext()).updateTeamData(teamData);
                        Toast.makeText(getApplicationContext(), "Data Saved.", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        //startActivity(intent);
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

    public void addStack(View view){


    }



}