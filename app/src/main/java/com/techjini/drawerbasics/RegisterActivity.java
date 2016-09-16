package com.techjini.drawerbasics;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.techjini.drawerbasics.helpers.DBOpenHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mUsername,mPassword;
    DBOpenHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DBOpenHelper(this);
        Button registerButton = (Button) findViewById(R.id.button_register);
        mUsername = (EditText) findViewById(R.id.input_username);
        mPassword = (EditText) findViewById(R.id.input_password);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.button_register:
               if (!(mUsername.getText().toString().isEmpty() || mUsername.getText().toString().isEmpty())){
                   db.addUsers(mUsername.getText().toString(),mPassword.getText().toString());
                   Toast.makeText(this, "user registered successfully", Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(this, "username or password should not be null", Toast.LENGTH_SHORT).show();
               }
               break;

       }

    }
}
