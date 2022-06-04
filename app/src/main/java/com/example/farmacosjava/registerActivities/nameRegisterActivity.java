package com.example.farmacosjava.registerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.farmacosjava.MainActivity;
import com.example.farmacosjava.R;
import com.example.farmacosjava.registerActivities.PatientActivity.genreActivity;
import com.example.farmacosjava.registerActivities.doctorActivity.specialityRegisterActivity;

public class nameRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_register);
    }

    public void clickNextButton(View view) {
        //se recibe el tipo de usuario de la activity anterior
        Intent intent = getIntent();
        String profileType = intent.getStringExtra("UserType");
        if (profileType.equals("Paciente")) { //En caso de ser un paciente
            intent = new Intent(this, genreActivity.class);
            startActivity(intent);
        } else {//En caso de ser doctor
            intent = new Intent(this, specialityRegisterActivity.class);
            startActivity(intent);
        }
    }
}