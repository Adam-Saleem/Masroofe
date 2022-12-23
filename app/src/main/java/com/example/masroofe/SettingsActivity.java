package com.example.masroofe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SettingsActivity extends AppCompatActivity {
    private ImageView imgHome, imgCal, imgGuide;
    private Button updateUserInformationBtn, updateUserPasswordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportActionBar().hide();
        setupReference();
        setUp();
    }

    private void setUp() {

        updateUserInformationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent UpdateUserInformation = new Intent(SettingsActivity.this, UpdateUserInformation.class);
                startActivity(UpdateUserInformation);
            }
        });

        updateUserPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent UpdateUserPassword = new Intent(SettingsActivity.this, UpdateUserPassword.class);
                startActivity(UpdateUserPassword);
            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        imgCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, MonthsActivity.class);
                startActivity(intent);
            }
        });

        imgGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
    }

    private void setupReference() {
        updateUserInformationBtn = findViewById(R.id.UpdateUserInformation);
        updateUserPasswordBtn = findViewById(R.id.UpdateUserPassword);
        imgHome = findViewById(R.id.imgHome);
        imgCal = findViewById(R.id.imgCal);
        imgGuide = findViewById(R.id.imgGuide);

    }
}