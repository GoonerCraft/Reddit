package myapps.reddit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/21/2015.
 */
public class ChildrenArray {
    @SerializedName("children")
    private List<Children> childrenList;

    public List<Children> getChildrenList(){
        return childrenList;
    }
}
