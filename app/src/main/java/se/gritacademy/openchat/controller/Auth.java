package se.gritacademy.openchat.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import se.gritacademy.openchat.ChatActivity;

public class Auth {

    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;

    public void authUser(final Context context, final String mEmail, String mPassword) {
        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG,"Sign-in: successful" +getClass());
                    Intent intent = new Intent(context, ChatActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                } else {
                    Log.d(TAG,"Sign-in: failed " +task.getException());
                    Toast.makeText(context, "Authentication Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
