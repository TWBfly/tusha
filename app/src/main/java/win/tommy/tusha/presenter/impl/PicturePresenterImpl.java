package win.tommy.tusha.presenter.impl;

import android.util.Log;

import java.util.List;

import win.tommy.tusha.model.PictureModel;
import win.tommy.tusha.model.bean.PictureBean;
import win.tommy.tusha.model.impl.PictureModelImpl;
import win.tommy.tusha.presenter.PicturePresenter;
import win.tommy.tusha.rxlistener.GetPicDataListener;
import win.tommy.tusha.ui.view.PictureView;

/**
 * Created by tommy on 2017/9/26 0026.
 */

public class PicturePresenterImpl implements PicturePresenter,GetPicDataListener{
    private PictureView pictureView;
    private PictureModel pictureModel;

    public PicturePresenterImpl(PictureView pictureView) {
        this.pictureView = pictureView;
        this.pictureModel = new PictureModelImpl();
    }

    @Override
    public void onError() {
        pictureView.showErrorView();
    }

    @Override
    public void onSuccess(final List<PictureBean.ResultsBean> results) {
        pictureView.setUpPicData(results);
    }

    public void getPicData(){
        pictureModel.getPicData(this);
    }
}
