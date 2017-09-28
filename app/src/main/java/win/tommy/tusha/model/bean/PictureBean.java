package win.tommy.tusha.model.bean;

import java.util.List;

import win.tommy.tusha.base.BaseEntity;

/**
 * Created by tommy on 2017/9/26 0026.
 */

public class PictureBean extends BaseEntity {

    /**
     * error : false
     * results : [{"_id":"59c46011421aa972845f2089","createdAt":"2017-09-22T08:57:53.998Z","desc":"9-22","publishedAt":"2017-09-26T12:12:07.813Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjs25xfq48j20u00mhtb6.jpg","used":true,"who":"daimajia"},{"_id":"59c30b37421aa9727ddd19b4","createdAt":"2017-09-21T08:43:35.381Z","desc":"9-21","publishedAt":"2017-09-21T13:27:15.675Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjqw4n86lhj20u00u01kx.jpg","used":true,"who":"daimajia"},{"_id":"59c1b3e0421aa9727ddd19a8","createdAt":"2017-09-20T08:18:40.702Z","desc":"9-20","publishedAt":"2017-09-20T13:17:38.709Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjppsiclufj20u011igo5.jpg","used":true,"who":"带马甲"},{"_id":"59bf0c37421aa9118887ac33","createdAt":"2017-09-18T07:58:47.204Z","desc":"9-18","publishedAt":"2017-09-19T12:07:31.405Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjndz4dh39j20u00u0ada.jpg","used":true,"who":"daimajia"},{"_id":"59b720df421aa9118887ac18","createdAt":"2017-09-12T07:48:47.73Z","desc":"9-12","publishedAt":"2017-09-14T16:36:51.63Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjgfyxgwgnj20u00gvgmt.jpg","used":true,"who":"daimajia"},{"_id":"59b5cfb5421aa9118887ac0b","createdAt":"2017-09-11T07:50:13.510Z","desc":"9-11","publishedAt":"2017-09-12T08:15:08.26Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjfae1hjslj20u00tyq4x.jpg","used":true,"who":"代码家"},{"_id":"59b0d757421aa901bcb7dc0c","createdAt":"2017-09-07T13:21:27.937Z","desc":"9-7","publishedAt":"2017-09-07T13:25:26.977Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034ly1fjaxhky81vj20u00u0ta1.jpg","used":true,"who":"daimajia"},{"_id":"599f7362421aa901c85e5fc2","createdAt":"2017-08-25T08:46:26.461Z","desc":"8-25","publishedAt":"2017-09-06T12:18:11.687Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fivohbbwlqj20u011idmx.jpg","used":true,"who":"daimajia"},{"_id":"59aca203421aa901c1c0a8d8","createdAt":"2017-09-04T08:44:51.44Z","desc":"09-04","publishedAt":"2017-09-05T11:29:05.240Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fj78mpyvubj20u011idjg.jpg","used":true,"who":"dmj"},{"_id":"59a8cfdc421aa901c1c0a8c7","createdAt":"2017-09-01T11:11:24.81Z","desc":"9-1","publishedAt":"2017-09-01T12:55:52.582Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fj3w0emfcbj20u011iabm.jpg","used":true,"who":"daimajia"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean extends BaseEntity {
        /**
         * _id : 59c46011421aa972845f2089
         * createdAt : 2017-09-22T08:57:53.998Z
         * desc : 9-22
         * publishedAt : 2017-09-26T12:12:07.813Z
         * source : chrome
         * type : 福利
         * url : https://ws1.sinaimg.cn/large/610dc034ly1fjs25xfq48j20u00mhtb6.jpg
         * used : true
         * who : daimajia
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
