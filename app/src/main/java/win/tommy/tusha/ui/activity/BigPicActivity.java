package win.tommy.tusha.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import uk.co.senab.photoview.PhotoView;
import win.tommy.tusha.R;
import win.tommy.tusha.base.BaseActivity;
import win.tommy.tusha.model.bean.PictureBean;
import win.tommy.tusha.ui.adapter.PhotoViewAdapter;
import win.tommy.tusha.util.SDCardUtil;
import win.tommy.tusha.util.ToastUtil;
import win.tommy.tusha.widget.progress.HorizontalProgressBarWithNumber;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;

public class BigPicActivity extends BaseActivity {


    private PhotoView photo_img;
    private ImageView photo_download;
//    private int position;
    private List<PictureBean.ResultsBean> results;
    private HorizontalProgressBarWithNumber photo_progress;
    private static final int MSG_PROGRESS_UPDATE = 0x110;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int progress = photo_progress.getProgress();
//            int roundProgress = mRoundProgressBar.getProgress();
            photo_progress.setProgress(++progress);
//            mRoundProgressBar.setProgress(++roundProgress);
            if (progress >= 100) {
                mHandler.removeMessages(MSG_PROGRESS_UPDATE);
            }
            mHandler.sendEmptyMessageDelayed(MSG_PROGRESS_UPDATE, 100);
        }
    };
    private RxDownload rxDownload;
    private File savePath;
    private String defaultSavePath;
    private ViewPager viewPager;
    private int curPos;

    @Override
    public int getLayoutId() {
        return R.layout.activity_big_pic;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
//        photo_img = (PhotoView) findViewById(R.id.photo_img);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        photo_download = (ImageView) findViewById(R.id.photo_download);
        photo_progress = (HorizontalProgressBarWithNumber) findViewById(R.id.photo_progress);
    }

    @Override
    protected void initData() {
//        position = getIntent().getIntExtra("position", 0);
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
                Log.e("twbtommy","选中的position="+curPos);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(curPos);
        if (SDCardUtil.ExistSDCard() && SDCardUtil.getSDFreeSize() > 1.0f){
            defaultSavePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath();
//            defaultSavePath = Environment.getExternalStorageDirectory()+"/tushaDownload";
            savePath = Environment.getExternalStorageDirectory();
            Log.e("twb","defaultSavePath=SD卡="+defaultSavePath);
            Log.e("twb","savePath=SD卡="+savePath);
        }else {
            defaultSavePath = Environment.getDataDirectory().getPath();
            savePath = Environment.getDataDirectory();
            Log.e("twb","defaultSavePath=内存="+defaultSavePath);
            Log.e("twb","savePath=内存="+savePath);
        }

        File file = new File(defaultSavePath);
        if (!file.exists()) {
            file.mkdir();
        }

        rxDownload = RxDownload.getInstance(this)
                //.retrofit(myRetrofit)             //若需要自己的retrofit客户端,可在这里指定
                .defaultSavePath(defaultSavePath) //设置默认的下载路径
                .maxThread(3)                     //设置最大线程
                .maxRetryCount(3)                 //设置下载失败重试次数
                .maxDownloadNumber(5);


        photo_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("twbtommy","下载的url地址="+results.get(curPos).getUrl());
                //开始下载
                new RxPermissions(BigPicActivity.this)
                        .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .doOnNext(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (!aBoolean) {  //权限被拒绝
                                    ToastUtil.showLongToast(BigPicActivity.this, "权限被拒绝!");
                                }
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .compose(rxDownload.<Boolean>transformService(results.get(curPos).getUrl()))  //download
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Object>() {
                                       @Override
                                       public void accept(Object o) throws Exception {
//                                           ToastUtil.showLongToast(BigPicActivity.this,"开始下载");
                                           photo_progress.setVisibility(View.VISIBLE);
                                       }
                                   }, new Consumer<Throwable>() {
                                       @Override
                                       public void accept(Throwable throwable) throws Exception {
                                           ToastUtil.showLongToast(BigPicActivity.this, "添加任务失败");
                                       }
                                   }
                        );
                //接受接收下载事件和下载状态
                rxDownload.receiveDownloadStatus(results.get(curPos).getUrl())
                        .subscribe(new Consumer<DownloadEvent>() {
                            @Override
                            public void accept(DownloadEvent event) throws Exception {
                                //当事件为Failed时, 才会有异常信息, 其余时候为null.
                                if (event.getFlag() == DownloadFlag.FAILED) {
                                    Throwable throwable = event.getError();
                                    Log.e("twb", "Errore==" + throwable);
                                    ToastUtil.showLongToast(BigPicActivity.this, "下载出错");
                                }
                                long percentNumber = event.getDownloadStatus().getPercentNumber();
                                photo_progress.setProgress((int) percentNumber);
                                if (percentNumber == 100) {
                                    photo_progress.setVisibility(View.GONE);
                                    ToastUtil.showLongToast(BigPicActivity.this, "下载完成");
                                    //把图片保存后声明这个广播事件通知系统相册有新图片到来
                                    Log.e("twb","savePath=="+savePath);
                                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                                    Uri uri = Uri.fromFile(savePath);
                                    intent.setData(uri);
                                    BigPicActivity.this.sendBroadcast(intent);
                                }
                            }
                        });
            }
        });


    }
}
