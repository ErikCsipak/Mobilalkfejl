package com.example.vercukornezegeto;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vercukornezegeto.entities.Observation;
import com.example.vercukornezegeto.entities.Resource.CodeableConcept;
import com.example.vercukornezegeto.entities.Resource.Component;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;

public class InsertActivity extends AppCompatActivity {
    private static final String LOG_TAG = InsertActivity.class.getName();
    private static final int SECRET_KEY = 99;
    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;


    private ArrayList<String> valueNames;
    private ArrayList<String> optValues;

    private TextView date;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText WBC;
    private EditText RBC;
    private EditText HGB;
    private EditText HCT;
    private EditText MCV;
    private EditText MCH;
    private EditText MCHC;
    private EditText PLT;
    private EditText RDWSD;
    private EditText RDWCV;
    private EditText PDW;
    private EditText MPV;
    private EditText PCT;
    private EditText NEUT;
    private EditText LYMPH;
    private EditText MONO;
    private EditText EO;
    private EditText BASO;
    private EditText IG;
    private ArrayList<EditText> textArray;
    private FirebaseUser user;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

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

        if (user != null) {
            userName = user.getEmail();
        } else {
            userName = "unauthenticated";
        }
        WBC = findViewById(R.id.WBCValue);
        RBC = findViewById(R.id.RBCValue);
        HGB = findViewById(R.id.HGBValue);
        HCT = findViewById(R.id.HCTValue);
        MCV = findViewById(R.id.MCVValue);
        MCH = findViewById(R.id.MCHValue);
        MCHC = findViewById(R.id.MCHCValue);
        PLT = findViewById(R.id.PLTValue);
        RDWSD = findViewById(R.id.RDWSDValue);
        RDWCV = findViewById(R.id.RDWCVValue);
        PDW = findViewById(R.id.PDWValue);
        MPV = findViewById(R.id.MPVValue);
        PCT = findViewById(R.id.PCTValue);
        NEUT = findViewById(R.id.NEUTValue);
        LYMPH = findViewById(R.id.LYMPHValue);
        MONO = findViewById(R.id.MONOValue);
        EO = findViewById(R.id.EOValue);
        BASO = findViewById(R.id.BASOValue);
        IG = findViewById(R.id.IGValue);
        textArray = new ArrayList<>();
        valueNames = new ArrayList<>();
        optValues = new ArrayList<>();

        Collections.addAll(textArray, WBC, RBC, HGB, HCT, MCV, MCH, MCHC, PLT, RDWSD, RDWCV, PDW, MPV, PCT, NEUT, LYMPH, MONO, EO, BASO, IG);

        String[] names = getResources().getStringArray(R.array.keysForBloodValues);
        String[] values =  getResources().getStringArray(R.array.valuesForBloodKeys);
        valueNames.addAll(Arrays.asList(names));
        optValues.addAll(Arrays.asList(values));

        date = findViewById(R.id.date);
        date.setOnClickListener(v -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(this,
                    R.style.Theme_AppCompat_DayNight_Dialog,
                    mDateSetListener,
                    year,
                    month,
                    day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
            dialog.show();
        });
        mDateSetListener = (view, year, month, dayOfMonth) -> {
            month=month+1;
            String dateStr = year + "/" + month + "/" + dayOfMonth;
            date.setText(dateStr);
        };


        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Observations");
    }

    //Collect data from input and make an Observation out of it
    private Observation initData(String user){
        CodeableConcept c = new CodeableConcept();
        c.setText("Blood map");

        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 8;
        Random random = new Random();

        //Input field values
        ArrayList<String> resultValues = new ArrayList<>();
        ArrayList<Component> components = new ArrayList<>();

        for (EditText text: textArray){
            resultValues.add(text.getText().toString());
            //Log.d(LOG_TAG,"Input mezo erteke: " + text.getText().toString());
        }
        for (String res : resultValues){
            //Generate random identifier as component code
            String generatedString = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            components.add(new Component(generatedString, res));
        }

        Observation o = new Observation("registered", c);
        o.setComponent(components);
        o.setSubject(user);
        o.setFocus(valueNames);
        o.setBasedOn(optValues);
        o.setEffectiveInstant(date.getText().toString());
        //Log.d(LOG_TAG, o.getComponent().toString());

        return o;
    }

    //Add data to FireStore
    public void insertData(View view) {
        Observation o = initData(userName);
        boolean b = true;

        //Check for empty fields, or invalid values
        for (Component c: o.getComponent()){
            String str = c.getValueString();
            if (str.equals("")){
                Toast.makeText(InsertActivity.this, "Tölts ki minden mezőt!", Toast.LENGTH_LONG).show();
                b = false;
                break;
            }
            if (str.length() > 6){
                Toast.makeText(InsertActivity.this, "Túl hosszú értékek! Legfejlebb 6 karakter hosszú számot adhatsz meg!", Toast.LENGTH_LONG).show();
                b = false;
                break;
            }
            try {
                Float.parseFloat(str);
            } catch(NumberFormatException | NullPointerException e ) {
                Toast.makeText(InsertActivity.this, "Az értékek számok kell, hogy legyenek (pl. 4.15)!", Toast.LENGTH_LONG).show();
                b = false;
                break;
            }
        }
        //Check if date is selected
        if (b) {
            if (o.getEffectiveInstant().equals("")) {
                Toast.makeText(InsertActivity.this, "Válassz dátumot!", Toast.LENGTH_LONG).show();
            } else {
                //Add to FireStore
                mItems.add(o);
                if (user != null) {
                    Intent intent = new Intent(this, ListingActivity.class);
                    intent.putExtra("SECRET_KEY", SECRET_KEY);
                    Toast.makeText(InsertActivity.this, "Sikeres felvitel!", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                } else {
                    this.finish();
                }
            }
        }
    }

    //Go back to ListingActivity
    public void backToListing(View view) {
        Intent intent = new Intent(this, ListingActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }
}