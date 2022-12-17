package com.example.masroofe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    private Button updateUserInformationBtn, updateUserPasswordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportActionBar().hide();
        setUpViews();

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
    }

    private void setUpViews() {
        updateUserInformationBtn = findViewById(R.id.UpdateUserInformation);
        updateUserPasswordBtn = findViewById(R.id.UpdateUserPassword);
    }
}