package com.example.proyectopinterest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authentication extends AppCompatActivity {

    private static final String TAG = "Antut";
    private EditText EtEmail,EtPassword;
    private Button  BtnLogin;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener AuthListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);


        mAuth = FirebaseAuth.getInstance();

        EtEmail = (EditText) findViewById(R.id.email);
        EtPassword = (EditText) findViewById(R.id.password);
        BtnLogin = (Button) findViewById(R.id.login);



        AuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(Authentication.this, Gallery.class));
                } else {
                    //Toast.makeText(Authentication.this, "Datos incorectos", Toast.LENGTH_SHORT).show();
                }
            }

        };



        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUser();
            }
        });





    }

//    @Override
//    protected void onStart(){
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }

    private void LogUser(){

        String email = EtEmail.getText().toString();
        String password = EtPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Log.d(TAG, "singWithEmail:onComplete:"+ task.isSuccessful());

                        if(!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(Authentication.this, "Authentication failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
        
    }
}