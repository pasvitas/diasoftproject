package ru.pasvitas.diasoftproject.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import ru.pasvitas.diasoftproject.Items.Friend;
import ru.pasvitas.diasoftproject.Items.Photo;
import ru.pasvitas.diasoftproject.Items.Response;
import ru.pasvitas.diasoftproject.Items.ResponsePhoto;

public class VkApi {

    public static String token;
    private static Integer expires;
    private static Integer userId;

    public static Friend[] getFriends()  {

        final String apiFriends = "https://api.vk.com/method/friends.get?v=5.85&order=name&fields=photo_50,status&access_token=%s";




                URL url;
                try {
                    url = new URL(String.format(apiFriends, token));

                    HttpURLConnection connection =
                            (HttpURLConnection) url.openConnection();

                    StringBuilder json = new StringBuilder(1024);

                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));


                    String tmp;
                    while ((tmp = reader.readLine()) != null)
                        json.append(tmp).append("\n");
                    reader.close();

                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    Response response = gson.fromJson(json.toString(), Response.class);


                    return response.friendResponse.getFriends();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return null;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }


    }

    public static Photo[] getPhotos(Integer userId)  {

        final String apiPhotos = "https://api.vk.com/method/photos.getAll?v=5.85&owner_id=%s&access_token=%s";
        //Неверный запрос



        URL url;
        try {
            url = new URL(String.format(apiPhotos, userId, token));

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            StringBuilder json = new StringBuilder(1024);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));


            String tmp;
            while ((tmp = reader.readLine()) != null)
                json.append(tmp).append("\n");
            reader.close();

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            ResponsePhoto response = gson.fromJson(json.toString(), ResponsePhoto.class);


            return response.photoResponse.getPhotos();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Bitmap DownloadPhoto(String stringUrl)
    {
        try {
            java.net.URL url = new java.net.URL(stringUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void getInfos(String response)
    {
        String[] props = response.split("&");
        token = props[0].split("=")[1];
        expires = Integer.parseInt(props[1].split("=")[1]);
        userId = Integer.parseInt(props[2].split("=")[1]);
    }
}
