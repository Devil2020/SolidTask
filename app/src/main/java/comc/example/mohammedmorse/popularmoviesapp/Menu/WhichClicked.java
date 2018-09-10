package comc.example.mohammedmorse.popularmoviesapp.Menu;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import comc.example.mohammedmorse.popularmoviesapp.Adapters.CustomAdapter;
import comc.example.mohammedmorse.popularmoviesapp.DataBase.DataBaseOperationInt;
import comc.example.mohammedmorse.popularmoviesapp.Model.MovieData;
import comc.example.mohammedmorse.popularmoviesapp.R;
import comc.example.mohammedmorse.popularmoviesapp.SettingActivity;

/**
 * Created by Mohammed Morse on 10/09/2018.
 */

public class WhichClicked {
    ArrayList<MovieData> list;
    CustomAdapter adapter;
    Context context;
    boolean b;

    public WhichClicked(Context context, ArrayList<MovieData> list, CustomAdapter adapter, boolean b) {
        this.list = list;
        this.adapter = adapter;
        this.context = context;
        this.b = b;
    }

    public Action whichClicked(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.setting) {
            return new Setting(context);
        } else if (id == R.id.favourite) {
            return new Favourite(list, adapter, context, b);
        }
        return null;
    }
}
