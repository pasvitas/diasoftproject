package ru.pasvitas.diasoftproject.Adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

import ru.pasvitas.diasoftproject.DB.DBHelper;
import ru.pasvitas.diasoftproject.DB.Storage;
import ru.pasvitas.diasoftproject.Items.Friend;
import ru.pasvitas.diasoftproject.Items.Photo;
import ru.pasvitas.diasoftproject.R;

public class GalleryListAdapter extends ArrayAdapter {

    private final Activity context;
    private final Integer friendid;
    private final ArrayList<Photo> photos;

    private DBHelper dbHelper;

    public GalleryListAdapter(Activity context, String[] tmp, ArrayList<Photo> photos, Integer friendid, DBHelper storage) {

        super(context, R.layout.gallery_list, photos);

        this.photos = photos;
        this.context=context;
        this.friendid=friendid;
        this.dbHelper = storage;
    }


    public View getView(int position, View view, ViewGroup parent) {


        View row = view;
        ImageView imageView = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.gallery_list, parent, false);
            imageView = (ImageView) row.findViewById(R.id.image);
            row.setTag(imageView);
        } else {
            imageView = (ImageView) row.getTag();
        }

        Bitmap pic = tryToGetPic(photos[position]., friendid);
        holder.imageTitle.setText(item.getTitle());
        holder.image.setImageBitmap(item.getImage());
        return row;

        /*final ImageView image;

        //need rework

        View rowView = view;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            rowView = inflater.inflate(R.layout.gallery_list, parent, false);
            image = (ImageView) rowView.findViewById(R.id.image);
            rowView.setTag(image);
        } else {
            image = (ImageView) rowView.getTag();
        }

        final Bitmap pic = tryToGetPic(photoIds.get(position), friendid);

        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                image.setImageBitmap(pic);
            }
        });
        return rowView;*/

    }

    private Bitmap tryToGetPic(Integer photoid, Integer friendid)
    {
        Storage storage = new Storage(dbHelper);

        return storage.getPhoto(photoid, friendid);


    }
}
