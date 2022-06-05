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
import com.example.farmacosjava.registerActivities.doctorActivity.specialityRegisterActivity;
import com.example.farmacosjava.registerActivities.typerRegisterActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class directionRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction_register);
    }

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void clickNextButton(View view) {

        //Esto va a cambiar por un spinner
        String provincia = ((EditText) findViewById(R.id.editTextProvince)).getText().toString();
        String ciudad = ((EditText) findViewById(R.id.editTextCity)).getText().toString();

//        Map<String,String> data = new HashMap<>();
//        data.put("provincia",provincia.getText().toString());
//        data.put("ciudad",ciudad.getText().toString());

        Intent intent = getIntent();
        String cedula = intent.getStringExtra("cedula");
        String profileType = intent.getStringExtra("UserType");
        String nombre1 = intent.getStringExtra("nombre1");
        String nombre2 = intent.getStringExtra("nombre2");
        String apellido1 = intent.getStringExtra("apellido1");
        String apellido2 = intent.getStringExtra("apellido2");
        String genero = intent.getStringExtra("genero");
        String fecnac = intent.getStringExtra("fecnac");

//        db.collection("pacientes").document(cedula).set(data)
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error en registro", e);
//                    }
//                });

        intent = new Intent(this, treatmentPlaceRegisterActivity.class);
        intent.putExtra("cedula",cedula);
        intent.putExtra("UserType",profileType);
        intent.putExtra("nombre1",nombre1);
        intent.putExtra("nombre2",nombre2);
        intent.putExtra("apellido1",apellido1);
        intent.putExtra("apellido2",apellido2);
        intent.putExtra("genero",genero);
        intent.putExtra("fecnac",fecnac);
        intent.putExtra("provincia",provincia);
        intent.putExtra("ciudad",ciudad);
        startActivity(intent);
    }
}