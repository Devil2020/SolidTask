package comc.example.mohammedmorse.popularmoviesapp.DataBase;

import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import comc.example.mohammedmorse.popularmoviesapp.Model.MovieData;
import comc.example.mohammedmorse.popularmoviesapp.Model.Review;
import comc.example.mohammedmorse.popularmoviesapp.Model.Trailer;

/**
 * Created by Mohammed Morse on 08/09/2018.
 */

public interface DataBaseOperationInt {
    // When Select data from Cursor , we should convert it to ArrList
    public ArrayList<MovieData> OperationInCursor(Cursor cursor) throws JSONException;
   // i save reviews and trailers in database as String , we should convert it
    public ArrayList<Review> ReturnReviewsAsArrayList(String data) throws JSONException;
    // it also as Review
    public ArrayList<Trailer> ReturnTrailerAsArrayList(String data) throws JSONException;
    //Convert ReviewData to JSONARR to Convert to String
    public JSONArray ReturnReviewsAsJson(MovieData data) throws JSONException ;
    // it also as Review
    public JSONArray ReturnTrailersAsJson(MovieData data) throws JSONException ;
}
