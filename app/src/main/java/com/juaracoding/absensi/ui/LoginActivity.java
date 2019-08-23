package com.juaracoding.absensi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.juaracoding.absensi.R;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText txtUsername,txtPassword;
    FloatingActionButton fabLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = (TextInputEditText)findViewById(R.id.txtUsername);
        txtPassword = (TextInputEditText)findViewById(R.id.txtPassword);
        fabLogin = (FloatingActionButton)findViewById(R.id.fabLogin);


        fabLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtUsername.getText().toString().equalsIgnoreCase(txtPassword.getText().toString())){

                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
