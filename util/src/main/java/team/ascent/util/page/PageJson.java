package team.ascent.util.page;

import java.util.List;


public class PageJson<E>  extends team.ascent.util.JsonResult{
    private int current;//当前页
    private int rowCount;//总页数
    private int total;//总记录数
    private List<E> rows;
    public PageJson(int current){
    	this.current = current;
    }
    public  PageJson(int current,int rowCount, int total,List<E> rows){
    	this.current = current;
    	this.rowCount = rowCount;
    	this.total = total;
    	this.rows = rows;
    }
    
    
    public PageJson<E> setPageJson(PageList<E> pageList){
    	this.setRowCount(pageList.getTotalPages());
    	this.setTotal(pageList.getTotalItems());
    	this.setRows(pageList.getItems());
    	return this;
    	
    }
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<E> getRows() {
		return rows;
	}
	public void setRows(List<E> rows) {
		this.rows = rows;
	}
    
}
