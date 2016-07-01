package com.lanen.view.action.qa;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.qa.QAChkReportRecord;
import com.lanen.service.qa.QAChkReportRecordService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class QAChkReportRecordAction extends BaseAction<QAChkReportRecord>{

	private static final long serialVersionUID = -4902970405921009550L;
	
	@Resource
	private QAChkReportRecordService qAChkReportRecordService;
	
	private String replyContent;

	public void saveReplyMessage()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		QAChkReportRecord record = qAChkReportRecordService.getById(model.getChkReportRecordId());
		record.setReplyContent(model.getReplyContent());
		qAChkReportRecordService.update(record);
		
		map.put("success", true);
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void saveDelayMessage()
	{
		User user = (User)ActionContext.getContext().getSession().get("user");
		Map<String, Object> map = new HashMap<String, Object>();
		QAChkReportRecord record = qAChkReportRecordService.getById(model.getChkReportRecordId());
		record.setDelayPlanFinishDate(model.getDelayPlanFinishDate());
		//record.setDelayDesc(model.getDelayDesc());
		record.setDelayRsn(model.getDelayRsn());
		record.setDelayPlanFinishDate(model.getDelayPlanFinishDate());
		record.setNeedDelay(1);//0：未申请；1：已申请
		record.setDelaySd(user.getRealName());
		record.setDelayDesc("检查项、检查结果");//默认检查项、检查结果
		
		qAChkReportRecordService.update(record);
		
		map.put("success", true);
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void remodeDelay()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		QAChkReportRecord record = qAChkReportRecordService.getById(model.getChkReportRecordId());
		
		record.setDelayRsn(null);
		record.setDelayPlanFinishDate(null);
		record.setNeedDelay(null);//0：未申请；1：已申请
		
		qAChkReportRecordService.update(record);
		
		map.put("success", true);
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	
	public QAChkReportRecordService getqAChkReportRecordService() {
		return qAChkReportRecordService;
	}

	public void setqAChkReportRecordService(
			QAChkReportRecordService qAChkReportRecordService) {
		this.qAChkReportRecordService = qAChkReportRecordService;
	}




	public String getReplyContent() {
		return replyContent;
	}




	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

}
