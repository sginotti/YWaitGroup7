package com.example.sophia.ywaitgroup7;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;

//import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends Activity implements View.OnClickListener{

    private TextView textViewYWait;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonMainSubmit;
    private Button buttonGoogle;
    private Button buttonFacebook;
    private Button buttonCrtAcct;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewYWait = (TextView) findViewById(R.id.textViewYWait);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonMainSubmit = (Button) findViewById(R.id.buttonMainSubmit);
        buttonGoogle = (Button) findViewById(R.id.buttonGoogle);
        buttonFacebook = (Button) findViewById(R.id.buttonFacebook);
        buttonCrtAcct = (Button) findViewById(R.id.buttonCrtAcct);

        buttonMainSubmit.setOnClickListener(this);
        buttonFacebook.setOnClickListener(this);
        buttonGoogle.setOnClickListener(this);

    }







    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonMainSubmit){
            Intent intentLogin = new Intent(this, ActivityHome.class);
            this.startActivity(intentLogin);
        } else if ((view.getId() == R.id.buttonFacebook) || (view.getId() == R.id.buttonGoogle)){
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
}

