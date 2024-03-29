package org.stormroboticsnj.frc_scouting_2015_user;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import net.glxn.qrgen.android.QRCode;

import java.io.ByteArrayOutputStream;

import database.DatabaseHandler;
import de.greenrobot.event.EventBus;


public class qr extends ActionBarActivity {

    private String output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        output = EventBus.getDefault().removeStickyEvent(String.class);
        Display disp = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        ByteArrayOutputStream code = QRCode.from(output).withSize(size.x-10,size.x-10).stream();
        byte[] byteArray = code.toByteArray();
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        ImageView myImage = (ImageView) findViewById(R.id.imageView);

        myImage.setMinimumHeight(size.y-10);
        myImage.setMinimumWidth(size.x-10);
        myImage.setMaxHeight(size.y-10);
        myImage.setMaxWidth(size.x-10);
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

