package com.example.vercukornezegeto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vercukornezegeto.adapters.ObsAdapter;
import com.example.vercukornezegeto.entities.Observation;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("%%%%%%%%%%%%%RESUME%%%%%%%%%%%%%%%");
        queryData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void queryData(){
        System.err.println("\nAdatok lekérése\n");
        mItemsData.clear();
        mItems.get().addOnSuccessListener(queryDocumentSnapshots -> {
            System.err.println("\nAdatlekérés sikeres\n");
            for (QueryDocumentSnapshot document : queryDocumentSnapshots){
                Observation o = document.toObject(Observation.class);
                o.setDocumentId(document.getId());
                System.out.println(o.getDocumentId());;
                System.err.println(user.getEmail());
                System.err.println(o.getSubject());
                if (Objects.equals(user.getEmail(), o.getSubject()))mItemsData.add(o);
            }
            mAdapter.notifyDataSetChanged();
        });
    }

    public void newPage(View view) {
        Intent intent = new Intent(this, InsertActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    public void logOut(View view) {
        this.finish();
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}