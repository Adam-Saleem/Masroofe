package com.example.masroofe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FixedIncomeInfoActivity extends AppCompatActivity {

    private ImageView imgHome, imgCal, imgGuide, imgMenu;
    private Button addIncomeBtn;
    private TextView nameOfUser;
    private EditText amountOfIncome;
    private SharedPreferences userPrefs;
    private SharedPreferences.Editor userEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fixed_income_info_activity);
        getSupportActionBar().hide();
        setupReference();
        setUp();
    }

    private void setUp() {
        String full_name = userPrefs.getString("fullName", "");
        nameOfUser.setText("مرحبًا" + " " + full_name);
        String fixedIncome = userPrefs.getString("fixedIncome", "");
        if (fixedIncome.length() > 0) {
            amountOfIncome.setText(fixedIncome);
            addIncomeBtn.setText("تعديل");
        }

        addIncomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "";
                if (fixedIncome.length() > 0) {
                    message = "تم تعديل الدخل بنجاح";
                } else {
                    message = "تم إضافة الدخل بنجاح";
                }
                Toast.makeText(FixedIncomeInfoActivity.this, message, Toast.LENGTH_SHORT).show();
                userEditor = userPrefs.edit();
                userEditor.putString("fixedIncome", amountOfIncome.getText().toString());
                userEditor.apply();
                Intent main = new Intent(FixedIncomeInfoActivity.this, MainActivity.class);
                startActivity(main);

            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FixedIncomeInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        imgCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FixedIncomeInfoActivity.this, MonthsActivity.class);
                startActivity(intent);
            }
        });

        imgGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FixedIncomeInfoActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    //References
    private void setupReference() {
        userPrefs = getSharedPreferences("userInformation", 0);
        nameOfUser = findViewById(R.id.nameOfUser);
        addIncomeBtn = findViewById(R.id.addIncomeBtn);
        amountOfIncome = findViewById(R.id.amountOfIncome);
        imgHome = findViewById(R.id.imgHome);
        imgCal = findViewById(R.id.imgCal);
        imgGuide = findViewById(R.id.imgGuide);
        imgMenu = findViewById(R.id.imgMenu);
    }
}