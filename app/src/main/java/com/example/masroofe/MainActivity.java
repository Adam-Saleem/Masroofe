package com.example.masroofe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView imgCal, imgGuide, imgMenu;
    private TextView fixedIncome;
    private SharedPreferences userPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        getSupportActionBar().hide();
        setUpSharedPrefs();
        setUp();
    }

    private void setUp() {
        String fixed_income = userPrefs.getString("fixedIncome", "");
        if (fixed_income.length() > 0) {
            fixedIncome.setText(fixed_income);
        }

        imgCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MonthsActivity.class);
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
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });


    }
    private void setUpSharedPrefs() {
        userPrefs = getSharedPreferences("userInformation", 0);
        imgCal = findViewById(R.id.imgCal);
        imgMenu = findViewById(R.id.imgMenu);
        imgGuide = findViewById(R.id.imgGuide);
        fixedIncome = findViewById(R.id.fixedIncome);
    }
}




