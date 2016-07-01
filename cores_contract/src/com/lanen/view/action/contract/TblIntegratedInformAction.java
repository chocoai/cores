package com.lanen.view.action.contract;



import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.HighGradeTreeGrid;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.contract.TblContract;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.contract.TblTestItem_Json;

import com.lanen.model.contract.TblCustomer_Json;
import com.lanen.service.contract.TblCustomerService;
import com.lanen.service.contract.TblIntegratedInformService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.contract.TblTestItemService;

import com.lanen.model.contract.TblContract_Json;
import com.lanen.service.contract.TblContractService;
import com.lanen.util.DateUtil;
import com.lanen.util.ReportMap;
import com.opensymphony.xwork2.ActionContext;


/**
 * 综合查询
 * @author 小万
 *
 */
@Controller
@Scope("prototype")
public class TblIntegratedInformAction extends BaseAction<TblContract>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6088359386117327051L;
	@Resource
	TblCustomerService tblCustomerService;//用户
	@Resource
	private TblContractService tblContractService;	//合同信息
	@Resource
	private TblTestItemService tblTestItemService; //供试品
	@Resource
	private TblStudyItemService tblStudyItemService;//委托项目
	@Resource
	private TblIntegratedInformService tblIntegratedInformService;	//汇总查询
	
	private Date startDate;//开始时间
	
	private Date endDate;//结束时间
	
	private String tiCode;//供试品类型
	
	private String tiName;//供试品名称
	
	private String studyName;//委托项目名称
	
	private String studyid;//委托项目的id
	
	private String studyNo;//课题编号，打印任命书用
	
	private String tiNo ;			//供试品编号
	
	private String sd;				//SD
	
	private int glpFlag;			//是否GLP
	
	
	public String left(){
		return "left";
	}
	/**委托单位信息查询*/
	@SuppressWarnings("static-access")
	public String customerList(){
		if(null == startDate){
			endDate = new Date();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(endDate);
			calendar.add(calendar.MONTH, -3);// 把日期往后增加一天.整数往后推,负数往前移动
			startDate = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		}
		boolean  read =  (Boolean)ActionContext.getContext().getSession().get("read");//获取读的权限
		ActionContext.getContext().put("read", read);
		ActionContext.getContext().put("startTime", DateUtil.dateToString(startDate, "yyyy-MM-dd"));
		ActionContext.getContext().put("endTime",  DateUtil.dateToString(endDate, "yyyy-MM-dd"));
		ActionContext.getContext().put("tiCode", tiCode);
		//判断是否有登记权限
		int addContract = 0;
		boolean add = (Boolean) ActionContext.getContext().getSession().get("add"); 
		if(add){
			addContract = 1;
		}
		ActionContext.getContext().put("addContract", addContract);
		return "customerList";
	}
	
	/**点击委托单位查询，自动加载客户信息*/
    public void loadCustomerList(){
    	boolean readAll = (Boolean) ActionContext.getContext().getSession().get("read");
    	String reader = getCurrentRealName();
    	List<TblCustomer_Json> list = tblCustomerService.getCustomerByCondition(tiCode, startDate, endDate, model.getSponsorName(),readAll,reader);
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("rows", list);
		map.put("total", list.size());
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
    }
   

	/**合同查询*/
	@SuppressWarnings("static-access")
	public String contractList(){
		if(null == startDate){
			endDate = new Date();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(endDate);
			calendar.add(calendar.MONTH, -3);// 把日期往后增加一天.整数往后推,负数往前移动
			startDate = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		}
		boolean  read =  (Boolean)ActionContext.getContext().getSession().get("read");//获取读的权限
		ActionContext.getContext().put("read", read);
		ActionContext.getContext().put("startTime", DateUtil.dateToString(startDate, "yyyy-MM-dd"));
		ActionContext.getContext().put("endTime",  DateUtil.dateToString(endDate, "yyyy-MM-dd"));
		ActionContext.getContext().put("tiCode", tiCode);
		return "contractList";
	}
	/**加载合同信息*/
	public void loadcontractList(){
		boolean readAll = (Boolean) ActionContext.getContext().getSession().get("read");
    	String reader = getCurrentRealName();
		List<TblContract_Json> listjson = tblContractService.getByStartimeAndEndtimeAndTiCode(startDate, endDate, tiCode,model.getContractCode(),readAll,reader);
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows", listjson);
		 map.put("total", listjson.size());
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
	}

	/**供试品查询*/
	@SuppressWarnings("static-access")
	public String testItemList(){
		if(null == startDate){
			endDate = new Date();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(endDate);
			calendar.add(calendar.MONTH, -3);// 把日期往后增加一天.整数往后推,负数往前移动
			startDate = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		}
		boolean  read =  (Boolean)ActionContext.getContext().getSession().get("read");//获取读的权限
		ActionContext.getContext().put("read", read);
		ActionContext.getContext().put("startTime", DateUtil.dateToString(startDate, "yyyy-MM-dd"));
		ActionContext.getContext().put("endTime",  DateUtil.dateToString(endDate, "yyyy-MM-dd"));
		ActionContext.getContext().put("tiCode", tiCode);
		return "testItemList";
	}
	
	/**加载供试品信息*/
	public void loadtestItemList(){
		boolean readAll = (Boolean) ActionContext.getContext().getSession().get("read");
		String reader = getCurrentRealName();
		List<TblTestItem_Json> listjson = tblTestItemService.getByStartimeAndEndtimeAndTiCode(startDate, endDate, tiCode,tiName,readAll,reader);
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows", listjson);
		 map.put("total", listjson.size());
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
	}

	/**委托项目*/
	@SuppressWarnings("static-access")
	public String studyItemList(){
		if(null == startDate){
			endDate = new Date();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(endDate);
			calendar.add(calendar.MONTH, -3);// 把日期往后增加一天.整数往后推,负数往前移动
			startDate = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		}
		boolean  read =  (Boolean)ActionContext.getContext().getSession().get("read");//获取读的权限
		ActionContext.getContext().put("read", read);
		ActionContext.getContext().put("startTime", DateUtil.dateToString(startDate, "yyyy-MM-dd"));
		ActionContext.getContext().put("endTime",  DateUtil.dateToString(endDate, "yyyy-MM-dd"));
		ActionContext.getContext().put("tiCode", tiCode);
		//判断是否有登记权限
		int addContract = 0;
		boolean add = (Boolean) ActionContext.getContext().getSession().get("add"); 
		if(add){
			addContract = 1;
		}
		ActionContext.getContext().put("addContract", addContract);
		return "studyItemList";
	}
	/**加载委托项目信息*/
	public void loadStudyItemList(){
		boolean readAll = (Boolean) ActionContext.getContext().getSession().get("read");
		String reader = getCurrentRealName();
		List<TblStudyItem> list=tblStudyItemService.loadStudyItemsByCondition(tiCode, startDate, endDate, studyName,readAll,reader);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", list.size());
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	/**汇总查询 collectList*/
	@SuppressWarnings("static-access")
	public String collectList(){
		if(null == startDate){
			endDate = new Date();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(endDate);
			calendar.add(calendar.MONTH, -3);// 把日期往后增加一天.整数往后推,负数往前移动
			startDate = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		}
		boolean  read =  (Boolean)ActionContext.getContext().getSession().get("read");//获取读的权限
		ActionContext.getContext().put("read", read);
		ActionContext.getContext().put("startTime", DateUtil.dateToString(startDate, "yyyy-MM-dd"));
		ActionContext.getContext().put("endTime",  DateUtil.dateToString(endDate, "yyyy-MM-dd"));
		ActionContext.getContext().put("tiCode", tiCode);
		return "collectList";
	}
	
	/**加载合同的汇总信息*/
	public void LoadcollectList(){
		User user =(User) ActionContext.getContext().getSession().get("user");//获取人
		boolean  read =  (Boolean)ActionContext.getContext().getSession().get("read");//获取读的权限
		List<?>  list;
		Map<Integer, Integer> stCountMap;
		if(read){
			list= tblIntegratedInformService.getByStartimeAndEndtimeAndTiCodeCollectList(startDate, endDate,tiCode,"");
		    stCountMap=tblStudyItemService.getCountStudyItemsByState(startDate, endDate,tiCode,""); 
		}else{
			 String name = user.getRealName();
			 list= tblIntegratedInformService.getByStartimeAndEndtimeAndTiCodeCollectList(startDate, endDate,tiCode,name);
			 stCountMap=tblStudyItemService.getCountStudyItemsByState(startDate, endDate,tiCode,name); 
		}
		
		List<Map<String,String>> rowsList = new ArrayList<Map<String,String>>();
		
		if(null != list && list.size() > 0){
			Object[] objs = (Object[]) list.get(0);
			if(null != objs[3]&& !objs[3].equals("")){
				Map<String,String> map3 =  new HashMap<String,String>();
				map3.put("name", "供试品数量");
				map3.put("value",(Integer)objs[2]+""+" 个 ");
				rowsList.add(map3);
			}
			if(null != objs[2]&& !objs[2].equals("")){
				Map<String,String> map2 =  new HashMap<String,String>();
				map2.put("name", "委托项目数量");
				map2.put("value",(Integer)objs[3]+""+" 个 ");
				rowsList.add(map2);
				}
				
				Set<Integer> lKeys=stCountMap.keySet();
				
				for(Integer k:lKeys){
				   if(k==0){
					   Map<String,String> mapS1 =  new HashMap<String,String>();
					    mapS1.put("name", "&nbsp&nbsp&nbsp--未启动项目数量");
						mapS1.put("value",stCountMap.get(k)+""+" 个 ");
						rowsList.add(mapS1);
				   }else if(k==1){
					   Map<String,String> mapS2 =  new HashMap<String,String>();
					    mapS2.put("name", "&nbsp&nbsp&nbsp--审批中项目数量");
						mapS2.put("value",stCountMap.get(k)+""+" 个 ");
						rowsList.add(mapS2);
				   }else if(k==2){
					   Map<String,String> mapS3 =  new HashMap<String,String>();
					    mapS3.put("name", "&nbsp&nbsp&nbsp--方案已确认数量");
						mapS3.put("value",stCountMap.get(k)+""+" 个 ");
						rowsList.add(mapS3);
				   }else if(k==3){
					   Map<String,String> mapS4 =  new HashMap<String,String>();
					    mapS4.put("name", "&nbsp&nbsp&nbsp--试验进行中数量");
						mapS4.put("value",stCountMap.get(k)+""+" 个 ");
						rowsList.add(mapS4);
				   }else if(k==4){
					   Map<String,String> mapS5 =  new HashMap<String,String>();
					    mapS5.put("name", "&nbsp&nbsp&nbsp--试验完成数量");
						mapS5.put("value",stCountMap.get(k)+""+" 个 ");
						rowsList.add(mapS5);
				   }else if(k==5){
					   Map<String,String> mapS6 =  new HashMap<String,String>();
					    mapS6.put("name", "&nbsp&nbsp&nbsp--试验报告完成数量");
						mapS6.put("value",stCountMap.get(k)+""+" 个 ");
						rowsList.add(mapS6);
				   }else if(k==6){
					   Map<String,String> mapS7 =  new HashMap<String,String>();
					    mapS7.put("name", "&nbsp&nbsp&nbsp--试验报告归档数量");
						mapS7.put("value",stCountMap.get(k)+""+" 个 ");
						rowsList.add(mapS7);
				   }else if(k==-1){
					   Map<String,String> mapS8 =  new HashMap<String,String>();
					    mapS8.put("name", "&nbsp&nbsp&nbsp--课题终止数量");
						mapS8.put("value",stCountMap.get(k)+""+" 个 ");
						rowsList.add(mapS8);
				   }
				}
				
			
			if(null != objs[0] && !objs[0].equals("")){
			Map<String,String> map =  new HashMap<String,String>();
			map.put("name", "委托单位数量");
			map.put("value",(Integer)objs[0]+""+" 个 ");
			rowsList.add(map);
			}
			if(null != objs[1]&& !objs[1].equals("")){
				Map<String,String> map1 =  new HashMap<String,String>();
				map1.put("name", "合同数量");
				map1.put("value",(Integer)objs[1]+""+" 个 ");
				rowsList.add(map1);
			}
			Map<String,String> map4 =  new HashMap<String,String>();
			String str = "";
			double sum=0.0;
			double sum1=0.0;
			double sum2=0.0;
			for(Object obj:list){
				Object[] objs1 = (Object[]) obj;
				if((Integer)objs1[5]==1||(Integer)objs1[5]==4){
					if((Integer)objs1[5]==1){
						if(null != objs1[4]){
							sum=sum+(double) Double.parseDouble(objs1[4].toString());
						}
					}
					if((Integer)objs1[5]==4){
						if(null != objs1[4]){
						  sum=sum+(double) Double.parseDouble(objs1[4].toString())*10000;
						}
					}
					
				}
				if((Integer)objs1[5]==2){
					sum1=sum1+(double) Double.parseDouble(objs1[4].toString());
					
				}
				if((Integer)objs1[5]==3){
					sum2=sum2+(double)Double.parseDouble(objs1[4].toString());
					
				}
				
			}
			if(sum>0){
				BigDecimal sumbd = new BigDecimal(sum);  
				str = str + sumbd+""+"元 ";
			}
			if(sum1>0){
				BigDecimal sumbd1 = new BigDecimal(sum1); 
				if(!str.equals("") ){
					str = str +" + ";
					str = str + sumbd1+""+"美元 ";
				}else{
					str = str + sumbd1+""+"美元 ";
				}
			}
			if(sum2>0){
				BigDecimal sumbd2 = new BigDecimal(sum2); 
				if(!str.equals("") ){
					str = str +" + ";
					str = str + sumbd2+""+"欧元";
				}else{
					str = str + sumbd2+""+"欧元";
				}
			}
			map4.put("name", "合同金额");
			map4.put("value",str);
			rowsList.add(map4);
		}else{
			Map<String,String> map3 =  new HashMap<String,String>();
			map3.put("name", "供试品数量");
			map3.put("value","" );
			rowsList.add(map3);
			Map<String,String> map2 =  new HashMap<String,String>();
			map2.put("name", "委托项目数量");
			map2.put("value","");
			rowsList.add(map2);
			Map<String,String> map =  new HashMap<String,String>();
			map.put("name", "委托单位数量");
			map.put("value","");
			rowsList.add(map);
			Map<String,String> map1 =  new HashMap<String,String>();
			map1.put("name", "合同数量");
			map1.put("value","");
			rowsList.add(map1);
			
			Map<String,String> map4 =  new HashMap<String,String>();
			map4.put("name", "合同金额");
			map4.put("value","");
			rowsList.add(map4);
		}
		 String jsonStr = JsonPluginsUtil.beanListToJson(rowsList);
		 System.out.println(jsonStr);
		 writeJson(jsonStr);
	}
	
	/**根据合同编号判断权限*/
	public void accessControl(){
		Map<String,Object> map =  new HashMap<String,Object>();
		System.out.println(model.getId());
		TblContract contract = tblContractService.getByContractCode(model.getId());
		int start = contract.getContractState();
		
		User user =(User) ActionContext.getContext().getSession().get("user");//获取人
		Boolean  write =  (Boolean) ActionContext.getContext().getSession().get("write");//获取写的权限
		Boolean  read =  (Boolean)ActionContext.getContext().getSession().get("read");//获取读的权限
		String realName = user.getRealName();
		String name = contract.getOperator();
		if(realName.equals(name)){
			map.put("name", true);
		}else{
			map.put("name", false);
		}
		map.put("start", start);
		map.put("write", write);
		map.put("read", read);
		map.put("success",true);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	public void accessControlAndStudyItem(){
		Map<String,Object> map =  new HashMap<String,Object>();
		TblContract contract = tblContractService.getByContractCode(model.getContractCode());
		TblStudyItem studyItem  = tblStudyItemService.getById(studyid);
		int studyState = studyItem.getStudyState();
		String sd = studyItem.getSd();
		map.put("sd", sd);
		map.put("studyState", studyState);// 0：未启动，1：课题方案审批中，2：课题方案已确认，3：试验进行中，4：试验完成，5：报告完成，6：报告归档，-1：课题终止
		int start = contract.getContractState();
		User user =(User) ActionContext.getContext().getSession().get("user");//获取人
		Boolean  write =  (Boolean) ActionContext.getContext().getSession().get("write");//获取写的权限
		Boolean  read =  (Boolean)ActionContext.getContext().getSession().get("read");//获取读的权限
		String realName = user.getRealName();
		String name = contract.getOperator();
		if(realName.equals(name)){
			map.put("name", true);
		}else{
			map.put("name", false);
		}
		map.put("start", start);
		map.put("write", write);
		map.put("read", read);
		map.put("success",true);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	public void printNumber(){
		List<String> studyNoList = new ArrayList<String>();
		String[] strarray = studyNo.split(",");	
		for (int j = 0; j < strarray.length; j++) {
			studyNoList.add(strarray[j].trim());
		}
		List<String> list = tblStudyItemService.selectPrintNumber(studyNoList);
		String studyNolist = "";
		Map<String,Object> map = new HashMap<String,Object>();
		if(list != null && list.size() > 0){
			for(String str:list){
				if(!studyNolist.equals("")){
					studyNolist = studyNolist + ";";
				}
				studyNolist = studyNolist + str;
			}
			map.put("success", false);
			map.put("studyNolist", studyNolist);
		}else{
			map.put("success", true);
		}
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		
	}
	
	/**转到任命书显示页面*/
	public String ireport() throws Exception{
		List<String> studyNoList = new ArrayList<String>();
		studyNoList.add(studyNo);
		tblStudyItemService.addPrintNumber(studyNoList);
		return "ireport";
	}
	
	/** out 打印任命书*/
	public String outport() throws Exception{
		
		//参数
		Map<String,Object> paraMap = new HashMap<String, Object>();
		
		URL url = ServletActionContext.getServletContext().getResource("/jasperReport/"+"logo.jpg");

		paraMap.put("logoImage", url);
		//结果集
		String[] studyNos = studyNo.split(",");
		//List<Map<String,Object>> mapList = tblStudyItemService.getMapListForImprot(studyNo);
		List<String> studyNoList = new ArrayList<String>();
		for(String s : studyNos)
			studyNoList.add(s);
		List<Map<String,Object>> mapList = tblStudyItemService.getMapMoreListForImprot(studyNoList);
		
		ReportMap.getInstance(request).addCompanyInfoIntoMap(paraMap);
		String fileName = "SD任命书";
		ActionContext.getContext().put("paraMap", paraMap);
		ActionContext.getContext().put("mapList", mapList);
		ActionContext.getContext().put("fileName", fileName);
		
		return "outport";
	}
	
	
	
	/**
	 * 高级查询
	 * @return
	 */
	public String highGradeQuery(){
		
		return "highGradeQuery";
	}
	/**
	 * 根据条件 进行查询（高级查询）
	 */
	public void loadHighGradeQuery(){
		int total = 0;
		int tTotal = 0;
		int sTotal = 0;
		Map<String,Object> map = new HashMap<String,Object>();
		if( model.getContractCode().equals("") && model.getContractName().equals("") && 
			model.getSponsorName().equals("")&& model.getSponsorLinkman().equals("") &&
			model.getSponsorTel().equals("") && tiCode.equals("00") && tiNo.equals("") && 
			tiName.equals("") && studyNo.equals("") && studyName.equals("") && sd.equals("") && glpFlag == -1
				//,model.getContractName(),model.getSponsorName(),model.getSponsorLinkman(),model.getSponsorTel(),
				//tiCode,tiNo,tiName,studyNo,studyName,sd,glpFlag
		){
			map.put("rows", "");
			map.put("success", false);
		}else{
			String operator = getCurrentRealName();
			Boolean  readAll =  (Boolean)ActionContext.getContext().getSession().get("read");//获取读的权限
			int readAllInt = 0;  //1:查看所有
			if(readAll){
				readAllInt  = 1;
			}
			List<HighGradeTreeGrid> highGradeTreeGridList = tblContractService.getHighGradeTreeGrid(operator,readAllInt,
					model.getContractCode(),model.getContractName(),model.getSponsorName(),model.getSponsorLinkman(),model.getSponsorTel(),
					tiCode,tiNo,tiName,studyNo,studyName,sd,glpFlag
					);
			
			if(null != highGradeTreeGridList){
				for(HighGradeTreeGrid obj:highGradeTreeGridList){
					switch (obj.getLevel()) {
					case 1:
						total++;
						break;
					case 2:
						tTotal++;
						break;
					case 3:
						sTotal++;
						break;

					default:
						break;
					}
				}
			}
			map.put("rows", highGradeTreeGridList);
			map.put("success", true);
		}
	
		map.put("total", total);
		map.put("tTotal", tTotal);
		map.put("sTotal", sTotal);
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
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
	public String getTiCode() {
		return tiCode;
	}
	public void setTiCode(String tiCode) {
		this.tiCode = tiCode;
	}
	public String getTiName() {
		return tiName;
	}
	public void setTiName(String tiName) {
		this.tiName = tiName;
	}
	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}
	public String getStudyName() {
		return studyName;
	}
	public String getStudyid() {
		return studyid;
	}
	public void setStudyid(String studyid) {
		this.studyid = studyid;
	}
	public String getStudyNo() {
		return studyNo;
	}
	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}
	public String getTiNo() {
		return tiNo;
	}
	public void setTiNo(String tiNo) {
		this.tiNo = tiNo;
	}
	public String getSd() {
		return sd;
	}
	public void setSd(String sd) {
		this.sd = sd;
	}
	public int getGlpFlag() {
		return glpFlag;
	}
	public void setGlpFlag(int glpFlag) {
		this.glpFlag = glpFlag;
	}
	
}
