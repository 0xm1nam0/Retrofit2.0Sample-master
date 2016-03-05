package models;

import java.util.ArrayList;
import java.util.List;

public class shopResult {

    private int code;
    private String hasmore;
    private String page_total;
    private datas datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getHasmore() {
        return hasmore;
    }

    public void setHasmore(String hasmore) {
        this.hasmore = hasmore;
    }


    public String getPage_total() {
        return page_total;
    }

    public void setPage_total(String page_total) {
        this.page_total = page_total;
    }

    public datas getDatas() {
        return datas;
    }

    public void setDatas(datas datas) {
        this.datas = datas;
    }
    public class datas {
        private List<GoodsBean> goods_list = new ArrayList<GoodsBean>();

        public List<GoodsBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsBean> goods_list) {
            this.goods_list = goods_list;
        }
    }
}