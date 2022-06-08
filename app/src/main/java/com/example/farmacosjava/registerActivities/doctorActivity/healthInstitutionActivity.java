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
import com.example.farmacosjava.registerActivities.PatientActivity.treatmentPlaceRegisterActivity;
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
import java.util.HashMap;
import java.util.Map;

public class healthInstitutionActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_institution);

        ArrayList<String> hospitales = new ArrayList<String>();

        CollectionReference docRef = db.collection("estado-ciudad");
        docRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        docRef.document(document.getId()).collection("hospitales").get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task2) {
                                        if (task2.isSuccessful()) {
                                            for (QueryDocumentSnapshot doc2 : task2.getResult()) {
                                                hospitales.add(doc2.getId());
                                            }

                                        }
                                    }
                                });
//                        System.out.println(document.getId() + " => " + document.getData());
//                        lugares.add(document.getId());
//                                myListOfDocuments = document.getId();
//                                System.out.println(document.getId() + " => " + document.getData());
                    }
                    Spinner spinnerHealthInstitution =  (Spinner) findViewById(R.id.spinnerHealthInstitution);
                    spinnerHealthInstitution.setAdapter(new ArrayAdapter<String>(healthInstitutionActivity.this, android.R.layout.simple_spinner_dropdown_item,hospitales));
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }

//        Spinner spinnerHealth =  (Spinner) findViewById(R.id.spinnerHealthInstitution);
//        String[] array = {"Seleccione","1","2","3","4","5","6","7"};

//        spinnerHealth.setAdapter(new ArrayAdapter<String>(healthInstitutionActivity.this, android.R.layout.simple_spinner_dropdown_item,array));

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