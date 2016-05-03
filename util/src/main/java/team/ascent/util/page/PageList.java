
package team.ascent.util.page;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 包含“分页”信息的List
 * 
 * 要得到总页数请使用 toPaginator().getTotalPages()
 * 
 */
public class PageList<E> implements Serializable {
	
	private static final long serialVersionUID = 9014902923937298609L;

	/** 页数  */
	private int page = 1;
	
	/** 分页大小 */
	private int pageSize = Paginator.DEFAULT_PAGE_SIZE;
	
	/**
	 * 排序字段
	 */
	public String sortColumns;
   
	/** 总记录数 */
    private int               	totalItems;
    
    private List<E> 			items;

    public PageList() {}
    
	public PageList(List<E> c) {
		this.items = c;
	}

	public PageList(int page, int pageSize, int totalItems) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
    }
	
	public PageList(List<E> c,int page, int pageSize, int totalItems) {
		this.items = c;
        this.page = page;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
    }

    public PageList(Paginator p) {
        this.page = p.getPage();
        this.pageSize = p.getPageSize();
        this.totalItems = p.getTotalItems();
    }
    
    public PageList(List<E> c,Paginator p) {
    	this.items = c;
        this.page = p.getPage();
        this.pageSize = p.getPageSize();
        this.totalItems = p.getTotalItems();
    }/**/
    
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

	public String getSortColumns() {
		return sortColumns;
	}

	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	
	public List<E> getItems() {
		return items;
	}

	public void setItems(List<E> items) {
		this.items = items;
	}

	/**
	 * 得到分页器，通过Paginator可以得到总页数等值
	 * @return
	 */
	public Paginator getPaginator() {
		return new Paginator(page,pageSize,totalItems);
	}
	
	
	
	/**
	 * 开始行，可以用于oracle分页使用 (1-based)。
	 **/
	public int getStartRow() {
		if(getPageSize() <= 0) return 0;
		return page > 0 ? (page - 1) * getPageSize() + 1 : 0;
	}
	
	/**
     * 结束行，可以用于oracle分页使用 (1-based)。
     **/
	public int getEndRow() {
	    return page > 0 ? pageSize * page : 0; 
	}
	
    /**
     * offset，计数从0开始，可以用于mysql分页使用(0-based)
     **/	
	public int getOffset() {
		return page > 0 ? (page - 1) * getPageSize() : 0;
	}
	
	/**
     * limit，可以用于mysql分页使用(0-based)
     **/
    public int getLimit() {
        if (page > 0) {
            return pageSize * page - (pageSize * (page - 1));
        } else {
            return 0;
        }
    }
	
	public String toString() {
		return "page:" + page + ",pageSize:" + pageSize + ",sortColumns:" + sortColumns;
	}
	
	/**
	 * 得到 总页数
	 * 
	 * @return
	 */
	public int getTotalPages() {
		if (totalItems <= 0) {
			return 0;
		}
		if (pageSize <= 0) {
			return 0;
		}

		int count = totalItems / pageSize;
		if (totalItems % pageSize > 0) {
			count++;
		}
		return count;
	}
	/**
	 * 是否有上一页
	 * 
	 * @return 上一页标识
	 */
	public boolean getIsHasPrePage() {
		return (page - 1 >= 1);
	}

	/**
	 * 是否有下一页
	 * 
	 * @return 下一页标识
	 */
	public boolean getIsHasNextPage() {
		return (page + 1 <= getTotalPages());
	}
}
