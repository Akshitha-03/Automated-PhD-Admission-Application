package com.example.college_phd;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.college_phd.ui.home.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class form1 extends AppCompatActivity {
    String[] gender = {"Male", "Female", "Other"};
    String[] nationality = {"Afghan","Albanian","Algerian","Argentine","Argentinian","Australian","Austrian","Bangladeshi","Belgian","Bolivian","Batswana","Brazilian","Bulgarian","Cambodian","Cameroonian","Canadian","Chilean","Chinese","Colombian","Costa Rican","Croatian","Cuban","Czech","Danish","Dominican","Ecuadorian","Egyptian","Salvadorian","English","Estonian","Ethiopian","Fijian","Finnish","French","German","Ghanaian","Greek","Guatemalan","Haitian","Honduran","Hungarian","Icelandic","Indian","Indonesian","Iranian","Iraqi","Irish","Israeli","Italian","Jamaican","Japanese","Jordanian","Kenyan","Kuwaiti","Lao","Latvian","Lebanese","Libyan","Lithuanian","Malagasy","Malaysian","Malian","Maltese","Mexican","Mongolian","Moroccan","Mozambican","Namibian","Nepalese","Dutch","New Zealand","Nicaraguan","Nigerian","Norwegian","Pakistani","Panamanian","Paraguayan","Peruvian","Philippine","Polish","Portuguese","Romanian","Russian","Saudi","Scottish","Senegalese","Serbian","Singaporean","Slovak","South African","Korean","Spanish","Sri Lankan","Sudanese","Swedish","Swiss","Syrian","Taiwanese","Tajikistani","Thai","Tongan","Tunisian","Turkish","Ukrainian","Emirati","British","American","Uruguayan","Venezuelan","Vietnamese","Welsh","Zambian","Zimbabwean"};
    AutoCompleteTextView gender_atc,nationality_atc;
    EditText editText;
    Button save;
    final Calendar myCalendar = Calendar.getInstance();
    EditText dob;
    StorageReference storageReference;
    StorageReference databaseReference;
    FirebaseFirestore fstore;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);
        save = findViewById(R.id.savebutton);
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),form1.class));
            finish();
        }
        EditText dob = (EditText) findViewById(R.id.dob);
        gender_atc = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView_gender);
        nationality_atc = (AutoCompleteTextView) findViewById(R.id.nationality);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();

            }

            private void updateLabel() {
                String myFormat = "dd/MM/yy";
                SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
                dob.setText(dateFormat.format(myCalendar.getTime()));
            }
        };

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(form1.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(form1.this, "Saved Data", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(form1.this, HomeFragment.class);
                startActivity(new Intent(getApplicationContext(),HomeFragment.class));
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_selectable_list_item,gender);
        gender_atc.setAdapter(adapter);
        gender_atc.setThreshold(1);
        gender_atc.setTextColor(Color.BLACK);

        gender_atc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gender_atc.showDropDown();
                return false;
            }
        });
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_selectable_list_item,nationality);
        nationality_atc.setAdapter(adapter2);
        nationality_atc.setThreshold(1);
        nationality_atc.setTextColor(Color.BLACK);

        nationality_atc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                nationality_atc.showDropDown();
                return false;
            }
        });
    }

}
