package win.tommy.tusha.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import win.tommy.tusha.R;
import win.tommy.tusha.model.bean.PictureBean;
import win.tommy.tusha.ui.activity.BigPicActivity;
import win.tommy.tusha.util.glide.GlideImageLoader;

/**
 * Created by tommy on 2017/10/9 0009.
 */

public class PhotoViewAdapter extends PagerAdapter {
    private List<PictureBean.ResultsBean> data;
    private BigPicActivity mActivity;

    public PhotoViewAdapter(BigPicActivity bigPicActivity, List<PictureBean.ResultsBean> results) {
        this.mActivity = bigPicActivity;
        this.data = results;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.adapter_item_image_layout, null);
        PhotoView photo_img = view.findViewById(R.id.photo_img);
        photo_img.setScaleType(ImageView.ScaleType.FIT_CENTER);
        GlideImageLoader.getInstance().displayImage(mActivity, data.get(position).getUrl(), photo_img);
        container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
