package com.lanen.view.action;

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
import com.lanen.model.qa.QAFileReg;
import com.lanen.model.qa.QAFileRegReader;
import com.lanen.model.qa.QAFileType;
import com.lanen.model.qa.QALearnTask;
import com.lanen.model.qa.QALearnTaskFile;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.qa.QAFileRegReaderService;
import com.lanen.service.qa.QAFileRegService;
import com.lanen.service.qa.QALearnTaskFileService;
import com.lanen.service.qa.QALearnTaskService;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class QALearnTaskFileAction extends BaseAction<QALearnTaskFile>{

	private static final long serialVersionUID = -4257945300990943579L;
	@Resource
	private QALearnTaskFileService qALearnTaskFileService;
	@Resource
	private QAFileRegService qAFileRegService;
	@Resource
	private QAFileRegReaderService qAFileRegReaderService;
	@Resource
	private QALearnTaskService qALearnTaskService;
	
	private String learnTaskId;
	private String fileList;
	
	public void list()
	{
		Map<String,Object> returnMap =new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		List<QALearnTaskFile> taskFiles = qALearnTaskFileService.findAll();
		
		for(QALearnTaskFile file:taskFiles)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("learnTaskId", file.getQalearnTask().getLearnTaskId());
			boolean flag = false;
			//task显示时间和学生
			for(Map<String, Object> existMap:mapList)
			{
				if(existMap.get("learnTaskId").equals(file.getQalearnTask().getLearnTaskId()))
				{
					flag=true;
				}
			}
			if(!flag)
			{
				String taskLabel = DateUtil.dateToString(file.getQalearnTask().getCreateTime(),"yyyy-MM-dd")+"  "+file.getQalearnTask().getStudent();
				map.put("fileName", taskLabel);
				map.put("learnTaskId", file.getQalearnTask().getLearnTaskId());//对于任务是任务id
				map.put("learnState", file.getQalearnTask().getLearnState());
				//0：未提交；1：学习中（已提交）；2：完成
				
				map.put("purpose",file.getQalearnTask().getPurpose());
				map.put("operate","删除");
				
				map.put("student",file.getQalearnTask().getStudent());
				map.put("createTime", DateUtil.dateToString(file.getQalearnTask().getCreateTime(),"yyyy-MM-dd"));
				mapList.add(map);
				map = new HashMap<String, Object>();//用来加下面的file
			}
			map.put("learnState", file.getQalearnTask().getLearnState());
			
			map.put("learnTaskFileId",file.getLearnTaskFileId());
			QAFileReg qAFileReg = qAFileRegService.getById(file.getFileRegId());
			map.put("learnTaskId","file"+qAFileReg.getFileRegId());//对于file没有，是fileId
			map.put("fileRegId", qAFileReg.getFileRegId());
			map.put("fileName", qAFileReg.getFileName());
			map.put("createTime", DateUtil.dateToString(file.getQalearnTask().getCreateTime(),"yyyy-MM-dd"));
			map.put("operate","删除");
			map.put("_parentId", file.getQalearnTask().getLearnTaskId());//父节点是task
			mapList.add(map);
		}
		returnMap.put("rows",mapList);
		String json=JsonPluginsUtil.beanToJson(returnMap);
		writeJson(json);
	}
	public void del()
	{
		qALearnTaskFileService.delete(model.getLearnTaskFileId());
	}
	public void confirmLearnFinish()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) ActionContext.getContext().getSession().get("user");
		QAFileRegReader reader = qAFileRegReaderService.getByFileRegIdAndUser(user,model.getFileRegId());
		if(reader==null)
		{
			map.put("success", false);
			map.put("msg","当前用户没有该文件的学习任务");
		}else {
			if(reader.getLearnState()==2)
			{
				map.put("success", false);
				map.put("msg","该学习任务已经确认过学习完成！");
			}else {
				reader.setLearnState(2);//学习完成
				reader.setFinishTime(DateUtil.getTodayDate());
				
				qAFileRegReaderService.update(reader);
				map.put("success", true);
				//判断该任务下的所有文件是否都已经学习完成
				
				boolean isFinish = qALearnTaskService.isFinishedByTask(reader.getQalearnTask());
				if(isFinish)//任务下的所有学习者都学习完成了制动更新为完成
				{
					QALearnTask task=reader.getQalearnTask();
					task.setLearnState(2);
					qALearnTaskService.update(task);
				}
			}
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public void getFileListByTaskId()
	{
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		List<QALearnTaskFile> files = qALearnTaskFileService.getFileListByTaskId(learnTaskId);
		for(QALearnTaskFile file:files)
		{
			/*QAFileReg oneFileReg = qAFileRegService.getById(file.getFileRegId());
			if(oneFileReg!=null)
			{
				Map<String, Object> map = new HashMap<String, Object>();
				// fileRegId; ;fileType;//1：法规；2：指导原则；3：SOP
			  //   fileTypeName; fileCode; fileName;fileVersion; filePublishTime;filePublishDepartment;remark;isVersionUpdate;
				map.put("learnTaskFileId", file.getLearnTaskFileId());
				map.put("fileRegId", oneFileReg.getFileRegId());
				map.put("fileType", oneFileReg.getFileType());
				map.put("fileTypeName", oneFileReg.getFileTypeName());
				map.put("fileCode", oneFileReg.getFileCode());
				map.put("fileName", oneFileReg.getFileName());
				map.put("fileVersion", oneFileReg.getFileVersion());
				map.put("filePublishTime",oneFileReg.getFilePublishTime());
				map.put("filePublishDepartment", oneFileReg.getFilePublishDepartment());
				map.put("remark", oneFileReg.getRemark());
				map.put("isVersionUpdate", oneFileReg.getIsVersionUpdate());
				map.put("operate", "删除");
				map.put("taskState", file.getQalearnTask().getLearnState());
			
				mapList.add(map);
			}else{*/
				Map<String, Object> map = new HashMap<String, Object>();
				// fileRegId; ;fileType;//1：法规；2：指导原则；3：SOP
			  //   fileTypeName; fileCode; fileName;fileVersion; filePublishTime;filePublishDepartment;remark;isVersionUpdate;
				map.put("learnTaskFileId", file.getLearnTaskFileId());
				map.put("fileRegId", file.getFileRegId());
				map.put("fileType", file.getFileType());
				map.put("fileTypeName", file.getFileTypeName());
				map.put("fileCode", file.getFileCode());
				map.put("fileName", file.getFileName());
				map.put("fileVersion", file.getFileVersion());
				map.put("filePublishTime",file.getFilePublishTime());
				map.put("filePublishDepartment", file.getFilePublishDepartment());
				map.put("remark", file.getRemark());
				map.put("isVersionUpdate", file.getIsVersionUpdate());
				map.put("operate", "删除");
				map.put("taskState", file.getQalearnTask().getLearnState());
			
				mapList.add(map);
			//}
		}
		
		writeJson(JsonPluginsUtil.beanListToJson(mapList));
	}
	public void saveFileList()
	{
		QALearnTask  task = qALearnTaskService.getById(learnTaskId);
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = false;
		String[] fileRegs = fileList.split(",");
		List<QAFileReg> fileRegList = qAFileRegService.getByIds(fileRegs);
		
		for(QAFileReg fileReg:fileRegList)
		{
			boolean isExist = qALearnTaskFileService.isExistByTaskAndFile(learnTaskId,fileReg.getFileRegId());
			if(!isExist)
			{
				QALearnTaskFile taskFile = new QALearnTaskFile();
				String taskFileKey = qALearnTaskFileService.getKey("QALearnTaskFile");
				taskFile.setLearnTaskFileId(taskFileKey);
				taskFile.setFileRegId(fileReg.getFileRegId());
				taskFile.setQalearnTask(task);
				
				taskFile.setFileCode(fileReg.getFileCode());
				taskFile.setFileName(fileReg.getFileName());
				taskFile.setFilePublishDepartment(fileReg.getFilePublishDepartment());
				taskFile.setFilePublishTime(fileReg.getFilePublishTime());
				taskFile.setFileType(fileReg.getFileType());
				taskFile.setFileTypeId(fileReg.getQafileType().getFileTypeId());
				taskFile.setFileTypeName(fileReg.getFileTypeName());
				taskFile.setFileVersion(fileReg.getFileVersion());
				taskFile.setIsVersionUpdate(fileReg.getIsVersionUpdate());
				taskFile.setRemark(fileReg.getRemark());
				
				qALearnTaskFileService.save(taskFile);
				
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
	public String getLearnTaskId() {
		return learnTaskId;
	}
	public void setLearnTaskId(String learnTaskId) {
		this.learnTaskId = learnTaskId;
	}
	public String getFileList() {
		return fileList;
	}
	public void setFileList(String fileList) {
		this.fileList = fileList;
	}
	
	
}
