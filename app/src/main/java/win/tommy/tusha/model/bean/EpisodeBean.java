package win.tommy.tusha.model.bean;

import java.util.List;

/**
 * Created by tommy on 2017/10/9 0009.
 */

public class EpisodeBean {

    /**
     * error_code : 0
     * reason : Success
     * result : {"data":[{"content":"丈夫躺在床上，他得了感冒。亲爱的，如果我死了，你会因想念我而感到有点忧郁吗？那当然，亲爱的，因为你是知道的，任何微不足道的小事我都要哭一场的。","hashId":"DF57F98C7A8E1D0C339C49257FB8C23B","unixtime":1418745238,"updatetime":"2014-12-16 23:53:58"},{"content":"老公文件忘拿了叫我送去他单位，我打的到他单位想打电话叫他下来拿。一找手机忘带了，那就给送上去吧，文件也忘拿了，回家从拿，钥匙也没带！只有叫老公把我送回去他自己拿文件！","hashId":"D9C605E103CDC7FC4F061E090FF1177E","unixtime":1418745238,"updatetime":"2014-12-16 23:53:58"},{"content":"某女要去法国出差两周，临行前问丈夫：需要带什么礼物回来呀。丈夫说：法国女孩儿！！等回来的时候某女告诉丈夫：我已经尽力了，但是至于是男孩还是女孩，要等几个月才能知道！","hashId":"A85FA078615C098304615BDD8388A220","unixtime":1418745238,"updatetime":"2014-12-16 23:53:58"},{"content":"\u201c亲爱的，昨晚我做噩梦了。\u201d\u201c什么梦啊？\u201d\u201c我梦到你被绑架了！\u201d\u201c好感动啊，做梦都梦见我，然后呢，发生什么了？\u201d\u201c绑架居然把你送回来了！！！\u201d","hashId":"1E72B96D229F4DC4506AE2A53B45CCC4","unixtime":1418745238,"updatetime":"2014-12-16 23:53:58"},{"content":"老公：老婆今天咱们结婚有什么想说的么。老婆：我好庆幸当初选择了你陪我一生。老公：哎今天大喜的日子就别提不开心的事了！\u2026","hashId":"F25A81440B957C55B66A0636E67E253F","unixtime":1418745238,"updatetime":"2014-12-16 23:53:58"},{"content":"中午老公仔细地盯着我看了半天，说道：\u201c不做家务的女人永远不可能美丽。\u201d晚上他回来晚了，没办法，我只好炒菜。老公又仔细地看我半天，说道：\u201c做家务的女人永远都是美丽的，但是你例外。\u201d然后我就罢工。","hashId":"0DF7B0DE12C6528D896B158CF2F53930","unixtime":1418745238,"updatetime":"2014-12-16 23:53:58"},{"content":"夏天到了，问老公，现在觉得我老不老？老公说感觉比以前小了，我问为什么呢，老公说刚认识你的时候觉得你很深沉，现在有点幼稚了，通俗点说就是刚认识那会很装逼，现在很二逼。。。","hashId":"D41AE95F3E12F8400AF5DDD74E815D50","unixtime":1418745238,"updatetime":"2014-12-16 23:53:58"},{"content":"那天在公交车上，老公站我身后。由于之前两个人在冷战中，我越想越来气，回头看了他一眼\u2026\u2026突然大叫，你摸我屁股干嘛，变态啊你！然后我老公在全车人鄙视的眼神中下车了，把我丢下了。。。","hashId":"C29663312C0E9DC037FD104F80D1DD7E","unixtime":1418745238,"updatetime":"2014-12-16 23:53:58"},{"content":"吃完晚饭，我和老婆都懒得洗碗，我提议：\u201c要不咱们猜拳吧？输的人洗！\u201d她摇摇头娇羞地说：\u201c才不要呢，人家是淑女来着，猜拳这么粗鲁！\u201d我想了想又提议：\u201c那咱们猜硬币吧！\u201d说罢，我从口袋里掏出一枚硬币。突然她怒道：\u201c你竟然敢藏私房钱！！！罚你洗碗三天。\u201d","hashId":"1F820843DA039E144AFEB2F892AEDF4B","unixtime":1418745238,"updatetime":"2014-12-16 23:53:58"},{"content":"门口街上路过一结婚车队！一家三口同时哇塞！却各有各看法！媳妇说：\u201c看人家这车队，真高档，真气派！\u201d。四岁女儿说：\u201c气球好漂亮呀\u201d。我说：\u201c新娘真美\u201d！结果媳妇一脸黑线，怒曰：\u201c滚回去做饭去！！！\u201d","hashId":"32720510643A161931AA51FF23D5EF38","unixtime":1418745238,"updatetime":"2014-12-16 23:53:58"}]}
     */

    private int error_code;
    private String reason;
    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * content : 丈夫躺在床上，他得了感冒。亲爱的，如果我死了，你会因想念我而感到有点忧郁吗？那当然，亲爱的，因为你是知道的，任何微不足道的小事我都要哭一场的。
             * hashId : DF57F98C7A8E1D0C339C49257FB8C23B
             * unixtime : 1418745238
             * updatetime : 2014-12-16 23:53:58
             */

            private String content;
            private String hashId;
            private String unixtime;
            private String updatetime;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getHashId() {
                return hashId;
            }

            public void setHashId(String hashId) {
                this.hashId = hashId;
            }

            public String getUnixtime() {
                return unixtime;
            }

            public void setUnixtime(String unixtime) {
                this.unixtime = unixtime;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }
        }
    }
}
