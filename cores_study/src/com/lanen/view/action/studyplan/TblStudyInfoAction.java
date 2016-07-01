package com.lanen.view.action.studyplan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.ComboTreeModel;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.schedule.TblAnimalHouse;
import com.lanen.model.schedule.TblStudyInfo;
import com.lanen.service.schdeule.TblAnimalHouseService;
import com.lanen.service.schdeule.TblStudyInfoService;

@Controller
@Scope("prototype")
public class TblStudyInfoAction extends BaseAction<TblStudyInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private TblStudyInfoService tblStudyInfoService;
	
	@Resource
	private TblAnimalHouseService tblAnimalHouseService;
	
	/**list展示*/
	public void loadRes(){
		List<ComboTreeModel> list= tblStudyInfoService.loadAnimalHouseTable();
		String json = JSONArray.fromObject(list).toString();// 转化为JSON
		writeJson(json);
	}
	
	public void initTblStudyInfoBut(){
		TblStudyInfo tblStudyInfo = tblStudyInfoService.getByStudyNo(model.getStudyNo());
		Map<String,Object> map = new HashMap<String,Object>();
		if(null == tblStudyInfo){
			 map.put("success",false);
		}else{
			 map.put("success",true);
			 TblAnimalHouse tblAnimalHouse = tblAnimalHouseService.getById(tblStudyInfo.getResID());
			 TblAnimalHouse ptblAnimalHouse = tblAnimalHouseService.getById(tblAnimalHouse.getParentId());
			 map.put("resName",ptblAnimalHouse.getResName()+" - "+tblAnimalHouse.getResName());
			 if(null == tblStudyInfo.getScheduleReviewSignID() || tblStudyInfo.getScheduleReviewSignID().equals("") ){
				 map.put("sign", false);
			 }else{
				 map.put("sign", true);
			 }
		}
       
		String json= JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	public void saveRes(){
		TblStudyInfo studyInfo = new TblStudyInfo();
		TblStudyInfo tblStudyInfo = tblStudyInfoService.getByStudyNo(model.getStudyNo());
		if(null == tblStudyInfo ){
			String id = tblStudyInfoService.getKey();
			studyInfo.setId(id);
			studyInfo.setResID(model.getResID());
			studyInfo.setStudyNo(model.getStudyNo());
			tblStudyInfoService.save(studyInfo);
		}else{
			tblStudyInfo.setResID(model.getResID());
			tblStudyInfo.setStudyNo(model.getStudyNo());
			tblStudyInfoService.update(tblStudyInfo);
		}
		Map<String,Object> map = new HashMap<String,Object>();
        map.put("success",true);
		String json= JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		
	}
	

}
