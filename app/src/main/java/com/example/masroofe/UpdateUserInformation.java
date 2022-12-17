package com.example.masroofe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class UpdateUserInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_user_information_activity);

        getSupportActionBar().hide();
    }
}