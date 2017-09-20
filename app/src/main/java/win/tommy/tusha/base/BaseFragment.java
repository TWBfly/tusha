package win.tommy.tusha.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tommy on 2017/9/20 0020.
 */

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayoutResourceID(), container, false);
        init(view);//在初始化视图之前，做的一些操作
        setUpView();
        setUpData();
        return view;
    }

    protected abstract int setLayoutResourceID();

    protected abstract void init(View view);

    protected abstract void setUpView();

    protected abstract void setUpData();
}
