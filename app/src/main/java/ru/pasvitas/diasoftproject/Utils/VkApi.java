package ru.pasvitas.diasoftproject.Utils;

import android.os.Handler;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import ru.pasvitas.diasoftproject.Items.Friend;

public class VkApi {

    public static Friend[] getFriends()  {

        final String apiFriends = "https://api.vk.com/method/friends.get?v=5.85&order=name&fields=photo_50&access_token=%s";




                URL url = null;
                try {
                    url = new URL(String.format(apiFriends, UserInfo.token));

                    HttpURLConnection connection =
                            (HttpURLConnection) url.openConnection();

                    StringBuffer json = new StringBuffer(1024);

                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));


                    String tmp = "";
                    while ((tmp = reader.readLine()) != null)
                        json.append(tmp).append("\n");
                    reader.close();

                    return json.toString();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return null;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }


    }

    public static String getUser(final Integer userid)  {

        final String apiUsers = "https://api.vk.com/method/users.get?v=5.85&user_ids=%d&access_token=%s";
        final StringBuffer json = new StringBuffer(1024);

        new Thread(new Runnable(){
            @Override
            public void run() {

                try {

                URL url = new URL(String.format(apiUsers, userid, UserInfo.token));
                HttpURLConnection connection =
                        (HttpURLConnection)url.openConnection();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));


                String tmp="";
                while((tmp=reader.readLine())!=null)
                    json.append(tmp).append("\n");
                reader.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }}).start();

        return json.toString();
    }
}
