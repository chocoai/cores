package com.lanen.model.qa;

import java.util.HashSet;
import java.util.Set;


/**
 * DictQacheckTable entity. @author MyEclipse Persistence Tools
 */

public class DictQACheckTable  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 6983452930824333127L;
	private String chkTblId;
     private String chkTblCode;
     private String chkTblName;
     private java.util.Date beginDate;
     private java.util.Date endDate;
   

    // Constructors

    /** default constructor */
    public DictQACheckTable() {
    }

	/** minimal constructor */
    public DictQACheckTable(String chkTblId) {
        this.chkTblId = chkTblId;
    }
    
    /** full constructor */
    public DictQACheckTable(String chkTblId, String chkTblCode, String chkTblName, java.util.Date beginDate, java.util.Date endDate) {
        this.chkTblId = chkTblId;
        this.chkTblCode = chkTblCode;
        this.chkTblName = chkTblName;
        this.beginDate = beginDate;
        this.endDate = endDate;
        
    }

   
    // Property accessors

    public String getChkTblId() {
        return this.chkTblId;
    }
    
    public void setChkTblId(String chkTblId) {
        this.chkTblId = chkTblId;
    }

    public String getChkTblCode() {
        return this.chkTblCode;
    }
    
    public void setChkTblCode(String chkTblCode) {
        this.chkTblCode = chkTblCode;
    }

    public String getChkTblName() {
        return this.chkTblName;
    }
    
    public void setChkTblName(String chkTblName) {
        this.chkTblName = chkTblName;
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