package com.lanen.view.action.contract;

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
import com.lanen.model.User;
import com.lanen.model.contract.TblNotification;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TblNotificationAction extends BaseAction<TblNotification> {
	
	@Resource
    private TblNotificationService tblNotificationService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Date startDate;
	private Date endDate;
	
	/** 列表 */
	public String list() throws Exception {
		String currentDateStr = DateUtil.getNow("yyyy-MM-dd");
		String oneMonthAgoDateStr = DateUtil.getDateMonthAgo(-1);
		
		ActionContext.getContext().put("startDate", oneMonthAgoDateStr);
		ActionContext.getContext().put("endDate", currentDateStr);
		return "list";
	}
	/**
	 * 加载发送人列表,去重
	 */
	public void loadAllSender(){
		List<Map<String,String>> mapList =null;
		User user =  getCurrentUser();
		if(null != user){
			mapList = tblNotificationService.getAllSenderByReceiver(user.getUserName());
		}
		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(jsonStr);
	}
    /**加载数据*/
    public void loadList()throws Exception{
    	User user= getCurrentUser();
    	List<TblNotification> list=null;
    	String sender = model.getSender();
    	if(null != user && null != startDate && null != endDate){
    		String receiver = user.getUserName();
    		list=tblNotificationService.getListBySenderReceiverDate(receiver,sender,startDate,endDate);
    		if(null != list && list.size()>0){
    			List<String> idList = new ArrayList<String>();
    			for(TblNotification obj:list){
    				if(null == obj.getRecTime()){
    					idList.add(obj.getId());
    				}
    			}
    			if(idList.size()>0){
    				tblNotificationService.setRecTime(idList,new Date());
    			}
    		}
    	}
    	
    	Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", list);
		map.put("total", list.size());
		String json = JsonPluginsUtil.beanToJson(map,"yyyy-MM-dd hh:mm");
		writeJson(json);
    }
    /**查看信息*/
    public String view() throws Exception{
    	if(null != model.getId() && !"".equals(model.getId())){
    		TblNotification tblNotification = tblNotificationService.getById(model.getId());
    		if(null != tblNotification ){
    			//已读
    			tblNotification.setReadFlag(1);
    			tblNotificationService.update(tblNotification);
    			
    			ActionContext.getContext().getValueStack().push(tblNotification);
    			DateUtil.getWeekDay(tblNotification.getSendTime());
    			ActionContext.getContext().put("week", "星期"+DateUtil.getWeekDay(tblNotification.getSendTime()));
    		}
    	}
    	User  user = (User)ActionContext.getContext().getSession().get("user");
    	boolean isFM = userService.checkPrivilege(user,"FM");
    	boolean isSD = userService.checkPrivilege(user,"SD");
    	ActionContext.getContext().put("hasQATab",false);
    	if(isFM||isSD){
    		ActionContext.getContext().put("hasQATab",true);
    	}
    	return "view";
    }
    
    /**加载新通知数量，并接收新通知，更新接收时间*/
    public void getReceiveMail() {
    	
    	List<String> list=tblNotificationService.getUnRecMail(getCurrentUser() != null?getCurrentUser().getUserName():"" );
    	Map<String,Object> map = new HashMap<String,Object>();
    	if(list.size()==0){
    	   map.put("unRecCount", list.size());
    	}else{
    		map.put("unRecCount", list.size());
    		for(String id:list){
    			TblNotification tblNotification=tblNotificationService.getById(id);
    			Date date=new Date();
    			tblNotification.setRecTime(date);
    			tblNotificationService.update(tblNotification);
    		}
    	}
    	String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
    }
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
    
}
