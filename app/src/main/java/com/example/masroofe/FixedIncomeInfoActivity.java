package com.example.masroofe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FixedIncomeInfoActivity extends AppCompatActivity
{

    private Button addIncomeBtn;
    private EditText fixedInc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fixed_income_info_activity);

        //References
        setupReference();
        getSupportActionBar().hide();

        addIncomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String fixed = fixedInc.getText().toString().trim();
                addFixedIncome(fixed);
            }
        });
    }

    //--------------Methods---------------------------------------------------------
    //References
    private void setupReference()
    {
        addIncomeBtn = findViewById(R.id.addIncomeBtn);
        fixedInc = findViewById(R.id.fixedValue);
    }

    private void addFixedIncome(String fixedMoney)
    {
        if(fixedMoney.length() > 0)
        {
            Intent inc = new Intent(FixedIncomeInfoActivity.this, LoginActivity.class);
            startActivity(inc);
            Toast.makeText(FixedIncomeInfoActivity.this, "تم إضافة الدخل بنجاح", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(FixedIncomeInfoActivity.this, "الحقل فارغ! حاول مرة أخرى", Toast.LENGTH_SHORT).show();
        }
    }


}