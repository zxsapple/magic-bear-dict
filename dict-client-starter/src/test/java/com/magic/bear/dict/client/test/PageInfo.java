package com.magic.bear.dict.client.test;

import java.util.List;

/**
 * @author zxs
 * @date 2020/12/7 15:34
 * @desc
 */

public class PageInfo<T> {
    //总数
   private long total;

    //数据列表
    private List<T> listInfo;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getListInfo() {
        return listInfo;
    }

    public void setListInfo(List<T> listInfo) {
        this.listInfo = listInfo;
    }
}
