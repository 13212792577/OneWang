package com.wanglipeng.a32014.onewang.bean;

import java.util.List;

/**
 * Created by wanglipeng on 2016/9/21.
 */
public class ReadTopData {

    /**
     * id : 105
     * title : 要做你自己，因为没人想做你
     * cover : http://image.wufazhuce.com/FgA7ELCwoaD7NBJcpyKsF_jxdIS9
     * bottom_text : “我要有能做我自己的自由， 和敢做我自己的胆量。”
     * bgcolor : #fc553b
     * pv_url : http://v3.wufazhuce.com:8000/api/reading/carousel/pv/105
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String title;
        private String cover;
        private String bottom_text;
        private String bgcolor;
        private String pv_url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getBottom_text() {
            return bottom_text;
        }

        public void setBottom_text(String bottom_text) {
            this.bottom_text = bottom_text;
        }

        public String getBgcolor() {
            return bgcolor;
        }

        public void setBgcolor(String bgcolor) {
            this.bgcolor = bgcolor;
        }

        public String getPv_url() {
            return pv_url;
        }

        public void setPv_url(String pv_url) {
            this.pv_url = pv_url;
        }
    }
}
