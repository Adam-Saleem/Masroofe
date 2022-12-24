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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SignupActivity extends AppCompatActivity {

    private Button finishBtn, nextBtn;
    private EditText fullName, dateBirth, username, password, repeatPassword;
    private int day, month, year;
    private SharedPreferences prefs, userPrefs;
    private SharedPreferences.Editor editor;

    private boolean FLAG = true;
    public static final String USERNAME = "username";
    public static final String DATEBIRTH = "dateBirth";
    public static final String FULLNAME = "fullName";
    public static final String PASSWORD = "password";
    public static final String REPEATPASSWORD = "repeatPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        //---References-----------------------------
        setupReference();
        getSupportActionBar().hide();

        calendarSetup();
        finishSetup();
        nextSetup();
        checkDate();
    }


    //--------------Methods---------------------------------------------------------
    private void calendarSetup() {
        Calendar calendarBirthDay = Calendar.getInstance();

        dateBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendarBirthDay.get(Calendar.YEAR);
                month = calendarBirthDay.get(Calendar.MONTH);
                day = calendarBirthDay.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(SignupActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        dateBirth.setText(SimpleDateFormat.getDateInstance().format(calendarBirthDay.getTime()));
                    }
                }, year, month, day);

                datePicker.show();
            }
        });
    }

    private void finishSetup() {
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkSignUp()) {
                    Intent signup = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(signup);
                }

            }
        });
    }

    private void nextSetup() {
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkSignUp()) {
                    Intent income = new Intent(SignupActivity.this, FixedIncomeInfoActivity.class);
                    startActivity(income);
                }
            }
        });
    }

    private boolean checkSignUp() {
        String fName = fullName.getText().toString().trim();
        String bDate = dateBirth.getText().toString().trim();
        String userName = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String rePassword = repeatPassword.getText().toString().trim();

        //get information about user
        if (pass.equals(rePassword)) {
            return userData(fName, bDate, userName, pass, rePassword);
        } else {
            Toast.makeText(SignupActivity.this, "كلمات السر غير متطابقة!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    //References
    private void setupReference() {
        finishBtn = findViewById(R.id.finishBtn);
        nextBtn = findViewById(R.id.nextBtn);
        dateBirth = findViewById(R.id.birthDay);
        fullName = findViewById(R.id.fullName);
        username = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repeatPassword = findViewById(R.id.passwordRepeat);
        userPrefs = getSharedPreferences("userInformation", 0);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    //get information about user method
    private boolean userData(String fName, String bDate, String username, String password, String rePassword) {
        if (fName.length() > 0 && bDate.length() > 0 && username.length() > 0 && password.length() > 0 && rePassword.length() > 0) {
            SharedPreferences.Editor editorPref = userPrefs.edit();
            editorPref.putString("fullName", fName);
            editorPref.putString("dateBirth", bDate);
            editorPref.putString("username", username);
            editorPref.putString("password", password);
            editorPref.apply();

            Toast.makeText(SignupActivity.this, "تم إنشاء الحساب", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(SignupActivity.this, "أملئ جميع الحقول، وحاول مرة أخرى", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (fullName.length() > 0 || dateBirth.length() > 0 || username.length() > 0 || password.length() > 0 || repeatPassword.length() > 0) {
            String FULLNAME = fullName.getText().toString();
            String DATEBIRTH = dateBirth.getText().toString();
            String USERNAME = username.getText().toString();
            String Password = password.getText().toString();
            String REPASSWORD = repeatPassword.getText().toString();

            editor.putString("fullName", FULLNAME);
            editor.putString("dateBirth", DATEBIRTH);
            editor.putString("username", USERNAME);
            editor.putString("password", Password);
            editor.putString("repeatPassword", REPASSWORD);
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
            String passWord = prefs.getString(PASSWORD, "");
            String repeat_password = prefs.getString(REPEATPASSWORD, "");
            fullName.setText(full_name);
            dateBirth.setText(date_birth);
            username.setText(user_name);
            password.setText(passWord);
            repeatPassword.setText(repeat_password);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editor.clear();
        editor.commit();
    }
}