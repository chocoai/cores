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
import com.lanen.model.Tb;
import com.lanen.service.arp.ExportService;
import com.lanen.service.arp.NormalService;
import com.lanen.service.arp.QuarantineService;
import com.lanen.service.arp.TbService;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.lanen.util.RandomUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TbAction extends BaseAction<Tb> {

	/**
	 * tb
	 */
	private static final long serialVersionUID = -4649671033078667336L;
	private String page;
	private String rows;

	@Resource
	private TbService tbService;
	@Resource
	private QuarantineService quarantineService;
	@Resource
	private NormalService normalService;
	@Resource
	private ExportService exportService;
	
	private List<Map<String,Object>> dataSourceList;
	private Map<String,Object> paraMap;
	private String fileName;
	
	private String monkeylist;//检疫动物ID数组
	private String tbdate;
	
	private String orderid;//检疫ID.
	private String tb24s;
	private String tb48s;//
	private String tb72s;
	private String remarks;//备注.
	private String checkId;//检疫编号.
	
	public void addNormalTB() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getMonkeyid() != null) {
			Tb tb = new Tb();
			if(request.getParameter("tb_cdate")!=null&&!"".equals(request.getParameter("tb_cdate"))){
				tb.setCdate(DateUtil.stringToDate(request.getParameter("tb_cdate"), "yyyy-MM-dd"));
			}
			tb.setQ_id(request.getParameter("tb_qid"));//tb药剂
			tb.setDrugs_count(request.getParameter("tb_drugscount"));//剂量
			tb.setVeterinarian(request.getParameter("tb_veterinarian"));
			tb.setRecorder(request.getParameter("tb_recorder"));
			tb.setProtector(request.getParameter("tb_protector"));
			tb.setRemark(request.getParameter("tb_remark"));
			tb.setTb24(request.getParameter("tb24"));
			tb.setTb24v(request.getParameter("tb24v"));
			tb.setTb48(request.getParameter("tb48"));
			tb.setTb48v(request.getParameter("tb48v"));
			tb.setTb72(request.getParameter("tb72"));
			tb.setTb72v(request.getParameter("tb72v"));
			tb.setNormal_id(Integer.valueOf(request.getParameter("tb_normalid")));
			tb.setMonkeyid(model.getMonkeyid());
			tbService.save(tb);
			
			String tb_normalid=request.getParameter("tb_normalid");
			normalService.updateNormalById(tb_normalid,Constant.TB_NAME);
		}
		map.put("success", true);
		map.put("msg", "添加成功");
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void loadListByMonkeyId() {
		Map<String, Object> map = tbService.loadListByMonkeyId(rows, page,
				model.getMonkeyid(), Constant.normal);
		String str = JsonPluginsUtil.beanToJson(map, "yyyy-MM-dd");
		writeJson(str);
	}

	public void loadListTB(){
		List<Map<String,String>> listMap=quarantineService.getQuarantineName(Constant.tb);
		String str=JsonPluginsUtil.beanListToJson(listMap);
		writeJson(str);
	}
	/**
	 * TB检测记录报表--分页
	 * @return
	 */
	public String listTB(){
		return "tbRecord";
	}
	public void tbByJson(){
		Map map=tbService.getTB(rows, page,model.getMonkeyid(),model.getCdate());
		String jsonTB=JsonPluginsUtil.beanToJson(map);
		writeJson(jsonTB);
	}
	public String reportTB(){
		return "tbReport";
	}
	public String tbByReport(){
		URL logoImage=null;
		logoImage=this.getClass().getResource("logo.jpg");
		fileName="tbDetails"+DateUtil.dateToString(new Date(), "yyyyMMddsss");
		
		paraMap=new HashMap<String,Object>();
		paraMap.put("company", Constant.COMPANY_NAME);
		paraMap.put("logoImage", logoImage);
		//paraMap.put("title", Constant.TITLE_XCG_RECORD);
		paraMap.put("checkNumber", Constant.NUM_JB+RandomUtil.randomNum(5, 10));
		paraMap.put("animalType", Constant.animalType);//
		paraMap.put("checkId", checkId);
		//查询检疫时间.
		String checkDate=normalService.getCheckDateByTitle(checkId);
		paraMap.put("checkDate", checkDate);
		
		Map map=tbService.getTB(model.getMonkeyid(),tbdate,checkId);
		dataSourceList=(List<Map<String, Object>>) map.get("rows");
		return "tbByReport";
	}
	
	//出口检疫，批量添加
	public void addMonkeyList() {
		Map<String, Object> map = new HashMap<String, Object>();

		if (monkeylist != null && !"".equals(monkeylist)) {
			String[] s = monkeylist.split(",");
			for (int i = 0; i < s.length; i++) {
				Tb tb = new Tb();
				tb.setMonkeyid(s[i]);
				tb.setVeterinarian(request.getParameter("tb_veterinarian"));
				tb.setProtector(request.getParameter("tb_protector"));
				tb.setRecorder(request.getParameter("tb_recorder"));
				tb.setRemark(request.getParameter("tb_remark"));
				tb.setDeleted(Byte.valueOf(Constant.deleted_0.toString()));
				tb.setPtype(Constant.export);//出口检疫标志
				//药剂
				tb.setQ_id(request.getParameter("tbyj"));
				//药量
				tb.setDrugs_count(request.getParameter("tb_count"));
				tb.setTb24(request.getParameter("tb24"));
				tb.setTb24v(request.getParameter("tb24v"));
				tb.setTb48(request.getParameter("tb48"));
				tb.setTb48v(request.getParameter("tb48v"));
				tb.setTb72(request.getParameter("tb72"));
				tb.setTb72v(request.getParameter("tb72v"));
				//出口检疫也需同步normal_id.
				//sur.setNormal_id(Integer.valueOf(request.getParameter("tb_exportid")));
				tb.setNormal_id(exportService.getNextNormalId());
				tbService.save(tb);
				//此处没有更新normal表。
				
				map.put("success", true);
				map.put("msg", "添加成功");
			}
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
			String[] tb24s1 = tb24s.split(",");
			String[] tb48s1 = tb48s.split(",");
			String[] tb72s1 = tb72s.split(",");
			
			String [] monkeylist1=monkeylist.split(",");
			if (monkeylist1 != null) {
				for (int i = 0; i < monkeylist1.length; i++) {
					Normal normal=normalService.getById(Long.valueOf(orderid));
					Tb tb = new Tb();
					if (!"-".equals(tb24s1[i])) {
						tb.setTb24(tb24s1[i]);
					}
					if (!"-".equals(tb48s1[i])) {
						tb.setTb48(tb48s1[i]);
					}
					if (!"-".equals(tb72s1[i])) {
						tb.setTb72(tb72s1[i]);
					}
					tb.setMonkeyid(monkeylist1[i]);
					if (!"-".equals(remarks1[i])) {
						tb.setRemark(remarks1[i]);
					}
					tb.setNormal_id(Integer.valueOf(orderid));
					tb.setLastmodifytime(Timestamp.valueOf(DateUtil.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss")));
					Employee user=(Employee)ActionContext.getContext().getSession().get("user");
					tb.setModified_by(Integer.valueOf(user.getId()+""));
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String dataString = sdf.format(date);
					//date = sdf.parse(dataString);
					tb.setDeleted(Byte.valueOf("0"));
					tb.setCdate(normal.getCheckdate());
					tbService.save(tb);
					//更新normal表.
					
					normal.setTb("√");
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

	public String getTbdate() {
		return tbdate;
	}

	public void setTbdate(String tbdate) {
		this.tbdate = tbdate;
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

	public String getTb24s() {
		return tb24s;
	}

	public void setTb24s(String tb24s) {
		this.tb24s = tb24s;
	}

	public String getTb48s() {
		return tb48s;
	}

	public void setTb48s(String tb48s) {
		this.tb48s = tb48s;
	}

	public String getTb72s() {
		return tb72s;
	}

	public void setTb72s(String tb72s) {
		this.tb72s = tb72s;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
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
