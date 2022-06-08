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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.farmacosjava.registerActivities.PasswordRegisterActivity;
import com.example.farmacosjava.registerActivities.PatientActivity.treatmentPlaceRegisterActivity;
import com.example.farmacosjava.registerActivities.doctorActivity.healthInstitutionActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DoctorPacientListActivity extends AppCompatActivity {

    private ListView listview;
    private ArrayList<String> names;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_pacient_list);

        listview = (ListView) findViewById(R.id.listViewPacients);

        names = new ArrayList<String>();
        names.add("Veracruz");
        names.add("Tabasco");
        names.add("Chiapas");
        names.add("Campeche");
        names.add("Quintana Roo");

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        Map<String,String> pacientesMap = new HashMap<>();
        ArrayList<String> pacientes = new ArrayList<String>();

        DocumentReference docRef = db.collection("profesionales").document(username);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        docRef.collection("pacientesRegistrados").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task2) {
                                if (task2.isSuccessful()){
                                    for (QueryDocumentSnapshot doc2 : task2.getResult()){
                                            pacientesMap.put(doc2.getId(), doc2.getString("nombre"));
                                            pacientes.add(doc2.getString("nombre"));
                                    }
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(DoctorPacientListActivity.this,
                                            android.R.layout.simple_list_item_1, pacientes);

                                    listview.setAdapter(adapter);

                                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                            Intent intent = getIntent();
                                            String username = intent.getStringExtra("username");
                                            String paciente = adapter.getItem(position);
                                            String idPaciente = getKey(pacientesMap, paciente);

                                            intent = new Intent(getApplicationContext(), PatientMedicinesDoctorView.class);
                                            intent.putExtra("username",username);
                                            intent.putExtra("paciente",idPaciente);
                                            startActivity(intent);
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

        intent = new Intent(this, NewPacient.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }
}