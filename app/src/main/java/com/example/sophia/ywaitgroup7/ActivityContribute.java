package com.example.sophia.ywaitgroup7;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import org.w3c.dom.Text;

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

        buttonContSubmit.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
        imageButtonMedia.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonContSubmit) {
            Intent intentSubmitToEstPage = new Intent(this, ActivityEstPage.class);
            this.startActivity(intentSubmitToEstPage);
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
