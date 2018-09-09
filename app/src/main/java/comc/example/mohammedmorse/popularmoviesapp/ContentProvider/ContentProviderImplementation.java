package comc.example.mohammedmorse.popularmoviesapp.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import comc.example.mohammedmorse.popularmoviesapp.DataBase.MovieContract;
import comc.example.mohammedmorse.popularmoviesapp.DataBase.MovieDataBase;

/**
 * Created by Mohammed Morse on 06/07/2018.
 */

public class ContentProviderImplementation extends ContentProvider {
    public MovieDataBase dataBase;
   public UriMatcher Urimatcher;
   public Matcher matcher;
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        ContentProviderOperations operations=matcher.MakeMatch(uri);
         Delet(operations,uri,selection,selectionArgs);
        return 0;
    }
    public void Delet(ContentProviderOperations operations,Uri uri,String selection,String[] selectionArgs){
        operations.delete(dataBase,uri,selection,selectionArgs);
    }
    @Override
    public boolean onCreate() {
        dataBase=new MovieDataBase(getContext());
        matcher=new Matcher();
        Urimatcher=matcher.InitMatch();
        return true;
    }
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        ContentProviderOperations operations=matcher.MakeMatch(uri);
       Cursor myCursor=null;
      myCursor= operations.Select(myCursor,dataBase,uri,projection,selection,selectionArgs,sortOrder);
        return myCursor;
    }
    public Cursor Select(ContentProviderOperations operations,Cursor myCursor, MovieDataBase dataBase, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        myCursor=operations.Select(myCursor,dataBase,uri,projection,selection,selectionArgs,sortOrder);
    return myCursor;
    }
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
      ContentProviderOperations operations=matcher.MakeMatch(uri);
       Insert(operations,dataBase,uri,values);
        return null;
    }
    public void Insert(ContentProviderOperations operations,MovieDataBase dataBase,Uri uri,ContentValues values){
              operations.insert(dataBase,uri,values);
    }
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
