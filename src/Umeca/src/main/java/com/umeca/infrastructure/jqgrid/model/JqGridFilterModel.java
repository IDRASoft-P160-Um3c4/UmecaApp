package com.umeca.infrastructure.jqgrid.model;

import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 4/30/14
 * Time: 1:54 PM
 */
public class JqGridFilterModel
{
    public static final String OPERATOR_AND = "and";
    public static final String OPERATOR_OR = "or";
    public static final String COMPARE_EQUAL = "eq";
    public static final String COMPARE_IN = "in";
    public static final String COMPARE_NOT_IN = "not in";

    public String sidx;
    public String sord;
    public Integer page;
    public Integer rows;
    public Boolean _search;
    public String searchField;
    public String searchOper;
    public String searchString;
    public String filters;
    public List<JqGridRulesModel> extraFilters;

    //public Long primaryId;
    //public String codeItem;

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Boolean get_search() {
        return _search;
    }

    public void set_search(Boolean _search) {
        this._search = _search;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchOper() {
        return searchOper;
    }

    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public List<JqGridRulesModel> getExtraFilters() {
        return extraFilters;
    }

    public void setExtraFilters(List<JqGridRulesModel> extraFilters) {
        this.extraFilters = extraFilters;
    }
    /*
    public long getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(long primaryId) {
        this.primaryId = primaryId;
    }

    public String getCodeItem() {
        return codeItem;
    }

    public void setCodeItem(String codeItem) {
        this.codeItem = codeItem;
    }*/
}