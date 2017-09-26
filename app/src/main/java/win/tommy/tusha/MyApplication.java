package win.tommy.tusha;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import win.tommy.tusha.rxnet.EasyHttp;
import win.tommy.tusha.rxnet.cache.converter.SerializableDiskConverter;
import win.tommy.tusha.rxnet.cache.model.CacheMode;
import win.tommy.tusha.rxnet.intercepter.GzipRequestInterceptor;
import win.tommy.tusha.rxnet.utils.HttpLog;
import win.tommy.tusha.util.Api;
import win.tommy.tusha.util.interceptor.CustomSignInterceptor;

/**
 * Created by tommy on 2017/9/20
 */

public class MyApplication extends Application {
    private  static MyApplication myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        //初始化EasyHttp
        EasyHttp.init(this);
        initEasyHttp();
    }

    public static Context getContext(){
        return myApplication;
    }

    public Resources getAppResources(){
        return myApplication.getResources();
    }

    private void initEasyHttp() {
        //设置请求头
//        HttpHeaders headers = new HttpHeaders();
//        headers.put("User-Agent", SystemInfoUtils.getUserAgent(this, AppConstant.APPID));
        //设置请求参数
//        HttpParams params = new HttpParams();
//        params.put("appId", AppConstant.APPID);
        EasyHttp.getInstance()
                .debug("twb", true)
                .setRetryCount(3)//默认网络不好自动重试3次
                .setRetryDelay(500)//每次延时500ms重试
                .setRetryIncreaseDelay(500)//每次延时叠加500ms
                .setCacheMode(CacheMode.FIRSTREMOTE)//先请求网络，请求网络失败后再加载缓存 具体请看CacheMode
                .setBaseUrl(Api.BaseUrl)
                .setCacheDiskConverter(new SerializableDiskConverter())//默认缓存使用序列化转化
                .setCacheMaxSize(50 * 1024 * 1024)//设置缓存大小为50M
                .setCacheVersion(1)//缓存版本为1
                .setHostnameVerifier(new UnSafeHostnameVerifier(Api.BaseUrl))//全局访问规则
                .setCertificates()//信任所有证书
                //.addConverterFactory(GsonConverterFactory.create(gson))//本框架没有采用Retrofit的Gson转化，所以不用配置
                //.addCommonHeaders(headers)//设置全局公共头
                //.addCommonParams(params)//设置全局公共参数
                .addInterceptor(new GzipRequestInterceptor())//开启post数据进行gzip后发送给服务器
                .addInterceptor(new CustomSignInterceptor());//添加参数签名拦截器
                //.addInterceptor(new HeTInterceptor());//处理自己业务的拦截器
    }

    public class UnSafeHostnameVerifier implements HostnameVerifier {
        private String host;

        public UnSafeHostnameVerifier(String host) {
            this.host = host;
            HttpLog.i("###############　UnSafeHostnameVerifier " + host);
        }

        @Override
        public boolean verify(String hostname, SSLSession session) {
            HttpLog.i("############### verify " + hostname + " " + this.host);
            if (this.host == null || "".equals(this.host) || !this.host.contains(hostname))
                return false;
            return true;
        }
    }
}
