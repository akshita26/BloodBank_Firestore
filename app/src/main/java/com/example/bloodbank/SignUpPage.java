package com.example.bloodbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpPage extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5,e6;
    String name,addr,dob,mob,bg,dod;
    DBHlpr dbHlpr;
    CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        e1=findViewById(R.id.editTextTextPersonName2);
        e2=findViewById(R.id.editTextTextPersonName3);
        e3=findViewById(R.id.editTextTextPersonName4);
        e4=findViewById(R.id.editTextPhone2);
        e5=findViewById(R.id.editTextTextPersonName6);
        e6=findViewById(R.id.editTextTextPersonName5);
        cb=findViewById(R.id.checkBox);
        dbHlpr=new DBHlpr(this);
    }

    public void signupclick(View view) {
        if(cb.isChecked()) {
            name = e1.getText().toString();
            addr = e2.getText().toString();
            dob = e3.getText().toString();
            mob = e4.getText().toString();
            bg = e5.getText().toString();
            dod = e6.getText().toString();
            dbHlpr.savedata(name, addr, dob, mob, bg, dod);
            Toast.makeText(this, "Your details are safe with us!", Toast.LENGTH_LONG).show();

            Intent i = new Intent(this, ListDetails.class);
            startActivity(i);
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