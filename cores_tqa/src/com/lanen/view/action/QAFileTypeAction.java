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
import com.lanen.jsonAndModel.ComboTreeModel;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.qa.QAFileType;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.qa.QAFileTypeService;
import com.lanen.service.qa.QALearnTaskService;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class QAFileTypeAction extends BaseAction<QAFileType> {

	private static final long serialVersionUID = -7170110893332860555L;
	
	@Resource
	private QAFileTypeService qAFileTypeService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblESService tblESService;
	@Resource
	private QALearnTaskService qALearnTaskService;
	
	private String noFileTypeId;
	
	public void save()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isExist = qAFileTypeService.isExistByFileTypeAndNameAndParent(model.getFileType(),model.getFileTypeName(),model.getParentFileTypeId());
		if(!isExist)
		{
			QAFileType type = new QAFileType();
			String key = qAFileTypeService.getKey("QAFileType");
			type.setFileTypeId(key);
			type.setFileType(model.getFileType());
			type.setFileTypeName(model.getFileTypeName());
			if(model.getParentFileTypeId()!=null)
				type.setParentFileTypeId(model.getParentFileTypeId());
			
			qAFileTypeService.save(type);
			map.put("success", true);
			map.put("id", key);
			map.put("text", model.getFileTypeName());
			map.put("fileType", model.getFileType());
			map.put("parentId",model.getParentFileTypeId());
			
		}else {
			map.put("success", false);
			map.put("msg", "本条信息已经存在！");
		}
	
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void editSave()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isExist = qAFileTypeService.isExistByFileTypeAndNameAndParent(model.getFileType(),model.getFileTypeName(),model.getParentFileTypeId());
		if(!isExist)
		{
			QAFileType qAFileTypeq = qAFileTypeService.getById(model.getFileTypeId());
			if(qAFileTypeq.getFileType().equals(model.getFileType())&&qAFileTypeq.getFileTypeName().equals(model.getFileTypeName())&&qAFileTypeq.getParentFileTypeId().equals(model.getParentFileTypeId()))
			{
				map.put("success", false);
				map.put("msg", "没有做任何修改！");
			}else
			{
				qAFileTypeq.setFileType(model.getFileType());
				qAFileTypeq.setFileTypeName(model.getFileTypeName());
				if(model.getParentFileTypeId()!=null)
					qAFileTypeq.setParentFileTypeId(model.getParentFileTypeId());
				
				qAFileTypeService.update(qAFileTypeq);
				map.put("success", true);
				map.put("id", model.getFileTypeId());
				map.put("text", model.getFileTypeName());
				map.put("fileType", model.getFileType());
				map.put("parentId",model.getParentFileTypeId());
			}
	
		}else {
			map.put("success", false);
			map.put("msg", "相同信息的数据已经存在！");
		}
	
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void del()
	{
		QAFileType qAFileType = qAFileTypeService.getById(model.getFileTypeId());
		qAFileTypeService.delete(model.getFileTypeId());
		//签名写入
		writeES("QAM删除一个文件类型",825,"QAFileType",qAFileType.getFileTypeName());
		
		//日志录入
		writeLog("删除文件类型","QAM删除一个文件类型","QAM删除一个文件类型,类型名为："+qAFileType.getFileTypeName());
	}
	public void getById()
	{
		
		QAFileType qAFileType= qAFileTypeService.getById(model.getFileTypeId());
		String[] _nory_formatStrings = {"fileTypeId","fileType","fileTypeName","parentFileTypeId"};
		
		String json = JsonPluginsUtil.beanToJson(qAFileType,_nory_formatStrings,true);
		
		writeJson(json);
	}
	
	public void isExistChildFileType()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		List<QAFileType> set=qAFileTypeService.getListByParentId(model.getFileTypeId());
		if(set==null||set.size()==0)
		{
			//没有子文件类型，看该文件类型下的文件是否有学习任务。如果存在则不可删除
			boolean flag = qALearnTaskService.isExistTaskByFileType(model.getFileTypeId());
			map.put("isExistTask", flag);
			
			map.put("isExistChild", false);
		}else {
			map.put("isExistChild", true);
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public void loadListByFileType()
	{
		List<QAFileType> list = qAFileTypeService.getListByFileType(model.getFileType());
		int index=-1;
		for(QAFileType type:list)
		{
			if(type.getFileTypeId().equals(noFileTypeId))
				index = list.indexOf(type);
		}
		if(index!=-1)
			list.remove(index);
		
		String[] _nory_formatStrings = {"fileTypeId","fileType","fileTypeName","parentFileTypeId"};
		String json = JsonPluginsUtil.beanListToJson(list,_nory_formatStrings,true);
		writeJson(json);
	}
	public void loadList()
	{
		List<ComboTreeModel> tree = new ArrayList<ComboTreeModel>();
		
		ComboTreeModel ctm1 = new ComboTreeModel();
		ctm1.setId(""+1);
		ctm1.setText("法规");
		ctm1.setChildren(new ArrayList<ComboTreeModel>());
		tree.add(ctm1);
		
		ComboTreeModel ctm2 = new ComboTreeModel();
		ctm2.setId(""+2);
		ctm2.setText("指导原则");
		ctm2.setChildren(new ArrayList<ComboTreeModel>());
		tree.add(ctm2);
		
		ComboTreeModel ctm3 = new ComboTreeModel();
		ctm3.setId(""+3);
		ctm3.setText("SOP");
		ctm3.setChildren(new ArrayList<ComboTreeModel>());
		tree.add(ctm3);
		
		
		
		List<QAFileType> list = qAFileTypeService.findAll();
		//生成树形结构
		getTree(list,tree);
		
		String json = JsonPluginsUtil.beanListToJson(tree);
		writeJson(json);
		
		
	}
	public void getTree(List<QAFileType> list,List<ComboTreeModel> tree)
	{
		List<QAFileType> noDealList = new ArrayList<QAFileType>();
		ComboTreeModel ctm = null;
		ComboTreeModel ctm1 = tree.get(0);
		ComboTreeModel ctm2 = tree.get(1);
		ComboTreeModel ctm3 = tree.get(2);
		
		for(int i=0;i<list.size();i++)
		{
			QAFileType type=list.get(i);
			if(type.getParentFileTypeId()==null||"".equals(type.getParentFileTypeId()))
			{
				//没有父类就是第一级，直接按照fileType加入tree
				ctm = new ComboTreeModel();
				ctm.setId(type.getFileTypeId());
				ctm.setText(type.getFileTypeName());
				if(type.getFileType()==1)
					ctm1.getChildren().add(ctm);
				else if(type.getFileType()==2)
					ctm2.getChildren().add(ctm);
				else if(type.getFileType()==3)
					ctm3.getChildren().add(ctm);
				//tree.add(ctm);
				
			}else {//有父类的处理
				
				ComboTreeModel parent = getParent(type,tree);
				if(parent!=null)//父类不为空，并且父类在tree中存在
				{
					ComboTreeModel ctmChile = new ComboTreeModel();
					ctmChile.setId(type.getFileTypeId());
					ctmChile.setText(type.getFileTypeName());
					if(parent.getChildren()==null){
						parent.setState("closed");
						parent.setChildren(new ArrayList<ComboTreeModel>());
					}
					parent.getChildren().add(ctmChile);
				}else {//父类不为空，并且tree中不存在,先处理list中的其他的
					noDealList.add(type);
				}
			}
			
		}
		if(noDealList.size()>0)
		{
			getTree(noDealList, tree);
		}
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
	
	public ComboTreeModel getParent(QAFileType type,List<ComboTreeModel> tree)
	{
		ComboTreeModel parent = null;
		for(ComboTreeModel model:tree)
		{
			if(model.getId().equals(type.getParentFileTypeId()))
			{
				parent=model;
				break;
			}
			if(model.getChildren()!=null)
			{
				parent=getParent(type,model.getChildren());
				if(parent!=null)
				{
					break;
				}
			}
		}
		
		return parent;
		
	}
	public QAFileTypeService getqAFileTypeService() {
		return qAFileTypeService;
	}

	public void setqAFileTypeService(QAFileTypeService qAFileTypeService) {
		this.qAFileTypeService = qAFileTypeService;
	}
	public String getNoFileTypeId() {
		return noFileTypeId;
	}
	public void setNoFileTypeId(String noFileTypeId) {
		this.noFileTypeId = noFileTypeId;
	}
	

}
