package win.tommy.tusha.ui.fragment;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.List;

import win.tommy.tusha.R;
import win.tommy.tusha.base.BaseFragment;
import win.tommy.tusha.model.bean.PictureBean;
import win.tommy.tusha.presenter.impl.PicturePresenterImpl;
import win.tommy.tusha.ui.adapter.PicAdapter;
import win.tommy.tusha.ui.view.PictureView;

/**
 * Created by tommy on 2017/9/26 0026.
 */

public class PictureFragment extends BaseFragment implements PictureView{

    private SwipeRefreshLayout pic_swipe;
    private RecyclerView pic_recyle;
    private Handler handler = new Handler();
    private PicturePresenterImpl picturePresenter = new PicturePresenterImpl(this);
    private int num = 100;
    private int pageNum = 1;
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
        picturePresenter.getPicData(num,pageNum);

    }
    @Override
    protected void initData() {
        pic_swipe.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        pic_recyle.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        pic_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        num=num+10;
                        picturePresenter.getPicData(num,pageNum);
                        pic_swipe.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }


    @Override
    public void setUpPicData(List<PictureBean.ResultsBean> results) {
        pic_recyle.setAdapter(new PicAdapter(R.layout.item_frgment_picture,results));
    }

    @Override
    public void showErrorView() {

    }
}
