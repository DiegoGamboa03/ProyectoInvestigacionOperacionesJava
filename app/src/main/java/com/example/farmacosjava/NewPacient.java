package com.example.farmacosjava;

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

import com.example.farmacosjava.registerActivities.PasswordRegisterActivity;
import com.example.farmacosjava.registerActivities.PatientActivity.directionRegisterActivity;
import com.example.farmacosjava.registerActivities.PatientActivity.treatmentPlaceRegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

public class NewPacient extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Map<String,String> actuales = new HashMap<>();
    Map<String,String> disponiblesMap = new HashMap<>();
    ArrayList<String> disponibles = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        DocumentReference docRef = db.collection("profesionales").document(username);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        docRef.collection("pacientesRegistrados").get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task2) {
                                if (task2.isSuccessful()){
                                    for (QueryDocumentSnapshot doc2 : task2.getResult()){
                                        actuales.put(doc2.getId(), doc2.getString("nombre1")+
                                                " "+doc2.getString("apellido1"));
                                    }
//                                    Spinner spinnerTreatment =  (Spinner) findViewById(R.id.spinnerTreatmentPlace);
//                                    spinnerTreatment.setAdapter(new ArrayAdapter<String>(treatmentPlaceRegisterActivity.this,
//                                            android.R.layout.simple_spinner_dropdown_item,hospitales));
                                }
                            }

                        });
//                        System.out.println("DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        CollectionReference colRef = db.collection("pacientes");
        colRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (!actuales.containsKey(document.getId()))
                        System.out.println(document.getId() + " => " + document.getData());
                        disponiblesMap.put(document.getId(), document.getString("nombre1")+
                                " "+document.getString("apellido1"));
                        disponibles.add(document.getString("nombre1")+
                                " "+document.getString("apellido1"));
//                                myListOfDocuments = document.getId();
//                                System.out.println(document.getId() + " => " + document.getData());
                    }
                    Spinner spinnerPacient =  (Spinner) findViewById(R.id.spinnerPacient);
                    spinnerPacient.setAdapter(new ArrayAdapter<String>(NewPacient.this,
                            android.R.layout.simple_spinner_dropdown_item, disponibles));
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pacient);
    }

    public static <K, V> K getKey(Map<K, V> map, V value)
    {
        for (Map.Entry<K, V> entry: map.entrySet())
        {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void clickNextButton(View view) {
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String paciente = ((Spinner) findViewById(R.id.spinnerPacient)).getSelectedItem().toString();
        String idPaciente = getKey(disponiblesMap, paciente);

        Map<String,String> data = new HashMap<>();
        data.put("nombre",paciente);

        db.collection("profesionales").document(username)
                .collection("pacientesRegistrados").document(idPaciente).set(data)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error en registro",e);
                    }
                });

        intent = new Intent(this, DoctorPacientListActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }
}