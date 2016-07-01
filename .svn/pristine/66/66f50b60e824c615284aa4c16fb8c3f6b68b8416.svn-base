package com.lanen.model.qa;

import java.util.HashSet;
import java.util.Set;


/**
 * QalearnTask entity. @author MyEclipse Persistence Tools
 */

public class QALearnTask  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1472373082539608324L;
	private String learnTaskId;
     private String purpose;
     private String student;
     private java.util.Date createTime;
     private Integer learnState;//0：未提交；1：学习中（已提交）；2：完成
     

    // Constructors

    /** default constructor */
    public QALearnTask() {
    }

	/** minimal constructor */
    public QALearnTask(String learnTaskId) {
        this.learnTaskId = learnTaskId;
    }
    
    /** full constructor */
    public QALearnTask(String learnTaskId, String purpose, String student, java.util.Date createTime, Integer learnState) {
        this.learnTaskId = learnTaskId;
        this.purpose = purpose;
        this.student = student;
        this.createTime = createTime;
        this.learnState = learnState;
       
    }

   
    // Property accessors

    public String getLearnTaskId() {
        return this.learnTaskId;
    }
    
    public void setLearnTaskId(String learnTaskId) {
        this.learnTaskId = learnTaskId;
    }

    public String getPurpose() {
        return this.purpose;
    }
    
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getStudent() {
        return this.student;
    }
    
    public void setStudent(String student) {
        this.student = student;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLearnState() {
        return this.learnState;
    }
    
    public void setLearnState(Integer learnState) {
        this.learnState = learnState;
    }

   

}