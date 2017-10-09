package win.tommy.tusha.presenter.impl;

import android.util.Log;

import java.util.List;

import win.tommy.tusha.model.bean.EpisodeBean;
import win.tommy.tusha.model.impl.EpisodeModelImpl;
import win.tommy.tusha.presenter.EpisodePresenter;
import win.tommy.tusha.rxlistener.GetEpisodeDataListener;
import win.tommy.tusha.ui.view.EpisodeView;

/**
 * Created by tommy on 2017/10/9 0009.
 */

public class EpisodePresenterImpl implements EpisodePresenter,GetEpisodeDataListener {

    private EpisodeView episodeView;
    private EpisodeModelImpl episodeModel;

    public EpisodePresenterImpl(EpisodeView episodeView) {
        this.episodeView = episodeView;
        this.episodeModel = new EpisodeModelImpl();
    }

    @Override
    public void getEpisodecData(int page, String time) {
        episodeModel.getEpisodeData(this,page,time);
    }

    @Override
    public void onError() {
        Log.e("twb","EpisodePresenterImpl == onError");
    }

    @Override
    public void onSuccess(List<EpisodeBean.ResultBean.DataBean> data) {
        episodeView.setUpEpisodeData(data);
    }
}
