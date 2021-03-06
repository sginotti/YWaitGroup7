package com.example.sophia.ywaitgroup7;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;


//Code for Search capability adapted from:
//http://www.androidbegin.com/tutorial/android-search-filter-listview-images-and-texts-tutorial/
public class ActivityHome extends Activity implements View.OnClickListener {

    private EditText editTextSearch;
    private Button buttonSearch;
    private Button buttonDistSort;
    private Button buttonWaitSort;
    private ListView listViewEstablishments;

    public static String keepName;
    public static Integer keepNumber;


    //See note at bottom of page//
        /*public Integer aveWaitTime;
        public Double totalWaitTime;
        public String msg;*/

    ArrayList<EstabList> arraylist = new ArrayList<EstabList>();

    public String[] NAMES = {"Rick's All American Cafe", "Scorekeeper's", "MASH", "LIVE", "Cantina"};
    public int[] IMAGES = {R.drawable.ricks, R.drawable.skeeps, R.drawable.mash, R.drawable.live, R.drawable.cantina};

    //See note at bottom of page//
        //public String[] TIMES = {calWait(300, NAMES[1]), calWait(300,NAMES[2], calWait(300,NAMES[4],calWait(300,NAMES[4],calWait(300,NAMES[5],);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //See note at bottom of page//
            //cantina = calWait(300, "Cantina");

        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        buttonSearch = (Button) findViewById(R.id.buttonAcct);
        buttonDistSort = (Button) findViewById(R.id.buttonDistSort);
        buttonWaitSort = (Button) findViewById(R.id.buttonWaitSort);
        listViewEstablishments = (ListView) findViewById(R.id.listViewEstablishments);
        buttonSearch.setOnClickListener(this);

        for (int i = 0; i < NAMES.length; i++) {
            EstabList wp = new EstabList(NAMES[i], "0", "0", IMAGES[i]);
            arraylist.add(wp);
        }

        final CustomAdapter customAdapter = new CustomAdapter(this, arraylist);
        listViewEstablishments.setAdapter(customAdapter);

        //When clicked the program will send information to Establishment page and populate info
        listViewEstablishments.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentEst = new Intent(getApplicationContext(), ActivityEstPage.class);
                keepName = NAMES[i].toString();
                keepNumber = IMAGES[i];
                startActivity(intentEst);
            }
        });

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editTextSearch.getText().toString().toLowerCase(Locale.getDefault());
                customAdapter.filter(text);
            }
        });
    }

    class CustomAdapter extends BaseAdapter {
        Context mContext;
        LayoutInflater inflater;
        private List<EstabList> establishmentlist = null;
        private ArrayList<EstabList> arraylist;

        public CustomAdapter(Context context, List<EstabList> establishmentlist) {
            mContext = context;
            this.establishmentlist = establishmentlist;
            inflater = LayoutInflater.from(mContext);
            this.arraylist = new ArrayList<EstabList>();
            this.arraylist.addAll(establishmentlist);
        }

        public class ViewHolder {
            TextView name;
            TextView dnum;
            TextView wnum;
            ImageView pic;
        }

        @Override
        public int getCount() {
            return establishmentlist.size();
        }

        @Override
        public EstabList getItem(int i) {
            return establishmentlist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final ViewHolder holder;
            if (view == null) {
                holder = new ViewHolder();
                view = getLayoutInflater().inflate(R.layout.listitem, null);
                //Locate the TextViews/ImageView in Xml
                holder.name = (TextView) view.findViewById(R.id.textViewEstName);
                holder.dnum = (TextView) view.findViewById(R.id.textViewRDist);
                holder.wnum = (TextView) view.findViewById(R.id.textViewRWait);
                holder.pic = (ImageView) view.findViewById(R.id.imageViewEstPic);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            //Set the results
            holder.name.setText(establishmentlist.get(i).getName());
            holder.dnum.setText("--");
            holder.wnum.setText("--");
            holder.pic.setImageResource(establishmentlist.get(i).getPic());

            return view;
        }

        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            establishmentlist.clear();
            if (charText.length() == 0) {
                establishmentlist.addAll(arraylist);
            } else {
                for (EstabList wp : arraylist) {
                    if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                        establishmentlist.add(wp);
                    }
                }
            }
            notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonAcct) {
            Intent intentAcct = new Intent(this, ActivityAcct.class);
            this.startActivity(intentAcct);
        } else if (view.getId() == R.id.buttonDistSort) {
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
        } else if (view.getId() == R.id.buttonWaitSort) {
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
        MenuInflater homeMenuInflater = getMenuInflater();
        homeMenuInflater.inflate(R.menu.homemenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuAcct) {
            Intent intentAcct = new Intent(this, ActivityAcct.class);
            this.startActivity(intentAcct);
        } else if (item.getItemId() == R.id.menuLogout) {
            Intent intentLogout = new Intent(this, MainActivity.class);
            this.startActivity(intentLogout);
        }
        return super.onOptionsItemSelected(item);
    }
}

    /*NOTE: Ideally we would have liked to populate the home page list with real wait time data
        but were unable to get the feature to work in time for the submission. Below is the code we
        were playing around with. In order to populate the ListView we though we could call
        this method in the list. In our preliminary tests we could not get even a toast to appear
        that would display one of the aggregated wait times.*/

    /*public void calWait(long offset, final String name) {
        totalWaitTime = 0.0;
        msg = "";
        final long time = System.currentTimeMillis() / 1000 - offset;

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference EstabRef = db.getReference(name);

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

                }
                // if no fresh records (see offet)
                // then checking all the records made in the last week
                if ((totalWaitTime == 0)) {
                    calWait(60 * 60 * 24 * 7, name);
                } else {
                    aveWaitTime = (int) Math.ceil(totalWaitTime / i);
                    String msg = String.valueOf(aveWaitTime / 60) + "h " + String.valueOf(aveWaitTime % 60) + "m";
                    //textViewWaitNum.setText(msg);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }


}*/



