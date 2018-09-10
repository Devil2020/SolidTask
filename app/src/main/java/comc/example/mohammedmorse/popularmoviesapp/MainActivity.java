package comc.example.mohammedmorse.popularmoviesapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.MainThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import comc.example.mohammedmorse.popularmoviesapp.Adapters.CustomAdapter;
import comc.example.mohammedmorse.popularmoviesapp.ContentProvider.ContentProviderContract;
import comc.example.mohammedmorse.popularmoviesapp.DataBase.DataBaseOperationImp;
import comc.example.mohammedmorse.popularmoviesapp.DataBase.DataBaseOperationInt;
import comc.example.mohammedmorse.popularmoviesapp.DataBase.MovieContract;
import comc.example.mohammedmorse.popularmoviesapp.DataBase.MovieDataBase;
import comc.example.mohammedmorse.popularmoviesapp.Menu.Action;
import comc.example.mohammedmorse.popularmoviesapp.Menu.WhichClicked;
import comc.example.mohammedmorse.popularmoviesapp.Model.URLSUtils;
import comc.example.mohammedmorse.popularmoviesapp.Model.MovieData;
import comc.example.mohammedmorse.popularmoviesapp.Model.Review;
import comc.example.mohammedmorse.popularmoviesapp.Model.Trailer;
import comc.example.mohammedmorse.popularmoviesapp.Model.UrlOperaInt;
import comc.example.mohammedmorse.popularmoviesapp.Network.HandleNetworkDataImp;
import comc.example.mohammedmorse.popularmoviesapp.Network.HandleNetworkDataInt;
import comc.example.mohammedmorse.popularmoviesapp.Network.NetworkImp;
import comc.example.mohammedmorse.popularmoviesapp.Network.NetworkInterface;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
     public UrlOperaInt uri;
     Pair<String,String > FinalUrl=null;
     boolean ifNotConnect=false;
     ProgressDialog dialog;
     ArrayList<MovieData> myData;
     CustomAdapter adapter;
    RecyclerView.LayoutManager manager;
    ImageView imageView;
        WhichClicked whichClicked;
        Action action;
    RequestQueue requestQueue;
    Parcelable State;
    Observer<ArrayList<MovieData>> observer;
    MovieData data;
    DataBaseOperationInt dataBaseOperationInt;
     MovieDataBase dataBase;
    RecyclerView MyrecyclerView;
    FloatingActionButton button;
    NetworkImp imp;
    boolean networkAvailability;
    NetworkInterface networkInterface;
    HandleNetworkDataInt handleNetworkDataInt;
    boolean IamVisitFavPage=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Movies");
        imp=new NetworkImp(this);
        dataBaseOperationInt=new DataBaseOperationImp();
        dialog=new ProgressDialog(this);
        dataBase=new MovieDataBase(this);
        uri=new URLSUtils(this);
        handleNetworkDataInt=new HandleNetworkDataImp();
        imageView=findViewById(R.id.myImage);
        button=findViewById(R.id.floatingImage);
        myData=new ArrayList<MovieData>();
        networkInterface=new NetworkImp(this);
        MyrecyclerView=findViewById(R.id.MyrecyclerList);
        manager=new GridLayoutManager(this,2);
        adapter=new CustomAdapter(this,myData);
        requestQueue=Volley.newRequestQueue(this);
        MyrecyclerView.setAdapter(adapter);
        State=new Bundle();
        MyrecyclerView.setLayoutManager(manager);
        whichClicked=new WhichClicked(this,myData,adapter,IamVisitFavPage);
        SharedPreferences preferences= android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(this);
        preferences.registerOnSharedPreferenceChangeListener(this);
        networkAvailability=imp.CheckNetwork();
        if(networkAvailability==false){
            ShowAlerDialog();
            ifNotConnect=true;
        }
        else {
            FinalUrl=uri.GetFinalUrl();
            myData=networkInterface.onGetDataFromNetwork(FinalUrl.second,requestQueue , adapter,myData,data);
             adapter.notifyDataSetChanged();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(ifNotConnect==true){
            button.setVisibility(View.VISIBLE);
        }
        if(IamVisitFavPage==true){
            ArrayList<MovieData> data =new ArrayList<>();

            try {
                Cursor cursor=getContentResolver().query(ContentProviderContract.FinalUrl,null,
                        null,null,null,null);
                data=dataBaseOperationInt.OperationInCursor(cursor);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            myData.clear();
            myData.addAll(data);
            adapter.notifyDataSetChanged();
        }
        Log.e("Cycle", "onResume: ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.e("Cycle", "onCreateOptionsMenu: ");
        getMenuInflater().inflate(R.menu.settingmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        action=whichClicked.whichClicked(item);
        try {
            action.doIt();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Cycle", "onOptionsItemSelected: " );
        return super.onOptionsItemSelected(item);
    }
    public void ShowAlerDialog(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Network");
        builder.setIcon(R.drawable.ic_network_check_black_24dp);
        builder.setMessage("Please Check the Wifi or Data , if They aren`t workink Please Turn Wifi or Mobile Data ON .");
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(android.provider.Settings.ACTION_SETTINGS);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            finish();
            }
        });
        builder.show();
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
   String url;
     if(imageView.getVisibility()==View.VISIBLE) {
         imageView.setVisibility(View.INVISIBLE);
         MyrecyclerView.setVisibility(View.VISIBLE);
     }
        //Toast.makeText(MainActivity.this, "This Shared Preference Switched ", Toast.LENGTH_LONG).show();
        String movieChoise=sharedPreferences.getString("movies","Populer");
        if(movieChoise.equals("Populer")){
          url=URLSUtils.PopularUrl;
        }
        else {
            url=URLSUtils.TopRated;
        }
        IamVisitFavPage=false;
        //GetMoviesDetails(FinalUrl);
        networkInterface.onGetDataFromNetwork(url,requestQueue,adapter,myData,myData.get(0));
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        State=manager.onSaveInstanceState();
        outState.putParcelable("Position",State);
        outState.putBoolean("IfFavourite",IamVisitFavPage);
        Log.e("LifeCycle","OnSaveInstance "+IamVisitFavPage);
        super.onSaveInstanceState(outState);

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null) {
            State = savedInstanceState.getParcelable("Position");
            IamVisitFavPage=savedInstanceState.getBoolean("IfFavourite");
            Log.e("LifeCycle","OnRestoreInstance "+IamVisitFavPage);
        }

    }
    @Override
    protected void onDestroy() {
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
        Log.e("Cycle", "onDestroy: ");
        super.onDestroy();
    }
    public void Refresh(View view) {
        FinalUrl= uri.GetFinalUrl();
        //GetMoviesDetails(FinalUrl);
        networkInterface.onGetDataFromNetwork(FinalUrl.second,requestQueue,adapter,myData,myData.get(0));
        button.setVisibility(View.INVISIBLE);
        ifNotConnect=false;
    }
}
