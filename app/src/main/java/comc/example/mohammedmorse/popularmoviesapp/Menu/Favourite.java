package comc.example.mohammedmorse.popularmoviesapp.Menu;

import android.content.Context;
import android.database.Cursor;

import org.json.JSONException;

import java.util.ArrayList;

import comc.example.mohammedmorse.popularmoviesapp.Adapters.CustomAdapter;
import comc.example.mohammedmorse.popularmoviesapp.ContentProvider.ContentProviderContract;
import comc.example.mohammedmorse.popularmoviesapp.DataBase.DataBaseOperationImp;
import comc.example.mohammedmorse.popularmoviesapp.DataBase.DataBaseOperationInt;
import comc.example.mohammedmorse.popularmoviesapp.Model.MovieData;

/**
 * Created by Mohammed Morse on 10/09/2018.
 */

public class Favourite implements Action {
       ArrayList<MovieData> list;
       CustomAdapter adapter;
       Context context;
       DataBaseOperationInt dataBaseOperationInt;
       boolean b;
       public Favourite(ArrayList<MovieData> list, CustomAdapter adapter, Context context , boolean b){
           this.list=list;
           this.adapter=adapter;
           this.context=context;
           this.b=b;
           dataBaseOperationInt=new DataBaseOperationImp();
       }
       @Override
    public void doIt() throws JSONException {
          ArrayList<MovieData> data= doAction();
           list.clear();
           list.addAll(data);
           adapter.notifyDataSetChanged();
    }
    public ArrayList<MovieData> doAction() throws JSONException {
           b=true;
           ArrayList<MovieData> data =new ArrayList<>();
        Cursor cursor=context.getContentResolver().query(ContentProviderContract.FinalUrl,null,null,null,null);
        data=dataBaseOperationInt.OperationInCursor(cursor);
        return data;
    }
}
