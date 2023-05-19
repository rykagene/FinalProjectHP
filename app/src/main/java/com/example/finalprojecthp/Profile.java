package com.example.finalprojecthp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        userRef = FirebaseDatabase.getInstance().getReference("User").child(user.getUid());

        if (user != null) {
            String email = user.getEmail();
            tvEmail.setText(email);

        }

//        if (user != null) {
//            String email = user.getEmail();
//            tvEmail.setText(email);
//            et_Email.setText(email);
//
//            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.exists()) {
//                        User userDetails = dataSnapshot.getValue(User.class);
//                        if (userDetails != null) {
//                            fname = userDetails.getFname();
//                            lname = userDetails.getLname();
//                            tvName.setText(fname + " " + lname);
//                            et_Fname.setText(fname);
//                            et_Lname.setText(lname);
//                        }
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    // Handle the error
//                }
//            });
//        }

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