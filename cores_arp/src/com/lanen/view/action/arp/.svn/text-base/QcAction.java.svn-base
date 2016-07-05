package com.lanen.view.action.arp;

import java.math.BigInteger;
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
import com.lanen.model.Qc;
import com.lanen.service.arp.NormalService;
import com.lanen.service.arp.QcService;
import com.lanen.service.arp.QuarantineService;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.lanen.util.RandomUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class QcAction extends BaseAction<Qc> {

	/**
	 * 常规检疫-病毒-报表
	 */
	private static final long serialVersionUID = -896536196629903425L;
	@Resource
	private QuarantineService quarantineService;

	@Resource
	private QcService qcService;

	@Resource
	private NormalService normalService;
	private String rows;
	private String page;
	private String virusname;
	private String sumlist;

	private String monkeyid;

	private String qcdate;
	private List<Map<String,Object>> dataSourceList;
	private Map<String,Object> paraMap;
	private String fileName;
	
	private String orderid;//检疫ID;
	private String monkeylist;//检疫动物ID数组.
	private String qcrqs;//驱虫日期数组.
	private String qcyps;//驱虫药品.
	private String qcyls;//药品用量数组.
	private String remarks;//备注.
	private String checkId;//检疫编号.
	
	/**
	 * 报表页
	 */
	public String listQC(){
		return "qcRecord";
	}
	public void qcByJson(){
		Map map=qcService.getQC(rows, page,model.getMonkeyid(),model.getCdate());
		String jsonStr=JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	public String reportQC(){
		return "qcReport";
	}
	//按分页打印,打印按搜素条件.
	public String qcByReport(){
		URL logoImage=null;
		logoImage=this.getClass().getResource("logo.jpg");
		fileName="qcDetails"+DateUtil.dateToString(new Date(), "yyyyMMddsss");
		
		paraMap=new HashMap<String,Object>();
		paraMap.put("company", Constant.COMPANY_NAME);
		paraMap.put("logoImage", logoImage);
		//paraMap.put("title", Constant.TITLE_XCG_RECORD);
		paraMap.put("checkNumber", Constant.NUM+RandomUtil.randomNum(5, 10));//检测单号.
		paraMap.put("checkId", checkId==null?"":checkId);//检疫编号.
		paraMap.put("animalType", Constant.animalType);//
		//查询检疫时间.
		String checkDate=normalService.getCheckDateByTitle(checkId);
		paraMap.put("checkDate", checkDate);
		
		Map map=qcService.getQC(model.getMonkeyid(),qcdate,checkId);
		dataSourceList=(List<Map<String, Object>>) map.get("rows");
		return "qcByReport";
	}

	/**
	 * 后加
	 * @return
	 */
	public void saveRecord(){
		Map<String, Object> map = new HashMap<String, Object>();
		if (monkeylist != null) {
			String[] remarks1 = remarks.split(",");
			String[] qcyps1 = qcyps.split(",");
			String[] qcrqs1 = qcrqs.split(",");
			String[] qcyls1 = qcyls.split(",");
			String [] monkeylist1=monkeylist.split(",");
			if (monkeylist1 != null) {
				for (int i = 0; i < monkeylist1.length; i++) {
					Normal normal=normalService.getById(Long.valueOf(orderid));
					Qc qc = new Qc();
					if (!"-".equals(qcrqs1[i])) {
						qc.setQcrq(qcrqs1[i]);
					}
					if (!"-".equals(qcyps1[i])) {
						qc.setQcyp(qcyps1[i]);
					}
					if (!"-".equals(qcyls1[i])) {
						qc.setQcyl(qcyls1[i]);
					}
					qc.setMonkeyid(monkeylist1[i]);
					if (!"-".equals(remarks1[i])) {
						qc.setRemark(remarks1[i]);
					}
					qc.setNormal_id(Integer.valueOf(orderid));
					qc.setLastmodifytime(Timestamp.valueOf(DateUtil.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss")));
					Employee user=(Employee)ActionContext.getContext().getSession().get("user");
					qc.setModified_by(user.getId());
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String dataString = sdf.format(date);
					BigInteger del=BigInteger.ZERO;
					qc.setDeleted(del);
					qc.setCdate(normal.getCheckdate());
					qcService.save(qc);
					//更新normal表.
					
					normal.setQc("√");
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

	public List<Map<String, Object>> getDataSourceList() {
		return dataSourceList;
	}

	public void setDataSourceList(List<Map<String, Object>> dataSourceList) {
		this.dataSourceList = dataSourceList;
	}
	public String getQcdate() {
		return qcdate;
	}
	public void setQcdate(String qcdate) {
		this.qcdate = qcdate;
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
	public String getMonkeylist() {
		return monkeylist;
	}
	public void setMonkeylist(String monkeylist) {
		this.monkeylist = monkeylist;
	}
	public String getQcrqs() {
		return qcrqs;
	}
	public void setQcrqs(String qcrqs) {
		this.qcrqs = qcrqs;
	}
	public String getQcyps() {
		return qcyps;
	}
	public void setQcyps(String qcyps) {
		this.qcyps = qcyps;
	}
	public String getQcyls() {
		return qcyls;
	}
	public void setQcyls(String qcyls) {
		this.qcyls = qcyls;
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
