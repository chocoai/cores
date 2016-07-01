package com.lanen.model.qa;



public class DictChkItemQAFileReg  implements java.io.Serializable {

	private static final long serialVersionUID = 401148179613798256L;
	private String chkItemQAFileRegId;
     private String fileRegId;//qafilereg
     private DictQACheckItem dictQacheckItem;
     private java.util.Date beginDate;
     private java.util.Date endDate;


    // Constructors

    /** default constructor */
    public DictChkItemQAFileReg() {
    }

	/** minimal constructor */
    public DictChkItemQAFileReg(String chkItemQAFileRegId) {
        this.chkItemQAFileRegId = chkItemQAFileRegId;
    }
    
    /** full constructor */
    public DictChkItemQAFileReg(String chkItemQAFileRegId, String fileRegId, DictQACheckItem dictQacheckItem, java.util.Date beginDate, java.util.Date endDate) {
        this.chkItemQAFileRegId = chkItemQAFileRegId;
        this.fileRegId = fileRegId;
        this.dictQacheckItem = dictQacheckItem;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

   
    // Property accessors

  
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


	public String getFileRegId() {
		return fileRegId;
	}

	public void setFileRegId(String fileRegId) {
		this.fileRegId = fileRegId;
	}

	public String getChkItemQAFileRegId() {
		return chkItemQAFileRegId;
	}

	public void setChkItemQAFileRegId(String chkItemQAFileRegId) {
		this.chkItemQAFileRegId = chkItemQAFileRegId;
	}
   








}