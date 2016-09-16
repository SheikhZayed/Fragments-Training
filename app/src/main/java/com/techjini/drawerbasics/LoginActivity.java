package com.techjini.drawerbasics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.techjini.drawerbasics.helpers.DBOpenHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    DBOpenHelper mdbOpenHelper;
    String mUsername, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mdbOpenHelper = new DBOpenHelper(this);

        Button registerButton = (Button) findViewById(R.id.button_register);
        Button loginButton = (Button) findViewById(R.id.button_login);
        TextView username = (TextView) findViewById(R.id.input_username);
        TextView password = (TextView) findViewById(R.id.input_password);
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

        mUsername = username.getText().toString();
        mPassword = password.getText().toString();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                String validPassword = mdbOpenHelper.checkIfUserExists(mUsername);
                if (mPassword.equals(validPassword)) {
                    Intent intent = new Intent(LoginActivity.this, UserDashboardActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "User Credentials invalid", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }

    }
}
