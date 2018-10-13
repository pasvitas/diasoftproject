package ru.pasvitas.diasoftproject.Utils;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.pasvitas.diasoftproject.DB.DBHelper;
import ru.pasvitas.diasoftproject.Items.Friend;
import ru.pasvitas.diasoftproject.R;

public class FriendsListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final Friend[] friends;

    DBHelper dbHelper;

    public FriendsListAdapter(Activity context, String[] friendnames, Friend[] friends, DBHelper storage) {

        super(context, R.layout.friends_list, friendnames);

        this.context=context;
        this.friends=friends;
        this.dbHelper = storage;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.friends_list, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.friendname);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.picture);

        txtTitle.setText(friends[position].toString());



                    final Bitmap pic = tryToGetPic(friends[position]);

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(pic);
                        }
                    });



        return rowView;

    };

    Bitmap tryToGetPic(Friend friend)
    {
        Storage storage = new Storage(dbHelper);

        return storage.GetImage(friend);


    }
}
