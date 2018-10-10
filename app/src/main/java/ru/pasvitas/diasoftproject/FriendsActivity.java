package ru.pasvitas.diasoftproject;

import android.app.ListActivity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import ru.pasvitas.diasoftproject.Items.Friend;
import ru.pasvitas.diasoftproject.Items.FriendResponse;
import ru.pasvitas.diasoftproject.Items.Response;
import ru.pasvitas.diasoftproject.Utils.UserInfo;
import ru.pasvitas.diasoftproject.Utils.VkApi;

public class FriendsActivity extends ListActivity {

    private ArrayAdapter<String> arrayAdapter;
    private VkApi vkApi = new VkApi();

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
            loadFriendsList();

    }

    void loadFriendsList() {

        new Thread() {
            public void run() {

                final String json = vkApi.getFriends();

                if (json == null) {
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
                }

            }
        }.start();



        //String friends = vkApi.getFriends();




        /*ArrayList<String> arrayList = new ArrayList<>();

        for(Integer friendId : friendResponse[0].friendsId)
        {
            String friendInfo = vkApi.getUser(friendId);
            Friend friend = gson.fromJson(friendInfo, Friend.class);
            arrayList.add(friend.getFirst_name() + " " + friend.getSecond_name());
        }



        setListAdapter(arrayAdapter);*/
    }

    private void applyFrindsList(String json) {

        Response response;


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        response = gson.fromJson(json, Response.class);

        ArrayList<String> arrayList = new ArrayList<>();

        for(Friend friend : response.friendResponse.friends)
        {
            arrayList.add(friend.toString());
            //String friendInfo = vkApi.getUser(friendId);
            //Friend friend = gson.fromJson(friendInfo, Friend.class);
            //arrayList.add(friend.getFirst_name() + " " + friend.getSecond_name());
        }

        Collections.sort(arrayList);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);

        setListAdapter(arrayAdapter);
    }
}
