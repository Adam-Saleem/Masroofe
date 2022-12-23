package com.example.masroofe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdateUserInformation extends AppCompatActivity {
    private Button updateUserInformation;
    private EditText fullName, dateBirth;
    private int day, month, year;

    private boolean flag = true;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    public static final String FULLNAME = "full_name";
    public static final String DATEBIRTH = "date_birth";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_user_information_activity);
        getSupportActionBar().hide();
        setupReference();
        setUpSharedPrefs();

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
                if (!fullName.getText().toString().equals("") && !dateBirth.getText().toString().equals("")){
                    Toast.makeText(UpdateUserInformation.this, "تم تحديث المعلومات!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(UpdateUserInformation.this, "أكمل المعلومات أولاً!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //References
    private void setupReference() {
        updateUserInformation = findViewById(R.id.updateUserInformation);
        fullName = findViewById(R.id.fullName);
        dateBirth = findViewById(R.id.birthDay);
    }

    private void setUpSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void checkDate() {
        boolean f = prefs.getBoolean("FLAG", false);
        if (f) {
            String full_name = prefs.getString(FULLNAME, "");
            String date_birth = prefs.getString(DATEBIRTH, "");
            fullName.setText(full_name);
            dateBirth.setText(date_birth);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!fullName.getText().toString().equals("") || !dateBirth.getText().toString().equals("")) {
            String full_name = fullName.getText().toString();
            String date_birth = dateBirth.getText().toString();

            editor.putString(FULLNAME, full_name);
            editor.putString(DATEBIRTH, date_birth);
            editor.putBoolean("FLAG", flag);
            editor.commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkDate();
    }

    @Override
    protected void onStop() {
        super.onStop();
        editor.clear();
        editor.commit();
    }
}