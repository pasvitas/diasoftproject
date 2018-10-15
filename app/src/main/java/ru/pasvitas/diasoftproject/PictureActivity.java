package ru.pasvitas.diasoftproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import ru.pasvitas.diasoftproject.DB.DBHelper;
import ru.pasvitas.diasoftproject.DB.Storage;

public class PictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        Integer photoId = intent.getIntExtra("photoId", 0);
        Integer friendId = intent.getIntExtra("friendId", 0);

        DBHelper dbHelper = new DBHelper(this);

        Storage storage = new Storage(dbHelper);

        setContentView(R.layout.activity_picture);
        ImageView iv = findViewById(R.id.photoView);
        Bitmap bitmap = storage.getPhoto(photoId, friendId);
        if (bitmap == null)  Toast.makeText(getApplicationContext(),
                    "Ошибка! Дождитесь загрузки фото!",
                    Toast.LENGTH_LONG).show();
        else iv.setImageBitmap(bitmap);

    }
}
