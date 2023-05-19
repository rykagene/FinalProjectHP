package com.example.finalprojecthp;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOuser {
    private DatabaseReference databaseReference;
    public DAOuser() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(User.class.getSimpleName());
    }
    public Task<Void> add(User details){
        return databaseReference.push().setValue(details);

    }
}
