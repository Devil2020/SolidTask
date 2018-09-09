package comc.example.mohammedmorse.popularmoviesapp.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;
import android.view.View;

/**
 * Created by Mohammed Morse on 30/06/2018.
 */

public class URLSUtils implements UrlOperaInt{
    public static  String AuthRev="https://api.themoviedb.org/3/movie/";
    public static  String PathRev="/reviews?api_key=12ae0210d107863fd1d89b1e2ee1f26a&language=en-US";
    public static  String PathTra="/videos?api_key=12ae0210d107863fd1d89b1e2ee1f26a&language=en-US";
    private Context context;
    public URLSUtils(Context context){
        this.context=context;
    }

    public  String getPopularUrl() {
        return PopularUrl;
    }

    public  String getTopRated() {
        return TopRated;
    }

    public String getYoutubeTrailer() {
        return YoutubeTrailer;
    }

    public void setYoutubeTrailer(String key) {
        YoutubeTrailer += key;
    }

    private String YoutubeTrailer="https://www.youtube.com/watch?v=";
    public static String PopularUrl="http://api.themoviedb.org/3/movie/popular?api_key=12ae0210d107863fd1d89b1e2ee1f26a";
    public static   String TopRated="http://api.themoviedb.org/3/movie/top_rated?api_key=12ae0210d107863fd1d89b1e2ee1f26a";
   public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReviewUrl() {
        return ReviewUrl;
    }

    public void setReviewUrl(String reviewUrl) {
        this.ReviewUrl=AuthRev+id+PathRev;
    }

    public String getTrailerUrl() {
        return TrailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.TrailerUrl = AuthRev+id+PathTra;
    }

    public String ReviewUrl;
   public String TrailerUrl;

    @Override
    public Pair<String,String> GetFinalUrl() {
        SharedPreferences preferences= android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(context);
        String movieChoise=preferences.getString("movies","Populer");
        Pair<String, String> Url=null;
        if(movieChoise.equals("Populer")){
            Url=new Pair<>("Populer",PopularUrl);

        }
        else if(movieChoise.equals("Top Rated")){
            Url=new Pair<>("Top Rated",TopRated);
        }

        return Url;
    }
}
