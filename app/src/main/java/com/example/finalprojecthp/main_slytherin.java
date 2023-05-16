package com.example.finalprojecthp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class main_slytherin extends AppCompatActivity {

    private Dialog logoutConfirmationDialog;

    ImageButton BTNcharacters, BTNspell, BTNbooks, BTNmovies, BTNpotions, BTNwands, BTNlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreen.makeFullScreen(this);
        setContentView(R.layout.activity_main_slytherin);
        initialize();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initialize() {
        BTNcharacters = findViewById(R.id.BTNcharacters);
        BTNspell = findViewById(R.id.BTNspell);
        BTNbooks = findViewById(R.id.BTNbooks);
        BTNmovies = findViewById(R.id.BTNmovies);
        BTNpotions = findViewById(R.id.BTNpotions);
        BTNwands = findViewById(R.id.BTNwands);

        BTNlogout = findViewById(R.id.BTNlogout);

        BTNlogout.setOnClickListener(v -> {
            logout();
        });


        BTNcharacters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), main_spells.class);
                startActivity(intent);
            }
        });

        BTNspell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), main_spells.class);
                startActivity(intent);
            }
        });

        BTNbooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), main_books.class);
                startActivity(intent);
            }
        });

        BTNmovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), main_movies.class);
                startActivity(intent);
            }
        });

        BTNpotions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), main_potions.class);
                startActivity(intent);
            }
        });

        BTNwands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), main_wands.class);
                startActivity(intent);
            }
        });
    }

//    private void logout() {
//        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
//        builder1.setMessage("Are you sure you want to logout?");
//        builder1.setCancelable(true);
//
//        builder1.setPositiveButton(
//                "Yes",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());
//                        builder1.setMessage("Write your message here.");
//                        builder1.setCancelable(true);
//
//                        builder1.setPositiveButton(
//                                "Yes",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        FirebaseAuth.getInstance().signOut();
//                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                                    }
//                                });
//
//                        builder1.setNegativeButton(
//                                "No",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        dialog.cancel();
//                                    }
//                                });
//
//                        AlertDialog alert11 = builder1.create();
//                        alert11.show();
//                    }
//                });
//
//        builder1.setNegativeButton(
//                "No",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//
//        AlertDialog alert11 = builder1.create();
//        alert11.show();
//    }

    private  void  logout() {
        CustomDialog CustomDialog = new CustomDialog(this);
        CustomDialog.show();
    }


}