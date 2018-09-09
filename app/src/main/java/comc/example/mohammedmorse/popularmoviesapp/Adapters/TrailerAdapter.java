package comc.example.mohammedmorse.popularmoviesapp.Adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import comc.example.mohammedmorse.popularmoviesapp.Model.Trailer;
import comc.example.mohammedmorse.popularmoviesapp.R;

/**
 * Created by Mohammed Morse on 30/06/2018.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerHolder>{
    private Context context;
    private ArrayList<Trailer>trailerData;
    private MyCustomCallBackForTrailer listener;
    public TrailerAdapter(MyCustomCallBackForTrailer listener,Context context,ArrayList<Trailer> trailerData){
        this.context=context;
        this.trailerData=trailerData;
        this.listener=listener;
    }
    @Override
    public TrailerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View myTrailers=LayoutInflater.from(context).inflate(R.layout.traileritem,parent,false);
      TrailerHolder trailerHolder=new TrailerHolder(myTrailers);
        return trailerHolder;
    }

    @Override
    public void onBindViewHolder(TrailerHolder holder, final int position) {
        if(trailerData.size()==0){
            holder.YoutubeLogo.setVisibility(View.INVISIBLE);
            holder.FilmName.setText("There are Trailers For this Film ");
        }
        else{
                if(holder.YoutubeLogo.getVisibility()==View.INVISIBLE){
                    holder.YoutubeLogo.setVisibility(View.VISIBLE);
                }
            else{
                  holder.FilmName.setText(trailerData.get(position).getName());
                  holder.FilmSize.setText(trailerData.get(position).getSize());
                  holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          listener.OpenYoutubeListener(trailerData.get(position).getKey());
                      }
                  });
            }
        }
    }

    @Override
    public int getItemCount() {
        return trailerData.size();
    }

    public class TrailerHolder extends RecyclerView.ViewHolder{
        private ImageView YoutubeLogo;
        private TextView FilmName , FilmSize;
        private ConstraintLayout constraintLayout;
        private TrailerHolder(View itemView) {
            super(itemView);
          YoutubeLogo=itemView.findViewById(R.id.TrailerImage);
          FilmName=itemView.findViewById(R.id.MovieNameTrailer);
          FilmSize=itemView.findViewById(R.id.MovieSizeTrailer);
          constraintLayout=itemView.findViewById(R.id.ConstrainTrailer);
        }
    }
}
