package win.tommy.tusha.ui.activity;

import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import win.tommy.tusha.R;
import win.tommy.tusha.base.BaseActivity;
import win.tommy.tusha.model.bean.PictureBean;
import win.tommy.tusha.ui.adapter.PhotoViewAdapter;
import win.tommy.tusha.util.AppUtil;
import win.tommy.tusha.util.ImageHelper;

public class BigPicActivity extends BaseActivity {


    private ImageView photo_download;
    private int position;
    private List<PictureBean.ResultsBean> results;
    private ViewPager viewPager;
    private int curPos;
    private ImageHelper imageHelper;

    @Override
    public int getLayoutId() {
        return R.layout.activity_big_pic;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        photo_download = (ImageView) findViewById(R.id.photo_download);
    }

    @Override
    protected void initData() {
        position = getIntent().getIntExtra("position", 0);//点击的position
        results = (ArrayList<PictureBean.ResultsBean>) getIntent().getSerializableExtra("results");

        viewPager.setAdapter(new PhotoViewAdapter(this,results));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                curPos = position;
                viewPager.setTag(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(position);
        //下载
        AppUtil.singleClick(photo_download,consumer->{
            imageHelper = new ImageHelper(BigPicActivity.this);
            imageHelper.saveImage(results.get(curPos).getUrl());
        });
    }

    @Override
    protected void onDestroy() {
        if (imageHelper != null) {
            imageHelper.unInit();
        }
        super.onDestroy();
    }
}
