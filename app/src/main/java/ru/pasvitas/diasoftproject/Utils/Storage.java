package ru.pasvitas.diasoftproject.Utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

import ru.pasvitas.diasoftproject.DB.DBHelper;
import ru.pasvitas.diasoftproject.Items.Friend;

public class Storage {

    DBHelper dbHelper;

    public Storage(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public Bitmap GetImage(Friend friend)
    {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE, null, DBHelper.KEY_VKID + " = " + friend.getId(), null, null, null, null);
        if (cursor.moveToFirst()) {
            /*int idIndex = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ID));
            int vkid = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_VKID));
            String fname = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_FNAME));
            String sname  = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_SNAME));
            String statsus  = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_STATUS));
            int emailIndex = cursor.getColumnIndex(DBHelper.KEY_SNAME);*/

            byte[] photo  = cursor.getBlob(cursor.getColumnIndex(DBHelper.KEY_AVATAR));
            do {
                return BitmapFactory.decodeByteArray(photo, 0, photo.length);
            } while (cursor.moveToNext());
        } else
        {
            ContentValues cv = new  ContentValues();
            cv.put(DBHelper.KEY_VKID,    friend.getId());
            cv.put(DBHelper.KEY_STATUS,    friend.getStatus());
            cv.put(DBHelper.KEY_FNAME,    friend.getFirst_name());
            cv.put(DBHelper.KEY_LNAME,    friend.getLast_name());

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap photo =  PhotoDownloader.DownlaodPhoto(friend.getPhoto_50());
            photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            cv.put(DBHelper.KEY_AVATAR,  stream.toByteArray());

            database.insert(DBHelper.TABLE, null, cv);
            return photo;
        }

    }
}
