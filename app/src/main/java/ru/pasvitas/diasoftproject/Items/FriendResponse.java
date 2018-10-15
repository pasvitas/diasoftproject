package ru.pasvitas.diasoftproject.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FriendResponse {
    @Expose
    @SerializedName("count")
    private Integer count;

    @Expose
    @SerializedName("items")
    private Friend[] friends;

    public Integer getCount() {
        return count;
    }

    public Friend[] getFriends() {
        return friends;
    }
}
