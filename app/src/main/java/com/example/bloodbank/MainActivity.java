package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sigin(View view) {
        Intent i= new Intent(this,SignInPage.class);
        startActivity(i);
    }

    public void sigup(View view) {
        Intent intent=new Intent(this,SignUpPage.class);
        startActivity(intent);
    }

    public void admi(View view) {
        Intent intent=new Intent(this,AdminPage.class);
        startActivity(intent);
    }
}