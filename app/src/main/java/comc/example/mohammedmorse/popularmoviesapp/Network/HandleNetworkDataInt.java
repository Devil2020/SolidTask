package comc.example.mohammedmorse.popularmoviesapp.Network;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import comc.example.mohammedmorse.popularmoviesapp.Model.MovieData;
import comc.example.mohammedmorse.popularmoviesapp.Model.Review;
import comc.example.mohammedmorse.popularmoviesapp.Model.Trailer;

/**
 * Created by Mohammed Morse on 07/09/2018.
 */

public interface HandleNetworkDataInt {
    public ArrayList<MovieData> ConvertToArrayListMoviesDetail(JSONArray array) throws JSONException;
    public ArrayList<Review> ConvertToArrayListMoviesReview(JSONArray array) throws JSONException;
    public ArrayList<Trailer> ConvertToArrayListMoviesTrailer(JSONArray array) throws JSONException;
}
