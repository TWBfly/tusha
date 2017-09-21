package win.tommy.tusha;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 * Created by tommy on 2017/9/20 0020.
 */

public class myApplication extends Application {
    private  static myApplication myApplication;
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
