
package com.example.proyectopinterest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    private EditText rName;
    private EditText rEmail;
    private EditText rPassword;
    private Button BtnRegis;

    private String name = "";
    private String email = "";
    private String password = "";

    FirebaseAuth mAuth;
    DatabaseReference mDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        rName = (EditText) findViewById(R.id.rName);
        rEmail = (EditText) findViewById(R.id.rEmail);
        rPassword = (EditText) findViewById(R.id.rPassword);
        BtnRegis = (Button) findViewById(R.id.Regis);

        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        BtnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = rName.getText().toString();
                email = rEmail.getText().toString();
                password = rPassword.getText().toString();

                if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){

                    if (password.length() >=6){
                        registerUser();
                    }else{
                        Toast.makeText(Registro.this, "The password must have at least 6characters.", Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(Registro.this, "All fields must be filled.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void registerUser() {

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Map<String, Object> map = new HashMap<>();
                    map.put("Name", name);
                    map.put("Email",email);
                    map.put("Password",password);

                    String id = mAuth.getCurrentUser().getUid();

                    mDataBase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){

                                Toast.makeText(Registro.this, "Registration Successful.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Registro.this, Authentication.class));
                                finish();

                            }else{
                                Toast.makeText(Registro.this, "Data creation failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }else{
                    Toast.makeText(Registro.this, "Registration Failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
