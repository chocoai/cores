package com.lanen.view.action.arp;

import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Area;
import com.lanen.model.Changeroom;
import com.lanen.model.ChangeroomReport;
import com.lanen.model.Changeroom_Json;
import com.lanen.model.Employee;
import com.lanen.model.Individual;
import com.lanen.service.arp.AreaService;
import com.lanen.service.arp.ChangeroomService;
import com.lanen.service.arp.EmployeeService;
import com.lanen.service.arp.IndividualService;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.lanen.util.RandomUtil;

@Controller
@Scope("prototype")
public class ChangeroomAction extends BaseAction<Changeroom> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -421374595868953047L;
	@Resource
	private ChangeroomService changeroomService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private AreaService areaService;
	@Resource
	private IndividualService individualService;

	private String page;
	private String rows;

	private String changeroomDate;
	private List<ChangeroomReport> dataSourceList;
	private Map<String,Object> paraMap;
	private String fileName;
	private Long xkeeper;
	/**
	 * 跳转到调栏记录页面
	 * 
	 * @return
	 */
	public String list() {
		return "list";
	}

	@SuppressWarnings("unchecked")
	public void loadList() {
		Map<String, Object> map = changeroomService.getListByConditions(page,
				rows, model.getMonkeyid(), model.getChangeroomdate());
		List<Changeroom> list = (List<Changeroom>) map.get("rows");
		List<Changeroom_Json> list2 = new ArrayList<Changeroom_Json>();
		for (Changeroom c : list) {
			Changeroom_Json json = new Changeroom_Json();
			json.setId(c.getId());
			json.setMonkeyid(c.getMonkeyid());
			json.setChangeroomdate(c.getChangeroomdate());
			String inarea = c.getChangeinarea();
			String inroom = c.getChangeinroom();
			if (inarea != null && inroom != null) {
				Long inareaId = Long.parseLong(inarea);
				String inareaName = getAreaName(inareaId);
				json.setChangeinarea(inareaName);
				Long inroomId = Long.parseLong(inroom);
				String inroomName = getAreaName(inroomId);
				json.setChangeinroom(inroomName);
			}
			json.setLhao(c.getLhao());
			String protecor = c.getProtector();
			if (protecor != null&&!"".equals(protecor)) {
				Long protectorId = Long.parseLong(protecor);
				String protecorName = getEmpName(protectorId);
				json.setProtector(protecorName);
			}
			String yareaName = getAreaName(c.getYarea());
			json.setYareaName(yareaName);
			String yroomName = getAreaName(c.getYroom());
			json.setYroomName(yroomName);
			json.setYlh(c.getYlh());
			json.setRemark(c.getRemark());
			String recorder = c.getRecorder();
			if (recorder != null&&!"".equals(recorder)) {
				Long recoderId = Long.parseLong(recorder);
				String recorderName = getEmpName(recoderId);
				json.setRecorder(recorderName);
			}
			//改为雕栏后所属饲养员.
			String operator = c.getOperater();
			if (operator != null&&!"".equals(operator)) {
				Long operatorId = Long.parseLong(operator);
				String operatorName = getEmpName(operatorId);
				json.setOperater(operatorName);
			}
			//原饲养员.
			Long ykeeper = c.getYkeeper();
			if (ykeeper != null&&!"".equals(ykeeper)) {
				String ykeeperName = getEmpName(ykeeper);
				json.setYkeeperName(ykeeperName);
			}
			list2.add(json);
		}
		Map<String, Object> map2 = new HashMap<String, Object>();
		Integer total = (Integer) map.get("total");
		map2.put("rows", list2);
		map2.put("total", total);
		String jsonStr = JsonPluginsUtil.beanToJson(map2, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	/**
	 * 根据区域ID获得区域名
	 * 
	 * @param id
	 * @return
	 */
	public String getAreaName(Long id) {
		String areaName = null;
		Area area = areaService.getById(id);
		if (area != null) {
			areaName = area.getAreaname();
		}
		return areaName;
	}

	/**
	 * 根据员工ID获得员工姓名
	 * 
	 * @param id
	 * @return
	 */
	public String getEmpName(Long id) {
		String name = null;
		if (id != null) {
			Employee e = employeeService.getById(id);
			if (e != null) {
				name = e.getName();
			}
		}
		return name;
	}

	/**
	 * 添加调栏记录
	 */
	public void add() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Changeroom c = new Changeroom();
		if (model.getMonkeyid() != null && !"".equals(model.getMonkeyid())) {
			c.setMonkeyid(model.getMonkeyid());
			c.setChangeroomdate(model.getChangeroomdate());
			c.setChangeinarea(model.getChangeinarea());
			c.setChangeinroom(model.getChangeinroom());
			c.setLhao(model.getLhao());
			c.setProtector(model.getProtector());
			Individual individual = individualService.getByMonkeyid(model
					.getMonkeyid());
			Integer blongarea2 = individual.getBlongarea();
			Long yarea = Long.parseLong(blongarea2 + "");
			//查询该动物原所属饲养员.
			Integer ykeeper=individual.getKeeper();
			c.setYkeeper(Long.valueOf(ykeeper+""));
			
			c.setYarea(yarea);
			Integer room2 = individual.getRoom();
			Long yroom = Long.parseLong(room2 + "");
			c.setYroom(yroom);
			c.setYlh(individual.getLhao());
			c.setRecorder(model.getRecorder());
			//改为调栏之后所属饲养员.
			c.setOperater(model.getOperater());
			c.setRemark(model.getRemark());
			//
			changeroomService.save(c);
			// Individual
			// individual=individualService.getByMonkeyid(c.getMonkeyid());
			String changeinArea = c.getChangeinarea();
			Integer blongarea = Integer.parseInt(changeinArea);
			individual.setBlongarea(blongarea);
			String changeinroom = c.getChangeinroom();
			Integer room = Integer.parseInt(changeinroom);
			individual.setRoom(room);
			if (!"".equals(model.getOperater())&&model.getOperater()!=null) {
				//并更新individual个体中的饲养员.
				individual.setKeeper(Integer.valueOf(model.getOperater()));
			}
			individualService.update(individual);
			map.put("success", true);
			map.put("msg", "添加成功");
			map.put("id", c.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 编辑调栏记录前数据加载
	 */
	public void toEdit() {
		if (model.getId() != null) {
			Changeroom c = changeroomService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(c, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}

	/**
	 * 编辑后保存
	 */
	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null && model.getMonkeyid() != null
				&& !"".equals(model.getMonkeyid())) {
			Changeroom c = changeroomService.getById(model.getId());
			if (c.getMonkeyid() != model.getMonkeyid()) {
				Individual individual = individualService.getByMonkeyid(c
						.getMonkeyid());
				Long yarea = c.getYarea();
				Integer blongarea = yarea.intValue();
				Long yroom = c.getYroom();
				Integer room = yroom.intValue();
				individual.setBlongarea(blongarea);
				individual.setRoom(room);
				individualService.update(individual);
			}
			Individual individual2 = individualService.getByMonkeyid(model
					.getMonkeyid());
			String changeinArea = model.getChangeinarea();
			Integer blongarea2 = Integer.parseInt(changeinArea);
			individual2.setBlongarea(blongarea2);
			String changeinroom = model.getChangeinroom();
			Integer room2 = Integer.parseInt(changeinroom);
			individual2.setRoom(room2);
			individualService.update(individual2);
			c.setMonkeyid(model.getMonkeyid());
			c.setChangeroomdate(model.getChangeroomdate());
			c.setChangeinarea(model.getChangeinarea());
			c.setChangeinroom(model.getChangeinroom());
			c.setLhao(model.getLhao());
			c.setProtector(model.getProtector());
			c.setYlh(model.getYlh());
			c.setRecorder(model.getRecorder());
			c.setOperater(model.getOperater());
			c.setRemark(model.getRemark());
			changeroomService.update(c);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", c.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 删除调栏记录
	 */
	public void delChangeroom() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Changeroom c = changeroomService.getById(model.getId());
			String monkeyid = c.getMonkeyid();
			Individual individual = individualService.getByMonkeyid(monkeyid);
			Long yarea = c.getYarea();
			Integer blongarea = yarea.intValue();
			Long yroom = c.getYroom();
			Integer room = yroom.intValue();
			individual.setBlongarea(blongarea);
			individual.setRoom(room);
			individualService.update(individual);
			changeroomService.delete(model.getId());
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 猴群调拨明细报表
	 * @return
	 */
	public String listChangeroom(){
		return "changeroomRecord";
	}
	public void changeroomByJson(){
		Map<String, Object> map = changeroomService.getChangeroom(page,
				rows, model.getMonkeyid(), model.getChangeroomdate(),xkeeper,model.getYkeeper());
		List<?> list =  (List<?>) map.get("rows");
		List<Map<String,Object>> list2 = new ArrayList<Map<String,Object>>();
		for (Object ob : list) {
			Map<String,Object> json = new HashMap<String,Object>();
			Object[] objs=(Object[])ob;
			json.put("id", objs[0]);
			json.put("monkeyid", objs[1]);
			String inarea = (String)objs[7];
			String inroom = (String)objs[8];
			if (inarea != null && inroom != null) {
				Long inareaId = Long.parseLong(inarea);
				String inareaName = getAreaName(inareaId);
				json.put("changeinarea", inareaName);
				Long inroomId = Long.parseLong(inroom);
				String inroomName = getAreaName(inroomId);
				json.put("changeinroom", inroomName);
			}
			json.put("lhao", objs[9]);
			
			
			String yareaName = getAreaName(Long.parseLong(objs[2]+""));
			json.put("yareaname",yareaName );
			String yroomName = getAreaName(Long.parseLong(objs[3]+""));
			json.put("yroomname", yroomName);
			json.put("ylh",objs[4] );
			json.put("remark", objs[10]);
			json.put("sex", objs[5]);
			json.put("weight", objs[6]);
			json.put("xkeeper", objs[11]);
			json.put("ykeeper", objs[12]);
			json.put("changeroomdate", DateUtil.dateToString((Date)objs[13], "yyyy-MM-dd"));
			//String protecor = c.getProtector();
			
			//String recorder = c.getRecorder();
			
			//String operator = c.getOperater();
			
			list2.add(json);
		}
		Map<String, Object> map2 = new HashMap<String, Object>();
		Integer total = (Integer) map.get("total");
		map2.put("rows", list2);
		map2.put("total", total);
		String jsonStr = JsonPluginsUtil.beanToJson(map2, "yyyy-MM-dd");
		writeJson(jsonStr);
	}
	public String reportChangeroom(){
		return "changeroomReport";
	}
	public String changeroomByReport(){
		String date="";
		if (!"".equals(changeroomDate)&&changeroomDate!=null) {
			date = DateUtil.yymmdd(changeroomDate, "");
		}
		List<?> queryList = changeroomService.getListByConditions(model.getMonkeyid(),date,xkeeper,model.getYkeeper());
		dataSourceList = new ArrayList<ChangeroomReport>();
		paraMap=new HashMap<String,Object>();
		int size=queryList.size();
		String []animalType=new String[size];
		String animalTypeStr="";
		int i=0;
		for (Object c : queryList) {
			
			Object[] objs=(Object[])c;
			ChangeroomReport cg = new ChangeroomReport();
			cg.setMonkeyid((String)objs[3]);
			cg.setYareaname(getAreaName(Long.parseLong(objs[0]+"")));
			cg.setYroomname(getAreaName(Long.parseLong(objs[1]+""))==null?"":getAreaName(Long.parseLong(objs[1]+"")));
			cg.setYlh((String)objs[2]==null?"":(String)objs[2]);
			cg.setSex(((BigInteger)objs[4])+"");
			if (!"".equals(objs[5])&&objs[5]!=null) {
				cg.setWeight(Float.parseFloat(objs[5] + ""));
			}
			cg.setChangeinarea(getAreaName(Long.parseLong(objs[6]+"")));
			cg.setChangeinroom(getAreaName(Long.parseLong(objs[7]+""))==null?"":getAreaName(Long.parseLong(objs[7]+"")));
			cg.setLhao((String)objs[8]);
			cg.setRemark((String)objs[9]==null?"":(String)objs[9]);
			cg.setYkeeper((String)objs[10]==null?"":(String)objs[10]);
			cg.setXkeeper((String)objs[11]==null?"":(String)objs[11]);
			cg.setChangeroomreason("");
			dataSourceList.add(cg);
			
			if (i<queryList.size()) {
				animalType[i] = (String) objs[12];
				i++;
			}
			
		}
		//set去重复
		Set<String> set = new HashSet<String>();
		for(String s : animalType)
		set.add(s);
		//遍历set
		Iterator<String> it = set.iterator();  
		while (it.hasNext()) {  
		  String str = it.next();
		  animalTypeStr=animalTypeStr+str+",";
		}
		URL logoImage=null;		
		logoImage=this.getClass().getResource("logo.jpg");
		paraMap.put("logoImage", logoImage);
		
		fileName = "ChangeroomDetail"+ DateUtil.dateToString(new Date(), "yyyyMMddsss");
		
		paraMap.put("changeroomDate", date);
		String num=RandomUtil.randomNum(5, 10);
		num=Constant.NUM_U+num;
		paraMap.put("changeRoomNum", num);
		paraMap.put("animalType", animalTypeStr);
		
		paraMap.put("title", Constant.TITLE_CHANGEROOM);

		paraMap.put("titleMsg", Constant.COMPANY_NAME);
		paraMap.put("titleMsg_En", Constant.COMPANY_NAME_EN);
		return "changeroomByReport";
	}
	public void getYkeeper(){
		if(model.getMonkeyid()!=null&&!"".equals(model.getMonkeyid())){
			Individual individual=individualService.getByMonkeyid(model.getMonkeyid());
			Integer keeper=individual.getKeeper();
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("success",true );
			map.put("ykeeper", keeper);
			String json=JsonPluginsUtil.beanToJson(map);
			writeJson(json);
		}
	}
	/**
	 * 动物调拨登记时所有原饲养员
	 * @return
	 */
	public void listAllYKeeper(){
		List<Long> listYkeeper=changeroomService.getYKeeper();
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", -1);
		map.put("text", "不限");
		list2.add(map);
		for (Long keeper : listYkeeper) {
			String name = getEmpName(keeper);
			map = new HashMap<String, Object>();
			map.put("id", keeper);
			map.put("text", name);
			list2.add(map);
		}
		String jsonStr = JsonPluginsUtil.beanListToJson(list2);
		writeJson(jsonStr);
	}
	/**
	 * 根据动物调拨记录获取所有现饲养员
	 * @return
	 */
	public void listAllXKeeper(){
		List<Long> listXkeeper=changeroomService.getXKeeper();
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", -1);
		map.put("text", "不限");
		listMap.add(map);
		for(Long k:listXkeeper){
			String name=getEmpName(k);
			map=new HashMap<String,Object>();
			map.put("id", k);
			map.put("text", name);
			listMap.add(map);
		}
		String jsonStr=JsonPluginsUtil.beanListToJson(listMap);
		writeJson(jsonStr);
	}
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getChangeroomDate() {
		return changeroomDate;
	}

	public void setChangeroomDate(String changeroomDate) {
		this.changeroomDate = changeroomDate;
	}

	

	public List<ChangeroomReport> getDataSourceList() {
		return dataSourceList;
	}

	public void setDataSourceList(List<ChangeroomReport> dataSourceList) {
		this.dataSourceList = dataSourceList;
	}

	public Map<String, Object> getParaMap() {
		return paraMap;
	}

	public void setParaMap(Map<String, Object> paraMap) {
		this.paraMap = paraMap;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getXkeeper() {
		return xkeeper;
	}

	public void setXkeeper(Long xkeeper) {
		this.xkeeper = xkeeper;
	}


}
