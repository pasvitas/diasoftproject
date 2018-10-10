package ru.pasvitas.diasoftproject.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FriendResponse {
    @Expose
    @SerializedName("count")
    public Integer count;
    @Expose
    @SerializedName("items")
    public Friend[] friends;
}
