package win.tommy.tusha.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import win.tommy.tusha.R;
import win.tommy.tusha.model.bean.EpisodeBean;

/**
 * Created by tommy on 2017/10/9 0009.
 */

public class EpisodeAdapter extends BaseQuickAdapter<EpisodeBean.ResultBean.DataBean,BaseViewHolder> {

    public EpisodeAdapter(@LayoutRes int layoutResId, @Nullable List<EpisodeBean.ResultBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EpisodeBean.ResultBean.DataBean item) {
        TextView item_text = helper.getView(R.id.item_text);
        item_text.setText(item.getContent());
    }

    public void addFrist(int position, List<EpisodeBean.ResultBean.DataBean> data) {
        mData.addAll(position,data);
        notifyDataSetChanged();
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }
}
