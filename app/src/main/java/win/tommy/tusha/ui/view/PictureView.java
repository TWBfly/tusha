package win.tommy.tusha.ui.view;

import java.util.List;

import win.tommy.tusha.model.bean.PictureBean;

/**
 * Created by tommy on 2017/9/26 0026.
 */

public interface PictureView{

    void setUpPicData(List<PictureBean.ResultsBean> results);
    void showErrorView();
}
