package database;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import com.example.frcscouting2015.R;
import com.example.frcscouting2015.MatchActivity;


public class QRGeneratorActivity extends Activity implements DialogInterface.OnClickListener{

    //////////INHERITED METHODS//////////
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show the Up button in the action bar.
        setupActionBar();

        //gets input from submit
        Intent i = getIntent();
        String input = i.getStringExtra(MatchActivity.EXTRA_MESSAGE);

        //sets the title of the activity
        this.setTitle("Ready to Scan");

        //creates a new QR code from the given input string
        DrawQRCode draw = new DrawQRCode(this, input);

        //shows and draws the generated QR code
        setContentView(R.layout.activity_qrgen);
        addContentView(draw,new LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.WRAP_CONTENT));

    }

    //Sets up the action bar if the build version is recent enough
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.help, menu);
        return true;
    }

    //this runs every time an item on the action bar is selected
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            //the home button
            case android.R.id.home:
			
			/*
			 * This creates a new dialog box for the user. We designate all the
			 * text and give it a positive and a negative button. When the user
			 * presses one of these buttons, it runs the onClick() method below.
			 */
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Return to Main Page");
                builder.setMessage("Exiting this page will return you to the main page." +
                        " All data will be erased.");
                builder.setPositiveButton("Yes", this);
                builder.setNegativeButton("No", this);
                builder.show();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //runs when the user responds to a dialog box
    public void onClick(DialogInterface dialog, int which) {

        if(which == DialogInterface.BUTTON_POSITIVE)
			
			/*
			 * In the manifest we designate a parent activity (or home) for this activity.
			 * The method below, navigateUpFromSameTask(), will send the user to the current
			 * activity's parent.
			 */

            NavUtils.navigateUpFromSameTask(this);
    }

}