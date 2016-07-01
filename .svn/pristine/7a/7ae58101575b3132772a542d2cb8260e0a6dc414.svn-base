package com.lanen.view.action.contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.contract.TblStudySchedule;
import com.lanen.model.contract.TblStudySchedule_Json;
import com.lanen.service.contract.TblStudyScheduleService;
import com.lanen.util.DateUtil;

/**
 * 项目进度
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class TblStudyScheduleAction extends BaseAction<TblStudySchedule> {

	private static final long serialVersionUID = 7952968889030133328L;
	
	@Resource
	private TblStudyScheduleService tblStudyScheduleService;
	
	private String contractCode;
	
	public String list(){
		return  "list";
	}
	
	public void loadList(){
		List<?> list = tblStudyScheduleService.getTblStudyScheduleByContractCode(contractCode);
		List<TblStudySchedule_Json> listjson = new ArrayList<TblStudySchedule_Json>();
		if (null != list) {
			String studyNos = "";
			for (Object obj : list) {
					Object[] objs= (Object[]) obj;
					if( objs[8] != null &&  (!(studyNos.contains((String)objs[8])) || studyNos.equals("")) ){
						TblStudySchedule_Json json = new TblStudySchedule_Json();
						json.setId((String)objs[8]);
						json.setStudyNo((String)objs[8]);
						studyNos =studyNos + "@"+(String)objs[8]+"@";
						json.setTiNo((String)objs[1]);
						json.setTiType((String)objs[4]);
						json.setStudyName((String)objs[7]);
						if(objs[9] != null){
							json.setSd("SD :"+(String)objs[9]);
						}
						json.setIconCls("icon-space");
						json.setState("closed");
					    String  progress= tblStudyScheduleService.getPercentageByStudyNo((String)objs[8]);
						double aa=Double.parseDouble(progress);
						String    p  =Double.toString(aa * 100);
						TblStudySchedule schedule =  tblStudyScheduleService.getByStudyNoMaxStudySchedule((String)objs[8]);
						json.setProgress(p+"#"+schedule.getNodeName()+"#"+DateUtil.dateToString(schedule.getActualDate(), "yyyy-MM-dd")+"#"+DateUtil.dateToString(schedule.getPlanDate(), "yyyy-MM-dd"));
						listjson.add(json);
						
						List<TblStudySchedule> slist = tblStudyScheduleService.getListByStudyNo((String)objs[8]);
						for(TblStudySchedule obj1:slist){
						    json = new TblStudySchedule_Json();
						    json.setId(UUID.randomUUID().toString()+obj1.getId());
						    json.set_parentId((String)objs[8]);
						    json.setStudyNo(obj1.getNodeName());
						    json.setTiNo(DateUtil.dateToString(obj1.getPlanDate(), "yyyy-MM-dd"));
						    json.setStudyName(DateUtil.dateToString(obj1.getActualDate(), "yyyy-MM-dd"));
						    json.setIconCls("icon-space");
						    listjson.add(json);
						}
				   }
			}
		}
		Map<String, Object> map=new HashMap<String, Object>();
   	    map.put("rows", listjson);
		map.put("total", list.size());
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	

}
