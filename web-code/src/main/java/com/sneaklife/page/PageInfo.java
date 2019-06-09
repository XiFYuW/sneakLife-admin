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
	private Long totalCount;

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

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return "PageInfo [page=" + page + ", pageSize=" + pageSize + ", rows=" + rows + ", totalCount=" + totalCount
				+ "]";
	}

}
