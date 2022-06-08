package com.example.farmacosjava;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farmacosjava.registerActivities.typerRegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickTextView(View view){
        Intent intent = new Intent(this, typerRegisterActivity.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), "Remplazar por tu codigo", Toast.LENGTH_LONG)
                .show();
    }

    public void clickNextButton(View view){
        EditText editTextUsername = findViewById(R.id.editTextUsername);
        EditText editTextPassword = findViewById(R.id.editTextPassword);

        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();


        if(editTextUsername.getText().toString().equals("d")){
            Intent intent = new Intent(this, DoctorPacientListActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }else{
           Intent intent = new Intent(this, PatientMedicinesPatientView.class);
            startActivity(intent);
        }

        DocumentReference docRef = db.collection("profesionales").document(username);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists() && document.getString("password").equals(password)) {
                        Intent intent = new Intent(MainActivity.this, DoctorPacientListActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                    } else {
                        DocumentReference docRef2 = db.collection("pacientes").document(username);
                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists() && document.getString("password").equals(password)) {
                                        Intent intent = new Intent(MainActivity.this, PatientMedicinesPatientView.class);
                                        intent.putExtra("username", username);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Error de usuario o contraseña", Toast.LENGTH_LONG)
                                                .show();
                                    }
                                }
                            }});
                        }}
                        else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
//        Toast.makeText(getApplicationContext(), "Remplazar por tu codigo", Toast.LENGTH_LONG)
//                .show();
    }
}