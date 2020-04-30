package se.gritacademy.openchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import se.gritacademy.openchat.controller.Auth;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    private static final String ERROR_EMAIL = "Not A Valid Email Address";
    private static final String ERROR_PASSWORD = "Required Field - Min 8 Characters";

    private EditText mEmail, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.email_login);
        mPassword = findViewById(R.id.password_login);
    }

    // If validation is true, send data to Authcontroller for verification and login
    public void signExistingUser(View view) {
        Auth auth = new Auth();
        String newEmail = mEmail.getText().toString();
        String newPassword = mPassword.getText().toString();

        if (!validate()){
            Log.d(TAG, "Error with validation" +getClass());
        } else {
            auth.authUser(this,newEmail, newPassword);
        }

    }

    public boolean validate() {
        boolean valid = true;


        String newEmail = mEmail.getText().toString();
        String newPassword = mPassword.getText().toString();

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
