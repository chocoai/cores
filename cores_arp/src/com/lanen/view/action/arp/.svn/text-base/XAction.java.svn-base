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
import com.lanen.model.Normal;
import com.lanen.model.Publiccode;
import com.lanen.model.X;
import com.lanen.service.arp.ExportService;
import com.lanen.service.arp.NormalService;
import com.lanen.service.arp.XService;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class XAction extends BaseAction<X> {

	/**
	 * x
	 */
	private static final long serialVersionUID = -46431033078667336L;
	private String page;
	private String rows;

	@Resource
	private XService xService;
	@Resource
	private NormalService normalService;
	@Resource
	private ExportService exportService;
	private String monkeylist;
	
	private String orderid;
	private String checkareas;
	private String remarks;
	public void addNormalX() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getMonkeyid() != null) {
			X x = new X();
			x.setMonkeyid(model.getMonkeyid());
			x.setCheckarea(request.getParameter("checkarea"));
			x.setVeterinarian(request.getParameter("x_veterinarian"));
			x.setProtector(request.getParameter("x_protector"));
			x.setRecorder(request.getParameter("x_recorder"));
			x.setRemark(request.getParameter("x_remark"));
			String x_cdate=request.getParameter("x_cdate");
			if (x_cdate!=null&&!"".equals(x_cdate)) {
				x.setCdate(DateUtil.stringToDate(x_cdate, "yyyy-MM-dd"));
			}
			String normalid=request.getParameter("x_normalid");
			if (normalid!=null&&!"".equals(normalid)) {
				x.setNormal_id(Integer.valueOf(normalid));
			}
			xService.save(x);
			
			String xNormalId=request.getParameter("x_normalid");
			normalService.updateNormalById(xNormalId,Constant.X_NAME);
		}
		map.put("success", true);
		map.put("msg", "添加成功");
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void loadListByMonkeyId() {
		Map<String, Object> map = xService.loadListByMonkeyId(rows, page,
				model.getMonkeyid(), Constant.normal);
		String str = JsonPluginsUtil.beanToJson(map);
		writeJson(str);
	}

	// 获取检测部位
	public void getCheckArea() {
		List<Map<String, String>> mapList = xService.getCheckArea();
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
	}

	//出口检疫批量添加
	public void addMonkeyList(){
		Map map=new HashMap<String,Object>();
		if(!"".equals(monkeylist)&&monkeylist!=null){
			String[] monkeylists=monkeylist.split(",");
			for(int i=0;i<monkeylists.length;i++){
				X x=new X();
				x.setCheckarea(request.getParameter("checkarea"));
				x.setVeterinarian(request.getParameter("x_veterinarian"));
				x.setProtector(request.getParameter("x_protector"));
				x.setRecorder(request.getParameter("x_recorder"));
				x.setRemark(request.getParameter("x_remark"));
				
				x.setDeleted(Byte.valueOf(Constant.deleted_0.toString()));
				//对应normal表id，没实际意义
				//x.setNormal_id(Integer.parseInt(request.getParameter("x_exportid")));
				x.setNormal_id(exportService.getNextNormalId());
				//对应X表dateid,其实就是检疫单号,唯一,且一批出口检疫动物的各检疫项目的dateid要一样
				x.setDateid(request.getParameter("x_exportid"));
				xService.save(x);
				
				//更新normal表
				
				
			}
			map.put("success", true);
			map.put("msg", "添加成功");
		}
		String strJson=JsonPluginsUtil.beanToJson(map);
		writeJson(strJson);
		
	}
	/**
	 * 检疫部位
	 * @return
	 */
	public void loadListCheckArea(){
		List<Map<String,Object>> lm=xService.getCheckArea(Constant.CHECK_AREA_MARK);
		String json=JsonPluginsUtil.beanListToJson(lm);
		writeJson(json);
	}
	/**
	 * 后加
	 * @return
	 */
	public void saveRecord(){
		Map<String, Object> map = new HashMap<String, Object>();
		if (monkeylist != null) {
			String[] checkareas1= checkareas.split(",");
			String[] remarks1=remarks.split(",");
			String [] monkeylist1=monkeylist.split(",");
			
			if (monkeylist1 != null) {
					for (int i = 0; i < monkeylist1.length; i++) {
						Normal normal = normalService.getById(Long.valueOf(orderid));
						X inf = new X();
						
						if (!"-".equals(checkareas1[i])) {
							
							//-----胸部(233)
							String checkareaName=(checkareas1[i]).split("[(]")[0];
							Publiccode q=xService.getIdByNamess(checkareaName);
							inf.setCheckarea(q.getId()+"");
							//-----
							
							String checkareaId=checkareas1[i].split("//(")[1];
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
						xService.save(inf);
						//更新normal表.
						
						normal.setX("√");
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

	public String getCheckareas() {
		return checkareas;
	}

	public void setCheckareas(String checkareas) {
		this.checkareas = checkareas;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
