package com.lanen.view.action.path;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.path.DictViscera;
import com.lanen.model.path.TblPathPlanAttachedViscera;
import com.lanen.model.path.TblPathPlanVisceraWeigh;
import com.lanen.service.path.DictVisceraService;
import com.lanen.service.path.TblPathPlanAttachedVisceraService;
import com.lanen.service.path.TblPathPlanVisceraWeighService;
@Controller
@Scope("prototype")
public class TblPathPlanAttachedVisceraAction extends BaseAction<TblPathPlanAttachedViscera> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2789854948218850684L;
    /**
     * 病理计划-脏器称重-附加脏器     service 
     */
    @Resource
    private TblPathPlanAttachedVisceraService tblPathPlanAttachedVisceraService;
    /**
     * 脏器字典     service 
     */
    @Resource
    private DictVisceraService dictVisceraService;
    /**
     * 病理计划-脏器称重    service 
     */
    @Resource
    private TblPathPlanVisceraWeighService tblPathPlanVisceraWeighService;
    
    /**
     * 加载附加脏器
     */
    public void loadList(){
    	List<TblPathPlanAttachedViscera> list=tblPathPlanAttachedVisceraService.getListByPid(model.getVisceraWeighPlanID());
		Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows", list);
		 map.put("total", list.size());
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
    }
    /**
     * 添加附加脏器
     */
    public void addPathPlanAttachedViscera(){
    	Map<String,Object> map = new HashMap<String, Object>();
    	//根据病理计划-脏器称重-附加脏器中的脏器称重表ID查询脏器称重实体
    	TblPathPlanVisceraWeigh tblPathPlanVisceraWeigh=tblPathPlanVisceraWeighService.getById(model.getVisceraWeighPlanID());
    	//添加附加脏器，因此将对应的脏器称重实体的有无附加脏器标志设为1（0,无，1.有）
    	tblPathPlanVisceraWeigh.setAttachedVisceraFlag(1);
    	TblPathPlanAttachedViscera tblPathPlanAttachedViscera=new TblPathPlanAttachedViscera();
		String id=tblPathPlanAttachedVisceraService.getKey();
		tblPathPlanAttachedViscera.setId(id);
		tblPathPlanAttachedViscera.setVisceraWeighPlanID(model.getVisceraWeighPlanID());
		String visceraCode=model.getVisceraCode();
		DictViscera dictViscera=dictVisceraService.getById(visceraCode);
		if(dictViscera!=null){
			tblPathPlanAttachedViscera.setVisceraCode(visceraCode);
			tblPathPlanAttachedViscera.setVisceraName(dictViscera.getVisceraName());
			tblPathPlanAttachedViscera.setVisceraType(dictViscera.getVisceraType());
//			tblPathPlanVisceraWeigh.setGender(dictViscera.getGender());
		}
		tblPathPlanAttachedVisceraService.addSave(tblPathPlanAttachedViscera,tblPathPlanVisceraWeigh);
//		tblPathPlanAttachedVisceraService.save(tblPathPlanAttachedViscera);
//		tblPathPlanVisceraWeighService.update(tblPathPlanVisceraWeigh);
		map.put("success",true);
		map.put("msg","添加成功");
		map.put("id", tblPathPlanAttachedViscera.getId());
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
    }
    /**
     * 删除附加脏器
     */
    public void deletePathPlanAttachedViscera(){
    	Map<String,Object> map = new HashMap<String, Object>();
		if(model.getId()!=null){
			tblPathPlanAttachedVisceraService.delete(model.getId());
			map.put("success",true);
			map.put("msg","删除成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
    }
}
