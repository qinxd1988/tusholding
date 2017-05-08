package com.TusFinancial.Credit.bean;

import java.util.ArrayList;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/5/8 11:07
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：推荐实体
 */
public class RecommendBean extends BaseBean {

    public String type;

    public Data data;

    public class Data {

        public ArrayList<String> images;

        public String iid;

        public long pub_time;

        public String channel;

        public int source_type;

        public String title;

        public String type;

        public int up_count;

        public String url;

        public int cc_count;

        public int share_count;

        public int view_count;

        public ArrayList<String> algs;

    }

}
