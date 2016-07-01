package com.lanen.model.studyplan;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 字典-课题类别
 * @author Administrator
 *
 */
public class DictStudyType   implements Serializable   {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5153705128824610227L;
	private String studyTypeCode ;         //课题类别编码
	private String studyName;              //课题名称
	private int studyPeriod;               //课题周期
	private String studyPeriodUnit;        //课题周期单位
	private String tiCode;				   //供试品代码
	
	private Set<DictStudyTestIndex> dictStudyTestIndexs=new HashSet<DictStudyTestIndex>();//课题缺省检验指标 列表
	
	//TODO 专题代号
	private String studyCode;
	
	private int animalHave;//动物是否是必填项 0 不是 1 是
	
	
	
	
	
	public int getAnimalHave() {
		return animalHave;
	}
	public void setAnimalHave(int animalHave) {
		this.animalHave = animalHave;
	}
	public String getStudyCode() {
		return studyCode;
	}
	public void setStudyCode(String studyCode) {
		this.studyCode = studyCode;
	}
	public String getStudyTypeCode() {
		return studyTypeCode;
	}
	public void setStudyTypeCode(String studyTypeCode) {
		this.studyTypeCode = studyTypeCode;
	}
	public String getStudyName() {
		return studyName;
	}
	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}
	public int getStudyPeriod() {
		return studyPeriod;
	}
	public void setStudyPeriod(int studyPeriod) {
		this.studyPeriod = studyPeriod;
	}
	public String getStudyPeriodUnit() {
		return studyPeriodUnit;
	}
	public void setStudyPeriodUnit(String studyPeriodUnit) {
		this.studyPeriodUnit = studyPeriodUnit;
	}
	public Set<DictStudyTestIndex> getDictStudyTestIndexs() {
		return dictStudyTestIndexs;
	}
	public void setDictStudyTestIndexs(Set<DictStudyTestIndex> dictStudyTestIndexs) {
		this.dictStudyTestIndexs = dictStudyTestIndexs;
	}
	public String getTiCode() {
		return tiCode;
	}
	public void setTiCode(String tiCode) {
		this.tiCode = tiCode;
	}
}
