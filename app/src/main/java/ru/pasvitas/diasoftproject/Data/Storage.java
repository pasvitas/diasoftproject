package ru.pasvitas.diasoftproject.Data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

import ru.pasvitas.diasoftproject.DB.DBHelper;
import ru.pasvitas.diasoftproject.Items.Friend;
import ru.pasvitas.diasoftproject.Utils.VkApi;

public class Storage {

    private DBHelper dbHelper;

    public Storage(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public Bitmap GetImage(final Friend friend)
    {
        final SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE, null, DBHelper.KEY_VKID + " = " + friend.getId(), null, null, null, null);
        if (cursor.moveToFirst()) {
            /*int idIndex = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ID));
            int vkid = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_VKID));
            String fname = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_FNAME));
            String sname  = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_SNAME));
            String statsus  = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_STATUS));
            int emailIndex = cursor.getColumnIndex(DBHelper.KEY_SNAME);*/

            byte[] photo  = cursor.getBlob(cursor.getColumnIndex(DBHelper.KEY_AVATAR));
            cursor.close();
            return BitmapFactory.decodeByteArray(photo, 0, photo.length);


        } else
        {
            final ContentValues cv = new  ContentValues();
            cv.put(DBHelper.KEY_VKID,    friend.getId());
            cv.put(DBHelper.KEY_STATUS,    friend.getStatus());
            cv.put(DBHelper.KEY_FNAME,    friend.getFirst_name());
            cv.put(DBHelper.KEY_LNAME,    friend.getLast_name());

             //final
            final Bitmap[] photo = new Bitmap[1];
            new Thread(new Runnable(){
                @Override
                public void run() {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    photo[0] = VkApi.DownloadPhoto(friend.getPhoto_50());
                    photo[0].compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    cv.put(DBHelper.KEY_AVATAR,  stream.toByteArray());

                    database.insert(DBHelper.TABLE, null, cv);

                }}).start();

            return photo[0];



        }

    }
}
