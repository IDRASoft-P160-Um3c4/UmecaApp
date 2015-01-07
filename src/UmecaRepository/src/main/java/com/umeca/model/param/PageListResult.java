package com.umeca.model.param;

import java.util.List;

public class PageListResult<T> {
    private List<T> records;
    private Long results;

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public Long getResults() {
        return results;
    }

    public void setResults(Long results) {
        this.results = results;
    }
}
