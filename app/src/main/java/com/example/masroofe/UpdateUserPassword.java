package com.example.masroofe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateUserPassword extends AppCompatActivity {

    private Button updateUserPassword;
    private EditText currentPassword, newPassword, repeatNewPassword;


    private boolean flag = true;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
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

        updateUserPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentPassword.getText().toString().equals("") && !newPassword.getText().toString().equals("") && !repeatNewPassword.getText().toString().equals("")){
                    if (newPassword.getText().toString().equals(repeatNewPassword.getText().toString())){
                        Toast.makeText(UpdateUserPassword.this, "تم تحديث كلمة السر!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(UpdateUserPassword.this, "كلمة السر الجديدة غير مطابقة!", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(UpdateUserPassword.this, "أكمل المعلومات أولاً!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupReference() {
        currentPassword = findViewById(R.id.currentPassword);
        newPassword = findViewById(R.id.newPassword);
        repeatNewPassword = findViewById(R.id.repeatNewPassword);
        updateUserPassword = findViewById(R.id.updateUserPassword);
    }

    private void setUpSharedPrefs() {
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
    protected void onPause() {
        super.onPause();
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