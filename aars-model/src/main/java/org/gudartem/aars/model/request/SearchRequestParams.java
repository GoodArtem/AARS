package org.gudartem.aars.model.request;

import org.gudartem.aars.model.Pair;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SearchRequestParams {
    private Set<String> fetchPlan;

    private Map<String, Pair<String, Object>> searchCondition;

    private List<Pair<String, String>> orderBy;

    private Integer limit;

    private Integer offset;

    public Set<String> getFetchPlan() {
        return fetchPlan;
    }

    public void setFetchPlan(Set<String> fetchPlan) {
        this.fetchPlan = fetchPlan;
    }

    public Map<String, Pair<String, Object>> getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(Map<String, Pair<String, Object>> searchCondition) {
        this.searchCondition = searchCondition;
    }

    public List<Pair<String, String>> getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(List<Pair<String, String>> orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
