package ru.pasvitas.diasoftproject.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoResponse {
    @Expose
    @SerializedName("count")
    Integer count;

    @Expose
    @SerializedName("items")
    Photo[] photos;

    public Integer getCount() {
        return count;
    }

    public Photo[] getPhotos() {
        return photos;
    }
}
