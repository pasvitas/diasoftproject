package ru.pasvitas.diasoftproject.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "friendsDB";
    public static final String TABLE = "friends";

    public static final String KEY_ID = "_id";
    public static final String KEY_VKID = "vkid";
    public static final String KEY_FNAME = "fname";
    public static final String KEY_SNAME = "sname";
    public static final String KEY_AVATAR = "ava";
    public static final String KEY_STATUS = "ava";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
