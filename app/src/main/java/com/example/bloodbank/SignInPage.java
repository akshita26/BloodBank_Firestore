package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignInPage extends AppCompatActivity {

    EditText e1,e2;
    DBHlpr dbHlpr;
    String s1,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);
        e1=findViewById(R.id.editTextPhone);
        e2=findViewById(R.id.editTextTextPersonName);
        dbHlpr=new DBHlpr(this);
    }

    public void signinclick(View view) {
        s1=e1.getText().toString();
        s2=e2.getText().toString();
        if(s2.equals("otp")){
            boolean pwd=dbHlpr.checkpd(s1);
            if(pwd==true){
                Intent i =new Intent(this,ListDetails.class);
                startActivity(i);
            }
            else{
                Toast.makeText(this, "User not found!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Invalid otp", Toast.LENGTH_SHORT).show();
        }
    }
}