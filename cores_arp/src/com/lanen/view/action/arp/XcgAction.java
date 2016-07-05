package com.lanen.view.action.arp;

import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
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
import com.lanen.model.Xcg;
import com.lanen.service.arp.NormalService;
import com.lanen.service.arp.XcgService;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.lanen.util.RandomUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class XcgAction extends BaseAction<Xcg> {

	/**
	 * xcg
	 */
	private static final long serialVersionUID = -5507310401777328490L;
	private String page;
	private String rows;

	@Resource
	private XcgService xcgService;
	@Resource
	private NormalService normalService;
	
	private String caiydate;
	private List<Map<String,Object>> dataSourceList;
	private Map<String,Object> paraMap;
	private String fileName;
	
	private String bhaos;//样本编号.
	private String wbcs;
	private String rbcs;
	private String hgbs;
	private String hcts;
	private String plts;
	private String mcvs;
	private String mchs;
	private String mchcs;
	private String lyms;
	private String mids;
	private String gras;
	private String orderid;//检疫ID.
	private String monkeylist;//检疫动物ID.
	private String checkId;//检疫编号。
	
	public void addNormalXCG() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getMonkeyid() != null) {
			Xcg x = new Xcg();
			x.setMonkeyid(model.getMonkeyid());
			String date=request.getParameter("xcg_cdate");
			if (!"".equals(date)&&date!=null) {
				x.setCdate(DateUtil.stringToDate(date, "yyyy-MM-dd"));
			}
			x.setWbc(request.getParameter("wbc"));
			x.setRbc(request.getParameter("rbc"));
			x.setHgb(request.getParameter("hgb"));
			x.setHct(request.getParameter("hct"));
			x.setPlt(request.getParameter("plt"));
			x.setMcv(request.getParameter("mcv"));
			x.setMch(request.getParameter("mch"));
			x.setMchc(request.getParameter("mchc"));
			x.setLym(request.getParameter("lym"));
			x.setMid(request.getParameter("mid"));
			x.setGra(request.getParameter("gra"));
			x.setVeterinarian(request.getParameter("xcg_veterinarian"));
			x.setRecorder(request.getParameter("xcg_recorder"));
			//保定人员，备注无.
			x.setNormal_id(Integer.valueOf(request.getParameter("xcg_normalid")));
			xcgService.save(x);
			
			String xcg_normalid=request.getParameter("xcg_normalid");
			normalService.updateNormalById(xcg_normalid,Constant.XCG_NAME);
		}
		map.put("success", true);
		map.put("msg", "添加成功");
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void loadListByMonkeyId() {
		Map<String, Object> map = xcgService.loadListByMonkeyId(rows, page,
				model.getMonkeyid(), Constant.normal);
		String str = JsonPluginsUtil.beanToJson(map);
		writeJson(str);
	}

	/**
	 * 血常规报表--采样日期.
	 * @return
	 */
	public String listXCG(){
		return "xcgRecord";
	}
	public void xcgByJson(){
		Map map=xcgService.getXCG(rows, page,model.getMonkeyid(),model.getCdate());
		String strJson=JsonPluginsUtil.beanToJson(map);
		writeJson(strJson);
	}
	public String reportXCG(){
		return "xcgReport";
	}
	public String xcgByReport(){
		URL logoImage=null;
		logoImage=this.getClass().getResource("logo.jpg");
		fileName="xcgDetails"+DateUtil.dateToString(new Date(), "yyyyMMddsss");
		
		paraMap=new HashMap<String,Object>();
		paraMap.put("company", Constant.COMPANY_NAME);
		paraMap.put("logoImage", logoImage);
		paraMap.put("title", Constant.TITLE_XCG_RECORD);
		paraMap.put("number", Constant.NUM_JF+RandomUtil.randomNum(5, 10));
		paraMap.put("animalType", Constant.animalType);//
		paraMap.put("checkId", checkId);
		//查询检疫时间.
		String checkDate=normalService.getCheckDateByTitle(checkId);
		paraMap.put("checkDate", checkDate);
		dataSourceList=xcgService.getXCGByDate(caiydate,checkId);
		return "xcgByReport";
	}
	public String listXCG1(){
		return "xcgRecord1";
	}
	public String reportXCG1(){
		return "xcgReport1";
	}
	
	/**
	 * 后加
	 * @throws ParseException
	 */
	public void saveRecord() throws ParseException{
		Map<String, Object> map = new HashMap<String, Object>();
		if (monkeylist != null) {
			String[] bhaos1 = bhaos.split(",");
			String[] wbcs1=wbcs.split(",");
			String[] rbcs1=rbcs.split(",");
			String[] hgbs1=hgbs.split(",");
			String[] hcts1=hcts.split(",");
			String[] plts1=plts.split(",");
			String[] mcvs1=mcvs.split(",");
			String[] mchs1=mchs.split(",");
			String[] mchcs1=mchcs.split(",");
			String[] lyms1=lyms.split(",");
			String[] mids1=mids.split(",");
			String[] gras1=gras.split(",");
			
			String [] monkeylist1=monkeylist.split(",");
			if (monkeylist1 != null) {
				for (int i = 0; i < monkeylist1.length; i++) {
					Normal normal = normalService.getById(Long.valueOf(orderid));
					Xcg xcg = new Xcg();
					xcg.setMonkeyid(monkeylist1[i]);
					if (!"-".equals(bhaos1[i])) {
						xcg.setBhao(bhaos1[i]);
					}
					if (!"-".equals(wbcs1[i])) {
						xcg.setWbc(wbcs1[i]);
					}
					if (!"-".equals(rbcs1[i])) {
						xcg.setRbc(rbcs1[i]);
					}
					if (!"-".equals(hgbs1[i])) {
						xcg.setHgb(hgbs1[i]);
					}
					if (!"-".equals(hcts1[i])) {
						xcg.setHct(hcts1[i]);
					}
					if (!"-".equals(plts1[i])) {
						xcg.setPlt(plts1[i]);
					}
					if (!"-".equals(mcvs1[i])) {
						xcg.setMcv(mcvs1[i]);
					}
					if (!"-".equals(mchs1[i])) {
						xcg.setMch(mchs1[i]);
					}
					if (!"-".equals(mchcs1[i])) {
						xcg.setMchc(mchcs1[i]);
					}
					if (!"-".equals(lyms1[i])) {
						xcg.setLym(lyms1[i]);
					}
					if (!"-".equals(mids1[i])) {
						xcg.setMid(mids1[i]);
					}
					if (!"-".equals(gras1[i])) {
						xcg.setGra(gras1[i]);
					}
					
					
					xcg.setNormal_id(Integer.valueOf(orderid));
					xcg.setLastmodifytime(Timestamp.valueOf(DateUtil.dateToString(new Date(),"yyyy-MM-dd hh:mm:ss")));
					Employee user = (Employee) ActionContext.getContext().getSession().get("user");
					xcg.setModified_by(Integer.valueOf(user.getId()+ ""));
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String dataString = sdf.format(date);
					xcg.setDeleted(Byte.valueOf("0"));
					xcg.setCdate(normal.getCheckdate());
					xcgService.save(xcg);
					
					//更新normal表.
					
					normal.setXcg("√");
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

	public String getCaiydate() {
		return caiydate;
	}

	public void setCaiydate(String caiydate) {
		this.caiydate = caiydate;
	}

	public List<Map<String, Object>> getDataSourceList() {
		return dataSourceList;
	}

	public void setDataSourceList(List<Map<String, Object>> dataSourceList) {
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

	public String getBhaos() {
		return bhaos;
	}

	public void setBhaos(String bhaos) {
		this.bhaos = bhaos;
	}

	public String getWbcs() {
		return wbcs;
	}

	public void setWbcs(String wbcs) {
		this.wbcs = wbcs;
	}

	public String getRbcs() {
		return rbcs;
	}

	public void setRbcs(String rbcs) {
		this.rbcs = rbcs;
	}

	public String getHgbs() {
		return hgbs;
	}

	public void setHgbs(String hgbs) {
		this.hgbs = hgbs;
	}

	public String getHcts() {
		return hcts;
	}

	public void setHcts(String hcts) {
		this.hcts = hcts;
	}

	public String getPlts() {
		return plts;
	}

	public void setPlts(String plts) {
		this.plts = plts;
	}

	public String getMcvs() {
		return mcvs;
	}

	public void setMcvs(String mcvs) {
		this.mcvs = mcvs;
	}

	public String getMchs() {
		return mchs;
	}

	public void setMchs(String mchs) {
		this.mchs = mchs;
	}

	public String getMchcs() {
		return mchcs;
	}

	public void setMchcs(String mchcs) {
		this.mchcs = mchcs;
	}

	public String getLyms() {
		return lyms;
	}

	public void setLyms(String lyms) {
		this.lyms = lyms;
	}

	public String getMids() {
		return mids;
	}

	public void setMids(String mids) {
		this.mids = mids;
	}

	public String getGras() {
		return gras;
	}

	public void setGras(String gras) {
		this.gras = gras;
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

	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

}
