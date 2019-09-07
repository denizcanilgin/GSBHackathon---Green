package website.timrobinson.opencvtutorial;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("12ec43fac275c180e9287cee5dd9b5d2a49e0614")
                .clientKey("49b75d17f3445bda4cf6c8eda6d8513f91b09453")
                .server("http://18.216.188.90:80/parse")
                .build()
        );

        ParseObject gameScore = new ParseObject("HORSETHIEF");
        gameScore.put("score", 1337);
        gameScore.put("playerName", "Cem GOKALP");
        gameScore.put("cheatMode", false);
        gameScore.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){

                    Log.i("RESULT ::", "SUCCEDED");

                }else
                    Log.i("RESULT ::", "FAILED" + e.getMessage());

            }
        });

    }
}
