package ru.pasvitas.diasoftproject;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import ru.pasvitas.diasoftproject.DB.DBHelper;
import ru.pasvitas.diasoftproject.Items.Friend;
import ru.pasvitas.diasoftproject.Items.FriendResponse;
import ru.pasvitas.diasoftproject.Items.Response;
import ru.pasvitas.diasoftproject.Utils.FriendsListAdapter;
import ru.pasvitas.diasoftproject.Utils.UserInfo;
import ru.pasvitas.diasoftproject.Utils.VkApi;

public class FriendsActivity extends ListActivity {

    private ArrayAdapter<String> arrayAdapter;


    Handler handler;

    DBHelper dbHelper;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        handler = new Handler();
        dbHelper = new DBHelper(this);
            loadFriendsList();

    }

    void loadFriendsList() {

        new Thread() {
            public void run() {

                final Friend[] friends = VkApi.getFriends();

                if (friends == null) {
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

                            applyFrindsList(friends);

                        }
                    });
                }



/*                if (json == null) {
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

                            applyFrindsList(json);

                        }
                    });
                }*/

            }
        }.start();
    }

    private void applyFrindsList(Friend[] friends) {




        //Collections.sort(arrayList);

        String[] tmp = new String[friends.length];

        FriendsListAdapter adapter=new FriendsListAdapter(this, tmp, friends, dbHelper);
        lv=findViewById(android.R.id.list);
        lv.setAdapter(adapter);

    }
}
