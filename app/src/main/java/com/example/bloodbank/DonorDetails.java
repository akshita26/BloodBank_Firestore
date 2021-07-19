package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DonorDetails extends AppCompatActivity {

    TextView textView;
    String s,s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_details);

        textView=findViewById(R.id.textView3);
        s=getIntent().getStringExtra("abc");
        s1=getIntent().getStringExtra("xyz");


        textView.setText(s);

    }

    public void call(View view) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+s1));
        startActivity(callIntent);
    }
}
