package ru.pasvitas.diasoftproject.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Friend {

    @Expose
    @SerializedName("id")
    private Integer id;

    @Expose
    @SerializedName("first_name")
    private String first_name;

    @Expose
    @SerializedName("last_name")
    private String last_name;

    @Expose
    @SerializedName("online")
    private Integer online;

    @Expose
    @SerializedName("photo_50")
    private String photo_50;

    @Expose
    @SerializedName("status")
    private String status;

    @Override
    public String toString()
    {
        return first_name + " " + last_name;
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

    public String getStatus() {
        return status;
    }
}
