package com.xisuo.sellmanager.utils;

import com.xisuo.sellmanager.constant.Constant;

import java.io.Serializable;
import java.util.List;

/**
 * @author zk
 * @Description: 分页的工具类
 * @date 2021-07-18 13:59
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页数
     */
    private int pageNo;
    /**
     * 每页显示记录数
     */
    private int pageSize;
    /**
     * 总记录数
     */
    private int totalCounts;
    /**
     * 总页数
     */
    private int totalPages;
    /**
     * 当前记录起始索引
     */
    private int pageNum;
    /**
     * 列表数据
     */
    private List<T> data;

    /**
     * @param pageNo      当前页
     * @param totalCounts 总记录数
     * @param data        数据
     */
    public Page(int pageNo, int totalCounts, List<T> data) {
        this(pageNo, Constant.PAGE_SIZE, totalCounts, data);
    }


    /**
     * 默认第一页,一页15个
     *
     * @param totalCounts 总记录数
     * @param data        数据
     */
    public Page(int totalCounts, List<T> data) {
        this(Constant.PAGE, Constant.PAGE_SIZE, totalCounts, data);
    }


    /**
     * @param pageNo      第几页
     * @param pageSize    一页多少个
     * @param totalCounts 总记录数
     * @param data        数据
     */
    public Page(int pageNo, int pageSize, int totalCounts, List<T> data) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCounts = totalCounts;
        this.totalPages = (totalCounts % pageSize == 0) ? totalCounts / pageSize : totalCounts / pageSize + 1;
        this.pageNum = (pageNo - 1) * pageSize;
        this.data = data;
    }


    private Page() {
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(int totalCounts) {
        this.totalCounts = totalCounts;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
