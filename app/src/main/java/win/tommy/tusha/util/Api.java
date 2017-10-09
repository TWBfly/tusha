package win.tommy.tusha.util;

/**
 * Created by tommy on 2017/9/26 0026.
 */

public interface Api {
    //http://gank.io/api/data/数据类型/请求个数/第几页
    //http://gank.io/api/data/福利/10/1
    String GankUrl = "http://gank.io/api/data/福利/";
    //http://japi.juhe.cn/joke/content/list.from?key=您申请的KEY&page=2&pagesize=10&sort=asc&time=1418745237
    //类型，desc:指定时间之前发布的，asc:指定时间之后发布的
    //key=1009eb9e83f3f2ce58a011f87b1f44fa&page=2&pagesize=10&sort=asc&time=1418745237
//    String EpisodeUrl = "http://japi.juhe.cn/joke/content/list.from?key=1009eb9e83f3f2ce58a011f87b1f44fa&page=2&pagesize=10&sort=asc&time=1418745237";
    String EpisodeUrl = "http://japi.juhe.cn/joke/content/list.from";
}
