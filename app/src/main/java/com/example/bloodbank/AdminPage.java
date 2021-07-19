package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminPage extends AppCompatActivity {
    EditText e1,e2;
    String s1,s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        e1=findViewById(R.id.editTextPhone3);
        e2=findViewById(R.id.editTextTextPassword);

    }

    public void submit(View view) {
        s1=e1.getText().toString();
        s2=e2.getText().toString();
        String mob="7976488745";
        String pwd= "admin";
        Log.d("abcd", "submit: "+s1+"\n"+s2);
        if(s1.equals(mob) && s2.equals(pwd)){
            Intent i = new Intent(this, DonorData.class);
            startActivity(i);
        }
        else{
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }
}