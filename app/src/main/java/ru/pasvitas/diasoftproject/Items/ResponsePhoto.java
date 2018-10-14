package ru.pasvitas.diasoftproject.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePhoto {
    @SerializedName("response")
    @Expose
    public PhotoResponse photoResponse;
}
