package com.lanen.view.action.arp;

import java.math.BigInteger;
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
import com.lanen.model.AreaJson;
import com.lanen.model.Employee;
import com.lanen.model.Quarantine;
import com.lanen.service.arp.AreaService;
import com.lanen.service.arp.IndividualService;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ReportAction extends BaseAction<Quarantine> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 675379284975314548L;

	private Map<String, Object> paraMap;
	private List<Map<String, Object>> sourceList;
	private String fileName;

	@Resource
	private AreaService areaService;
	@Resource
	private IndividualService individualService;

	/* 转到报表页面 */
	public String toReport() {
		return "report";
	}

	public String feedingToReport() {
		Employee e = (Employee) ActionContext.getContext().getSession()
				.get("user");
		String username = e.getName();
		String animalType = Constant.animalType;

		URL logoImage = null;
		URL subReportURL = null;

		paraMap = new HashMap<String, Object>();// 参数列表
		logoImage = this.getClass().getResource("logo.jpg");
		subReportURL = this.getClass().getResource("report4.jasper");

		paraMap.put("userName", username);
		paraMap.put("animalType", animalType);
		paraMap.put("inventoryDate",
				DateUtil.dateToString(new Date(), "yyyy-MM-dd"));
		paraMap.put("logoImage", logoImage);
		paraMap.put("subReportUrl", subReportURL);
		fileName = "ClinicalTestApply"
				+ DateUtil.dateToString(new Date(), "yyyyMMddsss");
		sourceList = new ArrayList<Map<String, Object>>();

		//区域
		List<Map<String, Object>> listmap = areaService.getAllPareaIdName();
		Map<String, Object> map = null;
		for (Map l : listmap) {
			Long pareaId = (Long.valueOf(l.get("id") + ""));
			//房间
			List<Map<String, Object>> listmap1 = areaService
					.getAllRoomIdName(pareaId);
			for (Map l1 : listmap1) {
				//年龄阶段
				List<?> list1 = individualService.getMonkeyAgeType();
				List<?> list2 = individualService.getMonkeySex();
				for (int i = 0; i < list1.size(); i++) {
					Integer type = (Integer) list1.get(i);
					map = new HashMap<String, Object>();
					map.put("area", l.get("text"));

					map.put("room", l1.get("text"));
					//年龄对应的总数
					BigInteger count1 = individualService.getMonkeyCount(
							(Integer) l1.get("id"), type);
					String countStr1=count1.toString();
					if(countStr1==null||"".equals(countStr1)){
						countStr1="×";
					}
					map.put("monkeyCount", countStr1);
					String monkeyType1 = null;
					if (null != type&&type==1) {
						
							monkeyType1 = "仔猴";
							
						
					}
					if (null != type&&type==2) {
						
						
						monkeyType1 = "育成";
					
					}
					if(monkeyType1==null||"".equals(monkeyType1)){
						monkeyType1="--";
					}
					map.put("monkeyType", monkeyType1);
					
					sourceList.add(map);
					if(type==3){
					for (int j = 0; j < list2.size(); j++) {
						map = new HashMap<String, Object>();
						map.put("area", l.get("text"));

						map.put("room", l1.get("text"));
						Byte sex = (Byte) list2.get(j);
						BigInteger count = individualService.getMonkeyCount(
								(Integer) l1.get("id"), type, sex);
						String countStr=count.toString();
						if(countStr==null||"".equals(countStr)){
							countStr="×";
						}
						map.put("monkeyCount", countStr);

						String monkeyType = null;
						if (null != type) {
							if (type == 3 && sex != null) {
								if (sex == 0) {
									monkeyType = "公猴";
								}
								if (sex == 1) {
									monkeyType = "母猴";
								}
							}
						}
						if(monkeyType==null||"".equals(monkeyType)){
							monkeyType="--";
						}
						map.put("monkeyType", monkeyType);
						sourceList.add(map);
					}
				}
				}}
			
		}
		return "feedingToReport";
	}

	public String feedingToReport1() {
		Employee e = (Employee) ActionContext.getContext().getSession()
				.get("user");
		String username = e.getName();
		String animalType = Constant.animalType;

		URL logoImage = null;
		URL subReportURL = null;

		paraMap = new HashMap<String, Object>();// 参数列表
		logoImage = this.getClass().getResource("logo.jpg");
		subReportURL = this.getClass().getResource("report4.jasper");

		paraMap.put("printName", username);
		paraMap.put("animalType", animalType);
		paraMap.put("inventoryDate",
				DateUtil.dateToString(new Date(), "yyyy-MM-dd"));
		paraMap.put("logoImage", logoImage);
		paraMap.put("subReportUrl", subReportURL);
		fileName = "ClinicalTestApply"
				+ DateUtil.dateToString(new Date(), "yyyyMMddsss");
		sourceList = new ArrayList<Map<String, Object>>();

		//区域
		List<Map<String, Object>> listmap = areaService.getAllPareaIdName();
		Map<String, Object> maleMonkeyMap = null;
		Map<String, Object> femaleMonkeyMap = null;
		Map<String, Object> yuchengMonkeyMap = null;
		Map<String, Object> cubMonkeyMap = null;
		for (Map l : listmap) {
			Long pareaId = (Long.valueOf(l.get("id") + ""));
			//房间
			List<Map<String, Object>> listmap1 = areaService
					.getAllRoomIdName(pareaId);
			for (Map l1 : listmap1) {
				//年龄阶段
				maleMonkeyMap = new HashMap<String, Object>();
				femaleMonkeyMap = new HashMap<String, Object>();
				yuchengMonkeyMap = new HashMap<String, Object>();
				cubMonkeyMap = new HashMap<String, Object>();
				
				
					//年龄对应的总数
					AreaJson area = areaService.getAreanameAndMonkeyCount(Long.parseLong(l1.get("id")+""));
					maleMonkeyMap.put("monkeyType", "公猴");
					maleMonkeyMap.put("monkeyCount", area.getMaleMonkeyCount());
					maleMonkeyMap.put("room",l1.get("text") );
					maleMonkeyMap.put("area", l.get("text"));
					sourceList.add(maleMonkeyMap);
					femaleMonkeyMap.put("monkeyType", "母猴");
					femaleMonkeyMap.put("monkeyCount", area.getFemaleMonkeyCount());
					femaleMonkeyMap.put("room",l1.get("text") );
					femaleMonkeyMap.put("area", l.get("text"));
					sourceList.add(femaleMonkeyMap);
					yuchengMonkeyMap.put("monkeyType", "育成");
					yuchengMonkeyMap.put("monkeyCount", area.getYuchengMonkeyCount());
					yuchengMonkeyMap.put("room",l1.get("text") );
					yuchengMonkeyMap.put("area", l.get("text"));
					sourceList.add(yuchengMonkeyMap);
					cubMonkeyMap.put("monkeyType", "仔猴");
					cubMonkeyMap.put("monkeyCount", area.getCubMonkeyCount());
					cubMonkeyMap.put("room",l1.get("text") );
					cubMonkeyMap.put("area", l.get("text"));
					sourceList.add(cubMonkeyMap);
					
				}
			
		}
		return "feedingToReport";
	}

	/**
	 * 检疫查询
	 * @return
	 */
	public String listCheckItems(){
		return "listCheckItems";
	}
	public Map<String, Object> getParaMap() {
		return paraMap;
	}

	public void setParaMap(Map<String, Object> paraMap) {
		this.paraMap = paraMap;
	}

	public List<Map<String, Object>> getSourceList() {
		return sourceList;
	}

	public void setSourceList(List<Map<String, Object>> sourceList) {
		this.sourceList = sourceList;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
