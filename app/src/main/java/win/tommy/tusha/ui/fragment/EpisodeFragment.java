package win.tommy.tusha.ui.fragment;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import win.tommy.tusha.R;
import win.tommy.tusha.base.BaseFragment;
import win.tommy.tusha.model.bean.EpisodeBean;
import win.tommy.tusha.presenter.impl.EpisodePresenterImpl;
import win.tommy.tusha.ui.adapter.EpisodeAdapter;
import win.tommy.tusha.ui.view.EpisodeView;

/**
 * Created by tommy on 2017/9/26 0026.
 */

public class EpisodeFragment extends BaseFragment implements EpisodeView {
    private SwipeRefreshLayout pic_swipe;
    private RecyclerView pic_recyle;
    private Handler handler = new Handler();
    private EpisodePresenterImpl episodePresenter = new EpisodePresenterImpl(this);
    private EpisodeAdapter episodeAdapter;
    private int page = 1;
    private boolean is_dropDown = false;

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
        long time = new Date().getTime();
        episodePresenter.getEpisodecData(page,time/1000+"");
    }

    @Override
    protected void initData() {
        pic_swipe.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        pic_recyle.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        //下拉刷新
        pic_swipe.setOnRefreshListener(() -> handler.postDelayed(() -> {
            long time = new Date().getTime();
            page=page+1;
            episodePresenter.getEpisodecData(page,time/1000+"");
            pic_swipe.setRefreshing(false);
            is_dropDown = true;
        }, 1000));

        List<EpisodeBean.ResultBean.DataBean> list = new ArrayList<>();
        episodeAdapter = new EpisodeAdapter(R.layout.item_fragment_episode, list);
        pic_recyle.setAdapter(episodeAdapter);

        //加载更多
        episodeAdapter.setOnLoadMoreListener(() -> pic_recyle.postDelayed(() -> {
            long time = new Date().getTime();
            page=page+1;
            episodePresenter.getEpisodecData(page,time/1000+"");
            episodeAdapter.loadMoreComplete();
        }, 1000), pic_recyle);

    }

    @Override
    public void setUpEpisodeData(List<EpisodeBean.ResultBean.DataBean> data) {
        if (is_dropDown){
            episodeAdapter.addFrist(0,data);
        }else {
            episodeAdapter.clear();
            episodeAdapter.addData(data);
        }
    }

    @Override
    public void showErrorView() {

    }
}
