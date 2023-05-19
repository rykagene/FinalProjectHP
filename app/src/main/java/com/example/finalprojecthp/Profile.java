package com.example.finalprojecthp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

    Button btnUpdate, btnBackP, btnReset;
    TextView tvEmail, tv_changePS, tvName, tvHouse;
    EditText et_Email, et_Fname, et_Lname;
    LinearLayout ll_bg;

    ImageView logo_img, name_img;

    String fname, lname, house;

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
        tvHouse = findViewById(R.id.tvHouse);
        ll_bg = findViewById(R.id.ll_bg);
        logo_img = findViewById(R.id.logo_img);
        name_img = findViewById(R.id.name_img);
        btnReset = findViewById(R.id.btnReset);


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
                        String house = userSnapshot.child("house").getValue(String.class);

                        userRef = userSnapshot.getRef(); // Assign the DatabaseReference for future use

                        tvName.setText(fname+" "+lname);

                        //set the textbox value
                        et_Fname.setText(fname);
                        et_Lname.setText(lname);


                        // Change the background drawable based on the house
                        if (house.equals("Ravenclaw")) {
                            ll_bg.setBackgroundResource(R.drawable.raven_bg);
                            logo_img.setImageResource(R.drawable.raven_circle);
                            name_img.setImageResource(R.drawable.raven_name);
                        }
                        else if (house.equals("Hufflepuff")) {
                            ll_bg.setBackgroundResource(R.drawable.huffle_bg);
                            logo_img.setImageResource(R.drawable.huffle_circle);
                            name_img.setImageResource(R.drawable.huffle_name);
                        }
                        else if (house.equals("Slytherin")) {
                            ll_bg.setBackgroundResource(R.drawable.slyth_bg);
                            logo_img.setImageResource(R.drawable.slyth_circle);
                            name_img.setImageResource(R.drawable.slyth_name);
                        }
                        else if (house.equals("Gryffindor")) {
                            ll_bg.setBackgroundResource(R.drawable.gryff_profile);
                            logo_img.setImageResource(R.drawable.gryff_circle);
                            name_img.setImageResource(R.drawable.gryff_name);
                        }

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
                String newFname = et_Fname.getText().toString().trim(); // Get the new first name
                String newLname = et_Lname.getText().toString().trim(); // Get the new last name

                if (newFname.isEmpty() || newLname.isEmpty()) {
                    // Display a toast indicating that the first name or last name is empty
                    Toast.makeText(Profile.this, "Please enter both first name and last name", Toast.LENGTH_SHORT).show();
                } else {
                    // Retrieve the current first name and last name from Firebase
                    String currentFname = tvName.getText().toString().split(" ")[0];
                    String currentLname = tvName.getText().toString().split(" ")[1];

                    // Compare the new values with the current values
                    if (newFname.equals(currentFname) && newLname.equals(currentLname)) {
                        // Display a toast indicating that the profile is already up to date
                        Toast.makeText(Profile.this, "Profile is already up to date", Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
                        builder.setTitle("Confirm Name Change");
                        builder.setMessage("Are you sure you want to update your name?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // User clicked Yes, update the first name and last name in Firebase
                                userRef.child("fname").setValue(newFname);
                                userRef.child("lname").setValue(newLname);
                                Toast.makeText(Profile.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                            }
                        });

                        builder.setNegativeButton("No", null); // If user clicks No, do nothing

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
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