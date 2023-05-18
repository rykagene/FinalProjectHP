package com.example.finalprojecthp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity {

    Button btnUpdate, btnBack;
    TextView tvEmail;

    EditText et_Email;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnUpdate = findViewById(R.id.btnUpdate);
        et_Email = findViewById(R.id.etEmail);
        tvEmail = findViewById(R.id.tvEmail);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        if (user != null) {
            String email = user.getEmail();
            tvEmail.setText(email);
            et_Email.setText(email);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }
}