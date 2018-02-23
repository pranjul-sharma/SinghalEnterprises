package in.test.singhalenterprises.singhalenterprises;

import android.app.Application;

/**
 * Created by pranjul on 10/8/2017.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Montserrat-Medium.ttf");
    }
}
