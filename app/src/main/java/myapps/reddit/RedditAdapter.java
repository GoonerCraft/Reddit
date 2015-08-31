package myapps.reddit;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import myapps.reddit.database.RedditDAO;
import myapps.reddit.model.Post;

/**
 * This is different than a list view in a sense that we have a method that firstly creates the view, and then adds data to the view.
 * Called recycle because inflator does not start from 0, we keep recycling the view. Meaning that the data is changing, but the view containers are remaining the same.
 * In list view we were getting the elements inflating them and setting them.
 */
public class RedditAdapter extends RecyclerView.Adapter<RedditAdapter.MyViewHolder> { //Implement the interface to click listener.

    List<Post> mPostList;
    Context mContext;
    MyItemClickListener mListener;

    public RedditAdapter(List<Post> postList, Context context, MyItemClickListener listener)
    {
        mPostList = postList;
        mContext = context;
        mListener  = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rows_post, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {//Called for every element.
        Post currentPost = mPostList.get(position);
        holder.mTextViewPostName.setText(mPostList.get(position).getTitle());

        if(!TextUtils.isEmpty(currentPost.getThumbnail())) { //if we have a thumbnail, we are going to show the image.
            ((View)holder.mPostImage.getParent()).setVisibility(View.VISIBLE); //Getting the parent of the image view which is relative layout and setting it to the progress bar.
            holder.mPostImage.setImageUrl(currentPost.getThumbnail(), ConnectionManager.getsImageLoader(mContext));
        }else{
            ((View)holder.mPostImage.getParent()).setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mPostList.size(); //Method with must be implemented with adapter. 
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mTextViewPostName;
        public NetworkImageView mPostImage;

        public MyViewHolder(View itemView) { //our view holder has two views.
            super(itemView);
            mTextViewPostName = (TextView) itemView.findViewById(R.id.rowTextViewName); //
            mPostImage = (NetworkImageView) itemView.findViewById(R.id.networkImageView);
            CardView cv = (CardView) itemView.findViewById(R.id.card_view);
            cv.setPreventCornerOverlap(false);

            itemView.setOnClickListener(this); //puts the item click on the view.
        }

        @Override
        public void onClick(View v) {
            mListener.OnItemClick(mPostList.get(getPosition()));
        }
    }

    public static interface MyItemClickListener{
        public void OnItemClick(Post itemClicked);
    }

}
