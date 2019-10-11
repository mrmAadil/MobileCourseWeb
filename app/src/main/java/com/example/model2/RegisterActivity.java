package com.example.model2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText editUserName, editPassword;
    Button  btnRegister;
    DBHelper dbHelper;
    RadioGroup radioGroup;
    RadioButton radioBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editUserName = (EditText) findViewById(R.id.Register_ET_username);
        editPassword = (EditText) findViewById(R.id.Register_ET_password);
        btnRegister = (Button) findViewById(R.id.Register_btn_register);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        int selectedID = radioGroup.getCheckedRadioButtonId();

        radioBtn = (RadioButton) findViewById(selectedID);

        dbHelper = new DBHelper(this);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedID = radioGroup.getCheckedRadioButtonId();

                radioBtn = (RadioButton) findViewById(selectedID);

                String username = editUserName.getText().toString();
                String password = editPassword.getText().toString();
                String type = (String)radioBtn.getText();

                dbHelper.addUser(username,password,type);

                Toast.makeText(RegisterActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this,LoginActivty.class);

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void addUser(){

    }
}
