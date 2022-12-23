package com.example.masroofe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Add_Activity extends AppCompatActivity {

    ImageView btnDef, addExp, addRev;
    ImageView imgHome, imgCal, imgGuide, imgMenu;
    FloatingActionButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setUp();
        barSetUp();
    }



    private void setUp() {


        btnDef = findViewById(R.id.btnDefine);
        btnDef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Activity.this, Define_Activity.class);
                startActivity(intent);

            }
        });

        addExp = findViewById(R.id.btnExp);
        addExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        addRev = findViewById(R.id.btnRev);
        addRev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void barSetUp() {
        imgCal = findViewById(R.id.imgCal);
        imgCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Activity.this, MonthsActivity.class);
                startActivity(intent);
            }
        });

        imgGuide = findViewById(R.id.imgGuide);
        imgGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });


        imgMenu = findViewById(R.id.imgMenu);
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Activity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        imgHome = findViewById(R.id.imgHome);
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}