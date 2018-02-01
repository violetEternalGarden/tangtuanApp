package com.cj.tangtuan.utils.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 封装分页数据信息
 * @author 创建人：何辉
 * @author 时间：2016-6-17
 */

/***************
 * DESCRIBE************************
 ***********************************************/
public class QueryBase implements Serializable {

	private final static long serialVersionUID = -977211858753607189L;

	private final static long defaultFirstRow = 0;
	private final static long defaultTotalRow = 0;
	private final static long firstPage = 1;
	private final static long defaultPageSize = 10;
	private final static long defaultTotalPage = 0;

	private Long firstRow = defaultFirstRow;  //LIMIT中的第一个参数，开始下标
	private Long maxRow = defaultPageSize;  //LIMIT中的第二个参数，从下标开始查询的条数
	private Long totalRow = defaultTotalRow;  //总条数

	private Long currentPage = firstPage;  //开始页码
	private Long pageSize = defaultPageSize;  //查询条数
	private Long totalPage = defaultTotalPage;  //总页数

	private String sidx;
	private String sord;

	private Map<String, Object> parameters=new HashMap<String, Object>();
	private List<? extends Object> results;

	public boolean isFirstPage() {
		return this.currentPage == firstPage;
	}

	public boolean isLastPage() {
		return this.currentPage == this.totalPage;
	}

	public boolean nextPage() {
		if (this.totalRow == defaultTotalRow || this.currentPage == this.totalPage)
			return false;
		this.setCurrentPage(this.getNextPage());
		return true;
	}

	public boolean previousPage() {
		if (this.totalRow == defaultTotalRow || this.currentPage == firstPage)
			return false;
		this.setCurrentPage(this.getPreviousPage());
		return true;
	}

	public Long getNextPage() {
		Long next = this.currentPage + 1;
		return next > this.totalPage ? this.totalPage : next;
	}

	public Long getPreviousPage() {
		Long previous = this.currentPage - 1;
		return previous < firstPage ? firstPage : previous;
	}

	public void setTotalRow(Long totalRow) {
		if (totalRow > 0 && totalRow != this.totalRow) {
			this.totalRow = totalRow;
			this.totalPage = totalRow / this.pageSize;
			if (totalRow % this.pageSize != 0)
				this.totalPage++;
		}
	}

	public void setTotalRow() {
		this.setTotalRow(defaultTotalRow);
	}

	public Long getTotalRow() {
		return totalRow;
	}

	public void setCurrentPage(Long currentPage) {
		if (currentPage > 0) {
			this.currentPage = currentPage;
			this.firstRow = (currentPage - 1) * this.pageSize;
		}
	}

	public void updatePages() {
		setCurrentPage(this.getCurrentPage());
	}

	public void setCurrentPage() {
		this.currentPage = firstPage;
		this.firstRow = (this.currentPage - 1) * this.pageSize;
	}

	public Long getCurrentPage() {
		return currentPage;
	}

	public void setPageSize(Long pageSize) {
		if (pageSize > 0) {
			this.pageSize = pageSize;
			this.maxRow = pageSize;
			this.setCurrentPage(this.currentPage);
		}
	}

	public void setPageSize() {
		this.pageSize = defaultPageSize;
		this.maxRow = defaultPageSize;
		this.setCurrentPage(this.currentPage);
	}

	public Long getPageSize() {
		return pageSize;
	}

	public Long getDefaultPageSize() {
		return defaultPageSize;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public void setFirstRow(Long firstRow) {
		if (firstRow >= 0) {
			this.firstRow = firstRow;
			this.currentPage = 1 + (Long) (firstRow / this.pageSize);
		}
	}

	public Long getFirstRow() {
		return firstRow;
	}

	public void setMaxRow(Long maxRow) {
		if (maxRow > 0) {
			this.pageSize = maxRow;
			this.maxRow = maxRow;
		}
	}

	public Long getMaxRow() {
		return maxRow;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void addParameter(String key, Object value) {
		parameters.put(key, value);
	}

	public void removeParameter(String key) {
		parameters.remove(key);
	}

	public Object getParameter(String key) {
		return parameters.get(key);
	}

	public void setResults(List<? extends Object> results) {
		this.results = results;
	}

	public List<? extends Object> getResults() {
		return results;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getSord() {
		return sord;
	}

	@Override
	public String toString() {
		return "QueryBase [firstRow=" + firstRow + ", maxRow=" + maxRow + ", totalRow=" + totalRow + ", currentPage="
				+ currentPage + ", pageSize=" + pageSize + ", totalPage=" + totalPage + ", sidx=" + sidx + ", sord="
				+ sord + ", parameters=" + parameters + ", results=" + results + "]";
	}

	// 重写toString

}