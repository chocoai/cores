package com.lanen.view.action.arp;

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
import com.lanen.model.Surface;
import com.lanen.service.arp.NormalService;
import com.lanen.service.arp.SurfaceService;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class NormalAction extends BaseAction<Normal> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -899736196629903425L;
	@Resource
	private NormalService normalService;
	@Resource
	private SurfaceService surfaceService;
	private String rows;
	private String page;
	private Date startweaningdate;
	private Date endweaningdate;

	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		Employee user = (Employee) ActionContext.getContext().getSession()
				.get("user");
		Normal n = new Normal();
		if (request.getParameter("normal_monkeyid") != null
				&& !"".equals(request.getParameter("normal_monkeyid"))) {
			n.setNormallist(request.getParameter("normal_monkeyid"));
			n.setCheckdate(
					(java.util.Date)DateUtil.stringToDate(request.getParameter("normal_cdate"), ""));
			n.setCreatetime(Timestamp.valueOf(DateUtil.dateToString(new Date(),
					"yyyy-MM-dd hh:mm:ss")));
			n.setCreated_by(Integer.parseInt(String.valueOf(user.getId())));

			n.setDeleted(Byte.valueOf(Constant.deleted_0.toString()));
			n.setQuarantinetype(Constant.normal);
			normalService.save(n);
			map.put("success", true);
			map.put("msg", "添加成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/*
	 * public void delWeaning(){ Map<String,Object> map = new HashMap<String,
	 * Object>(); if(model.getId()!=null){ Leavebreast
	 * i=weaningService.getById(model.getId()); String monkeyid=i.getMonkeyid();
	 * i.setDeleted(1); weaningService.update(i); map.put("success",true);
	 * map.put("msg","删除成功"); } String jsonStr =
	 * JsonPluginsUtil.beanToJson(map); writeJson(jsonStr); }
	 */
	public void toEdit() {
		if (model.getId() != null) {
			Normal d = normalService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(d, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}

	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null
				&& request.getParameter("normal_monkeyid") != null
				&& !"".equals(request.getParameter("normal_monkeyid"))) {
			Normal l = normalService.getById(Long.valueOf(model.getId()));
			l.setCheckdate((java.util.Date)DateUtil.stringToDate(request.getParameter("normal_cdate"), ""));
			normalService.update(l);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", l.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	public void delNormal(){
		Map<String, Object> map = new HashMap<String, Object>();
		if(model.getId()!=null){
			Normal normal=normalService.getById(model.getId());
			normal.setDeleted(Byte.valueOf(Constant.deleted_1.byteValue()));
			normalService.update(normal);
			
			//删除常规检疫记录时要更新各个检测项目记录表
			//surface,parasite(寄生虫),virus(病毒),bacteria(细菌),vaccine(疫苗),infectious(传染病),tb,x,xcg,xysh.
			//下面开始更新检测项目表。monkeyid,checkdate,quarantinetype.
			String normalid=Long.toString(model.getId());
			//查询检疫项目，monkeyid,checkdate
			List<Map<String,Object>> listItemMap=normalService.listItem(normalid);
			
			for(Map m:listItemMap){
				String surface=(String)m.get("surface");
				String parasite=(String)m.get("parasite");
				String virus=(String)m.get("virus");
				String bacteria=(String)m.get("bacteria");
				String vaccine=(String)m.get("vaccine");
				String infectious=(String)m.get("infectious");
				String tb=(String)m.get("tb");
				String x=(String)m.get("x");
				String xcg=(String)m.get("xcg");
				String xysh=(String)m.get("xysh");
				
				String monkeyid=(String)m.get("monkeyid");
				Date checkdate=(Date)m.get("checkdate");
				//下面判断是否需要更新surface表,checkdate要与cdate同样.
				//normal_id
				if(surface!=null&&!"".equals(surface)){
					//List<Surface> sur=(List<Surface>) surfaceService.getSurfaceBymonkeyidAndCheckdate(monkeyid, checkdate);
					//此处通过normalid确定删除的数据。
					List<Surface> sur=(List<Surface>) surfaceService.getSurfaceByNormalId(monkeyid, Integer.valueOf(normalid));
					Surface s=sur.get(0);
					s.setDeleted(Constant.deleted_1);
					surfaceService.update(s);
				}
				
			}
			
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	/**
	 * 检查检疫编号唯一性,是否存在
	 */
	public void checkNormalidHave() {
		if (null != request.getParameter("title") && !"".equals(request.getParameter("title"))) {
			boolean isExist = normalService.isExistNormalid(request.getParameter("title"));
			//字母加数字.
			//boolean isNum=RandomUtil.isNum(request.getParameter("title"));
			
			/*if(!isExist && isNum){
				writeJson("true");
			}else{
				writeJson("false");
			}*/
			if(!isExist){
				writeJson("true");
			}else{
				writeJson("false");
			}
		} else {
			writeJson("true");
		}
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

	public Date getStartweaningdate() {
		return startweaningdate;
	}

	public void setStartweaningdate(Date startweaningdate) {
		this.startweaningdate = startweaningdate;
	}

	public Date getEndweaningdate() {
		return endweaningdate;
	}

	public void setEndweaningdate(Date endweaningdate) {
		this.endweaningdate = endweaningdate;
	}
}
