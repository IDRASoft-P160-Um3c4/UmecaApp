package com.umeca.model.param;

public class ParamModel {

    private Integer page;
    private Integer start;
    private Integer limit;

    public Integer getPage() {

        if(page == null)
            return 0;

        return page-1;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {

        if(limit == null)
            return 0;

        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
