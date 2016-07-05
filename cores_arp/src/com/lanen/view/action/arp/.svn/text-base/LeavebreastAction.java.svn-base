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
import com.lanen.model.Childbirth;
import com.lanen.model.Leavebreast;
import com.lanen.model.LeavebreastErport;
import com.lanen.service.arp.LeavebreastService;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.lanen.util.RandomUtil;

@Controller
@Scope("prototype")
public class LeavebreastAction extends BaseAction<Leavebreast> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2025501326268491130L;
	@Resource
	private LeavebreastService leavebreastService;
	private String rows;// 每页显示的记录数
	private String page;// 当前第几页

	private String leavebreastDate;
	private List<Map<String,Object>> dataSourceList;
	private Map<String,Object> paraMap;
	private String fileName;
	public List<Map<String, Object>> getDataSourceList() {
		return dataSourceList;
	}

	public void setDataSourceList(List<Map<String, Object>> dataSourceList) {
		this.dataSourceList = dataSourceList;
	}

	/**
	 * 跳转到离乳登记主页面
	 * 
	 * @return
	 */
	public String list() {
		return "leavebreastList";
	}

	/**
	 * 根据条件加载主页面离乳登记数据
	 */
	public void loadList() {
		Map<String, Object> mapList = leavebreastService.loadListByCondition(
				page, rows, model.getMonkeyid(), model.getMotherid(),
				model.getLeavebreastdate());
		String jsonStr = JsonPluginsUtil.beanToJson(mapList, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	/**
	 * 添加离乳登记记录
	 */
	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		Childbirth childbirth = new Childbirth();
		if (null != model.getMonkeyid() && !model.getMonkeyid().equals("")) {

			map.put("success", true);
			map.put("msg", "添加成功");
			map.put("id", childbirth.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 编辑离乳登记前加载数据
	 */
	public void toEdit() {
		if (null != model.getId() && model.getId() != 0) {
			// Childbirth childbirth=childbirthService.getById(model.getId());
			// String
			// jsonString=JsonPluginsUtil.beanToJson(childbirth,"yyyy-MM-dd");
			// writeJson(jsonString);
		}
	}

	/**
	 * 编辑离乳登记后更新数据
	 */
	public void editSave() {
		// Map<String, Object> map=new HashMap<String, Object>();
		// if(null!=model.getId()&&model.getId()!=0){
		// Childbirth childbirth=childbirthService.getById(model.getId());
		// childbirth.setMonkeyid(model.getMonkeyid());
		// childbirth.setMonkeyids(model.getMonkeyids());
		// childbirth.setLabordate(model.getLabordate());
		// childbirth.setLaborcondition(model.getLaborcondition());
		// childbirth.setChildercount(model.getChildercount());
		// childbirth.setKeeper(model.getKeeper());
		// childbirth.setVeterinarian(model.getVeterinarian());
		// childbirth.setProtector(model.getProtector());
		// childbirth.setRecorder(model.getRecorder());
		// childbirth.setOperater(model.getOperater());
		// childbirth.setCreatetime(new Date());
		// childbirth.setDeleted(0);
		// childbirthService.update(childbirth);
		// map.put("success",true);
		// map.put("msg","编辑成功");
		// map.put("id", childbirth.getId());
		// }
		// String jsonStr = JsonPluginsUtil.beanToJson(map);
		// writeJson(jsonStr);
	}

	/**
	 * 离乳登记记录删除
	 */
	public void delete() {
		Map<String, Object> map = new HashMap<String, Object>();
		// if(null!=model.getId()&&model.getId()!=0){
		// childbirthService.delete(model.getId());
		// // Childbirth childbirth=childbirthService.getById(model.getId());
		// // childbirth.setDeleted(1);
		// // childbirthService.update(childbirth);
		// map.put("success",true);
		// map.put("msg","删除成功");
		// }
		// String jsonStr = JsonPluginsUtil.beanToJson(map);
		// writeJson(jsonStr);
	}

	public String listLeavebreast(){
		return "leavebreastRecord";
	}
	public void leavebreastByJson(){
		Map<String,Object> leavebreastMap=leavebreastService.getLeavebreast(page,rows,model.getMotherid(),model.getLeavebreastdate());
		
		List<LeavebreastErport> leavebreastList=(List<LeavebreastErport>) leavebreastMap.get("rows");
		
		List<Map<String,Object>> list2 = new ArrayList<Map<String,Object>>();
		
		Map<String,Object> map2=null;
		for(LeavebreastErport l:leavebreastList){
			map2=new HashMap<String,Object>();
			map2.put("mroom", l.getMroom());
			map2.put("mlhao", l.getMlhao());
			map2.put("monkeyid", l.getMonkeyid());
			map2.put("sex", l.getSex());
			map2.put("weight", l.getWeight());
			map2.put("motherid", l.getMotherid());
			map2.put("zroom", l.getZroom());
			map2.put("remark", l.getRemark());
			map2.put("leavebreastdate", l.getLeavebreastdate());
			list2.add(map2);
		}
		Map map=new HashMap<String,Object>();
		map.put("rows", list2);
		Integer total = (Integer) leavebreastMap.get("total");
		map.put("total", total);
		
		String jsonStr=JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	public String reportLeavebreast(){
		return "leavebreastReport";
	}
	//打印所有，无分页
	public String leavebreastByReport(){
		URL logoImage=null;
		logoImage=this.getClass().getResource("logo.jpg");
		fileName="leavebreastDetails"+DateUtil.dateToString(new Date(), "yyyyMMddsss");
		
		paraMap=new HashMap<String,Object>();
		paraMap.put("company", Constant.COMPANY_NAME);
		paraMap.put("logoImage", logoImage);
		paraMap.put("title", Constant.TITLE_XCG_RECORD);
		paraMap.put("leavebreastNum", Constant.NUM_MD+RandomUtil.randomNum(5, 10));
		paraMap.put("animalType", Constant.animalType);//
		paraMap.put("leavebreastDate", model.getLeavebreastdate());
		
		dataSourceList=new ArrayList<Map<String,Object>>();
		List<LeavebreastErport> leavebreastErportList=leavebreastService.getLeavebreastErport(model.getMonkeyid(),model.getLeavebreastdate());
		
		Map<String,Object> map=null;
		for(LeavebreastErport l:leavebreastErportList){
			map=new HashMap<String,Object>();
			map.put("mroom", l.getMroom()==null?"":l.getMroom());
			map.put("mlhao", l.getMlhao()==null?"":l.getMlhao());
			map.put("monkeyid", l.getMonkeyid());
			map.put("sex", l.getSex()=="0"?"公":"母");
			map.put("weight", l.getWeight()==null?"":l.getWeight());
			map.put("motherid", l.getMotherid());
			map.put("zroom", l.getZroom()==null?"":l.getZroom());
			map.put("remark", l.getRemark()==null?"":l.getRemark());
			
			dataSourceList.add(map);
		}
		return "leavebreastByReport";
	}
	//打印仔猴离乳记录--------------------------------------------------------
	public String leavebreastLoadToReport(){
		dataSourceList=new ArrayList<Map<String,Object>>();
		List<LeavebreastErport> leavebreastErportList=leavebreastService.getLeavebreastErport(model.getMonkeyid(),leavebreastDate);
		
		Map<String,Object> map=null;
		for(LeavebreastErport l:leavebreastErportList){
			map=new HashMap<String,Object>();
			map.put("mroom", l.getMroom());
			map.put("mlhao", l.getMlhao());
			map.put("monkeyid", l.getMonkeyid());
			map.put("sex", l.getSex());
			map.put("weight", l.getWeight());
			map.put("motherid", l.getMotherid());
			map.put("zroom", l.getZroom());
			map.put("remark", l.getRemark());
			
			dataSourceList.add(map);
		}
		
		return "leavebreastPrintToReport";
	}
	public void setPage(String page) {
		this.page = page;
	}

	public String getPage() {
		return page;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getRows() {
		return rows;
	}

	public String getLeavebreastDate() {
		return leavebreastDate;
	}

	public void setLeavebreastDate(String leavebreastDate) {
		this.leavebreastDate = leavebreastDate;
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
	
}
