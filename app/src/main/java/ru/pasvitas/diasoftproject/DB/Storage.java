package ru.pasvitas.diasoftproject.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

import ru.pasvitas.diasoftproject.DB.DBHelper;
import ru.pasvitas.diasoftproject.Items.Friend;
import ru.pasvitas.diasoftproject.Items.Photo;
import ru.pasvitas.diasoftproject.Utils.VkApi;

public class Storage {

    private DBHelper dbHelper;

    public Storage(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public Bitmap getAvatar(final Friend friend)
    {
        final SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE, null, DBHelper.KEY_VKID + " = " + friend.getId(), null, null, null, null);
        if (cursor.moveToFirst()) {

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

    public Bitmap getPhoto(final Photo photo, final Integer friendid)
    {
        final SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_PHOTO, null, DBHelper.KEY_PHOTOID + " = " + photo.getId(), null, null, null, null);
        if (cursor.moveToFirst()) {

            byte[] photoBitmap  = cursor.getBlob(cursor.getColumnIndex(DBHelper.KEY_PHOTO));
            cursor.close();
            return BitmapFactory.decodeByteArray(photoBitmap, 0, photoBitmap.length);


        } else
        {
            final ContentValues cv = new  ContentValues();
            cv.put(DBHelper.KEY_PHOTOID,    photo.getId());


            //final
            final Bitmap[] photoBitmap = new Bitmap[1];

            final String url = photo.getPhotoURL();

            new Thread(new Runnable(){
                @Override
                public void run() {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    photoBitmap[0] = VkApi.DownloadPhoto(url);
                    photoBitmap[0].compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    cv.put(DBHelper.KEY_PHOTO,  stream.toByteArray());

                    database.insert(DBHelper.TABLE_PHOTO, null, cv);

                }}).start();

            return photoBitmap[0];

            /*final ContentValues cv = new  ContentValues();
            final Bitmap[] returnPhoto = new Bitmap[1];

            new Thread(new Runnable(){
                @Override
                public void run() {

                Photo[] photos = VkApi.getPhotos(friendid);

                for (final Photo photo : photos)
                {

                    Cursor photoCursor = database.query(DBHelper.TABLE_PHOTO, null, DBHelper.KEY_PHOTOID + " = " + photo.getId(), null, null, null, null);
                    if (!photoCursor.moveToFirst()) {

                        cv.put(DBHelper.KEY_PHOTOID, photo.getId());


                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        Bitmap photoBitmap = VkApi.DownloadPhoto(photo.getPhotoURL());
                        photoBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                        cv.put(DBHelper.KEY_PHOTO, stream.toByteArray());
                        database.insert(DBHelper.TABLE_PHOTO, null, cv);
                        if (photo.getId() == photoid) {
                            returnPhoto[0] = photoBitmap;
                            break;
                        }
                    }
                    else
                    {
                        byte[] photoArray = photoCursor.getBlob(photoCursor.getColumnIndex(DBHelper.KEY_PHOTO));
                        photoCursor.close();
                        returnPhoto[0] = BitmapFactory.decodeByteArray(photoArray, 0, photoArray.length);
                    }
                }
                }}).start();

            return returnPhoto[0];*/



        }

    }
}
