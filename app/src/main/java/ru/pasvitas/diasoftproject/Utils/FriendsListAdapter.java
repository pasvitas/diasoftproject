package ru.pasvitas.diasoftproject.Utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.pasvitas.diasoftproject.Items.Friend;
import ru.pasvitas.diasoftproject.R;

public class FriendsListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final Friend[] friends;

    public FriendsListAdapter(Activity context, String[] friendnames, Friend[] friends) {

        super(context, R.layout.friends_list, friendnames);

        this.context=context;
        this.friends=friends;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.friends_list, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.friendname);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.picture);

        txtTitle.setText(friends[position].toString());

        new Thread(new Runnable(){
            @Override
            public void run() {

                    //

                    final Bitmap pic = PhotoDownloader.DownlaodPhoto(friends[position].getPhoto_50());

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(pic);
                        }
                    });

                }}).start();

        return rowView;

    };
}
