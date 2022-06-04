package com.example.farmacosjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.farmacosjava.registerActivities.PasswordRegisterActivity;

import java.util.ArrayList;

public class DoctorPacientListActivity extends AppCompatActivity {

    private ListView listview;
    private ArrayList<String> names;

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), PatientMedicinesDoctorView.class);
                startActivity(intent);
            }
        });
    }

    public void clickFloatingActionButton(View view) {
        Intent intent = new Intent(this, NewPacient.class);
        startActivity(intent);
    }
}