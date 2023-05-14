package com.example.finalprojecthp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button Bregister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bregister = findViewById(R.id.Bregister);

        Bregister.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), main_gryffindor.class);
        startActivity(intent);
        });

    }
}