package ru.pasvitas.diasoftproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Gallery;
import android.widget.GridView;

import java.util.ArrayList;

import ru.pasvitas.diasoftproject.Adapters.GalleryListAdapter;
import ru.pasvitas.diasoftproject.DB.DBHelper;
import ru.pasvitas.diasoftproject.Items.Friend;
import ru.pasvitas.diasoftproject.Items.Photo;
import ru.pasvitas.diasoftproject.Utils.VkApi;

public class GalleryActivity extends AppCompatActivity {

    private GridView gridView;
    private GalleryListAdapter gridAdapter;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galley);

        Intent intent = getIntent();

        final Integer friendid = Integer.parseInt(intent.getStringExtra("friendid"));
        final Friend[] galleryfriend = new Friend[1];
        final ArrayList<Integer> photoIds = new ArrayList<>();

          new Thread(new Runnable(){
            @Override
            public void run() {
                Friend[] friends = VkApi.getFriends();
                for (Friend friend: friends)
                {
                    if (friend.getId() == friendid)
                    {
                        galleryfriend[0] = friend;
                        break;
                    }
                }

                Photo[] photos = VkApi.getPhotos(galleryfriend[0].getId());
                for (Photo photo: photos)
                {
                    photoIds.add(photo.getId());
                }
            }});


        gridView = (GridView) findViewById(R.id.gridView);
        //Integer[] array = photoIds.toArray(new Integer[0]);
        gridAdapter = new GalleryListAdapter(this, photoIds.toArray(new Integer[0]), galleryfriend[0], dbHelper);
        gridView.setAdapter(gridAdapter);
    }
}
