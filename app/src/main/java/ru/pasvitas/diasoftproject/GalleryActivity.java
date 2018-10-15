package ru.pasvitas.diasoftproject;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import ru.pasvitas.diasoftproject.Adapters.GalleryListAdapter;
import ru.pasvitas.diasoftproject.DB.DBHelper;
import ru.pasvitas.diasoftproject.DB.Storage;
import ru.pasvitas.diasoftproject.Items.Friend;
import ru.pasvitas.diasoftproject.Items.Photo;
import ru.pasvitas.diasoftproject.Utils.VkApi;

public class GalleryActivity extends AppCompatActivity {

    private GridView gridView;
    private Handler handler;
    private DBHelper dbHelper;
    private Storage storage;
    Context context = this;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galley);

        intent = getIntent();
        handler = new Handler();
        dbHelper = new DBHelper(this);

        storage = new Storage(dbHelper);

        final Integer friendid = intent.getIntExtra("friendid", 0);

        gridView = (GridView) findViewById(R.id.gridView);



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Photo photo = (Photo) parent.getItemAtPosition(position);
                Intent intent = new Intent(context, PictureActivity.class);
                intent.putExtra("photoId", photo.getId());
                intent.putExtra("friendId", friendid);
                startActivity(intent);
            }
        });


        //loadGallery(friendid);

    }

    @Override
    protected void onStart() {
        super.onStart();
        final Integer friendid = intent.getIntExtra("friendid", 0);


        loadGallery(friendid);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    void loadGallery(final Integer friendid) {
        new Thread() {
            public void run() {

                final Photo[] photos = VkApi.getPhotos(friendid);

                if (photos == null) {
                    handler.post(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Ошибка!",
                                    Toast.LENGTH_LONG).show();

                        }
                    });
                } else {


                    handler.post(new Runnable() {
                        public void run() {

                            setGallery(photos, friendid);

                        }
                    });
                }
            }
        }.start();
    }


    void setGallery(Photo[] photos, Integer friendid)
    {
        ArrayList<Photo> listPhotos = new ArrayList<>();
        for (Photo photo: photos)
        {
            listPhotos.add(photo);
        }

        //Integer[] array = photoIds.toArray(new Integer[0]);
        GalleryListAdapter gridAdapter = new GalleryListAdapter(this, listPhotos, friendid, storage);
        gridView.setAdapter(gridAdapter);


    }
}
