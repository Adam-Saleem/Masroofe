package com.example.masroofe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;

public class MainActivity extends AppCompatActivity {

    BottomAppBar appBar;
    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        getSupportActionBar().hide();

        appBar = findViewById(R.id.bottomAppBar);


        appBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.imgHome:
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);

                        break;
                    case R.id.imgCal:
                        Toast.makeText(MainActivity.this, "true", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.imgMenu:
                        Intent intent2 = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });
    }
}




