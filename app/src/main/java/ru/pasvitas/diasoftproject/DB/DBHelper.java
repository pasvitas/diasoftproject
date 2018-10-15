package ru.pasvitas.diasoftproject.DB;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 18;
    public static final String DATABASE_NAME = "friendsDB";
    public static final String TABLE = "friends";
    public static final String TABLE_PHOTO = "photos";

    public static final String KEY_ID = "id";
    public static final String KEY_VKID = "vkid";
    public static final String KEY_FNAME = "fname";
    public static final String KEY_LNAME = "lname";
    public static final String KEY_AVATAR = "ava";
    public static final String KEY_AVAURL = "avaurl";
    public static final String KEY_STATUS = "status";

    public static final String KEY_PHOTOID = "photoid";
    public static final String KEY_PHOTO = "photo";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_PHOTO + "(" +  KEY_ID + " integer primary key AUTOINCREMENT, " + KEY_PHOTOID + " string, " +  KEY_PHOTO + " blob " + ")");

        db.execSQL("create table " + TABLE + "(" + KEY_VKID
                + " integer primary key," + KEY_FNAME + " text," + KEY_LNAME + " text," + KEY_AVAURL + " text," + KEY_AVATAR + " blob, "+ KEY_STATUS + " text " + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE);
        db.execSQL("drop table if exists " + TABLE_PHOTO);

        onCreate(db);
    }
}
