package com.example.finalprojecthp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class change_ps extends AppCompatActivity {

    Button btnSave;
    EditText etOPS, etNPS, etCPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_ps);

        etOPS = findViewById(R.id.etOPS);
       btnSave = findViewById(R.id.btnSave);
       etNPS = findViewById(R.id.etNPS);
       etCPS = findViewById(R.id.etCPS);


       btnSave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });






    }
}