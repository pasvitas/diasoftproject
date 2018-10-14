package ru.pasvitas.diasoftproject.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import ru.pasvitas.diasoftproject.Items.Friend;
import ru.pasvitas.diasoftproject.Items.Photo;
import ru.pasvitas.diasoftproject.Utils.VkApi;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 9;
    public static final String DATABASE_NAME = "friendsDB";
    public static final String TABLE = "friends";
    public static final String TABLE_PHOTO = "photos";

    public static final String KEY_ID = "_id";
    public static final String KEY_VKID = "vkid";
    public static final String KEY_FNAME = "fname";
    public static final String KEY_LNAME = "lname";
    public static final String KEY_AVATAR = "ava";
    public static final String KEY_STATUS = "status";

    public static final String KEY_PHOTOID = "photoid";
    public static final String KEY_PHOTO = "photo";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_PHOTO + "(" + "id integer primary key," +  KEY_PHOTOID + " integer, " +  KEY_PHOTO + " blob " + ")");

        db.execSQL("create table " + TABLE + "(" + KEY_ID
                + " integer primary key," + KEY_VKID + " integer," + KEY_FNAME + " text," + KEY_LNAME + " text," + KEY_AVATAR + " blob, "+ KEY_STATUS + " text " + ")");


        new Thread(new Runnable(){
            @Override
            public void run() {
                 ArrayList<Friend> friends = new ArrayList<>(Arrays.asList(VkApi.getFriends()));


                for (Friend friend : friends)
                {
                    ContentValues cv = new  ContentValues();
                    cv.put(KEY_VKID,    friend.getId());
                    cv.put(KEY_STATUS,    friend.getStatus());
                    cv.put(KEY_FNAME,    friend.getFirst_name());
                    cv.put(KEY_LNAME,    friend.getLast_name());

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    VkApi.DownloadPhoto(friend.getPhoto_50()).compress(Bitmap.CompressFormat.JPEG, 100, stream);

                    cv.put(KEY_AVATAR,  stream.toByteArray());

                    db.insert(DBHelper.TABLE, null, cv);


                    /*Photo[] photos = VkApi.getPhotos(friend.getId());

                    for (Photo photo : photos)
                    {
                        cv = new  ContentValues();
                        cv.put(KEY_PHOTOID, photo.getId());

                        stream = new ByteArrayOutputStream();
                        VkApi.DownloadPhoto(photo.getPhotoURL()).compress(Bitmap.CompressFormat.JPEG, 100, stream);

                        cv.put(KEY_PHOTO, stream.toByteArray());
                    }*/


                }
            }}).start();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE);
        db.execSQL("drop table if exists " + TABLE_PHOTO);

        onCreate(db);
    }
}
