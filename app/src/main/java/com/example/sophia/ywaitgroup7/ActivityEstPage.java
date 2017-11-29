package com.example.sophia.ywaitgroup7;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ActivityEstPage extends Activity implements View.OnClickListener{

    private ImageView imageViewEstPic;
    private TextView textViewEstName;
    private TextView textViewWaitDesc;
    private TextView textViewPeepDesc;
    private TextView textViewWaitNum;
    private TextView textViewPeepNum;
    private Button buttonJoinLine;
    // private ListView??

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_est_page);

        imageViewEstPic = (ImageView) findViewById(R.id.imageViewEstPic);
        textViewEstName = (TextView) findViewById(R.id.textViewEstName);
        textViewWaitDesc = (TextView) findViewById(R.id.textViewWaitDesc);
        textViewPeepDesc = (TextView) findViewById(R.id.textViewPeepDesc);
        textViewWaitNum = (TextView) findViewById(R.id.textViewWaitNum);
        textViewPeepNum = (TextView) findViewById(R.id.textViewPeepNum);
        buttonJoinLine = (Button) findViewById(R.id.buttonJoinLine);
        //ListView ??

        buttonJoinLine.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       Intent intentContribute = new Intent(this, ActivityContribute.class);
       this.startActivity(intentContribute);
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
