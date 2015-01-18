package com.example.frcscouting2015;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

import net.glxn.qrgen.android.QRCode;

import database.DatabaseHandler;
import de.greenrobot.event.EventBus;


public class qr extends ActionBarActivity {

    private String output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        output = (String) EventBus.getDefault().removeStickyEvent(String.class);
        Bitmap bmp = QRCode.from(output).bitmap();
        //bmp.setHeight(500);
        //bmp.setWidth(500);
        ImageView myImage = (ImageView) findViewById(R.id.imageView);
        myImage.setMinimumHeight(470);
        myImage.setMinimumWidth(470);
        myImage.setImageBitmap(bmp);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    public void returnToMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void deleteAndExit(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHandler.getInstance(getApplicationContext()).clearTable();
                        returnToMain();
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

