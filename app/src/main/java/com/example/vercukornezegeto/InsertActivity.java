package com.example.vercukornezegeto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vercukornezegeto.entities.Observation;
import com.example.vercukornezegeto.entities.Resource.CodeableConcept;
import com.example.vercukornezegeto.entities.Resource.Component;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class InsertActivity extends AppCompatActivity {
    private static final String LOG_TAG = InsertActivity.class.getName();

    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;


    private ArrayList<String> valueNames;
    private ArrayList<String> optValues;

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

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userName = user.toString();
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
        Collections.addAll(textArray, WBC, RBC, HGB, HCT, MCV, MCH, MCHC, PLT, RDWSD, RDWCV, PDW, MPV, PCT, NEUT, LYMPH, MONO, EO, BASO, IG);

        valueNames = new ArrayList<>();
        optValues = new ArrayList<>();
        String[] names = getResources().getStringArray(R.array.keysForBloodValues);
        valueNames.addAll(Arrays.asList(names));
        String[] values =  getResources().getStringArray(R.array.valuesForBloodKeys);
        optValues.addAll(Arrays.asList(values));


        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Observations");
    }



    private Observation initData(String user){
        CodeableConcept c = new CodeableConcept();
        c.setText("Blood map");

        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 5;
        Random random = new Random();


        String statusIdentifier = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        ArrayList<Component> resultValues = new ArrayList<>();

        int i=0;
        for (String value : valueNames){
            resultValues.add(new Component(value));
            System.out.println("Bevitvendő code construktorban: " + value);
            System.out.println("Bevitt code: " + resultValues.get(i).getCode());
            resultValues.get(i).setValueString(textArray.get(i).getText().toString());
            System.out.println("Input mezo erteke: " + textArray.get(i).getText().toString());
            System.out.println("Input mezo alapjan beallitott ertek: " + resultValues.get(i).getValueString());
            System.out.println("Resultvalue eleme: " + resultValues.get(i));
            i++;
        }
        System.out.println(resultValues.size());

        Observation o = new Observation(statusIdentifier, c);
        o.setComponent(resultValues);
        System.out.println(o.getComponent());
        o.setSubject(user);
        o.setFocus(valueNames);
        o.setBasedOn(optValues);

        return o;
    }

    public void insertData(View view) {
        Observation o = initData(userName);
        mItems.add(o);
    }
}