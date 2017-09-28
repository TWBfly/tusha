package win.tommy.tusha.ui.activity;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import win.tommy.tusha.R;
import win.tommy.tusha.base.BaseActivity;
import win.tommy.tusha.model.bean.PictureBean;
import win.tommy.tusha.util.glide.GlideImageLoader;

public class BigPicActivity extends BaseActivity {


    private PhotoView photo_img;

    @Override
    public int getLayoutId() {
        return R.layout.activity_big_pic;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        photo_img = (PhotoView) findViewById(R.id.photo_img);
    }

    @Override
    protected void initData() {
        int position = getIntent().getIntExtra("position", 0);
        List<PictureBean.ResultsBean> results = (ArrayList<PictureBean.ResultsBean>) getIntent().getSerializableExtra("results");
        GlideImageLoader.getInstance().displayImage(this,results.get(position).getUrl(),photo_img);
    }
}
