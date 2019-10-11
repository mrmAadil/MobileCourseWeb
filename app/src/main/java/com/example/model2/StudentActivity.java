package com.example.model2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ArrayList<Message> listMessage;
    TextView textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String username = extras.getString(LoginActivty.StudentName);

        textTitle = (TextView) findViewById(R.id.textView3);
        textTitle.setText("Welcome "+username);

        dbHelper = new DBHelper(this);

        ArrayList<HashMap<String,String>> meessageList = dbHelper.ReadAllMessage2();

        ListView listView = (ListView) findViewById(R.id.Student_listView);
        ListAdapter adapter= new SimpleAdapter(StudentActivity.this,meessageList,R.layout.user_list,new String[]{"id","subject","message"},new int[]{R.id.textID_student,R.id.textSubject_Student,R.id.textMessage_Student});

        listView.setAdapter(adapter);
    }
}
