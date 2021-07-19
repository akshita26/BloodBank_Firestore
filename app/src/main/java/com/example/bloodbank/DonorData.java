package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DonorData extends AppCompatActivity {

    Spinner sp;
    EditText et1,et2,et3;
    ArrayAdapter arrayAdapter;
    ArrayList al;
    String blood,name,add,mob;
    Donor donor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_data);

        et1=findViewById(R.id.editTextTextPersonName7);
        et2=findViewById(R.id.editTextTextPersonName8);
        et3=findViewById(R.id.editTextPhone4);
        sp=findViewById(R.id.spinner);
        donor=new Donor(DonorData.this);

        al=new ArrayList();
        al.add("A+"); al.add("B+"); al.add("AB+"); al.add("O+"); al.add("O-"); al.add("A-"); al.add("B-"); al.add("AB-");

        arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,al);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(arrayAdapter);

    }

    public void savedata(View view) {
        name=et1.getText().toString();
        add=et2.getText().toString();
        mob=et3.getText().toString();
        blood=sp.getSelectedItem().toString();
        donor.saved(name,add,mob,blood);
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();


    }

    public void anotheruser(View view) {
        Intent intent=new Intent(this,DonorData.class);
        startActivity(intent);
    }
}