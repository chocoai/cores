package com.lanen.model.qa;



/**
 * QachkReportReader entity. @author MyEclipse Persistence Tools
 */

public class QAChkReportReader  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -7669498647192526007L;
	private String ccId;
     private QAChkReport qachkReport;
     private String ccManCode;
     private String ccManName;
     private String remark;


    // Constructors

    /** default constructor */
    public QAChkReportReader() {
    }

	/** minimal constructor */
    public QAChkReportReader(String ccId) {
        this.ccId = ccId;
    }
    
    /** full constructor */
    public QAChkReportReader(String ccId, QAChkReport qachkReport, String ccManCode, String ccManName, String remark) {
        this.ccId = ccId;
        this.qachkReport = qachkReport;
        this.ccManCode = ccManCode;
        this.ccManName = ccManName;
        this.remark = remark;
    }

   
    // Property accessors

    public String getCcId() {
        return this.ccId;
    }
    
    public void setCcId(String ccId) {
        this.ccId = ccId;
    }

    public QAChkReport getQachkReport() {
        return this.qachkReport;
    }
    
    public void setQachkReport(QAChkReport qachkReport) {
        this.qachkReport = qachkReport;
    }

    public String getCcManCode() {
        return this.ccManCode;
    }
    
    public void setCcManCode(String ccManCode) {
        this.ccManCode = ccManCode;
    }

    public String getCcManName() {
        return this.ccManName;
    }
    
    public void setCcManName(String ccManName) {
        this.ccManName = ccManName;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   








}