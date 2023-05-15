package com.example.finalprojecthp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class main_gryffindor extends AppCompatActivity {

    ImageButton BTNcharacters, BTNspell, BTNbooks, BTNmovies, BTNpotions, BTNwands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreen.makeFullScreen(this);
        setContentView(R.layout.activity_main_gryffindor);
        initialize();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initialize() {
        BTNcharacters = findViewById(R.id.BTNcharacters);
        BTNspell = findViewById(R.id.BTNspell);
        BTNbooks = findViewById(R.id.BTNbooks);
        BTNmovies = findViewById(R.id.BTNmovies);
        BTNpotions = findViewById(R.id.BTNpotions);
        BTNwands = findViewById(R.id.BTNwands);

        BTNcharacters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), main_characters.class);
                startActivity(intent);
            }
        });

        BTNspell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), main_hufflepuff.class);
                startActivity(intent);
            }
        });

        BTNbooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        BTNmovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), main_characters.class);
                startActivity(intent);
            }
        });

        BTNpotions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), main_slytherin.class);
                startActivity(intent);
            }
        });

        BTNwands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), main_ravenclaw.class);
                startActivity(intent);
            }
        });
    }
}