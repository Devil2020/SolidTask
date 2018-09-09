package comc.example.mohammedmorse.popularmoviesapp.Adapters;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import comc.example.mohammedmorse.popularmoviesapp.Model.Review;
import comc.example.mohammedmorse.popularmoviesapp.R;

/**
 * Created by Mohammed Morse on 30/06/2018.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.HolderReview>  {
    private Context context;
    private ArrayList<Review> reviewData;
     public ReviewAdapter(Context context , ArrayList<Review> reviewData){
             this.context=context;
             this.reviewData=reviewData;
     }
    @Override
    public HolderReview onCreateViewHolder(ViewGroup parent, int viewType) {
        View MyShape=LayoutInflater.from(context).inflate(R.layout.reviewitem,parent,false);
        HolderReview holderReview=new HolderReview(MyShape);
        return holderReview;
    }

    @Override
    public void onBindViewHolder(HolderReview holder, int position) {
         if(reviewData.size()==0){
             holder.AuthorPicture.setVisibility(View.INVISIBLE);
             holder.AuthorName.setText("There are Reviews about this Film");
         }
        else {
             if(holder.AuthorPicture.getVisibility()==View.INVISIBLE){
                 holder.AuthorPicture.setVisibility(View.VISIBLE);
             }
             else {
                 holder.AuthorName.setText(reviewData.get(position).getName());
                 holder.AuthorReview.setText(reviewData.get(position).getReview());
                  }
             }
         }

    @Override
    public int getItemCount() {
        return reviewData.size();
    }

    class HolderReview extends RecyclerView.ViewHolder{
        private TextView AuthorName , AuthorReview;
        private ImageView AuthorPicture;
        private HolderReview(View itemView) {
            super(itemView);
            AuthorName=itemView.findViewById(R.id.authorName);
            AuthorReview=itemView.findViewById(R.id.authorReview);
        AuthorPicture=itemView.findViewById(R.id.authorImage);
        }
    }

}
