package myapps.reddit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/21/2015.
 */
public class Post {


    @SerializedName("permalink")
    private String permaLink;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("title")
    private String title;

    public Post(String permaLink, String thumbnail, String title) {
        this.permaLink = permaLink;
        this.thumbnail = thumbnail;
        this.title = title;
    }


    public String getTitle(){
        return title;
    }

    public String getPermaLink() {
        return permaLink;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
