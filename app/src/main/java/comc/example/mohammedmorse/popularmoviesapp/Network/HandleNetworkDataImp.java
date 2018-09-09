package comc.example.mohammedmorse.popularmoviesapp.Network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import comc.example.mohammedmorse.popularmoviesapp.Model.MovieData;
import comc.example.mohammedmorse.popularmoviesapp.Model.Review;
import comc.example.mohammedmorse.popularmoviesapp.Model.Trailer;
import io.reactivex.Observable;

/**
 * Created by Mohammed Morse on 07/09/2018.
 */

public class HandleNetworkDataImp implements HandleNetworkDataInt{
    ArrayList<MovieData> list;
    // When Request to get data , we have a JSONArr , So we Seperate our logic to acheieve s principle
    @Override
    public ArrayList<MovieData> ConvertToArrayListMoviesDetail(JSONArray array) throws JSONException {
        list=new ArrayList<>();
        int size=array.length();
        for(int i=0;i<size;i++){
            MovieData data=new MovieData();
            JSONObject object=array.getJSONObject(i);
            data.setId(object.getInt("id"));
            data.setName(object.getString("title"));
            data.setOverview(object.getString("overview"));
            data.setRate(object.getInt("vote_average"));
            data.setReleaseDate(object.getString("release_date"));
            data.setPosterMovie(object.getString("poster_path"));
            data.setBackgroundMovie(object.getString("backdrop_path"));
            //   Toast.makeText(MainActivity.this, "Name"+data.getName(), Toast.LENGTH_LONG).show();
            list.add(data);
        }
       // Observable<ArrayList<MovieData>> observable=Observable.fromArray(list);
        return list;
    }
    @Override
    public ArrayList<Review> ConvertToArrayListMoviesReview(JSONArray array) throws JSONException {
        Review Objectdata;
        ArrayList<Review> reviews=new ArrayList<>();
        for(int i=0;i<array.length();i++) {
            Objectdata=new Review();
            JSONObject object=array.getJSONObject(i);
            Objectdata.setName(object.getString("author"));
            Objectdata.setReview(object.getString("content"));
            reviews.add(Objectdata);
        }
        //Observable<ArrayList<Review>> observable=Observable.fromArray(reviews);
        return reviews;
    }
    @Override
    public ArrayList<Trailer> ConvertToArrayListMoviesTrailer(JSONArray array) throws JSONException {
        ArrayList<Trailer> myTrailer=new ArrayList<>();
        for(int i=0;i<array.length();i++) {
            Trailer Objectdata=new Trailer();
            JSONObject object=array.getJSONObject(i);
            Objectdata.setName(object.getString("name"));
            Objectdata.setKey(object.getString("key"));
            Objectdata.setSize(String.valueOf(object.getInt("size")));
            myTrailer.add(Objectdata);
        }
        //Observable<ArrayList<Trailer>> observable=Observable.fromArray(myTrailer);
        return myTrailer;
    }
}
