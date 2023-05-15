package com.example.finalprojecthp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreen.makeFullScreen(this);
        setContentView(R.layout.activity_register);
        initialize();
    }

    private void initialize() {
        EditText email_signUp = findViewById(R.id.email_signUp);
        EditText pass_signUp = findViewById(R.id.pass_signUp);
        Button registerBtn = findViewById(R.id.registerBtn);

        FirebaseAuth mAuth;

        mAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(v -> {
            String user = email_signUp.getText().toString().trim();
            String pass = pass_signUp.getText().toString().trim();
            if(user.isEmpty()) {
                email_signUp.setError("Sorry, you must enter email.");
            }
            if((pass.isEmpty()) ) {
                pass_signUp.setError("Sorry, you must enter pass.");
            }
            else {
                mAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }
}