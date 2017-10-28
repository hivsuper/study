package org.lxp.study.util;

import java.util.List;

public class Page<T> {
  public static final String DEFAULT_PAGE_SIZE = "10";
  public static final String DEFAULT_CURRENT_PAGE = "1";

  private int currentPage = Integer.valueOf(DEFAULT_CURRENT_PAGE);
  private int totalSize;
  private int pageSize = Integer.valueOf(DEFAULT_PAGE_SIZE);

  private List<T> objs;

  public Page() {
  }

  public Page(int currentPage) {
    this.currentPage = currentPage;
  }

  public Page(int currentPage, int pageSize) {
    setCurrentPage(currentPage);
    setPageSize(pageSize);
  }

  public int getNextPage() {
    return currentPage == getTotalPage() ? currentPage : currentPage + 1;
  }

  public int getPrePage() {
    return currentPage > 1 ? currentPage - 1 : 1;
  }

  public int getOffset() {
    return getCurPageFirstRecNum() - 1;
  }

  public int getCurPageFirstRecNum() {
    return (getCurrentPage() - 1) * pageSize + 1;
  }

  public int getCurPageLastRecNum() {
    return getCurrentPage() * pageSize;
  }

  public int getTotalPage() {
    int t = totalSize % pageSize > 0 ? totalSize / pageSize + 1 : totalSize / pageSize;
    if (t <= 0) {
      t = 1;
    }
    return t;
  }

  public void setObjs(List<T> objs) {
    this.objs = objs;
  }

  public List<T> getObjs() {
    return objs;
  }

  public int getCurrentPage() {
    if (currentPage > getTotalPage()) {
      currentPage = getTotalPage();
    }
    if (currentPage <= 0) {
      currentPage = 1;
    }
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public int getTotalSize() {
    return totalSize;
  }

  public void setTotalSize(int totalSize) {
    this.totalSize = totalSize;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    if (pageSize > 0) {
      this.pageSize = pageSize;
    }
  }

  public boolean containData() {
    return getTotalSize() > 0;
  }

  @Override
  public String toString() {
    return "Page [currentPage=" + currentPage + ", totalSize=" + totalSize + ", pageSize=" + pageSize + ", objs="
        + objs + "]";
  }

}

