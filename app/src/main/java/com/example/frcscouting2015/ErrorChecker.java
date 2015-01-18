package com.example.frcscouting2015;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ErrorChecker extends ActionBarActivity {

    String error = "RVZBTiBBTExBTiBUSEUgTEVHRU5E";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_checker);
        Toast.makeText(this, new String(Base64.decode(error,Base64.DEFAULT)), Toast.LENGTH_SHORT).show();
//        Uri otherPath = Uri.parse("android.resource://com.example.frcscouting2015/drawable/");
//        File from      = new File(otherPath.toString(), "errorcorrectionkey.txt");
//        File to        = new File(otherPath.toString(),  "errorcorrectionkey.jpg");
//        from.renameTo(to);
//        ImageView errorFixerUpper = (ImageView) findViewById(R.id.imageView3);
//        Drawable d = Drawable.createFromPath(to.toString());
//        errorFixerUpper.setBackground(d);

        try {

            AssetManager manager = this.getAssets();
            InputStream input = manager.open("errors.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String total = reader.readLine();
            reader.close();

            byte[] imageAsBytes = Base64.decode(total.getBytes(), Base64.DEFAULT);
            ImageView image = (ImageView)this.findViewById(R.id.imageView3);
            image.setImageBitmap(
                    BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
            );

        } catch (IOException ex) {

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_error, menu);
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

   public static boolean isError(String code){
       return code.equals("NjY2");
   }

   public static boolean isFixableError(String fix){
        return fix.equals("MTAyNg==");
    }

}
