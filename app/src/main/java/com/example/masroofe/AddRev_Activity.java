package com.example.masroofe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddRev_Activity extends AppCompatActivity {

    private ImageView imgHome, imgCal, imgGuide, imgMenu;
    private FloatingActionButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rev);
        barSetUp();
    }

    private void barSetUp() {
        imgCal = findViewById(R.id.imgCal);
        imgCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddRev_Activity.this, MonthsActivity.class);
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
                Intent intent = new Intent(AddRev_Activity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        imgHome = findViewById(R.id.imgHome);
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddRev_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn = findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddRev_Activity.this, Add_Activity.class);
                startActivity(intent);
            }
        });

    }
}

