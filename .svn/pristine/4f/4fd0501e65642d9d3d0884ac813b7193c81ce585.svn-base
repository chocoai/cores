package com.lanen.view.action.studyplan;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.studyplan.DictReportNumber;
import com.lanen.service.studyplan.DictReportNumberService;

/**报表编号
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class DictReportNumberAction extends BaseAction<DictReportNumber>{

	private static final long serialVersionUID = 1097314789091013789L;
	
	/**
	 * 报表编号service
	 */
	@Resource
	private DictReportNumberService dictReportNumberService;
	/**list.jsp
	 * @return
	 */
	public String list(){
		
		return "list";
	}
	
	/**加载列表数据
	 * @return
	 */
	public void loadList(){
		List<DictReportNumber> list = dictReportNumberService.findAll();
		String jsonStr = JsonPluginsUtil.beanListToJson(list);
		writeJson(jsonStr);
	}
	
	/**
	 * 保存数据
	 */
	public void save(){
		Json json = new Json();
		if(null != model.getId() && null != model.getNumber()){
			DictReportNumber dictReportNumber = dictReportNumberService.getById(model.getId());
			dictReportNumber.setNumber(model.getNumber());
			dictReportNumberService.update(dictReportNumber);
			json.setSuccess(true);
		}else{
			json.setMsg("与服务器交互错误！");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
		
	}

}
