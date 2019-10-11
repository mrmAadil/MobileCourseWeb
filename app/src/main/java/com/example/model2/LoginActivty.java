package com.example.model2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivty extends AppCompatActivity {

    EditText editUserName, editPassword;
    Button btnLogIn, btnRegister;
    DBHelper dbHelper;

    public static final String TeacherName = "Login.TeacherName";
    public static final String StudentName = "Login.StudentName";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUserName = (EditText) findViewById(R.id.LoginET_userName);
        editPassword = (EditText) findViewById(R.id.LoginET_password);
        btnLogIn = (Button) findViewById(R.id.Login_btnLogin);
        btnRegister = (Button) findViewById(R.id.Login_btnRegister);

        dbHelper = new DBHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivty.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        validateLogin();
    }

    public void validateLogin(){
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editUserName.getText().toString();
                String password = editPassword.getText().toString();

                int res = dbHelper.checkUser(username,password);
                 String type = dbHelper.checkUserType(username);

                if(res != -1){
                    if(type.equals("teacher")){
                        Toast.makeText(LoginActivty.this, "Welcome Lecturer !",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivty.this,TeacherActivity.class);
                        intent.putExtra(TeacherName,username);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivty.this, "Welcome Student !",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivty.this,StudentActivity.class);
                        intent.putExtra(StudentName,username);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(LoginActivty.this,"Invalid Login !!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
