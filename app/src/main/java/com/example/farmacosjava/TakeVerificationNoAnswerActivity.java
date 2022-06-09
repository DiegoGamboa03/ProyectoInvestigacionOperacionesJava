package com.example.farmacosjava;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farmacosjava.registerActivities.nameRegisterActivity;

public class TakeVerificationNoAnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_verification_no_answer);
    }

    public void clickNextButton(View view){
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.RadioGroup);
        Intent intent = new Intent(this, ReplyActivity.class);

        int radioButtonID = radioGroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);

        String selectedText = (String) radioButton.getText();
        System.out.println("RadioButtonID" + radioButtonID);
        if(radioButtonID == 0){
            System.out.println("Se metio en 1");
            intent.putExtra("Text", "Texto");
        }else if(radioButtonID == 1){
            System.out.println("Se metio en 2");
            intent.putExtra("Text", "Texto2");
        }else if(radioButtonID == 2){
            System.out.println("Se metio en 3");
            intent.putExtra("Text", "Texto3");
        }

        startActivity(intent);

        Toast.makeText(getApplicationContext(), "Remplazar por tu codigo", Toast.LENGTH_LONG)
                .show();
    }
}