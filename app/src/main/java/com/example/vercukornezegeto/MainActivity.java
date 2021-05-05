package com.example.vercukornezegeto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{
    private static final String LOG_TAG = MainActivity.class.getName();
    private static final String PREF_KEY = MainActivity.class.getPackage().toString();
    private static final int RC_SIGN_IN = 123;
    private static final int SECRET_KEY = 99;

    EditText userNameET;
    EditText passwordET;

    private SharedPreferences preferences;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameET = findViewById(R.id.editTextUserName);
        passwordET = findViewById(R.id.editTextPassword);

        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Log.i(LOG_TAG, "onCreate");
    }
    public void login(View view) {
        String userName = userNameET.getText().toString();
        String password = passwordET.getText().toString();

        Log.i(LOG_TAG, "Bejelentkezett: " + userName);

        mAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(this, task -> {
            if(task.isSuccessful()){
                Log.d(LOG_TAG, "User login successful");
                startListing();
            } else {
                Log.d(LOG_TAG, "User login failed");
                Toast.makeText(MainActivity.this, "User login failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void loginWithGoogle(View view) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
    private void startListing() {
        Intent intent = new Intent(this, ListingActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }
}