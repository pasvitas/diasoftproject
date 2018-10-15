package ru.pasvitas.diasoftproject.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseFriend {
    @SerializedName("response")
    @Expose
    public FriendResponse friendResponse;
}
