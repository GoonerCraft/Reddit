package myapps.reddit;

import android.app.Application;

import myapps.reddit.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by user on 8/22/2015.
 */
public class RedditApplication extends Application {

    @Override
    public void onCreate() { //Loading the font calligraphy library.
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Roboto-Bold.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }
}
