package com.example.finalprojecthp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    String house;
    Bitmap gryffBmp, huffleBmp, ravenBmp, slythBmp;
    ShapeableImageView LoginLogo;
    FirebaseAuth mAuth;

    DAOuser dao = new DAOuser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreen.makeFullScreen(this);
        setContentView(R.layout.activity_register);
        initialize();

        mAuth = FirebaseAuth.getInstance();
        gryffBmp = BitmapFactory.decodeResource(getResources(),R.drawable.gryff_circle);
        huffleBmp = BitmapFactory.decodeResource(getResources(),R.drawable.huffle_circle);
        ravenBmp = BitmapFactory.decodeResource(getResources(),R.drawable.raven_circle);
        slythBmp = BitmapFactory.decodeResource(getResources(),R.drawable.slyth_circle);
    }

    private void initialize() {

        EditText Fname_signUp = findViewById(R.id.RegisterFName);
        EditText Lname_signUp = findViewById(R.id.RegisterLName);
        EditText email_signUp = findViewById(R.id.RegisterEmail);
        EditText pass_signUp = findViewById(R.id.RegisterPassword);

        RadioGroup RegisterHouseRG = findViewById(R.id.RegisterHouseRG);
        RadioButton house1_signUp = findViewById(R.id.RegisterHouseRB1);
        RadioButton house2_signUp = findViewById(R.id.RegisterHouseRB2);
        RadioButton house3_signUp = findViewById(R.id.RegisterHouseRB3);
        RadioButton house4_signUp = findViewById(R.id.RegisterHouseRB4);





        LoginLogo = findViewById(R.id.LoginLogo);

        Button registerBtn = findViewById(R.id.RegisterBTN);
        TextView loginBtn = findViewById(R.id.RegisterLogBTN);
        TextView reg_back = findViewById(R.id.reg_back);



        registerBtn.setOnClickListener(v -> {

            String email = email_signUp.getText().toString().trim();
            String pass = pass_signUp.getText().toString().trim();
            String fname = Fname_signUp.getText().toString().trim();
            String lname = Lname_signUp.getText().toString().trim();


            if(email.isEmpty()) {
                email_signUp.setError("Sorry, you must enter an email.");
            }
            if(pass.isEmpty()) {
                pass_signUp.setError("Sorry, you must enter a password.");
            }
            if(fname.isEmpty()) {
                Fname_signUp.setError("Sorry, you must enter a name.");
            }
            if(lname.isEmpty()) {
                Lname_signUp.setError("Sorry, you must enter a name.");
            }
            else if(house.isEmpty()) {
                Toast.makeText(this, "Please pick a house", Toast.LENGTH_SHORT).show();;
           }

            else {

                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        // Store additional user information in the database

                        User userDetails = new User(email,pass, fname,lname, house);
                        dao.add(userDetails).addOnCompleteListener(suc -> {
                            Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                        }).addOnFailureListener(er -> {
                            Toast.makeText(RegisterActivity.this, er.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                    } else {
                        Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });

        RegisterHouseRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedId = group.getCheckedRadioButtonId();

                switch (checkedId) {
                    case R.id.RegisterHouseRB1:
                        house = house1_signUp.getText().toString();
                        LoginLogo.setImageBitmap(gryffBmp);
                        break;

                    case R.id.RegisterHouseRB2:
                        house = house2_signUp.getText().toString();
                        LoginLogo.setImageBitmap(slythBmp);
                        break;

                    case R.id.RegisterHouseRB3:
                        house = house3_signUp.getText().toString();
                        LoginLogo.setImageBitmap(ravenBmp);
                        break;

                    case R.id.RegisterHouseRB4:
                        house = house4_signUp.getText().toString();
                        LoginLogo.setImageBitmap(huffleBmp);
                        break;

                }

            }
        });

        reg_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(log);
            }
        });

    }

}