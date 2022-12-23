package com.example.masroofe;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SignupActivity extends AppCompatActivity
{

    private Button finishBtn, nextBtn;
    private EditText fullName, dateBirth, username, password, repeatPassword;
    private int day, month, year;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        //---References-----------------------------
        setupReference();
        getSupportActionBar().hide();

        Calendar calendarBirthDay = Calendar.getInstance();
        prefs = getSharedPreferences("userInformation",0);


        dateBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                year = calendarBirthDay.get(Calendar.YEAR);
                month = calendarBirthDay.get(Calendar.MONTH);
                day = calendarBirthDay.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(SignupActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth)
                    {
                        dateBirth.setText(SimpleDateFormat.getDateInstance().format(calendarBirthDay.getTime()));
                    }
                }, year, month, day);

                datePicker.show();
            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String fName = fullName.getText().toString().trim();
                String bDate = dateBirth.getText().toString().trim();
                String userName = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String rePassword = repeatPassword.getText().toString().trim();

                //get information about user
                if(pass.equals(rePassword))
                {
                    userData(fName, bDate, userName, pass, rePassword);
                }
                else
                {
                    Toast.makeText(SignupActivity.this, "كلمات السر غير متطابقة!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent income = new Intent(SignupActivity.this, FixedIncomeInfoActivity.class);
                startActivity(income);
            }
        });
    }

    //--------------Methods---------------------------------------------------------
    //References
    private void setupReference()
    {
        finishBtn = findViewById(R.id.finishBtn);
        nextBtn = findViewById(R.id.nextBtn);
        dateBirth = findViewById(R.id.birthDay);
        fullName = findViewById(R.id.fullName);
        username = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repeatPassword = findViewById(R.id.passwordRepeat);
    }

    //get information about user method
    private void userData(String fName, String bDate, String username , String password, String rePassword)
    {
        if(fName.length() > 0 && bDate.length() > 0 && username.length() > 0 && password.length() > 0 && rePassword.length() > 0)
        {
            SharedPreferences.Editor editorPref = prefs.edit();
            editorPref.putString("fullName", fName);
            editorPref.putString("dateBirth", bDate);
            editorPref.putString("username", username);
            editorPref.putString("password", password);
            editorPref.apply();

            Toast.makeText(SignupActivity.this, "تم إنشاء الحساب", Toast.LENGTH_SHORT).show();

            Intent signup = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(signup);
        }
        else
        {
            Toast.makeText(SignupActivity.this, "أملئ جميع الحقول، وحاول مرة أخرى", Toast.LENGTH_SHORT).show();
        }

    }


}