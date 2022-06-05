package com.example.farmacosjava.registerActivities.PatientActivity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.farmacosjava.R;
import com.example.farmacosjava.registerActivities.doctorActivity.specialityRegisterActivity;
import com.example.farmacosjava.registerActivities.typerRegisterActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class genreActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
    }

    public void clickNextButton(View view) {

        String genero = ((Spinner) findViewById(R.id.spinnerGenre)).getSelectedItem().toString();

        //Aqui va el hashmap
//        Map<String,String> data = new HashMap<>();
//        data.put("genero",genero.getSelectedItem().toString());


        Intent intent = getIntent();
        String cedula = intent.getStringExtra("cedula");
        String profileType = intent.getStringExtra("UserType");
        String nombre1 = intent.getStringExtra("nombre1");
        String nombre2 = intent.getStringExtra("nombre2");
        String apellido1 = intent.getStringExtra("apellido1");
        String apellido2 = intent.getStringExtra("apellido2");

//        db.collection("pacientes").document(cedula).set(data)
//                .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.w(TAG, "Error en registro", e);
//                        }
//                    });
            intent = new Intent(this, birthdateRegisterActivity.class);
            intent.putExtra("cedula",cedula);
            intent.putExtra("UserType",profileType);
            intent.putExtra("nombre1",nombre1);
            intent.putExtra("nombre2",nombre2);
            intent.putExtra("apellido1",apellido1);
            intent.putExtra("apellido2",apellido2);
            intent.putExtra("genero",genero);
            startActivity(intent);

    }
}