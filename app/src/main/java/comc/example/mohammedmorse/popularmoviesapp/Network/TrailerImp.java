package comc.example.mohammedmorse.popularmoviesapp.Network;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import comc.example.mohammedmorse.popularmoviesapp.Model.MovieData;
import comc.example.mohammedmorse.popularmoviesapp.Model.Review;
import comc.example.mohammedmorse.popularmoviesapp.Model.Trailer;
import io.reactivex.Observable;

/**
 * Created by Mohammed Morse on 09/09/2018.
 */

public class TrailerImp implements NetworkInterface{

    HandleNetworkDataInt handleNetworkDataInt;
    ArrayList<Trailer> list;
   public TrailerImp(){
       list=new ArrayList<>();
       handleNetworkDataInt=new HandleNetworkDataImp();
   }
    @Override
    public ArrayList onGetDataFromNetwork
            (String Url, RequestQueue queue, final RecyclerView.Adapter adapter, final ArrayList
                    mylist, final MovieData data)
    {
        list=new ArrayList<>();
        list=mylist;
        mylist.clear();
        final JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            list=handleNetworkDataInt.ConvertToArrayListMoviesTrailer(jsonArray);
                            mylist.addAll(list);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        data.setTrailerData(list);
                        adapter.notifyDataSetChanged();
                        Log.i("Morse", "onResponse: "+list.size());
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(request);
        return list;
    }
}
