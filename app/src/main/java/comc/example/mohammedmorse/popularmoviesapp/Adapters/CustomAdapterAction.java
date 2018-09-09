package comc.example.mohammedmorse.popularmoviesapp.Adapters;

import android.content.Context;
import android.content.Intent;

import comc.example.mohammedmorse.popularmoviesapp.DetailActivity;
import comc.example.mohammedmorse.popularmoviesapp.Model.MovieData;

/**
 * Created by Mohammed Morse on 06/09/2018.
 */

public class CustomAdapterAction {
    static void OpenActivty(MovieData myData, Context context , int position){
        Intent intent=new Intent(context,DetailActivity.class);
        intent.putExtra("Position",position);
        intent.putExtra("Name",myData.getName());
        intent.putExtra("id",myData.getId());
        intent.putExtra("Date",myData.getReleaseDate());
        intent.putExtra("Rate",myData.getRate());
        intent.putExtra("Overview",myData.getOverview());
        intent.putExtra("Background",myData.getBackgroundMovie());
        intent.putExtra("Poster",myData.getPosterMovie());
        context.startActivity(intent);
    }
}
