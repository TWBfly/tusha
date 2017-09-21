package win.tommy.tusha.base;

/**
 * Created by tommy on 2017/9/21 0021.
 */

public interface BaseView {
    /*******内嵌加载*******/
    void showLoading(String title);
    void stopLoading();
    void showErrorTip(String msg);
}
