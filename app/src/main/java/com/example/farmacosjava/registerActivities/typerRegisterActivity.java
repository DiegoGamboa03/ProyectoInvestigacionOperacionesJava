package com.example.farmacosjava.registerActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.farmacosjava.MainActivity;
import com.example.farmacosjava.R;

public class typerRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typer_register);
    }

    public void clickNextButton(View view){
        Spinner spinner = (Spinner) findViewById(R.id.spinnerUserType);
        String selectedItem = spinner.getSelectedItem().toString();

        Intent intent = new Intent(this, nameRegisterActivity.class);

        intent.putExtra("UserType", selectedItem);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), "Remplazar por tu codigo", Toast.LENGTH_LONG)
                .show();
    }
}