package com.example.userdbapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "user_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE USERS (USERID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT)";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS USERS";
        sqLiteDatabase.execSQL(DROP_USER_TABLE);

        onCreate(sqLiteDatabase);
    }

    public long insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", user.getUsername());
        contentValues.put("PASSWORD", user.getPassword());
        long row = db.insert("USERS", null, contentValues);
        db.close();
        return row;
    }

    public boolean fetchUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("USERS", new String[]{"USERID"}, "USERNAME=? AND PASSWORD=?", new String[]{username, password}, null, null, null);
        int num_rows = cursor.getCount();
        db.close();
        if (num_rows > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<User> fetchAllUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM USERS"; // ???
        Cursor cursor = db.rawQuery(sql, null);

        List<User> userList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                User user = new User(cursor.getString(1), cursor.getString(2));
                userList.add(user);
            } while(cursor.moveToLast());
        }
        return userList;
    }

    public int updatePassword(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("PASSWORD", user.getPassword());

        return db.update("USERS", contentValues, "USERNAME=?", new String[]{user.getUsername()});
    }
}
