package com.lanen.view.action.arp;

import java.net.URL;
import java.sql.Timestamp;
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
import com.lanen.model.Parasite;
import com.lanen.model.Quarantine;
import com.lanen.service.arp.ExportService;
import com.lanen.service.arp.NormalService;
import com.lanen.service.arp.ParasiteService;
import com.lanen.service.arp.QuarantineService;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.lanen.util.RandomUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ParasiteAction extends BaseAction<Parasite> {

	/**
	 * 常规检疫，寄生虫
	 */
	private static final long serialVersionUID = -896536196629903425L;
	@Resource
	private QuarantineService quarantineService;

	@Resource
	private ParasiteService parasiteService;

	@Resource
	private NormalService normalService;
	@Resource
	private ExportService exportService;
	
	private String rows;
	private String page;

	private String monkeyid;
	private String cdate;

	private String parasitedate;
	private List<Map<String,Object>> dataSourceList;
	private Map<String,Object> paraMap;
	private String fileName;
	private String monkeylist;
	
	private String ambs;//溶组织内阿米
	private String rcs;//蠕虫
	private String bmcs;//鞭毛虫
	private String twjscs;//体外寄生虫
	private String bhaos;//样本编号
	private String orderid;//检疫ID.
	private String remarks;//备注.
	private String checkId;//检疫编号.
	
	public void loadTable() {
		String Virusvalue = quarantineService.getQuarantineAndMBy(
				Constant.parasite, Constant.parasitemethod);
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
		Employee user=(Employee)ActionContext.getContext().getSession().get("user");
		if (model.getMonkeyid() != null && !"".equals(model.getMonkeyid())) {
			Map<String, Object> map = new HashMap<String, Object>();

			List<Quarantine> parasitelist = quarantineService
					.getQuarantineByMark(Constant.parasite);

			if (parasitelist != null && parasitelist.size() > 0) {

				for (Object obj : parasitelist) {
					Object[] ob = (Object[]) obj;
					Quarantine tp = new Quarantine();
					tp.setId(Long.valueOf(ob[0] + ""));
					tp.setName((String) ob[1]);

					if (tp != null) {
						Parasite t = new Parasite();
						String yb_id=request.getParameter("yb_id");
						if (yb_id!=null&&!"".equals(yb_id)) {
							//样品
							t.setYb_id(Integer.parseInt(yb_id));
						}
						//采样日期
						if (!"".equals(request.getParameter("getybdate"))&&request.getParameter("getybdate")!=null) {
							t.setGetybdate(DateUtil.stringToDate(request.getParameter("getybdate"), "yyyy-MM-dd"));
						}
						t.setMonkeyid(model.getMonkeyid());
						t.setProtector(request.getParameter("par_protector"));
						t.setRecorder(request.getParameter("par_recorder"));
						t.setVeterinarian(request.getParameter("par_veterinarian"));
						t.setRemark(request.getParameter("par_remark"));
						if (!"".equals(request.getParameter("par_cdate"))&&request.getParameter("par_cdate")!=null) {
							t.setCdate(DateUtil.stringToDate(request.getParameter("par_cdate"), "yyyy-MM-dd"));
						}
						t.setDeleted(Constant.deleted_0);
						//检疫项目
						t.setQ_id(Integer.valueOf(tp.getId().toString()));
						//阴性or阳性
						String resoult = request.getParameter(tp.getId()+ "_result");
						t.setResoult(Integer.valueOf(resoult));
						//检测方法
						String qconfig_id = request.getParameter(tp.getId()+ "_select");
						t.setQconfig_id(Integer.valueOf(qconfig_id));
						
						//常规检疫id更新进parasite表normalid
						t.setNormal_id(Integer.valueOf(request.getParameter("parasite_normalid")));
						try {
							Normal n=normalService.getById(Long.valueOf(request.getParameter("parasite_normalid")));
							//下面更新normal
							n.setModified_by((Integer.parseInt(user.getId()+"")));
							n.setLastmodifytime(Timestamp.valueOf(DateUtil.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss")));
							n.setParasite(Constant.NORMAL_FLAG);
							normalService.update(n);
							parasiteService.save(t);
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
		Map<String, Object> map = parasiteService.loadListByMonkeyIdAndCdate(
				page, rows, "");
		String jsonStr = JsonPluginsUtil.beanToJson(map, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	public void loadListItem() {
		Map<String, Object> map = parasiteService.loadListItem(
				model.getMonkeyid(),
				DateUtil.dateToString(model.getCdate(), "yyyy-MM-dd"));
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void loadListYP(){
		List<Map<String,String>> listMap=parasiteService.getYP(Constant.yp);
		String jsonStr=JsonPluginsUtil.beanListToJson(listMap);
		writeJson(jsonStr);
	}
	
	/**
	 * 报表--体内寄生虫
	 * @return
	 */
	
	public String listInParasite(){
		return "inParasiteRecord";
	}
	public void inParasiteByJson(){
		Map map=parasiteService.getInParasite(rows, page,model.getMonkeyid(),model.getCdate());
		String jsonStr=JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	public String reportInParasite(){
		return "inParasiteReport";
	}
	public String inParasiteByReport(){
		URL logoImage=null;
		logoImage=this.getClass().getResource("logo.jpg");
		fileName="inParasiteDetails"+DateUtil.dateToString(new Date(), "yyyyMMddsss");
		
		paraMap=new HashMap<String,Object>();
		paraMap.put("company", Constant.COMPANY_NAME);
		paraMap.put("logoImage", logoImage);
		paraMap.put("title", Constant.TITLE_XCG_RECORD);
		paraMap.put("checkNumber", Constant.NUM_JC+RandomUtil.randomNum(5, 10));
		paraMap.put("animalType", Constant.animalType);//
		paraMap.put("checkId", checkId);
		//查询检疫时间.
		String checkDate=normalService.getCheckDateByTitle(checkId);
		paraMap.put("checkDate", checkDate);
		
		Map map=parasiteService.getInParasite(model.getMonkeyid(),parasitedate,checkId);
		dataSourceList=(List<Map<String, Object>>) map.get("rows");
		return "inParasiteByReport";
	}
	
	/**
	 * 报表--体外寄生虫
	 * @return
	 */
	
	public String listOutParasite(){
		return "outParasiteRecord";
	}
	public void outParasiteByJson(){
		Map map=parasiteService.getOutParasite(rows, page,model.getMonkeyid(),model.getCdate());
		String jsonStr=JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	public String reportOutParasite(){
		return "outParasiteReport";
	}
	public String outParasiteByReport(){
		URL logoImage=null;
		logoImage=this.getClass().getResource("logo.jpg");
		fileName="outParasiteDetails"+DateUtil.dateToString(new Date(), "yyyyMMddsss");
		
		paraMap=new HashMap<String,Object>();
		paraMap.put("company", Constant.COMPANY_NAME);
		paraMap.put("logoImage", logoImage);
		paraMap.put("title", Constant.TITLE_XCG_RECORD);
		paraMap.put("checkNumber", Constant.NUM+RandomUtil.randomNum(5, 10));
		paraMap.put("animalType", Constant.animalType);//
		paraMap.put("checkDate", DateUtil.dateToString(new Date(), "yyyy-MM-dd"));
		
		Map map=parasiteService.getOutParasite(model.getMonkeyid(),parasitedate);
		dataSourceList=(List<Map<String, Object>>) map.get("rows");
		return "outParasiteByReport";
	}
	
	//出口检疫，批量添加
	public void addMonkeyList() {
		Map<String, Object> map = new HashMap<String, Object>();

		if (monkeylist != null && !"".equals(monkeylist)) {
			String[] s = monkeylist.split(",");
			for (int i = 0; i < s.length; i++) {
				//寄生虫出口检疫项目有：阿米巴：弓形虫：疟原虫：鞭毛虫：体外寄生虫：
				List<Quarantine> listquarantine=quarantineService.getQuarantineByMark(Constant.parasite);
				for (Object ob:listquarantine) {
					Object []objs=(Object[])ob;
					
					Integer id=(Integer)objs[0];
					String quarantineName=(String)objs[1];
					if ("阿米巴".equals(quarantineName) || "弓形虫".equals(quarantineName)||
							"疟原虫".equals(quarantineName) || "鞭毛虫".equals(quarantineName) || "体外寄生虫".equals(quarantineName)) {
						//通过寄生虫检测项目需循环检测项目insert表
						Parasite par = new Parasite();
						par.setMonkeyid(s[i]);
						
						//检疫项目id
						par.setQ_id(id);
						if("阿米巴".equals(quarantineName)){
							if (request.getParameter("amb")!=null&&!"".equals(request.getParameter("amb"))) {
								//par.setDrugs_name(request.getParameter("amb"));
								par.setResoult(Integer.valueOf(request.getParameter("amb").trim()));
								par.setAmb("amb");//优化
							}
							
						}
						if("弓形虫".equals(quarantineName)){
							if (!"".equals(request.getParameter("gxc"))&&request.getParameter("gxc")!=null) {
								//par.setDrugs_name(request.getParameter("gxc"));
								par.setResoult(Integer.valueOf(request
										.getParameter("gxc").trim()));
								par.setGxc("gxc");
							}
						}
						if("疟原虫".equals(quarantineName)){
							if (request.getParameter("lyc")!=null&&!!"".equals(request.getParameter("lyc"))) {
								//par.setDrugs_name(request.getParameter("lyc"));
								par.setResoult(Integer.valueOf(request.getParameter("lyc").trim()));
								par.setLyc("lyc");
							}
						}
						if("鞭毛虫".equals(quarantineName)){
							if (request.getParameter("lyc")!=null&&!"".equals(request.getParameter("lyc"))) {
								//par.setDrugs_name(request.getParameter("bmc"));
								par.setResoult(Integer.valueOf(request.getParameter("bmc").trim()));
								par.setBmc("bmc");
							}
						}
						if("体外寄生虫".equals(quarantineName)){
							if (request.getParameter("twjsc")!=null&&!"".endsWith(request.getParameter("twjsc"))) {
								//par.setDrugs_name(request.getParameter("twjsc"));
								par.setResoult(Integer.valueOf(request.getParameter("twjsc").trim()));
								par.setTwjsc("twjsc");
							}
						}
						String qcry=request.getParameter("qcry");
						if (!"".equals(qcry)&&qcry!=null) {
							//驱虫人员
							par.setQcry(Integer.valueOf(qcry));
						}
						//驱虫日期
						par.setQcrq(request.getParameter("qcrq"));
						//驱虫药品
						par.setQcyp(request.getParameter("qcyp"));
						if (request.getParameter("qcbd")!=null&&!"".equals(request.getParameter("qcbd"))) {
							//驱虫保定
							par.setQcbd(Integer.valueOf(request.getParameter("qcbd").trim()));
						}
						if (request.getParameter("yb_id")!=null&&!"".equals(request.getParameter("yb_id"))) {
							//样品
							par.setYb_id(Integer.valueOf(request.getParameter("yb_id").trim()));
						}
						if (!"".equals(request.getParameter("getybdate"))) {
							//采样日期 
							par.setGetybdate(DateUtil.stringToDate(request
									.getParameter("getybdate").toString(),
									"yyyy-MM-dd"));
						}
						//检测兽医
						par.setVeterinarian(request
								.getParameter("paras_veterinarian"));
						//保定人员
						par.setProtector(request
								.getParameter("paras_protector"));
						//记录人员
						par.setRecorder(request.getParameter("paras_recorder"));
						if (!"".equals(request.getParameter("paras_cdate"))) {
							//检疫时间
							par.setCdate(DateUtil.stringToDate(request
									.getParameter("paras_cdate").toString(),
									"yyyy-MM-dd"));
						}
						//备注
						par.setRemark(request.getParameter("paras_remark"));
						par.setDeleted(Constant.deleted_0);
						par.setPtype(Constant.export);//出口检疫标志
						/*if (!"".equals(request.getParameter("parasite_exportid"))&&
								request.getParameter("parasite_exportid")!=null) {
							//出口检疫也需同步normal_id.
							par.setNormal_id(Integer.valueOf(request
									.getParameter("parasite_exportid").trim()));
						}*/
						par.setNormal_id(exportService.getNextNormalId());
						parasiteService.save(par);
						//此处没有更新normal表。待检疫项目添加完毕再更新normal.
					}
				}
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
			String[] remarks1 =  remarks.split(",");
			String[] bhaos1 =  bhaos.split(",");
			String[] ambs1 = ambs.split(",");
			String[] rcs1 = rcs.split(",");
			String[] bmcs1 = bmcs.split(",");
			String[] twjscs1 = twjscs.split(",");
			
			String [] monkeylist1=monkeylist.split(",");
			if (monkeylist1 != null) {
				for (int i = 0; i < monkeylist1.length; i++) {
					Normal normal = normalService.getById(Long.valueOf(orderid));
					Parasite parasite = new Parasite();
					if (!"-".equals(bhaos1[i])) {
						parasite.setBhao(bhaos1[i]);
					}
					if (!"-".equals(ambs1[i])) {
						parasite.setAmb(ambs1[i]);
					}
					if (!"-".equals(rcs1[i])) {
						parasite.setGxc(rcs1[i]);// 蠕虫
					}
					if (!"-".equals(bmcs1[i])) {
						parasite.setBmc(bmcs1[i]);
					}
					if (!"-".equals(twjscs1[i])) {
						parasite.setTwjsc(twjscs1[i]);
					}
					parasite.setMonkeyid(monkeylist1[i]);
					if (!"-".equals(remarks1[i])) {
						parasite.setRemark(remarks1[i]);
					}
					parasite.setNormal_id(Integer.valueOf(orderid));
					parasite.setLastmodifytime(Timestamp.valueOf(DateUtil.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss")));
					Employee user = (Employee) ActionContext.getContext().getSession().get("user");
					parasite.setModified_by(Integer.valueOf(user.getId() + ""));
					parasite.setDeleted(0);// 新增的时候将删除标志一并保存.
					parasite.setCdate(normal.getCheckdate());
					parasiteService.save(parasite);

					// 更新normal表.
					
					normal.setParasite("√");
					// normal.setStatus("2");
					normalService.update(normal);
					map.put("success", true);
					map.put("msg", "添加成功");
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

	public String getMonkeyid() {
		return monkeyid;
	}

	public void setMonkeyid(String monkeyid) {
		this.monkeyid = monkeyid;
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

	public String getMonkeylist() {
		return monkeylist;
	}

	public void setMonkeylist(String monkeylist) {
		this.monkeylist = monkeylist;
	}

	public String getParasitedate() {
		return parasitedate;
	}

	public void setParasitedate(String parasitedate) {
		this.parasitedate = parasitedate;
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

	public String getAmbs() {
		return ambs;
	}

	public void setAmbs(String ambs) {
		this.ambs = ambs;
	}

	public String getRcs() {
		return rcs;
	}

	public void setRcs(String rcs) {
		this.rcs = rcs;
	}

	public String getBmcs() {
		return bmcs;
	}

	public void setBmcs(String bmcs) {
		this.bmcs = bmcs;
	}

	public String getTwjscs() {
		return twjscs;
	}

	public void setTwjscs(String twjscs) {
		this.twjscs = twjscs;
	}

	public String getBhaos() {
		return bhaos;
	}

	public void setBhaos(String bhaos) {
		this.bhaos = bhaos;
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
