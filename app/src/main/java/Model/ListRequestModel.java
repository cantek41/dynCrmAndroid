package Model;

import java.util.List;

/**
 * Created by Cantekin on 10.1.2017.
 * * {
 * "entity":"Activity",
 * "fields":["Id","Subject"],
 * "Sort":{"field":"Subject","dir":"desc"},
 * "filter":{"field":"Subject","op":"contains","val1":"a"},
 * "pageSize":15,
 * "page":1
 * }
 */
public class ListRequestModel {
    private String entity;
    private int SqlId;
    private List<String> fields;
    private int page;
    private int pageSize;
    private Filter filter;
    private Sort sort;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public int getSqlId() {
        return SqlId;
    }

    public void setSqlId(int sqlId) {
        SqlId = sqlId;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter Filter) {
        this.filter = Filter;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}

