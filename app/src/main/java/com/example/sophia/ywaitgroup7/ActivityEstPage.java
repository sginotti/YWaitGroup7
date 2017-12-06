package com.example.sophia.ywaitgroup7;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

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

    private String[] NAMES = {"User 1", "User 2", "User 3", "User 4", "User 5" };
    private String[] WAIT = {"40min", "30min", "50min", "20min", "30min"};
    private String[] PEEPS = {"100", "150", "90", "100", "125"};
    private String[] TIME = {"2min ago","4min ago","10min ago", "20min ago", "30min ago"};

    public Double aveWaitTime, aveWaitPeople;
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

        buttonJoinLine.setOnClickListener(this);
        imageButtonFavorite.setOnClickListener(this);

        PostAdapter postAdapter = new PostAdapter();
        listViewPosts.setAdapter(postAdapter);

        calWait();

    }

    public void calWait () {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference mashRef = db.getReference("Mash");
        totalWaitTime = 0.0;


        mashRef.child("Data").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //Toast.makeText(ActivityEstPage.this, snapshot.child("userName"), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(ActivityEstPage.this, "Test", Toast.LENGTH_SHORT).show();
                    if (snapshot.child("waitTime").getValue() != null) {

                        String wt = snapshot.child("waitTime").getValue().toString();
                        int foundWaitTime = Integer.parseInt(wt);
                        i = i +1;
                        totalWaitTime += foundWaitTime;
                        Toast.makeText(ActivityEstPage.this, totalWaitTime.toString(), Toast.LENGTH_SHORT).show();

                    }

                }
                aveWaitTime = totalWaitTime / i;

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

            view = getLayoutInflater().inflate(R.layout.post_items, null);
            ImageView imageViewPic = view.findViewById(R.id.imageViewPic);
            TextView textViewName = view.findViewById(R.id.textViewName);
            TextView textViewWT = view.findViewById(R.id.textViewWT);
            TextView textViewNoPeep = view.findViewById(R.id.textViewNoPeep);
            TextView textViewRWT = view.findViewById(R.id.textViewRWT);
            TextView textViewRNP = view.findViewById(R.id.textViewRNP);
            TextView textViewPostTime = view.findViewById(R.id.textViewPostTime);
            CheckBox checkBoxCheers = view.findViewById(R.id.checkBoxCheers);

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
