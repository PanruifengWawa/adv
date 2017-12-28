package com.adv.utils;


public class DataWrapper<T>  {
    private int status;
    private T data;

    // 用于分页结果
    private int numberPerPage;
    private int currentPage;
    private int totalNumber;
    private int totalPage;
    
    
   

	public DataWrapper() {
		status = 0;
    }

   

    public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

 

    public int getNumberPerPage() {
        return numberPerPage;
    }

    public void setNumberPerPage(int numberPerPage) {
        this.numberPerPage = numberPerPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return	"Page :" + this.currentPage + "\n" +
                "Total Page :" + this.totalPage + "\n" +
                "Num per Page:" + this.numberPerPage + "\n" +
                "Total Num:" + this.totalNumber + "\n" ;
    }
}
