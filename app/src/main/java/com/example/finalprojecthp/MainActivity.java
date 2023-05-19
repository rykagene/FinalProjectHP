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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
            email_signIn.setError("Email Required");
        }
        if((pass.isEmpty()) ) {
            password_signIn.setError("Password Required");
        }
        else {
            mAuth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Retrieve the user's data from Firebase
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    String userId = firebaseUser.getUid();
                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("User").child(userId);
                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                // Check the user's house
                                String house = snapshot.child("house").getValue(String.class);
                                if (house != null) {
                                    Intent intent;
                                    switch (house) {
                                        case "Gryffindor":
                                            Toast.makeText(MainActivity.this, "Logged in successfully as Gryffindor.", Toast.LENGTH_SHORT).show();
                                            intent = new Intent(getApplicationContext(), main_gryffindor.class);
                                            startActivity(intent);
                                            break;
                                        case "Hufflepuff":
                                            Toast.makeText(MainActivity.this, "Logged in successfully as Hufflepuff.", Toast.LENGTH_SHORT).show();
                                            intent = new Intent(getApplicationContext(), main_hufflepuff.class);
                                            startActivity(intent);
                                            break;
                                        case "Slytherin":
                                            Toast.makeText(MainActivity.this, "Logged in successfully as Slytherin.", Toast.LENGTH_SHORT).show();
                                            intent = new Intent(getApplicationContext(), main_slytherin.class);
                                            startActivity(intent);
                                            break;
                                        case "Ravenclaw":
                                            Toast.makeText(MainActivity.this, "Logged in successfully as Ravenclaw.", Toast.LENGTH_SHORT).show();
                                            intent = new Intent(getApplicationContext(), main_ravenclaw.class);
                                            startActivity(intent);
                                            break;
                                        default:
                                            Toast.makeText(MainActivity.this, "Invalid house.", Toast.LENGTH_SHORT).show();
                                            break;
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, "House data not found.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "User data not found.", Toast.LENGTH_SHORT).show();
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(MainActivity.this, "Failed to retrieve user data.", Toast.LENGTH_SHORT).show();

                        }
                    });

                    Toast.makeText(MainActivity.this, "Logged in successfully.", Toast.LENGTH_SHORT).show();
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
