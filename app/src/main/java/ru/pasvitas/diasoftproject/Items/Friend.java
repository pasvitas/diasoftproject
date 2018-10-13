package ru.pasvitas.diasoftproject.Items;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.media.Image;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Friend {

    @Expose
    @SerializedName("id")
    Integer id;
    @Expose
    @SerializedName("first_name")
    String first_name;



    @Expose
    @SerializedName("last_name")
    String last_name;
    @Expose
    @SerializedName("online")
    Integer online;

    @Expose
    @SerializedName("photo_50")
    String photo_50;

    @Override
    public String toString()
    {
        return new String(first_name + " " + last_name);
    }

    public Integer getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public Integer getOnline() {
        return online;
    }

    public String getPhoto_50() {
        return photo_50;
    }


}
