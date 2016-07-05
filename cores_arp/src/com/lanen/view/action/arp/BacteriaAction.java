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
import com.lanen.model.Bacteria;
import com.lanen.model.Employee;
import com.lanen.model.Normal;
import com.lanen.model.Qc;
import com.lanen.model.Quarantine;
import com.lanen.service.arp.BacteriaService;
import com.lanen.service.arp.ExportService;
import com.lanen.service.arp.NormalService;
import com.lanen.service.arp.QuarantineService;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.lanen.util.RandomUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class BacteriaAction extends BaseAction<Bacteria> {

	/**
	 * 防疫配置，细菌
	 */
	private static final long serialVersionUID = -899736196629903425L;
	@Resource
	private QuarantineService quarantineService;
	@Resource
	private BacteriaService bacteriaService;
	@Resource
	private NormalService normalService;
	@Resource
	private ExportService exportService;
	private String rows;
	private String page;
	private String bacterianame;;

	private String cdate;
	private String monkeyid;

	private String bacteriadate;
	private List<Map<String, Object>> dataSourceList;
	private Map<String,Object> paraMap;
	private String fileName;
	private String monkeylist;//检疫动物ID数组.
	
	private String orderid;//检疫ID.
	private String ypids;//样本ID数组.
	private String salms;//沙门氏菌
	private String shigs;//志贺氏菌
	private String yerss;//耶尔森氏菌数组
	private String remarks;//
	private String checkId;//检疫编号.
	
	public void loadTable() {
		String Virusvalue = quarantineService.getQuarantineAndMBy(
				Constant.bacteria, Constant.bacteriamethod);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tables", Virusvalue);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);

	}

	public void addNormalBacteria() {
		if (model.getMonkeyid() != null && !"".equals(model.getMonkeyid())) {
			Map<String, Object> map = new HashMap<String, Object>();

			List<Quarantine> bacterialist = quarantineService
					.getQuarantineByMark(Constant.bacteria);

			if (bacterialist != null && bacterialist.size() > 0) {

				for (Object obj : bacterialist) {
					Object[] ob = (Object[]) obj;
					Quarantine tp = new Quarantine();
					tp.setId(Long.valueOf(ob[0] + ""));
					tp.setName((String) ob[1]);

					if (tp != null) {
						Bacteria t = new Bacteria();
						t.setMonkeyid(model.getMonkeyid());
						t.setProtector(request.getParameter("bac_protector"));
						t.setRecorder(request.getParameter("bac_recorder"));
						t.setVeterinarian(request.getParameter("bac_veterinarian"));
						t.setRemark(request.getParameter("bac_remark"));
						String checkdate=request.getParameter("bac_cdate");
						if (checkdate!=null&&!"".equals(checkdate)) {
							t.setCdate(DateUtil.stringToDate(checkdate, "yyyy-MM-dd"));
						}
						t.setDeleted(Constant.deleted_0.byteValue());
						
						//细菌检疫项目
						t.setQ_id(Integer.valueOf(tp.getId().toString()));

						String resoult = request.getParameter(tp.getId()+ "_result");
						t.setResoult(Byte.parseByte(resoult));
						String qconfig_id = request.getParameter(tp.getId()+ "_select");
						t.setQconfig_id(Integer.valueOf(qconfig_id));

						String drugs_count = request.getParameter(tp.getId()+ "_drugs_count");
						t.setDrugs_count(drugs_count);
						String drugs_name = request.getParameter(tp.getId()+ "_drugs_name");
						t.setDrugs_name(drugs_name);

						t.setPtype(Constant.normal);
						try {
							bacteriaService.save(t);
							Normal no=normalService.getById(Long.valueOf(request.getParameter("bacteria_normalid")));
							no.setLastmodifytime(Timestamp.valueOf(DateUtil.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss")));
							Employee user=(Employee)ActionContext.getContext().getSession().get("user");
							no.setModified_by(Integer.parseInt(user.getId()+""));
							no.setBacteria(Constant.NORMAL_FLAG);
							normalService.update(no);
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

	public void loadListByMonkeyIdAndCdate() {
		Map<String, Object> map = bacteriaService.loadListByMonkeyIdAndCdate(
				page, rows, "");
		String jsonStr = JsonPluginsUtil.beanToJson(map, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	public void loadListItem() {
		Map<String, Object> map = bacteriaService.loadListItem(monkeyid, cdate);
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 细菌检测报表，分页
	 * @return
	 */
	public String listBacteria(){
		return "bacteriaRecord";
	}
	public void bacteriaByJson(){
		Map map=bacteriaService.getBacteria(rows, page,model.getMonkeyid(),model.getCdate());
		String jsonStr=JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
		
	}
	public String reportBacteria(){
		return "bacteriaReport";
	}
	public String bacteriaByReport(){
		URL logoImage=null;
		logoImage=this.getClass().getResource("logo.jpg");
		fileName="bacteriaDetails"+DateUtil.dateToString(new Date(), "yyyyMMddsss");
		
		paraMap=new HashMap<String,Object>();
		paraMap.put("company", Constant.COMPANY_NAME);
		paraMap.put("logoImage", logoImage);
		//paraMap.put("title", Constant.TITLE_XCG_RECORD);
		paraMap.put("checkNumber", Constant.NUM_JD+RandomUtil.randomNum(5, 10));
		paraMap.put("animalType", Constant.animalType);//
		paraMap.put("checkId", checkId);
		//查询检疫时间.
		String checkDate=normalService.getCheckDateByTitle(checkId);
		paraMap.put("checkDate", checkDate);
		
		Map map=bacteriaService.getBacteria(model.getMonkeyid(),bacteriadate,checkId);
		dataSourceList=(List<Map<String, Object>>) map.get("rows");
		return "bacteriaByReport";
	}
	// 批量添加,出口检疫添加
	public void addMonkeyList() {
		Map<String, Object> map = new HashMap<String, Object>();

		if (monkeylist != null && !"".equals(monkeylist)) {
			String[] s = monkeylist.split(",");
			for (int i = 0; i < s.length; i++) {
				//列出细菌检疫项目
				List<?> listitem=quarantineService.getQuarantineByMark(Constant.bacteria);
				
				for (int j = 0; j < listitem.size(); j++) {
					Object[]objs=(Object[])listitem.get(i);
					//细菌检测项目id
					Integer itemid=(Integer)objs[0];
					//细菌检测项目名
					String itemname=(String)objs[1];
					
					//暂时设定出口检疫项目为以下四种.
					if ("Hepatitis A".equals(itemname)||"Hepatitis B".equals(itemname)
							||"Monkey Pox".equals(itemname)||"Rabies".equals(itemname)) {
						Bacteria bac = new Bacteria();
						bac.setMonkeyid(s[i]);
						bac.setVeterinarian(request
								.getParameter("bacte_veterinarian"));
						bac.setProtector(request
								.getParameter("bacte_protector"));
						bac.setRecorder(request.getParameter("bacte_recorder"));
						bac.setRemark(request.getParameter("bacte_remark"));
						bac.setDeleted(Byte.valueOf(Constant.deleted_0
								.toString()));
						bac.setPtype(Constant.export);//出口检疫标志
						//出口检疫也需同步normal_id.
						//bac.setNormal_id(Integer.valueOf(request.getParameter("bacteria_exportid")));
						bac.setNormal_id(exportService.getNextNormalId());
						//下面开始设置新增检疫项目信息q_id
						bac.setQ_id(itemid);
						if("Hepatitis A".equals(itemname)){
							bac.setResoult((Byte.parseByte(request.getParameter("HepatitisA"))));//阴性/阳性
							bac.setDrugs_name(request.getParameter("HepatitisA_drung_name"));
							bac.setDrugs_count(request.getParameter("HepatitisA_drung_count"));
							//检测方法qconfig_id
							bac.setQconfig_id(Integer.parseInt(request.getParameter("HepatitisA_qconfig_id")));
						}
						if("Hepatitis B".equals(itemname)){
							bac.setResoult((Byte.parseByte(request.getParameter("HepatitisB"))));//阴性/阳性
							bac.setDrugs_name(request.getParameter("HepatitisB_drung_name"));
							bac.setDrugs_count(request.getParameter("HepatitisB_drung_count"));
							bac.setQconfig_id(Integer.parseInt(request.getParameter("HepatitisB_qconfig_id")));
						}
						if("Monkey Pox".equals(itemname)){
							bac.setResoult((Byte.parseByte(request.getParameter("MonkeyPox"))));//阴性/阳性
							bac.setDrugs_name(request.getParameter("MonkeyPox_drung_name"));
							bac.setDrugs_count(request.getParameter("MonkeyPox_drung_count"));
							bac.setQconfig_id(Integer.parseInt(request.getParameter("MonkeyPox_qconfig_id")));
						}
						if("Rabies".equals(itemname)){
							bac.setResoult((Byte.parseByte(request.getParameter("Rabies"))));//阴性/阳性
							bac.setDrugs_name(request.getParameter("Rabies_drung_name"));
							bac.setDrugs_count(request.getParameter("Rabies_drung_count"));
							bac.setQconfig_id(Integer.parseInt(request.getParameter("Rabies_qconfig_id")));
						}
						bacteriaService.save(bac);
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
	
	/**
	 * 后加
	 * @return
	 */
	public void saveRecord(){
		Map<String, Object> map = new HashMap<String, Object>();
		if (monkeylist != null) {
			String[] remarks1 = remarks.split(",");
			String[] salms1 = salms.split(",");
			String[] shigs1 = shigs.split(",");
			String[] yerss1 = yerss.split(",");
			String[] ypids1 = ypids.split(",");
			String [] monkeylist1=monkeylist.split(",");
			if (monkeylist1 != null) {
				for (int i = 0; i < monkeylist1.length; i++) {
					Normal normal=normalService.getById(Long.valueOf(orderid));
					Bacteria bacteria = new Bacteria();
					if (!"-".equals(salms1[i])) {
						bacteria.setSalm(salms1[i]);
					}
					if (!"-".equals(shigs1[i])) {
						bacteria.setShig(shigs1[i]);
					}
					if (!"-".equals(yerss1[i])) {
						bacteria.setYers(yerss1[i]);
					}
					bacteria.setMonkeyid(monkeylist1[i]);
					if (!"-".equals(ypids1[i])) {
						bacteria.setYpid(ypids1[i]);
					}
					if (!"-".equals(remarks1[i])) {
						bacteria.setRemark(remarks1[i]);
					}
					bacteria.setNormal_id(Integer.valueOf(orderid));
					bacteria.setLastmodifytime(Timestamp.valueOf(DateUtil.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss")));
					Employee user=(Employee)ActionContext.getContext().getSession().get("user");
					bacteria.setModified_by(Integer.valueOf(user.getId()+""));
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String dataString = sdf.format(date);
					//date = sdf.parse(dataString);
					Byte b[]={0};
					bacteria.setDeleted(b[0]);
					bacteria.setCdate(normal.getCheckdate());
					bacteriaService.save(bacteria);
					//更新normal表.
					
					normal.setBacteria("√");
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

	public String getBacterianame() {
		return bacterianame;
	}

	public void setBacterianame(String bacterianame) {
		this.bacterianame = bacterianame;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getMonkeyid() {
		return monkeyid;
	}

	public void setMonkeyid(String monkeyid) {
		this.monkeyid = monkeyid;
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

	public String getBacteriadate() {
		return bacteriadate;
	}

	public void setBacteriadate(String bacteriadate) {
		this.bacteriadate = bacteriadate;
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

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getYpids() {
		return ypids;
	}

	public void setYpids(String ypids) {
		this.ypids = ypids;
	}

	public String getSalms() {
		return salms;
	}

	public void setSalms(String salms) {
		this.salms = salms;
	}

	public String getShigs() {
		return shigs;
	}

	public void setShigs(String shigs) {
		this.shigs = shigs;
	}

	public String getYerss() {
		return yerss;
	}

	public void setYerss(String yerss) {
		this.yerss = yerss;
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
