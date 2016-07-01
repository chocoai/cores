package com.lanen.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.lanen.util.DateUtil;

/**
 * TblFileRecord entity. @author MyEclipse Persistence Tools
 */

public class TblFileRecord implements java.io.Serializable {

	// Fields

	private String fileRecordId;
	//private String archiveCode;
	private TblFileIndex tblFileIndex;
	private Integer fileRecordSn;
	private String archiveMaker;
	private String fileOperator;
	private Date fileDate;
	private Date keepDate;
	private Date destoryDate;
	private String destoryRegSign;
	private Integer archiveMediaFlag;
	private String archiveMedia;
	private String archiveMediaEleCode;
	private Date operateTime;
	private String operator;
	private String remark;
	private String keyWord;
	private Integer delFlag;
	private Date delTime;
	
	private Integer fileFlag;//0归档，-1没归档
	private Integer fileDateType;//归档日期标识，1年，2年月，3年月日
	

	// Constructors

	/** default constructor */
	public TblFileRecord() {
	}

	/** minimal constructor */
	public TblFileRecord(String fileRecordId) {
		this.fileRecordId = fileRecordId;
	}

	/** full constructor */
	public TblFileRecord(String fileRecordId, TblFileIndex tblFileIndex,
			Integer fileRecordSn, String archiveMaker, String fileOperator,
			Date fileDate, Date keepDate, Date destoryDate,
			String destoryRegSign, Integer archiveMediaFlag,
			String archiveMedia, Date operateTime, String operator,
			String remark, String keyWord, Integer delFlag, Date delTime
			) {
		this.fileRecordId = fileRecordId;
		this.tblFileIndex = tblFileIndex;
		this.fileRecordSn = fileRecordSn;
		this.archiveMaker = archiveMaker;
		this.fileOperator = fileOperator;
		this.fileDate = fileDate;
		this.keepDate = keepDate;
		this.destoryDate = destoryDate;
		this.destoryRegSign = destoryRegSign;
		this.archiveMediaFlag = archiveMediaFlag;
		this.archiveMedia = archiveMedia;
		this.operateTime = operateTime;
		this.operator = operator;
		this.remark = remark;
		this.keyWord = keyWord;
		this.delFlag = delFlag;
		this.delTime = delTime;
		
	}

	// Property accessors

	public String getFileRecordId() {
		return this.fileRecordId;
	}

	public void setFileRecordId(String fileRecordId) {
		this.fileRecordId = fileRecordId;
	}

	public Integer getFileRecordSn() {
		return this.fileRecordSn;
	}

	public void setFileRecordSn(Integer fileRecordSn) {
		this.fileRecordSn = fileRecordSn;
	}

	public String getArchiveMaker() {
		return this.archiveMaker;
	}

	public void setArchiveMaker(String archiveMaker) {
		this.archiveMaker = archiveMaker;
	}

	public String getFileOperator() {
		return this.fileOperator;
	}

	public void setFileOperator(String fileOperator) {
		this.fileOperator = fileOperator;
	}

	public Date getFileDate() {
		return this.fileDate;
	}

	public void setFileDate(Date fileDate) {
		this.fileDate = fileDate;
	}

	public Date getKeepDate() {
		return this.keepDate;
	}

	public void setKeepDate(Date keepDate) {
		this.keepDate = keepDate;
	}

	public Date getDestoryDate() {
		if(this.destoryDate!=null&&this.destoryDate.compareTo(DateUtil.stringToDate("1900-01-01","yyyy-MM-dd"))!=0)
			return this.destoryDate;
		else 
			return null;
	}

	public void setDestoryDate(Date destoryDate) {
		if(destoryDate==null||"".equals(destoryDate))
			this.destoryDate = null;
		else {
			this.destoryDate = destoryDate;
		}
	}

	public String getDestoryRegSign() {
		return this.destoryRegSign;
	}

	public void setDestoryRegSign(String destoryRegSign) {
		this.destoryRegSign = destoryRegSign;
	}

	public Integer getArchiveMediaFlag() {
		return this.archiveMediaFlag;
	}

	public void setArchiveMediaFlag(Integer archiveMediaFlag) {
		this.archiveMediaFlag = archiveMediaFlag;
	}

	public String getArchiveMedia() {
		return this.archiveMedia;
	}

	public void setArchiveMedia(String archiveMedia) {
		this.archiveMedia = archiveMedia;
	}

	public Date getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Integer getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Date getDelTime() {
		return this.delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}

	

	public TblFileIndex getTblFileIndex() {
		return tblFileIndex;
	}

	public void setTblFileIndex(TblFileIndex tblFileIndex) {
		this.tblFileIndex = tblFileIndex;
	}

	public String getArchiveMediaEleCode() {
		return archiveMediaEleCode;
	}

	public void setArchiveMediaEleCode(String archiveMediaEleCode) {
		this.archiveMediaEleCode = archiveMediaEleCode;
	}

	public Integer getFileFlag() {
		return fileFlag;
	}

	public void setFileFlag(Integer fileFlag) {
		this.fileFlag = fileFlag;
	}

	public Integer getFileDateType() {
		return fileDateType;
	}

	public void setFileDateType(Integer fileDateType) {
		this.fileDateType = fileDateType;
	}

}