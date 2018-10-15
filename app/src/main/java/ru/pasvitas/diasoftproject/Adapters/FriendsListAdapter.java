package ru.pasvitas.diasoftproject.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ru.pasvitas.diasoftproject.Items.Friend;
import ru.pasvitas.diasoftproject.R;
import ru.pasvitas.diasoftproject.DB.Storage;

public class FriendsListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    public final Friend[] friends;

    private Storage storage;

    public FriendsListAdapter(Activity context, String[] friendnames, Friend[] friends, Storage storage) {

        super(context, R.layout.friends_list, friendnames);

        this.context=context;
        this.friends=friends;
        this.storage = storage;
    }


    @NonNull
    public View getView(final int position, View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"}) View rowView=inflater.inflate(R.layout.friends_list, null,true);

        TextView tvName = rowView.findViewById(R.id.friendname);
        TextView tvStatus = rowView.findViewById(R.id.friendstatus);
        final ImageView imageView = rowView.findViewById(R.id.picture);

        tvName.setText(friends[position].toString());
        tvStatus.setText(friends[position].getStatus());

        final Bitmap pic = tryToGetPic(friends[position]);
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(pic);
            }});

        return rowView;

    }

    private Bitmap tryToGetPic(Friend friend)
    {
        return storage.getAvatar(friend);
    }
}
