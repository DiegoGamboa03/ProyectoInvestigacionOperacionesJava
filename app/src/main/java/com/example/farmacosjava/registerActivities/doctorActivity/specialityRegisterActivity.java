package com.example.farmacosjava.registerActivities.doctorActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.farmacosjava.MainActivity;
import com.example.farmacosjava.R;

public class specialityRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speciality_register);
    }

    public void clickNextButton(View view) {
        Intent intent = new Intent(this, healthInstitutionActivity.class);
        startActivity(intent);
    }
}