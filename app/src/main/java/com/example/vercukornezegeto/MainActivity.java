package com.example.vercukornezegeto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity{
    private static final String LOG_TAG = MainActivity.class.getName();
    private static final int SECRET_KEY = 99;

    EditText userNameET;
    EditText passwordET;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameET = findViewById(R.id.editTextUserName);
        passwordET = findViewById(R.id.editTextPassword);

        mAuth = FirebaseAuth.getInstance();
    }
    public void login(View view) {
        String userName = userNameET.getText().toString();
        String password = passwordET.getText().toString();

        //Log.i(LOG_TAG, "Logged in user: " + userName);

        mAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(this, task -> {
            if(task.isSuccessful()){
                Log.d(LOG_TAG, "User login successful");
                startListing();
            } else {
                Log.d(LOG_TAG, "User login failed");
                Toast.makeText(MainActivity.this, "A belépés sikertelen: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        FirebaseAuth.getInstance().signOut();
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    private void startListing() {
        Intent intent = new Intent(this, ListingActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }
}