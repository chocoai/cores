package com.lanen.view.action.arp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jfree.chart.JFreeChart;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Employee;
import com.lanen.model.Task;
import com.lanen.service.arp.EmployeeService;
import com.lanen.service.arp.SchedulePlanService;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class SchedulePlanAction extends
		BaseAction<Task> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8001271124159867887L;
	@Resource
	private SchedulePlanService schedulePlanService;

	private String taskKind;
	private String rows;
	private String page;
	private String ower;//计划操作人.
	private String begindate;
	private String enddate;
	private String typeid;//类型.
	private String title;//标题.
	
	@Resource
	private EmployeeService employeeService;
	/**
	 * tabs页加载
	 * @return
	 */
	public String list(){
		return "list";
	}
	/**
	 * 加载计划安排页
	 * @return
	 */
	public String loadSchedulePlan() {
		ActionContext.getContext().put("taskKind", taskKind);
		return "select";
	}
	public void getSchedulePlandatagrid(){
		
		//Date startTime = model.getStartTime();
		//Date endTime=model.getEndTime();	
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		//String end=sdf.format(endTime); 
		//String star=sdf.format(startTime); 
		//ActionContext.getContext().put("endtime", end);
		//ActionContext.getContext().put("startime", star);
		//ActionContext.getContext().put("description", description);
		Map<String,Object> map =schedulePlanService.getSchedulebyTaskKindCodeTypeTaskType(rows, page,model.getStatus(),model.getTitle(),model.getBegindate(),model.getEnddate(),model.getTypeid(),model.getOwer());
		if (!"".equals(model.getOwer())&&model.getOwer()!=null) {
			Employee e = employeeService.getById(model.getOwer());
			map.put("owerName", e.getName());//计划人名称.
		}else{
			map.put("owerName", "");
		}
		if (!"".equals(model.getTypeid())&&model.getTypeid()!=null) {
			map.put("typeName", schedulePlanService.getTypeName(model.getTypeid()));//计划类型.
		}else{
			map.put("typeName", "");
		}
		
		String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
		
	}
	/**
	 * 计划变更之前
	 * @return
	 */
	public void toEdit(){
		if(!"".equals(model.getId())){
			Task task=schedulePlanService.getById(model.getId());			
			String json=JsonPluginsUtil.beanToJson(task,"yyyy-MM-dd");
			writeJson(json);
		}
	}
	public void editSave(){
		if(!"".equals(model.getId())){
			Task task=schedulePlanService.getById(model.getId());
			Map<String,Object> map=new HashMap<String,Object>();
			task.setTitle(model.getTitle());
			task.setContant(model.getContant());
			task.setBegindate(model.getBegindate());
			task.setEnddate(model.getEnddate());
			task.setOwer(model.getOwer());
			task.setTypeid(model.getTypeid());
			//task.setStatus(model.getStatus());
			task.setRemark(model.getRemark());
			Employee user=(Employee)ActionContext.getContext().getSession().get("user");
			task.setLastmodifytime(Timestamp.valueOf(DateUtil.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss")));
			task.setModified_by(Integer.parseInt(user.getId()+""));
			schedulePlanService.update(task);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", task.getId());
			
			String json=JsonPluginsUtil.beanToJson(map);
			writeJson(json);
		}
	}
	/**
	 * 新建计划安排
	 * @return
	 */
	public void add(){
		Task task=new Task();
		Map<String,Object> map=new HashMap<String,Object>();
		if(model.getTitle()!=null&&!"".equals(model.getTitle())){
			task.setTitle(model.getTitle());
			task.setContant(model.getContant());
			task.setBegindate(model.getBegindate());
			task.setEnddate(model.getEnddate());
			task.setOwer(Long.valueOf(model.getOwer()));
			task.setOwer(model.getOwer());
			task.setTypeid(model.getTypeid());
			task.setStatus(1);//计划状态分：
			task.setDeleted(0);
			task.setCreatetime(Timestamp.valueOf(DateUtil.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss")));
			Employee user=(Employee)ActionContext.getContext().getSession().get("user");
			task.setCreated_by(Integer.parseInt(user.getId()+""));
			schedulePlanService.save(task);
			map.put("success", true);
			map.put("msg", "计划新建成功");
			map.put("id", task.getId());
		}
		String json=JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	/**
	 * 计划分类
	 * @return
	 */
	public void getSchedulePlanType(){
		List<Map<String, String>> mapList = schedulePlanService.getSchedulePlanMapNo(Constant.SCHEDULEPLAN_TYPE_MARK);
		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(jsonStr);
	}
	/**
	 * 提醒
	 * @return
	 */
	public void getSchedulePlanTips(){
		Date date=new Date();
		String sdate=DateUtil.dateToString(date, "yyyy-MM-dd");
		//获得当前日期的后7天.
		Date date7=DateUtil.AddDate(date, 7);
	    String sdate7=DateUtil.dateToString(date7, "yyyy-MM-dd");
	    Map<String,Object> map =schedulePlanService.getSchedulePlanTips(sdate, sdate7);
	    List<Task> listmap=(List<Task>) map.get("rows");
	    /*if(listmap.size()>0){
	    	for(Task t:listmap){
	    		//当前日期超过计划开始日期.提醒状态置为1，且以后不出现提醒.
	    		if(1!=t.getIsremind()&&t.getBegindate().before((DateUtil.stringToDate(DateUtil.dateToString(date, "yyyy-MM-dd"), "yyyy-MM-dd")))){
	    			t.setIsremind(1);
	    			schedulePlanService.update(t);
	    		}
	    	}
	    }*/
		String json = JsonPluginsUtil.beanToJson(map,"yyyy-MM-dd");//net.sf.json.JSONException: java.lang.reflect.InvocationTargetException
		writeJson(json);
	}
	/**
	 * 计划完成登记
	 * @return
	 */
	public void scheduleFinishRegis(){
		Map<String,Object> map=new HashMap<String,Object>();
		if(model.getId()!=null&&!"".equals(model.getId())){
			Task task=schedulePlanService.getById(model.getId());
			task.setStatus(3);
			Employee user=(Employee)ActionContext.getContext().getSession().get("user");
			task.setLastmodifytime(Timestamp.valueOf(DateUtil.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss")));
			task.setModified_by(Integer.parseInt(user.getId()+""));
			schedulePlanService.update(task);
			map.put("success", true);
			map.put("msg", "登记成功");
			map.put("id", task.getId());
		}
		String json=JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	/**
	 * 计划开始登记
	 * @return
	 */
	public void scheduleStartRegis(){
		Map<String,Object> map=new HashMap<String,Object>();
		if(model.getId()!=null&&!"".equals(model.getId())){
			Task task=schedulePlanService.getById(model.getId());
			task.setStatus(2);
			Employee user=(Employee)ActionContext.getContext().getSession().get("user");
			task.setLastmodifytime(Timestamp.valueOf(DateUtil.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss")));
			task.setModified_by(Integer.parseInt(user.getId()+""));
			schedulePlanService.update(task);
			map.put("success", true);
			map.put("msg", "登记成功");
			map.put("id", task.getId());
		}
		String json=JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	/**
	 * 计划汇总
	 * @return
	 */
	private JFreeChart chart;
	
	public String loadSchedulePlanByJfreechar(){
		chart=schedulePlanService.createBarChart();
		return SUCCESS;
	}
	public String getTaskKind() {
		return taskKind;
	}
	public void setTaskKind(String taskKind) {
		this.taskKind = taskKind;
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
	public JFreeChart getChart() {
		return chart;
	}
	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
	public String getOwer() {
		return ower;
	}
	public void setOwer(String ower) {
		this.ower = ower;
	}
	public String getBegindate() {
		return begindate;
	}
	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
