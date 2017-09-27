package win.tommy.tusha.ui.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.List;

import win.tommy.tusha.R;
import win.tommy.tusha.base.BaseFragment;
import win.tommy.tusha.model.bean.PictureBean;
import win.tommy.tusha.presenter.impl.PicturePresenterImpl;
import win.tommy.tusha.ui.view.PictureView;

/**
 * Created by tommy on 2017/9/26 0026.
 */

public class PictureFragment extends BaseFragment implements PictureView{

    private SwipeRefreshLayout pic_swipe;
    private RecyclerView pic_recyle;
    private PicturePresenterImpl picturePresenter = new PicturePresenterImpl(this);
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_picture;
    }
    @Override
    protected void initView(View view) {
        pic_swipe = view.findViewById(R.id.pic_swipe);
        pic_recyle = view.findViewById(R.id.pic_recyle);
    }

    @Override
    public void initPresenter() {
        picturePresenter.getPicData();

    }
    @Override
    protected void initData() {
        pic_swipe.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        pic_recyle.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
    }


    @Override
    public void setUpPicData(List<PictureBean.ResultsBean> results) {
        Log.e("twb","PictureFragment=="+results.get(0).getUrl());
    }

    @Override
    public void showErrorView() {
        Log.e("twb","PictureFragment=showErrorView=");
    }
}
