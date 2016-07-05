package com.lanen.view.action.arp;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Employee;
import com.lanen.model.Normal;
import com.lanen.model.Publiccode;
import com.lanen.model.Surface;
import com.lanen.model.X;
import com.lanen.service.arp.ExportService;
import com.lanen.service.arp.NormalService;
import com.lanen.service.arp.SurfaceService;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class SurfaceAction extends BaseAction<Surface> {

	private static final long serialVersionUID = -8617076076727956005L;
	/**
	 * 体表信息Action
	 */
	@Resource
	private SurfaceService surfaceService;
	@Resource
	private NormalService normalService;
	@Resource
	private ExportService exportService;
	private String rows;
	private String page;
	private String monkeylist;
	private String monkeyid;

	private String orderid;
	private String remarks;
	public void list() {
		Map map = surfaceService.getSurfaceListByMonkeyId(monkeyid);
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/*
	 * public void loadList(){ Map<String,Object>
	 * map=routineService.loadListByCondition(page, rows,model.getMonkeyid());
	 * String json = JsonPluginsUtil.beanToJson(map); writeJson(json); } public
	 * void normalLIst(){ Map<String, Object>
	 * map=routineService.loadNormalList(); String
	 * json=JsonPluginsUtil.beanToJson(map); writeJson(json); }
	 */
	// 批量添加,出口检疫添加
	public void addMonkeyList() {
		Map<String, Object> map = new HashMap<String, Object>();

		if (monkeylist != null && !"".equals(monkeylist)) {
			String[] s = monkeylist.split(",");
			for (int i = 0; i < s.length; i++) {
				Surface sur = new Surface();
				sur.setMonkeyid(s[i]);
				sur.setVeterinarian(model.getVeterinarian());
				sur.setProtector(model.getProtector());
				sur.setRecorder(model.getRecorder());
				sur.setRemark(model.getRemark());
				sur.setDeleted(Constant.deleted_0);
				sur.setPtype(Constant.export);//出口检疫标志
				
				
				//出口检疫也需同步normal_id.
				//sur.setNormal_id(Integer.valueOf(request.getParameter("surface_exportid")));
				//此时不能拿到normal id 的值.
				sur.setNormal_id(exportService.getNextNormalId());
				surfaceService.save(sur);
				//此处没有更新normal表。
				//在exportAction中处理。
				map.put("success", true);
				map.put("msg", "添加成功");
			}
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	// 个体添加
	public void add() {
		Employee user = (Employee) ActionContext.getContext().getSession()
				.get("user");
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getMonkeyid() != null) {
			Surface s = new Surface();
			s.setMonkeyid(model.getMonkeyid());
			// s.setVeterinarian(model.getVeterinarian());
			// s.setRecorder(model.getRecorder());
			// s.setRemark(model.getRemark());
			// s.setProtector(model.getProtector());
			s.setVeterinarian(request.getParameter("sur_veterinarian"));
			s.setProtector(request.getParameter("sur_protector"));
			s.setRecorder(request.getParameter("sur_recorder"));
			s.setRemark(request.getParameter("sur_remark"));
			s.setCreated_by(Integer.parseInt(String.valueOf(user.getId())));
			// s.setCreatetime(new Date());
			s.setCdate(new Date());
			s.setDeleted(Constant.deleted_0);
			s.setPtype(Constant.normal);
			//此处拿到常规检疫的id 一并记录到surface表
			s.setNormal_id(Integer.valueOf(request.getParameter("surface_normalid")));
			surfaceService.save(s);
			
			
			//此时更新normal表
			//Normal n=new Normal();
			//Normal n=normalService.getById(Integer.parseInt(request.getParameter("to_normalid")));
			Normal n=normalService.getById(Long.valueOf(request.getParameter("surface_normalid")));
			n.setSurface(Constant.NORMAL_FLAG);
			n.setModified_by(Integer.parseInt(String.valueOf(user.getId())));
			n.setLastmodifytime(Timestamp.valueOf(DateUtil.dateToString(new Date(),
					"yyyy-MM-dd hh:mm:ss")));
			normalService.update(n);	
			map.put("success", true);
			map.put("msg", "添加成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	public void loadListByMonkeyId() {
		if (model.getMonkeyid() != null) {
			Map<String, Object> map = surfaceService.loadListByMonkeyId(rows,
					page, Constant.normal, model.getMonkeyid());
			String jsonStr = JsonPluginsUtil.beanToJson(map, "yyyy-MM-dd");
			writeJson(jsonStr);
		}

	}

	/**
	 * 后加
	 * @return
	 */
	public void saveRecord(){
		Map<String, Object> map = new HashMap<String, Object>();
		if (monkeylist != null) {
			String[] remarks1=remarks.split(",");
			String [] monkeylist1=monkeylist.split(",");
			
			if (monkeylist1 != null) {
					for (int i = 0; i < monkeylist1.length; i++) {
						Normal normal = normalService.getById(Long.valueOf(orderid));
						Surface sur = new Surface();
						
						if (!"-".equals(remarks1[i])) {
							sur.setRemark(remarks1[i]);
						}
						
						sur.setMonkeyid(monkeylist1[i]);
						sur.setNormal_id(Integer.valueOf(orderid));
						sur.setLastmodifytime(Timestamp
								.valueOf(DateUtil.dateToString(new Date(),
										"yyyy-MM-dd hh:mm:ss")));
						Employee user = (Employee) ActionContext.getContext()
								.getSession().get("user");
						sur.setModified_by(Integer.valueOf(user.getId()
								+ ""));
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd hh:mm:ss");
						String dataString = sdf.format(date);
						sur.setCreatetime(Timestamp.valueOf(dataString));
						sur.setDeleted(0);
						sur.setCdate(normal.getCheckdate());
						surfaceService.save(sur);
						//更新normal表.
						
						normal.setSurface("√");
						//normal.setStatus("2");
						normalService.update(normal);
						map.put("success", true);
						map.put("msg", "添加成功");
					}		
			}
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	public String getMonkeylist() {
		return monkeylist;
	}

	public void setMonkeylist(String monkeylist) {
		this.monkeylist = monkeylist;
	}

	public String getMonkeyid() {
		return monkeyid;
	}

	public void setMonkeyid(String monkeyid) {
		this.monkeyid = monkeyid;
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

}
