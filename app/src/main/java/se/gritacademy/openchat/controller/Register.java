package se.gritacademy.openchat.controller;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import se.gritacademy.openchat.models.UserModel;


public class Register {

    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    private DatabaseReference mDB;


    public void register(final Context context, final String username, String email, String password) {
        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser fb = mAuth.getCurrentUser();
                    assert fb != null;


                    //Creates a new account with username, email and date of creation
                    mDB = FirebaseDatabase.getInstance().getReference().child("Users");
                    mDB.child(Objects.requireNonNull(mAuth.getUid())).setValue(
                            new UserModel(username, mAuth.getUid(),fb.getEmail(), System.currentTimeMillis()))
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "Userdata has been set:success");
                                    }
                                }
                            });
                } else {
                    Log.d(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(context.getApplicationContext(), "Email already in use", Toast.LENGTH_SHORT).show();
                }
            }
                });

    }
}
