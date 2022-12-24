package com.example.masroofe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UpdateUserPassword extends AppCompatActivity {

    private Button updateUserPassword;
    private EditText currentPassword, newPassword, repeatNewPassword;
    private ImageView imgHome, imgCal, imgGuide, imgMenu;
    private FloatingActionButton btn;
    private boolean flag = true;
    private SharedPreferences prefs, userPrefs;
    private SharedPreferences.Editor editor, userEditor;
    public static final String CURRENTPASSWORD = "current_password";
    public static final String NEWPASSWORD = "new_password";
    public static final String REPEATNEWPASSWORD = "repeat_new_password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_user_password_activity);
        getSupportActionBar().hide();
        setupReference();
        setUpSharedPrefs();
        setUp();
        checkDate();
    }

    private void setUp() {
        String current_password = userPrefs.getString("password", "");

        updateUserPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPassword.length() > 0 && newPassword.length() > 0 && repeatNewPassword.length() > 0) {
                    if (currentPassword.getText().toString().equals(current_password)) {
                        if (newPassword.getText().toString().equals(repeatNewPassword.getText().toString())) {
                            userEditor = userPrefs.edit();
                            userEditor.putString("password", newPassword.getText().toString());
                            userEditor.apply();
                            Toast.makeText(UpdateUserPassword.this, "تم تحديث كلمة السر!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UpdateUserPassword.this, "كلمة السر الجديدة غير مطابقة!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(UpdateUserPassword.this, "كلمة السر الحالية غير صحيحة", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UpdateUserPassword.this, "أكمل المعلومات أولاً!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateUserPassword.this, MainActivity.class);
                startActivity(intent);
            }
        });

        imgCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateUserPassword.this, MonthsActivity.class);
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
                Intent intent = new Intent(UpdateUserPassword.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateUserPassword.this, Add_Activity.class);
                startActivity(intent);
            }
        });

    }

    private void setupReference() {
        currentPassword = findViewById(R.id.currentPassword);
        newPassword = findViewById(R.id.newPassword);
        repeatNewPassword = findViewById(R.id.repeatNewPassword);
        updateUserPassword = findViewById(R.id.updateUserPassword);
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

    private void checkDate() {
        boolean f = prefs.getBoolean("FLAG", false);
        if (f) {
            String current_password = prefs.getString(CURRENTPASSWORD, "");
            String new_password = prefs.getString(NEWPASSWORD, "");
            String repeat_new_password = prefs.getString(REPEATNEWPASSWORD, "");
            currentPassword.setText(current_password);
            newPassword.setText(new_password);
            repeatNewPassword.setText(repeat_new_password);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (!currentPassword.getText().toString().equals("") || !newPassword.getText().toString().equals("") || !repeatNewPassword.getText().toString().equals("")) {
            String current_password = currentPassword.getText().toString();
            String new_password = newPassword.getText().toString();
            String repeat_new_password = repeatNewPassword.getText().toString();

            editor.putString(CURRENTPASSWORD, current_password);
            editor.putString(NEWPASSWORD, new_password);
            editor.putString(REPEATNEWPASSWORD, repeat_new_password);
            editor.putBoolean("FLAG", flag);
            editor.commit();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        editor.clear();
        editor.commit();
    }
}