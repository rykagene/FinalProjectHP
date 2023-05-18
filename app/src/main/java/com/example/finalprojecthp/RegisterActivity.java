package com.example.finalprojecthp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    final int REQUEST_CODE = 1;
    final int REQUEST_CODE_CAM = 2;
    String house = "";
    Bitmap bmp;
    ShapeableImageView LoginLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreen.makeFullScreen(this);
        setContentView(R.layout.activity_register);
        initialize();
    }

    private void initialize() {
        EditText username_signUp = findViewById(R.id.RegisterUsername);
        EditText name_signUp = findViewById(R.id.RegisterName);
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

        FirebaseAuth mAuth;

        mAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(v -> {
            String user = email_signUp.getText().toString().trim();
            String pass = pass_signUp.getText().toString().trim();

            String username = username_signUp.getText().toString();
            String name = username_signUp.getText().toString();

            if(user.isEmpty()) {
                email_signUp.setError("Sorry, you must enter an email.");
            }
            if(pass.isEmpty()) {
                pass_signUp.setError("Sorry, you must enter a password.");
            }
            if(name.isEmpty()) {
                name_signUp.setError("Sorry, you must enter a name.");
            }
            if(username.isEmpty()) {
                username_signUp.setError("Sorry, you must enter a username.");
            }

            else if(house.isEmpty()) {
                Toast.makeText(this, "Please pick a house", Toast.LENGTH_SHORT).show();;
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

        RegisterHouseRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedId = group.getCheckedRadioButtonId();

                switch (checkedId) {
                    case R.id.RegisterHouseRB1:
                        house = house1_signUp.getText().toString();
                        break;

                    case R.id.RegisterHouseRB2:
                        house = house2_signUp.getText().toString();
                        break;

                    case R.id.RegisterHouseRB3:
                        house = house3_signUp.getText().toString();
                        break;

                    case R.id.RegisterHouseRB4:
                        house = house4_signUp.getText().toString();
                        break;

                }
            }
        });

        LoginLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, REQUEST_CODE_CAM);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAM && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            bmp = photo;
            LoginLogo.setImageBitmap(photo);
        }
    }
}