package com.example.farmacosjava.registerActivities.doctorActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.farmacosjava.R;
import com.example.farmacosjava.registerActivities.PasswordRegisterActivity;

public class healthInstitutionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_institution);
    }

    public void clickNextButton(View view) {
        Intent intent = new Intent(this, PasswordRegisterActivity.class);
        startActivity(intent);
    }
}