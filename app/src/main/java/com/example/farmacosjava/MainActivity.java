package com.example.farmacosjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farmacosjava.registerActivities.typerRegisterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickTextView(View view){
        Intent intent = new Intent(this, typerRegisterActivity.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), "Remplazar por tu codigo", Toast.LENGTH_LONG)
                .show();
    }

    public void clickNextButton(View view){
        EditText editTextUsername = findViewById(R.id.editTextUsername);
        if(editTextUsername.getText().toString().equals("d")){
            Intent intent = new Intent(this, DoctorPacientListActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, PatientMedicinesPatientView.class);
            startActivity(intent);
        }
        Toast.makeText(getApplicationContext(), "Remplazar por tu codigo", Toast.LENGTH_LONG)
                .show();
    }
}