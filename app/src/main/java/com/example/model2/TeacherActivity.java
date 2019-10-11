package com.example.model2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TeacherActivity extends AppCompatActivity {

    TextView textTitle;
    EditText editSubject, editMessage;
    Button btnSend;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        Intent intent = getIntent();

        String username = intent.getStringExtra(LoginActivty.TeacherName);

        textTitle = (TextView)findViewById(R.id.textViewTeacherTiltle);
        textTitle.setText("Welcome "+username);

        editSubject = (EditText)findViewById(R.id.Teacher_ET_subject);
        editMessage = (EditText)findViewById(R.id.Teacher_ET_Message);

        btnSend = (Button) findViewById(R.id.Teacher_btnSend);

        dbHelper = new DBHelper(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        addMessage();
    }

    public void addMessage(){
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = editSubject.getText().toString();
                String message = editMessage.getText().toString();

                dbHelper.addMessage("staff",subject,message);

                Toast.makeText(TeacherActivity.this,"Message Published",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
