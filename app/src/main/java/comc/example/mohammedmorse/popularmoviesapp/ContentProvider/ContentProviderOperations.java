package comc.example.mohammedmorse.popularmoviesapp.ContentProvider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import comc.example.mohammedmorse.popularmoviesapp.DataBase.MovieDataBase;

/**
 * Created by Mohammed Morse on 06/09/2018.
 *    \  ContentProviderOperations \
 *                    \
 *          ----------\---------
 *    \     \                   \
 *         single               multiple
 *
 *
 *        < ContentProviderImplementation >
 *
 */

public interface ContentProviderOperations {
    public Cursor Select(Cursor myCursor, MovieDataBase dataBase, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder);
    public void delete(MovieDataBase dataBase,Uri uri,String selection,String[] selectionArgs);
    public void insert(MovieDataBase dataBase,Uri uri,ContentValues values);
}
