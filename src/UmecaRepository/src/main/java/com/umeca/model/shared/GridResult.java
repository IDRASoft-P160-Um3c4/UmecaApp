package com.umeca.model.shared;

import com.umeca.infrastructure.jqgrid.model.JqGridRowsModel;

import java.util.List;

/**
 * Created by Administrator on 12/18/2015.
 */
public class GridResult {
    private Long total;
    public int page;
    public long records;
    private List<JqGridRowsModel> rows;

    public List<JqGridRowsModel> getRows() {
        return rows;
    }

    public void setRows(List<JqGridRowsModel> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
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
}
