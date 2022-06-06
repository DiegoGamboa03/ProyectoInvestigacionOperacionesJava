package com.example.farmacosjava;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.farmacosjava.registerActivities.doctorActivity.specialityRegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NewMedicineForPacient extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medicine_for_pacient);

        CollectionReference meds = db.collection("medicamentos");
        ArrayList<String> medsArr = new ArrayList<String>();

        meds.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        medsArr.add(document.getId());
                    }
                    Spinner spinner =  (Spinner) findViewById(R.id.spinnerMedicine);
                    spinner.setAdapter(new ArrayAdapter<String>(NewMedicineForPacient.this, android.R.layout.simple_spinner_dropdown_item,medsArr));
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

        CollectionReference dosis = db.collection("dosis");
        ArrayList<String> dosisArr = new ArrayList<String>();

        dosis.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        dosisArr.add(document.getId());
                    }
                    Spinner spinner =  (Spinner) findViewById(R.id.spinnerDose);
                    spinner.setAdapter(new ArrayAdapter<String>(NewMedicineForPacient.this, android.R.layout.simple_spinner_dropdown_item,dosisArr));
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

        CollectionReference presentaciones = db.collection("presentaciones");
        ArrayList<String> presentacionesArr = new ArrayList<String>();
        presentaciones.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        presentacionesArr.add(document.getId());
                    }
                    Spinner spinner =  (Spinner) findViewById(R.id.spinnerPresentation);
                    spinner.setAdapter(new ArrayAdapter<String>(NewMedicineForPacient.this, android.R.layout.simple_spinner_dropdown_item,presentacionesArr));
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

        CollectionReference frecuencia = db.collection("frecuencia");
        ArrayList<String> frecuenciaArr = new ArrayList<String>();
        presentaciones.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        frecuenciaArr.add(document.getId());
                    }
                    Spinner spinner =  (Spinner) findViewById(R.id.spinnerPresentation);
                    spinner.setAdapter(new ArrayAdapter<String>(NewMedicineForPacient.this, android.R.layout.simple_spinner_dropdown_item,frecuenciaArr));
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }

    public void clickNextButton(){
        String med = ((Spinner) findViewById(R.id.spinnerMedicine)).getSelectedItem().toString();
        String dose = ((Spinner) findViewById(R.id.spinnerDose)).getSelectedItem().toString();
        String pres = ((Spinner) findViewById(R.id.spinnerPresentation)).getSelectedItem().toString();
        String takefreq = ((Spinner) findViewById(R.id.spinnerTakeFrecuency)).getSelectedItem().toString();
        String duration = ((EditText) findViewById(R.id.editTextDurationDate)).getText().toString();
        String medtime = ((EditText) findViewById(R.id.editTextMedicationTime)).getText().toString();

        Intent intent = getIntent();
        String cedula_prof = intent.getStringExtra("cedula_prof");
        String cedula_pac = intent.getStringExtra("cedula_pac");

        Map<String,String> data = new HashMap<>();
        data.put("dosis",dose);
        data.put("presentacion",pres);
        data.put("frecuencia de la toma",takefreq);
        data.put("duracion",duration);
        data.put("tiempo medicacion",medtime);

        String pk = cedula_prof+"-"+cedula_pac+"-"+med;

        db.collection("meds_pacientes_profesional").document(pk).set(data)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error en registro", e);
                    }
                });

    }
}