package comc.example.mohammedmorse.popularmoviesapp.Network;

import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;

import comc.example.mohammedmorse.popularmoviesapp.Adapters.CustomAdapter;
import comc.example.mohammedmorse.popularmoviesapp.Model.MovieData;
import io.reactivex.Observable;

/**
 * Created by Mohammed Morse on 06/09/2018.
 */

public interface NetworkInterface {
    public ArrayList onGetDataFromNetwork(String Url,
                                                     RequestQueue queue, RecyclerView.Adapter adapter ,
                                                     ArrayList list , MovieData data);
}
