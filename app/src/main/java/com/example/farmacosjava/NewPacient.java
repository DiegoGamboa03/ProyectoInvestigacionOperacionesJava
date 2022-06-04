package com.example.farmacosjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.farmacosjava.registerActivities.PasswordRegisterActivity;

public class NewPacient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pacient);
    }

    public void clickNextButton(View view) {
        Intent intent = new Intent(this, PasswordRegisterActivity.class);
        startActivity(intent);
    }
}