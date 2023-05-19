package com.example.finalprojecthp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    Button btnUpdate, btnBack;
    TextView tvEmail, tv_changePS, tvName;
    EditText et_Email, et_Fname, et_Lname;

    String fname, lname;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        FirebaseApp.initializeApp(this);

        btnUpdate = findViewById(R.id.btnUpdate);
        et_Fname = findViewById(R.id.fname);
        et_Lname = findViewById(R.id.lname);
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tv_changePS = findViewById(R.id.tv_changePS);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        // Retrieve the key dynamically from the database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("User");
        Query query = databaseRef.orderByChild("email").equalTo(user.getEmail());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {

                        // retrieve ng id, fname,lname
                        String key = userSnapshot.getKey();
                        String fname = userSnapshot.child("fname").getValue(String.class);
                        String lname = userSnapshot.child("lname").getValue(String.class);


                        tvName.setText(fname+" "+lname);

                        //set the textbox value
                        et_Fname.setText(fname);
                        et_Lname.setText(lname);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, "Failed to retrieve data.", Toast.LENGTH_SHORT).show();
            }
        });


        if (user != null) {
            String email = user.getEmail();
            tvEmail.setText(email);

        }




        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        tv_changePS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), change_ps.class);
                startActivity(intent);
            }
        });
    }
}