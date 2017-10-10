package win.tommy.tusha.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import win.tommy.tusha.R;
import win.tommy.tusha.model.bean.PictureBean;
import win.tommy.tusha.util.glide.GlideImageLoader;

/**
 * Created by tommy on 2017/9/27 0027.
 */

public class PicAdapter extends BaseQuickAdapter<PictureBean.ResultsBean,BaseViewHolder> {
    public PicAdapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, PictureBean.ResultsBean item) {
        ImageView imageView = (ImageView) helper.getView(R.id.item_img);
        GlideImageLoader.getInstance().displayImage(mContext,item.getUrl(),imageView);
    }

    public void clear() {
        mData.clear();
        notifyItemRangeInserted(0,50);
//        notifyDataSetChanged();
    }
    public void addFrist(int position, List<PictureBean.ResultsBean> results){
        mData.addAll(position,results);
        notifyItemRangeInserted(0,50);
//        notifyDataSetChanged();
    }
}
