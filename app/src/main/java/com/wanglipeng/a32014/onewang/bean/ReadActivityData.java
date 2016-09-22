package com.wanglipeng.a32014.onewang.bean;

import java.util.List;

/**
 * Created by wanglipeng on 2016/9/21.
 */
public class ReadActivityData {

    /**
     * item_id : 734
     * title : 我没有成为自己不喜欢的样子
     * introduction : 我做得到的，我竭力做到最好；我做不到的，可能我永远也做不到。或许生命并不意味着成为了什么、做到了什么花好月圆，它原本就是这般的自在安然。
     * author : 严明
     * web_url :
     * number : 0
     * type : 1
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String item_id;
        private String title;
        private String introduction;
        private String author;
        private String web_url;
        private int number;
        private String type;

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
