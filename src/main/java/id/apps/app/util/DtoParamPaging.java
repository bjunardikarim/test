package id.apps.app.util;

import java.util.Map;

public class DtoParamPaging {
	
    private int offset;
    private int limit;
    private int page;
    private String sort;
    private String order;
    private Map<String, Object> search;
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public Map<String, Object> getSearch() {
		return search;
	}
	public void setSearch(Map<String, Object> search) {
		this.search = search;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
}
