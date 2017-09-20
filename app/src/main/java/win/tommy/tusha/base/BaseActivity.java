package win.tommy.tusha.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by tommy on 2017/9/20 0020.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);//用于初始化view之前做一些事情
        setContentView(setLayoutResourceID());
        setUpView();
        setUpData();
    }

    protected abstract int setLayoutResourceID();
    protected abstract void setUpView();
    protected abstract void setUpData();

    protected void init(Bundle savedInstanceState) {
    }
}
