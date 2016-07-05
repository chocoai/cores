package com.lanen.model;

import java.io.Serializable;
import java.util.Date;

public class Childbirth_Json implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8402182010336697673L;
	private Long id;              //主键（自增长）
	private String monkeyid;      //猴子编号(母)
	private String monkeyids;     //猴子编号(幼崽，不止一个用逗号隔开)
	private Date  labordate;      //分娩日期
	private Integer childercount;     //产仔数量
	private String laborcondition;//备注（分娩情况）
	private Long veterinarian;    //兽医（ID）
	private String veterinarianName; //兽医（名字）
	private Long keeper;          //饲养员（ID）
	private String keeperName; //饲养员（名字）
	private Long protector;       //保定人员（ID）
	private String protectorName; //保定人员（名字）
    private Long recorder;        //记录人员（ID）
    private String recorderName;  //记录人员（名字）
    private Long operater;        //档案录入（ID）
    private String operaterName;  //档案录入（名字）
    private int deleted;          //删除标记
    private Date createtime;     //录入时间
    private Long created_by;     //录入者
    private Long modified_by;    //修改者
    private Date lastmodifytime; //最后修改时间
    private String leavebreastdate;//离乳日期（错误字段，已弃用）
    private String leavebreastweight;//离乳体重（错误字段，已弃用）
    private String room;             //房舍（已弃用）
    private String createin;         //未知字段（已弃用）
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMonkeyid() {
		return monkeyid;
	}
	public void setMonkeyid(String monkeyid) {
		this.monkeyid = monkeyid;
	}
	public String getMonkeyids() {
		return monkeyids;
	}
	public void setMonkeyids(String monkeyids) {
		this.monkeyids = monkeyids;
	}
	public Date getLabordate() {
		return labordate;
	}
	public void setLabordate(Date labordate) {
		this.labordate = labordate;
	}
	public Integer getChildercount() {
		return childercount;
	}
	public void setChildercount(Integer childercount) {
		this.childercount = childercount;
	}
	public String getLaborcondition() {
		return laborcondition;
	}
	public void setLaborcondition(String laborcondition) {
		this.laborcondition = laborcondition;
	}
	public Long getVeterinarian() {
		return veterinarian;
	}
	public void setVeterinarian(Long veterinarian) {
		this.veterinarian = veterinarian;
	}
	public Long getKeeper() {
		return keeper;
	}
	public void setKeeper(Long keeper) {
		this.keeper = keeper;
	}
	public Long getProtector() {
		return protector;
	}
	public void setProtector(Long protector) {
		this.protector = protector;
	}
	public Long getRecorder() {
		return recorder;
	}
	public void setRecorder(Long recorder) {
		this.recorder = recorder;
	}
	public Long getOperater() {
		return operater;
	}
	public void setOperater(Long operater) {
		this.operater = operater;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Long getCreated_by() {
		return created_by;
	}
	public void setCreated_by(Long createdBy) {
		created_by = createdBy;
	}
	public Long getModified_by() {
		return modified_by;
	}
	public void setModified_by(Long modifiedBy) {
		modified_by = modifiedBy;
	}
	public Date getLastmodifytime() {
		return lastmodifytime;
	}
	public void setLastmodifytime(Date lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}
	public String getLeavebreastdate() {
		return leavebreastdate;
	}
	public void setLeavebreastdate(String leavebreastdate) {
		this.leavebreastdate = leavebreastdate;
	}
	public String getLeavebreastweight() {
		return leavebreastweight;
	}
	public void setLeavebreastweight(String leavebreastweight) {
		this.leavebreastweight = leavebreastweight;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getCreatein() {
		return createin;
	}
	public void setCreatein(String createin) {
		this.createin = createin;
	}
	public void setVeterinarianName(String veterinarianName) {
		this.veterinarianName = veterinarianName;
	}
	public String getVeterinarianName() {
		return veterinarianName;
	}
	public void setKeeperName(String keeperName) {
		this.keeperName = keeperName;
	}
	public String getKeeperName() {
		return keeperName;
	}
	public void setProtectorName(String protectorName) {
		this.protectorName = protectorName;
	}
	public String getProtectorName() {
		return protectorName;
	}
	public void setRecorderName(String recorderName) {
		this.recorderName = recorderName;
	}
	public String getRecorderName() {
		return recorderName;
	}
	public void setOperaterName(String operaterName) {
		this.operaterName = operaterName;
	}
	public String getOperaterName() {
		return operaterName;
	}
    
}
