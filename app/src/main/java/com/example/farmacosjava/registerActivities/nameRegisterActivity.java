package com.example.farmacosjava.registerActivities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.example.farmacosjava.R;
import com.example.farmacosjava.registerActivities.PatientActivity.genreActivity;
import com.example.farmacosjava.registerActivities.doctorActivity.specialityRegisterActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class nameRegisterActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_register);
    }

    EditText cedula = findViewById(R.id.editTextName);
    EditText nombre1 = findViewById(R.id.editTextName2);
    EditText nombre2 = findViewById(R.id.editTextSecondText);
    EditText apellido1 = findViewById(R.id.editTextLastName);
    EditText apellido2 = findViewById(R.id.editTextSecondSurname);

    public void clickNextButton(View view) {

        Map<String,String> data = new HashMap<>();
        data.put("nombre1",nombre1.getText().toString());
        data.put("nombre2",nombre2.getText().toString());
        data.put("apellido1",apellido1.getText().toString());
        data.put("apellido2",apellido2.getText().toString());

        Intent intent = getIntent();
        String profileType = intent.getStringExtra("UserType");
        if (profileType.equals("Paciente")) { //En caso de ser un paciente
            db.collection("pacientes").document(cedula.getText().toString()).set(data)
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error en registro", e);
                        }
                    });

            intent = new Intent(this, genreActivity.class);
            intent.putExtra("cedula",cedula.getText().toString());
            intent.putExtra("UserType",profileType);
            startActivity(intent);
        } else {//En caso de ser doctor
            db.collection("profesionales").document(cedula.getText().toString()).set(data)
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error en registro", e);
                        }
                    });

            intent = new Intent(this, specialityRegisterActivity.class);
            intent.putExtra("cedula",cedula.getText().toString());
            intent.putExtra("UserType",profileType);
            startActivity(intent);
        }
    }
}