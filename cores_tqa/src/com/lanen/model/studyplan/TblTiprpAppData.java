package com.lanen.model.studyplan;

import java.io.Serializable;
/**
 * 配置申请数据
 * @author Administrator
 *
 */
public class TblTiprpAppData implements Serializable{

	private static final long serialVersionUID = -8556877555029173647L;
	private String id;                                //主键，自增长
	private TblTiprpAppInd tblTiprpAppInd;             
	private int AppSn;                   //申请编号    1,2,3,4,5....
	private String AniCode;//动物编号
	private String AniWeight;//动物体重
	private String SmplWeight;//供试品重量（计算）
	private String WeighUnit;//重量单位
	private int CapsNum;//胶囊数量（计算）
	private String StudyNo;              //课题编号
	
	public String getStudyNo() {
		return StudyNo;
	}
	public void setStudyNo(String studyNo) {
		StudyNo = studyNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    
	public TblTiprpAppInd getTblTiprpAppInd() {
		return tblTiprpAppInd;
	}
	public void setTblTiprpAppInd(TblTiprpAppInd tblTiprpAppInd) {
		this.tblTiprpAppInd = tblTiprpAppInd;
	}
	public int getAppSn() {
		return AppSn;
	}
	public void setAppSn(int appSn) {
		AppSn = appSn;
	}
	public String getAniCode() {
		return AniCode;
	}
	public void setAniCode(String aniCode) {
		AniCode = aniCode;
	}
	public String getAniWeight() {
		return AniWeight;
	}
	public void setAniWeight(String aniWeight) {
		AniWeight = aniWeight;
	}
	public String getSmplWeight() {
		return SmplWeight;
	}
	public void setSmplWeight(String smplWeight) {
		SmplWeight = smplWeight;
	}
	public String getWeighUnit() {
		return WeighUnit;
	}
	public void setWeighUnit(String weighUnit) {
		WeighUnit = weighUnit;
	}
	public int getCapsNum() {
		return CapsNum;
	}
	public void setCapsNum(int capsNum) {
		CapsNum = capsNum;
	}
	
	
   
	
	

}
