package myapps.reddit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import java.util.List;

import myapps.reddit.database.RedditDAO;
import myapps.reddit.model.Listing;
import myapps.reddit.model.Post;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainHome extends AppCompatActivity implements RedditAdapter.MyItemClickListener{

    public final String REDDIT_URL = "https://www.reddit.com/r/all.json?limit=30";

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerListView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //Indicates which layout manager we are using. Way the elements are going to be displayed.
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        StringRequest request = new StringRequest(Request.Method.GET, REDDIT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Listing listing = new Gson().fromJson(response, Listing.class);

                List<Post> postList = listing.getPostList();

                RedditAdapter adapter = new RedditAdapter(listing.getPostList(), MainHome.this, MainHome.this); //One is context, the other is implementation of the pattern

                RedditDAO.getsInstance().storePosts(MainHome.this,postList);

                mRecyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                        List<Post> postList = RedditDAO.getsInstance().getPostsFromDB(MainHome.this);
                        RedditAdapter adapter = new RedditAdapter(postList, MainHome.this, MainHome.this); //One is context, the other is implementation of the pattern
                        mRecyclerView.setAdapter(adapter);
            }
        });

        //Retrieve our existing queue from manager and add it to the request.
        ConnectionManager.getInstance(this).add(request);
    }


    @Override
    public void OnItemClick(Post itemClicked) {
        Intent webIntent = new Intent(MainHome.this, WebActivity.class);
        webIntent.putExtra("URL", itemClicked.getPermaLink());
        startActivity(webIntent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}