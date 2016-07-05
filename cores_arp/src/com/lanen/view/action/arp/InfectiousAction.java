package com.lanen.view.action.arp;

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
import com.lanen.model.Infectious;
import com.lanen.model.Normal;
import com.lanen.model.Quarantine;
import com.lanen.service.arp.ExportService;
import com.lanen.service.arp.InfectiousService;
import com.lanen.service.arp.NormalService;
import com.lanen.service.arp.QuarantineService;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class InfectiousAction extends BaseAction<Infectious> {

	/**
	 * 防疫配置，传染病
	 */
	private static final long serialVersionUID = -899736196629903425L;
	@Resource
	private InfectiousService infectiousService;

	@Resource
	private QuarantineService quarantineService;
	
	@Resource
	private NormalService normalService;
	
	@Resource
	private ExportService exportService;
	
	private String rows;
	private String page;
	private String infectiousname;
	
	private String monkeylist;
	private String ypins;
	private String drugs_names;
	private String drugs_counts;
	private String remarks;
	private String orderid;
	public void addNormalInfectious() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getMonkeyid() != null) {
			Infectious i = new Infectious();
			i.setMonkeyid(model.getMonkeyid());
			String checkdate=request.getParameter("inf_cdate");
			if (!"".equals(checkdate)&&checkdate!=null) {
				i.setCdate(DateUtil.stringToDate(checkdate, "yyyy-MM-dd"));
			}
			i.setQ_id(request.getParameter("inf_crbmc"));//传染病名称
			i.setVeterinarian(request.getParameter("inf_veterinarian"));
			i.setProtector(request.getParameter("inf_protector"));
			i.setRecorder(request.getParameter("inf_recorder"));
			i.setRemark(request.getParameter("inf_remark"));
			i.setDrugs_count(request.getParameter("inf_drugsname"));
			i.setDrugs_name(request.getParameter("inf_drugscount"));

			i.setDeleted(Byte.valueOf(Constant.deleted_0.toString()));
			i.setPtype(Constant.normal);

			infectiousService.save(i);
			
			Normal normal=normalService.getById(Long.valueOf(request.getParameter("infectious_normalid")));
			normal.setInfectious(Constant.NORMAL_FLAG);
			normal.setLastmodifytime(Timestamp.valueOf(DateUtil.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss")));
			Employee user=(Employee)ActionContext.getContext().getSession().get("user");
			normal.setModified_by(Integer.valueOf(user.getId()+""));
			normalService.update(normal);
		}
		map.put("success", true);
		map.put("msg", "添加成功");

		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);

	}

	// 查看
	public void loadListByMonkeyId() {
		Map<String, Object> map = infectiousService.loadListByMonkeyId(rows,
				page, Constant.normal, model.getMonkeyid());
		String JsonStr = JsonPluginsUtil.beanToJson(map, "yyyy-MM-dd");
		writeJson(JsonStr);
	}

	public void loadListInfectiousName() {
		List<Map<String, String>> mapList = quarantineService
				.getQuarantineName(Constant.infectious);
		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(jsonStr);
	}
	
	/**
	 * 出口检疫批量添加
	 * @return
	 */
	public void addMonkeyList(){
		Map map=new HashMap<String,Object>();
		if(!"".equals(monkeylist) && monkeylist!=null){
			String [] str=monkeylist.split(",");
			for(int i=0;i<str.length;i++){

				Infectious inf=new Infectious();
				inf.setQ_id(request.getParameter("infectiousName"));//传染病名称
				inf.setDrugs_name(request.getParameter("infe_name"));
				inf.setDrugs_count(request.getParameter("infe_count"));
				inf.setVeterinarian(request.getParameter("infe_veterinarian"));
				inf.setDrugs_name(request.getParameter("infe_name"));
				inf.setDrugs_count(request.getParameter("infe_count"));
				inf.setProtector(request.getParameter("infe_protector"));
				inf.setRecorder(request.getParameter("infe_recorder"));
				inf.setRemark(request.getParameter("infe_remark"));
				
				//inf.setNormal_id((Integer.parseInt(request.getParameter("infectious_exportid"))));//出口检疫单号
				inf.setNormal_id(exportService.getNextNormalId());
				inf.setPtype(Constant.export);
				infectiousService.save(inf);
				
				//更新normal表
				map.put("success", true);
				map.put("msg", "添加成功");
			}
		}
		String strJson=JsonPluginsUtil.beanToJson(map);
		writeJson(strJson);
	}
	
	/**
	 * 传染病列表
	 * @return
	 */
	public void loadListInfectious(){
		List<Map<String,Object>> ls=infectiousService.getInfectiousMap(Constant.infectious);
		String json=JsonPluginsUtil.beanListToJson(ls);
		writeJson(json);
	}
	/**
	 * 后加
	 * @return
	 */
	public void saveRecord(){
		Map<String, Object> map = new HashMap<String, Object>();
		if (monkeylist != null) {
			String[] ypins1 =  ypins.split(",");
			String[] drugs_names1= drugs_names.split(",");
			String [] drugs_counts1=drugs_counts.split(",");
			String[] remarks1=remarks.split(",");
			String [] monkeylist1=monkeylist.split(",");
			
			if (monkeylist1 != null) {
					for (int i = 0; i < monkeylist1.length; i++) {
						Normal normal = normalService.getById(Long.valueOf(orderid));
						Infectious inf = new Infectious();
						if (!"-".equals(ypins1[i])) {
							inf.setYpin(ypins1[i]);
						}
						if (!"-".equals(drugs_names1[i])) {
							inf.setDrugs_name(drugs_names1[i]);
							
							//-----
							Quarantine q=infectiousService.getIdByNamw(drugs_names1[i]);
							inf.setDrugs_id(q.getId()+"");
							//-----
						}
						if (!"-".equals(drugs_counts1[i])) {
							inf.setDrugs_count(drugs_counts1[i]);
						}
						if (!"-".equals(remarks1[i])) {
							inf.setRemark(remarks1[i]);
						}
						
						inf.setMonkeyid(monkeylist1[i]);
						inf.setNormal_id(Integer.valueOf(orderid));
						inf.setLastmodifytime(Timestamp
								.valueOf(DateUtil.dateToString(new Date(),
										"yyyy-MM-dd hh:mm:ss")));
						Employee user = (Employee) ActionContext.getContext()
								.getSession().get("user");
						inf.setModified_by(Integer.valueOf(user.getId()
								+ ""));
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						String dataString = sdf.format(date);
						inf.setDeleted(Byte.valueOf("0"));
						inf.setCdate(normal.getCheckdate());
						infectiousService.save(inf);
						//更新normal表.
						
						normal.setInfectious("√");
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

	public String getInfectiousname() {
		return infectiousname;
	}

	public void setInfectiousname(String infectiousname) {
		this.infectiousname = infectiousname;
	}

	public String getMonkeylist() {
		return monkeylist;
	}

	public void setMonkeylist(String monkeylist) {
		this.monkeylist = monkeylist;
	}

	public String getYpins() {
		return ypins;
	}

	public void setYpins(String ypins) {
		this.ypins = ypins;
	}

	public String getDrugs_names() {
		return drugs_names;
	}

	public void setDrugs_names(String drugsNames) {
		drugs_names = drugsNames;
	}

	public String getDrugs_counts() {
		return drugs_counts;
	}

	public void setDrugs_counts(String drugsCounts) {
		drugs_counts = drugsCounts;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

}
