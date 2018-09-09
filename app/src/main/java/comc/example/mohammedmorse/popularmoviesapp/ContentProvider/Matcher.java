package comc.example.mohammedmorse.popularmoviesapp.ContentProvider;

import android.content.UriMatcher;
import android.net.Uri;

/**
 * Created by Mohammed Morse on 06/09/2018.
 */

public class Matcher {
   private UriMatcher matcher;
    public UriMatcher InitMatch(){
        matcher=new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(ContentProviderContract.AUTHONTCATION,ContentProviderContract.TABLEPATH,2);
        matcher.addURI(ContentProviderContract.AUTHONTCATION,ContentProviderContract.TABLEPATH+"/*",4);
        return matcher;
    }
    public ContentProviderOperations MakeMatch(Uri uri){
       int x= matcher.match(uri);
       if(x==2){
           return new MultipleTaskOperation();
       }
       else
           return new SingleTaskOperation();
    }
}
