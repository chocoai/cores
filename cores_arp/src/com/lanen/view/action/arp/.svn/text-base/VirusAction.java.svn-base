package com.lanen.view.action.arp;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import com.lanen.model.Virus;
import com.lanen.service.arp.ExportService;
import com.lanen.service.arp.NormalService;
import com.lanen.service.arp.QuarantineService;
import com.lanen.service.arp.VirusService;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.lanen.util.RandomUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class VirusAction extends BaseAction<Virus> {

	/**
	 * 常规检疫-病毒-报表
	 */
	private static final long serialVersionUID = -896536196629903425L;
	@Resource
	private QuarantineService quarantineService;

	@Resource
	private VirusService virusService;

	@Resource
	private NormalService normalService;
	@Resource
	private ExportService exportService;
	private String rows;
	private String page;
	private String virusname;
	private String sumlist;

	private String monkeyid;

	private String cdate;
	private String virusdate;
	private List<Map<String,Object>> dataSourceList;
	private Map<String,Object> paraMap;
	private String fileName;
	
	private String remarks;//备注数组.
	private String bvs;//
	private String srvs;//
	private String sivs;//
	private String filos;//
	private String stlvs;//
	private String xueqs;//血清号数组.
	private String orderid;//检疫ID。
	private String monkeylist;//检疫动物ID数组.
	private String checkId;//检疫编号.
	
	public String list() {
		return "virusList";
	}

	public void loadList() {
		Map<String, Object> map = quarantineService.getQuarantine(page, rows,
				virusname, "QuarantineTypeVirus");
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	public void loadTable() {
		String Virusvalue = quarantineService.getQuarantineAndMBy(
				Constant.virus, Constant.virusmethod);
		request.setAttribute("Virusvalue1", Virusvalue);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tables", Virusvalue);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);

	}

	// 常规检疫新增

	/*
	 * public void add(){ Map<String,Object> map=new HashMap<String,Object>();
	 * if(model.getMonkeyid()!=null&&!"".equals(model.getMonkeyid())){ String []
	 * str=sumlist.split(","); int c=str.length/4;
	 * 
	 * Enumeration<String> v2 = request.getParameterNames(); for (Enumeration e
	 * = v2 ; e.hasMoreElements() ;) { String name = (String)e.nextElement();
	 * System.out.println(name); request.getParameter(name); }
	 * 
	 * 
	 * for(int i=0;i<c;i++){ Virus v=new Virus();
	 * v.setResoult(Integer.valueOf(str[i]));
	 * v.setQconfig_id(Integer.valueOf(str[i+2])); v.setDrugs_name(str[i+4]);
	 * v.setDrugs_count(str[i+6]);
	 * 
	 * v.setMonkeyid(model.getMonkeyid()); virusService.save(v);
	 * 
	 * } map.put("success", true); map.put("msg", "添加成功");
	 * 
	 * String jsonStr = JsonPluginsUtil.beanToJson(map); writeJson(jsonStr); } }
	 */
	public void add() {
		if (model.getMonkeyid() != null && !"".equals(model.getMonkeyid())) {
			Map<String, Object> map = new HashMap<String, Object>();

			List<Quarantine> viruslist = quarantineService
					.getQuarantineByMark(Constant.virus);

			if (viruslist != null && viruslist.size() > 0) {

				// Quarantine tp = null;
				for (Object obj : viruslist) {
					Object[] ob = (Object[]) obj;
					Quarantine tp = new Quarantine();
					tp.setId(Long.valueOf(ob[0] + ""));
					tp.setName((String) ob[1]);

					if (tp != null) {
						Virus t = new Virus();
						t.setMonkeyid(request.getParameter("vir_monkeyid"));
						t.setProtector(request.getParameter("vir_protector"));
						t.setRecorder(request.getParameter("vir_recorder"));
						t.setVeterinarian(request.getParameter("vir_veterinarian"));
						t.setRemark(request.getParameter("vir_remark"));
						String checkdate=request.getParameter("vir_cdate");
						if (!"".equals(checkdate)&&checkdate!=null) {
							t.setCdate(DateUtil.stringToDate(checkdate, "yyyy-MM-dd"));
						}
						t.setDeleted(Constant.deleted_0);
						t.setXueq(request.getParameter("xueq"));
						t.setQ_id((Integer.valueOf(tp.getId() + "")));//病毒检测项目
						/*
						 * t.setMisc(misc); t.setMonkeyid(ml[i]);
						 * t.setNormal_id(nid); t.setProtector(protector);
						 * t.setRecorder(recorder);
						 * t.setVeterinarian(veterinarian); t.setRemark(remark);
						 * t.setPtype(ptype); t.setCdate(cdate);
						 * t.setDateid(hd); // 血清号的处理 if(xueqall!=null &&
						 * i<xueqall.length){ String xueid = null; try{ xueid =
						 * xueqall[i]; t.setXueq(xueid); }catch(Exception e){
						 * 
						 * } }
						 */
						String drugs_count = request.getParameter(tp.getId()+ "_drugs_count");
						t.setDrugs_count(drugs_count);
						String drugs_name = request.getParameter(tp.getId()+ "_drugs_name");
						t.setDrugs_name(drugs_name);
						String resoult = request.getParameter(tp.getId()+ "_result");
						t.setResoult((Integer.valueOf(resoult)));
						String qconfig_id = request.getParameter(tp.getId()+ "_select");
						t.setQconfig_id((Integer.valueOf(qconfig_id)));

						//常规检疫id更新进parasite表normalid
						t.setNormal_id(Integer.valueOf(request.getParameter("virus_normalid")));
						try {
							virusService.save(t);
							Normal normal=normalService.getById(Long.valueOf(request.getParameter("virus_normalid")));
							normal.setVirus(Constant.NORMAL_FLAG);
							Employee user=(Employee)ActionContext.getContext().getSession().get("user");
							normal.setModified_by(Integer.valueOf(user.getId()+""));
							normal.setLastmodifytime(Timestamp.valueOf(DateUtil.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss")));
							normalService.update(normal);
						} catch (Exception es) {
							es.printStackTrace();
						}
					}
				}

			}
			map.put("success", true);
			map.put("msg", "添加成功");

			String jsonStr = JsonPluginsUtil.beanToJson(map);
			writeJson(jsonStr);
		}
	}

	/**
	 * 报表页
	 */
	public String listVirus(){
		return "virusRecord";
	}
	public void virusByJson(){
		Map map=virusService.getVirus(rows, page,model.getMonkeyid(),model.getCdate());
		String jsonStr=JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	public String reportVirus(){
		return "virusReport";
	}
	//按打印
	public String virusByReport(){
		URL logoImage=null;
		logoImage=this.getClass().getResource("logo.jpg");
		fileName="virusDetails"+DateUtil.dateToString(new Date(), "yyyyMMddsss");
		
		paraMap=new HashMap<String,Object>();
		paraMap.put("company", Constant.COMPANY_NAME);
		paraMap.put("logoImage", logoImage);
		//paraMap.put("title", Constant.TITLE_XCG_RECORD);
		paraMap.put("checkNumber", Constant.NUM_JA+RandomUtil.randomNum(5, 10));
		paraMap.put("animalType", Constant.animalType);//
		paraMap.put("checkId", checkId);
		//查询检疫时间.
		String checkDate=normalService.getCheckDateByTitle(checkId);
		paraMap.put("checkDate", checkDate);
		
		Map map=virusService.getVirus(model.getMonkeyid(),virusdate,checkId);
		dataSourceList=(List<Map<String, Object>>) map.get("rows");
		return "virusByReport";
	}
	//出口检疫，批量添加,
	//同寄生虫parasiteAction
	public void addMonkeyList() {
		Map<String, Object> map = new HashMap<String, Object>();
		String monkeylist=request.getParameter("virus_monkeylist");
		if (monkeylist != null && !"".equals(monkeylist)) {
			String[] s = monkeylist.split(",");
			for (int i = 0; i < s.length; i++) {
				List listItem=quarantineService.getQuarantineByMark(Constant.virus);
				for (int j = 0; j < listItem.size(); j++) {
					Object[] objs=(Object[])listItem.get(j);
					Integer itemId=(Integer)objs[0];
					String itemName=(String)objs[1];
					if ("B-V".equals(itemName) ||"STLV".equals(itemName)||"SRV".equals(itemName)||
							"SIV".equals(itemName)||"FILO".equals(itemName)) {
						Virus vir = new Virus();
						vir.setQ_id(itemId);//检疫项目对应id.
						vir.setXueq(request.getParameter("xueq"));
						vir.setMonkeyid(s[i]);
						vir.setVeterinarian(request.getParameter("virus_veterinarian"));
						vir.setProtector(request.getParameter("virus_protector"));
						vir.setRecorder(request.getParameter("virus_recorder"));
						vir.setRemark(request.getParameter("virus_remark"));
						vir.setDeleted(Constant.deleted_0);
						vir.setPtype(Constant.export);//出口检疫标志
						/*String virusexportid = request.getParameter("virus_exportid");
						if (!"".equals(virusexportid) && virusexportid != null) {
							//出口检疫也需同步normal_id.
							vir.setNormal_id(Integer.valueOf(virusexportid));
						}*/
						vir.setNormal_id(exportService.getNextNormalId());
						//下面开始添加项目
						if("B-V".equals(itemName)){
							vir.setBv("bv");
							
							if (request.getParameter("bv")!=null&&!"".equals(request.getParameter("bv"))) {
								//阴性，阳性
								vir.setResoult(Integer.parseInt(request.getParameter("bv")));
							}
							//检测方法
							//vir.setQconfig_id(qconfig_id);
							//药品名
							vir.setDrugs_name(request.getParameter("bv_drugs_name"));
							//用药量
							vir.setDrugs_count(request.getParameter("bv_drugs_count"));
							
						}
						if("STLV".equals(itemName)){
							vir.setStlv("stlv");
							
							if (request.getParameter("stlv")!=null&&!"".equals(request.getParameter("stlv"))) {
								//阴性，阳性
								vir.setResoult(Integer.parseInt(request.getParameter("stlv")));
							}
							//检测方法
							//vir.setQconfig_id(qconfig_id);
							//药品名
							vir.setDrugs_name(request.getParameter("stlv_drugs_name"));
							//用药量
							vir.setDrugs_count(request.getParameter("stlv_drugs_count"));
							
						}
						if("SRV".equals(itemName)){
							vir.setSrv("srv");
							
							if (request.getParameter("srv")!=null&&!"".equals(request.getParameter("srv"))) {
								//阴性，阳性
								vir.setResoult(Integer.parseInt(request.getParameter("srv")));
							}
							//检测方法
							//vir.setQconfig_id(qconfig_id);
							//药品名
							vir.setDrugs_name(request.getParameter("srv_drugs_name"));
							//用药量
							vir.setDrugs_count(request.getParameter("srv_drugs_count"));
							
						}
						if("SIV".equals(itemName)){
							vir.setSiv("siv");
							
							if (request.getParameter("siv")!=null&&!"".equals(request.getParameter("siv"))) {
								//阴性，阳性
								vir.setResoult(Integer.parseInt(request.getParameter("siv")));
							}
							//检测方法
							//vir.setQconfig_id(qconfig_id);
							//药品名
							vir.setDrugs_name(request.getParameter("siv_drugs_name"));
							//用药量
							vir.setDrugs_count(request.getParameter("siv_drugs_count"));
							
						}
						if("FILO".equals(itemName)){
							vir.setFilo("filo");
							
							if (request.getParameter("filo")!=null&&!"".equals(request.getParameter("filo"))) {
								//阴性，阳性
								vir.setResoult(Integer.parseInt(request.getParameter("filo")));
							}
							//检测方法
							//vir.setQconfig_id(qconfig_id);
							//药品名
							vir.setDrugs_name(request.getParameter("filo_drugs_name"));
							//用药量
							vir.setDrugs_count(request.getParameter("filo_drugs_count"));
							
						}
						virusService.save(vir);
						//此处没有更新normal表。
					}
				}
			}
			map.put("success", true);
			map.put("msg", "添加成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	public void loadListByMonkeyIdAndCdate() {
		Map<String, Object> map = virusService.loadListByMonkeyIdAndCdate(page,
				rows, "");
		String jsonStr = JsonPluginsUtil.beanToJson(map, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	public void loadListItem() {
		Map<String, Object> map = virusService.loadListItem(monkeyid, cdate);
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 后加
	 * @return
	 */
	public void saveRecord(){
		Map<String, Object> map = new HashMap<String, Object>();
		if (monkeylist != null) {
			String[] remarks1 = remarks.split(",");
			String[] bvs1 = bvs.split(",");
			String[] stlvs1 = stlvs.split(",");
			String[] srvs1 = srvs.split(",");
			String[] sivs1 = sivs.split(",");
			String[] filos1 =filos.split(",");
			String[] xueqs1 =xueqs.split(",");
			String [] monkeylist1=monkeylist.split(",");
			if (monkeylist1 != null) {
				for (int i = 0; i < monkeylist1.length; i++) {
					Normal normal=normalService.getById(Long.valueOf(orderid));
					Virus virus = new Virus();
					if (!"-".equals(bvs1[i])) {
						virus.setBv(bvs1[i]);
					}
					if (!"-".equals(stlvs1[i])) {
						virus.setStlv(stlvs1[i]);
					}
					if (!"-".equals(srvs1[i])) {
						virus.setSrv(srvs1[i]);
					}
					if (!"-".equals(sivs1[i])) {
						virus.setSiv(sivs1[i]);
					}
					if (!"-".equals(filos1[i])) {
						virus.setFilo(filos1[i]);
					}
					if (!"-".equals(xueqs1[i])) {
						virus.setXueq(xueqs1[i]);
					}
					virus.setMonkeyid(monkeylist1[i]);
					if (!"-".equals(remarks1[i])) {
						virus.setRemark(remarks1[i]);
					}
					virus.setNormal_id(Integer.valueOf(orderid));
					virus.setLastmodifytime(Timestamp.valueOf(DateUtil.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss")));
					Employee user=(Employee)ActionContext.getContext().getSession().get("user");
					virus.setModified_by(Integer.valueOf(user.getId()+""));
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String dataString = sdf.format(date);
					virus.setDeleted(0);
					virus.setCdate(normal.getCheckdate());
					virusService.save(virus);
					//更新normal表.
					
					normal.setVirus("√");
					//normal.setStatus("2");
		            normalService.update(normal);
					map.put("success", true);
					map.put("msg", "添加成功");
					map.put("inventoryDate", dataString);
				}
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

	public String getVirusname() {
		return virusname;
	}

	public void setVirusname(String virusname) {
		this.virusname = virusname;
	}

	public String getMonkeyid() {
		return monkeyid;
	}

	public void setMonkeyid(String monkeyid) {
		this.monkeyid = monkeyid;
	}

	public String getSumlist() {
		return sumlist;
	}

	public void setSumlist(String sumlist) {
		this.sumlist = sumlist;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public List<Map<String, Object>> getDataSourceList() {
		return dataSourceList;
	}

	public void setDataSourceList(List<Map<String, Object>> dataSourceList) {
		this.dataSourceList = dataSourceList;
	}

	public String getVirusdate() {
		return virusdate;
	}

	public void setVirusdate(String virusdate) {
		this.virusdate = virusdate;
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

	public String getBvs() {
		return bvs;
	}

	public void setBvs(String bvs) {
		this.bvs = bvs;
	}

	public String getSrvs() {
		return srvs;
	}

	public void setSrvs(String srvs) {
		this.srvs = srvs;
	}

	public String getSivs() {
		return sivs;
	}

	public void setSivs(String sivs) {
		this.sivs = sivs;
	}

	public String getFilos() {
		return filos;
	}

	public void setFilos(String filos) {
		this.filos = filos;
	}

	public String getStlvs() {
		return stlvs;
	}

	public void setStlvs(String stlvs) {
		this.stlvs = stlvs;
	}

	public String getXueqs() {
		return xueqs;
	}

	public void setXueqs(String xueqs) {
		this.xueqs = xueqs;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getMonkeylist() {
		return monkeylist;
	}

	public void setMonkeylist(String monkeylist) {
		this.monkeylist = monkeylist;
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
