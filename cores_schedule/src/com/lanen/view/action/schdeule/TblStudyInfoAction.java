package com.lanen.view.action.schdeule;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.ComboTreeModel;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.contract.TblNotification;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.schedule.TblAnimalHouse;
import com.lanen.model.schedule.TblResManager;
import com.lanen.model.schedule.TblSchedulePlan;
import com.lanen.model.schedule.TblStudyInfo;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.contract.TblTestItemService;
import com.lanen.service.schdeule.TblAnimalHouseService;
import com.lanen.service.schdeule.TblResManagerService;
import com.lanen.service.schdeule.TblSchedulePlanService;
import com.lanen.service.schdeule.TblStudyInfoService;
import com.lanen.service.studyplan.DictStudyTypeService;
import com.lanen.util.DateUtil;

@Controller
@Scope("prototype")
public class TblStudyInfoAction extends BaseAction<TblStudyInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private TblStudyInfoService tblStudyInfoService;
	
	@Resource
	private TblAnimalHouseService tblAnimalHouseService;
	
	@Resource
	private TblSchedulePlanService tblSchedulePlanService;
	
	@Resource
	private TblResManagerService tblResManagerService;
	
	@Resource
	protected DictStudyTypeService dictStudyTypeService;
	
	/**试验项目（委托项目）service*/
	@Resource
	private TblStudyItemService tblStudyItemService;
	
	/**
	 * 供试品
	 */
	@Resource
	private TblTestItemService tblTestItemService;
	
	/**通知信息*/
	@Resource
	private TblNotificationService tblNotificationService;
	
	public void loadRes(){
		List<ComboTreeModel> list= tblStudyInfoService.loadAnimalHouseTable();
		System.out.println("----json--");
		String json = JSONArray.fromObject(list).toString();// 转化为JSON
		System.out.print(json);
		writeJson(json);
	}
	
	public void initTblStudyInfoBut(){
		TblStudyInfo tblStudyInfo = tblStudyInfoService.getByStudyNo(model.getStudyNo());
		Map<String,Object> map = new HashMap<String,Object>();
		if(null == tblStudyInfo){
			 map.put("success",false);
		}else{
			 map.put("success",true);
			 TblAnimalHouse tblAnimalHouse = tblAnimalHouseService.getById(tblStudyInfo.getResID());
			 TblAnimalHouse ptblAnimalHouse = tblAnimalHouseService.getById(tblAnimalHouse.getParentId());
			 map.put("resName",ptblAnimalHouse.getResName()+" - "+tblAnimalHouse.getResName());
			 if(null == tblStudyInfo.getScheduleReviewSignID() || tblStudyInfo.getScheduleReviewSignID().equals("") ){
				 map.put("sign", false);
			 }else{
				 map.put("sign", true);
			 }
		}
       
		String json= JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	public void saveRes(){
		TblStudyInfo studyInfo = new TblStudyInfo();
		TblStudyInfo tblStudyInfo = tblStudyInfoService.getByStudyNo(model.getStudyNo());
		if(null == tblStudyInfo ){
			String id = tblStudyInfoService.getKey();
			studyInfo.setId(id);
			studyInfo.setResID(model.getResID());
			studyInfo.setStudyNo(model.getStudyNo());
			tblStudyInfoService.save(studyInfo);
		}else{
			tblStudyInfo.setResID(model.getResID());
			tblStudyInfo.setStudyNo(model.getStudyNo());
			tblStudyInfoService.update(tblStudyInfo);
			List<TblSchedulePlan> planlist = tblSchedulePlanService.getSchedulePlanList(2, tblStudyInfo.getStudyNo(), 2);
			//TODO 发消息
			//当前时间
			String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");
			TblAnimalHouse tblAnimalHouse = tblAnimalHouseService.getById(tblStudyInfo.getResID());
			TblAnimalHouse ptblAnimalHouse = tblAnimalHouseService.getById(tblAnimalHouse.getParentId());
			TblStudyItem studyItem =tblStudyItemService.getByStudyNoStudyItem(studyInfo.getStudyNo());
			String tiNo = studyItem.getTiNo();
			String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
			DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
			String studyNoName = "";
			if(dictStudyType.getAnimalHave() == 1){
				studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
			}else{
				studyNoName = testItemName+studyItem.getStudyName();
			}
			if(planlist != null){
				String sd = planlist.get(0).getCreater();
				//通知消息 给SD 
				TblNotification tblNotification = new TblNotification();
				tblNotification.setMsgTitle("专题"+tblStudyInfo.getStudyNo()+"日程安置区域更改提醒！");//消息头
				//String msgContent = "SD，您好<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				String msgContent = "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				msgContent = msgContent+"试验系统部负责人于"+currentDate+"，将您所提交"+
				"专题编号:　 "+tblStudyInfo.getStudyNo()+"专题名称:　"+studyNoName+"　的日程，安置区域更改在（"
				+ptblAnimalHouse.getResName()+" - "+tblAnimalHouse.getResName()+"）区域下，特此提醒！";
				tblNotification.setMsgContent(msgContent);
				tblNotification.setMsgType(1);//系统消息
				tblNotification.setSender(getCurrentRealName());// 发送者
				tblNotification.setSendTime(new Date());// 发送时间
				List<String>  sdlist = new ArrayList<String>();
			
				sdlist.add(sd);
				tblNotificationService.save(tblNotification,sdlist);
				
			}
			
			List<TblResManager> managerList = tblResManagerService.getByHouseId(tblStudyInfo.getResID());
			List<String>  receiverList = new ArrayList<String>();
			for(TblResManager manager:managerList){
				receiverList.add(manager.getResManager());
			}
			
			//通知消息 给SD 和区域负责人
			TblNotification tblNotification = new TblNotification();
			tblNotification.setMsgTitle("专题"+tblStudyInfo.getStudyNo()+"日程区域安置提醒！");//消息头
			//String msgContent = "区域负责人，您好<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			String msgContent = "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			msgContent = msgContent+"试验系统部负责人于"+currentDate+"将"+
			"专题编号:　 "+tblStudyInfo.getStudyNo()+"专题名称:　"+studyNoName+"　的日程，安置在你所负责的区域 （"
			+ptblAnimalHouse.getResName()+" - "+tblAnimalHouse.getResName()+"），请为此专题日程安排具体房间，特此提醒！";
			tblNotification.setMsgContent(msgContent);
			tblNotification.setMsgType(1);//系统消息
			tblNotification.setSender(getCurrentRealName());// 发送者
			tblNotification.setSendTime(new Date());// 发送时间
			tblNotificationService.save(tblNotification,receiverList);

		}
		Map<String,Object> map = new HashMap<String,Object>();
        map.put("success",true);
		String json= JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	/**
	 * 判断是否签字
	 */
	public void scheduleHaveReviewSignID(){
		TblStudyInfo tblStudyInfo = tblStudyInfoService.getByStudyNo(model.getStudyNo());
		Map<String,Object> map = new HashMap<String,Object>();
		if(null == tblStudyInfo){
			 map.put("success",false);
		}else{
			String signId = tblStudyInfo.getScheduleReviewSignID();
			if(null == signId){
				map.put("success",false);
			}else{
				map.put("success",true);
			}
		}
		String json= JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	

}
