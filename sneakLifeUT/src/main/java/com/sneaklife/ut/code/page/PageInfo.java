package com.sneaklife.ut.code.page;

public class PageInfo {

	private Integer page;

	private Integer rows;

	private String sort;

	private String sortOrder;

	public PageInfo() { super();}

	public PageInfo(Integer page, Integer rows, String sort, String sortOrder) {
		this.page = page;
		this.rows = rows;
		this.sort = sort;
		this.sortOrder = sortOrder;
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

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"page\":")
				.append(page);
		sb.append(",\"rows\":")
				.append(rows);
		sb.append(",\"sort\":\"")
				.append(sort).append('\"');
		sb.append(",\"sortOrder\":\"")
				.append(sortOrder).append('\"');
		sb.append('}');
		return sb.toString();
	}
}
