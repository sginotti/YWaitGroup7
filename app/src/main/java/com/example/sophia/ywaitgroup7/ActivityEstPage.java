package com.example.sophia.ywaitgroup7;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.nio.BufferUnderflowException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.lang.Math.ceil;

public class ActivityEstPage extends Activity implements View.OnClickListener{

    private ImageView imageViewEstPic;
    private TextView textViewEstName;
    private TextView textViewWaitDesc;
    private TextView textViewPeepDesc;
    private TextView textViewWaitNum;
    private TextView textViewPeepNum;
    private Button buttonJoinLine;
    private ImageButton imageButtonFavorite;
    private ListView listViewPosts;

    //Store of Static information for posts
    private String[] NAMES = {"User 1", "User 2", "User 3", "User 4", "User 5" };
    private String[] WAIT = {"40min", "30min", "50min", "20min", "30min"};
    private String[] PEEPS = {"100", "150", "90", "100", "125"};
    private String[] TIME = {"2min ago","4min ago","10min ago", "20min ago", "30min ago"};

    public Integer aveWaitTime, aveWaitPeople;
    public Double totalWaitTime, totalWaitPeople;

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
        imageButtonFavorite = (ImageButton) findViewById(R.id.imageButtonFavorite);
        listViewPosts = (ListView) findViewById(R.id.listViewPosts);

        //Assigns Picture and Name per Selected Item
        textViewEstName.setText(ActivityHome.keepName);
        imageViewEstPic.setImageResource(ActivityHome.keepNumber);

        buttonJoinLine.setOnClickListener(this);
        imageButtonFavorite.setOnClickListener(this);

        //Assigns adapter to listview
        PostAdapter postAdapter = new PostAdapter();
        listViewPosts.setAdapter(postAdapter);

        calWait(300);
    }

    public void calWait (long offset) {
        totalWaitTime = 0.0;
        totalWaitPeople = 0.0;
        final long time = System.currentTimeMillis()/1000 - offset;

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference EstabRef = db.getReference(ActivityHome.keepName);

        EstabRef.child("Data2").orderByChild("loginTime").startAt(time).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                int j = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.child("waitTime").getValue() != null) {

                        String wt = snapshot.child("waitTime").getValue().toString();
                        int foundWaitTime = Integer.parseInt(wt);
                        i = i + 1;
                        totalWaitTime += foundWaitTime;
                    }
                    if (snapshot.child("waitPeople").getValue() != null) {
                        String wp = snapshot.child("waitPeople").getValue().toString();
                        int foundWaitPeople = Integer.parseInt(wp);
                        j = j + 1;
                        totalWaitPeople += foundWaitPeople;
                    }

                }
                // if no fresh records (see offet)
                // then checking all the records made in the last week
                if ((totalWaitTime == 0) | (totalWaitPeople == 0)) {
                    calWait(60*60*24*7);
                } else {
                    aveWaitTime = (int) Math.ceil(totalWaitTime / i);
                    String msg = String.valueOf(aveWaitTime / 60) + "h " + String.valueOf(aveWaitTime % 60) + "m";
                    textViewWaitNum.setText(msg);

                    aveWaitPeople = (int) Math.ceil(totalWaitPeople / j);
                    textViewPeepNum.setText(aveWaitPeople.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    class PostAdapter extends BaseAdapter{
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
            //Attaches ListView to UI of Layout
            view = getLayoutInflater().inflate(R.layout.post_items, null);
            ImageView imageViewPic = view.findViewById(R.id.imageViewPic);
            TextView textViewName = view.findViewById(R.id.textViewName);
            TextView textViewWT = view.findViewById(R.id.textViewWT);
            TextView textViewNoPeep = view.findViewById(R.id.textViewNoPeep);
            TextView textViewRWT = view.findViewById(R.id.textViewRWT);
            TextView textViewRNP = view.findViewById(R.id.textViewRNP);
            TextView textViewPostTime = view.findViewById(R.id.textViewPostTime);
            CheckBox checkBoxCheers = view.findViewById(R.id.checkBoxCheers);
            //Assigns values to each List UI
            textViewName.setText(NAMES[i]);
            textViewWT.setText("Wait Time: ");
            textViewNoPeep.setText("Number of People: ");
            textViewRWT.setText(WAIT[i]);
            textViewRNP.setText(PEEPS[i]);
            textViewPostTime.setText(TIME[i]);

            return view;
        }
    }

    @Override
    public void onClick(View view) {
       if (view.getId() == R.id.buttonJoinLine) {
           Intent intentContribute = new Intent(this, ActivityContribute.class);
           this.startActivity(intentContribute);

       } else if (view.getId() == R.id.imageButtonFavorite){
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

/*    public void calWait () {
        totalWaitTime = 0.0;
        totalWaitPeople = 0.0;
        final long time = System.currentTimeMillis()/1000 - 1800; // half an hour ago

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference mashRef = db.getReference("Mash");

        mashRef.child("Data2").orderByChild("loginTime").startAt(time).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                int j = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.child("waitTime").getValue() != null) {

                        String wt = snapshot.child("waitTime").getValue().toString();
                        int foundWaitTime = Integer.parseInt(wt);
                        i = i + 1;
                        totalWaitTime += foundWaitTime;

                    }
                    if (snapshot.child("waitPeople").getValue() != null) {

                        String wp = snapshot.child("waitPeople").getValue().toString();
                        int foundWaitPeople = Integer.parseInt(wp);
                        j = j + 1;
                        totalWaitPeople += foundWaitPeople;
                    }

                }
                aveWaitTime = (int) Math.ceil(totalWaitTime / i);
                String msg = String.valueOf(aveWaitTime / 60) + "h " + String.valueOf(aveWaitTime % 60) + "m";
                textViewWaitNum.setText(msg);

                aveWaitPeople = (int) Math.ceil(totalWaitPeople / j);
                textViewPeepNum.setText(aveWaitPeople.toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/
