package com.lanen.model.qa;




/**
 * DictChkItemChkTblReg entity. @author MyEclipse Persistence Tools
 */

public class DictChkItemChkTblReg  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -7288387464808589463L;
	private String chkItemChkTblRegId;
     private DictQACheckTable dictQacheckTable;
     private DictQACheckItem dictQacheckItem;
     private java.util.Date beginDate;
     private java.util.Date endDate;


    // Constructors

    /** default constructor */
    public DictChkItemChkTblReg() {
    }

	/** minimal constructor */
    public DictChkItemChkTblReg(String chkItemChkTblRegId) {
        this.chkItemChkTblRegId = chkItemChkTblRegId;
    }
    
    /** full constructor */
    public DictChkItemChkTblReg(String chkItemChkTblRegId, DictQACheckTable dictQacheckTable, DictQACheckItem dictQacheckItem, java.util.Date beginDate, java.util.Date endDate) {
        this.chkItemChkTblRegId = chkItemChkTblRegId;
        this.dictQacheckTable = dictQacheckTable;
        this.dictQacheckItem = dictQacheckItem;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

   
    // Property accessors

    public String getChkItemChkTblRegId() {
        return this.chkItemChkTblRegId;
    }
    
    public void setChkItemChkTblRegId(String chkItemChkTblRegId) {
        this.chkItemChkTblRegId = chkItemChkTblRegId;
    }

    public DictQACheckTable getDictQacheckTable() {
        return this.dictQacheckTable;
    }
    
    public void setDictQacheckTable(DictQACheckTable dictQacheckTable) {
        this.dictQacheckTable = dictQacheckTable;
    }

    public DictQACheckItem getDictQacheckItem() {
        return this.dictQacheckItem;
    }
    
    public void setDictQacheckItem(DictQACheckItem dictQacheckItem) {
        this.dictQacheckItem = dictQacheckItem;
    }

    public java.util.Date getBeginDate() {
        return this.beginDate;
    }
    
    public void setBeginDate(java.util.Date beginDate) {
        this.beginDate = beginDate;
    }

    public java.util.Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }
   








}