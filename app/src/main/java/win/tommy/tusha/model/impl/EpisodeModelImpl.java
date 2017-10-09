package win.tommy.tusha.model.impl;

import android.text.TextUtils;
import android.util.Log;

import java.util.List;

import win.tommy.tusha.model.EpisodeModel;
import win.tommy.tusha.model.bean.EpisodeBean;
import win.tommy.tusha.rxlistener.GetEpisodeDataListener;
import win.tommy.tusha.rxnet.EasyHttp;
import win.tommy.tusha.rxnet.cache.converter.GsonDiskConverter;
import win.tommy.tusha.rxnet.callback.CallBack;
import win.tommy.tusha.rxnet.exception.ApiException;
import win.tommy.tusha.util.Api;
import win.tommy.tusha.util.GsonUtil;

/**
 * Created by tommy on 2017/10/9 0009.
 */

public class EpisodeModelImpl implements EpisodeModel {
    @Override
    public void getEpisodeData(GetEpisodeDataListener listener, int page, String time) {
        EasyHttp.get(Api.EpisodeUrl)
                .cacheDiskConverter(new GsonDiskConverter())//GSON-数据转换器
                .retryCount(5)//本次请求重试次数
                .retryDelay(500)//本次请求重试延迟时间500ms
                .cacheKey("cachekey")//缓存key
                .params("key","1009eb9e83f3f2ce58a011f87b1f44fa")
                .params("page",page+"")     //int
                .params("pagesize",10+"")   //int
                .params("sort","desc")
                .params("time",time)
                .execute(new CallBack<String>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(ApiException e) {
                        listener.onError();
                        Log.e("twb","错误=="+e);
                    }

                    @Override
                    public void onSuccess(String s) {
                        Log.e("twb","成功=="+s);
                        EpisodeBean episodeBean = GsonUtil.GsonToBean(s, EpisodeBean.class);
                        String reason = episodeBean.getReason();
                        if (TextUtils.equals("Success",reason)){
                            List<EpisodeBean.ResultBean.DataBean> data = episodeBean.getResult().getData();
                            if(data!=null && data.size()>0){
                                listener.onSuccess(data);
                            }
                        }
                    }
                });


    }
}
