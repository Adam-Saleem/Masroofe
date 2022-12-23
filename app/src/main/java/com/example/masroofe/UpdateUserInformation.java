package com.example.masroofe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdateUserInformation extends AppCompatActivity {
    private Button updateUserInformation;
    private EditText fullName, dateBirth, username;
    private int day, month, year;
    private ImageView imgHome, imgCal, imgGuide, imgMenu;

    private FloatingActionButton btn;

    private boolean FLAG = true;
    private SharedPreferences prefs, userPrefs;
    private SharedPreferences.Editor editor, userEditor;
    public static final String FULLNAME = "fullName";
    public static final String DATEBIRTH = "dateBirth";
    public static final String USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_user_information_activity);
        getSupportActionBar().hide();
        setupReference();
        setUpSharedPrefs();
        setUp();
        checkDate();
    }

    private void setUp() {
        String full_name = userPrefs.getString("fullName", "");
        String user_name = userPrefs.getString("username", "");
        String date_birth = userPrefs.getString("dateBirth", "");

        fullName.setText(full_name);
        dateBirth.setText(date_birth);
        username.setText(user_name);

        Calendar calendarBirthDay = Calendar.getInstance();

        dateBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendarBirthDay.get(Calendar.YEAR);
                month = calendarBirthDay.get(Calendar.MONTH);
                day = calendarBirthDay.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(UpdateUserInformation.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        dateBirth.setText(SimpleDateFormat.getDateInstance().format(calendarBirthDay.getTime()));
                    }
                }, year, month, day);

                datePicker.show();
            }
        });

        updateUserInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((fullName.length() > 0) && (dateBirth.length() > 0) && (username.length() > 0)) {

                    userEditor = userPrefs.edit();
                    userEditor.putString("fullName", fullName.getText().toString());
                    userEditor.putString("dateBirth", dateBirth.getText().toString());
                    userEditor.putString("username", username.getText().toString());
                    userEditor.apply();

                    Toast.makeText(UpdateUserInformation.this, "تم تحديث المعلومات!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateUserInformation.this, "أكمل المعلومات أولاً!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateUserInformation.this, MainActivity.class);
                startActivity(intent);
            }
        });

        imgCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateUserInformation.this, MonthsActivity.class);
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
                Intent intent = new Intent(UpdateUserInformation.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateUserInformation.this, Add_Activity.class);
                startActivity(intent);
            }
        });
    }

    //References
    private void setupReference() {
        updateUserInformation = findViewById(R.id.updateUserInformation);
        fullName = findViewById(R.id.fullName);
        dateBirth = findViewById(R.id.birthDay);
        username = findViewById(R.id.username);
        imgHome = findViewById(R.id.imgHome);
        imgCal = findViewById(R.id.imgCal);
        imgGuide = findViewById(R.id.imgGuide);
        imgMenu = findViewById(R.id.imgMenu);
        btn = findViewById(R.id.floatingActionButton);

    }

    private void setUpSharedPrefs() {
        userPrefs = getSharedPreferences("userInformation", 0);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (!fullName.getText().toString().equals("") || !dateBirth.getText().toString().equals("") || !username.getText().toString().equals("")) {
            String full_name = fullName.getText().toString();
            String date_birth = dateBirth.getText().toString();
            String user_name = username.getText().toString();

            editor.putString("fullName", full_name);
            editor.putString("dateBirth", date_birth);
            editor.putString("username", user_name);
            editor.putBoolean("flag", FLAG);
            editor.commit();
        }
    }

    private void checkDate() {
        boolean f = prefs.getBoolean("flag", false);
        if (f) {
            String full_name = prefs.getString(FULLNAME, "");
            String date_birth = prefs.getString(DATEBIRTH, "");
            String user_name = prefs.getString(USERNAME, "");
            fullName.setText(full_name);
            dateBirth.setText(date_birth);
            username.setText(user_name);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editor.clear();
        editor.commit();
    }
}