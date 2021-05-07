package com.example.vercukornezegeto;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vercukornezegeto.entities.Observation;
import com.example.vercukornezegeto.entities.Resource.CodeableConcept;
import com.example.vercukornezegeto.entities.Resource.Component;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;

public class UpdateActivity extends AppCompatActivity {

    private TextView date;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;

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
    private Observation currentItem;
    private ArrayList<EditText> textArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        currentItem = getIntent().getExtras().getParcelable("currentItem");

        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Observations");

        WBC = findViewById(R.id.uWBCValue);
        RBC = findViewById(R.id.uRBCValue);
        HGB = findViewById(R.id.uHGBValue);
        HCT = findViewById(R.id.uHCTValue);
        MCV = findViewById(R.id.uMCVValue);
        MCH = findViewById(R.id.uMCHValue);
        MCHC = findViewById(R.id.uMCHCValue);
        PLT = findViewById(R.id.uPLTValue);
        RDWSD = findViewById(R.id.uRDWSDValue);
        RDWCV = findViewById(R.id.uRDWCVValue);
        PDW = findViewById(R.id.uPDWValue);
        MPV = findViewById(R.id.uMPVValue);
        PCT = findViewById(R.id.uPCTValue);
        NEUT = findViewById(R.id.uNEUTValue);
        LYMPH = findViewById(R.id.uLYMPHValue);
        MONO = findViewById(R.id.uMONOValue);
        EO = findViewById(R.id.uEOValue);
        BASO = findViewById(R.id.uBASOValue);
        IG = findViewById(R.id.uIGValue);
        date = findViewById(R.id.uDate);
        textArray = new ArrayList<>();
        Collections.addAll(textArray, WBC, RBC, HGB, HCT, MCV, MCH, MCHC, PLT, RDWSD, RDWCV, PDW, MPV, PCT, NEUT, LYMPH, MONO, EO, BASO, IG);

        int i = 0;
        for (EditText text: textArray){
            text.setText(currentItem.getComponent().get(i).getValueString());
            i++;
        }
        date.setText(currentItem.getEffectiveInstant());


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

    }

    private Observation initData(){
        ArrayList<String> resultValues = new ArrayList<>();
        ArrayList<Component> components = new ArrayList<>();

        for (EditText text: textArray){
            resultValues.add(text.getText().toString());
            System.out.println("Input mezo erteke: " + text.getText().toString());
        }

        int i =0;
        for (String res : resultValues){
            components.add(new Component(currentItem.getComponent().get(i).getCode(), res));
            i++;
        }

        Observation o = currentItem;
        o.setComponent(components);
        o.setEffectiveInstant(date.getText().toString());

        return o;
    }

    public void backToListingFromUpdate(View view) {
        Intent intent = new Intent(this, ListingActivity.class);
        startActivity(intent);
    }

    public void updateData(View view) {
        Observation o = initData();
        boolean b = true;
        for (Component c: o.getComponent()){
            String str = c.getValueString();
            if (str.equals("")){
                Toast.makeText(UpdateActivity.this, "Tölts ki minden mezőt!", Toast.LENGTH_LONG).show();
                b = false;
                break;
            }
            try {
                Float.parseFloat(str);
            } catch(NumberFormatException | NullPointerException e ) {
                Toast.makeText(UpdateActivity.this, "Az értékek számok kell, hogy legyenek (pl. 4.15)!", Toast.LENGTH_LONG).show();
                b = false;
                break;
            }
        }
        if (b) {
            if (o.getEffectiveInstant().equals("")) {
                Toast.makeText(UpdateActivity.this, "Válassz dátumot!", Toast.LENGTH_LONG).show();
            } else {
                mItems.document(o.getDocumentId()).set(o);
                Intent intent = new Intent(this, ListingActivity.class);
                Toast.makeText(UpdateActivity.this, "Sikeres felvitel!", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        }
    }
}