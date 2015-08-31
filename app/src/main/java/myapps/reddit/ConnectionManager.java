package myapps.reddit;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * This method represents the singleton design pattern which is having the same object in the whole application.
 */
public class ConnectionManager {

    private static RequestQueue squeue; //static variable is part of the class. Directly accessed outside

    private static ImageLoader sImageLoader;


    public static RequestQueue getInstance(Context context){ //easy way to pass a class into this method
            if (squeue == null){
                squeue = Volley.newRequestQueue(context);
            }
        return squeue;
    }

    public static ImageLoader getsImageLoader(Context context){ //easy way to pass a class into this method(context)
        if (sImageLoader == null){
            sImageLoader = new ImageLoader(getInstance(context),new ImageLoader.ImageCache(){
                    private final LruCache<String, Bitmap>mCache = new LruCache<String,Bitmap>(30); //Image saved in cache memory.
                @Override
                public Bitmap getBitmap(String url) {
                    return mCache.get(url);
                }

                @Override
                public void putBitmap(String url, Bitmap bitmap) {
                     mCache.put(url,bitmap);
                }
            });
        }
        return sImageLoader;
    }

}
