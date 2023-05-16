package com.example.finalprojecthp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    Button btnUpdate;
    TextView tvEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnUpdate = findViewById(R.id.btnUpdate);
        tvEmail = findViewById(R.id.tvEmail);
    }
}