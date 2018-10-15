package ru.pasvitas.diasoftproject.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoSizes {
    @Expose
    @SerializedName("type")
    private String type;

    @Expose
    @SerializedName("url")
    private String url;

    @Expose
    @SerializedName("width")
    private Integer width;

    @Expose
    @SerializedName("height")
    private Integer height;

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }
}
