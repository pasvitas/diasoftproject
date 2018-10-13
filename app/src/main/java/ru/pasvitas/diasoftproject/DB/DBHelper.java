package ru.pasvitas.diasoftproject.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import ru.pasvitas.diasoftproject.Items.Friend;
import ru.pasvitas.diasoftproject.Items.Response;
import ru.pasvitas.diasoftproject.Utils.PhotoDownloader;
import ru.pasvitas.diasoftproject.Utils.VkApi;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "friendsDB";
    public static final String TABLE = "friends";

    public static final String KEY_ID = "_id";
    public static final String KEY_VKID = "vkid";
    public static final String KEY_FNAME = "fname";
    public static final String KEY_LNAME = "lname";
    public static final String KEY_AVATAR = "ava";
    public static final String KEY_STATUS = "status";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE + "(" + KEY_ID
                + " integer primary key," + KEY_VKID + " integer," + KEY_FNAME + " text," + KEY_LNAME + " text," + KEY_AVATAR + " blob, "+ KEY_STATUS + " text " + ")");


        Friend[] friends = VkApi.getFriends();

        SQLiteDatabase database = this.getWritableDatabase();

        for (Friend friend : friends)
        {
            ContentValues cv = new  ContentValues();
            cv.put(KEY_VKID,    friend.getId());
            cv.put(KEY_STATUS,    friend.getStatus());
            cv.put(KEY_FNAME,    friend.getFirst_name());
            cv.put(KEY_LNAME,    friend.getLast_name());

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            PhotoDownloader.DownlaodPhoto(friend.getPhoto_50()).compress(Bitmap.CompressFormat.JPEG, 100, stream);

            cv.put(KEY_AVATAR,  stream.toByteArray());

            database.insert(DBHelper.TABLE, null, cv);

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE);

        onCreate(db);
    }
}
