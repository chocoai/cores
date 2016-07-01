package com.lanen.model.studyplan;

import java.io.Serializable;

public class TbLWeightData_json implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4367758347856440780L;
	private String id;                     //主键，自增长
	private String StudyNo;              //课题编号
	private int WeighSn;                //序号    1,2,3,4,5....
	private String AniCode;   //动物编号
	private String Weight;    //体重
	
	private String  unit;//体重单位
	private String showtime;//称重时间
	
	
	
	public String getShowtime() {
		return showtime;
	}
	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStudyNo() {
		return StudyNo;
	}
	public void setStudyNo(String studyNo) {
		StudyNo = studyNo;
	}
	public int getWeighSn() {
		return WeighSn;
	}
	public void setWeighSn(int weighSn) {
		WeighSn = weighSn;
	}
	public String getAniCode() {
		return AniCode;
	}
	public void setAniCode(String aniCode) {
		AniCode = aniCode;
	}
	public String getWeight() {
		return Weight;
	}
	public void setWeight(String weight) {
		Weight = weight;
	}
	
	

}
