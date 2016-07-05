package com.lanen.service.arp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.Animaltype;
import com.lanen.model.Area;
import com.lanen.model.Employee;
import com.lanen.model.Individual;
import com.lanen.model.Individual_Json;
import com.lanen.model.Normal;
import com.lanen.model.Publiccode;
import com.lanen.model.Quarantine;
import com.lanen.util.DateUtil;

@Service
public class IndividualServiceImpl extends BaseDaoImpl<Individual> implements
		IndividualService {
	@Resource
	private EmployeeService employeeService;
	@Resource
	private AreaService areaService;
	@Resource
	private AnimaltypeService animaltypeService;
	//表名小写.
	public Map<String, Object> getAllIndividual(String page, String rows,
			String monkeyid, Integer animaltype, Integer sex, Integer keeper,
			String yjaddress, String sourceaddress, Double mincurrentweight,
			Double maxcurrentweight, Date birthdaymin, Date birthdaymax,
			Date yjdatemin, Date yjdatemax, String monkeyidMin,
			String monkeyidMax, Integer quyu) {
		String sql = "SELECT id,monkeyid,animaltype,birthday,frid,agetype,iszhongqun,sex,source"
				+ ",generation,leavebreastdate,currentweight,birthdayweight,leavebreastweight "
				+ " ,fatherid,motherid"
				+ ",(SELECT areaname FROM area WHERE id=i.blongarea) AS blongarea"
				+ ",dong"
				+ ",(SELECT areaname FROM area WHERE id=i.room) AS room"
				+ ",(SELECT e.name FROM  employee AS e WHERE e.id=i.keeper) as keeper"
				+ ",(SELECT e.name FROM  employee AS e WHERE e.id=i.veterinarian) as veterinarian"
				+ ",(SELECT e.name FROM  employee AS e WHERE e.id=i.operater) as operater"
				+ ",deleted"
				+ " ,modified_by,createtime,created_by,lastmodifytime,tnid,ysz,yjdate,sourceaddress"
				+ " ,yjaddress,remark,status,lhao,currentdate,image,weighingDate" +
						",(select name from animaltype a where a.id=i.animaltype) as animaltypeName," +
						"(select name from publiccode p where p.id=i.yjaddress) as yjaddressName,chipid "
				+ " FROM individual as i WHERE deleted != 1 ";
		if (null != monkeyid && !monkeyid.equals("")) {
			sql = sql + " and monkeyid = :monkeyid ";
		}
		if (null != animaltype && !animaltype.equals("") && animaltype != 0) {
			sql = sql + " and animaltype = :animaltype ";
		}
		if (null != sex && !sex.equals("-1") && sex != -1) {
			sql = sql + " and sex = :sex ";
		}
		if (null != keeper && !keeper.equals("-1") && keeper != -1) {
			sql = sql + " and keeper = :keeper ";
		}
		if (null != yjaddress && !yjaddress.equals("")) {
			sql = sql + " and yjaddress = :yjaddress ";
		}
		if (null != sourceaddress && !sourceaddress.equals("")) {
			sql = sql + " and source = :source ";
		}
		if (null != maxcurrentweight && null != mincurrentweight
				&& !maxcurrentweight.equals("") && !mincurrentweight.equals("")) {
			sql = sql
					+ " and currentweight between  :mincurrentweight and :maxcurrentweight ";
		}
		if (null != birthdaymin && null != birthdaymax) {
			sql = sql + " and birthday between  :birthdaymin and :birthdaymax ";
		}
		if (null != yjdatemin && null != yjdatemax) {
			sql = sql + " and yjdate between  :yjdatemin and :yjdatemax ";
		}
		if (quyu != -1 && quyu != 0 && quyu != null) {
			sql = sql + " and ( blongarea  =  :quyu  or room = :quyu )";

		}
		List<String> monkeyidList = new ArrayList<String>();
		if (null != monkeyidMin && null != monkeyidMax
				&& !monkeyidMin.equals("") && !monkeyidMax.equals("")) {
			int min = Integer.valueOf("10" + monkeyidMin).intValue();
			int max = Integer.valueOf("10" + monkeyidMax).intValue();
			for (; min <= max; min++) {
				monkeyidList.add(String.valueOf(min).substring(2, 9).trim());
			}
		}
		if (null != monkeyidMin && null != monkeyidMax
				&& !monkeyidMin.equals("") && !monkeyidMax.equals("")) {
			sql = sql + " and monkeyid in  (:monkeyidList) ";
		}
		sql=sql+" order by createtime desc";
		Query query = getSession().createSQLQuery(sql);
		if (null != monkeyid && !monkeyid.equals("")) {
			query.setParameter("monkeyid", monkeyid);
		}
		if (null != animaltype && !animaltype.equals("-1") && animaltype != -1) {
			query.setParameter("animaltype", animaltype);
		}
		if (null != sex && !sex.equals("-1") && sex != -1) {
			query.setParameter("sex", sex);
		}
		if (null != keeper && !keeper.equals("-1") && keeper != -1) {
			query.setParameter("keeper", keeper);
		}
		if (null != yjaddress && !yjaddress.equals("")) {
			query.setParameter("yjaddress", yjaddress);
		}
		if (null != sourceaddress && !sourceaddress.equals("")) {
			query.setParameter("source", sourceaddress);
		}
		if (null != maxcurrentweight && null != mincurrentweight
				&& !maxcurrentweight.equals("") && !mincurrentweight.equals("")) {
			query.setParameter("maxcurrentweight", maxcurrentweight);
			query.setParameter("mincurrentweight", mincurrentweight);
		}
		if (null != birthdaymin && null != birthdaymax) {
			query.setParameter("birthdaymin", birthdaymin);
			query.setParameter("birthdaymax", birthdaymax);
		}
		if (null != yjdatemin && null != yjdatemax) {
			query.setParameter("yjdatemin", yjdatemin);
			query.setParameter("yjdatemax", yjdatemax);
		}
		if (null != monkeyidMin && null != monkeyidMax
				&& !monkeyidMin.equals("") && !monkeyidMax.equals("")) {
			query.setParameterList("monkeyidList", monkeyidList);
		}
		if (quyu != -1 && quyu != 0 && quyu != null) {
			query.setParameter("quyu", quyu);

		}
		List<?> listtotal = query.list();
		// 当为缺省值的时候进行赋值
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);// 第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);// 每页多少行
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize)
				.setMaxResults(pagesize).list();

		List<Individual_Json> list1 = new ArrayList<Individual_Json>();
		if (null != list) {
			for (Object obj : list) {
				Object[] objs = (Object[]) obj;

				Individual_Json individual = new Individual_Json();
				individual.setId((Integer) objs[0]);
				individual.setMonkeyid((String) objs[1]);
				individual.setAnimaltype((Integer) objs[2]);
				individual.setBirthday((String) objs[3]);
				individual.setFrid((String) objs[4]);
				if (null != objs[5]) {
					individual.setAgetype((Integer) objs[5]);
				}
				// 种群
				if (null != objs[6]) {
					individual.setIszhongqun((Byte) objs[6]);
				}
				if (null != objs[7]) {
					individual.setSex((Byte) objs[7]);
				}
				// 来源
				if (null != objs[8]) {
					Publiccode p=getPubliccodeById((Integer)objs[8]);
					individual.setSourceaddress(p.getName());
				}
				// 代数 12345 generation
				if (null != objs[9]) {
					individual.setGeneration((Byte) objs[9]);
				}
				// leavebreastdate
				if (null != objs[10]) {
					individual.setLeavebreastdate((String) objs[10]);
				}
				// currentweight
				if (null != objs[11]) {
					individual.setCurrentweight((Float) objs[11]);
				}
				// birthdayweight
				if (null != objs[12]) {
					individual.setBirthdayweight((Float) objs[12]);
				}
				// leavebreastweight
				if (null != objs[13]) {
					individual.setLeavebreastweight((Float) objs[13]);
				}
				// fatherid
				if (null != objs[14]) {
					individual.setFatherid((String) objs[14]);
				}
				// motherid
				if (null != objs[15]) {
					individual.setMotherid((String) objs[15]);
				}
				// blongarea
				if (null != objs[16]) {
					// int areaid = (Integer)objs[16];
					// Area area = areaService.getById(Long.valueOf(areaid));
					// individual.setBlongarea((Integer)objs[16]);
					//
					// //room
					// Area area2 = new Area();
					// if(null != objs[18] ){
					// int areaid2 = (Integer)objs[18];
					// area2 = areaService.getById(Long.valueOf(areaid2));
					// individual.setBlongarea((Integer)objs[18]);
					// //individual.setQuyu(area2.getAreaname());
					// }
					// if(null != objs[18]){
					// individual.setQuyu(area.getAreaname()+"    "+area2.getAreaname());
					// }else{
					// individual.setQuyu(area.getAreaname());
					// }
					//individual.setQuyu((String) objs[16] + " "+ (String) objs[18]);
					individual.setQuyu((String )objs[16]);
				}
				// dong
				if (null != objs[17]) {
					individual.setDong((Integer) objs[17]);
				}
				if(objs[18]!=null){
					individual.setRoomName((String)objs[18]);
				}
				// keeper饲养员
				if (null != objs[19]) {
					// int keepid = (Integer)objs[19];
					// Employee employee =
					// employeeService.getById(Long.valueOf(keepid));
					individual.setKeeperp((String) objs[19]);
					// individual.setKeeper((Integer)objs[19]);

				}
				// veterinarian 兽医
				if (null != objs[20]) {
					// individual.setKeeper((Integer)objs[20]);
				}
				// operater 操作员（null）
				if (null != objs[21]) {
					// individual.setOperater((Integer)objs[21]);
				}
				// deleted
				if (null != objs[22]) {
					individual.setDeleted((Byte) objs[22]);
				}
				// modified_by 修改者
				if (null != objs[23]) {
					individual.setModified_by((Integer) objs[23]);
				}
				// createtime 创建时间
				if (null != objs[24]) {
					individual.setCreatetime((Date) objs[24]);
				}
				// created_by 创建人
				if (null != objs[25]) {
					individual.setCreated_by((Integer) objs[25]);
				}
				// lastmodifytime最后修改时间
				if (null != objs[26]) {
					individual.setLastmodifytime((Date) objs[26]);
				}
				// tnid 体内芯片
				if (null != objs[27]) {
					individual.setTnid((String) objs[27]);
				}
				// ysz
				if (null != objs[28]) {
					individual.setYsz((String) objs[28]);
				}
				// yjdate
				if (null != objs[29]) {
					individual.setYjdate((String) objs[29]);
				}
				// sourceaddress
				//if (null != objs[30]) {
				//	individual.setSourceaddress((String)objs[30]);
				//}
				// yjaddress
				if (null != objs[31]) {
					Publiccode p=getPubliccodeById(Integer.valueOf(objs[31]+""));
					individual.setYjaddress(p.getName());
				}
				// remark
				if (null != objs[32]) {
					individual.setRemark((String) objs[32]);
				}
				// status
				if (null != objs[33]) {
					individual.setStatus((Integer) objs[33]);
				}
				// lhao
				if (null != objs[34]) {
					individual.setLhao((String) objs[34]);
				}
				// currentdate
				if (null != objs[35]) {
					individual.setCurrentdate((String) objs[35]);
				}
				// imag
				if (null != objs[36]) {
					individual.setImage((String) objs[36]);
				}
				// weighingDate称重日期
				if (null != objs[37]) {
					individual.setWeighingDate((Date) objs[37]);
				}
				individual.setAnimaltypeName((String) objs[38]);
				individual.setYjaddress((String) objs[39]);
				individual.setChipid((String) objs[40]);
				list1.add(individual);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list1);
		map.put("total", listtotal.size());
		return map;
	}
	//小写.
	public List<Map<String, Object>> getAllSourceaddressMap(String mark) {
		//String sql = " SELECT DISTINCT sourceaddress FROM individual WHERE deleted != 1 AND sourceaddress IS NOT NULL ";
		String sql = " SELECT  id,name from publiccode where mark=:mark ";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("mark", mark);
		List<?> list = query.list();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		map = new HashMap<String, Object>();
		map.put("id", "-1");
		map.put("text", "&nbsp;");
		mapList.add(map);
		if (list != null) {
			for (Object obj : list) {
				Object[]objs=(Object[])obj;
				map = new HashMap<String, Object>();
				map.put("id", (Integer) objs[0]);
				map.put("text", (String) objs[1]);
				mapList.add(map);
			}
		}
		return mapList;
	}
	//小写.
	public List<Map<String, Object>> getAllYjaddressMap(String mark) {
		//String sql = " SELECT DISTINCT yjaddress FROM individual WHERE deleted != 1 AND yjaddress IS NOT NULL  AND yjaddress != '' ";
		String sql = " SELECT  id,name from publiccode where mark=:mark ";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("mark", mark);
		List<?> list = query.list();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		map = new HashMap<String, Object>();
		map.put("id", -1);
		map.put("text", "&nbsp;");
		mapList.add(map);
		if (list != null) {
			for (Object obj : list) {
				Object[]objs=(Object[])obj;
				map = new HashMap<String, Object>();
				map.put("id", (Integer) objs[0]);
				map.put("text", (String) objs[1]);
				mapList.add(map);
			}
		}
		return mapList;
	}

	public List<Map<String, Object>> getAnimaltypeMap() {
		String sql = " select id,name from animaltype where del != 1  ";
		Query query = getSession().createSQLQuery(sql);
		List<?> list = query.list();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		map = new HashMap<String, Object>();
		map.put("id", -1);
		map.put("text", "&nbsp;");
		mapList.add(map);
		if (list != null) {
			for (Object obj : list) {
				Object[] objs = (Object[]) obj;
				map = new HashMap<String, Object>();
				map.put("id",  objs[0]);
				map.put("text", objs[1]);
				mapList.add(map);
			}
		}
		return mapList;
	}

	public ArrayList<Object> getExcelFiledNameList() {
		// 获取表头,存放到ArrayList filedName对象中(序号 动物编号 类别 出生日期 所属区域 所属房舍 饲养员 父亲编号
		// 母亲编号 当前体重 检疫日期 离乳日期 离乳体重 体检 )
		String[] titles = { "序号", "动物编号", "类别", "出生日期", "所属区域", "所属房舍", "饲养员",
				"父亲编号", "母亲编号", "当前体重", "检疫日期", "离乳日期", "离乳体重", "体检" };
		ArrayList<Object> filedName = new ArrayList<Object>();
		for (int i = 0; i < titles.length; i++) {
			String title = titles[i];
			filedName.add(title);
		}
		return filedName;
	}

	public ArrayList<Object> getExcelFiledDataList(String monkeyid,
			Integer animaltype, Integer sex, Integer keeper, String yjaddress,
			String sourceaddress, Double mincurrentweight,
			Double maxcurrentweight, Date birthdaymin, Date birthdaymax,
			Date yjdatemin, Date yjdatemax, String monkeyidMin,
			String monkeyidMax, Integer quyu) {
		// 组织查询条件.表名小写.
		String sql = "SELECT id,monkeyid,animaltype,birthday,frid,agetype,iszhongqun,sex,source,generation,leavebreastdate,currentweight "
				+ " ,birthdayweight,leavebreastweight,fatherid,motherid,blongarea,dong,room,keeper,veterinarian,"
				+ " operater,deleted,modified_by,createtime,created_by,lastmodifytime,tnid,ysz,yjdate,sourceaddress,yjaddress,"
				+ " remark,status,lhao,currentdate,image,weighingDate FROM individual WHERE deleted != 1 ";
		if (null != monkeyid && !monkeyid.equals("")) {
			sql = sql + " and monkeyid = :monkeyid ";
		}
		if (null != animaltype && !animaltype.equals("") && animaltype != 0) {
			sql = sql + " and animaltype = :animaltype ";
		}
		if (null != sex && !sex.equals("-1") && sex != -1) {
			sql = sql + " and sex = :sex ";
		}
		if (null != keeper && !keeper.equals("-1") && keeper != -1) {
			sql = sql + " and keeper = :keeper ";
		}
		if (null != yjaddress && !yjaddress.equals("")) {
			sql = sql + " and yjaddress = :yjaddress ";
		}
		if (null != sourceaddress && !sourceaddress.equals("")) {
			sql = sql + " and sourceaddress = :sourceaddress ";
		}
		if (null != maxcurrentweight && null != mincurrentweight
				&& !maxcurrentweight.equals("") && !mincurrentweight.equals("")) {
			sql = sql
					+ " and currentweight between  :mincurrentweight and :maxcurrentweight ";
		}
		if (null != birthdaymin && null != birthdaymax) {
			sql = sql + " and birthday between  :birthdaymin and :birthdaymax ";
		}
		if (null != yjdatemin && null != yjdatemax) {
			sql = sql + " and yjdate between  :yjdatemin and :yjdatemax ";
		}
		if (quyu != -1 && quyu != 0 && quyu != null) {
			sql = sql + " and ( blongarea  =  :quyu  or room = :quyu )";

		}
		List<String> monkeyidList = new ArrayList<String>();
		if (null != monkeyidMin && null != monkeyidMax
				&& !monkeyidMin.equals("") && !monkeyidMax.equals("")) {
			int min = Integer.valueOf("10" + monkeyidMin).intValue();
			int max = Integer.valueOf("10" + monkeyidMax).intValue();
			for (; min <= max; min++) {
				monkeyidList.add(String.valueOf(min).substring(2, 9).trim());
			}
		}
		if (null != monkeyidMin && null != monkeyidMax
				&& !monkeyidMin.equals("") && !monkeyidMax.equals("")) {
			sql = sql + " and monkeyid in  (:monkeyidList) ";
		}
		Query query = getSession().createSQLQuery(sql);
		if (null != monkeyid && !monkeyid.equals("")) {
			query.setParameter("monkeyid", monkeyid);
		}
		if (null != animaltype && !animaltype.equals("-1") && animaltype != -1) {
			query.setParameter("animaltype", animaltype);
		}
		if (null != sex && !sex.equals("-1") && sex != -1) {
			query.setParameter("sex", sex);
		}
		if (null != keeper && !keeper.equals("-1") && keeper != -1) {
			query.setParameter("keeper", keeper);
		}
		if (null != yjaddress && !yjaddress.equals("")) {
			query.setParameter("yjaddress", yjaddress);
		}
		if (null != sourceaddress && !sourceaddress.equals("")) {
			query.setParameter("sourceaddress", sourceaddress);
		}
		if (null != maxcurrentweight && null != mincurrentweight
				&& !maxcurrentweight.equals("") && !mincurrentweight.equals("")) {
			query.setParameter("maxcurrentweight", maxcurrentweight);
			query.setParameter("mincurrentweight", mincurrentweight);
		}
		if (null != birthdaymin && null != birthdaymax) {
			query.setParameter("birthdaymin", birthdaymin);
			query.setParameter("birthdaymax", birthdaymax);
		}
		if (null != yjdatemin && null != yjdatemax) {
			query.setParameter("yjdatemin", yjdatemin);
			query.setParameter("yjdatemax", yjdatemax);
		}
		if (null != monkeyidMin && null != monkeyidMax
				&& !monkeyidMin.equals("") && !monkeyidMax.equals("")) {
			query.setParameterList("monkeyidList", monkeyidList);
		}
		if (quyu != -1 && quyu != 0 && quyu != null) {
			query.setParameter("quyu", quyu);

		}
		List<?> list = query.list();

		// 构造报表导出数据
		ArrayList<Object> filedData = new ArrayList<Object>();
		if (null != list) {
			int i = 1;
			for (Object obj : list) {
				Object[] objs = (Object[]) obj;
				ArrayList<Object> dataList = new ArrayList<Object>();
				// 获取表头,存放到ArrayList filedName对象中
				// (序号 动物编号 类别 出生日期 所属区域 所属房舍 饲养员 父亲编号 母亲编号 当前体重 检疫日期 离乳日期 离乳体重
				// 体检 )
				// 序号
				dataList.add(i);
				i++;
				// id (Integer)objs[0]
				// (String)objs[1] 猴子编号
				dataList.add((String) objs[1]);
				// Animaltype (Integer)objs[2] 类别
				if (null != objs[2]) {
					int animaltypeid = (Integer) objs[2];
					Animaltype type = animaltypeService.getById(Long
							.valueOf(animaltypeid));
					dataList.add(type.getName());
				} else {
					dataList.add("");
				}

				// 出生日期(String)objs[3]
				dataList.add((String) objs[3]);
				// blongarea 区域
				if (null != objs[16]) {
					int areaid = (Integer) objs[16];
					Area area = areaService.getById(Long.valueOf(areaid));
					dataList.add(area.getAreaname());
				} else {
					dataList.add("");
				}
				// room 房舍
				if (null != objs[18]) {
					int areaid = (Integer) objs[18];
					Area area = areaService.getById(Long.valueOf(areaid));
					dataList.add(area.getAreaname());
				} else {
					dataList.add("");
				}
				// keeper饲养员
				if (null != objs[19]) {
					int keepid = (Integer) objs[19];
					Employee employee = employeeService.getById(Long.valueOf(keepid));
					dataList.add(employee.getName());
				} else {
					dataList.add("");
				}
				// fatherid
				if (null != objs[14]) {
					dataList.add((String) objs[14]);
				} else {
					dataList.add("");
				}
				// motherid
				if (null != objs[15]) {
					dataList.add((String) objs[15]);
				} else {
					dataList.add("");
				}
				// 当前体重
				// currentweight
				if (null != objs[11]) {
					dataList.add((Float) objs[11]);
				} else {
					dataList.add("");
				}

				// 检疫日期
				// currentdate
				if (null != objs[35]) {
					dataList.add((String) objs[35]);
				}
				// 离乳日期
				// leavebreastdate
				if (null != objs[10]) {
					dataList.add((String) objs[10]);
				} else {
					dataList.add("");
				}
				// 离乳体重
				// birthdayweight
				if (null != objs[12]) {
					dataList.add((Float) objs[12]);
				} else {
					dataList.add("");
				}
				// frid (String)objs[4]
				// 体检
				// DOTO
				dataList.add("");
				// agetype年龄段
				if (null != objs[5]) {
				}
				// 种群
				if (null != objs[6]) {
				}
				if (null != objs[7]) {
				}
				// 录入人
				if (null != objs[8]) {
				}
				// 代数 12345 generation
				if (null != objs[9]) {
				}
				// leavebreastweight
				if (null != objs[13]) {
				}
				// dong
				if (null != objs[17]) {
				}
				// veterinarian 兽医
				if (null != objs[20]) {
				}
				// operater 操作员（null）
				if (null != objs[21]) {
				}
				// deleted
				if (null != objs[22]) {
				}
				// modified_by 修改者
				if (null != objs[23]) {
				}
				// createtime 创建时间
				if (null != objs[24]) {
				}
				// created_by 创建人
				if (null != objs[25]) {
				}
				// lastmodifytime最后修改时间
				if (null != objs[26]) {
				}
				// tnid 体内芯片
				if (null != objs[27]) {
				}
				// ysz
				if (null != objs[28]) {
				}
				// yjdate
				if (null != objs[29]) {
				}
				// sourceaddress
				if (null != objs[30]) {
				}
				// yjaddress
				if (null != objs[31]) {
				}
				// remark
				if (null != objs[32]) {
				}
				// status
				if (null != objs[33]) {
				}
				// lhao
				if (null != objs[34]) {
				}
				// currentdate
				if (null != objs[35]) {
				}
				// imag
				if (null != objs[36]) {
				}
				// weighingDate称重日期
				if (null != objs[37]) {
				}
				filedData.add(dataList);
			}
		}
		return filedData;
	}

	@SuppressWarnings("unchecked")
	public Individual getByMonkeyid(String monkeyid) {
		List<Individual> list = getSession()
				.createQuery("FROM Individual o WHERE o.monkeyid = ?   ")//
				.setParameter(0, monkeyid).list();
		if (list.size() > 0 && null != list) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Individual getByMonkeyidAndDeleted(String monkeyid) {
		List<Individual> list = getSession()
				.createQuery(
						"FROM Individual o WHERE o.deleted!=1 and o.monkeyid = ?   ")//
				.setParameter(0, monkeyid).list();
		if (list.size() > 0 && null != list) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public List<Map<String, String>> getAnimaltypeMapNo() {
		String sql = " select id,name from animaltype where del != 1  ";
		Query query = getSession().createSQLQuery(sql);
		List<?> list = query.list();
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		if (list != null) {
			for (Object obj : list) {
				Object[] objs = (Object[]) obj;
				map = new HashMap<String, String>();
				map.put("id", (Integer) objs[0] + "");
				map.put("text", (String) objs[1]);
				mapList.add(map);
			}
		}
		return mapList;
	}

	public boolean isExistMonkeyid(String monkeyid) {
		if (null != monkeyid) {
			List<?> list = getSession()
					.createQuery(
							//" From Individual where deleted != 1 and monkeyid = ? ")
					" From Individual where  monkeyid = ? ")
					.setParameter(0, monkeyid).list();
			if (null != list && list.size() > 0) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	public boolean isExistMonkeyidM(String monkeyid) {
		if (null != monkeyid) {
			List<?> list = getSession()
					.createQuery(
							" From Individual where monkeyid = ? and sex = 1 ")
					.setParameter(0, monkeyid).list();
			if (null != list && list.size() > 0) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	public boolean isExistMonkeyidF(String monkeyid) {
		if (null != monkeyid) {
			List<?> list = getSession()
					.createQuery(
							" From Individual where monkeyid = ? and sex = 0 ")
					.setParameter(0, monkeyid).list();
			if (null != list && list.size() > 0) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	public List<Map<String, String>> getAllMonkeyidCombobox() {
		String sql = " SELECT DISTINCT monkeyid FROM Individual WHERE deleted != 1 AND monkeyid IS NOT NULL  AND monkeyid != '' ";
		Query query = getSession().createSQLQuery(sql);
		List<?> list = query.list();
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		// map = new HashMap<String, String>();
		// map.put("id", "-1");
		// map.put("text", "&nbsp;");
		// mapList.add(map);
		if (list != null) {
			for (Object obj : list) {
				map = new HashMap<String, String>();
				map.put("id", (String) obj);
				map.put("text", (String) obj);
				mapList.add(map);
			}
		}
		return mapList;
	}
	//小写.
	public Map<String, Object> getBreedingIndividual(String page, String rows,
			String monkeyid, Integer animaltype, Integer sex, Integer keeper,
			Date birthdaymin, Date birthdaymax, Integer quyu, Integer room) {
		String sql = "SELECT id,monkeyid,(select name from animaltype where id=animaltype)as animaltype,birthday,frid,agetype,iszhongqun,sex,source"
				+ ",generation,leavebreastdate,currentweight,birthdayweight,leavebreastweight "
				+ " ,fatherid,motherid"
				+ ",(SELECT areaname FROM area WHERE id=i.blongarea) AS blongarea"
				+ ",dong"
				+ ",(SELECT areaname FROM area WHERE id=i.room) AS room"
				+ ",(SELECT e.name FROM  employee AS e WHERE e.id=i.keeper) as keeper"
				+ ",(SELECT e.name FROM  employee AS e WHERE e.id=i.veterinarian) as veterinarian"
				+ ",(SELECT e.name FROM  employee AS e WHERE e.id=i.operater) as operater"
				+ ",deleted"
				+ " ,modified_by,createtime,created_by,lastmodifytime,tnid,ysz,yjdate,sourceaddress"
				+ " ,yjaddress,remark,status,lhao,currentdate,image,weighingDate "
				+ " FROM individual as i WHERE deleted != 1 ";
		if (null != monkeyid && !monkeyid.equals("")) {
			sql = sql + " and monkeyid = :monkeyid ";
		}
		if (null != animaltype && !animaltype.equals("") && animaltype != 0) {
			sql = sql + " and animaltype = :animaltype ";
		}
		if (null != sex && !sex.equals("-1") && sex != -1) {
			sql = sql + " and sex = :sex ";
		}
		if (null != keeper && !keeper.equals("-1") && keeper != -1) {
			sql = sql + " and keeper = :keeper ";
		}
		if (null != birthdaymin && null != birthdaymax) {
			sql = sql + " and birthday between  :birthdaymin and :birthdaymax ";
		}
		if (null != quyu && quyu != -1 && quyu != 0) {
			sql = sql + " and  blongarea  =  :quyu  ";
		}
		if (null != room && room != -1 && room != 0) {
			sql = sql + " and  room  =  :room  ";
		}
		List<String> monkeyidList = new ArrayList<String>();
		Query query = getSession().createSQLQuery(sql);
		if (null != monkeyid && !monkeyid.equals("")) {
			query.setParameter("monkeyid", monkeyid);
		}
		if (null != animaltype && !animaltype.equals("-1") && animaltype != -1) {
			query.setParameter("animaltype", animaltype);
		}
		if (null != sex && !sex.equals("-1") && sex != -1) {
			query.setParameter("sex", sex);
		}
		if (null != keeper && !keeper.equals("-1") && keeper != -1) {
			query.setParameter("keeper", keeper);
		}
		if (null != birthdaymin && null != birthdaymax) {
			query.setParameter("birthdaymin", birthdaymin);
			query.setParameter("birthdaymax", birthdaymax);
		}
		if (null != quyu && quyu != -1 && quyu != 0) {
			query.setParameter("quyu", quyu);
		}
		if (null != room && room != -1 && room != 0) {
			query.setParameter("room", room);
		}
		List<?> listtotal = query.list();
		// 当为缺省值的时候进行赋值
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);// 第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);// 每页多少行
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize)
				.setMaxResults(pagesize).list();

		List<Individual_Json> list1 = new ArrayList<Individual_Json>();
		if (null != list) {
			for (Object obj : list) {
				Object[] objs = (Object[]) obj;

				Individual_Json individual = new Individual_Json();
				individual.setId((Integer) objs[0]);
				individual.setMonkeyid((String) objs[1]);
				individual.setAnimaltypeName((String) objs[2]);
				individual.setBirthday((String) objs[3]);
				individual.setFrid((String) objs[4]);
				if (null != objs[5]) {
					individual.setAgetype((Integer) objs[5]);
				}
				// 种群
				if (null != objs[6]) {
					individual.setIszhongqun((Byte) objs[6]);
				}
				if (null != objs[7]) {
					individual.setSex((Byte) objs[7]);
				}
				// 录入人
				if (null != objs[8]) {
					individual.setSource((Integer) objs[8]);
				}
				// 代数 12345 generation
				if (null != objs[9]) {
					individual.setGeneration((Byte) objs[9]);
				}
				// leavebreastdate
				if (null != objs[10]) {
					individual.setLeavebreastdate((String) objs[10]);
				}
				// currentweight
				if (null != objs[11]) {
					individual.setCurrentweight((Float) objs[11]);
				}
				// birthdayweight
				if (null != objs[12]) {
					individual.setBirthdayweight((Float) objs[12]);
				}
				// leavebreastweight
				if (null != objs[13]) {
					individual.setLeavebreastweight((Float) objs[13]);
				}
				// fatherid
				if (null != objs[14]) {
					individual.setFatherid((String) objs[14]);
				}
				// motherid
				if (null != objs[15]) {
					individual.setMotherid((String) objs[15]);
				}
				// blongarea
				if (null != objs[16]) {
					// int areaid = (Integer)objs[16];
					// Area area = areaService.getById(Long.valueOf(areaid));
					// individual.setBlongarea((Integer)objs[16]);
					//
					// //room
					// Area area2 = new Area();
					// if(null != objs[18] ){
					// int areaid2 = (Integer)objs[18];
					// area2 = areaService.getById(Long.valueOf(areaid2));
					// individual.setBlongarea((Integer)objs[18]);
					// //individual.setQuyu(area2.getAreaname());
					// }
					// if(null != objs[18]){
					// individual.setQuyu(area.getAreaname()+"    "+area2.getAreaname());
					// }else{
					// individual.setQuyu(area.getAreaname());
					// }
					//individual.setQuyu((String) objs[16] + "    "+ (String) objs[18]);
					individual.setQuyu((String) objs[16]);
				}
				// dong
				if (null != objs[17]) {
					individual.setDong((Integer) objs[17]);
				}
				//room
				if (null != objs[18]) {
					individual.setRoomName((String) objs[18]);
				}
				// keeper饲养员
				if (null != objs[19]) {
					// int keepid = (Integer)objs[19];
					// Employee employee =
					// employeeService.getById(Long.valueOf(keepid));
					individual.setKeeperp((String) objs[19]);
					// individual.setKeeper((Integer)objs[19]);

				}
				// veterinarian 兽医
				if (null != objs[20]) {
					// individual.setKeeper((Integer)objs[20]);
				}
				// operater 操作员（null）
				if (null != objs[21]) {
					// individual.setOperater((Integer)objs[21]);
				}
				// deleted
				if (null != objs[22]) {
					individual.setDeleted((Byte) objs[22]);
				}
				// modified_by 修改者
				if (null != objs[23]) {
					individual.setModified_by((Integer) objs[23]);
				}
				// createtime 创建时间
				if (null != objs[24]) {
					individual.setCreatetime((Date) objs[24]);
				}
				// created_by 创建人
				if (null != objs[25]) {
					individual.setCreated_by((Integer) objs[25]);
				}
				// lastmodifytime最后修改时间
				if (null != objs[26]) {
					individual.setLastmodifytime((Date) objs[26]);
				}
				// tnid 体内芯片
				if (null != objs[27]) {
					individual.setTnid((String) objs[27]);
				}
				// ysz
				if (null != objs[28]) {
					individual.setYsz((String) objs[28]);
				}
				// yjdate
				if (null != objs[29]) {
					individual.setYjdate((String) objs[29]);
				}
				// sourceaddress
				if (null != objs[30]) {
					individual.setSourceaddress((String) objs[30]);
				}
				// yjaddress
				if (null != objs[31]) {
					individual.setYjaddress((String) objs[31]);
				}
				// remark
				if (null != objs[32]) {
					individual.setRemark((String) objs[32]);
				}
				// status
				if (null != objs[33]) {
					individual.setStatus((Integer) objs[33]);
				}
				// lhao
				if (null != objs[34]) {
					individual.setLhao((String) objs[34]);
				}
				// currentdate
				if (null != objs[35]) {
					individual.setCurrentdate((String) objs[35]);
				}
				// imag
				if (null != objs[36]) {
					individual.setImage((String) objs[36]);
				}
				// weighingDate称重日期
				if (null != objs[37]) {
					individual.setWeighingDate((Date) objs[37]);
				}
				list1.add(individual);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list1);
		map.put("total", listtotal.size());
		return map;
	}

	public List<Map<String, Object>> getRoomIndividual(String blongarea,
			String room) {
		String sql = "select id,monkeyid from individual where blongarea=:blongarea and room=:room";

		Query query = getSession().createSQLQuery(sql);

		if (!("").equals(blongarea) && blongarea != null && !("").equals(room)
				&& room != null) {
			query.setParameter("blongarea", blongarea);
			query.setParameter("room", room);
		}
		List<?> listSql = query.list();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (listSql != null) {
			for (Object obj : listSql) {
				Map<String, Object> map = new HashMap<String, Object>();
				Object[] objs = (Object[]) obj;
				map.put("id", (Integer) objs[0]);
				map.put("text", (String) objs[1]);
				list.add(map);
			}
		}
		return list;

	}

	public List<?> getCountMonkey() {
		String sql = "SELECT COUNT(id) FROM individual i1  WHERE deleted != 1 AND agetype = 3 AND sex = 0";
		return null;
	}

	public BigInteger getMonkeyCount(Integer room, Integer agetype, Byte sex) {
		BigInteger count = null;
		String sql = "select Count(id) from individual where deleted!=1 and agetype=:type and room=:room";
		if (agetype != null) {
			if (agetype == 3 ) {
				sql = sql + " and sex=:sex";
			}
			Query query = getSession().createSQLQuery(sql);
			query.setParameter("type", agetype);
			query.setParameter("room", room);
			if (agetype == 3 ) {

				query.setParameter("sex", sex);
			}
			List<?> list = query.list();

			count = (BigInteger) list.get(0);
		}
		return count;

	}
	public BigInteger getMonkeyCount(Integer room, Integer agetype) {
		BigInteger count = null;
		String sql = "select Count(id) from individual where deleted!=1 and agetype=:type and room=:room";
		
			
			Query query = getSession().createSQLQuery(sql);
			query.setParameter("type", agetype);
			query.setParameter("room", room);
			
			List<?> list = query.list();

			count = (BigInteger) list.get(0);
		
		return count;

	}

	public List<?> getMonkeyAgeType() {
		String sql1 = "select distinct agetype from individual";
		List<?> list1 = getSession().createSQLQuery(sql1).list();

		return list1;
	}

	public List<?> getMonkeySex() {
		String sql2 = "select distinct sex from individual";
		List<?> list2 = getSession().createSQLQuery(sql2).list();
		return list2;
	}

	public List<?> getSaleCountByType(Date nows, int agetype,int sex) {
		String begin=DateUtil.dateToString(DateUtil.AddDate(nows, -30), "yyyy-MM-dd");
		String end=DateUtil.dateToString(nows, "yyyy-MM-dd");
		String sql="select monkeylist,salecount " +
				"from sale s left join individual i on i.monkeyid=s.monkeyid " +
				"where s.deleted!=1 and s.status=5 and s.outdate between :begin and :end ";
		
		if(!"".equals(agetype)){
			sql=sql+" and i.agetype=?";
		}
		if (agetype==3) {
			if (!"".equals(sex)) {
				sql = sql + " and i.sex=?";
			}
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(agetype)){
			query.setParameter(0, agetype);
		}
		if (agetype==3) {
			if (!"".equals(sex)) {
				query.setParameter(1, sex);
			}
		}
		query.setParameter("begin", begin);
		query.setParameter("end", end);
		
		return (List<?>)query.list();
	}
	//小写.
	public Map<String, Object> getAllIndividualByMores(String page, String rows,
			String monkeyid, Integer animaltype, Integer sex, Integer keeper,
			String yjaddress, String sourceaddress, Double mincurrentweight,
			Double maxcurrentweight, Date birthdaymin, Date birthdaymax,
			Date yjdatemin, Date yjdatemax, String monkeyidMin,
			String monkeyidMax, Integer quyu,String bv,String stlv,String srv,String siv,String measles,String hepatitisa,
			String amb,String gxc,String lyc,String bmc,String twjsc,
			String shig,String salm,String yers,String mazhen,String jiag,String yig) {
		String sql = "SELECT i.id,i.monkeyid,animaltype,birthday,frid,agetype,iszhongqun,sex,source"
				+ ",generation,leavebreastdate,currentweight,birthdayweight,leavebreastweight "
				+ " ,fatherid,motherid"
				+ ",(SELECT areaname FROM area WHERE id=i.blongarea) AS blongarea"
				+ ",dong"
				+ ",(SELECT areaname FROM area WHERE id=i.room) AS room"
				+ ",(SELECT e.name FROM  employee AS e WHERE e.id=i.keeper) as keeper"
				+ ",(SELECT e.name FROM  employee AS e WHERE e.id=i.veterinarian) as veterinarian"
				+ ",(SELECT e.name FROM  employee AS e WHERE e.id=i.operater) as operater"
				+ ",i.deleted"
				+ " ,i.modified_by,i.createtime,i.created_by,i.lastmodifytime,tnid,ysz,yjdate,sourceaddress"
				+ " ,yjaddress,i.remark,status,lhao,currentdate,image,weighingDate "
				+ " FROM (select * from individual where deleted!=1) as i" 
				+ " left join virus v on i.monkeyid=v.monkeyid left join parasite p on i.monkeyid=p.monkeyid left join bacteria b on i.monkeyid=b.monkeyid left join vaccine v2 on v2.monkeyid=i.monkeyid " ;
				//+ "  WHERE i.deleted != 1 and i.monkeyid=v.monkeyid or i.monkeyid=p.monkeyid or i.monkeyid=b.monkeyid";
		sql=sql+" where 1=1";
		boolean f1=!"0".equals(bv)||!"0".equals(stlv)||!"0".equals(srv)||!"0".equals(siv)||!"0".equals(measles)||!"0".equals(hepatitisa);
		boolean f2=!"0".equals(amb)||!"0".equals(gxc)||!"0".equals(lyc)||!"0".equals(bmc)||!"0".equals(twjsc);
		boolean f3=!"0".equals(shig)||!"0".equals(salm)||!"0".equals(yers);
		
		//病毒
		if(!"".equals(bv)||!"".equals(stlv)||!"".equals(srv)||!"".equals(siv)||!"".equals(measles)||!"".equals(hepatitisa)){
			if(!"".equals(bv)&&bv!=null){
				sql = sql + " and v.bv=:bv";
			}
			if(!"".equals(stlv)&&stlv!=null){
				sql=sql+" and v.stlv=:stlv";
			}
			if(!"".equals(srv)&&srv!=null){
				sql=sql+" and v.srv=:srv";
			}
			if(!"".equals(siv)&&siv!=null){
				sql=sql+" and v.siv=:siv";
			}
			if(!"".equals(measles)&&measles!=null){
				sql=sql+" and v.measles=:measles";
			}
			if(!"".equals(hepatitisa)&&hepatitisa!=null){
				sql=sql+" and v.hepatitisa=:hepatitisa";
			}
		}
		//寄生虫
		
		if(!"".equals(amb)||!"".equals(gxc)||!"".equals(lyc)||!"".equals(bmc)||!"".equals(twjsc)){
			if(!"".equals(amb)){
				//如果病毒有值.
				sql = sql + " and p.amb=:amb";
				
			}
			if(!"".equals(gxc)){
					sql = sql + " and p.gxc=:gxc";
				
			}
			if(!"".equals(lyc)){
					sql = sql + " and p.lyc=:lyc";
				
			}
			if(!"".equals(bmc)){
					sql = sql + " and p.bmc=:bmc";
				
			}
			if(!"".equals(twjsc)){
					sql = sql + " and p.twjsc=:twjsc";
				
			}
		}
		//细菌
		if(!"".equals(shig)||!"".equals(salm)||!"".equals(yers)){
			if(!"".equals(shig)){
				//如果病毒有值,或者寄生虫有值.

					sql = sql + " and b.shig=:shig";
			}
			if(!"".equals(salm)){
				
					sql = sql + " and b.salm=:salm";
				
			}
			if(!"".equals(yers)){
				
					sql = sql + " and b.yers=:yers";
				
			}
		}
		//疫苗
		Long q_id=null;
		if("Measles".equals(mazhen)){
			//获取麻疹的ID.
			List<?> listquerantine=getVaccineIdByName("Measles");
			if(listquerantine.size()>0){
				Quarantine q=(Quarantine)listquerantine.get(0);
				q_id=q.getId();
			}
			sql=sql+" and  v2.q_id=:id";
		}
		if("HepatitisA".equals(mazhen)){
			//获取甲肝的ID.
			List<?> listquerantine=getVaccineIdByName("HepatitisA");
			if(listquerantine.size()>0){
				Quarantine q=(Quarantine)listquerantine.get(0);
				q_id=q.getId();
			}
			sql=sql+" and  v2.q_id=:id";
		}
		if("HepatitisB".equals(mazhen)){
			//获取乙肝的ID.
			List<?> listquerantine=getVaccineIdByName("HepatitisB");
			if(listquerantine.size()>0){
				Quarantine q=(Quarantine)listquerantine.get(0);
				q_id=q.getId();
			}
			sql=sql+" and  v2.q_id=:id";
		}
		if (null != monkeyid && !monkeyid.equals("")) {
			sql = sql + " and monkeyid = :monkeyid ";
		}
		if (null != animaltype && !animaltype.equals("") && animaltype != 0) {
			sql = sql + " and animaltype = :animaltype ";
		}
		if (null != sex && !sex.equals("-1") && sex != -1) {
			sql = sql + " and sex = :sex ";
		}
		if (null != keeper && !keeper.equals("-1") && keeper != -1) {
			sql = sql + " and keeper = :keeper ";
		}
		if (null != yjaddress && !yjaddress.equals("")) {
			sql = sql + " and yjaddress = :yjaddress ";
		}
		if (null != sourceaddress && !sourceaddress.equals("")) {
			sql = sql + " and sourceaddress = :sourceaddress ";
		}
		if (null != maxcurrentweight && null != mincurrentweight
				&& !maxcurrentweight.equals("") && !mincurrentweight.equals("")) {
			sql = sql
					+ " and currentweight between  :mincurrentweight and :maxcurrentweight ";
		}
		if (null != birthdaymin && null != birthdaymax) {
			sql = sql + " and birthday between  :birthdaymin and :birthdaymax ";
		}
		if (null != yjdatemin && null != yjdatemax) {
			sql = sql + " and yjdate between  :yjdatemin and :yjdatemax ";
		}
		if (quyu != -1 && quyu != 0 && quyu != null) {
			sql = sql + " and ( blongarea  =  :quyu  or room = :quyu )";

		}
		
		
		//对检疫进行分组，防重复项出现
		//sql=sql+" group by v.normal_id";
		List<String> monkeyidList = new ArrayList<String>();
		if (null != monkeyidMin && null != monkeyidMax
				&& !monkeyidMin.equals("") && !monkeyidMax.equals("")) {
			int min = Integer.valueOf("10" + monkeyidMin).intValue();
			int max = Integer.valueOf("10" + monkeyidMax).intValue();
			for (; min <= max; min++) {
				monkeyidList.add(String.valueOf(min).substring(2, 9).trim());
			}
		}
		if (null != monkeyidMin && null != monkeyidMax
				&& !monkeyidMin.equals("") && !monkeyidMax.equals("")) {
			sql = sql + " and monkeyid in  (:monkeyidList) ";
		}
		Query query = getSession().createSQLQuery(sql);
		if (null != monkeyid && !monkeyid.equals("")) {
			query.setParameter("monkeyid", monkeyid);
		}
		if (null != animaltype && !animaltype.equals("-1") && animaltype != -1) {
			query.setParameter("animaltype", animaltype);
		}
		if (null != sex && !sex.equals("-1") && sex != -1) {
			query.setParameter("sex", sex);
		}
		if (null != keeper && !keeper.equals("-1") && keeper != -1) {
			query.setParameter("keeper", keeper);
		}
		if (null != yjaddress && !yjaddress.equals("")) {
			query.setParameter("yjaddress", yjaddress);
		}
		if (null != sourceaddress && !sourceaddress.equals("")) {
			query.setParameter("sourceaddress", sourceaddress);
		}
		if (null != maxcurrentweight && null != mincurrentweight
				&& !maxcurrentweight.equals("") && !mincurrentweight.equals("")) {
			query.setParameter("maxcurrentweight", maxcurrentweight);
			query.setParameter("mincurrentweight", mincurrentweight);
		}
		if (null != birthdaymin && null != birthdaymax) {
			query.setParameter("birthdaymin", birthdaymin);
			query.setParameter("birthdaymax", birthdaymax);
		}
		if (null != yjdatemin && null != yjdatemax) {
			query.setParameter("yjdatemin", yjdatemin);
			query.setParameter("yjdatemax", yjdatemax);
		}
		if (null != monkeyidMin && null != monkeyidMax
				&& !monkeyidMin.equals("") && !monkeyidMax.equals("")) {
			query.setParameterList("monkeyidList", monkeyidList);
		}
		if (quyu != -1 && quyu != 0 && quyu != null) {
			query.setParameter("quyu", quyu);

		}
		//病毒
		if(!"".equals(bv)||!"".equals(stlv)||!"".equals(srv)||!"".equals(siv)||!"".equals(measles)||!"".equals(hepatitisa)){
			if(!"".equals(bv)){
				query.setParameter("bv", bv);
			}
			if(!"".equals(stlv)){
				query.setParameter("stlv", stlv);
			}
			if(!"".equals(srv)){
				query.setParameter("srv", srv);
			}
			if(!"".equals(siv)){
				query.setParameter("siv", siv);
			}
			if(!"".equals(measles)){
				query.setParameter("measles", measles);
			}
			if(!"".equals(hepatitisa)){
				query.setParameter("hepatitisa", hepatitisa);
			}
		}
		//寄生虫
		if(!"".equals(amb)||!"".equals(gxc)||!"".equals(lyc)||!"".equals(bmc)||!"".equals(twjsc)){
			if(!"".equals(amb)){
				query.setParameter("amb", amb);
			}
			if(!"".equals(gxc)){
				query.setParameter("gxc", gxc);
			}
			if(!"".equals(lyc)){
				query.setParameter("lyc", lyc);
			}
			if(!"".equals(bmc)){
				query.setParameter("bmc", bmc);
			}
			if(!"".equals(twjsc)){
				query.setParameter("twjsc", twjsc);
			}
		}
		//细菌
		if(!"".equals(shig)||!"".equals(salm)||!"".equals(yers)){
			if(!"".equals(shig)){
				query.setParameter("shig", shig);
			}
			if(!"".equals(salm)){
				query.setParameter("salm", salm);
			}
			if(!"".equals(yers)){
				query.setParameter("yers", yers);
			}
		}
		//疫苗
		if(!"".equals(mazhen)&&mazhen!=null){
			query.setParameter("id", q_id);
		}
		List<?> listtotal = query.list();
		// 当为缺省值的时候进行赋值
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);// 第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);// 每页多少行
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize)
				.setMaxResults(pagesize).list();

		List<Individual_Json> list1 = new ArrayList<Individual_Json>();
		if (null != list) {
			for (Object obj : list) {
				Object[] objs = (Object[]) obj;

				Individual_Json individual = new Individual_Json();
				individual.setId((Integer) objs[0]);
				individual.setMonkeyid((String) objs[1]);
				individual.setAnimaltype((Integer) objs[2]);
				individual.setBirthday((String) objs[3]);
				individual.setFrid((String) objs[4]);
				if (null != objs[5]) {
					individual.setAgetype((Integer) objs[5]);
				}
				// 种群
				if (null != objs[6]) {
					individual.setIszhongqun((Byte) objs[6]);
				}
				if (null != objs[7]) {
					individual.setSex((Byte) objs[7]);
				}
				// 录入人
				if (null != objs[8]) {
					individual.setSource((Integer) objs[8]);
				}
				// 代数 12345 generation
				if (null != objs[9]) {
					individual.setGeneration((Byte) objs[9]);
				}
				// leavebreastdate
				if (null != objs[10]) {
					individual.setLeavebreastdate((String) objs[10]);
				}
				// currentweight
				if (null != objs[11]) {
					individual.setCurrentweight((Float) objs[11]);
				}
				// birthdayweight
				if (null != objs[12]) {
					individual.setBirthdayweight((Float) objs[12]);
				}
				// leavebreastweight
				if (null != objs[13]) {
					individual.setLeavebreastweight((Float) objs[13]);
				}
				// fatherid
				if (null != objs[14]) {
					individual.setFatherid((String) objs[14]);
				}
				// motherid
				if (null != objs[15]) {
					individual.setMotherid((String) objs[15]);
				}
				// blongarea
				if (null != objs[16]) {
					// int areaid = (Integer)objs[16];
					// Area area = areaService.getById(Long.valueOf(areaid));
					// individual.setBlongarea((Integer)objs[16]);
					//
					// //room
					// Area area2 = new Area();
					// if(null != objs[18] ){
					// int areaid2 = (Integer)objs[18];
					// area2 = areaService.getById(Long.valueOf(areaid2));
					// individual.setBlongarea((Integer)objs[18]);
					// //individual.setQuyu(area2.getAreaname());
					// }
					// if(null != objs[18]){
					// individual.setQuyu(area.getAreaname()+"    "+area2.getAreaname());
					// }else{
					// individual.setQuyu(area.getAreaname());
					// }
					individual.setQuyu((String) objs[16] + " "
							+ (String) objs[18]);
				}
				// dong
				if (null != objs[17]) {
					individual.setDong((Integer) objs[17]);
				}

				// keeper饲养员
				if (null != objs[19]) {
					// int keepid = (Integer)objs[19];
					// Employee employee =
					// employeeService.getById(Long.valueOf(keepid));
					individual.setKeeperp((String) objs[19]);
					// individual.setKeeper((Integer)objs[19]);

				}
				// veterinarian 兽医
				if (null != objs[20]) {
					// individual.setKeeper((Integer)objs[20]);
				}
				// operater 操作员（null）
				if (null != objs[21]) {
					// individual.setOperater((Integer)objs[21]);
				}
				// deleted
				if (null != objs[22]) {
					individual.setDeleted((Byte) objs[22]);
				}
				// modified_by 修改者
				if (null != objs[23]) {
					individual.setModified_by((Integer) objs[23]);
				}
				// createtime 创建时间
				if (null != objs[24]) {
					individual.setCreatetime((Date) objs[24]);
				}
				// created_by 创建人
				if (null != objs[25]) {
					individual.setCreated_by((Integer) objs[25]);
				}
				// lastmodifytime最后修改时间
				if (null != objs[26]) {
					individual.setLastmodifytime((Date) objs[26]);
				}
				// tnid 体内芯片
				if (null != objs[27]) {
					individual.setTnid((String) objs[27]);
				}
				// ysz
				if (null != objs[28]) {
					individual.setYsz((String) objs[28]);
				}
				// yjdate
				if (null != objs[29]) {
					individual.setYjdate((String) objs[29]);
				}
				// sourceaddress
				if (null != objs[30]) {
					individual.setSourceaddress((String) objs[30]);
				}
				// yjaddress
				if (null != objs[31]) {
					individual.setYjaddress((String) objs[31]);
				}
				// remark
				if (null != objs[32]) {
					individual.setRemark((String) objs[32]);
				}
				// status
				if (null != objs[33]) {
					individual.setStatus((Integer) objs[33]);
				}
				// lhao
				if (null != objs[34]) {
					individual.setLhao((String) objs[34]);
				}
				// currentdate
				if (null != objs[35]) {
					individual.setCurrentdate((String) objs[35]);
				}
				// imag
				if (null != objs[36]) {
					individual.setImage((String) objs[36]);
				}
				// weighingDate称重日期
				if (null != objs[37]) {
					individual.setWeighingDate((Date) objs[37]);
				}
				list1.add(individual);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list1);
		map.put("total", listtotal.size());
		return map;
	}
	
	public List<?> getDeathCount(Date nows) {
		String begin=DateUtil.dateToString(DateUtil.AddDate(nows, -30), "yyyy-MM-dd");
		String end=DateUtil.dateToString(nows, "yyyy-MM-dd");
		String sql="select sum(case sex when 0  and agetype=3 then 1 else 0 end ) as '公猴'," +
				"sum(case sex when 1 and agetype=3 then 1 else 0 end) as '母猴'," +
				"sum(case agetype=2 when 2 then 1 ELSE 0 end) as '育成'," +
				"sum(case agetype=1 when 1 then 1 else 0 end) as'仔猴' " +
				"from " +
				"(select i.sex sex,i.agetype agetype from " +
				"(select * from death where deathdate between :begin and :end) as d left join individual i on i.monkeyid=d.monkeyid) as A";
		
		Query query=getSession().createSQLQuery(sql);
		
		query.setParameter("begin", begin);
		query.setParameter("end", end);
		
		return (List<?>)query.list();
	}
	public List<?> getLeavebreastCount(Date nows) {
		String begin=DateUtil.dateToString(DateUtil.AddDate(nows, -30), "yyyy-MM-dd");
		String end=DateUtil.dateToString(nows, "yyyy-MM-dd");
		
		String sql="select sum(case sex when 0  and agetype=3 then 1 else 0 end ) as '公猴' , " +
				"sum(case sex when 1 and agetype=3 then 1 else 0 end) as '母猴', " +
				"sum(case agetype=2 when 2 then 1 ELSE 0 end) as '育成'," +
				"sum(case agetype=1 when 1 then 1 else 0 end) as'仔猴'  " +
				"from individual where deleted!=1 and leavebreastdate between  :begin and :end ";
		
		Query query=getSession().createSQLQuery(sql);
		
		query.setParameter("begin", begin);
		query.setParameter("end", end);
		
		return (List<?>)query.list();
	}
	//deleted=0status=1在场.
	public List<?> getMonkeyCount(){
		String sql="select sum(case sex when 0  and agetype=3 then 1 else 0 end ) as '公猴' ," +
				"sum(case sex when 1 and agetype=3 then 1 else 0 end) as '母猴'," +
				"sum(case agetype=2 when 2 then 1 ELSE 0 end) as '育成'," +
				"sum(case agetype=1 when 1 then 1 else 0 end) as'仔猴' from individual where deleted=0 and status=1 ";
		
		Query query=getSession().createSQLQuery(sql);
		
		return (List<?>)query.list();
	}

	public List<Individual> getindividuals(String room) {
		String hql="  From Individual where deleted!=1 ";
		if(!"".equals(room)&&room!=null){
			hql=hql+" and room=:room";
		}
		Query query=getSession().createQuery(hql);
		if(!"".equals(room)&&room!=null){
			query.setParameter("room", Integer.parseInt(room));
		}
		return query.list();
	}
	public List<Individual_Json> getIndividualJsonById(Integer id){
		String sql="select monkeyid,(select name from animaltype a where a.id=i.animaltype),sex,currentweight,birthday,leavebreastdate,fatherid,motherid from individual i where id=:id";
		Query query=getSession().createSQLQuery(sql);
		query.setParameter("id", id);
		List<?> list=query.list();
		List<Individual_Json> lists=new ArrayList<Individual_Json>();
		for(Object ob:list){
			Object[]objs=(Object[])ob;
			Individual_Json ij=new Individual_Json();
			ij.setMonkeyid((String )objs[0]);
			ij.setAnimaltypeName((String)objs[1]);
			ij.setSex((Byte)objs[2]);
			ij.setCurrentweight((Float)objs[3]);
			ij.setBirthday((String)objs[4]);
			ij.setLeavebreastdate((String)objs[5]);
			ij.setFatherid((String)objs[6]);
			ij.setMotherid((String)objs[7]);
			
			lists.add(ij);
		}
		return lists;
	}
	
	public List<?> getVaccineIdByName(String name) {
		String sql="from Quarantine ";
		if(!"".equals(name)&&name!=null){
			sql=sql+" where name=?";
		}
		Query query=getSession().createQuery(sql);
		if(!"".equals(name)&&name!=null){
			query.setParameter(0, name);
		}
		return query.list();
	}
	public List<Publiccode> getYJAddressInfo(String mark) {
		String hql="from Publiccode ";
		if(!"".equals(mark)&&mark!=null){
			hql=hql+" where mark=?";
		}
		Query query=getSession().createQuery(hql);
		query.setParameter(0, mark);
		List<Publiccode> l=query.list();
		return l;
	}
	
	public Individual_Json getIndividualJsonById(String id){
		String sql="select monkeyid,(select name from animaltype a where a.id=i.animaltype),sex,currentweight,birthday,leavebreastdate," +
				"fatherid,motherid,(select name from employee e where e.id=i.keeper) as keeper," +
				"(select areaname from area a where a.id=i.room) as room," +
				"(select areaname from area a where a.id=i.blongarea) as quyu," +
				"agetype,chipid,weighingDate,leavebreastweight,(select name from publiccode where id=i.yjaddress)yjaddress,remark,birthdayweight from individual i where monkeyid=:id";
		Query query=getSession().createSQLQuery(sql);
		query.setParameter("id", id);
		List<?> list=query.list();
		Individual_Json ij=new Individual_Json();
		if(list.size()>0){
			for(Object ob:list){
				Object[]objs=(Object[])ob;
				
				ij.setMonkeyid((String )objs[0]);
				ij.setAnimaltypeName((String)objs[1]);
				ij.setSex((Byte)objs[2]);
				ij.setCurrentweight((Float)objs[3]);
				ij.setBirthday((String)objs[4]);
				ij.setLeavebreastdate((String)objs[5]);
				ij.setFatherid((String)objs[6]);
				ij.setMotherid((String)objs[7]);
				ij.setKeeperp((String)objs[8]);
				ij.setRoomName((String)objs[9]);
				ij.setQuyu((String)objs[10]);
				ij.setAgetype((Integer)objs[11]);
				ij.setChipid((String)objs[12]);
				ij.setWeighingDate((Date)objs[13]);
				ij.setLeavebreastweight((Float)objs[14]);
				ij.setYjaddress((String)objs[15]);
				ij.setRemark((String)objs[16]);
				ij.setBirthdayweight((Float)objs[17]);
			}
			return ij;
		}
		
		return null;
	}
	public List<Normal> getCheckInfoById(String monkeyid) {
		String sql=" from Normal where monkeylist  like :monkeyid";
		List<Normal> ln=getSession().createQuery(sql).setParameter("monkeyid", "%"+monkeyid+"%").list();
		return ln;
	}
	private Publiccode getPubliccodeById(Integer id){
		String sql="from Publiccode where id=?";
		Query query=getSession().createQuery(sql);
		query.setParameter(0, id);
		Publiccode p=(Publiccode)query.list().get(0);
		return p;
	}
	public String getMonkeyIdByChipId(String chipid) {
		String hql="from Individual where chipid=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0, chipid);
		Individual in=(Individual)query.list().get(0);
		if(in!=null){
			return in.getMonkeyid();
		}else{
			return null;
		}
		
	}
}
