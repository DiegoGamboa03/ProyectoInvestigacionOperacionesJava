package com.example.farmacosjava.registerActivities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.farmacosjava.MainActivity;
import com.example.farmacosjava.R;
import com.example.farmacosjava.registerActivities.PatientActivity.genreActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PasswordRegisterActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_register);
    }



    public void clickNextButton(View view) {

        Intent intent = getIntent();
        String cedula = intent.getStringExtra("cedula");
        String profileType = intent.getStringExtra("UserType");


        String password = ((EditText) findViewById(R.id.editTextSpeciality)).getText().toString();

//        Map<String, String> data = new HashMap<>();
//        data.put("password", password.getText().toString());

        if (profileType.equals("Paciente")) { //En caso de ser un paciente

            Map<String,String> data = new HashMap<>();
            data.put("nombre1",intent.getStringExtra("nombre1"));
            data.put("nombre2",intent.getStringExtra("nombre2"));
            data.put("apellido1",intent.getStringExtra("apellido1"));
            data.put("apellido2",intent.getStringExtra("apellido2"));
            data.put("fecnac",intent.getStringExtra("fecnac"));
            data.put("genero",intent.getStringExtra("genero"));
            data.put("lugar_tratamiento",intent.getStringExtra("lugar_tratamiento"));
            data.put("provincia",intent.getStringExtra("provincia"));
            data.put("ciudad", intent.getStringExtra("ciudad"));
            data.put("password",password);

            db.collection("pacientes").document(cedula).set(data)
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error en registro", e);
                        }
                    });

            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {//En caso de ser doctor

            Map<String,String> data = new HashMap<>();
            data.put("nombre1",intent.getStringExtra("nombre1"));
            data.put("nombre2",intent.getStringExtra("nombre2"));
            data.put("apellido1",intent.getStringExtra("apellido1"));
            data.put("apellido2",intent.getStringExtra("apellido2"));
            data.put("area",intent.getStringExtra("area"));
            data.put("institucion",intent.getStringExtra("institucion"));
            data.put("password",password);

            db.collection("profesionales").document(cedula).set(data)
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error en registro", e);
                        }
                    });

            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}