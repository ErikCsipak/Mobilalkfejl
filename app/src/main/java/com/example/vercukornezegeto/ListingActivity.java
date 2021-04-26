package com.example.vercukornezegeto;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ListingActivity extends AppCompatActivity {
    private static final String LOG_TAG = ListingActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("beléptünk a listingbe");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            Log.d(LOG_TAG, "Authenticated user: "  + user.getEmail());
        } else {
            Log.d(LOG_TAG, "UNAUTHENTICATED USER!");
            finish();
        }

    }
}