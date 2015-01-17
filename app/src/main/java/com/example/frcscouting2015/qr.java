package com.example.frcscouting2015;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import net.glxn.qrgen.android.QRCode;

import database.TeamData;
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
        myImage.setMinimumHeight(500);
        myImage.setMinimumWidth(500);
        myImage.setImageBitmap(bmp);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_qr, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
