package com.lanen.view.action.arp;

import java.math.BigInteger;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Employee;
import com.lanen.model.Normal;
import com.lanen.model.Quarantine;
import com.lanen.model.Vaccine;
import com.lanen.model.Vaccine_Json;
import com.lanen.service.arp.ExportService;
import com.lanen.service.arp.NormalService;
import com.lanen.service.arp.QuarantineService;
import com.lanen.service.arp.VaccineService;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.lanen.util.RandomUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class VaccineAction extends BaseAction<Vaccine> {

	/**
	 * 防疫配置，疫苗
	 */
	private static final long serialVersionUID = -899736196629903425L;
	@Resource
	private VaccineService vaccineService;

	@Resource
	private NormalService normalService;
	
	@Resource
	private QuarantineService quarantineService;
	
	@Resource
	private ExportService exportService;
	private String rows;
	private String page;
	private String vaccinename;
	
	private String vac_cdate;
	private String vac_ymlx;
	private String vac_veterinarian;
	private String vac_protector;
	private String vac_recorder;
	private String vac_remark;
	
	private String startTime;
	private String endTime;

	private List<Map<String,Object>> dataSourceList;
	private Map<String,Object> paraMap;
	private String fileName;
	
	private String vaccinedate;
	private String monkeylist;//检疫动物ID数组.
	private String ypins;//疫苗类型
	private String orderid;//检疫ID.
	private String ms;//麻疹
	private String as;//甲肝
	private String bs;//乙肝
	private String remarks;//备注.
	private String checkId;//检疫编号.
	
	// 获取疫苗类型
	public void loadYMLX() {
		List<Map<String, String>> listMap = vaccineService
				.getYMLX(Constant.vaccine);
		String strJson = JsonPluginsUtil.beanListToJson(listMap);
		writeJson(strJson);
	}

	// 常规检疫添加
	public void addNormalVaccine() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getMonkeyid() != null) {
			Vaccine v = new Vaccine();
			v.setMonkeyid(model.getMonkeyid());
			v.setCdate(DateUtil.stringToDate(vac_cdate, "yyyy-MM-dd"));
			v.setQ_id(vac_ymlx);//指疫苗类型
			v.setVeterinarian(vac_veterinarian);
			v.setProtector(vac_protector);
			v.setRecorder(vac_recorder);
			v.setRemark(vac_remark);

			v.setDeleted(Byte.valueOf(Constant.deleted_0.toString()));
			v.setPtype(Constant.normal);
			
			if (!"".equals(request.getParameter("vaccine_normalid"))&&
					request.getParameter("vaccine_normalid")!=null) {
				v.setNormal_id((Integer.valueOf(request
						.getParameter("vaccine_normalid"))));
			}
			vaccineService.save(v);
			
			Normal n=normalService.getById(Long.valueOf(request.getParameter("vaccine_normalid")));
			n.setVaccine(Constant.NORMAL_FLAG);
			n.setLastmodifytime(Timestamp.valueOf(DateUtil.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss")));
			Employee user=(Employee)ActionContext.getContext().getSession().get("user");
			n.setModified_by(Integer.valueOf(user.getId()+""));
			normalService.update(n);
		}
		map.put("success", true);
		map.put("msg", "添加成功");

		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);

	}

	// 常规检疫根据编号查看详情
	public void loadListByMonkeyId() {
		Map<String, Object> map = vaccineService.loadListByMonkeyId(rows, page,
				Constant.normal, model.getMonkeyid());
		String JsonStr = JsonPluginsUtil.beanToJson(map, "yyyy-MM-dd");
		writeJson(JsonStr);
	}

	//疫苗接种记录报表
	public String listVaccine(){
		return "vaccineRecord";
	}
	//预览缓冲页面
	public String loadToReport(){
		return "vaccineLoadToReport";
	}
	//先查猴子，再根据monkeyid查q_id,记录页--打印预览页
	public void loadListVaccineByJson(){
		//麻疹
		String isMeasles=null;
		//甲肝
		String isHepatitisA=null;
		//乙肝
		String isHepatitisB=null;
		
		Map<String,Object> map=vaccineService.getVaccine(page, rows, model.getMonkeyid(), model.getCdate());
		dataSourceList=new ArrayList<Map<String,Object>>();
		List<Vaccine_Json> list=(List<Vaccine_Json>)map.get("rows");
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		for(Vaccine_Json vjson:list){
			Map<String,Object> vaccineMap=new HashMap<String,Object>();
			
			String monkeyid=vjson.getMonkeyid();
			String normalid=vjson.getNormal_id()+"";
			if(monkeyid!=null&&!"".equals(monkeyid)){
				List<?> li=vaccineService.getVaccineId(monkeyid,normalid);
				for(Object oj:li){
					Object[]ojs=(Object[])oj;
					if((ojs[0]==null||"".equals(ojs[0]))&& (ojs[1]==null||"".equals(ojs[1]))){
						isMeasles = "";
						vaccineMap.put("isMeasles", isMeasles);
						isHepatitisA = "";
						vaccineMap.put("isHepatitisA", isHepatitisA);
						isHepatitisB = "";
						vaccineMap.put("isHepatitisB", isHepatitisB);
					}
					if (ojs!=null&&!"".equals(ojs)) {
						String vaccineName = null;
						if (!"".equals(ojs[0])&&ojs[0]!=null) {
							vaccineName = getVaccineName(Long.valueOf((String) ojs[0]));
						}
						if ((Constant.VACCINE_NAME_MEASLES).equals(vaccineName)) {
							isMeasles = "√";
							//vaccineMap.put("isMeasles", isMeasles);normal_id,monkeyid查q_id,ypin.
							
								vaccineMap.put("isMeasles", ojs[1]);
							
							
						}
						if ((Constant.VACCINE_NAME_HEPATITIS_A)
								.equals(vaccineName)) {
							isHepatitisA = "√";
								//vaccineMap.put("isHepatitisA", isHepatitisA);
								vaccineMap.put("isHepatitisA", ojs[1]);
							
						}
						if ((Constant.VACCINE_NAME_HEPATITIS_B)
								.equals(vaccineName)) {
							isHepatitisB = "√";
								vaccineMap.put("isHepatitisB", ojs[1]);
							
							//vaccineMap.put("isHepatitisB", isHepatitisB);
						}
					}
				}
			}
			vaccineMap.put("roomId", vjson.getRoomname());
			vaccineMap.put("lhao", vjson.getLhao());
			vaccineMap.put("monkeyid", vjson.getMonkeyid());
			vaccineMap.put("sex", vjson.getSex());
			
			vaccineMap.put("remark", vjson.getRemark());
			vaccineMap.put("checkId", vjson.getTitle());//检疫编号.
			dataSourceList.add(vaccineMap);
			
			listMap.add(vaccineMap);
		}

		Map<String,Object> vaccineMap=new HashMap<String,Object>();
		Integer total = (Integer) map.get("total");
		vaccineMap.put("total", total);
		vaccineMap.put("rows", listMap);
		String strJson=JsonPluginsUtil.beanToJson(vaccineMap);
		writeJson(strJson);
	}
	public String loadListVaccineByReport(){
		URL logoImage=null;
		logoImage=this.getClass().getResource("logo.jpg");
		fileName="vaccineDetails"+DateUtil.dateToString(new Date(), "yyyyMMddsss");
		
		paraMap=new HashMap<String,Object>();
		paraMap.put("company", Constant.COMPANY_NAME);
		paraMap.put("logoImage", logoImage);
		//paraMap.put("title", Constant.TITLE_XCG_RECORD);
		paraMap.put("checkNumber", Constant.NUM_JE+RandomUtil.randomNum(5, 10));
		paraMap.put("animalType", Constant.animalType);
		paraMap.put("checkId", checkId);
		/*if (!"".equals(vaccinedate)&&vaccinedate!=null) {
			paraMap.put("checkDate", vaccinedate);
		}*/
		//查询检疫时间.
		String checkDate=normalService.getCheckDateByTitle(checkId);
		paraMap.put("checkDate", checkDate);
		//麻疹
		String isMeasles=null;
		//甲肝
		String isHepatitisA=null;
		//乙肝
		String isHepatitisB=null;
		Date date=null;
		if (!"".equals(vaccinedate)&&vaccinedate!=null) {
			 date= DateUtil.stringToDate(vaccinedate, "yyyy-MM-dd");
		}
		List<Vaccine_Json> list=vaccineService.getVaccine(model.getMonkeyid(), date,checkId);
		dataSourceList=new ArrayList<Map<String,Object>>();
		for(Vaccine_Json vjson:list){
			Map<String,Object> vaccineMap=new HashMap<String,Object>();
			
			String monkeyid=vjson.getMonkeyid();
			String normalid=vjson.getNormal_id()+"";
			if(monkeyid!=null&&!"".equals(monkeyid)){
				List<?> li=vaccineService.getVaccineId(monkeyid,normalid);
				for(Object ob:li){
					Object[]obj=(Object[])ob;
					if(obj[0]==null||"".equals(obj[0])){
						isMeasles = "";
						vaccineMap.put("isMeasles", isMeasles);
						isHepatitisA = "";
						vaccineMap.put("isHepatitisA", isHepatitisA);
						isHepatitisB = "";
						vaccineMap.put("isHepatitisB", isHepatitisB);
					}
					if (obj!=null&&!"".equals(obj)) {
						String vaccineName = null;
						if (!"".equals(obj[0])&&obj[0]!=null) {
							vaccineName = getVaccineName(Long.valueOf((String) obj[0]));
						}
						if ((Constant.VACCINE_NAME_MEASLES).equals(vaccineName)) {
							vaccineMap.put("isMeasles", obj[1]==null?"":obj[1]);
						}
						if ((Constant.VACCINE_NAME_HEPATITIS_A).equals(vaccineName)) {
							vaccineMap.put("isHepatitisA", obj[1]==null?"":obj[1]);
						}
						if ((Constant.VACCINE_NAME_HEPATITIS_B).equals(vaccineName)) {
							vaccineMap.put("isHepatitisB", obj[1]==null?"":obj[1]);
						}
					}
				}
			}
			/*if(!vaccineMap.containsKey("isMeasles")){
				vaccineMap.put("isMeasles", "--");
			}
			if(!vaccineMap.containsKey("isHepatitisA")){
				vaccineMap.put("isHepatitisA", "--");
			}
			if(!vaccineMap.containsKey("isHepatitisB")){
				vaccineMap.put("isHepatitisB", "--");
			}*/
			if(!"".equals(vjson.getRemark())&&vjson.getRemark()!=null){
				vaccineMap.put("remark", vjson.getRemark());
			}else{
				vaccineMap.put("remark", "");
			}
			vaccineMap.put("roomId", vjson.getRoomname());
			vaccineMap.put("lhao", vjson.getLhao()==null?"":vjson.getLhao());
			vaccineMap.put("monkeyid", vjson.getMonkeyid());
				vaccineMap.put("sex",vjson.getSex());
			dataSourceList.add(vaccineMap);
			
			//listMap.add(vaccineMap);
		}
		
		/*List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		for(Object ob:list){
			Map<String,Object> vaccineMap=new HashMap<String,Object>();
			
			Object[] objs=(Object[])ob;
			String monkeyid=(String)objs[4];
			if(monkeyid!=null&&!"".equals(monkeyid)){
				List<?> li=vaccineService.getVaccineId(monkeyid);
				for(int i=0;i<li.size();i++){
					String vaccineName=getVaccineName(Long.valueOf((String)li.get(i)));
					
					if((Constant.VACCINE_NAME_MEASLES).equals(vaccineName)){
						isMeasles="√";
						vaccineMap.put("isMeasles", isMeasles);
					}
					if((Constant.VACCINE_NAME_HEPATITIS_A).equals(vaccineName)){
						isHepatitisA="√";
						vaccineMap.put("isHepatitisA", isHepatitisA);
					}
					if((Constant.VACCINE_NAME_HEPATITIS_B).equals(vaccineName)){
						isHepatitisB="√";
						vaccineMap.put("isHepatitisB", isHepatitisB);
					}
				}
			}
			vaccineMap.put("id", objs[0]);
			vaccineMap.put("roomId", objs[2]);
			vaccineMap.put("lhao", objs[3]);
			vaccineMap.put("monkeyid", objs[4]);
			String sex=(String)objs[5];
			if(Constant.MONKEY_SEX_MALE.equals(sex)){
				vaccineMap.put("sex",Constant.MALE_MONKEY);
			}else{
				vaccineMap.put("sex",Constant.FEMALE_MONKEY);
			}
			vaccineMap.put("remark", objs[6]);
			
			listMap.add(vaccineMap);
		}*/	
		/*Map<String,Object> vaccineMap=new HashMap<String,Object>();
		Integer total = (Integer) map.get("total");
		vaccineMap.put("total", total);
		vaccineMap.put("rows", listMap);
		String strJson=JsonPluginsUtil.beanToJson(vaccineMap);
		writeJson(strJson);*/
		return "vaccinePrintToReport";
	}
	public void loadListVaccine(){
		//Map<String,Object> map=vaccineService.getVaccine(page, rows, model.getMonkeyid(), startTime, endTime);
		Map<String,Object> map=vaccineService.getVaccine(page, rows, model.getMonkeyid(), model.getCdate());
		List<Map<String,Object>> dataSourceList=new ArrayList<Map<String,Object>>();
		List<?> list=(List<?>)map.get("rows");
		for(Object ob:list){
			Object[] objs=(Object[])ob;
			Vaccine_Json json=new Vaccine_Json();
			
			String q_id=(String)objs[0];
			
			if(!"".equals(q_id)&&q_id!=null){
				String vaccineName=getVaccineName(Long.parseLong(q_id));
				json.setYmlx(vaccineName);
			}
			json.setRoomId((BigInteger) objs[2]);
			json.setLhao((String) objs[3]);
			json.setMonkeyid((String) objs[4]);
			
			String sex=(String)objs[5];
			if(Constant.MONKEY_SEX_MALE.equals(sex)){
				json.setSex(BigInteger.valueOf(Long.valueOf(Constant.MALE_MONKEY)));
			}else{
				json.setSex(BigInteger.valueOf(Long.valueOf(Constant.FEMALE_MONKEY)));
			}
			json.setRemark((String) objs[6]);
		}
		
	}

	/**
	 * 根据疫苗ID获得疫苗名
	 * 
	 * @param id
	 * @return
	 */
	public String getVaccineName(Long id) {
		String vaccineName = null;
		Quarantine q = quarantineService.getById(id);
		if (q != null) {
			vaccineName = q.getName();
		}
		return vaccineName;
	}
	/**
	 * 根据猴子ID获得疫苗ID
	 * 
	 * @param id
	 * @return
	 */
	public String getVaccineId(Long id) {
		String vaccineName = null;
		Quarantine q = quarantineService.getById(id);
		if (q != null) {
			vaccineName = q.getName();
		}
		return vaccineName;
	}
	
	//出口检疫
	public void addMonkeyList(){
		Map map=new HashMap<String,Object>();
		if(!"".equals(monkeylist)&& monkeylist!=null){
			String[] str=monkeylist.split(",");
			for(int i=0;i<str.length;i++){
				//获取疫苗检测
				//List listItem=quarantineService.getQuarantineByMark(Constant.vaccine);
				
				//for (Object ob:listItem) {
					//Object[] objs=(Object[])ob;
					//疫苗id,name
					//Integer itemId=(Integer)objs[0];
					//String itemName=(String)objs[1];
					Vaccine v = new Vaccine();
					//疫苗类型,此处通过Combobox获取.
					v.setQ_id(request.getParameter("vaccineType"));
					//是否采样标志
					//检疫编号dateid
					v.setVeterinarian(request.getParameter("vacci_veterinarian"));
					v.setProtector(request.getParameter("vacci_protector"));
					v.setRecorder(request.getParameter("vacci_recorder"));
					v.setRemark(request.getParameter("vacci_remark"));
					v.setPtype(Constant.export);
//					v.setNormal_id(Integer.valueOf(request
//							.getParameter("vaccine_exportid")));
					v.setNormal_id(exportService.getNextNormalId());
					vaccineService.save(v);
				//}
				
			}
			map.put("success", true);
			map.put("msg", "添加成功");
		}
		String strJson=JsonPluginsUtil.beanToJson(map);
		writeJson(strJson);
	}
	/**
	 * 后加
	 * @throws ParseException
	 */
	public void saveRecord() throws ParseException{
		Map<String, Object> map = new HashMap<String, Object>();
		if (monkeylist != null) {
			String[] remarks1 = remarks.split(",");
			String[] ypins1 = ypins.split(",");
			String[] ms1 = ms.split(",");
			String[] as1 = as.split(",");
			String[] bs1 = bs.split(",");
			String [] monkeylist1=monkeylist.split(",");
			if (monkeylist1 != null) {
				Normal normal = normalService.getById(Long.valueOf(orderid));
				for (int i = 0; i < monkeylist1.length; i++) {
					
					for (int j = 0; j < 3; j++) {
						Vaccine vaccine = new Vaccine();
						vaccine.setMonkeyid(monkeylist1[i]);
						if (j==0&&!"-".equals(ms1[i])) {
							//获取麻疹的ID.
							List<?> listquerantine=vaccineService.getVaccineIdByName("Measles");
							if(listquerantine.size()>0){
								Quarantine q=(Quarantine)listquerantine.get(0);
								vaccine.setQ_id(q.getId().toString());
							}
							//vaccine.setQ_id(81+"");//麻疹81
							vaccine.setYpin(ms1[i]);
						}
						if (j==1&&!"-".equals(ms1[i])) {
							//获取甲肝的ID.
							List<?> listquerantine=vaccineService.getVaccineIdByName("HepatitisA");
							if(listquerantine.size()>0){
								Quarantine q=(Quarantine)listquerantine.get(0);
								vaccine.setQ_id(q.getId().toString());
							}
							//vaccine.setQ_id(82+"");//甲肝82
							vaccine.setYpin(as1[i]);
						}
						if (j==2&&!"-".equals(ms1[i])) {
							//获取乙肝的ID.
							List<?> listquerantine=vaccineService.getVaccineIdByName("HepatitisB");
							if(listquerantine.size()>0){
								Quarantine q=(Quarantine)listquerantine.get(0);
								vaccine.setQ_id(q.getId().toString());
							}
							//vaccine.setQ_id(83+"");//乙肝83
							vaccine.setYpin(bs1[i]);
						}
						if (!"-".equals(remarks1[i])) {
							//vaccine.setYpin(ypins1[i]);
							vaccine.setRemark(remarks1[i]);
						}
						vaccine.setMonkeyid(monkeylist1[i]);
						vaccine.setNormal_id(Integer.valueOf(orderid));
						vaccine.setLastmodifytime(Timestamp.valueOf(DateUtil.dateToString(new Date(),"yyyy-MM-dd hh:mm:ss")));
						Employee user = (Employee) ActionContext.getContext().getSession().get("user");
						vaccine.setModified_by(Integer.valueOf(user.getId()+ ""));
						byte[] b={0};
						vaccine.setDeleted(b[0]);
						vaccine.setCdate(normal.getCheckdate());
						vaccineService.save(vaccine);
					}
					
				}
				//更新normal表.
				
				normal.setVaccine("√");
				//normal.setStatus("2");
				normalService.update(normal);
				map.put("success", true);
				map.put("msg", "添加成功");
			}
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getVaccinename() {
		return vaccinename;
	}

	public void setVaccinename(String vaccinename) {
		this.vaccinename = vaccinename;
	}

	public String getVac_cdate() {
		return vac_cdate;
	}

	public void setVac_cdate(String vac_cdate) {
		this.vac_cdate = vac_cdate;
	}

	public String getVac_ymlx() {
		return vac_ymlx;
	}

	public void setVac_ymlx(String vac_ymlx) {
		this.vac_ymlx = vac_ymlx;
	}

	public String getVac_veterinarian() {
		return vac_veterinarian;
	}

	public void setVac_veterinarian(String vac_veterinarian) {
		this.vac_veterinarian = vac_veterinarian;
	}

	public String getVac_protector() {
		return vac_protector;
	}

	public void setVac_protector(String vac_protector) {
		this.vac_protector = vac_protector;
	}

	public String getVac_recorder() {
		return vac_recorder;
	}

	public void setVac_recorder(String vac_recorder) {
		this.vac_recorder = vac_recorder;
	}

	public String getVac_remark() {
		return vac_remark;
	}

	public void setVac_remark(String vac_remark) {
		this.vac_remark = vac_remark;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<Map<String, Object>> getDataSourceList() {
		return dataSourceList;
	}

	public void setDataSourceList(List<Map<String, Object>> dataSourceList) {
		this.dataSourceList = dataSourceList;
	}

	public String getMonkeylist() {
		return monkeylist;
	}

	public void setMonkeylist(String monkeylist) {
		this.monkeylist = monkeylist;
	}

	public String getVaccinedate() {
		return vaccinedate;
	}

	public void setVaccinedate(String vaccinedate) {
		this.vaccinedate = vaccinedate;
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

	public String getYpins() {
		return ypins;
	}

	public void setYpins(String ypins) {
		this.ypins = ypins;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getMs() {
		return ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public String getAs() {
		return as;
	}

	public void setAs(String as) {
		this.as = as;
	}

	public String getBs() {
		return bs;
	}

	public void setBs(String bs) {
		this.bs = bs;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

}
