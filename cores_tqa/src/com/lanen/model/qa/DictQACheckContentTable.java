package com.lanen.model.qa;



/**
 * DictQacheckContentTable entity. @author MyEclipse Persistence Tools
 */

public class DictQACheckContentTable  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 6311948426372014243L;
	private String chkTblContentId;
     private DictQACheckTable dictQacheckTable;
     private Integer sn;
     private String chkContent;
     private String remark;
     private java.util.Date begindate;
     private java.util.Date enddate;


    // Constructors

    /** default constructor */
    public DictQACheckContentTable() {
    }

	/** minimal constructor */
    public DictQACheckContentTable(String chkTblContentId) {
        this.chkTblContentId = chkTblContentId;
    }
    
    /** full constructor */
    public DictQACheckContentTable(String chkTblContentId, DictQACheckTable dictQacheckTable, Integer sn, String chkContent, String remark, java.util.Date begindate, java.util.Date enddate) {
        this.chkTblContentId = chkTblContentId;
        this.dictQacheckTable = dictQacheckTable;
        this.sn = sn;
        this.chkContent = chkContent;
        this.remark = remark;
        this.begindate = begindate;
        this.enddate = enddate;
    }

   
    // Property accessors

    public String getChkTblContentId() {
        return this.chkTblContentId;
    }
    
    public void setChkTblContentId(String chkTblContentId) {
        this.chkTblContentId = chkTblContentId;
    }

    public DictQACheckTable getDictQacheckTable() {
        return this.dictQacheckTable;
    }
    
    public void setDictQacheckTable(DictQACheckTable dictQacheckTable) {
        this.dictQacheckTable = dictQacheckTable;
    }

    public Integer getSn() {
        return this.sn;
    }
    
    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public String getChkContent() {
        return this.chkContent;
    }
    
    public void setChkContent(String chkContent) {
        this.chkContent = chkContent;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public java.util.Date getBegindate() {
        return this.begindate;
    }
    
    public void setBegindate(java.util.Date begindate) {
        this.begindate = begindate;
    }

    public java.util.Date getEnddate() {
        return this.enddate;
    }
    
    public void setEnddate(java.util.Date enddate) {
        this.enddate = enddate;
    }
   








}