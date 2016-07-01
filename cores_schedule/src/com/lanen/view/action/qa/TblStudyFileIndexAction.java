package com.lanen.view.action.qa;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.contract.TblNotification;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.qa.QAStudyChkIndex;
import com.lanen.model.qa.TblStudyFile;
import com.lanen.model.qa.TblStudyFileDis;
import com.lanen.model.qa.TblStudyFileIndex;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.contract.TblTestItemService;
import com.lanen.service.qa.QAStudyChkIndexService;
import com.lanen.service.qa.TblStudyFileDisService;
import com.lanen.service.qa.TblStudyFileIndexService;
import com.lanen.service.qa.TblStudyFileService;
import com.lanen.service.studyplan.DictStudyTypeService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TblStudyFileIndexAction extends BaseAction<TblStudyFileIndex>{

	private static final long serialVersionUID = -2378797888943341845L;
	
	@Resource
	private TblStudyFileIndexService tblStudyFileIndexService;
	@Resource
	private TblStudyFileService tblStudyFileService;
	@Resource
	private TblESService  tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblStudyFileDisService tblStudyFileDisService;
	@Resource
	private QAStudyChkIndexService qAStudyChkIndexService;
	@Resource
	private TblStudyItemService tblStudyItemService;
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	@Resource
	private TblNotificationService tblNotificationService;
	@Resource
	private UserService userService;
	@Resource
	private TblTestItemService tblTestItemService;
	@Resource
	private DictStudyTypeService dictStudyTypeService;
	
	
	private String password;
	private String readers;
	private Integer result;
	private String canUploadFile;
	
	private String msgTitle;
	private String msgContent;
	private String receiverList;
	
	public String studyProgram()
	{
		ActionContext.getContext().getSession().put("studyNoPara", model.getStudyNo());
		ActionContext.getContext().getSession().put("fileType", model.getFileType());
		
		return "studyProgram";
	}
	@SuppressWarnings("unchecked")
	public void getStudyFileByStudyNo()
	{
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		TblStudyFileIndex index = tblStudyFileIndexService.getByStudyNoAndFileType(model.getStudyNo(),model.getFileType());
		if(index!=null)
		{
			//Set<TblStudyFile> files = index.getTblStudyFiles();
			List<TblStudyFile> files = tblStudyFileService.getByFileIndexId(index.getStudyFileIndexId());
			for(TblStudyFile file:files)
			{
				Map<String, Object> map = new HashMap<String, Object>();
				//fileType 1：方案；2：报告；
				map.put("studyFileIndexId", index.getStudyFileIndexId());
				map.put("fileType", index.getFileType());
				map.put("fileState", index.getFileState());//0：草稿；1：提交审批中；2：结束
				
				//	Map<String, Object> child = new HashMap<String, Object>();
				//child.put("attachmentFileString",file.getAttachmentFile());
				map.put("id", file.getId());
				map.put("fileVersion", file.getFileVersion());
				map.put("attachmentDesc", file.getAttachmentDesc());
				map.put("submitTime", DateUtil.dateToString(file.getSubmitTime(),"yyyy-MM-dd HH:mm"));
				map.put("attachmentName", file.getAttachmentName());
				map.put("delFlag", file.getDelFlag());//0有效，1删除
				map.put("downLoad", "aaa");
				int flag=0;
				if((file.getTblStudyFileIndex().getFileState()!=null&&file.getTblStudyFileIndex().getFileState()==0)//还没提交
						||(file.getTblStudyFileIndex().getChangeFlag()!=null&&file.getTblStudyFileIndex().getChangeFlag()==2))//批准修改
				{
					flag=1;
				}
					
				map.put("operate", flag);
				mapList.add(map);
				
			}
	
		}
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
		
	}
	public void applyChange()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		//if(user.getPassword().equals( DigestUtils.md5Hex(password)))
		//{
			//签名链接
			TblESLink esLink = new TblESLink();
			//电子签名
			TblES es = new TblES();
			//验证通过则进行一下操作
			String typeMessage = "";
			Integer typeInteger = 806;
			if( model.getFileType()==1)
			{
				typeMessage="专题方案变更申请";
				typeInteger = 806;
			}
			if( model.getFileType()==2)
			{
				typeMessage="专题报告变更申请";
				typeInteger = 807;
			}
			User tempUser = (User) ActionContext.getContext().getSession().get("user");
			es.setSigner(tempUser.getRealName());
			es.setEsTypeDesc(typeMessage);
			es.setEsType(typeInteger);
			es.setDateTime(new Date());
			String eid = tblESService.getKey("TblES");
			es.setEsId(eid);
		
			tblESService.save(es);

			//typeMessage+=",专题编号："+model.getStudyNo();
			TblStudyFileIndex index = tblStudyFileIndexService.getByStudyNoAndFileType(model.getStudyNo(), model.getFileType());
			
			esLink.setTableName("TblStudyFileIndex");
			esLink.setDataId(index.getStudyFileIndexId());
			esLink.setTblES(es);
			esLink.setEsType(typeInteger);
			esLink.setEsTypeDesc(typeMessage+"签字确认");
			esLink.setRecordTime(new Date());
			esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			tblESLinkService.save(esLink);
			
			index.setChangeFlag(1);//0：未申请；1：已申请；2：已批准；-2：未批准。每次提交时，记录提交记录。
			index.setChangeTime(new Date());
			index.setChangeRsn(model.getChangeRsn());
			
			tblStudyFileIndexService.update(index);
			
			map.put("success", true);
		
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	//查看方案状态
	public void viewQAStudyIndex()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		TblStudyPlan studyPlan = tblStudyPlanService.getByStudyNo(model.getStudyNo());
		
		
		QAStudyChkIndex qAStudyChkIndex = qAStudyChkIndexService.getByStudyNo(model.getStudyNo());
		if(qAStudyChkIndex==null||(qAStudyChkIndex!=null&&(qAStudyChkIndex.getChkPlanFinishFlag()==null||qAStudyChkIndex.getChkPlanFinishFlag()==0)
				&&(qAStudyChkIndex.getReportState()==null||qAStudyChkIndex.getReportState()==0)))
		{
			if(qAStudyChkIndex!=null)
			{
				if(qAStudyChkIndex.getStudyPlanState()!=null&&qAStudyChkIndex.getStudyPlanState()==1)//方案报告审批完了
				{
					map.put("canApproval", false);	
					//方案审批通过，并且日程没有提交的情况
					if(qAStudyChkIndex.getScheduleState()!=null&&qAStudyChkIndex.getScheduleState()==1)
					{
						map.put("canCommitSchedule", false);
					}else {
						map.put("canCommitSchedule", true);
					}
					
					if(studyPlan!=null&&(studyPlan.getScheduleState()==null||(studyPlan.getScheduleState()!=null&&(studyPlan.getScheduleState()==0||studyPlan.getScheduleState()==3))))
					{
						map.put("canCommitSchedule", true);
					}
					
					map.put("canFileDis", true);	
				}
				
			}
			//最多两个，一个方案，一个报告
			TblStudyFileIndex index = tblStudyFileIndexService.getByStudyNoAndFileType(model.getStudyNo(), model.getFileType());
			if(index!=null)
			{
				
				boolean fileFlag = false;
				List<TblStudyFile> files=tblStudyFileService.getByFileIndexId(index.getStudyFileIndexId());
				if(files!=null&&files.size()>0)//有文件存在
				{
					//Set<TblStudyFile> files = index.getTblStudyFiles();
					for(TblStudyFile oneFile:files)
					{
						if(oneFile.getDelFlag()==null||oneFile.getDelFlag()==0)
						{
							fileFlag = true;
							break;
						}
					}
					/*if(fileFlag)
					{
						map.put("canFileDis", true);	
					}*/
				}
				if(index.getFileState()==null&&index.getFileState()==2//qam审批完毕
						&&qAStudyChkIndex!=null
						&&(qAStudyChkIndex.getStudyPlanState()!=null&&qAStudyChkIndex.getStudyPlanState()==0))//0：未完成；1：已完成
				{
					map.put("canApproval", true);	
				}
				if(index.getChangeFlag()!=null&&index.getChangeFlag()==1)
				{//提交过修改申请
					map.put("canApprovalChange",true);
				}
				if(index.getFileState()==null||(index.getFileState()==0)//0：草稿；1：提交QAM审批中；2：结束
					||(index.getChangeFlag()!=null&&index.getChangeFlag()==2)//0：未申请；1：已申请；2：已批准；-2：未批准。
					)//0：草稿；1：提交审批中；2：结束
				{
					if(fileFlag)//有有效文件存在
					{
						map.put("canCommit", true);//提交QAM
					}
					map.put("canUploadFile", true);
				}
				
				//提交过并且已经被qa接收
				if((index.getFileState()!=null&&index.getFileState()==1)
						&&(index.getConfirmer()!=null&&!"".equals(index.getConfirmer()))
						&&(index.getChangeFlag()==null||index.getChangeFlag()==0||index.getChangeFlag()==3)
						)//方案还没完成
					
				{
					//0：未申请；1：已申请；2：已批准；-2：未批准。3变更已处理 每次提交时，记录提交记录。
					//如果QA已经确认接收,并且没有申请过变更
					if(index.getFileType()==1)
					{
						if(qAStudyChkIndex!=null&&(qAStudyChkIndex.getReportState()==null||qAStudyChkIndex.getReportState()==0)
								&&(qAStudyChkIndex.getStudyPlanState()==null||qAStudyChkIndex.getStudyPlanState()==0))
						{
							map.put("canApplyChange", true);						
						}else {
							map.put("canApplyChange", false);
						}
					}else if(index.getFileType()==2)//报告
					{
						if(qAStudyChkIndex!=null&&
								(qAStudyChkIndex.getReportState()==null||qAStudyChkIndex.getReportState()==0))
						{
							map.put("canApplyChange", true);						
						}else {
							map.put("canApplyChange", false);
						}
					}
				}else {
					map.put("canApplyChange", false);
				}
				
			}else {
				//没有文件的情况下
				map.put("canCommit", false);
				map.put("canUploadFile", true);
			}
			
		}else {
			//report已经结束了
			map.put("canApplyChange", false);
			map.put("canCommit", false);
			map.put("canUploadFile", false);
			map.put("canApproval", false);
			map.put("canApprovalChange",false);
			map.put("canFileDis", false);
			map.put("canCommitSchedule", false);
		}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public void commitToQA()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
			//签名链接
			TblESLink esLink = new TblESLink();
			//电子签名
			TblES es = new TblES();
			//验证通过则进行一下操作
			String typeMessage = "";
			Integer typeInteger = 808;
			if( model.getFileType()==1)
			{
				typeMessage="专题方案提交QAM";
				typeInteger = 808;
			}
			if( model.getFileType()==2)
			{
				typeMessage="专题报告提交QAM";
				typeInteger = 809;
			}
			User tempUser = (User) ActionContext.getContext().getSession().get("user");
			es.setSigner(tempUser.getRealName());
			es.setEsTypeDesc(typeMessage);
			es.setEsType(typeInteger);
			es.setDateTime(new Date());
			String eid = tblESService.getKey("TblES");
			es.setEsId(eid);
		
			tblESService.save(es);

			//typeMessage+=",专题编号："+model.getStudyNo();
			TblStudyFileIndex index = tblStudyFileIndexService.getByStudyNoAndFileType(model.getStudyNo(), model.getFileType());
			
			esLink.setTableName("TblStudyFileIndex");
			esLink.setDataId(index.getStudyFileIndexId());
			esLink.setTblES(es);
			esLink.setEsType(typeInteger);
			esLink.setEsTypeDesc(typeMessage+"签字确认");
			esLink.setRecordTime(new Date());
			esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			tblESLinkService.save(esLink);
			
			if(index.getFileState()==1&&index.getChangeFlag()==2)
			{
				index.setChangeFlag(3);//3变更已处理
				index.setConfirmer(null);
				index.setConfirmTime(null);
				index.setCurVersion(index.getCurVersion()+1);
			}else {				
				index.setFileState(1);//0：草稿；1：提交QAM审批中；2：结束
			}
			index.setSubmitter(tempUser.getRealName());
			index.setSubmitTime(new Date());
			
			tblStudyFileIndexService.update(index);
			
			//qa专题检查索引
			QAStudyChkIndex qAStudyChkIndex = qAStudyChkIndexService.getByStudyNo(model.getStudyNo());
			TblStudyItem item =  tblStudyItemService.getByStudyNoStudyItem(model.getStudyNo());
			if(qAStudyChkIndex==null)
			{
				String sd = tblStudyItemService.getSDByStudyNo(model.getStudyNo());
				
				qAStudyChkIndex = new QAStudyChkIndex();
				
				qAStudyChkIndex.setStudyPlanState(0);//方案审批状态 0未完成 1完成
				
				//qAStudyChkIndex.setChkPlanAuthor(tempUser.getRealName());
				//qAStudyChkIndex.setChkPlanCurVersion(1);
				
				if(item!=null)
				{
					String qa = item.getQa();
					qAStudyChkIndex.setInspector(qa);
					qAStudyChkIndex.setInspectorAppointState(item.getQaState());//0：未任命；1：已任命
					qAStudyChkIndex.setInspectorAppointTime(item.getQaAppointDate());
				}
				qAStudyChkIndex.setReportState(0);//0：未完成；1：已完成
				if(sd!=null&&!"".equals(sd))
				{
					qAStudyChkIndex.setSd(sd);
				}else {
					qAStudyChkIndex.setSd(tempUser.getRealName());					
				}
				
				qAStudyChkIndex.setStudyNo(model.getStudyNo());
				
				//专题里面的日程信息
				TblStudyPlan tblStudyPlan = tblStudyPlanService.getByStudyNo(model.getStudyNo());
				if(tblStudyPlan!=null)
				{
					qAStudyChkIndex.setScheduleState(tblStudyPlan.getScheduleState());
				}
				qAStudyChkIndexService.save(qAStudyChkIndex);
				
			}else {
				if(qAStudyChkIndex.getSd()==null||"".equals(qAStudyChkIndex.getSd()))
				{
					qAStudyChkIndex.setSd(tempUser.getRealName());
					qAStudyChkIndexService.update(qAStudyChkIndex);
				}
			}
			TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(model.getStudyNo());
			String tiNo = studyItem.getTiNo();
			String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
			DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
			String studyNoName = "";
			if(null != dictStudyType &&  dictStudyType.getAnimalHave() == 1){
				studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
			}else{
				studyNoName = testItemName+studyItem.getStudyName();
			}
			//通知QAM
			String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");
			
			String msgTitle=(typeMessage+",专题编号：　"+model.getStudyNo()+"，专题名称："+studyNoName);
			String msgContent = "<br>";
			
			msgContent = msgContent+"SD("+tempUser.getRealName()+")于"+currentDate+" "+typeMessage+"，<br>专题编号：　"+model.getStudyNo()+"，<br>专题名称："+studyNoName+"  ，<br>特此提醒";
			
			//接收者列表
			String receiverList = "";
			//获取QAM
			List<User> qams = userService.findByPrivilegeName("QA负责人");
			if(qams!=null&&qams.size()>0)
			{
				receiverList+=(qams.get(0).getUserName())+",";
			}
			
			String qa = qAStudyChkIndex.getInspector();
			if(qa!=null)
			{
				User qaUser = userService.getByRealName(qa);
				receiverList+=(qaUser.getUserName())+",";
			}
			
			map.put("msgTitle", msgTitle);
			map.put("msgContent", msgContent);
			map.put("receiverList", receiverList);
			
			map.put("success", true);
		
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void getStudyFileDisPeople()
	{
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		
		//List<User> users = userService.findAll();
		List<User> users = userService.findByPrivilegeName("综合管理-登录");
		
		for(User user:users)
		{
			if(user.getDepartment()==null||tempUser.getId().equals(user.getId()))//被分发的人中不包括本人
			{
				continue;
			}
			String depName = user.getDepartment().getName();
			
			//department,realName,id
			boolean flag = false;
			Map<String, Object> parentMap = null;
			for(Map<String, Object> map:mapList)
			{
				if((user.getDepartment().getName()).equals(map.get("department")))
				{
					flag = true;
					parentMap=map;
					break;
				}
			}
			
			if(!flag)
			{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", depName.replaceAll("&", "_"));
				map.put("department", depName);
				map.put("realName", "");
				map.put("state", "closed");	
				mapList.add(map);
				parentMap=map;
			}
			List<Map<String, Object>> children = null;
			if(parentMap.get("children")==null)
			{
				children = new ArrayList<Map<String,Object>>();
				parentMap.put("children", children);
				
			}else {
				children = (List<Map<String, Object>>)parentMap.get("children");
			}
			
			Map<String, Object> child = new HashMap<String, Object>();
			child.put("id", user.getId());
			child.put("department", "");
			child.put("realName", user.getRealName());
			if(!children.contains(child))
			{
				children.add(child);
			}
			
		}
		
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
		
	}
	
	public void addReaders()
	{
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		
		Map<String, Object> map = new HashMap<String, Object>();
		TblStudyFileIndex index = tblStudyFileIndexService.getByStudyNoAndFileType(model.getStudyNo(), model.getFileType());//方案或者报告
		//readers
		//List<User> users = userService.findByPrivilegeName(readers);
		List<User> users = userService.getByIds(readers.split(","));
		if(users!=null)
		{
			//判断是否已经存在分发
			List<User> existUserDis = tblStudyFileDisService.getByStudyNoAndUser(model.getStudyNo(), users); 
			if(existUserDis!=null&&existUserDis.size()>0){
				users.removeAll(existUserDis);
				map.put("existDis", true);
				map.put("msg", "存在重复分发的用户");
			}
			//接收者列表
			String receiverList = "";
			for(User user:users)
			{
				TblStudyFileDis dis = new TblStudyFileDis();
				String key = tblStudyFileDisService.getKey("TblStudyFileDis");
				dis.setDisId(key);
				dis.setStudyNo(model.getStudyNo());
				dis.setDisTime(new Date());
				dis.setReader(user.getRealName());
				dis.setReadFlag(0);//0未读，1已读
				dis.setTblStudyFileIndex(index);
				
				receiverList+=(user.getUserName())+",";
				tblStudyFileDisService.save(dis);
				
			}
			String msg="的";
			if( model.getFileType()==1)
			{
				msg+="方案";
			}else if( model.getFileType()==2){
				msg+="报告";
			}
			msg+="文件，请及时查看";
			//通知消息
			//通知被分发的人
			String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");
			
			String msgTitle=("分发专题编号:"+model.getStudyNo()+msg);
			String msgContent = "<br>";
			
			msgContent = msgContent+"SD("+tempUser.getRealName()+")于"+currentDate+" 分发了专题编号："+model.getStudyNo()+msg+"，<br>特此提醒";
			
			map.put("msgTitle", msgTitle);
			map.put("msgContent", msgContent);
			map.put("receiverList", receiverList);
			
			map.put("success", true);
		}else {
			map.put("success", false);
			map.put("msg", "不存在所选用户");
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	
	}
	public void sendNotification()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<String> receList = new ArrayList<String>();
			if(receiverList!=null)
			{
				String[] resList = receiverList.split(",");
				if(resList!=null&&resList.length>0)
				{
					for(String str:resList)
					{
						receList.add(str);
					}
					
				}
			}
			TblNotification tblNotification = new TblNotification();
			tblNotification.setId(tblNotificationService.getKey("TblNotification"));
			
			tblNotification.setMsgTitle(msgTitle);//消息头
			tblNotification.setMsgContent(msgContent);
			tblNotification.setMsgType(1);//系统消息
			tblNotification.setSender(getCurrentRealName());// 发送者
			tblNotification.setSendTime(new Date());// 发送时间
			
			tblNotificationService.save(tblNotification,receList);
			map.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg","发送邮件过程中出现异常"+e.getMessage());
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	//判断是否存在没有完成的文件分发，如果存在则其他权限不显示专题的也要显示专题信息
	public void isExistNotFinishDiv()
	{
		User user = (User) ActionContext.getContext().getSession().get("user");
		
		boolean flag = tblStudyFileDisService.isExistNoFinishByUser(user);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isExist", flag);
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	public void isNoFinishFileDisByType()
	{
		User user = (User) ActionContext.getContext().getSession().get("user");
		
		boolean flag = tblStudyFileIndexService.getByStudyNoAndTypeAndUser(model.getStudyNo(),model.getFileType(),user);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("exist", flag);
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public void finishFileDis()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) ActionContext.getContext().getSession().get("user");
		TblStudyFileDis tblStudyFileDis = tblStudyFileDisService.getNoFinishByStudyNoAndUser(model.getStudyNo(),user);
		//tblStudyFileIndexService.finishFileDis(model.getStudyNo(),model.getFileType(),user);
		if(tblStudyFileDis!=null){
			tblStudyFileDis.setReadFlag(1);
			tblStudyFileDisService.update(tblStudyFileDis);
			writeES("完成分发阅读签字", 843, "TblStudyFileDis", tblStudyFileDis.getDisId());
			map.put("success", true);
		}else{
			map.put("success", false);
			map.put("msg","不存在没有完成阅读的分发！");
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
 	public void studyProgramApproval()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		QAStudyChkIndex qAStudyChkIndex = qAStudyChkIndexService.getByStudyNo(model.getStudyNo());
		
		if(model.getFileType()==1)//1：方案；2：报告；
		{
			if(result==-1)
			{
				//"基于研究的检查计划指定完毕",801,"QAStudyChkIndex",studyNoParam
				writeES("FM退回方案",812,"QAStudyChkIndex",model.getStudyNo());
				//日志录入
				writeLog("方案审批","FM审批方案","FM审批方案，方案专题编号："+model.getStudyNo());
			}else if(result==2){
				writeES("方案审批通过",813,"QAStudyChkIndex",model.getStudyNo());
			}
						
			qAStudyChkIndex.setStudyPlanState(result);//方案审批
			qAStudyChkIndex.setStudyPlanTime(new Date());
			
		}else if(model.getFileType()==2){
			if(result==-1)
			{
				//"基于研究的检查计划指定完毕",801,"QAStudyChkIndex",studyNoParam
				writeES("FM退回报告",814,"QAStudyChkIndex",model.getStudyNo());
				//日志录入
				writeLog("报告审批","FM审批报告","FM审批报告，方案专题编号："+model.getStudyNo());
			}else if(result==2){
				writeES("报告审批通过",815,"QAStudyChkIndex",model.getStudyNo());
			}
			qAStudyChkIndex.setReportState(result);
			
		}
		
		qAStudyChkIndexService.update(qAStudyChkIndex);
		
		TblStudyFileIndex fileIndex = tblStudyFileIndexService.getByStudyNoAndFileType(model.getStudyNo(), model.getFileType());
		fileIndex.setFileState(result);
		tblStudyFileIndexService.update(fileIndex);
		
		
		map.put("success", true);
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	private void writeES(String EsTypeDesc,int EsType,String tableName,String dataId)
	{
		//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		//验证通过则进行一下操作
		
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		es.setSigner(tempUser.getRealName());
		es.setEsTypeDesc(EsTypeDesc);
		es.setEsType(EsType);
		es.setDateTime(new Date());
		String eid = tblESService.getKey("TblES");
		es.setEsId(eid);
	
		tblESService.save(es);
		
		esLink.setTableName(tableName);
		esLink.setDataId(dataId);
		esLink.setTblES(es);
		esLink.setEsType(EsType);
		esLink.setEsTypeDesc(EsTypeDesc+"签字确认");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		tblESLinkService.save(esLink);
		
	}
	/**
	 * 写日志
	 * @return
	 */
	private void writeLog(String operatType,String operatOjbect,String operatContent){
		
		  TblLog tblLog = new TblLog();
		  tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		  tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		  tblLog.setOperatType(operatType);
		  tblLog.setOperatOject(operatOjbect);
		  User user = (User) ActionContext.getContext().getSession().get("user");
		  if(null!=user){
			  tblLog.setOperator(user.getRealName());
		  }
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
	
	public TblStudyFileIndexService getTblStudyFileIndexService() {
		return tblStudyFileIndexService;
	}

	public void setTblStudyFileIndexService(
			TblStudyFileIndexService tblStudyFileIndexService) {
		this.tblStudyFileIndexService = tblStudyFileIndexService;
	}
	
	public TblStudyFileService getTblStudyFileService() {
		return tblStudyFileService;
	}
	public void setTblStudyFileService(TblStudyFileService tblStudyFileService) {
		this.tblStudyFileService = tblStudyFileService;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public TblESService getTblESService() {
		return tblESService;
	}
	public void setTblESService(TblESService tblESService) {
		this.tblESService = tblESService;
	}
	public TblESLinkService getTblESLinkService() {
		return tblESLinkService;
	}
	public void setTblESLinkService(TblESLinkService tblESLinkService) {
		this.tblESLinkService = tblESLinkService;
	}
	public String getReaders() {
		return readers;
	}
	public void setReaders(String readers) {
		this.readers = readers;
	}
	public TblStudyFileDisService getTblStudyFileDisService() {
		return tblStudyFileDisService;
	}
	public QAStudyChkIndexService getqAStudyChkIndexService() {
		return qAStudyChkIndexService;
	}
	public void setqAStudyChkIndexService(
			QAStudyChkIndexService qAStudyChkIndexService) {
		this.qAStudyChkIndexService = qAStudyChkIndexService;
	}
	public void setTblStudyFileDisService(
			TblStudyFileDisService tblStudyFileDisService) {
		this.tblStudyFileDisService = tblStudyFileDisService;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public TblStudyItemService getTblStudyItemService() {
		return tblStudyItemService;
	}
	public void setTblStudyItemService(TblStudyItemService tblStudyItemService) {
		this.tblStudyItemService = tblStudyItemService;
	}
	public TblStudyPlanService getTblStudyPlanService() {
		return tblStudyPlanService;
	}
	public void setTblStudyPlanService(TblStudyPlanService tblStudyPlanService) {
		this.tblStudyPlanService = tblStudyPlanService;
	}
	public String getCanUploadFile() {
		return canUploadFile;
	}
	public void setCanUploadFile(String canUploadFile) {
		this.canUploadFile = canUploadFile;
	}
	public TblNotificationService getTblNotificationService() {
		return tblNotificationService;
	}
	public void setTblNotificationService(
			TblNotificationService tblNotificationService) {
		this.tblNotificationService = tblNotificationService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public TblTestItemService getTblTestItemService() {
		return tblTestItemService;
	}
	public void setTblTestItemService(TblTestItemService tblTestItemService) {
		this.tblTestItemService = tblTestItemService;
	}
	public DictStudyTypeService getDictStudyTypeService() {
		return dictStudyTypeService;
	}
	public void setDictStudyTypeService(DictStudyTypeService dictStudyTypeService) {
		this.dictStudyTypeService = dictStudyTypeService;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getReceiverList() {
		return receiverList;
	}
	public void setReceiverList(String receiverList) {
		this.receiverList = receiverList;
	}
	
	
	

}
