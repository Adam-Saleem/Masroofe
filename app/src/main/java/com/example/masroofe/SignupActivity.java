package com.example.masroofe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity
{

    private Button finishBtn, nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        setupReference();
        getSupportActionBar().hide();

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent finishInent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(finishInent);

                Toast.makeText(SignupActivity.this, "تم إنشاء الحساب", Toast.LENGTH_SHORT).show();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent incomeInent = new Intent(SignupActivity.this, FixedIncomeInfoActivity.class);
                startActivity(incomeInent);
            }
        });
    }

    //References
    private void setupReference()
    {
        finishBtn = findViewById(R.id.finishBtn);
        nextBtn = findViewById(R.id.nextBtn);
    }


}