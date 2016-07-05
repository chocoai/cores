package com.lanen.model;

import java.io.Serializable;
import java.util.Date;

public class Approval implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6283796857812065714L;
    private Long id;       //许可信息表
    private String title;  //申请标题（申请的名称）
    private String phao;   //许可批号
    private String head;   //申请主题
    private String content;//申请内容
    private Date createtime;//许可信息录入时间
    private Long modified_by;//信息修改者
    private Long created_by; //信息录入者
    private Date lastmodifytime;//最后修改时间
    private int deleted;       //删除标记0：未删除1.删除
    private Date approvaldate;  //批准日期
    private String pic;         //未知字段（不用设为NULL）
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhao() {
		return phao;
	}
	public void setPhao(String phao) {
		this.phao = phao;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Long getModified_by() {
		return modified_by;
	}
	public void setModified_by(Long modifiedBy) {
		modified_by = modifiedBy;
	}
	public Long getCreated_by() {
		return created_by;
	}
	public void setCreated_by(Long createdBy) {
		created_by = createdBy;
	}
	public Date getLastmodifytime() {
		return lastmodifytime;
	}
	public void setLastmodifytime(Date lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public Date getApprovaldate() {
		return approvaldate;
	}
	public void setApprovaldate(Date approvaldate) {
		this.approvaldate = approvaldate;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
    
}
