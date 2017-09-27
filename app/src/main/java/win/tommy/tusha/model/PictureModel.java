package win.tommy.tusha.model;

import win.tommy.tusha.rxlistener.GetPicDataListener;

/**
 * Created by tommy on 2017/9/26 0026.
 */

public interface  PictureModel{
     void getPicData(GetPicDataListener listener,int num,int pageNum);
}
