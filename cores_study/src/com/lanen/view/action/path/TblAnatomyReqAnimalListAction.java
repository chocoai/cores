package com.lanen.view.action.path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.path.TblAnatomyReqAnimalList;
import com.lanen.model.path.TblAnatomyReqAnimalList_Json;
import com.lanen.service.path.TblAnatomyReqAnimalListService;
@Controller
@Scope("prototype")
public class TblAnatomyReqAnimalListAction extends BaseAction<TblAnatomyReqAnimalList> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -148758049825141866L;
    /**
     * 解剖申请-申请解剖动物列表     service 
     */
    @Resource
    private TblAnatomyReqAnimalListService tblAnatomyReqAnimalListService;
    
    /**
	 * 课题编号
	 */
	private String studyNoPara;
	/**
	 * 判断添加，编辑，查看
	 */
	private String addOrEdit;
	
    /**获取课题下所有还未被提交申请解剖的动物*/
    public void getAllAnimalByStudyNo(){
    	//TODO
    	List<?> sqlList=tblAnatomyReqAnimalListService.getAllAnimalByStudyNo(studyNoPara);
    	List<TblAnatomyReqAnimalList> list=new ArrayList<TblAnatomyReqAnimalList>();
    	if(sqlList!=null&&sqlList.size()>0){
    		for(Object obj:sqlList){
    			Object[] objs=(Object[])obj;
    			TblAnatomyReqAnimalList tblAnatomyReqAnimalList=new TblAnatomyReqAnimalList();
        		tblAnatomyReqAnimalList.setAnimalCode((String)objs[0]);
        		tblAnatomyReqAnimalList.setGender((Integer)objs[1]);
        		list.add(tblAnatomyReqAnimalList);
    		}
    	}
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("rows", list);
    	map.put("total", list.size());
    	String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
    }
    /**
     * 获取编辑数据
     */
    public void toEdit(){
    	List<TblAnatomyReqAnimalList> list=tblAnatomyReqAnimalListService.getListByStudyNoAndReqNo(studyNoPara,model.getAnatomyReqNo());
    	List<TblAnatomyReqAnimalList_Json> list2=new ArrayList<TblAnatomyReqAnimalList_Json>();
    	if(list!=null&&list.size()>0){
    		for(TblAnatomyReqAnimalList aral:list){
    			TblAnatomyReqAnimalList_Json json=new TblAnatomyReqAnimalList_Json();
    			json.setAnatomyReqNo(aral.getAnatomyReqNo());
    			json.setGender(aral.getGender());
    			json.setId(aral.getId());
    			json.setAnimalCode(aral.getAnimalCode());
//    			//查询课题下动物是否已被申请解剖（解剖申请提交标志变为大于0），如果已存在返回1，不存在返回0
//    			int isAnatomyReq=tblAnatomyReqAnimalListService.isHaveAnatomyReq(studyNoPara, aral.getAnimalCode());
    			//查询课题下动物是否已被申请解剖（解剖申请提交标志变为1），如果已存在返回1，不存在返回0
    			int isAnatomyReq=tblAnatomyReqAnimalListService.isHaveAnatomyReq(studyNoPara, aral.getAnimalCode());
    			json.setIsAnatomyReq(isAnatomyReq);
    			json.setAddOrEdit(addOrEdit);
    			list2.add(json);
    		}
    	}
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("rows", list2);
    	map.put("total", list2.size());
    	String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
    }
    /**
     * 提交前检查是否有动物已被申请
     */
    public void submitCheck(){
    	Integer haveReqAnimalNumber=tblAnatomyReqAnimalListService.getAnimalNumberByStudyNoAndReqNo(model.getStudyNo(),model.getAnatomyReqNo());
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("haveReqAnimalNumber", haveReqAnimalNumber);
    	String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
    }
	public void setStudyNoPara(String studyNoPara) {
		this.studyNoPara = studyNoPara;
	}

	public String getStudyNoPara() {
		return studyNoPara;
	}
	public String getAddOrEdit() {
		return addOrEdit;
	}
	public void setAddOrEdit(String addOrEdit) {
		this.addOrEdit = addOrEdit;
	}
	
}
