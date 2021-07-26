package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SignUpPage extends AppCompatActivity {

    EditText e1, e2, e3, e4, e5, e6;
    String name, addr, dob, mob, bg, dod;
    CheckBox cb;
    FirebaseFirestore db;
    DocumentReference ref;
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        e1 = findViewById(R.id.editTextTextPersonName2);
        e2 = findViewById(R.id.editTextTextPersonName3);
        e3 = findViewById(R.id.editTextTextPersonName4);
        e4 = findViewById(R.id.editTextPhone2);
        e5 = findViewById(R.id.editTextTextPersonName6);
        e6 = findViewById(R.id.editTextTextPersonName5);
        cb = findViewById(R.id.checkBox);
//        dbHlpr=new DBHlpr(this);

        db = FirebaseFirestore.getInstance();
        ref = db.collection("client").document();

        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(SignUpPage.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                e3.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        e6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(SignUpPage.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                e6.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

    }

    public void signupclick(View view) {
        if (cb.isChecked()) {
            name = e1.getText().toString();
            addr = e2.getText().toString();
            dob = e3.getText().toString();
            mob = e4.getText().toString();
            bg = e5.getText().toString();
            dod = e6.getText().toString();

            ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {

                    if (documentSnapshot.exists())
                    {
                        Toast.makeText(SignUpPage.this, "Sorry,this user exists", Toast.LENGTH_SHORT).show();
                    } else {
                        Map<String, Object> reg_entry = new HashMap<>();
                        reg_entry.put("Name", name);
                        reg_entry.put("Address", addr);
                        reg_entry.put("Date of birth", dob);
                        reg_entry.put("mobile", mob);
                        reg_entry.put("Blood Group", bg);
                        reg_entry.put("Last donated", dod);


                        //   String myId = ref.getId();
                        db.collection("client")
                                .add(reg_entry)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(SignUpPage.this, "Data saved", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(SignUpPage.this,dataList.class);
                                        startActivity(i);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("Failed to save data!", e.getMessage());
                                    }
                                });
                    }
                }
            });



//            Toast.makeText(this, "Your details are safe with us!", Toast.LENGTH_LONG).show();

//        Intent i = new Intent(this, ListDetails.class);
//        startActivity(i);
    }

        else{
            AlertDialog.Builder a = new AlertDialog.Builder(SignUpPage.this);
            a.setTitle("Warning");
            a.setMessage("Please tick the checkbox");
            a.setPositiveButton("Ok", null);
            a.show();
        }
    }
}