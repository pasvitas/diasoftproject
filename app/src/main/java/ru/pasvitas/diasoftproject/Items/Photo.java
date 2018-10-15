package ru.pasvitas.diasoftproject.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @Expose
    @SerializedName("id")
    private Integer id;

    @Expose
    @SerializedName("album_id")
    private Integer album_id;

    @Expose
    @SerializedName("owner_id")
    private Integer owner_id;

    @Expose
    @SerializedName("sizes")
    private PhotoSizes[] photoSizes;

    @Expose
    @SerializedName("text")
    private String text;

    @Expose
    @SerializedName("date")
    private Integer date;

    @Expose
    @SerializedName("post_id")
    private Integer real_offset;

    public Integer getId() {
        return id;
    }

    public Integer getAlbum_id() {
        return album_id;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public PhotoSizes[] getPhotoSizes() {
        return photoSizes;
    }

    public String getText() {
        return text;
    }

    public Integer getDate() {
        return date;
    }

    public Integer getReal_offset() {
        return real_offset;
    }

    public String getPhotoURL()
    {
        return photoSizes[photoSizes.length-1].getUrl();
    }
}
