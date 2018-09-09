package comc.example.mohammedmorse.popularmoviesapp.ContentProvider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import comc.example.mohammedmorse.popularmoviesapp.DataBase.MovieContract;
import comc.example.mohammedmorse.popularmoviesapp.DataBase.MovieDataBase;

/**
 * Created by Mohammed Morse on 06/09/2018.
 */

public class MultipleTaskOperation implements ContentProviderOperations {
    @Override
    public Cursor Select(Cursor myCursor, MovieDataBase dataBase, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        myCursor=dataBase.getReadableDatabase().query(MovieContract.TableName,
                new String[]{MovieContract.id,MovieContract.name,MovieContract.postermovie,MovieContract.backgroundmovie,
                        MovieContract.releasedate,MovieContract.rate,MovieContract.overview,MovieContract.reviewdata,MovieContract.trailerdata}
                ,null,null,null,null,null);
        return myCursor;
    }

    @Override
    public void delete(MovieDataBase dataBase, Uri uri, String selection, String[] selectionArgs) {
        dataBase.getWritableDatabase().delete(MovieContract.TableName,null, null);
        dataBase.close();
    }

    @Override
    public void insert(MovieDataBase dataBase, Uri uri, ContentValues values) {
        dataBase.getWritableDatabase().insert(MovieContract.TableName,null,values);
        dataBase.close();
    }
}
