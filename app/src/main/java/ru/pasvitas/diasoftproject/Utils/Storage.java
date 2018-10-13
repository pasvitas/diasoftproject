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
            ContentValues contentValues = new ContentValues();

            Bitmap b = PhotoDownloader.DownlaodPhoto(friend.getPhoto_50());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            b.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] img = bos.toByteArray();
            contentValues.put(DBHelper.KEY_AVATAR, img);
            return b;
        }

    }
}
