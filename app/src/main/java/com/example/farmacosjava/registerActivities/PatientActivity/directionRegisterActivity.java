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
import com.example.farmacosjava.registerActivities.doctorActivity.specialityRegisterActivity;
import com.example.farmacosjava.registerActivities.typerRegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class directionRegisterActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction_register);

        CollectionReference opciones = db.collection("estado-ciudad");

        ArrayList<String> lugares = new ArrayList<String>();

        opciones.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                System.out.println(document.getId() + " => " + document.getData());
                                lugares.add(document.getId());
//                                myListOfDocuments = document.getId();
//                                System.out.println(document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        Spinner spinnerDirection =  (Spinner) findViewById(R.id.spinnerDirection);
        spinnerDirection.setAdapter(new ArrayAdapter<String>(directionRegisterActivity.this, android.R.layout.simple_spinner_dropdown_item,lugares));
    }

    public void clickNextButton(View view) {


        //Agregar logica para que divida la ciudad y la provincia
        String estadociudad = ((Spinner) findViewById(R.id.spinnerDirection)).getSelectedItem().toString();


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
        intent.putExtra("estadociudad",estadociudad);
        startActivity(intent);
    }
}