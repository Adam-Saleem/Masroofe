package com.example.masroofe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MonthsActivity extends AppCompatActivity {

    private ImageView imgHome, imgGuide, imgMenu;
    private FloatingActionButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.months_activity);
        getSupportActionBar().hide();
        setupReference();
        setUp();
    }

    private void setUp() {
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonthsActivity.this, MainActivity.class);
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
                Intent intent = new Intent(MonthsActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonthsActivity.this, Add_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void setupReference() {
        imgHome = findViewById(R.id.imgHome);
        imgGuide = findViewById(R.id.imgGuide);
        imgMenu = findViewById(R.id.imgMenu);
        btn = findViewById(R.id.floatingActionButton);

    }


}