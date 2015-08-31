package myapps.reddit.database;

/**
 * Created by user on 8/21/2015.
 */
public class DatabaseContract {// DB structure
    public static final String DB_NAME = "reddit_database.db";

    public abstract class PostTable{ //CAnnot be instantiated for security reasons.
        public static final String TABLE_NAME = "post_table";
        public static final String TITLE = "title";
        public static final String LINK = "link";
        public static final String IMAGELINK = "imageLink";

    }
}
