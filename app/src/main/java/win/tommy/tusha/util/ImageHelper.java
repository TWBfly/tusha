package win.tommy.tusha.util;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import win.tommy.tusha.util.dialog.LoadingDialog;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadStatus;

import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static android.os.Environment.getDataDirectory;
import static android.os.Environment.getExternalStoragePublicDirectory;

/**
 * Created by tommy on 2017/10/9 0009.
 */

public class ImageHelper {
    private Activity mActivity;
    private LoadingDialog mDialog;
    private Disposable mDisposable;
    private String imagePath;
    private File file;

    public ImageHelper(Activity activity) {
        this.mActivity = activity;
        if (SDCardUtil.ExistSDCard() && SDCardUtil.getSDFreeSize() > 1.0f){
            imagePath = getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS).getPath();
            file = getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS);
            Log.e("twb","defaultSavePath=SD卡="+imagePath);
        }else {
            imagePath = getDataDirectory().getPath();
            file = getDataDirectory();
            Log.e("twb","defaultSavePath=内存="+imagePath);
        }
        File file = new File(imagePath);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public void unInit() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }

        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    // 保存图片
    public void saveImage(String imageUrl) {
        RxPermissions rxPermissions = new RxPermissions(mActivity);
        rxPermissions.request(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (!granted) {
                        ToastUtil.showLongToast(mActivity, "您已禁止了写数据权限");
                    } else {
                        Observable.just(imageUrl)
                                .filter(it -> !TextUtils.isEmpty(it))
                                .map(this::getImageName)
                                .filter(it -> !TextUtils.isEmpty(it))
                                .filter(it -> !isImageExist(it))
                                .subscribe(it -> downloadImage(imageUrl, it),
                                        Throwable::printStackTrace);
                    }
                });
        //通知相册
        Uri uri = Uri.fromFile(file);
        mActivity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
    }

    // 保存图片
    public void saveImage2(String imageUrl) {
        RxPermissions rxPermissions = new RxPermissions(mActivity);
        Observable<Boolean> requestPermissionObservable = rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        Observable<String> getImageNameObservable = Observable.just(imageUrl)
                .filter(it -> !TextUtils.isEmpty(it))
                .map(this::getImageName)
                .filter(it -> !TextUtils.isEmpty(it))
                .filter(it -> !isImageExist(it))
                .doOnError(Throwable::printStackTrace);

        Observable.zip(requestPermissionObservable, getImageNameObservable,
                (permission, imageName) -> {
                    if (!permission) {
                        ToastUtil.showLongToast(mActivity, "您已禁止了写数据权限");
                        return null;
                    }
                    return imageName;
                })
                .filter(it -> !TextUtils.isEmpty(it))
                .subscribe(it -> downloadImage(imageUrl, it), Throwable::printStackTrace);
    }

    // 下载图片
    public void downloadImage(String imageUrl, String imageName) {
        RxDownload.getInstance(mActivity)
                .download(imageUrl, imageName, imagePath)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DownloadStatus>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                        mDialog = new LoadingDialog(mActivity);
                        mDialog.show("下载图片中...");
                    }

                    @Override
                    public void onNext(DownloadStatus value) {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mDialog.dismiss();
                        ToastUtil.showLongToast(mActivity, "保存失败");
                    }

                    @Override
                    public void onComplete() {
                        mDialog.dismiss();
                        ToastUtil.showLongToast(mActivity, "保存成功");
                    }
                });
    }

    // 获取图片名称
    public String getImageName(String url) {
        String[] split = url.split("/");
        return split[split.length - 1];
    }

    // 判断图片是否存在
    private boolean isImageExist(String fileName) {
        File file = new File(imagePath, fileName);
        boolean isExist = file.exists();
        if (isExist) {
            ToastUtil.showLongToast(mActivity, "图片已存在");
        }
        return isExist;
    }
}
