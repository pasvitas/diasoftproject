package ru.pasvitas.diasoftproject.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public String getPhoto_50() {
        return photo_50;
    }

    public void setPhoto_50(String photo_50) {
        this.photo_50 = photo_50;
    }
}
