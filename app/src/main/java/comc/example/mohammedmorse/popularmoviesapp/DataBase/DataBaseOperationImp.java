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

public class DataBaseOperationImp implements DataBaseOperationInt  {
    @Override
    public ArrayList<MovieData> OperationInCursor(Cursor cursor) throws JSONException {
        ArrayList<MovieData> data =new ArrayList<>();
        cursor.moveToFirst();
        MovieData modelData;
        for(int i=0;i<cursor.getCount();i++){
            modelData=new MovieData();
            modelData.setId(cursor.getInt(cursor.getColumnIndex(MovieContract.id)));
            modelData.setName(cursor.getString(cursor.getColumnIndex(MovieContract.name)));
            modelData.setPosterMovie(cursor.getString(cursor.getColumnIndex(MovieContract.postermovie)));
            modelData.setBackgroundMovie(cursor.getString(cursor.getColumnIndex(MovieContract.backgroundmovie)));
            modelData.setRate(cursor.getInt(cursor.getColumnIndex(MovieContract.rate)));
            modelData.setOverview(cursor.getString(cursor.getColumnIndex(MovieContract.overview)));
            modelData.setReleaseDate(cursor.getString(cursor.getColumnIndex(MovieContract.releasedate)));
            String Review=cursor.getString(cursor.getColumnIndex(MovieContract.reviewdata));
            String Trailer=cursor.getString(cursor.getColumnIndex(MovieContract.trailerdata));
            // ArrayList<Review> ReviewArray = new Gson().fromJson(Review, new TypeToken<ArrayList<Review>>(){}.getType());
            ArrayList<comc.example.mohammedmorse.popularmoviesapp.Model.Review> ReviewArray=ReturnReviewsAsArrayList(Review);
            modelData.setReviewData(ReviewArray);
            //ArrayList<Trailer> TrailerArray = new Gson().fromJson(Trailer, new TypeToken<ArrayList<Trailer>>(){}.getType());
            ArrayList<comc.example.mohammedmorse.popularmoviesapp.Model.Trailer> TrailerArray=ReturnTrailerAsArrayList(Trailer);
            modelData.setTrailerData(TrailerArray);
            data.add(modelData);
            cursor.moveToNext();
        }
        cursor.close();
        return data;
    }

    @Override
    public ArrayList<Review> ReturnReviewsAsArrayList(String data) throws JSONException {
        ArrayList<Review> dataArrayList=new ArrayList<>();
        JSONArray array=new JSONArray(data);
        Review redata;
        for (int i=0;i<array.length();i++){
            redata=new Review();
            JSONObject object=array.getJSONObject(i);
            redata.setName(object.getString("name"));
            redata.setReview(object.getString("review"));
            dataArrayList.add(redata);
        }
        return dataArrayList;
    }

    @Override
    public ArrayList<Trailer> ReturnTrailerAsArrayList(String data) throws JSONException {
        ArrayList<Trailer> dataArrayList=new ArrayList<>();
        JSONArray array=new JSONArray(data);
        Trailer redata;
        for (int i=0;i<array.length();i++){
            redata=new Trailer();
            JSONObject object=array.getJSONObject(i);
            redata.setName(object.getString("name"));
            redata.setKey(object.getString("key"));
            redata.setSize(object.getString("size"));
            dataArrayList.add(redata);
        }
        return dataArrayList;
    }
     @Override
    public JSONArray ReturnReviewsAsJson(MovieData data) throws JSONException{
        JSONArray myreviews = new JSONArray();
        if(data.getReviewData().size()==0){
            ArrayList<Review> list=new ArrayList<>();
            list.add(new Review("Nothing","Nothing"));
            data.setReviewData(list);

        }
        JSONObject Element;
        for (int i = 0; i < data.getReviewData().size(); i++) {
            Element = new JSONObject();
            Element.put("name", data.getReviewData().get(i).getName());
            Element.put("review", data.getReviewData().get(i).getReview());
            myreviews.put(Element);

        }
        return myreviews;
    }
     @Override
    public JSONArray ReturnTrailersAsJson(MovieData data) throws JSONException {
        if(data.getReviewData().size()==0){
            ArrayList<Trailer> list=new ArrayList<>();
            list.add(new Trailer("Nothing","Nothing","Nothing"));
            data.setTrailerData(list);
        }
        JSONArray mytrailers=new JSONArray();
        JSONObject Element;
        for (int i =0;i<data.getTrailerData().size();i++){
            Element=new JSONObject();
            Element.put("key",data.getTrailerData().get(i).getKey());
            Element.put("name",data.getTrailerData().get(i).getName());
            Element.put("size",data.getTrailerData().get(i).getSize());
            mytrailers.put(Element);
        }
        return mytrailers;
    }
}
