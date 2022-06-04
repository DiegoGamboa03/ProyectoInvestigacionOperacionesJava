package com.example.farmacosjava.registerActivities.PatientActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.farmacosjava.R;
import com.example.farmacosjava.registerActivities.typerRegisterActivity;

public class directionRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction_register);
    }

    public void clickNextButton(View view) {
        Intent intent = new Intent(this, treatmentPlaceRegisterActivity.class);
        startActivity(intent);
    }
}