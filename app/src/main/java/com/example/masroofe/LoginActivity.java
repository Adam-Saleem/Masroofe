package com.example.masroofe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button loginBtn, signUpBtn;
    private EditText email, password;

    private boolean FLAG = true;
    private SharedPreferences prefs, userPrefs;
    private SharedPreferences.Editor editor;
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        getSupportActionBar().hide();
        //References
        setupReference();
        loginSetup();
        signupSetup();
        checkDate();

    }

    //--------------Methods---------------------------------------------------------
    private void loginSetup() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userNameLogin = email.getText().toString();
                String passwordLogin = password.getText().toString();
                checkLogin(userNameLogin, passwordLogin);
            }
        });
    }

    private void signupSetup() {
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(signup);
            }
        });
    }


    //References
    private void setupReference() {
        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);
        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signUpBtnInLoginActivity);
        userPrefs = getSharedPreferences("userInformation", 0);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    //check login method
    private void checkLogin(String username, String password) {

        if (username.length() > 0 && password.length() > 0) {
            //get data from prefs register
            String regUsername = userPrefs.getString("username", "");
            String regPassword = userPrefs.getString("password", "");

            if (username.trim().equals(regUsername.trim()) && password.trim().equals(regPassword.trim())) {
                Intent login = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(login);
                Toast.makeText(LoginActivity.this, "تم الدخول بنجاح", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "البيانات غير صحيحة! حاول مرة أخرى", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LoginActivity.this, "أملئ جميع الحقول، وحاول مرة أخرى", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!email.getText().toString().equals("") || !password.getText().toString().equals("")) {
            String Email = email.getText().toString();
            String Password = password.getText().toString();
            editor.putString("username", Email);
            editor.putString("password", Password);
            editor.putBoolean("flag", FLAG);
            editor.commit();
        }
    }

    private void checkDate() {
        boolean f = prefs.getBoolean("flag", false);
        if (f) {
            String Email = prefs.getString(USERNAME, "");
            String Password = prefs.getString(PASSWORD, "");
            email.setText(Email);
            password.setText(Password);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editor.clear();
        editor.commit();
    }
}