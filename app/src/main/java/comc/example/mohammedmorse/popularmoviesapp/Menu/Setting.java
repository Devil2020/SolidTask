package comc.example.mohammedmorse.popularmoviesapp.Menu;

import android.content.Context;
import android.content.Intent;

import comc.example.mohammedmorse.popularmoviesapp.SettingActivity;

/**
 * Created by Mohammed Morse on 10/09/2018.
 */

public class Setting implements Action {
    Context context;
    public Setting(Context context){
        this.context=context;
    }
    @Override
    public void doIt() {
       doIntent();
    }
    public void doIntent(){
        Intent intent=new Intent(context,SettingActivity.class);
        context.startActivity(intent);
    }
}
