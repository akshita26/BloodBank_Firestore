package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDetails extends AppCompatActivity {


    ListView lv;
    Donor donor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details);

        lv=findViewById(R.id.lv);
        donor = new Donor(this);

        ArrayList<String> al= new ArrayList<>();
        ArrayList<String> al2= new ArrayList<>();
        ArrayList<String> mobil= new ArrayList<>();
        Cursor data= donor.getListContents();

        if(data.getCount()<1){
            Toast.makeText(this, "No donor!", Toast.LENGTH_SHORT).show();
        }
        else{
            while(data.moveToNext()){
                al.add("Name: "+ data.getString(0)+"\nBlood Group: "+data.getString(3));
                al2.add("Name: "+ data.getString(0)+"\nBlood Group: "+data.getString(3)+"\nMobile: "+data.getString(2)+"\nAddress: "+data.getString(1));
                mobil.add(data.getString(2));
                ArrayAdapter listAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,al);
                lv.setAdapter(listAdapter);
            }
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==id){
                    Intent i = new Intent(ListDetails.this,DonorDetails.class);
                    String s1= (String) al2.get(position);
                    String s2=(String) mobil.get(position);
                    i.putExtra("abc",s1);
                    i.putExtra("xyz",s2);
                    startActivity(i);

                }
            }
        });

    }

}