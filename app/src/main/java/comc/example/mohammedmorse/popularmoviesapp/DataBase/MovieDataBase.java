package comc.example.mohammedmorse.popularmoviesapp.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mohammed Morse on 29/06/2018.
 */

public class MovieDataBase extends SQLiteOpenHelper {
    public final static String DataBaseName="TodoList";
    public final static int DataBaseVersion=9;
    public MovieDataBase(Context context) {
        super(context, DataBaseName, null, DataBaseVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE "+MovieContract.TableName+"( "+MovieContract.id +" Integer Primary Key , "+MovieContract.name+" Text , "+
    MovieContract.postermovie +" Text , "+MovieContract.backgroundmovie +" Text , "+MovieContract.releasedate +" Text , "+MovieContract.rate+" Integer , "+
            MovieContract.overview +" Text , "+MovieContract.reviewdata+" Text , "+MovieContract.trailerdata+" Text );");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS "+MovieContract.TableName);
       onCreate(db);
    }
}
