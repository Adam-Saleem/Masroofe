package com.example.masroofe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button loginBtn, signUpBtn;
    private EditText email, password;


    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        getSupportActionBar().hide();
        setupReference();

        prefs = getSharedPreferences("userInformation",0);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String userNameLogin = email.getText().toString();
                String passwordLogin = password.getText().toString();
                checkLogin(userNameLogin, passwordLogin);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent signup = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(signup);
            }
        });


    }

    //--------------Methods---------------------------------------------------------
    //References
    private void setupReference()
    {
        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);
        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signUpBtnInLoginActivity);
    }

    //check login method
    private void checkLogin(String username, String password)
    {

        if(username.length() > 1 && password.length() > 1)
        {
            //get data from prefs register
            String regUsername = prefs.getString("username", "");
            String regPassword = prefs.getString("password", "");

            if(username.trim().equals(regUsername.trim()) && password.trim().equals(regPassword.trim()))
            {
                Intent login = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(login);
                Toast.makeText(LoginActivity.this, "تم الدخول بنجاح", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(LoginActivity.this, "البيانات غير صحيحة! حاول مرة أخرى", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(LoginActivity.this, "أملئ جميع الحقول، وحاول مرة أخرى", Toast.LENGTH_SHORT).show();
        }

    }
}