package com.allen.lifthelper.wechatnews.bean;

import java.util.List;

/**
 * Created by Allen on 2016/1/22.
 */
public class WechatNewsBean {

    /**
     * reason : success
     * result : {"list":[{"id":"wechat_20160122042202","title":"心里憋屈写的很入心！","source":"点点星光","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-3576206.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20160122042202"},{"id":"wechat_20160122042203","title":"2016，你该明白的人生10件事","source":"点点星光","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-3576205.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20160122042203"}],"totalPage":250,"ps":2,"pno":1}
     * error_code : 0
     */

    private String reason;
    /**
     * list : [{"id":"wechat_20160122042202","title":"心里憋屈写的很入心！","source":"点点星光","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-3576206.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20160122042202"},{"id":"wechat_20160122042203","title":"2016，你该明白的人生10件事","source":"点点星光","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-3576205.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20160122042203"}]
     * totalPage : 250
     * ps : 2
     * pno : 1
     */

    private ResultEntity result;
    private int error_code;

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public ResultEntity getResult() {
        return result;
    }

    public int getError_code() {
        return error_code;
    }

    public static class ResultEntity {
        private int totalPage;
        private int ps;
        private int pno;
        /**
         * id : wechat_20160122042202
         * title : 心里憋屈写的很入心！
         * source : 点点星光
         * firstImg : http://zxpic.gtimg.com/infonew/0/wechat_pics_-3576206.jpg/640
         * mark :
         * url : http://v.juhe.cn/weixin/redirect?wid=wechat_20160122042202
         */

        private List<ListEntity> list;

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public void setPs(int ps) {
            this.ps = ps;
        }

        public void setPno(int pno) {
            this.pno = pno;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public int getPs() {
            return ps;
        }

        public int getPno() {
            return pno;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public static class ListEntity {
            private String id;
            private String title;
            private String source;
            private String firstImg;
            private String mark;
            private String url;

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public void setFirstImg(String firstImg) {
                this.firstImg = firstImg;
            }

            public void setMark(String mark) {
                this.mark = mark;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getSource() {
                return source;
            }

            public String getFirstImg() {
                return firstImg;
            }

            public String getMark() {
                return mark;
            }

            public String getUrl() {
                return url;
            }
        }
    }
}
