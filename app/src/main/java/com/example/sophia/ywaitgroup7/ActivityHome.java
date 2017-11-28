package com.example.sophia.ywaitgroup7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

public class ActivityHome extends Activity implements View.OnClickListener{

    private EditText editTextSearch;
    private Button buttonSearch;
    private Button buttonDistSort;
    private Button buttonWaitSort;
    private ListView listViewEstablishments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonDistSort = (Button) findViewById(R.id.buttonDistSort);
        buttonWaitSort = (Button) findViewById(R.id.buttonWaitSort);
        listViewEstablishments = (ListView) findViewById(R.id.listViewEstablishments);

        buttonSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intentEst = new Intent(this, ActivityEstPage.class);
        this.startActivity(intentEst);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater homeMenuIncflater = getMenuInflater();
        homeMenuIncflater.inflate(R.menu.homemenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if  (item.getItemId() == R.id.menuAcct) {
            Intent intentAcct = new Intent(this, ActivityAcct.class);
            this.startActivity(intentAcct);
        } else if (item.getItemId() == R.id.menuLogout) {
            Intent intentLogout = new Intent(this, MainActivity.class);
            this.startActivity(intentLogout);
        }
        return super.onOptionsItemSelected(item);
    }
}
