package com.blaze.demo.rest.responses;

import java.util.ArrayList;
import java.util.List;

/**
 * This class was intended to be used in some crud tables, because there we need pagination but since time...
 */
public class SearchResult<T> {
    private List<T> values = new ArrayList<>();
    private int skip;
    private int limit;
    private Long total = 0l;
    private String locationId;

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

}
