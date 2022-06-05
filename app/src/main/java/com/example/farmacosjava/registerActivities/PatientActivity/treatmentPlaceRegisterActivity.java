package com.example.farmacosjava.registerActivities.PatientActivity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.farmacosjava.R;
import com.example.farmacosjava.registerActivities.PasswordRegisterActivity;
import com.example.farmacosjava.registerActivities.doctorActivity.specialityRegisterActivity;
import com.example.farmacosjava.registerActivities.typerRegisterActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class treatmentPlaceRegisterActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_place_register);
    }

    EditText lugar_tratamiento = findViewById(R.id.editTextTextPersonName);

    public void clickNextButton(View view) {
        Map<String,String> data = new HashMap<>();
        data.put("lugar_tratamiento",lugar_tratamiento.getText().toString());

        Intent intent = getIntent();
        String cedula = intent.getStringExtra("cedula");
        String profileType = intent.getStringExtra("UserType");

        db.collection("pacientes").document(cedula).set(data)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error en registro", e);
                    }
                });
        intent = new Intent(this, specialityRegisterActivity.class);
        intent.putExtra("cedula",cedula);
        intent.putExtra("UserType",profileType);
        startActivity(intent);
    }
}