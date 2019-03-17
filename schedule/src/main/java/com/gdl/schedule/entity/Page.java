package com.gdl.schedule.entity;

import java.io.Serializable;

public class Page implements Serializable {

	private static final long serialVersionUID = 3611152262637082839L;

	private int pageNow = 1; // 当前页数
	private int pageSize = 10; // 每页显示记录的条数
	private int totalCount; // 总的记录条数
	private int totalPageCount; // 总的页数
	@SuppressWarnings("unused")	//用于抑制编译器产生警告信息
	private int startPos; // 开始位置，从0开始

	/**
	 * 通过构造函数 传入 总记录数 和 当前页
	 * 
	 * @param totalCount
	 * @param pageNow
	 */
	public Page(int totalCount, int pageNow) {
		this.totalCount = totalCount;
		this.pageNow = pageNow;
	}

	
	public Page(int pageNow, int pageSize, int totalCount, int totalPageCount, int startPos) {
		super();
		this.pageNow = pageNow;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPageCount = totalPageCount;
		this.startPos = startPos;
	}


	/**
	 * 取得总页数，总页数=总记录数/每页显示记录的条数
	 * 
	 * @return
	 */
	public int getTotalPageCount() {
		totalPageCount = getTotalCount() / getPageSize();
		return (totalCount % pageSize == 0) ? totalPageCount // 总页数
				: totalPageCount + 1;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 取得选择记录的初始位置
	 * 
	 * @return
	 */
	public int getStartPos() {
		return (pageNow - 1) * pageSize;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	@Override
	public String toString() {
		return "Page{" +
				"pageNow=" + pageNow +
				", pageSize=" + pageSize +
				", totalCount=" + totalCount +
				", totalPageCount=" + totalPageCount +
				", startPos=" + startPos +
				'}';
	}
}
