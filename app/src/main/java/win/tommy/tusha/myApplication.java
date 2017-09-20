package win.tommy.tusha;

import android.app.Application;
import android.content.Context;

/**
 * Created by tommy on 2017/9/20 0020.
 */

public class myApplication extends Application {
    private  static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext(){
        return context;
    }
}
