package com.example.model2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "courseweb.db";
    public static final String TABLE_USER = "user";
    public static final String USER_COLUMN_ID = "id";
    public static final String TABLE_MESSAGE = "message";
    public static final String USER_COLUMN_NAME = "username";
    public static final String USER_COLUMN_PASSWORD = "password";
    public static final String USER_COLUMN_TYPE = "type";
    public static final String MESSAGE_COLUMN_ID = "id";
    public static final String MESSAGE_COLUMN_NAME = "user";
    public static final String MESSAGE_COLUMN_SUBJECT = "subject";
    public static final String MESSAGE_COLUMN_MESSAGE = "message";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGE);

        db.execSQL("CREATE TABLE "+ TABLE_USER + "(" +
                USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                USER_COLUMN_NAME + " TEXT,"+
                USER_COLUMN_PASSWORD + " TEXT,"+
                USER_COLUMN_TYPE + " TEXT)");

        db.execSQL("CREATE TABLE "+ TABLE_MESSAGE + "(" +
                MESSAGE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                MESSAGE_COLUMN_NAME + " TEXT,"+
                MESSAGE_COLUMN_SUBJECT+ " TEXT,"+
                MESSAGE_COLUMN_MESSAGE+ " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_MESSAGE);
        onCreate(sqLiteDatabase);
    }
    public void addUser(String username, String password,String Type){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_NAME,username);
        values.put(USER_COLUMN_PASSWORD,password);
        values.put(USER_COLUMN_TYPE,Type);

        long newRowID = db.insert(TABLE_USER,null,values);
    }

    public int checkUser(String username, String password){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE username = '" + username + "' and password = '" +password+ "'",null);

        do{
            if(!cursor.moveToNext())
                return -1;

            return cursor.getInt(0);
        }while(cursor.moveToNext());
    }

    public String checkUserType(String username){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT type FROM " + TABLE_USER + " WHERE username = '" + username + "'",null);
        do {
            if (!cursor.moveToNext())
                return null;
            return cursor.getString(0);
        }while (cursor.moveToNext()) ;


      /**  do {
            if (!cursor.moveToNext())
                return null;

            String type = cursor.getString(cursor.getColumnIndexOrThrow(USER_COLUMN_TYPE));
            return type;
        }while(cursor.moveToNext());**/
    }

    public void addMessage(String user, String subject, String message){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MESSAGE_COLUMN_NAME,user);
        values.put(MESSAGE_COLUMN_SUBJECT, subject);
        values.put(MESSAGE_COLUMN_MESSAGE, message);

        long newRowID = db.insert(TABLE_MESSAGE,null,values);
    }

    public ArrayList<HashMap<String,String>> ReadAllMessage2(){
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<HashMap<String,String>> messageList = new ArrayList<>();

        String query = "SELECT id,subject,message FROM "+TABLE_MESSAGE;

        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()) {
            HashMap<String,String> message = new HashMap<>();
            message.put("id",cursor.getString(cursor.getColumnIndex(MESSAGE_COLUMN_ID)));
            message.put("subject",cursor.getString(cursor.getColumnIndex(MESSAGE_COLUMN_SUBJECT)));
            message.put("message",cursor.getString(cursor.getColumnIndex(MESSAGE_COLUMN_MESSAGE)));

            messageList.add(message);
        }


        return messageList;
    }

    public Cursor readAllMessage(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_MESSAGE;

        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

}
