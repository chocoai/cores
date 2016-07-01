package com.lanen.model.qa;



/**
 * DictChkItemChkTblReg entity. @author MyEclipse Persistence Tools
 */

public class DictChkItemStudyGroupReg  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -7288387464808589463L;
	private String chkItemStudyGroupRegId;
     private DictStudyGroup dictStudyGroup;
     private DictQACheckItem dictQacheckItem;
     private java.util.Date beginDate;
     private java.util.Date endDate;
     
     private Integer chkFreqFlag;
     private Integer chkFreq;
     private String chkFreqUnit;

    // Constructors

    /** default constructor */
    public DictChkItemStudyGroupReg() {
    }

	/** minimal constructor */
    public DictChkItemStudyGroupReg(String chkItemStudyGroupRegId) {
        this.chkItemStudyGroupRegId = chkItemStudyGroupRegId;
    }
    
    /** full constructor */
    public DictChkItemStudyGroupReg(String chkItemStudyGroupRegId, DictStudyGroup dictStudyGroup, DictQACheckItem dictQacheckItem, java.util.Date beginDate, java.util.Date endDate) {
        this.chkItemStudyGroupRegId = chkItemStudyGroupRegId;
        this.dictStudyGroup = dictStudyGroup;
        this.dictQacheckItem = dictQacheckItem;
        this.beginDate = beginDate;
        this.endDate = endDate;
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
    
    public DictStudyGroup getDictStudyGroup() {
		return dictStudyGroup;
	}

	public void setDictStudyGroup(DictStudyGroup dictStudyGroup) {
		this.dictStudyGroup = dictStudyGroup;
	}

	public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }

	public String getChkItemStudyGroupRegId() {
		return chkItemStudyGroupRegId;
	}

	public void setChkItemStudyGroupRegId(String chkItemStudyGroupRegId) {
		this.chkItemStudyGroupRegId = chkItemStudyGroupRegId;
	}

	public Integer getChkFreqFlag() {
		return chkFreqFlag;
	}

	public void setChkFreqFlag(Integer chkFreqFlag) {
		this.chkFreqFlag = chkFreqFlag;
	}

	public Integer getChkFreq() {
		return chkFreq;
	}

	public void setChkFreq(Integer chkFreq) {
		this.chkFreq = chkFreq;
	}

	public String getChkFreqUnit() {
		return chkFreqUnit;
	}

	public void setChkFreqUnit(String chkFreqUnit) {
		this.chkFreqUnit = chkFreqUnit;
	}
   








}