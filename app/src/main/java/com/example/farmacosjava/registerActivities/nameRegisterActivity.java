package com.example.farmacosjava.registerActivities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.example.farmacosjava.R;
import com.example.farmacosjava.registerActivities.PatientActivity.genreActivity;
import com.example.farmacosjava.registerActivities.doctorActivity.specialityRegisterActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class nameRegisterActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_register);
    }

    public void clickNextButton(View view) {
        String cedula = ((EditText) findViewById(R.id.editTextName)).getText().toString();
        String nombre1 = ((EditText) findViewById(R.id.editTextName2)).getText().toString();
        String nombre2 = ((EditText) findViewById(R.id.editTextSecondText)).getText().toString();
        String apellido1 = ((EditText) findViewById(R.id.editTextLastName)).getText().toString();
        String apellido2 = ((EditText) findViewById(R.id.editTextSecondSurname)).getText().toString();

//        Map<String,String> data = new HashMap<>();
//        data.put("nombre1",nombre1);
//        data.put("nombre2",nombre2);
//        data.put("apellido1",apellido1);
//        data.put("apellido2",apellido2);

        Intent intent = getIntent();
        String profileType = intent.getStringExtra("UserType");
        if (profileType.equals("Paciente")) { //En caso de ser un paciente
            intent = new Intent(this, genreActivity.class);
            intent.putExtra("cedula",cedula);
            intent.putExtra("UserType",profileType);
            intent.putExtra("nombre1",nombre1);
            intent.putExtra("nombre2",nombre2);
            intent.putExtra("apellido1",apellido1);
            intent.putExtra("apellido2",apellido2);
            startActivity(intent);
        } else {//En caso de ser doctor
            intent = new Intent(this, specialityRegisterActivity.class);
            intent.putExtra("cedula",cedula);
            intent.putExtra("UserType",profileType);
            intent.putExtra("nombre1",nombre1);
            intent.putExtra("nombre2",nombre2);
            intent.putExtra("apellido1",apellido1);
            intent.putExtra("apellido2",apellido2);
            startActivity(intent);
        }
    }
}