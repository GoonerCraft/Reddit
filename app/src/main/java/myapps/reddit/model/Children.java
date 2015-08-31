package myapps.reddit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/21/2015.
 */
public class Children {
    @SerializedName("data")
    private Post post;

    public Post getPost(){
        return post;
    }
}
