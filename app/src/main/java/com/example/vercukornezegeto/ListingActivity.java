package com.example.vercukornezegeto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vercukornezegeto.adapters.ObsAdapter;
import com.example.vercukornezegeto.entities.Observation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class ListingActivity extends AppCompatActivity {
    private static final String LOG_TAG = ListingActivity.class.getName();
    private static final int SECRET_KEY = 99;

    private FirebaseFirestore mFirestore;
    private FirebaseUser user;
    private CollectionReference mItems;
    private ArrayList<Observation> mItemsData;

    private RecyclerView mRecyclerView;
    private ObsAdapter mAdapter;

    private int gridNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("beléptünk a listingbe");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);

        if (secret_key != 99) {
            finish();
        }

        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            Log.d(LOG_TAG, "Authenticated user: "  + user.getEmail());
        } else {
            Log.d(LOG_TAG, "UNAUTHENTICATED USER!");
            finish();
        }
        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Observations");

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridNumber));

        mItemsData = new ArrayList<>();

        mAdapter = new ObsAdapter(this, mItemsData);
        mRecyclerView.setAdapter(mAdapter);
    }

    //Query data when activity started or resumed
    @Override
    protected void onResume() {
        super.onResume();
        queryData();
    }

    //Query data from database
    private void queryData(){
        //Log.d(LOG_TAG, "Data query started!");
        mItemsData.clear();
        mItems.get().addOnSuccessListener(queryDocumentSnapshots -> {
            //Log.d(LOG_TAG, "Data query successful!");
            for (QueryDocumentSnapshot document : queryDocumentSnapshots){
                Observation o = document.toObject(Observation.class);
                o.setDocumentId(document.getId());
                if (Objects.equals(user.getEmail(), o.getSubject()))mItemsData.add(o);
            }
            mAdapter.notifyDataSetChanged();
        });
    }

    //Nav to InsertActivity
    public void newPage(View view) {
        Intent intent = new Intent(this, InsertActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    //Log out user, nav to MainActivity
    public void logOut(View view) {
        this.finish();
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}