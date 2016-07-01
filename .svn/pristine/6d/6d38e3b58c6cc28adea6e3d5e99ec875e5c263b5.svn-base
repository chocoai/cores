package com.lanen.view.action.qa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.swing.tree.TreeNode;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.ComboTreeModel;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.qa.QAFileType;
import com.lanen.service.qa.QAFileTypeService;
@Controller
@Scope("prototype")
public class QAFileTypeAction extends BaseAction<QAFileType> {

	private static final long serialVersionUID = -7170110893332860555L;
	
	@Resource
	private QAFileTypeService qAFileTypeService;
	
	
	public void getById()
	{
		
		QAFileType qAFileType= qAFileTypeService.getById(model.getFileTypeId());
		String[] _nory_formatStrings = {"fileTypeId","fileType","fileTypeName","parentFileTypeId"};
		
		String json = JsonPluginsUtil.beanToJson(qAFileType,_nory_formatStrings,true);
		
		writeJson(json);
	}
	
	public void loadListByFileType()
	{
		List<QAFileType> list = qAFileTypeService.getListByFileType(model.getFileType());
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
					if(parent.getChildren()==null)
						parent.setChildren(new ArrayList<ComboTreeModel>());
					parent.getChildren().add(ctmChile);
				}else {//父类不为空，并且tree中不存在,先处理list中的其他的
					noDealList.add(type);
				}
			}
			
		}
		if(noDealList!=null&&noDealList.size()>0)
		{
			getTree(noDealList, tree);
		}
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
	

}
