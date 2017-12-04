package com.example.sophia.ywaitgroup7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ActivityHome extends Activity implements View.OnClickListener{

    private EditText editTextSearch;
    private Button buttonSearch;
    private Button buttonDistSort;
    private Button buttonWaitSort;
    private ListView listViewEstablishments;

    //ArrayList<String> list = new ArrayList<>();
    //ArrayAdapter<String> adapter;

    private String[] NAMES = {"Rick's All American Cafe", "Scorekeeper's", "MASH", "LIVE", "Cantina" };
    private int[] IMAGES = {R.drawable.ricks, R.drawable.skeeps, R.drawable.mash,R.drawable.live,R.drawable.cantina};

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

        CustomAdapter customAdapter = new CustomAdapter();
        listViewEstablishments.setAdapter(customAdapter);

       //FirebaseDatabase database = FirebaseDatabase.getInstance();
       // DatabaseReference myRef = database.getReference("message");

    }

    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return NAMES.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.listitem, null);

            TextView textViewEstName = view.findViewById(R.id.textViewEstName);
            TextView textViewDist = view.findViewById(R.id.textViewDist);
            TextView textViewWait = view.findViewById(R.id.textViewWait);
            TextView textViewRDist = view.findViewById(R.id.textViewRDist);
            TextView textViewRWait = view.findViewById(R.id.textViewRWait);
            TextView textViewGo = view.findViewById(R.id.textViewGo);
            ImageView imageViewEstPic = view.findViewById(R.id.imageViewEstPic);

            textViewEstName.setText(NAMES[i]);
            textViewDist.setText("Distance:");
            textViewWait.setText("Wait:");
            textViewRDist.setText("--");
            textViewRWait.setText("--");
            textViewGo.setText(">>");
            imageViewEstPic.setImageResource(IMAGES[i]);

            return view;

        }
    }

    @Override
    public void onClick(View view) {
        Intent intentEst = new Intent(this, ActivityEstPage.class);
        this.startActivity(intentEst);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater homeMenuInflater = getMenuInflater();
        homeMenuInflater.inflate(R.menu.homemenu, menu);

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
