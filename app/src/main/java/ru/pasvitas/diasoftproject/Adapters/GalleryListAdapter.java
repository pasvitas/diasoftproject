package ru.pasvitas.diasoftproject.Adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import ru.pasvitas.diasoftproject.DB.DBHelper;
import ru.pasvitas.diasoftproject.DB.Storage;
import ru.pasvitas.diasoftproject.Items.Friend;
import ru.pasvitas.diasoftproject.R;

public class GalleryListAdapter extends ArrayAdapter<Integer> {

    private final Activity context;
    private final Friend friend;
    private final Integer[] photoIds;

    private DBHelper dbHelper;

    public GalleryListAdapter(Activity context, Integer[] photoIds, Friend friend, DBHelper storage) {

        super(context, R.layout.gallery_list, photoIds);

        this.photoIds = photoIds;
        this.context=context;
        this.friend=friend;
        this.dbHelper = storage;
    }


    public View getView(final int position, View view, ViewGroup parent) {


        final ImageView image;

        View rowView = view;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            rowView = inflater.inflate(R.layout.gallery_list, parent, false);
            image = (ImageView) rowView.findViewById(R.id.image);
            rowView.setTag(image);
        } else {
            image = (ImageView) rowView.getTag();
        }

        final Bitmap pic = tryToGetPic(photoIds[position], friend.getId());

        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                image.setImageBitmap(pic);
            }
        });
        return rowView;

    }

    private Bitmap tryToGetPic(Integer photoid, Integer friendid)
    {
        Storage storage = new Storage(dbHelper);

        return storage.getPhoto(photoid, friendid);


    }
}
