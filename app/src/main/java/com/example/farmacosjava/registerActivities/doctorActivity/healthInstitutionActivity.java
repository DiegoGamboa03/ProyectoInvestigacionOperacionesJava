package com.example.farmacosjava.registerActivities.doctorActivity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.farmacosjava.R;
import com.example.farmacosjava.registerActivities.PasswordRegisterActivity;
import com.example.farmacosjava.registerActivities.PatientActivity.directionRegisterActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class healthInstitutionActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_institution);

        Spinner spinnerDirection =  (Spinner) findViewById(R.id.spinnerHealthInstitution);
        String[] array = {"Seleccione","1","2","3","4","5","6","7"};

        spinnerDirection.setAdapter(new ArrayAdapter<String>(healthInstitutionActivity.this, android.R.layout.simple_spinner_dropdown_item,array));

    }

    public void clickNextButton(View view) {

        Spinner spinnerDirection =  (Spinner) findViewById(R.id.spinnerHealthInstitution);
        String institucion = spinnerDirection.toString();

//        Map<String, String> data = new HashMap<>();
//        data.put("institucion", institucion.getText().toString());

        Intent intent = getIntent();
        String cedula = intent.getStringExtra("cedula");
        String profileType = intent.getStringExtra("UserType");
        String nombre1 = intent.getStringExtra("nombre1");
        String nombre2 = intent.getStringExtra("nombre2");
        String apellido1 = intent.getStringExtra("apellido1");
        String apellido2 = intent.getStringExtra("apellido2");
        String area = intent.getStringExtra("area");

//        db.collection("profesionales").document(cedula).set(data)
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error en registro", e);
//                    }
//                });

        intent = new Intent(this, PasswordRegisterActivity.class);
        intent.putExtra("cedula",cedula);
        intent.putExtra("UserType",profileType);
        intent.putExtra("nombre1",nombre1);
        intent.putExtra("nombre2",nombre2);
        intent.putExtra("apellido1",apellido1);
        intent.putExtra("apellido2",apellido2);
        intent.putExtra("area",area);
        intent.putExtra("institucion",institucion);
        startActivity(intent);
    }
}