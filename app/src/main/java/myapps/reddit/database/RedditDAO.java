package myapps.reddit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import myapps.reddit.model.Post;

/**
 * Created by user on 8/21/2015.
 */
public class RedditDAO { //Database Access object
    /*Singleton pattern*/
    private static RedditDAO sInstance = null;

    /*Get an instance of the Database Access Object*/
    public static RedditDAO getsInstance(){
        if(sInstance == null){
            sInstance = new RedditDAO();
        }
        return  sInstance;
    }

    public boolean storePosts(Context context, List<Post> postList){
        List<Post> storedPosts = RedditDAO.getsInstance().getPostsFromDB(context);

        try{
            SQLiteDatabase db = new DBOpenHelper(context).getWritableDatabase();

        //            db.beginTransaction();

            for (Post post : postList) {
                boolean isNotInDB = false;
                for(Post postStored : storedPosts){
                    if (post.getTitle().equals(postStored.getTitle())){
                        isNotInDB = true;
                    }
                }
                if(!isNotInDB) { //If not internet access, it app will DISPLAY previously stored information in the database.
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseContract.PostTable.TITLE, post.getTitle());
                    cv.put(DatabaseContract.PostTable.LINK, post.getPermaLink());
                    cv.put(DatabaseContract.PostTable.IMAGELINK, post.getThumbnail());

                    db.insert(DatabaseContract.PostTable.TABLE_NAME, null, cv);

                }

            }



        //            db.setTransactionSuccessful();    Saves us computation.
        //            db.endTransaction();

            db.close();

        }catch(Exception e){
            return false;
        }

        return true;
    }

    public List<Post> getPostsFromDB(Context context){
        SQLiteDatabase db = new DBOpenHelper(context).getWritableDatabase();

        Cursor cursor = db.query(DatabaseContract.PostTable.TABLE_NAME,null,null,null,null,null,null);

        cursor.moveToFirst();

        List<Post> postList = new ArrayList<>();

        while(cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex(DatabaseContract.PostTable.TITLE));
            String link = cursor.getString(cursor.getColumnIndex(DatabaseContract.PostTable.LINK));
            String imageLink = cursor.getString(cursor.getColumnIndex(DatabaseContract.PostTable.IMAGELINK));

            Post post = new Post(link,imageLink,title);
            postList.add(post);
        }
        cursor.close();
        db.close();

        return postList;
    }
}
