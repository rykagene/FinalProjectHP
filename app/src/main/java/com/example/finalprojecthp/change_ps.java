package com.example.finalprojecthp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;

public class change_ps extends AppCompatActivity {
    DAOuser dao = new DAOuser();
    Button btnSave;
    EditText etOPS, etNPS, etCPS;

    FirebaseAuth firebaseAuth;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_ps);

        etOPS = findViewById(R.id.etOPS);
       btnSave = findViewById(R.id.btnSave);
       etNPS = findViewById(R.id.etNPS);
       etCPS = findViewById(R.id.etCPS);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

       btnSave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String oldPassword = etOPS.getText().toString().trim();
               String newPassword = etNPS.getText().toString().trim();
               String confirmPassword = etCPS.getText().toString().trim();

               if (!newPassword.equals(confirmPassword)) {
                   Toast.makeText(change_ps.this, "New password is the same as the current password", Toast.LENGTH_SHORT).show();
                   return;
               }

               HashMap<String, Object> hashMap = new HashMap<>();
               hashMap.put("pass", newPassword);
               User details = new User(newPassword);
               dao.update("-NVmiBUCT7-xLIolBpkT",hashMap).addOnCompleteListener(suc-> {

                   AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), oldPassword);
                   final Task<Void> reaunthentication_failure = user.reauthenticate(credential)
                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if (task.isSuccessful()) {
                                       // Proceed with password update
                                       updatePassword(newPassword);
                                   } else {
                                       // Display an error message or show a Toast indicating reauthentication failure
                                       Toast.makeText(change_ps.this, "Reaunthentication failure", Toast.LENGTH_SHORT).show();
                                   }
                               }
                           });
               }).addOnFailureListener(er-> {
                   Toast.makeText(change_ps.this, "Reaunthentication failure", Toast.LENGTH_SHORT).show();
               });

               AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), oldPassword);
               final Task<Void> reaunthentication_failure = user.reauthenticate(credential)
                       .addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                               if (task.isSuccessful()) {
                                   // Proceed with password update
                                   updatePassword(newPassword);
                               } else {
                                   // Display an error message or show a Toast indicating reauthentication failure
                                   Toast.makeText(change_ps.this, "Reaunthentication failure", Toast.LENGTH_SHORT).show();
                               }
                           }
                       });

           }
       });


    }
    private void updatePassword(String newPassword) {
        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(change_ps.this, "Change PS Success", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}