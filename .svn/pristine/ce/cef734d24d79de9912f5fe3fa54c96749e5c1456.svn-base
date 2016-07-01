package com.lanen.view.action;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.qa.QAFileRegReader;
import com.lanen.model.qa.QALearnTask;
import com.lanen.service.UserService;
import com.lanen.service.qa.QAFileRegReaderService;
import com.lanen.service.qa.QAFileRegService;
import com.lanen.service.qa.QALearnTaskFileService;
import com.lanen.service.qa.QALearnTaskService;
import com.lanen.util.DateUtil;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class QAFileRegReaderAction extends BaseAction<QAFileRegReader>{

	private static final long serialVersionUID = -8233456755925575903L;
	@Resource
	private QALearnTaskFileService qALearnTaskFileService;
	@Resource
	private QAFileRegService qAFileRegService;
	@Resource
	private QAFileRegReaderService qAFileRegReaderService;
	@Resource
	private QALearnTaskService qALearnTaskService;
	@Resource
	private UserService userService;
	
	private String fileRegId;
	
	private String learnTaskId;
	private String student;
	private String studentList;
	
	
	public void hasAddedComment()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) ActionContext.getContext().getSession().get("user");
		QAFileRegReader reader = qAFileRegReaderService.getByFileRegIdAndUser(user,fileRegId);
		if(reader==null)
		{
			map.put("success", false);
			map.put("msg","当前用户没有该文件的学习任务");		
		}else {
			if(reader.getRemark()==null||"".equals(reader.getRemark()))
			{
				map.put("success", false);
				map.put("msg","该文件你已经添加评论！");
			}
			else {	
				map.put("success", true);
				map.put("learnRecordId", reader.getLearnRecordId());
			}
			
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void saveComment()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		QAFileRegReader reader = qAFileRegReaderService.getById(model.getLearnRecordId());
		reader.setRemark(model.getRemark());
		qAFileRegReaderService.update(reader);
		map.put("success", true);
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void getCommentByFileRegId()
	{
		List<QAFileRegReader> reader = qAFileRegReaderService.getByFileRegId(fileRegId);
		
	}
	public void getReadersByTaskId()
	{
		List<QAFileRegReader> readers = qAFileRegReaderService.getByTask(learnTaskId);
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		for(QAFileRegReader reader:readers)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("taskState", reader.getLearnState());
			map.put("learnRecordId", reader.getLearnRecordId());
			map.put("readerName", reader.getReaderName());
			map.put("readerCode", reader.getReaderCode());
			map.put("learnState", reader.getLearnState());
			map.put("createTime", DateUtil.dateToString(reader.getCreateTime(),"yyyy-MM-dd"));
			map.put("planFinishTime", DateUtil.dateToString(reader.getPlanFinishTime(),"yyyy-MM-dd"));
			map.put("finishTime", DateUtil.dateToString(reader.getFinishTime(),"yyyy-MM-dd"));
			map.put("remark", reader.getRemark());
			
			mapList.add(map);
		}
	   
		/*String[] _nory_format = {"learnRecordId", "readerName","readerCode","learnState","createTime",
				"planFinishTime","finishTime","remark"};*/
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
	}
	public void del()
	{
		
		qAFileRegReaderService.delete(model.getLearnRecordId().split(",")[0]);
		
	}
	public void getListByStudent()
	{
		List<User> users = userService.findByPrivilegeName2(student);
		
		String[] _nory_format = {"userName", "realName","id",};
		String json = JsonPluginsUtil.beanListToJson(users, _nory_format, true);
		writeJson(json);
	}
	public void saveStudents()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = false;
		QALearnTask task = qALearnTaskService.getById(learnTaskId);
		
		List<User> users = userService.getByIds(studentList.split(","));
		for(User user:users)
		{
			boolean isExist = qAFileRegReaderService.isExistByTaskAndReader(learnTaskId,user.getRealName());
			if(!isExist)
			{
				QAFileRegReader reader = new QAFileRegReader();
				reader.setCreateTime(DateUtil.getTodayDate());
				String recordKey = qAFileRegReaderService.getKey("QAFileRegReader");
				reader.setLearnRecordId(recordKey);
				reader.setLearnState(0);
			//	reader.setPlanFinishTime(DateUtil.stringToDate(planFinishTime,"yyyy-MM-dd"));
				
				reader.setQalearnTask(task);
				reader.setReaderCode(user.getUserName());
				reader.setReaderName(user.getRealName());
				qAFileRegReaderService.save(reader);
			}else {
				flag = true;
			}
			
		}
		map.put("flag", flag);
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	
	public QALearnTaskFileService getqALearnTaskFileService() {
		return qALearnTaskFileService;
	}

	public void setqALearnTaskFileService(
			QALearnTaskFileService qALearnTaskFileService) {
		this.qALearnTaskFileService = qALearnTaskFileService;
	}




	public QAFileRegService getqAFileRegService() {
		return qAFileRegService;
	}




	public void setqAFileRegService(QAFileRegService qAFileRegService) {
		this.qAFileRegService = qAFileRegService;
	}
	public QAFileRegReaderService getqAFileRegReaderService() {
		return qAFileRegReaderService;
	}
	public void setqAFileRegReaderService(
			QAFileRegReaderService qAFileRegReaderService) {
		this.qAFileRegReaderService = qAFileRegReaderService;
	}
	public QALearnTaskService getqALearnTaskService() {
		return qALearnTaskService;
	}
	public void setqALearnTaskService(QALearnTaskService qALearnTaskService) {
		this.qALearnTaskService = qALearnTaskService;
	}


	public String getFileRegId() {
		return fileRegId;
	}


	public void setFileRegId(String fileRegId) {
		this.fileRegId = fileRegId;
	}
	public String getLearnTaskId() {
		return learnTaskId;
	}
	public void setLearnTaskId(String learnTaskId) {
		this.learnTaskId = learnTaskId;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String getStudentList() {
		return studentList;
	}
	public void setStudentList(String studentList) {
		this.studentList = studentList;
	}
	
	
}
