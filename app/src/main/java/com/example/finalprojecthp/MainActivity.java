package com.example.finalprojecthp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText email_signIn, password_signIn;
    Button btn_signIn, btn_signUp;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FullScreen.makeFullScreen(this);

        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        email_signIn = findViewById(R.id.email_signIn);
        password_signIn = findViewById(R.id.password_signIn);
        btn_signIn = findViewById(R.id.btn_signIn);
        btn_signUp = findViewById(R.id.btn_signUp);

        btn_signIn.setOnClickListener(v -> login());
        btn_signUp.setOnClickListener(v -> register());
    }

    private void register() {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }

    private void login() {
        String user = email_signIn.getText().toString().trim();
        String pass = password_signIn.getText().toString().trim();
        if(user.isEmpty()) {
            email_signIn.setError("Sorry, you must enter email.");
        }
        if((pass.isEmpty()) ) {
            password_signIn.setError("Sorry, you must enter pass.");
        }
        else {
            mAuth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), main_gryffindor.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
