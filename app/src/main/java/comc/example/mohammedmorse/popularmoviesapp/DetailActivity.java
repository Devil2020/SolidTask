package comc.example.mohammedmorse.popularmoviesapp;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import comc.example.mohammedmorse.popularmoviesapp.Adapters.MyCustomCallBackForTrailer;
import comc.example.mohammedmorse.popularmoviesapp.Adapters.ReviewAdapter;
import comc.example.mohammedmorse.popularmoviesapp.Adapters.TrailerAdapter;
import comc.example.mohammedmorse.popularmoviesapp.ContentProvider.ContentProviderContract;
import comc.example.mohammedmorse.popularmoviesapp.DataBase.DataBaseOperationImp;
import comc.example.mohammedmorse.popularmoviesapp.DataBase.DataBaseOperationInt;
import comc.example.mohammedmorse.popularmoviesapp.DataBase.MovieContract;
import comc.example.mohammedmorse.popularmoviesapp.DataBase.MovieDataBase;
import comc.example.mohammedmorse.popularmoviesapp.Model.URLSUtils;
import comc.example.mohammedmorse.popularmoviesapp.Model.MovieData;
import comc.example.mohammedmorse.popularmoviesapp.Model.Review;
import comc.example.mohammedmorse.popularmoviesapp.Model.Trailer;
import comc.example.mohammedmorse.popularmoviesapp.Network.NetworkImp;
import comc.example.mohammedmorse.popularmoviesapp.Network.NetworkInterface;
import comc.example.mohammedmorse.popularmoviesapp.Network.ReviewImp;
import comc.example.mohammedmorse.popularmoviesapp.Network.TrailerImp;
import comc.example.mohammedmorse.popularmoviesapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity implements MyCustomCallBackForTrailer{
   public URLSUtils urls;
    public ProgressDialog dialog;
    public ArrayList<Review> myReviews;
    public ArrayList<Trailer> myTrailer;
    public TrailerAdapter trailerAdapter;
    public String MovieName;
    public DataBaseOperationInt dataBaseOperationInt;
    //public boolean ifExist;
    public JSONArray array1;
    public Parcelable StateR;
    public NetworkInterface networkInterface1;
    public NetworkInterface networkInterface2;
    public Parcelable StateT;
    public JSONArray array2;
    public int X=-1;
    Cursor ifExist=null;
    public int Y=-1;
    ArrayList<Review>reviewData;
    public ReviewAdapter reviewAdapter;
    public RecyclerView.LayoutManager layoutManager;
    public RecyclerView.LayoutManager layoutManagerc;
    public RequestQueue queue ;
    MovieDataBase dataBase;
    MovieData data;
    public ActivityDetailBinding detailBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailBinding= DataBindingUtil.setContentView(this,R.layout.activity_detail);
        dialog=new ProgressDialog(this);
          urls=new URLSUtils(this);
          data=new MovieData();
          myReviews=new ArrayList<>();
          myTrailer=new ArrayList<>();
          array1=new JSONArray();
        array2=new JSONArray();
        StateR=new Bundle();
        StateT=new Bundle();
        reviewData=new ArrayList<>();
          dataBase=new MovieDataBase(this);
        trailerAdapter=new TrailerAdapter(this,this,myTrailer);
        reviewAdapter = new ReviewAdapter(this,myReviews);
        layoutManager=new GridLayoutManager(this,1);
        layoutManagerc=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        detailBinding.Review.setLayoutManager(layoutManager);
        detailBinding.Review.setAdapter(reviewAdapter);
        detailBinding.Trailer.setLayoutManager(layoutManagerc);
        detailBinding.Trailer.setAdapter(trailerAdapter);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        detailBinding.Review.addItemDecoration(dividerItemDecoration);
        DividerItemDecoration dividerItemDecorationCopy=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        detailBinding.Trailer.addItemDecoration(dividerItemDecorationCopy);
        queue= Volley.newRequestQueue(this);
         SetData();
        Log.i("Detail", "onCreate: Detail Activity");
    }
    public void SetData(){
        Intent intent=getIntent();
        data=new MovieData();
        dataBaseOperationInt=new DataBaseOperationImp();
        int Position=intent.getIntExtra("Position",0);
        data.setId(intent.getIntExtra("id",0));
        data.setName(intent.getStringExtra("Name"));
        setTitle(data.getName());
        MovieName=data.getName();
        data.setOverview(intent.getStringExtra("Overview"));
        data.setRate(intent.getIntExtra("Rate",0));
        data.setReleaseDate(intent.getStringExtra("Date"));
        String background=intent.getStringExtra("Background");
        String poster=intent.getStringExtra("Poster");
        data.setPosterMovie(poster);
        data.setBackgroundMovie(background);
        //ifExist=dataBase.IfExistInDataBaseOrNot(MovieName);
        ifExist=getContentResolver().query(Uri.parse(ContentProviderContract.FinalUrl+"/"+MovieContract.name),null,
                null,new String[]{MovieName},null);
        if(ifExist.getCount()!=0){
            detailBinding.unorFavourite.setImageResource(R.drawable.ic_favorite);
        }
        else{
            detailBinding.unorFavourite.setImageResource(R.drawable.unfavourite);
        }
       //LoadReviewFirst
        urls.setId(data.getId());
        urls.setReviewUrl(String.valueOf(data .getId()));
        networkInterface1=new ReviewImp();
       networkInterface1.onGetDataFromNetwork(urls.getReviewUrl(),queue,reviewAdapter,myReviews , data);

        urls.setId(data.getId());
        urls.setTrailerUrl(String.valueOf(data .getId()));
        networkInterface2=new TrailerImp();
        networkInterface2.onGetDataFromNetwork(urls.getTrailerUrl(),queue,trailerAdapter,myTrailer,data)  ;

        Picasso.with(this).load(background).into(detailBinding.MovieBackroundImage);
        detailBinding.MovieNameDetail.setText(data.getName());
        detailBinding.MovieRateDetail.setText(String .valueOf(data.getRate())+"/10");
        detailBinding.MovieOverviewDetail.setText(data.getOverview());
        detailBinding.MovieDateDetail.setText(data.getReleaseDate());

}
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        StateR=layoutManager.onSaveInstanceState();
        outState.putParcelable("ReviewRecyclerView",StateR);
        StateT=layoutManagerc.onSaveInstanceState();
        outState.putParcelable("TrailerRecyclerView",StateT);
        X=detailBinding.ScrollView.getScrollX();
        Y=detailBinding.ScrollView.getScrollY();
        outState.putInt("X",X);
        outState.putInt("Y",Y);
        super.onSaveInstanceState(outState);
        Log.i("Detail", "onSaveInstanceState: Detail ActivityX "+X +" Y "+Y);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            StateR = savedInstanceState.getParcelable("ReviewRecyclerView");
            StateT = savedInstanceState.getParcelable("TrailerRecyclerView");
            X = savedInstanceState.getInt("X");
            Y = savedInstanceState.getInt("Y");
            Log.i("Detail", "onRestoreInstanceState: Detail Activity X "+X +" Y "+Y);
        }
    }
    public void MakeitFavourite(View view) throws JSONException {
       // If It`s in DataBase So >>>>>>>>>>>>>> Un Favourite
     if(ifExist.getCount()!=0){
         //Delet
        getContentResolver().delete(Uri.parse(ContentProviderContract.FinalUrl+"/"+ MovieContract.name),null, new String[]{String.valueOf(data.getName())});
         detailBinding.unorFavourite.setImageResource(R.drawable.unfavourite);
         Toast.makeText(this, "Delet Operation has Done .", Toast.LENGTH_SHORT).show();
     }
     else{
         reviewData=data.getReviewData();
         int Size1=reviewData.size();
         if(Size1==0){
             ArrayList<Review> NullObject=new ArrayList<>();
             NullObject.add(new Review("Nothing","Nothing"));
             data.setReviewData(NullObject);
         }
         if(data.getTrailerData().size()==0){
             ArrayList<Trailer> NullObject=new ArrayList<>();
             NullObject.add(new Trailer("Nothing","Nothing","Nothing"));
             data.setTrailerData(NullObject);
         }
         ContentValues values=new ContentValues();
         values.put(MovieContract.id,data.getId());
         values.put(MovieContract.name,data.getName());
         values.put(MovieContract.postermovie,data.getPosterMovie());
         values.put(MovieContract.backgroundmovie,data.getBackgroundMovie());
         values.put(MovieContract.releasedate,data.getReleaseDate());
         values.put(MovieContract.rate,data.getRate());
         values.put(MovieContract.overview,data.getOverview());
         array1=dataBaseOperationInt.ReturnReviewsAsJson(data);
         String MyReview=array1.toString();
         array2=dataBaseOperationInt.ReturnTrailersAsJson(data);
         String MyTrailer=array2.toString();
         values.put(MovieContract.reviewdata,MyReview);
         values.put(MovieContract.trailerdata,MyTrailer);
         getContentResolver().insert(ContentProviderContract.FinalUrl,values);
         detailBinding.unorFavourite.setImageResource(R.drawable.ic_favorite);
         //Insert
         Toast.makeText(this, "Insert Operation has Done .", Toast.LENGTH_SHORT).show();
     }
       //Else Favourite It
    }
    @Override
    public void OpenYoutubeListener(String data) {
       urls.setYoutubeTrailer(data);
       String Uri=urls.getYoutubeTrailer();
       Intent OPenYoutube=new Intent(Intent.ACTION_VIEW);
       OPenYoutube.setData(android.net.Uri.parse(Uri));
       if(OPenYoutube.resolveActivity(getPackageManager())==null){
          Intent Chooser=Intent.createChooser(OPenYoutube,"Choose an App to open the Video ");
          startActivity(Chooser);
       }
       else{
       startActivity(OPenYoutube);
           }
   }
}
