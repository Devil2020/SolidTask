package comc.example.mohammedmorse.popularmoviesapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import comc.example.mohammedmorse.popularmoviesapp.DetailActivity;
import comc.example.mohammedmorse.popularmoviesapp.Model.MovieData;
import comc.example.mohammedmorse.popularmoviesapp.R;

/**
 * Created by Mohammed Morse on 16/06/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder> {
    private Context context;
    private ArrayList<MovieData> myData;
    public CustomAdapter(Context context, ArrayList<MovieData> myData){
        this.context=context;
        this.myData=myData;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.main_activity_recycler_view_item,parent,false);
        Holder holder=new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
       Picasso.with(context).load(myData.get(position).getPosterMovie()).into(holder.MoviePoster);
       // holder.MoviePoster.setText(myData.get(position).getName());
        holder.myLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               CustomAdapterAction.OpenActivty(myData.get(position),context,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myData.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        private ImageView MoviePoster;
        private LinearLayout myLayout;
        private Holder(View itemView) {
            super(itemView);
        MoviePoster=itemView.findViewById(R.id.MoviePosterImage);
        myLayout=itemView.findViewById(R.id.constrain);
        }
    }
}
