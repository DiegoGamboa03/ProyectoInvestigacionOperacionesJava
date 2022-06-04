package com.example.farmacosjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.farmacosjava.registerActivities.PasswordRegisterActivity;

public class PatientMedicinesDoctorView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_medicines_doctor_view);
    }

    public void clickFloatingActionButton(View view) {
        Intent intent = new Intent(this, NewMedicineForPacient.class);
        startActivity(intent);
    }
}