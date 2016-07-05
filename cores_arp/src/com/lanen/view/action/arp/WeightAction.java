package com.lanen.view.action.arp;

import java.net.URL;
import java.util.ArrayList;
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
import com.lanen.model.Publiccode;
import com.lanen.model.Weight;
import com.lanen.model.Weight_Json;
import com.lanen.service.arp.EmployeeService;
import com.lanen.service.arp.IndividualService;
import com.lanen.service.arp.PubliccodeService;
import com.lanen.service.arp.WeightService;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.lanen.util.RandomUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class WeightAction extends BaseAction<Weight> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private WeightService weightService;

	@Resource
	private EmployeeService employeeService;

	@Resource
	private IndividualService individualService;

	private String rows;// 每页显示的记录数
	private String page;// 当前第几页

	private Date startDate;// 开始时间

	private Date endDate;// 结束时间

	private List<Weight_Json> sourceList;
	private Map<String,Object> paraMap;
	private String fileName;
	private String weightDate;
	
	@Resource
	private PubliccodeService publiccodeService;
	public String list() {
		return "list";
	}

	public void loadDate() {
		int type;
		if (model.getWeighttype() == null) {
			type = -1;
		} else {
			type = model.getWeighttype();
		}
		Map<String, Object> mapList = weightService.getAllWeight(page, rows,
				type, DateUtil.dateToString(startDate, "yyyy-MM-dd"),
				DateUtil.dateToString(endDate, "yyyy-MM-dd"));
		String jsonStr = JsonPluginsUtil.beanToJson(mapList, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	public void loadOneDate() {
		List<Weight> list = weightService.getOneWeight(model.getMonkeyid());

		List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
		for (Weight obj : list) {
			Map<String,Object> weight = new HashMap<String,Object>();
			Integer type=obj.getWeighttype();
			Publiccode pc=publiccodeService.getById(type);
			weight.put("weighttypeName", pc.getName());
			weight.put("id", obj.getId());
			weight.put("monkeyid", obj.getMonkeyid());
			weight.put("weightdate", obj.getWeightdate());
			weight.put("weight", obj.getWeight());
			weight.put("weighttype", obj.getWeighttype());
			
			if (null != obj.getBoss() && (!obj.getBoss().equals(""))) {
				Employee employee = employeeService.getById(Long.parseLong(obj
						.getBoss()));
				if (null != employee) {
					weight.put("boss", employee.getName());
				}
			}

			if (null != obj.getProtector() && (!obj.getProtector().equals(""))) {
				Employee employee1 = employeeService.getById(Long.parseLong(obj
						.getProtector()));
				if (null != employee1) {
					weight.put("protector", employee1.getName());
				}
			}
			if (null != obj.getRecorder() && (!obj.getRecorder().equals(""))) {
				Employee employee2 = employeeService.getById(Long.parseLong(obj
						.getRecorder()));
				if (null != employee2) {
					weight.put("recorder", employee2.getName());
				}
			}
			if (null != obj.getOperater() && (!obj.getOperater().equals(""))) {
				Employee employee3 = employeeService.getById(Long.parseLong(obj
						.getOperater()));
				if (null != employee3) {
					weight.put("operater", employee3.getName());
				}
			}
			weight.put("remark", obj.getRemark());
			weight.put("deleted", obj.getDeleted());
			weight.put("modified_by", obj.getModified_by());
			weight.put("createtime", obj.getCreatetime());
			weight.put("created_by", obj.getCreated_by());
			weight.put("lastmodifytime", obj.getLastmodifytime());
			weight.put("pccode", obj.getPccode());
			weight.put("process", obj.getProcess());
			list1.add(weight);

		}
		String jsonStr = JsonPluginsUtil.beanListToJson(list1);
		writeJson(jsonStr);
	}

	// 加载猴子下拉选
	public void monkeyidCombobox() {
		List<Map<String, String>> mapList = individualService
				.getAllMonkeyidCombobox();
		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(jsonStr);

	}

	/** 保存 */
	public void addSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		Employee user = (Employee) ActionContext.getContext().getSession()
				.get("user");
		if (null != model) {
			model.setDeleted((byte) 0);
			model.setCreatetime(new Date());
			model.setCreated_by(Integer.parseInt(String.valueOf(user.getId())));
			weightService.saveWeight(model);
		} else {
			map.put("success", false);
		}
		map.put("success", true);
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);

	}

	/**
	 * 删除
	 */
	public void del() {

		Map<String, Object> map = new HashMap<String, Object>();
		Employee user = (Employee) ActionContext.getContext().getSession()
				.get("user");
		if (null != model) {
			model.setDeleted((byte) 1);
			model.setLastmodifytime(new Date());
			model.setModified_by(Integer.parseInt(String.valueOf(user.getId())));
			weightService.update(model);
		} else {
			map.put("success", false);
		}
		map.put("success", true);
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);

	}

	/** 编辑 */
	public void editSave() {
		Weight weight = weightService.getById(model.getId());
		boolean flag = employeeService.isExistEmployeeid(model.getBoss());
		if (flag) {
			weight.setBoss(model.getBoss());
		}
		boolean flag1 = employeeService.isExistEmployeeid(model.getProtector());
		if (flag1) {
			weight.setProtector(model.getProtector());
		}
		boolean flag2 = employeeService.isExistEmployeeid(model.getRecorder());
		if (flag2) {
			weight.setProtector(model.getRecorder());
		}
		boolean flag3 = employeeService.isExistEmployeeid(model.getOperater());
		if (flag3) {
			weight.setProtector(model.getOperater());
		}
		weight.setWeight(model.getWeight());
		weight.setWeightdate(model.getWeightdate());
		weight.setWeighttype(model.getWeighttype());
		weight.setRemark(model.getRemark());
		weight.setLastmodifytime(new Date());
		Employee user = (Employee) ActionContext.getContext().getSession()
				.get("user");
		weight.setModified_by(Integer.parseInt(String.valueOf(user.getId())));

		weightService.updateWeight(weight);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);

	}
	//体格检查
	public String listWeight(){
		return "weightRecord";
	}
	//分页
	public void weightByJson(){
		Map map=weightService.getWeight(page, rows,model.getMonkeyid(),model.getWeightdate());
		String str=JsonPluginsUtil.beanToJson(map);
		writeJson(str);
	}
	public String reportWeight(){
		return "weightReport";
	}
	//----no分页，打印当前页
	public String weightByReport(){
		URL logoImage=null;
		paraMap=new HashMap<String,Object>();
		paraMap.put("company", Constant.COMPANY_NAME);
		paraMap.put("title", Constant.TITLE_WEIGHT_RECORD);
		paraMap.put("number", Constant.NUM+RandomUtil.randomNum(5, 10));
		paraMap.put("checkdate", weightDate);
		paraMap.put("animalType", Constant.animalType);
		
		logoImage=this.getClass().getResource("logo.jpg");
		paraMap.put("logoImage", logoImage);
		
		fileName = "CheckWeightDetail"+ DateUtil.dateToString(new Date(), "yyyyMMddsss");
		sourceList=new ArrayList<Weight_Json>();
		Map<String,Object> map=weightService.getWeight(model.getMonkeyid(), weightDate);
		List<Weight_Json> jsonWeight=(List<Weight_Json>)map.get("rows");
		sourceList=jsonWeight;
		
		return "weightByReport";
	}
	public List<Weight_Json> getSourceList() {		
		return sourceList;
	}

	public void setSourceList(List<Weight_Json> sourceList) {
		this.sourceList = sourceList;
	}

	public Map<String, Object> getParaMap() {
		return paraMap;
	}

	public void setParaMap(Map<String, Object> paraMap) {
		this.paraMap = paraMap;
	}

	public String toReport(){
		return "toReport";
	}

	/*public String weightToReport(){
		sourceList=new ArrayList<Map<String,Object>>();
		List<?> m=weightService.getMonkeyid();
		for(int i=0;i<m.size();i++){
			Map map=new HashMap<String,Object>();
			Integer wtype=weightService.getWeightType(String.valueOf(m.get(i)));
			Float weight=weightService.getWeight(String.valueOf(m.get(i)), wtype);
			String sex=weightService.getSex(String.valueOf(m.get(i)));
			//String sex=weightService.getSex("09120422");
			List<?> l=weightService.getMore(String.valueOf(m.get(i)));
			for(int j=0;j<l.size();j++){
				map.put("monkeyid", String.valueOf(m.get(i)));
				map.put("wtype",wtype);
				map.put("weight", weight);
				map.put("sex", sex);
				for(Object ob:l){
					Object[] objs=(Object[])ob;
					map.put("room", objs[0]);
					map.put("lhao", objs[1]);
				}				
				sourceList.add(map);
			}
		}
		
		return "weightToReport";
	}*/
	
	/**
	 * 称重类型
	 */
	public void getWeightTypeMap(){
		List<Map<String,String>> listMap=weightService.getWeightTypeMap(Constant.WEIGHT_TYPE_MARK);
		String jsonStr=JsonPluginsUtil.beanListToJson(listMap);
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getWeightDate() {
		return weightDate;
	}

	public void setWeightDate(String weightDate) {
		this.weightDate = weightDate;
	}

}
