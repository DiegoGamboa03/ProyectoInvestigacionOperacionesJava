package com.example.farmacosjava.registerActivities.PatientActivity;

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
import com.example.farmacosjava.registerActivities.doctorActivity.specialityRegisterActivity;
import com.example.farmacosjava.registerActivities.typerRegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class treatmentPlaceRegisterActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_place_register);

        String estadociudad = getIntent().getStringExtra("estadociudad");

        DocumentReference opciones = db.collection("estado-ciudad").document(estadociudad);
        CollectionReference op2;

        ArrayList<String> lugares = new ArrayList<String>();

        opciones.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });


        Spinner spinnerDirection =  (Spinner) findViewById(R.id.spinnerTreatmentPlace);
        String[] array = {"Seleccione","1","2","3","4","5","6","7"};

        spinnerDirection.setAdapter(new ArrayAdapter<String>(treatmentPlaceRegisterActivity.this, android.R.layout.simple_spinner_dropdown_item,array));

    }

    public void clickNextButton(View view) {

        Spinner spinner =  (Spinner) findViewById(R.id.spinnerTreatmentPlace);
        String lugar_tratamiento = spinner.toString();

//        Map<String,String> data = new HashMap<>();
//        data.put("lugar_tratamiento",lugar_tratamiento.getText().toString());

        Intent intent = getIntent();
        String cedula = intent.getStringExtra("cedula");
        String profileType = intent.getStringExtra("UserType");
        String nombre1 = intent.getStringExtra("nombre1");
        String nombre2 = intent.getStringExtra("nombre2");
        String apellido1 = intent.getStringExtra("apellido1");
        String apellido2 = intent.getStringExtra("apellido2");
        String genero = intent.getStringExtra("genero");
        String fecnac = intent.getStringExtra("fecnac");
        String provincia = intent.getStringExtra("provincia");
        String ciudad = intent.getStringExtra("ciudad");

//        db.collection("pacientes").document(cedula).set(data)
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
        intent.putExtra("genero",genero);
        intent.putExtra("fecnac",fecnac);
        intent.putExtra("provincia",provincia);
        intent.putExtra("ciudad",ciudad);
        intent.putExtra("lugar_tratamiento",lugar_tratamiento);
        startActivity(intent);
    }
}