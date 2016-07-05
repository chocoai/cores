package com.lanen.service.arp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.Individual;
import com.lanen.model.Individual_Json;
import com.lanen.model.Normal;
import com.lanen.model.Publiccode;

public interface IndividualService extends BaseDao<Individual>{
	
	/**
	 * 根据动物编号获取实体
	 * @param monkeyid
	 * @return
	 */
	Individual getByMonkeyid(String monkeyid);
	/**根据动物编号获取实体并且删除标记不为1
	 * @param monkeyid
	 * @return
	 */
	Individual getByMonkeyidAndDeleted(String monkeyid);
	
	/**
	 * 获得所有猴子
	 * @return
	 */
	Map<String, Object> getAllIndividual(String page,String rows,String monkeyid,Integer animaltype,Integer sex,Integer keeper,
			String yjaddress,String sourceaddress,Double mincurrentweight,Double maxcurrentweight,
			Date birthdaymin,Date birthdaymax,Date yjdatemin,Date yjdatemax,String monkeyidMin,String monkeyidMax
			,Integer quyu);

	/**
	 * 获取所有的猴号
	 * @return
	 */
	List<Map<String,String>> getAllMonkeyidCombobox();
	
	/**
	 * 引进地Map
	 * @return
	 */
	List<Map<String, Object>> getAllYjaddressMap(String mark);
	
	/**
	 * 源地址
	 * @return
	 */
	List<Map<String, Object>> getAllSourceaddressMap(String mark);
	/**
	 * 动物种类
	 * @return
	 */
	List<Map<String, Object>> getAnimaltypeMap();
	/**
	 * 动物种类必须
	 * @return
	 */
	List<Map<String, String>> getAnimaltypeMapNo();
	/**
	 * 获取表头,存放到ArrayList filedName对象中(序号	动物编号 类别   出生日期  所属区域  所属房舍  饲养员  父亲编号  母亲编号  当前体重 检疫日期  离乳日期 离乳体重 体检 )
	 * @return
	 */
	ArrayList<Object> getExcelFiledNameList();
	/**
	 * 获取导出数据
	 * @param monkeyid
	 * @param animaltype
	 * @param sex
	 * @param keeper
	 * @param yjaddress
	 * @param sourceaddress
	 * @param mincurrentweight
	 * @param maxcurrentweight
	 * @param birthdaymin
	 * @param birthdaymax
	 * @param yjdatemin
	 * @param yjdatemax
	 * @param monkeyidMin
	 * @param monkeyidMax
	 * @param quyu
	 * @return
	 */
	ArrayList<Object> getExcelFiledDataList(String monkeyid,Integer animaltype,Integer sex,Integer keeper,
			String yjaddress,String sourceaddress,Double mincurrentweight,Double maxcurrentweight,
			Date birthdaymin,Date birthdaymax,Date yjdatemin,Date yjdatemax,String monkeyidMin,String monkeyidMax
			,Integer quyu);
	
	/**检查动物编号的唯一性
	 * @param monkeyid
	 * @return
	 */
	boolean isExistMonkeyid(String monkeyid);
	
	/**检查动物编号的唯一性(母)
	 * @param monkeyid
	 * @return
	 */
	boolean isExistMonkeyidM(String monkeyid);
	
	/**检查动物编号的唯一性(公)
	 * @param monkeyid
	 * @return
	 */
	boolean isExistMonkeyidF(String monkeyid);
	/**根据条件查询繁殖区的猴子
	 * @param page
	 * @param rows
	 * @param monkeyid
	 * @param animaltype
	 * @param sex
	 * @param keeper
	 * @param birthdaymin
	 * @param birthdaymax
	 * @param quyu
	 * @param room
	 * @return
	 */
	Map<String, Object> getBreedingIndividual(String page, String rows,
			String monkeyid, Integer animaltype, Integer sex, Integer keeper,
			Date birthdaymin, Date birthdaymax, Integer quyu, Integer room);
	
	//根据区域，房间查询猴子
	List<Map<String, Object>> getRoomIndividual(String blong,String room);
	//获取种类
	List<?> getMonkeyAgeType();
	List<?> getMonkeySex();
	BigInteger getMonkeyCount(Integer room,Integer agetype,Byte sex);
	BigInteger getMonkeyCount(Integer room,Integer agetype);
	//上月猴子数根据年龄阶段.
	List<?> getMonkeyCount();
	//断奶猴子数
	List<?> getLeavebreastCount(Date nows);
	//调入
	//调出
	List<?> getSaleCountByType(Date nows,int agetype,int sex);
	//死亡
	List<?> getDeathCount(Date nows);
	Map<String, Object> getAllIndividualByMores(String page, String rows,
			String monkeyid, Integer animaltype, Integer sex, Integer keeper,
			String yjaddress, String sourceaddress, Double mincurrentweight,
			Double maxcurrentweight, Date birthdaymin, Date birthdaymax,
			Date yjdatemin, Date yjdatemax, String monkeyidMin,
			String monkeyidMax, Integer quyu,String bv,String stlv,String srv,String siv,String measles,String hepatitisa,
			String amb,String gxc,String lyc,String bmc,String twjsc,
			String shig,String salm,String yers,String mazhen,String jiag,String yig);
	/**
	 * 获取该房舍下的猴子
	 * @return
	 */
	List<Individual> getindividuals(String room);
	/**
	 * 导出Word动物数据.
	 */
	List<Individual_Json> getIndividualJsonById(Integer id);
	/**
	 * 引进地信息
	 */
	List<Publiccode> getYJAddressInfo(String mark); 
	
	Individual_Json getIndividualJsonById(String monkeyid);
	/**
	 * 根据动物编号获取所有检疫编号
	 */
	List<Normal> getCheckInfoById(String monkeyid);
	/**
	 * 根据chipid查monkeyid
	 */
	String getMonkeyIdByChipId(String chipid);
}
