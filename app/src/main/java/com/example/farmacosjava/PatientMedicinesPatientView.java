package com.example.farmacosjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PatientMedicinesPatientView extends AppCompatActivity {

    private ListView listview;
    private ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_medicines_patient_view);

        listview = (ListView) findViewById(R.id.listViewMedicines);

        names = new ArrayList<String>();
        names.add("Medicina 1");
        names.add("Medicina 2");
        names.add("Medicina 3");
        names.add("Medicina 4");
        names.add("Medicina 5");

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
}