package win.tommy.tusha.rxlistener;

import java.util.List;

import win.tommy.tusha.model.bean.PictureBean;

/**
 * Created by tommy on 2017/9/27 0027.
 */

public interface GetPicDataListener {
    void onError();
    void onSuccess(List<PictureBean.ResultsBean> results);
}
