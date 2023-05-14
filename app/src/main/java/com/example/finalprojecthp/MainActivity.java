package com.example.finalprojecthp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {
    EditText email_signIn, password_signIn;
    Button btn_signIn, btn_signUp;
    Firebase mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email_signIn = findViewById(R.id.email_signIn);
        password_signIn = findViewById(R.id.password_signIn);
        btn_signIn = findViewById(R.id.btn_signIn);
        btn_signUp = findViewById(R.id.btn_signUp);

        btn_signIn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), main_gryffindor.class);
        startActivity(intent);
        });
    }
}