package win.tommy.tusha.ui.view;

import java.util.List;

import win.tommy.tusha.model.bean.EpisodeBean;

/**
 * Created by tommy on 2017/10/9 0009.
 */

public interface EpisodeView {
    void setUpEpisodeData(List<EpisodeBean.ResultBean.DataBean> data);
    void showErrorView();
}
