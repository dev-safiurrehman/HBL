package com.example.hbl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Signup extends AppCompatActivity {

    EditText mName,mEmail,mPassword;
    SharedPreferences ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mName = findViewById(R.id.fullname);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);

        // initialize the shared preference in private mode
        ref = getSharedPreferences("myapp",MODE_PRIVATE);

    }

    public void signup(View view) {

        //getting edit text values
        String name = mName.getText().toString();
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        //save the values to shared preferences
        ref.edit().putString("name",name).apply();
        ref.edit().putString("email",email).apply();
        ref.edit().putString("password",password).apply();
        ref.edit().putBoolean("login",true).apply();

        //call the main activity after signup successfull
        Intent i = new Intent(Signup.this,MainActivity.class);
        startActivity(i);


    }
}