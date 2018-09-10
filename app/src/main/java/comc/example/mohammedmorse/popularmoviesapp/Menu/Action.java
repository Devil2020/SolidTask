package comc.example.mohammedmorse.popularmoviesapp.Menu;

import android.widget.ResourceCursorTreeAdapter;

import org.json.JSONException;

/**
 * Created by Mohammed Morse on 10/09/2018.
 */

public interface Action {
   public void doIt() throws JSONException;
}
