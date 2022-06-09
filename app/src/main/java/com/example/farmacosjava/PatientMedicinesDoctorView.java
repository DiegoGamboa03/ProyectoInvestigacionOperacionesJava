package com.example.farmacosjava;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.farmacosjava.registerActivities.PasswordRegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PatientMedicinesDoctorView extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ListView listview;
    private ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_medicines_doctor_view);

        listview = (ListView) findViewById(R.id.listViewMedicines);

        names = new ArrayList<String>();
        names.add("Medicina 1");
        names.add("Medicina 2");
        names.add("Medicina 3");
        names.add("Medicina 4");
        names.add("Medicina 5");

        Intent intent = getIntent();
        String paciente = intent.getStringExtra("paciente");

        ArrayList<String> meds = new ArrayList<String>();

        DocumentReference docRef = db.collection("pacientes").document(paciente);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        docRef.collection("medicamentosRegistrados").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task2) {
                                if (task2.isSuccessful()){
                                    for (QueryDocumentSnapshot doc2 : task2.getResult()){
                                        meds.add(doc2.getId());
                                    }
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(PatientMedicinesDoctorView.this,
                                            android.R.layout.simple_list_item_1, meds);

                                    listview.setAdapter(adapter);

                                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                            Intent intent = getIntent();
                                            String username = intent.getStringExtra("username");
                                            String paciente = intent.getStringExtra("paciente");
                                            String med = adapter.getItem(position);

                                            intent = new Intent(getApplicationContext(), PatientMedicinesDoctorView.class);
                                            intent.putExtra("username",username);
                                            intent.putExtra("paciente",paciente);
                                            intent.putExtra("med",med);
//                                            startActivity(intent);
                                        }
                                    });
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
    }

    public void clickFloatingActionButton(View view) {
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String paciente = intent.getStringExtra("paciente");

        intent = new Intent(this, NewMedicineForPacient.class);
        intent.putExtra("username",username);
        intent.putExtra("paciente",paciente);
        startActivity(intent);
    }
}