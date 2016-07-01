package com.lanen.model.qa;

import java.util.HashSet;
import java.util.Set;


/**
 * QafileType entity. @author MyEclipse Persistence Tools
 */

public class QAFileType  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 4012313763240015768L;
	private String fileTypeId;
     private Integer fileType;
     private String fileTypeName;
     private String parentFileTypeId;
     private Set qafileRegs;
     
    // Constructors

    /** default constructor */
    public QAFileType() {
    }

	/** minimal constructor */
    public QAFileType(String fileTypeId) {
        this.fileTypeId = fileTypeId;
    }
    
    /** full constructor */
    public QAFileType(String fileTypeId, Integer fileType, String fileTypeName, String parentFileTypeId) {
        this.fileTypeId = fileTypeId;
        this.fileType = fileType;
        this.fileTypeName = fileTypeName;
        this.parentFileTypeId = parentFileTypeId;
    }

   
    // Property accessors

    public String getFileTypeId() {
        return this.fileTypeId;
    }
    
    public void setFileTypeId(String fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    public Integer getFileType() {
        return this.fileType;
    }
    
    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getFileTypeName() {
        return this.fileTypeName;
    }
    
    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    public String getParentFileTypeId() {
        return this.parentFileTypeId;
    }
    
    public void setParentFileTypeId(String parentFileTypeId) {
        this.parentFileTypeId = parentFileTypeId;
    }

	public Set getQafileRegs() {
		return qafileRegs;
	}

	public void setQafileRegs(Set qafileRegs) {
		this.qafileRegs = qafileRegs;
	}



}