package com.example.farmacosjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.farmacosjava.registerActivities.typerRegisterActivity;

public class TakeVerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_verification);
    }

    public void clickYesButton(View view){
        Intent intent = new Intent(this, TakeVerificationYesAnswerActivity.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), "Remplazar por tu codigo", Toast.LENGTH_LONG)
                .show();
    }

    public void clickNoButton(View view){
        Intent intent = new Intent(this, TakeVerificationNoAnswerActivity.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), "Remplazar por tu codigo", Toast.LENGTH_LONG)
                .show();
    }
}