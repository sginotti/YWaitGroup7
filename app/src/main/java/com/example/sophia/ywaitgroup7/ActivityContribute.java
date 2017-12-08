package com.example.sophia.ywaitgroup7;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.sql.RowSetReader;

public class ActivityContribute extends Activity implements View.OnClickListener{

    private ImageView imageViewEstPic;
    private TextView textViewEstName;
    private TextView textViewWaitQ;
    private SeekBar seekBarWaitTime;
    private TextView textViewPeepQ;
    private SeekBar seekBarPeepNum;
    private ImageButton imageButtonMedia;
    private Button buttonContSubmit;
    private Button buttonCancel;
    private TextView textViewMins;
    private TextView textViewPeeps;

    public int waitTime, waitPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribute);

        imageViewEstPic = (ImageView) findViewById(R.id.imageViewEstPic);
        textViewEstName = (TextView) findViewById(R.id.textViewEstName);
        textViewWaitQ = (TextView) findViewById(R.id.textViewWaitQ);
        seekBarWaitTime = (SeekBar) findViewById(R.id.seekBarWaitTime);
        textViewPeepQ = (TextView) findViewById(R.id.textViewPeepQ);
        seekBarPeepNum = (SeekBar) findViewById(R.id.seekBarPeepNum);
        imageButtonMedia = (ImageButton) findViewById(R.id.imageButtonMedia);
        buttonContSubmit = (Button) findViewById(R.id.buttonContSubmit);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);

        //Assigns Picture and Name per Selected Item
        textViewEstName.setText(ActivityHome.keepName);
        imageViewEstPic.setImageResource(ActivityHome.keepNumber);

        buttonContSubmit.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
        imageButtonMedia.setOnClickListener(this);

        seekbarWT();
        seekbarPeeps();
    }

    @Override
    public void onClick(View view) {

        //String estNode = textViewEstName.getText().toString();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference recordRef = db.getReference("Mash");

        if (view.getId() == R.id.buttonContSubmit) {

            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = mAuth.getCurrentUser();
            String uid = user.getUid();

            //String ts = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            //Double dblTimestamp = Double.parseDouble(ts.replace("_", ""));
            long longTimestamp = System.currentTimeMillis()/1000;

            Record myRecord = new Record(uid, waitTime, waitPeople, longTimestamp);
            //recordRef.child("Data").child(ts).setValue(myRecord);
            recordRef.child("Data2").push().setValue(myRecord);

            startActivity(new Intent(ActivityContribute.this, ActivityEstPage.class));

        } else if (view.getId() == R.id.buttonCancel){
            Intent intentCancel = new Intent(this, ActivityEstPage.class);
            this.startActivity(intentCancel);
        } else if (view.getId() == R.id.imageButtonMedia){
            new AlertDialog.Builder(this)
                    .setMessage("Coming Soon to a TO Class Near You!")
                    .setNeutralButton("Okay",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                    .show();
        }
    }


   public void seekbarWT(){
       seekBarWaitTime = (SeekBar) findViewById(R.id.seekBarWaitTime);
       textViewMins = (TextView) findViewById(R.id.textViewMins);
       textViewMins.setText(seekBarWaitTime.getProgress() + " Mins");

       seekBarWaitTime.setOnSeekBarChangeListener(
               new SeekBar.OnSeekBarChangeListener() {

                   int progress_value;
                   @Override
                   public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                       progress_value = i;
                       textViewMins.setText(i + " Mins");
                       waitTime = progress_value;
                   }

                   @Override
                   public void onStartTrackingTouch(SeekBar seekBar) {

                   }

                   @Override
                   public void onStopTrackingTouch(SeekBar seekBar) {
                       textViewMins.setText(progress_value + " Mins");
                   }
               }
       );
   }

    public void seekbarPeeps(){
        seekBarPeepNum = (SeekBar) findViewById(R.id.seekBarPeepNum);
        textViewPeeps = (TextView) findViewById(R.id.textViewPeeps);
        textViewPeeps.setText(seekBarPeepNum.getProgress() + " People");

        seekBarPeepNum.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        progress_value = i;
                        textViewPeeps.setText(i + " People");
                        waitPeople = progress_value;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        textViewPeeps.setText(progress_value + " People");
                    }
                }
        );
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater mainMenuInflator = getMenuInflater();
        mainMenuInflator.inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuHome) {
            Intent intentHome = new Intent(this, ActivityHome.class);
            this.startActivity(intentHome);
        } else if (item.getItemId() == R.id.menuAcct) {
            Intent intentAcct = new Intent(this, ActivityAcct.class);
            this.startActivity(intentAcct);
        } else if (item.getItemId() == R.id.menuLogout) {
            Intent intentLogout = new Intent(this, MainActivity.class);
            this.startActivity(intentLogout);
        }


        return super.onOptionsItemSelected(item);
    }
}
