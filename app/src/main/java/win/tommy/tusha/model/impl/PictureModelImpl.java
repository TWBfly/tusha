package win.tommy.tusha.model.impl;

import java.util.List;

import win.tommy.tusha.model.PictureModel;
import win.tommy.tusha.model.bean.PictureBean;
import win.tommy.tusha.rxlistener.GetPicDataListener;
import win.tommy.tusha.rxnet.EasyHttp;
import win.tommy.tusha.rxnet.cache.converter.GsonDiskConverter;
import win.tommy.tusha.rxnet.callback.CallBack;
import win.tommy.tusha.rxnet.exception.ApiException;
import win.tommy.tusha.util.Api;
import win.tommy.tusha.util.GsonUtil;

/**
 * Created by tommy on 2017/9/26 0026.
 */

public class PictureModelImpl implements PictureModel {

    @Override
    public void getPicData(final GetPicDataListener listener,int num,int pageNum) {
        EasyHttp.get(Api.GankUrl +num+"/"+pageNum+"")//?imageView2/0/w/100
                .cacheDiskConverter(new GsonDiskConverter())//GSON-数据转换器
                .retryCount(5)//本次请求重试次数
                .retryDelay(500)//本次请求重试延迟时间500ms
                .cacheKey("cachekey")//缓存key
                .execute(new CallBack<String>() {
                    @Override
                    public void onStart() {
                        //开始请求

                    }

                    @Override
                    public void onCompleted() {
                        //请求完成

                    }

                    @Override
                    public void onError(ApiException e) {
                        //请求错误
                        listener.onError();

                    }

                    @Override
                    public void onSuccess(String picData) {
                        //请求成功
                        PictureBean pictureBean = GsonUtil.GsonToBean(picData, PictureBean.class);
                        List<PictureBean.ResultsBean> results = pictureBean.getResults();
                        if (results!=null && results.size()>0){
                            listener.onSuccess(results);
                        }
                    }
                });
    }
}
