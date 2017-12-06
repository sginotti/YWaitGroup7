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
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityAcct extends Activity implements View.OnClickListener{

    private ImageView imageViewUserPic;
    private TextView textViewUserName;
    private Button buttonChgName;
    private Button buttonChgPassword;
    private Button buttonFavorites;
    private Button buttonCheers;
    private Button buttonDiscounts;
    private Button buttonPastActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acct);

        imageViewUserPic = (ImageView)findViewById(R.id.imageViewUserPic);
        textViewUserName = (TextView) findViewById(R.id.textViewUserName);
        buttonChgName = (Button) findViewById(R.id.buttonChgName);
        buttonChgPassword = (Button) findViewById(R.id.buttonChgPassword);
        buttonFavorites = (Button) findViewById(R.id.buttonFavorites);
        buttonCheers = (Button) findViewById(R.id.buttonCheers);
        buttonDiscounts = (Button) findViewById(R.id.buttonDiscounts);
        buttonPastActivity = (Button) findViewById(R.id.buttonPastActivity);

        buttonChgName.setOnClickListener(this);
        buttonChgPassword.setOnClickListener(this);
        buttonFavorites.setOnClickListener(this);
        buttonCheers.setOnClickListener(this);
        buttonDiscounts.setOnClickListener(this);
        buttonPastActivity.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

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
