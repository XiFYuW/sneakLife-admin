package com.sneaklife.page;

import java.util.List;

public class PageInfo {
	/**
	 * 页码
	 */
	private Integer page = 1;
	/**
	 * 页的大小
	 */
	private Integer pageSize = 10;
	/**
	 * 页的数据
	 */
	private List<?> rows;
	/**
	 * 总数
	 */
	private Long total;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"page\":")
				.append(page);
		sb.append(",\"pageSize\":")
				.append(pageSize);
		sb.append(",\"rows\":")
				.append(rows);
		sb.append(",\"total\":")
				.append(total);
		sb.append('}');
		return sb.toString();
	}
}
