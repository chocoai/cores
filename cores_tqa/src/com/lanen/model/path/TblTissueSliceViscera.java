package com.lanen.model.path;

/**
 * 组织切片编号
 * @author 黄国刚
 *
 */
public class TblTissueSliceViscera implements java.io.Serializable{

	private static final long serialVersionUID = 4813860619108305794L;

	private String id;             //
	private String tissueSliceSnId;        //切片序号id 20
	private int visceraType;       //脏器类型 
	private String visceraCode;    //脏器编号(主)20
	private String visceraName;    //脏器名称(主)60
	private String subVisceraCode; //脏器编号(子)20
	private String subVisceraName; //脏器名称(子)60
	private String visceraFixedRecordId; //脏器固定记录id20
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getVisceraType() {
		return visceraType;
	}
	public void setVisceraType(int visceraType) {
		this.visceraType = visceraType;
	}
	public String getVisceraCode() {
		return visceraCode;
	}
	public void setVisceraCode(String visceraCode) {
		this.visceraCode = visceraCode;
	}
	public String getVisceraName() {
		return visceraName;
	}
	public void setVisceraName(String visceraName) {
		this.visceraName = visceraName;
	}
	public String getSubVisceraCode() {
		return subVisceraCode;
	}
	public void setSubVisceraCode(String subVisceraCode) {
		this.subVisceraCode = subVisceraCode;
	}
	public String getSubVisceraName() {
		return subVisceraName;
	}
	public void setSubVisceraName(String subVisceraName) {
		this.subVisceraName = subVisceraName;
	}
	public String getTissueSliceSnId() {
		return tissueSliceSnId;
	}
	public void setTissueSliceSnId(String tissueSliceSnId) {
		this.tissueSliceSnId = tissueSliceSnId;
	}
	public String getVisceraFixedRecordId() {
		return visceraFixedRecordId;
	}
	public void setVisceraFixedRecordId(String visceraFixedRecordId) {
		this.visceraFixedRecordId = visceraFixedRecordId;
	}
	
	
	
}
