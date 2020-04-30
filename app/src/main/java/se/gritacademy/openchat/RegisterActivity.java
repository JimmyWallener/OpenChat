package se.gritacademy.openchat;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import se.gritacademy.openchat.controller.Register;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    private static final String ERROR_EMAIL = "Not A Valid Email Address";
    private static final String REQUIRED = "Required Field";
    private static final String ERROR_PASSWORD = "Required Field - Min 8 Characters";

    private EditText mUsername, mEmail, mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUsername = findViewById(R.id.username);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);

    }

    // If validation is true, create new account with register controller.
    public void registerAccount(View view) {

        Register rc = new Register();

        String newUser = mUsername.getText().toString();
        String newEmail = mEmail.getText().toString();
        String newPassword = mPassword.getText().toString();

        if (!validate()) {
            Log.d(TAG, "Error with validation" +getClass());
        } else {
            rc.register(RegisterActivity.this, newUser, newEmail, newPassword);
            Log.d(TAG,"register complete "+getApplication());

            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }




    }

    public boolean validate() {
        boolean valid = true;

        String newUser = mUsername.getText().toString();
        String newEmail = mEmail.getText().toString();
        String newPassword = mPassword.getText().toString();

        if (TextUtils.isEmpty(newUser)) {
            mUsername.setError(REQUIRED);
            valid = false;
        } else {
            mUsername.setError(null);
        }


        if (TextUtils.isEmpty(newEmail) || !newEmail.contains("@") || !newEmail.contains(".")) {
            mEmail.setError(ERROR_EMAIL);
            valid = false;
        } else {
            mEmail.setError(null);
        }


        if (TextUtils.isEmpty(newPassword) || newPassword.length() < 8) {
            mPassword.setError(ERROR_PASSWORD);
            valid = false;
        } else {
            mPassword.setError(null);
        }

        return valid;
    }
}
