package ru.pasvitas.diasoftproject.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import ru.pasvitas.diasoftproject.Items.Friend;
import ru.pasvitas.diasoftproject.Items.Response;
import ru.pasvitas.diasoftproject.Utils.VkApi;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "friendsDB";
    public static final String TABLE = "friends";

    public static final String KEY_ID = "_id";
    public static final String KEY_VKID = "vkid";
    public static final String KEY_FNAME = "fname";
    public static final String KEY_SNAME = "sname";
    public static final String KEY_AVATAR = "ava";
    public static final String KEY_STATUS = "status";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE + "(" + KEY_ID
                + " integer primary key," + KEY_VKID + " integer," + KEY_FNAME + " text," + KEY_SNAME + " text," + KEY_AVATAR + " blob, "+ KEY_STATUS + " text " + ")");


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        response = gson.fromJson(json, Response.class);

        ArrayList<String> arrayList = new ArrayList<>();

        for(Friend friend : response.friendResponse.friends)
        {
            arrayList.add(friend.toString());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE);

        onCreate(db);
    }
}
