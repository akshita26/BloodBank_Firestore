package com.example.bloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

//import androidx.annotation.NonNull;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;


public class SignInPage extends AppCompatActivity {

    EditText e1,e2;
    DBHlpr dbHlpr;
    String s1,s2,codeSent;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);
        e1=findViewById(R.id.editTextPhone);
        e2=findViewById(R.id.editTextTextPersonName);
        s1=e1.getText().toString();
        dbHlpr=new DBHlpr(this);
        FirebaseApp.initializeApp(SignInPage.this);

        mAuth=FirebaseAuth.getInstance();
    }

    public void signinclick(View view) {
        s1=e1.getText().toString();
        s2=e2.getText().toString();
//        if(s2.isEmpty()){
//            Toast.makeText(this,"Enter verification code",Toast.LENGTH_SHORT).show();
//            e1.requestFocus();
//            return;
//        }
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, s2);
        db=FirebaseFirestore.getInstance();
        CollectionReference cref=db.collection("client");
        Query q1=cref.whereEqualTo("mobile",s1);
        System.out.println("Starting listener");
        q1.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Intent intent = new Intent(SignInPage.this,dataList.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(SignInPage.this, "User not found!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //here we can open a new activity
                            Intent i = new Intent(SignInPage.this, dataList.class);
                            startActivity(i);
                            finish();

                            Toast.makeText(getApplicationContext(),"Login successful", Toast.LENGTH_SHORT).show();
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(getApplicationContext(),"Incorrect Verification code", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    public void sendotp(View view) {

            String phoneNumber = e1.getText().toString();
            if(phoneNumber.isEmpty()){
                Toast.makeText(this,"Enter phone number",Toast.LENGTH_SHORT).show();
                e1.requestFocus();
                return;
            }

            if(phoneNumber.length() < 10){
                Toast.makeText(this,"Enter correct phone number",Toast.LENGTH_SHORT).show();
                e1.requestFocus();
                return;
            }
            if (phoneNumber.length()==10){
                phoneNumber="+91"+phoneNumber;
            }

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        Log.d("abcd", "sendotp: "+phoneNumber);
        PhoneAuthProvider.verifyPhoneNumber(options);      // OnVerificationStateChangedCallbacks


    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeSent = s;
        }
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(SignInPage.this, "Failed!", Toast.LENGTH_SHORT).show();
        }

    };
}