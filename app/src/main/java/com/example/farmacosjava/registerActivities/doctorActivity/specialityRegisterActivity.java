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

import com.example.farmacosjava.MainActivity;
import com.example.farmacosjava.R;
import com.example.farmacosjava.registerActivities.PatientActivity.directionRegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class specialityRegisterActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speciality_register);

        CollectionReference areas = db.collection("especialidades");

        ArrayList<String> lista = new ArrayList<String>();

        areas.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        System.out.println(document.getId() + " => " + document.getData());
                        lista.add(document.getId());
                    }
                    Spinner spinner =  (Spinner) findViewById(R.id.spinnerSpecialtity);
                    spinner.setAdapter(new ArrayAdapter<String>(specialityRegisterActivity.this, android.R.layout.simple_spinner_dropdown_item,lista));
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }

    public void clickNextButton(View view) {

        String area =  ((Spinner) findViewById(R.id.spinnerSpecialtity)).getSelectedItem().toString();

//        Map<String, String> data = new HashMap<>();
//        data.put("area", area.getText().toString());

        Intent intent = getIntent();
        String cedula = intent.getStringExtra("cedula");
        String profileType = intent.getStringExtra("UserType");
        String nombre1 = intent.getStringExtra("nombre1");
        String nombre2 = intent.getStringExtra("nombre2");
        String apellido1 = intent.getStringExtra("apellido1");
        String apellido2 = intent.getStringExtra("apellido2");

//        db.collection("profesionales").document(cedula).set(data)
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error en registro", e);
//                    }
//                });

        intent = new Intent(this, healthInstitutionActivity.class);
        intent.putExtra("cedula",cedula);
        intent.putExtra("UserType",profileType);
        intent.putExtra("nombre1",nombre1);
        intent.putExtra("nombre2",nombre2);
        intent.putExtra("apellido1",apellido1);
        intent.putExtra("apellido2",apellido2);
        intent.putExtra("area",area);
        startActivity(intent);
    }
}