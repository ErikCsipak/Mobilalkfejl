package com.example.vercukornezegeto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vercukornezegeto.entities.Observation;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListingActivity extends AppCompatActivity {
    private static final String LOG_TAG = ListingActivity.class.getName();
    private static final int SECRET_KEY = 99;

    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;
    List<Observation> mItemsData;

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
        mItemsData = new ArrayList<>();
        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Observations");

    }

    private void queryData(){
        mItemsData.clear();
        mItems.get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document : queryDocumentSnapshots){
                Observation o = document.toObject(Observation.class);
                mItemsData.add(o);
            }
        });
    }

    public void newPage(View view) {
        Intent intent = new Intent(this, InsertActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }
}