package com.example.sophia.ywaitgroup7;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends Activity implements View.OnClickListener{

    private TextView textViewYWait;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonMainSubmit;
    private Button buttonGoogle;
    private Button buttonFacebook;
    private Button buttonCrtAcct;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public Integer shortcut = new Integer(1);

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

        buttonCrtAcct.setOnClickListener(this);
        buttonMainSubmit.setOnClickListener(this);

        buttonMainSubmit.setOnClickListener(this);
        buttonFacebook.setOnClickListener(this);
        buttonGoogle.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("Tag", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("Tag", "onAuthStateChanged:signed_out");
                }
            }
        };

    }


    @Override
    public void onClick(View view) {

        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        /*if (email.equals("")) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
        } else {
            if (password.equals("")) {
                Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            } else {*/
                if (view.getId() == R.id.buttonMainSubmit){
                    Intent intentLogin = new Intent(getApplicationContext(), ActivityHome.class);
                    startActivity(intentLogin);
                    //signIn(email, password);
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
                } else if (view == buttonCrtAcct) {
                    createAccount(email, password);
                }
            }
        //}
    //}

    public void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Authentication Failed",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication Successful",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void signIn(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login Failed",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Login Successful",
                                    Toast.LENGTH_SHORT).show();
                            Intent intentLogin = new Intent(getApplicationContext(), ActivityHome.class);
                            startActivity(intentLogin);

                        }
                    }
                });
    }

}

