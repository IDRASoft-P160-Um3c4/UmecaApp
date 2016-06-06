package com.umeca.infrastructure.jqgrid.model;

import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 4/30/14
 * Time: 1:45 PM
 */
public class JqGridResultModel<T>
{
    public int total;
    public int page;
    public long records;
    public List<JqGridRowsModel> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public List<JqGridRowsModel> getRows() {
        return rows;
    }

    public void setRows(List<JqGridRowsModel> rows) {
        this.rows = rows;
    }
}
