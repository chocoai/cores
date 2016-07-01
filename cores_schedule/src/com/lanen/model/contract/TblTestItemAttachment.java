package com.lanen.model.contract;

import java.io.Serializable;
import java.util.Date;

/**
 * 供试品附件表
 * @author Administrator
 *
 */
public class TblTestItemAttachment implements Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 653079453226358941L;

	private String id;
	
	private String testItemCode;		// 供试品编号
	private String attachmentName;		// 附件名称
	private String remark;				// 备注
	private byte[] attachmentFile;		// 附件文件
	private Date appendDate;			// 添加日期
	private String operator;			// 操作者
	private int state;					//状态  0：未生效，1：执行中
	
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTestItemCode() {
		return testItemCode;
	}
	public void setTestItemCode(String testItemCode) {
		this.testItemCode = testItemCode;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public byte[] getAttachmentFile() {
		return attachmentFile;
	}
	public void setAttachmentFile(byte[] attachmentFile) {
		this.attachmentFile = attachmentFile;
	}
	public Date getAppendDate() {
		return appendDate;
	}
	public void setAppendDate(Date appendDate) {
		this.appendDate = appendDate;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}

}
