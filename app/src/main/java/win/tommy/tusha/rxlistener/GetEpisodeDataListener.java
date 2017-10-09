package win.tommy.tusha.rxlistener;

import java.util.List;

import win.tommy.tusha.model.bean.EpisodeBean;

/**
 * Created by tommy on 2017/9/27 0027.
 */

public interface GetEpisodeDataListener {
    void onError();
    void onSuccess(List<EpisodeBean.ResultBean.DataBean> data);
}
