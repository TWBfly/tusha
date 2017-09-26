package win.tommy.tusha;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 * Created by tommy on 2017/9/20 0020.
 */

public class MyApplication extends Application {
    private  static MyApplication myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public static Context getContext(){
        return myApplication;
    }

    public Resources getAppResources(){
        return myApplication.getResources();
    }
}
