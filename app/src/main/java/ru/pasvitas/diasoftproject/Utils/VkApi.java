package ru.pasvitas.diasoftproject.Utils;

import android.os.Handler;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VkApi {
    private static final String apiFriends = "https://api.vk.com/method/friends.get?v=5.85&fields=1&access_token=%s";
    private static final String apiUsers = "https://api.vk.com/method/users.get?v=5.85&user_ids=%d&access_token=%s";

    public String getFriends()  {

           try
           {

               URL url = new URL(String.format(apiFriends, UserInfo.token));
               HttpURLConnection connection =
                       (HttpURLConnection)url.openConnection();

               //connection.addRequestProperty("x-api-key",
               //        context.getString(R.string.open_weather_maps_app_id));

               BufferedReader reader = new BufferedReader(
                       new InputStreamReader(connection.getInputStream()));

               StringBuffer json = new StringBuffer(1024);
               String tmp="";
               while((tmp=reader.readLine())!=null)
                   json.append(tmp).append("\n");
               reader.close();
               return json.toString();
           }
           catch (Exception e)
           {
               e.printStackTrace();
               return null;
           }

    }

    public String getUser(Integer userid)  {


        try
        {

            URL url = new URL(String.format(apiUsers, userid, UserInfo.token));
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();

            //connection.addRequestProperty("x-api-key",
            //        context.getString(R.string.open_weather_maps_app_id));

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();
            return json.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
}
