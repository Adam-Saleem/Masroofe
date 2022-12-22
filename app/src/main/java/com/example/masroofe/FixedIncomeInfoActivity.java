package com.example.masroofe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FixedIncomeInfoActivity extends AppCompatActivity
{

    private Button addIncomeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fixed_income_info_activity);
        setupReference();
        getSupportActionBar().hide();

        addIncomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FixedIncomeInfoActivity.this, "تم إضافة الدخل بنجاح", Toast.LENGTH_SHORT).show();

            }
        });
    }

    //References
    private void setupReference()
    {
        addIncomeBtn = findViewById(R.id.addIncomeBtn);
    }
}