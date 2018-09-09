package comc.example.mohammedmorse.popularmoviesapp.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import java.net.*;
import java.util.ArrayList;
import java.util.Observable;

import comc.example.mohammedmorse.popularmoviesapp.Adapters.CustomAdapter;
import comc.example.mohammedmorse.popularmoviesapp.Model.MovieData;

/**
 * Created by Mohammed Morse on 06/09/2018.
 */

public class NetworkImp implements NetworkInterface {
    JSONArray jsonArray;
    HandleNetworkDataInt handleNetworkDataInt;
    ArrayList<MovieData> list;
    Context context;
    public NetworkImp(Context context){
        handleNetworkDataInt=new HandleNetworkDataImp();
        this.context=context;
        list=new ArrayList<>();
    }

    public boolean CheckNetwork() {
            boolean ifConnected;
            ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info=manager.getActiveNetworkInfo();
            if(info!=null &&info.isConnected()){
                ifConnected=true;
            }
            else
                ifConnected=false;

            return ifConnected;
    }
    @Override
    public ArrayList onGetDataFromNetwork
            (String Url, RequestQueue queue, final RecyclerView.Adapter adapter, final ArrayList
                    mylist , MovieData data) {
           list=mylist;

           mylist.clear();
        final JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            jsonArray = response.getJSONArray("results");
                            list=handleNetworkDataInt.ConvertToArrayListMoviesDetail(jsonArray);
                            mylist.addAll(list);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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