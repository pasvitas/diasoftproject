package ru.pasvitas.diasoftproject.Adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import java.util.ArrayList;
import ru.pasvitas.diasoftproject.DB.Storage;
import ru.pasvitas.diasoftproject.Items.Photo;
import ru.pasvitas.diasoftproject.R;

public class GalleryListAdapter extends ArrayAdapter {

    private final Activity context;
    private final Integer friendid;
    private final ArrayList<Photo> photos;

    private Storage storage;

    public GalleryListAdapter(Activity context, ArrayList<Photo> photos, Integer friendId, Storage storage) {

        super(context, R.layout.gallery_list, photos);

        this.photos = photos;
        this.context=context;
        this.friendid=friendId;
        this.storage = storage;
    }


    @NonNull
    public View getView(int position, View view, @NonNull ViewGroup parent) {


        View row = view;
        ImageView imageView;

        if (row == null) {
            LayoutInflater inflater = (context).getLayoutInflater();
            row = inflater.inflate(R.layout.gallery_list, parent, false);
            imageView = row.findViewById(R.id.image);
            row.setTag(imageView);
        } else {
            imageView = (ImageView) row.getTag();
        }

        final ImageView tmpImageView = imageView;

        final Bitmap pic = tryToGetPic(photos.get(position), friendid);

        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tmpImageView.setImageBitmap(pic);
            }
        });
        return row;

    }

    private Bitmap tryToGetPic(Photo photo, Integer friendId)
    {
        return storage.getPhoto(photo, friendId);
    }
}
