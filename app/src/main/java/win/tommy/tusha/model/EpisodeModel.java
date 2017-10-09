package win.tommy.tusha.model;

import win.tommy.tusha.rxlistener.GetEpisodeDataListener;

/**
 * Created by tommy on 2017/10/9 0009.
 */

public interface EpisodeModel {
    void getEpisodeData(GetEpisodeDataListener listener,int page,String time);
}
