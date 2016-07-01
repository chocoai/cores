package com.lanen.view.action.qa;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.qa.QAFileReg;
import com.lanen.model.qa.QAFileType;
import com.lanen.service.qa.QAFileRegService;
import com.lanen.service.qa.QAFileTypeService;

@Controller
@Scope("prototype")
public class QAFileRegAction extends BaseAction<QAFileReg> {

	private static final long serialVersionUID = 2269399178386702788L;
	@Resource
	private QAFileRegService qAFileRegService;
	@Resource
	private QAFileTypeService qAFileTypeService;
	
	private String fileTypeId;
	public String list()
	{
		return "list";
	}
	public void save()
	{
		//boolean flag = qAFileRegService.isExistToAdd(model);
		QAFileType type = qAFileTypeService.getById(fileTypeId);
		QAFileReg reg = new QAFileReg();
		String key = qAFileRegService.getKey("QAFileReg");
		reg.setFileRegId(key);
		reg.setFileCode(model.getFileCode());
		reg.setFileName(model.getFileName());
		reg.setFilePublishDepartment(model.getFilePublishDepartment());
		reg.setFilePublishTime(model.getFilePublishTime());
		reg.setFileType(type.getFileType());
		reg.setFileTypeName(type.getFileTypeName());
		reg.setFileVersion(model.getFileVersion());
		
		reg.setIsVersionUpdate(model.getIsVersionUpdate());
		reg.setQafileType(type);
		reg.setRemark(model.getRemark());
		
		qAFileRegService.save(reg);
			
			
	}
	public void loadListByType()
	{
		List<QAFileReg> list = qAFileRegService.getByType(fileTypeId);
		
	     String[] _nory_format={"fileRegId", "fileType", "fileTypeName","fileCode","fileName","fileVersion","filePublishTime","filePublishDepartment","remark","isVersionUpdate"};
	     
	     String json=JsonPluginsUtil.beanListToJson(list,_nory_format,true);
	     writeJson(json);
	
	}

	public String getFileTypeId() {
		return fileTypeId;
	}

	public void setFileTypeId(String fileTypeId) {
		this.fileTypeId = fileTypeId;
	}
	public QAFileRegService getqAFileRegService() {
		return qAFileRegService;
	}

	public void setqAFileRegService(QAFileRegService qAFileRegService) {
		this.qAFileRegService = qAFileRegService;
	}


	public QAFileTypeService getqAFileTypeService() {
		return qAFileTypeService;
	}


	public void setqAFileTypeService(QAFileTypeService qAFileTypeService) {
		this.qAFileTypeService = qAFileTypeService;
	}


}
