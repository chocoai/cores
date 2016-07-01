package com.lanen.model.path;

import java.util.Date;

/**组织切片编号索引
 * @author Administrator
 *
 */
public class TblTissueSliceIndex implements java.io.Serializable {

	private static final long serialVersionUID = -728770127813267884L;

	private String id;              //
	private String studyNo;			//专题编号20
	private String taskId;          //任务Id号20
	private int sliceCodeType;      //切片编号类型0：常规组织	1：非常规组织2：加做常规组织
	private String operatorSign;    //操作者签字20
	private Date createTime;        //创建时间
	private int gender ;			//实用性别  1：雄 2：雌  3：所有
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStudyNo() {
		return studyNo;
	}
	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public int getSliceCodeType() {
		return sliceCodeType;
	}
	public void setSliceCodeType(int sliceCodeType) {
		this.sliceCodeType = sliceCodeType;
	}
	public String getOperatorSign() {
		return operatorSign;
	}
	public void setOperatorSign(String operatorSign) {
		this.operatorSign = operatorSign;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	
}
