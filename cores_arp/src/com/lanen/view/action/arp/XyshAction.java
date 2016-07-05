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
import com.lanen.model.Xysh;
import com.lanen.service.arp.NormalService;
import com.lanen.service.arp.XyshService;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.lanen.util.RandomUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class XyshAction extends BaseAction<Xysh> {

	/**
	 * 血液生化-常规检疫
	 */
	private static final long serialVersionUID = -5507310401777328490L;
	private String page;
	private String rows;

	@Resource
	private XyshService xyshService;
	@Resource
	private NormalService normalService;
	private String xyshDate;
	private String xyshdate;
	private List<Map<String,Object>> dataSourceList;
	private Map<String,Object> paraMap;
	private String fileName;
	
	private String monkeylist;//检疫动物ID列表.
	private String orderid;//检疫ID.
	private String bhaos;//样本编号
	private String chols;
	private String tgs;
	private String glus;
	private String creas;
	private String buns;
	private String tbils;
	private String ggts;
	private String albs;
	private String tps;
	private String alps;
	private String alts;
	private String asts;
	private String checkId;//检疫编号.
	private String ldhs;
	private String cks;
	private String nas;
	private String ks;
	private String cis;
	
	public void addNormalXYSH() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getMonkeyid() != null) {
			Xysh x = new Xysh();
			x.setMonkeyid(model.getMonkeyid());
			String date=request.getParameter("xysh_cdate");
			if (!"".equals(date)&&date!=null) {
				x.setCdate(DateUtil.stringToDate(date, "yyyy-MM-dd"));
			}
			x.setAst(request.getParameter("ast"));
			x.setAlt(request.getParameter("alt"));
			x.setAlp(request.getParameter("alp"));
			x.setTp(request.getParameter("tp"));
			x.setAlb(request.getParameter("alb"));
			x.setGgt(request.getParameter("ggt"));
			x.setTbil(request.getParameter("tbil"));
			x.setBun(request.getParameter("bun"));
			x.setCrea(request.getParameter("crea"));
			x.setGlu(request.getParameter("glu"));
			x.setTg(request.getParameter("tg"));
			x.setChol(request.getParameter("chol"));
			x.setLdh(request.getParameter("ldh"));
			x.setCk(request.getParameter("ck"));
			x.setNa(request.getParameter("na"));
			x.setK(request.getParameter("k"));
			x.setCi(request.getParameter("ci"));
			x.setVeterinarian(request.getParameter("xysh_veterinarian"));
			x.setNormal_id(Integer.valueOf(request.getParameter("xysh_normalid")));
			xyshService.save(x);
			
			String xyshNormalId=request.getParameter("xysh_normalid");
			normalService.updateNormalById(xyshNormalId,Constant.XYSH_NAME);
		}
		map.put("success", true);
		map.put("msg", "添加成功");
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void loadListByMonkeyId() {
		Map<String, Object> map = xyshService.loadListByMonkeyId(rows, page,
				model.getMonkeyid(), Constant.normal);
		String str = JsonPluginsUtil.beanToJson(map);
		writeJson(str);
	}

	/**
	 * 血液生化报表--采样日期
	 * @return
	 */
	public String listXYSH(){
		return "xyshRecord";
	}
	public void xyshByJson(){
		Map<String,Object> map=xyshService.getXYSH(rows, page,model.getMonkeyid(),model.getCdate());
		String strjson=JsonPluginsUtil.beanToJson(map);
		writeJson(strjson);
	}
	public String reportXYSH(){
		return "xyshReport";
	}
	public String xyshByReport(){
		paraMap=new HashMap<String,Object>();
		URL logoImage=null;
		paraMap.put("company", Constant.COMPANY_NAME);
		logoImage = this.getClass().getResource("logo.jpg");
		paraMap.put("logoImage", logoImage);
		paraMap.put("title", Constant.TITLE_XYSH_RECORD);
		paraMap.put("checkId", checkId);
		/*if(!"".equals(xyshDate)&&xyshDate!=null){
			paraMap.put("checkDate", xyshDate);
		}*/
		//查询检疫时间.
		String checkDate=normalService.getCheckDateByTitle(checkId);
		paraMap.put("checkDate", checkDate);
		paraMap.put("animalType", Constant.animalType);
		paraMap.put("number", Constant.NUM_JF+RandomUtil.randomNum(5, 10));
		fileName="XYSHRecord"+DateUtil.dateToString(new Date(), "yyyyMMddsss");
		dataSourceList=xyshService.getXYSHByDate(xyshDate,checkId);

		return "xyshByReport";
	}
	
	/**
	 * 后加
	 * @return
	 */
	public void saveRecord(){
		Map<String, Object> map = new HashMap<String, Object>();
		if (monkeylist != null) {
			String[] bhaos1 =  bhaos.split(",");
			String[] chols1= chols.split(",");
			 String [] tgs1=tgs.split(",");
			 String[] glus1=glus.split(",");
			 String [] creas1=creas.split(",");
			 String [] buns1=buns.split(",");
			 String [] tbils1=buns.split(",");
			 String [] ggts1=ggts.split(",");
			 String [] albs1=albs.split(",");
			 String [] tps1=tps.split(",");
			 String [] alps1=alps.split(",");
			 String [] alts1=alts.split(",");
			 String [] asts1=asts.split(",");
			String [] monkeylist1=monkeylist.split(",");
			String[] ldhs1=ldhs.split(",");
			String [] cks1=cks.split(",");
			String [] nas1=nas.split(",");
			String [] ks1=ks.split(",");
			String [] cis1=cis.split(",");
			if (monkeylist1 != null) {
					for (int i = 0; i < monkeylist1.length; i++) {
						Normal normal = normalService.getById(Long.valueOf(orderid));
						Xysh xysh = new Xysh();
						if (!"-".equals(bhaos1[i])) {
							xysh.setBhao(bhaos1[i]);
						}
						if (!"-".equals(chols1[i])) {
							xysh.setChol(chols1[i]);
						}
						if (!"-".equals(tgs1[i])) {
							xysh.setTg(tgs1[i]);
						}
						if (!"-".equals(glus1[i])) {
							xysh.setGlu(glus1[i]);
						}
						if (!"-".equals(creas1[i])) {
							xysh.setCrea(creas1[i]);
						}
						if (!"-".equals(buns1[i])) {
							xysh.setBun(buns1[i]);
						}
						if (!"-".equals(tbils1[i])) {
							xysh.setTbil(tbils1[i]);
						}
						if (!"-".equals(ggts1[i])) {
							xysh.setGgt(ggts1[i]);
						}
						if (!"-".equals(albs1[i])) {
							xysh.setAlb(albs1[i]);
						}
						if (!"-".equals(tps1[i])) {
							xysh.setTp(tps1[i]);
						}
						if (!"-".equals(alps1[i])) {
							xysh.setAlp(alps1[i]);
						}
						if (!"-".equals(alts1[i])) {
							xysh.setAlt(alts1[i]);
						}
						if (!"-".equals(asts1[i])) {
							xysh.setAst(asts1[i]);
						}
						if (!"-".equals(ldhs1[i])) {
							xysh.setLdh(ldhs1[i]);
						}
						if(!"".equals(cks1[i])){
							xysh.setCk(cks1[i]);
						}
						if(!"".equals(nas1[i])){
							xysh.setNa(nas1[i]);
						}
						if(!"".equals(ks1[i])){
							xysh.setK(ks1[i]);
						}
						if(!"".equals(cis1[i])){
							xysh.setCi(cis1[i]);
						}
						xysh.setMonkeyid(monkeylist1[i]);
						xysh.setNormal_id(Integer.valueOf(orderid));
						xysh.setLastmodifytime(Timestamp
								.valueOf(DateUtil.dateToString(new Date(),
										"yyyy-MM-dd hh:mm:ss")));
						Employee user = (Employee) ActionContext.getContext()
								.getSession().get("user");
						xysh.setModified_by(Integer.valueOf(user.getId()
								+ ""));
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						String dataString = sdf.format(date);
						//date = sdf.parse(dataString);
						xysh.setDeleted(Byte.valueOf("0"));
						xysh.setCdate(normal.getCheckdate());
						xyshService.save(xysh);
						//更新normal表.
						
						normal.setXysh("√");
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

	public String getXyshDate() {
		return xyshDate;
	}

	public void setXyshDate(String xyshDate) {
		this.xyshDate = xyshDate;
	}

	public List<Map<String, Object>> getDataSourceList() {
		return dataSourceList;
	}

	public void setDataSourceList(List<Map<String, Object>> dataSourceList) {
		this.dataSourceList = dataSourceList;
	}

	public String getXyshdate() {
		return xyshdate;
	}

	public void setXyshdate(String xyshdate) {
		this.xyshdate = xyshdate;
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

	public String getMonkeylist() {
		return monkeylist;
	}

	public void setMonkeylist(String monkeylist) {
		this.monkeylist = monkeylist;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getBhaos() {
		return bhaos;
	}

	public void setBhaos(String bhaos) {
		this.bhaos = bhaos;
	}

	public String getChols() {
		return chols;
	}

	public void setChols(String chols) {
		this.chols = chols;
	}

	public String getTgs() {
		return tgs;
	}

	public void setTgs(String tgs) {
		this.tgs = tgs;
	}

	public String getGlus() {
		return glus;
	}

	public void setGlus(String glus) {
		this.glus = glus;
	}

	public String getCreas() {
		return creas;
	}

	public void setCreas(String creas) {
		this.creas = creas;
	}

	public String getBuns() {
		return buns;
	}

	public void setBuns(String buns) {
		this.buns = buns;
	}

	public String getTbils() {
		return tbils;
	}

	public void setTbils(String tbils) {
		this.tbils = tbils;
	}

	public String getGgts() {
		return ggts;
	}

	public void setGgts(String ggts) {
		this.ggts = ggts;
	}

	public String getAlbs() {
		return albs;
	}

	public void setAlbs(String albs) {
		this.albs = albs;
	}

	public String getTps() {
		return tps;
	}

	public void setTps(String tps) {
		this.tps = tps;
	}

	public String getAlps() {
		return alps;
	}

	public void setAlps(String alps) {
		this.alps = alps;
	}

	public String getAlts() {
		return alts;
	}

	public void setAlts(String alts) {
		this.alts = alts;
	}

	public String getAsts() {
		return asts;
	}

	public void setAsts(String asts) {
		this.asts = asts;
	}

	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public String getLdhs() {
		return ldhs;
	}

	public void setLdhs(String ldhs) {
		this.ldhs = ldhs;
	}

	public String getCks() {
		return cks;
	}

	public void setCks(String cks) {
		this.cks = cks;
	}

	public String getNas() {
		return nas;
	}

	public void setNas(String nas) {
		this.nas = nas;
	}

	public String getKs() {
		return ks;
	}

	public void setKs(String ks) {
		this.ks = ks;
	}

	public String getCis() {
		return cis;
	}

	public void setCis(String cis) {
		this.cis = cis;
	}

}
